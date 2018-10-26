package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteTopResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepDollarDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.StepDollarDetailInterface;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.activity.InviteDetailActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.InviteTopAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.StepDollarDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/16.
 */

public class StepDollarDetailFragment extends BaseFragment implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private final static String TAG = StepDollarDetailFragment.class.getSimpleName();
    @Bind(R.id.rv_coupon)
    SwipeMenuRecyclerView rvCoupon;
    @Bind(R.id.linear_empty)
    LinearLayout linearEmpty;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private StepDollarDetailAdapter adapter;
    LinearLayoutManager layoutManager;
    private int currentPage = 1;
    private List<StepDollarDetailResponse.DataBeanX.DataBean> listData = new ArrayList();

    @Override
    protected int getLayoutResId() {
        return R.layout.invite_team_fg;
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
        linearEmpty = (LinearLayout) viewRoot.findViewById(R.id.linear_empty);
        adapter = new StepDollarDetailAdapter(getActivity(), listData);
        rvCoupon = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.rv_coupon);
        refreshLayout = (SwipeRefreshLayout) viewRoot.findViewById(R.id.refresh_layout);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }

            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        };
        rvCoupon.setLayoutManager(layoutManager);
        rvCoupon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                refreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });
//        rvCoupon.setAutoLoadMore(true);
//        rvCoupon.useDefaultLoadMore();
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getActivity());
        loadMoreView.setCustomEmptyView(linearEmpty);
        rvCoupon.addFooterView(loadMoreView); // 添加为Footer。
        rvCoupon.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        rvCoupon.setLoadMoreListener(this);
        rvCoupon. setNestedScrollingEnabled(false);
        rvCoupon.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, final int position) {

            }
        });
        rvCoupon.setHasFixedSize(true);
        rvCoupon.setNestedScrollingEnabled(false);
        refreshLayout.setOnRefreshListener(this);
        rvCoupon.setAdapter(adapter);

        getPageData(1);
    }


    private void getPageData(final int page) {
        if (!isAdded()) {
            return;
        }
        currentPage = page;
        Map<String, String> params = new HashMap<>();
        params.put("userid", String.valueOf(Presenter.getInstance(getContext()).getId()));
        params.put("page", String.valueOf(currentPage));
        params.put("pagesize", String.valueOf(Constants.PAGE_SIZE));
        Presenter.getInstance(getActivity()).getPaoBuSimple(NetApi.urlCredit, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                    rvCoupon.loadMoreFinish(false, true);
                }
                try {
                    StepDollarDetailResponse stepDollarDetailResponse = new Gson().fromJson(s, StepDollarDetailResponse.class);
                    if (stepDollarDetailResponse.getData().getData().size() > 0) {
                        listData.addAll(stepDollarDetailResponse.getData().getData());
                        adapter.notifyDataSetChanged();
                        rvCoupon.loadMoreFinish(false, true);
                    } else {
                        adapter.notifyDataSetChanged();
                        rvCoupon.loadMoreFinish(false, false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                if (!isAdded()) {
                    return;
                }
                listData.clear();
                adapter.notifyDataSetChanged();
                rvCoupon.loadMoreFinish(false, true);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getPageData(1);
    }

    @Override
    public void onLoadMore() {
        getPageData(currentPage + 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPageData(1);
    }
}
