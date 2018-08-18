package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

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
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ConsumptiveRdBagBeGetAdapter;
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

public class GetConsumptiveRedBagHisActivity extends BaseBarActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeMenuRecyclerView.LoadMoreListener {

    @Bind(R.id.rv_history)
    SwipeMenuRecyclerView rvHistory;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private int currentPage = 1;
    private ConsumptiveRdBagBeGetAdapter adapter;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData = new ArrayList<>();

    @Override
    protected String title() {
        return getString(R.string.get_his);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_consumptive_red_bag_his);
        ButterKnife.bind(this);

        adapter = new ConsumptiveRdBagBeGetAdapter(this, listData);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
//        rvHistory.setAutoLoadMore(true);
//        rvHistory.useDefaultLoadMore();
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        rvHistory.addFooterView(loadMoreView); // 添加为Footer。
        rvHistory.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        rvHistory.setLoadMoreListener(this);

        /*rvHistory.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (listData.get(position).getStatus() == 0) {

                }
            }
        });*/
        refreshLayout.setOnRefreshListener(this);
        rvHistory.setAdapter(adapter);
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
        Presenter.getInstance(this).getPaoBuSimple(NetApi.getAllVoucherBeGetHis, params, new PaoTipsCallBack() {
            // 数据完更多数据，一定要调用这个方法。
            // 第一个参数：表示此次数据是否为空。
            // 第二个参数：表示是否还有更多数据。

            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                    rvHistory.loadMoreFinish(false, true);
                }
                ShopSendedRedBagResponse redBagResponse = new Gson().fromJson(s, ShopSendedRedBagResponse.class);
                if (redBagResponse.getData().getData().size() > 0) {
                    listData.addAll(redBagResponse.getData().getData());
                    adapter.notifyDataSetChanged();
                    rvHistory.loadMoreFinish(false, true);
                }else{
                    rvHistory.loadMoreFinish(false, false);
                }

                PagenationBean pagenationBean = redBagResponse.getData().getPagenation();
//                rvHistory.loadMoreFinish(redBagResponse.getData().getData().size() < 1, pagenationBean.getPage() < pagenationBean.getTotalPage());
                refreshLayout.setRefreshing(false);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
               /* if (errorBean != null) {
                }*/
                rvHistory.loadMoreError(0, "暂无数据");
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
        getPageData(currentPage+1);

        // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
        // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
        // errorMessage是会显示到loadMoreView上的，用户可以看到。
        // mRecyclerView.loadMoreError(0, "请求网络失败");
    }
}
