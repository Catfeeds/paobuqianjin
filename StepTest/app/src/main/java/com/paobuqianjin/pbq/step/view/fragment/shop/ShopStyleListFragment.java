package com.paobuqianjin.pbq.step.view.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ConSumRedStyleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CouponCateListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PagenationBean;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.GetConsumptiveRBResultActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ShopStyleAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ConsumptiveRedBagListAdapter;
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
 * Created by pbq on 2018/12/3.
 */

public class ShopStyleListFragment extends BaseFragment implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private final static String TAG = ShopStyleListFragment.class.getSimpleName();
    @Bind(R.id.rv_coupon)
    SwipeMenuRecyclerView rvCoupon;
    @Bind(R.id.linear_empty)
    LinearLayout linearEmpty;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private List<CouponCateListResponse.DataBean> listData = new ArrayList<>();
    private ShopStyleAdapter adapter;
    LinearLayoutManager layoutManager;
    private CouponCateListResponse.DataBean dataBean;
    private int currentPage = 1;


    public void setStyle(CouponCateListResponse.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.consum_red_fg;
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
        adapter = new ShopStyleAdapter(getActivity(), listData);
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
        if (!isAdded() || TextUtils.isEmpty(dataBean.getCate_id())) {
            return;
        }
        currentPage = page;
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("pagesize", String.valueOf(Constants.PAGE_SIZE));
        params.put("cate_id", dataBean.getCate_id());
        params.put("term_id", "1");
        Presenter.getInstance(getActivity()).postPaoBuSimple(NetApi.urlCouponCateList, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                    rvCoupon.loadMoreFinish(false, true);
                }
                CouponCateListResponse couponCateListResponse = new Gson().fromJson(s, CouponCateListResponse.class);
                if (couponCateListResponse.getData().size() > 0) {
                    listData.addAll(couponCateListResponse.getData());
                    adapter.notifyDataSetChanged();
                    rvCoupon.loadMoreFinish(false, true);
                } else {
                    adapter.notifyDataSetChanged();
                    rvCoupon.loadMoreFinish(false, false);
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
