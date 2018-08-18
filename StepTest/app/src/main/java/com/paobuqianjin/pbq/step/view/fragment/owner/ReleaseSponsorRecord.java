package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorPkgRecordResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ReleaseRecordAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/6/22.
 */

public class ReleaseSponsorRecord extends BaseFragment {
    private final static String TAG = ReleaseSponsorRecord.class.getSimpleName();
    @Bind(R.id.release_record)
    SwipeMenuRecyclerView releaseRecord;
    LinearLayoutManager layoutManager;
    @Bind(R.id.empty_record_span)
    RelativeLayout emptyRecordSpan;
    @Bind(R.id.release_refresh)
    SwipeRefreshLayout releaseRefresh;
    ReleaseRecordAdapter adapter;
    @Bind(R.id.no_record)
    ImageView noRecord;
    @Bind(R.id.no_task)
    TextView noTask;
    @Bind(R.id.go_to_release)
    TextView goToRelease;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGE_SIZE = 10;
    private final static int RELEASE_SPONSOR_TASK = 2;
    private ArrayList<SponsorPkgRecordResponse.DataBeanX.DataBean> myReleaseData = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.release_record_fg;
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
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        releaseRefresh = (SwipeRefreshLayout) viewRoot.findViewById(R.id.release_refresh);
        layoutManager = new LinearLayoutManager(getContext());
        releaseRecord = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.release_record);
        releaseRecord.setLayoutManager(layoutManager);

        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getContext());
        releaseRecord.addFooterView(loadMoreView); // 添加为Footer。
        releaseRecord.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        releaseRecord.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        adapter = new ReleaseRecordAdapter(getActivity(), null);
        releaseRecord.setAdapter(adapter);

        releaseRefresh.setOnRefreshListener(mRefreshListener);
        loadData(myReleaseData);
        getSponsorRecord();
        emptyRecordSpan = (RelativeLayout) viewRoot.findViewById(R.id.empty_record_span);
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
                            /*Toast.makeText(getContext(), "没有更多内容", Toast.LENGTH_SHORT).show();*/
                            PaoToastUtils.showLongToast(getContext(), "没有更多内容");
                            releaseRecord.loadMoreFinish(false, true);
                            releaseRecord.setLoadMoreView(null);
                            releaseRecord.setLoadMoreListener(null);
                            return;
                        }
                    }
                    if (getContext() == null) {
                        return;
                    }
                    getSponsorRecord();
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
    private void loadData(ArrayList<SponsorPkgRecordResponse.DataBeanX.DataBean> dataBeans) {
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


    private void loadMore(ArrayList<SponsorPkgRecordResponse.DataBeanX.DataBean> newData) {
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
    }


    private void getSponsorRecord() {
        /*Presenter.getInstance(getContext()).getReleaseRecord(pageIndex, PAGE_SIZE);*/
        String url = NetApi.urlSponsorRecord + "page=" + String.valueOf(pageIndex) + "&pagesize=" + String.valueOf(PAGE_SIZE);
        LocalLog.d(TAG, "url = " + url);
        Presenter.getInstance(getContext()).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (isAdded()) {
                    try {
                        SponsorPkgRecordResponse sponsorPkgRecordResponse = new Gson().fromJson(s, SponsorPkgRecordResponse.class);
                        response(sponsorPkgRecordResponse);
                    } catch (JsonSyntaxException j) {
                        j.printStackTrace();
                    }
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (e != null) {
                    e.printStackTrace();
                }
                if (errorBean != null) {
                    if (errorBean.getError() == 100) {
                        exitTokenUnfect();
                    } else if (errorBean.getError() == 1) {
                        LocalLog.d(TAG, "没有发布记录");
                        if (pageIndex == 1) {
                            emptyRecordSpan.setVisibility(View.VISIBLE);
                            releaseRecord.setVisibility(View.GONE);
                        }
                        if (releaseRecord == null) {
                            return;
                        }
                        releaseRecord.loadMoreFinish(false, true);
                    }
                }
                pageIndex++;
            }
        });
    }


    private void response(SponsorPkgRecordResponse sponsorPkgRecordResponse) {
        LocalLog.d(TAG, "ReleaseRecordResponse() enter " + sponsorPkgRecordResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (sponsorPkgRecordResponse.getError() == 0) {
            if (releaseRefresh.getVisibility() == View.GONE) {
                releaseRefresh.setVisibility(View.VISIBLE);
                emptyRecordSpan.setVisibility(View.GONE);
            }
            pageCount = sponsorPkgRecordResponse.getData().getPagenation().getTotalPage();
            LocalLog.d(TAG, "pageIndex = " + pageIndex + "pageCount = " + pageCount);
            loadMore((ArrayList<SponsorPkgRecordResponse.DataBeanX.DataBean>) sponsorPkgRecordResponse.getData().getData());
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
        }
    }

    @OnClick(R.id.go_to_release)
    public void onClick() {
        startActivityForResult(new Intent(getContext(), TaskReleaseActivity.class), RELEASE_SPONSOR_TASK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RELEASE_SPONSOR_TASK) {
            getSponsorRecord();
        }
    }
}
