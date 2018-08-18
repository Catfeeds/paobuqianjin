package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ShopSendedRdBagAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopRedBagActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeMenuRecyclerView.LoadMoreListener {

    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.rv_sended_coupon)
    SwipeMenuRecyclerView rvSendedCoupon;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.linear_empty)
    LinearLayout linearEmpty;

    private PopupWindow listPopupWindow;
    private int currentPage = 1;
    private ShopSendedRdBagAdapter adapter;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData = new ArrayList<>();
    private NormalDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_red_bag);
        ButterKnife.bind(this);

        barTitle.setText(R.string.shop_red_bag);
        barTvRight.setImageResource(R.drawable.exit);

        adapter = new ShopSendedRdBagAdapter(this, listData);
        rvSendedCoupon.setLayoutManager(new LinearLayoutManager(this));
//        rvSendedCoupon.setAutoLoadMore(true);
//        rvSendedCoupon.useDefaultLoadMore();
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        loadMoreView.setCustomEmptyView(linearEmpty);
        rvSendedCoupon.addFooterView(loadMoreView); // 添加为Footer。
        rvSendedCoupon.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        rvSendedCoupon.setLoadMoreListener(this);

        /*rvSendedCoupon.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (listData.get(position).getStatus() == 0) {

                }
            }
        });*/
        refreshLayout.setOnRefreshListener(this);
        rvSendedCoupon.setAdapter(adapter);
        adapter.setMyCustomClickLis(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, final int position) {
                if (listData.get(position).getStatus() != 0) return;
                if (dialog == null) {
                    dialog = new NormalDialog(ShopRedBagActivity.this);
                    dialog.setMessage("确定要下架消费红包？");
                }
                dialog.setYesOnclickListener(getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        dialog.dismiss();
                        cutDonwConsumptiveRedBag(position);
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

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });
    }

    private void cutDonwConsumptiveRedBag(final int position) {
        Map<String, String> params = new HashMap<>();
        params.put("voucherid", listData.get(position).getVoucherid());
        Presenter.getInstance(this).postPaoBuSimple(NetApi.lowerVoucher, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                listData.get(position).setStatus(1);
                adapter.notifyDataSetChanged();
                PaoToastUtils.showShortToast(ShopRedBagActivity.this, "下架成功");
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
        getPageData(1);
    }

    private void getPageData(final int page) {
        currentPage = page;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("pagesize", Constants.PAGE_SIZE + "");
        Presenter.getInstance(this).getPaoBuSimple(NetApi.getMySendVoucher, params, new PaoTipsCallBack() {
            // 数据完更多数据，一定要调用这个方法。
            // 第一个参数：表示此次数据是否为空。
            // 第二个参数：表示是否还有更多数据。

            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                    rvSendedCoupon.loadMoreFinish(false, true);
                }
                ShopSendedRedBagResponse redBagResponse = new Gson().fromJson(s, ShopSendedRedBagResponse.class);
                if (redBagResponse.getData().getData().size() > 0) {
                    listData.addAll(redBagResponse.getData().getData());
                    adapter.notifyDataSetChanged();
                    rvSendedCoupon.loadMoreFinish(false, true);
                } else {
                    rvSendedCoupon.loadMoreFinish(false, false);
                }

                PagenationBean pagenationBean = redBagResponse.getData().getPagenation();
//                rvSendedCoupon.loadMoreFinish(redBagResponse.getData().getData().size() < 1, pagenationBean.getPage() < pagenationBean.getTotalPage());
                refreshLayout.setRefreshing(false);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                if (errorBean != null && errorStr.contains("Not Found Data")) {
                    rvSendedCoupon.loadMoreError(0, "暂无数据");
                }
                refreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 4) {
            getPageData(1);
        }
    }

    @OnClick({R.id.button_return_bar, R.id.bar_tv_right,R.id.tv_empty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_return_bar:
                onBackPressed();
                break;
            case R.id.bar_tv_right:
                if (listPopupWindow == null) {
                    View popCircleOpBar = View.inflate(this, R.layout.top_share_no_admin, null);
                    listPopupWindow = new PopupWindow(popCircleOpBar,
                            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    View.OnClickListener onClickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listPopupWindow.dismiss();
                            switch (view.getId()) {
                                case R.id.share_text:
                                    startActivityForResult(new Intent(ShopRedBagActivity.this, AddConsumptiveRedBagActivity.class), 1);
                                    break;
                                case R.id.exit_text:
                                    Intent intent = new Intent(ShopRedBagActivity.this, GetConsumptiveRedBagHisActivity.class);
                                    startActivity(intent);
                                    break;
                            }
                        }
                    };
                    TextView tv2 = popCircleOpBar.findViewById(R.id.exit_text);
                    tv2.setOnClickListener(onClickListener);
                    TextView tv1 = popCircleOpBar.findViewById(R.id.share_text);
                    tv1.setOnClickListener(onClickListener);
                    tv1.setText("添加");
                    tv2.setText("领取记录");

                    listPopupWindow.setFocusable(true);
                    listPopupWindow.setOutsideTouchable(true);
                    listPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                }
                listPopupWindow.showAsDropDown(barTvRight, -20, -10);
                break;
            case R.id.tv_empty:
                Intent intent = new Intent(this, AddConsumptiveRedBagActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    public void onRefresh() {
        getPageData(1);
    }

    @Override
    public void onLoadMore() {
        getPageData(currentPage + 1);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }
}
