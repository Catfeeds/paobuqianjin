package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PagenationBean;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyConsumptiveRedBagAdapter;
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
import butterknife.OnClick;

public class MyConsumptiveRedBagActivity extends BaseBarActivity implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rv_coupon)
    SwipeMenuRecyclerView rvCoupon;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.linear_empty)
    LinearLayout linearEmpty;

    private int currentPage = 1;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData = new ArrayList<>();
    private MyConsumptiveRedBagAdapter adapter;
    private NormalDialog dialog;

    @Override
    protected String title() {
        return getString(R.string.consumptive_red_bag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_consumptive_red_bag);
        ButterKnife.bind(this);

        adapter = new MyConsumptiveRedBagAdapter(this, listData);
        rvCoupon.setLayoutManager(new LinearLayoutManager(this));
//        rvCoupon.setAutoLoadMore(true);
//        rvCoupon.useDefaultLoadMore();
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        loadMoreView.setCustomEmptyView(linearEmpty);
        rvCoupon.addFooterView(loadMoreView); // 添加为Footer。
        rvCoupon.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        rvCoupon.setLoadMoreListener(this);
        rvCoupon.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                String bId = listData.get(position).getBusinessid();
                if (!TextUtils.isEmpty(bId)) {
                    Intent intent = new Intent();
                    intent.putExtra("businessid", Integer.parseInt(bId));
                    intent.setClass(MyConsumptiveRedBagActivity.this, SponsorDetailActivity.class);
                    startActivity(intent);
                }
            }
        });

        adapter.setMyItemClickLis(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, final int position) {
                if (listData.get(position).getStatus() == 0) {
                    if (dialog == null) {
                        dialog = new NormalDialog(MyConsumptiveRedBagActivity.this);
                        dialog.setMessage("确定要使用消费红包吗?\n" +
                                "使用后则无法再次使用。");
                    }
                    dialog.setYesOnclickListener(getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            dialog.dismiss();
                            useConsumptiveRedBag(position);
                        }
                    });
                    dialog.setNoOnclickListener(getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });
        refreshLayout.setOnRefreshListener(this);
        rvCoupon.setAdapter(adapter);
        getPageData(1);
    }

    private void useConsumptiveRedBag(final int position) {
        Map<String, String> params = new HashMap<>();
        params.put("recordid", listData.get(position).getId());
        Presenter.getInstance(this).postPaoBuSimple(NetApi.useVoucher, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                listData.get(position).setStatus(3);
                adapter.notifyDataSetChanged();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    jsonObject = jsonObject.getJSONObject("data");
                    String vNo = jsonObject.getString("vno");
                    final NormalDialog codeDialog = new NormalDialog(MyConsumptiveRedBagActivity.this);
                    codeDialog.setMessage("消费红包码：" + vNo);
                    codeDialog.setSingleBtn(true);
                    codeDialog.setYesOnclickListener(getString(R.string.confirm), new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            codeDialog.dismiss();
                        }
                    });
                    codeDialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getPageData(final int page) {
        currentPage = page;
        Map<String, String> params = new HashMap<>();
        params.put("page", currentPage + "");
        params.put("pagesize", Constants.PAGE_SIZE + "");

        Presenter.getInstance(this).getPaoBuSimple(NetApi.getMyVoucher, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                    rvCoupon.loadMoreFinish(false, true);
                }
                ShopSendedRedBagResponse redBagResponse = new Gson().fromJson(s, ShopSendedRedBagResponse.class);
                if (redBagResponse.getData().getData().size() > 0) {
                    listData.addAll(redBagResponse.getData().getData());
                    adapter.notifyDataSetChanged();
                    rvCoupon.loadMoreFinish(false, true);
                } else {
                    rvCoupon.loadMoreFinish(false, false);
                }

                PagenationBean pagenationBean = redBagResponse.getData().getPagenation();
//                rvCoupon.loadMoreFinish(redBagResponse.getData().getData().size() < 1, pagenationBean.getPage() < pagenationBean.getTotalPage());
                refreshLayout.setRefreshing(false);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                if (errorBean != null && errorStr.contains("Not Found Data")) {
                    rvCoupon.loadMoreError(0, "暂无数据");
                }
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

    @OnClick(R.id.tv_empty)
    public void onClick() {
        Intent intent = new Intent(this, ConsumptiveRedBagActivity.class);
        startActivityForResult(intent,400);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 400) {
            getPageData(1);
        }
    }
}
