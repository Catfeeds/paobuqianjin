package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReleaseRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskMyReleaseResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ReleaseRecordInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyReleaseTaskAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ReleaseRecordAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/25.
 */

public class ReleaseRecordFragment extends BaseBarStyleTextViewFragment implements ReleaseRecordInterface {
    private final static String TAG = ReleaseRecordFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.release_record)
    SwipeMenuRecyclerView releaseRecord;
    LinearLayoutManager layoutManager;
    @Bind(R.id.empty_record_span)
    RelativeLayout emptyRecordSpan;
    @Bind(R.id.release_refresh)
    SwipeRefreshLayout releaseRefresh;
    @Bind(R.id.empty_record)
    ImageView emptyRecord;
    ReleaseRecordAdapter adapter;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGE_SIZE = 10;
    private ArrayList<ReleaseRecordResponse.DataBeanX.DataBean> myReleaseData = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.release_record_fg;
    }

    @Override
    protected String title() {
        return "发布记录";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Presenter.getInstance(getContext()).getReleaseRecord(pageIndex, PAGE_SIZE);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        releaseRefresh = (SwipeRefreshLayout) viewRoot.findViewById(R.id.release_refresh);
        layoutManager = new LinearLayoutManager(getContext());
        releaseRecord = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.release_record);
        releaseRecord.setLayoutManager(layoutManager);

        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        releaseRecord.addFooterView(loadMoreView); // 添加为Footer。
        releaseRecord.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        releaseRecord.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        adapter = new ReleaseRecordAdapter(getContext(), null);
        releaseRecord.setAdapter(adapter);

        releaseRefresh.setOnRefreshListener(mRefreshListener);
        loadData(myReleaseData);
    }

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            releaseRecord.postDelayed(new Runnable() {
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
                            Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();
                            releaseRecord.loadMoreFinish(false, true);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    Presenter.getInstance(getContext()).getReleaseRecord(pageIndex, PAGE_SIZE);
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
            releaseRecord.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(myReleaseData);
                    LocalLog.d(TAG, "加载数据");
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    /**
     * 第一次加载数据。
     */
    private void loadData(ArrayList<ReleaseRecordResponse.DataBeanX.DataBean> dataBeans) {
        if (releaseRefresh == null) {
            return;
        }
        adapter.notifyDataSetChanged(dataBeans);
        releaseRefresh.setRefreshing(false);

        // 第一次加载数据：一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        if (dataBeans == null || dataBeans.size() == 0) {
            releaseRecord.loadMoreFinish(true, true);
        } else {
            releaseRecord.loadMoreFinish(false, true);
        }
    }


    private void loadMore(ArrayList<ReleaseRecordResponse.DataBeanX.DataBean> newData) {
        /*ArrayList<ChoiceCircleResponse.DataBeanX.DataBean> strings = createDataList(adapter.getItemCount(), newData);*/
        myReleaseData.addAll(newData);
        // notifyItemRangeInserted()或者notifyDataSetChanged().
        adapter.notifyItemRangeInserted(myReleaseData.size() - newData.size(), newData.size());

        // 数据完更多数据，一定要掉用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        if (releaseRecord == null) {
            return;
        }
        releaseRecord.loadMoreFinish(false, true);

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
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "ErrorCode() enter " + errorCode.toString());
        if (errorCode.getError() == 1) {
            LocalLog.d(TAG, "没有记录");
        }
    }

    @Override
    public void response(ReleaseRecordResponse releaseRecordResponse) {
        LocalLog.d(TAG, "ReleaseRecordResponse() enter " + releaseRecordResponse.toString());
        if (releaseRecordResponse.getError() == 0) {
            if (releaseRefresh.getVisibility() == View.GONE) {
                releaseRefresh.setVisibility(View.VISIBLE);
            }
            pageCount = releaseRecordResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
            loadMore((ArrayList<ReleaseRecordResponse.DataBeanX.DataBean>) releaseRecordResponse.getData().getData());
            if (pageIndex == 1) {
                releaseRecord.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LocalLog.d(TAG, "滑动到顶端");
                        releaseRecord.scrollToPosition(0);
                    }
                }, 10);
            }
            pageIndex++;
        } else if (releaseRecordResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}
