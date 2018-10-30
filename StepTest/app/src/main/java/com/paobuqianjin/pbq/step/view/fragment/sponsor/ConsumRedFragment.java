package com.paobuqianjin.pbq.step.view.fragment.sponsor;

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
import com.paobuqianjin.pbq.step.view.activity.AddConsumptiveRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.GetConsumptiveRBResultActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorDetailActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ConsumptiveRedBagListAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/10/16.
 */

public class ConsumRedFragment extends BaseFragment implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private final static String TAG = ConsumRedFragment.class.getSimpleName();
    @Bind(R.id.rv_coupon)
    SwipeMenuRecyclerView rvCoupon;
    @Bind(R.id.linear_empty)
    LinearLayout linearEmpty;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData = new ArrayList<>();
    private ConsumptiveRedBagListAdapter adapter;
    LinearLayoutManager layoutManager;
    private ConSumRedStyleResponse.DataBean dataBean;
    private int currentPage = 1;
    private double[] location;

    public void setStyle(ConSumRedStyleResponse.DataBean dataBean) {
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
        adapter = new ConsumptiveRedBagListAdapter(getActivity(), listData);
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
                String bId = listData.get(position).getBusinessid();
                if (!TextUtils.isEmpty(bId) && !"0".equals(bId)) {
                    LocalLog.d(TAG, "businessid =" + bId);
                    Intent intent = new Intent();
                    intent.putExtra(getContext().getPackageName() + "businessid", Integer.parseInt(bId));
                    intent.setClass(getActivity(), SponsorDetailActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent().setClass(getActivity(), AddConsumptiveRedBagActivity.class));
                }
            }
        });
        rvCoupon.setHasFixedSize(true);
        rvCoupon.setNestedScrollingEnabled(false);
        adapter.setMyCustomClickLis(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position > listData.size()) {
                    return;
                }
                if (listData.get(position).getStatus() == 0) {
                    pullDownConsumptiveRedBag(position);
                }
            }
        });
        refreshLayout.setOnRefreshListener(this);
        rvCoupon.setAdapter(adapter);
        location = Presenter.getInstance(getActivity()).getLocation();
        getPageData(1);
    }


    /**
     * 领取消费红包
     */
    private void pullDownConsumptiveRedBag(int position) {

        Map<String, String> params = new HashMap<>();
        final String voucherid = listData.get(position).getVoucherid();
        final String bussinessid = listData.get(position).getBusinessid();
        params.put("voucherid", listData.get(position).getVoucherid());
        params.put("latitude", location[0] + "");
        params.put("longitude", location[1] + "");
        Presenter.getInstance(getActivity()).postPaoBuSimple(NetApi.receiveVoucher, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                Intent intent = new Intent(getActivity(), GetConsumptiveRBResultActivity.class);
                intent.putExtra(getContext().getPackageName() + "red_id", Integer.parseInt(voucherid));
                intent.putExtra(getContext().getPackageName() + "businessid", Integer.parseInt(bussinessid));
                startActivityForResult(intent, 1);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorStr.contains("距离")) {
                    PaoToastUtils.showLongToast(getActivity(), "距离太远无法领取");
                } else {
                    PaoToastUtils.showLongToast(getActivity(), "您还未达标！");
                }

            }
        });
    }

    private void getPageData(final int page) {
        if (!isAdded()) {
            return;
        }
        currentPage = page;
        Map<String, String> params = new HashMap<>();
        params.put("page", currentPage + "");
        params.put("pagesize", Constants.PAGE_SIZE + "");
        params.put("shadow", "1");
        params.put("latitude", location[0] + "");
        params.put("longitude", location[1] + "");
        if (dataBean != null) {
            params.put("cateid", String.valueOf(dataBean.getId()));
        }
        Presenter.getInstance(getActivity()).getPaoBuSimple(NetApi.getVoucherList, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                    rvCoupon.loadMoreFinish(false, true);
                }
                ShopSendedRedBagResponse redBagResponse = new Gson().fromJson(s, ShopSendedRedBagResponse.class);
                if (redBagResponse.getData().getData().size() > 0) {
                    listData.addAll(redBagResponse.getData().getData());
                    addEmpty();
                    adapter.notifyDataSetChanged();
                    rvCoupon.loadMoreFinish(false, true);
                } else {
                    addEmpty();
                    adapter.notifyDataSetChanged();
                    rvCoupon.loadMoreFinish(false, false);
                }

                PagenationBean pagenationBean = redBagResponse.getData().getPagenation();
//                rvCoupon.loadMoreFinish(redBagResponse.getData().getData().size() < 1, pagenationBean.getPage() < pagenationBean.getTotalPage());
                refreshLayout.setRefreshing(false);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
               /* if (errorBean != null && errorStr.contains("Not Found Data")) {
                    rvCoupon.loadMoreError(0, "暂无数据");
                }*/
                if (!isAdded()) {
                    return;
                }
                listData.clear();
                addEmpty();
                adapter.notifyDataSetChanged();
                rvCoupon.loadMoreFinish(false, true);
                refreshLayout.setRefreshing(false);

            }
        });
    }

    private void addEmpty() {
        LocalLog.d(TAG, "addEmpty() enter");
        int size = listData.size();
        if (size < 5) {
            for (int i = size; i < 5; i++) {
                ShopSendedRedBagResponse.ShopSendedRedBagBean emptyBean = new ShopSendedRedBagResponse.ShopSendedRedBagBean();
                listData.add(emptyBean);
            }
        }
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
