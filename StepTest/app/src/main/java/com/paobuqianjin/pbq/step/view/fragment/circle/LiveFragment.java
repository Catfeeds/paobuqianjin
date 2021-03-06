package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.NetworkUtil;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LiveResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.adapter.LiveAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/5/3.
 */
public class LiveFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = LiveFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.live_recycler)
    SwipeMenuRecyclerView liveRecycler;
    @Bind(R.id.sw_live)
    SwipeRefreshLayout swLive;
    LinearLayoutManager layoutManager;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 10;
    private ArrayList<?> liveData = new ArrayList<>();
    private LiveAdapter liveAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.live_fg;
    }

    @Override
    protected String title() {
        return "社群活动";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        EventBus.getDefault().register(this);
        swLive = (SwipeRefreshLayout) viewRoot.findViewById(R.id.sw_live);
        liveRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.live_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        liveRecycler.setLayoutManager(layoutManager);
        liveRecycler.setHasFixedSize(true);
        liveRecycler.setNestedScrollingEnabled(false);
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        liveRecycler.addFooterView(loadMoreView); // 添加为Footer。
        liveRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        liveRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        Presenter.getInstance(getContext()).getLiveList(innerCallBack, pageIndex, PAGESIZE);
        swLive.setRefreshing(false);
        swLive.setEnabled(false);
    }

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            liveRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新");
                    } else {
                        if (pageIndex > pageCount) {
                            if (getContext() == null) {
                                return;
                            }
                            PaoToastUtils.showLongToast(getContext(), "没有更多内容");
                            liveRecycler.loadMoreFinish(false, true);
                            liveRecycler.setLoadMoreView(null);
                            liveRecycler.setLoadMoreListener(null);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    Presenter.getInstance(getContext()).getLiveList(innerCallBack, pageIndex, PAGESIZE);
                }
            }, 1000);
        }
    };
    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (!isAdded()) {
                return;
            }
            if (object instanceof ErrorCode) {
                Toast.makeText(getContext(), ((ErrorCode) object).getMessage(), Toast.LENGTH_SHORT).show();
            } else if (object instanceof LiveResponse) {
                if (((LiveResponse) object).getError() == 0 && ((LiveResponse) object).getData().getData().size() > 0) {
                    pageCount = ((LiveResponse) object).getData().getPagenation().getTotalPage();
                    if (liveRecycler != null) {
                        if (pageIndex == 1) {
                            liveData.clear();
                            if (liveAdapter == null) {
                                liveData.addAll((ArrayList) ((LiveResponse) object).getData().getData());
                                liveAdapter = new LiveAdapter(getActivity(), liveData);
                                liveRecycler.setAdapter(liveAdapter);
                                liveRecycler.loadMoreFinish(false, true);
                            }
                        } else {
                            liveData.addAll((ArrayList) ((LiveResponse) object).getData().getData());
                            liveAdapter.notifyItemRangeInserted(liveData.size() - ((LiveResponse) object).getData().getData().size(),
                                    ((LiveResponse) object).getData().getData().size());
                            liveRecycler.requestLayout();
                            liveRecycler.loadMoreFinish(false, true);
                        }
                    }
                } else if (((LiveResponse) object).getError() == 1) {

                }
            }
            pageIndex++;
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PaoMessageEvent messageEvent) {
        LocalLog.d(TAG, "领取红包成功，刷新数据");
        pageIndex = 1;
        Presenter.getInstance(getContext()).getLiveList(innerCallBack, pageIndex, PAGESIZE);
    }

    public static class PaoMessageEvent {
        private int actionFlag;

        public PaoMessageEvent(int actionFlag) {
            this.actionFlag = actionFlag;
        }

        public int getActionFlag() {
            return actionFlag;
        }
    }
}
