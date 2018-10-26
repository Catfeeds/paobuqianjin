package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ConsumRedInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.RedRecListAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/10/22.
 */

public class ConsumRedInfoActivity extends BaseBarActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.rec_recycler)
    SwipeMenuRecyclerView recRecycler;
    private final static int PAGE_SIZE = 10;
    @Bind(R.id.refresh_swipe)
    SwipeRefreshLayout refreshSwipe;
    private int pageIndex = 1, pageCount = 0;
    private final static String TAG = ConsumRedInfoActivity.class.getSimpleName();
    LinearLayoutManager layoutManager;
    private String vid = "";
    ArrayList<ConsumRedInfoResponse.DataBeanX.DataBean> arrayList = new ArrayList<>();
    RedRecListAdapter redRecListAdapter;

    @Override
    protected String title() {
        return "已领取";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consum_red_info_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        layoutManager = new LinearLayoutManager(this);
        recRecycler = (SwipeMenuRecyclerView) findViewById(R.id.rec_recycler);
        recRecycler.setLayoutManager(layoutManager);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        recRecycler.addFooterView(loadMoreView); // 添加为Footer。
        recRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        recRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        recRecycler.setHasFixedSize(true);
        recRecycler.setNestedScrollingEnabled(false);
        refreshSwipe = (SwipeRefreshLayout)findViewById(R.id.refresh_swipe);
        refreshSwipe.setOnRefreshListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            vid = intent.getStringExtra(getPackageName() + "vid");
            LocalLog.d(TAG, "vid =" + vid);
            if (!TextUtils.isEmpty(vid)) {
                redInfo(vid);
                redRecListAdapter = new RedRecListAdapter(this, arrayList);
                recRecycler.setAdapter(redRecListAdapter);
            }
        }
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        pageCount = 0;
        if (!TextUtils.isEmpty(vid)) {
            redInfo(vid);
        }
    }

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            recRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocalLog.d(TAG, "加载更多! pageIndex = " + pageIndex + "pageCount = " + pageCount);
                    if (pageCount == 0) {
                        LocalLog.d(TAG, "第一次刷新");
                    } else {
                        if (pageIndex > pageCount) {
                            PaoToastUtils.showLongToast(ConsumRedInfoActivity.this, "没有更多内容");
                            recRecycler.loadMoreFinish(false, true);
                            recRecycler.setLoadMoreView(null);
                            recRecycler.setLoadMoreListener(null);
                            return;
                        }
                    }

                }
            }, 1000);
        }
    };

    private void redInfo(String vid) {
        Map<String, String> param = new HashMap<>();
        param.put("vid", vid);
        param.put("page", String.valueOf(pageIndex));
        param.put("pagesize", String.valueOf(PAGE_SIZE));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlConSumInfo, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                refreshSwipe.setRefreshing(false);
                try {
                    ConsumRedInfoResponse conSumSendHisResponse = new Gson().fromJson(s, ConsumRedInfoResponse.class);
                    pageCount = conSumSendHisResponse.getData().getPagenation().getTotalPage();
                    if (pageIndex == 1) {
                        arrayList.clear();
                        arrayList.addAll(conSumSendHisResponse.getData().getData());
                        redRecListAdapter.notifyDataSetChanged();
                    } else {
                        arrayList.addAll(conSumSendHisResponse.getData().getData());
                        redRecListAdapter.notifyItemRangeInserted(arrayList.size() - conSumSendHisResponse.getData().getData().size()
                                , conSumSendHisResponse.getData().getData().size());
                    }
                    recRecycler.loadMoreFinish(false, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pageIndex++;
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                refreshSwipe.setRefreshing(false);
            }
        });
    }
}
