package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.NearByInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.NearByAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/9.
 */

public class NearByFragment extends BaseFragment implements NearByInterface {
    private final static String TAG = NearByFragment.class.getSimpleName();
    @Bind(R.id.near_by_recycler)
    SwipeMenuRecyclerView nearByRecycler;
    @Bind(R.id.near_refresh)
    SwipeRefreshLayout nearRefresh;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private LinearLayoutManager layoutManager;
    private int pageIndex = 1, pageCount = 0;
    NearByAdapter nearByAdapter;
    private final static int PAGE_SIZE_DEFAULT = 200;
    private ArrayList<NearByResponse.DataBeanX.DataBean> nearByData = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.near_by_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        nearByRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.near_by_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        nearByRecycler.setLayoutManager(layoutManager);
        nearRefresh = (SwipeRefreshLayout) viewRoot.findViewById(R.id.near_refresh);
        notFoundData = (TextView) viewRoot.findViewById(R.id.not_found_data);
        nearByAdapter = new NearByAdapter(getContext(), null);
        DefineLoadMoreView defineLoadMoreView = new DefineLoadMoreView(getContext());
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        nearByRecycler.addFooterView(loadMoreView); // 添加为Footer。
        nearByRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        nearByRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        loadData(nearByData);
        nearByRecycler.setAdapter(nearByAdapter);
        nearRefresh.setOnRefreshListener(mRefreshListener);

    }

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            nearByRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isAdded()) {
                        return;
                    }
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新 暂时无分页");
                        nearByRecycler.loadMoreFinish(false, true);
                        PaoToastUtils.showLongToast(getActivity(), "没有更多内容");
                        nearByRecycler.loadMoreFinish(false, true);
                        return;
                    } else {
                        if (pageIndex > pageCount) {
                            if (getContext() == null) {
                                return;
                            }
                            PaoToastUtils.showLongToast(getActivity(), "没有更多内容");
                            nearByRecycler.loadMoreFinish(false, true);
                            nearByRecycler.setLoadMoreView(null);
                            nearByRecycler.setLoadMoreListener(null);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }

                   /* Presenter.getInstance(getContext()).getMyCreateCirlce(pageIndex, PAGE_SIZE_DEFAULT, keyWord);*/

                }
            }, 1000);
        }
    };


    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            nearByRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //loadData(nearByData);
                    if (nearRefresh != null) {
                        nearRefresh.setRefreshing(false);
                        LocalLog.d(TAG, "加载数据");
                        reload();
                    }
                }
            }, 2000); // 延时模拟请求服务器。
        }
    };

    synchronized private void reload() {
        nearByData.clear();
        nearByAdapter.notifyDataSetChanged();
        pageIndex = 1;
        pageCount = 0;
        Presenter.getInstance(getContext()).getNearByPeople(Presenter.getInstance(getContext()).getLocation()[0],
                Presenter.getInstance(getContext()).getLocation()[1], pageIndex, PAGE_SIZE_DEFAULT, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        reload();
    }

    /**
     * 第一次加载数据。
     */
    private void loadData(List<?> dataBeans) {

        nearByAdapter.notifyDataSetChanged(dataBeans);

        nearRefresh.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            nearByRecycler.loadMoreFinish(true, true);
        } else {
            nearByRecycler.loadMoreFinish(false, true);
        }
    }

    private void loadMore(ArrayList<NearByResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        nearByData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        nearByAdapter.notifyItemRangeInserted(nearByData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        if (nearByRecycler == null) {
            return;
        }
        nearByRecycler.loadMoreFinish(false, true);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(NearByResponse nearByResponse) {
        LocalLog.d(TAG, "NearByResponse() enter" + nearByResponse);
        if (!isAdded()) {
            return;
        }
        if (nearByResponse.getError() == 0) {
            if (nearByRecycler != null) {
                notFoundData.setVisibility(View.GONE);
                nearRefresh.setVisibility(View.VISIBLE);
                //TODO 数据结构更换
                /*pageCount = nearByResponse.getData().getPagenation().getTotalPage();*/
                LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
                loadMore((ArrayList<NearByResponse.DataBeanX.DataBean>) nearByResponse.getData().getData());
                if (pageIndex == 1) {
                    nearByRecycler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LocalLog.d(TAG, "滑动到顶端");
                            if (nearByRecycler == null) {
                                return;
                            }
                            nearByRecycler.scrollToPosition(0);
                        }
                    }, 10);
                }
                pageIndex++;
            } else {
                LocalLog.d(TAG, "不显示");
            }
        } else if (nearByResponse.getError() == 1) {
            if (pageIndex == 1) {
                nearRefresh.setVisibility(View.GONE);
                notFoundData.setVisibility(View.VISIBLE);
            }
        } else if (nearByResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }

    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}
