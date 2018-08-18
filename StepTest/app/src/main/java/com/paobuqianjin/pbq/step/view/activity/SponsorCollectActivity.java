package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CollectSponsorResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorCollectAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/8/4.
 */

public class SponsorCollectActivity extends BaseBarActivity {
    private final static String TAG = SponsorCollectActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.collect_recycler)
    SwipeMenuRecyclerView collectRecycler;
    @Bind(R.id.swipe_ref)
    SwipeRefreshLayout swipeRef;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private int pageIndex = 1, pageCount = 0;
    LinearLayoutManager layoutManager;
    SponsorCollectAdapter sponsorCollectAdapter;
    ArrayList<CollectSponsorResponse.DataBeanX.DataBean> collectList = new ArrayList<>();
    private final static int PAGESIZE = 10;
    private final static int SPONSOR_DETAIL_REQUEST = 100;

    @Override
    protected String title() {
        return "收藏";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_collect_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        swipeRef = (SwipeRefreshLayout) findViewById(R.id.swipe_ref);
        swipeRef.setOnRefreshListener(mRefreshListener);
        collectRecycler = (SwipeMenuRecyclerView) findViewById(R.id.collect_recycler);
        layoutManager = new LinearLayoutManager(this);
        collectRecycler.setLayoutManager(layoutManager);
        collectRecycler.setHasFixedSize(true);
        collectRecycler.setNestedScrollingEnabled(false);
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        collectRecycler.addFooterView(loadMoreView); // 添加为Footer。
        collectRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        collectRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        sponsorCollectAdapter = new SponsorCollectAdapter(SponsorCollectActivity.this, null);
        collectRecycler.setAdapter(sponsorCollectAdapter);
        loadCollect();
        notFoundData = (TextView) findViewById(R.id.not_found_data);
        collectRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRef.setEnabled(topRowVerticalPosition >= 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        collectRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, collectRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (position < collectList.size()) {
                    int busId = collectList.get(position).getBusinessid();
                    if (busId > 0) {
                        Intent intent = new Intent();
                        intent.putExtra(getPackageName() + "businessid", busId);
                        intent.setClass(SponsorCollectActivity.this, SponsorDetailActivity.class);
                        startActivityForResult(intent, SPONSOR_DETAIL_REQUEST);
                    }
                }
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        }));
    }


    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            collectRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新");
                    } else {
                        if (pageIndex > pageCount) {
                            PaoToastUtils.showLongToast(SponsorCollectActivity.this, "没有更多内容");
                            collectRecycler.loadMoreFinish(false, true);
                            collectRecycler.setLoadMoreView(null);
                            collectRecycler.setLoadMoreListener(null);
                            return;
                        }
                    }
                    loadCollect();
                    swipeRef.setRefreshing(false);
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
            swipeRef.postDelayed(new Runnable() {
                @Override
                public void run() {
                    collectList.clear();
                    sponsorCollectAdapter.notifyDataSetChanged();
                    pageIndex = 1;
                    pageCount = 0;
                    loadCollect();
                    swipeRef.setRefreshing(false);
                }
            }, 2000);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPONSOR_DETAIL_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                LocalLog.d(TAG, "刷新收藏列表");
                collectList.clear();
                sponsorCollectAdapter.notifyDataSetChanged();
                pageIndex = 1;
                pageCount = 0;
                loadCollect();
            }
        }
    }

    //用户收藏列表
    private void loadCollect() {
        LocalLog.d(TAG, "loadCollect() enter");
        String url = NetApi.urlCollectSponsor + "?page=" + String.valueOf(pageIndex) + "&pagesize=" + String.valueOf(PAGESIZE);
        LocalLog.d(TAG, "url =" + url);
        Presenter.getInstance(this).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    notFoundData.setVisibility(View.GONE);
                    collectRecycler.setVisibility(View.VISIBLE);
                    CollectSponsorResponse collectSponsorResponse = new Gson().fromJson(s, CollectSponsorResponse.class);
                    pageCount = collectSponsorResponse.getData().getPagenation().getTotalPage();
                    if (collectSponsorResponse.getData() != null
                            && collectSponsorResponse.getData().getData().size() > 0) {
                        if (pageIndex == 1) {
                            collectList.clear();
                            collectList.addAll(collectSponsorResponse.getData().getData());
                            sponsorCollectAdapter.notifyDataSetChanged(collectList);
                            if (collectSponsorResponse.getData().getData() == null || collectSponsorResponse.getData().getData().size() == 0) {
                                collectRecycler.loadMoreFinish(true, true);
                            } else {
                                collectRecycler.loadMoreFinish(false, true);
                            }
                        } else {
                            LocalLog.d(TAG, "加载更多!");
                            collectList.addAll(collectSponsorResponse.getData().getData());
                            sponsorCollectAdapter.notifyItemRangeInserted(collectList.size() - collectSponsorResponse.getData().getData().size(),
                                    collectSponsorResponse.getData().getData().size());
                            collectRecycler.loadMoreFinish(false, true);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pageIndex++;
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    if (pageIndex == 1 && errorBean.getMessage().contains("Not Found Data")) {
                        LocalLog.d(TAG, "没有收藏任何店铺");
                        notFoundData.setVisibility(View.VISIBLE);
                        collectRecycler.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}
