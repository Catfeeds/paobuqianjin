package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExWantResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.exchange.ExWantAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2019/1/7.
 */

public class ExWantActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener, SwipeMenuRecyclerView.LoadMoreListener {
    private final static String TAG = ExWantActivity.class.getSimpleName();
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
    @Bind(R.id.want_recycler)
    SwipeMenuRecyclerView wantRecycler;
    @Bind(R.id.want_may_recycler)
    SwipeMenuRecyclerView wantMayRecycler;
    @Bind(R.id.right_tv)
    TextView rightTv;
    @Bind(R.id.delete_span)
    LinearLayout deleteSpan;
    @Bind(R.id.all_select)
    ImageView allSelect;
    @Bind(R.id.not_found_data)
    TextView notFoundData;
    private int currentPage;
    private List<ExWantResponse.DataBeanX.DataBean> listData = new ArrayList<>();
    private ExWantAdapter exWantAdapter;
    private final static int GOOD_DETAIL = 11;

    @Override
    protected String title() {
        return "我想要的";
    }

    @Override
    public Object right() {
        return "";
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }


    @Override
    public void clickRight() {
        deleteSpan.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_wanted_activity_layout);
        ButterKnife.bind(this);
        setToolBarListener(this);
        exWantAdapter = new ExWantAdapter(this, listData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        wantRecycler.setLayoutManager(linearLayoutManager);

        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        wantRecycler.addFooterView(loadMoreView); // 添加为Footer。
        wantRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        wantRecycler.setLoadMoreListener(this);
        wantRecycler.setHasFixedSize(true);
        wantRecycler.setNestedScrollingEnabled(false);

        wantRecycler.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position < listData.size()) {
                    Intent intent = new Intent();
                    intent.setClass(ExWantActivity.this, ExchangeGoodDeatilActivity.class);
                    intent.putExtra("ex_id", String.valueOf(listData.get(position).getId()));
                    startActivityForResult(intent, GOOD_DETAIL);
                }
            }
        });
        wantRecycler.setAdapter(exWantAdapter);
        getWant(1);
    }

    @Override
    public void onLoadMore() {
        getWant(currentPage + 1);
    }

    private void getWant(final int page) {
        currentPage = page;
        Map<String, String> param = new HashMap<>();
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(Constants.PAGE_SIZE));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlExWantList, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ExWantResponse wantResponse = new Gson().fromJson(s, ExWantResponse.class);
                    if (page == 1) {
                        listData.clear();
                        exWantAdapter.notifyDataSetChanged();
                    }
                    if (wantResponse.getData().getData().size() > 0) {
                        listData.addAll(wantResponse.getData().getData());
                        exWantAdapter.notifyDataSetChanged();
                        wantMayRecycler.loadMoreFinish(false, true);
                    } else {
                        wantMayRecycler.loadMoreFinish(true, false);
                    }
                    if (notFoundData.getVisibility() == View.VISIBLE)
                        notFoundData.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                wantMayRecycler.loadMoreFinish(true, false);
                notFoundData.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick({R.id.all_select, R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_select:
                break;
            case R.id.right_tv:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOD_DETAIL && resultCode == Activity.RESULT_OK) {
            getWant(1);
        }
    }
}
