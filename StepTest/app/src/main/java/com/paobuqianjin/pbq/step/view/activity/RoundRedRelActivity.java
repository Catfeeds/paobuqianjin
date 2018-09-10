package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearRedInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RoundRedInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.RedRecListAdapter;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/8/20.
 */

public class RoundRedRelActivity extends BaseBarActivity {
    private final static String TAG = RoundRedRelActivity.class.getSimpleName();
    @Bind(R.id.user_head)
    CircleImageView userHead;
    @Bind(R.id.pkg_money)
    TextView pkgMoney;
    @Bind(R.id.op_des)
    TextView opDes;
    @Bind(R.id.rec_recycler)
    SwipeMenuRecyclerView recRecycler;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    String red_id = "";
    private final static int PAGE_SIZE = 10;
    private int pageIndex = 1, pageCount = 0;
    TextView redDes;
    RedRecListAdapter redRecListAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<RoundRedInfoResponse.DataBeanX.ReceiveListBean.DataBean> arrayList = new ArrayList<>();
    ArrayList<NearRedInfoResponse.DataBeanX.ReceiveListBean.DataBean> beanArrayList = new ArrayList<>();
    private String info;

    @Override

    protected String title() {
        return "红包信息";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_info_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        userHead = (CircleImageView) findViewById(R.id.user_head);
        pkgMoney = (TextView) findViewById(R.id.pkg_money);
        opDes = (TextView) findViewById(R.id.op_des);
        redDes = (TextView) findViewById(R.id.des_red);
        layoutManager = new LinearLayoutManager(this);
        recRecycler = (SwipeMenuRecyclerView) findViewById(R.id.rec_recycler);
        recRecycler.setLayoutManager(layoutManager);
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        recRecycler.addFooterView(loadMoreView); // 添加为Footer。
        recRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        recRecycler.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        recRecycler.setHasFixedSize(true);
        recRecycler.setNestedScrollingEnabled(false);

        Intent intent = getIntent();
        if (intent != null) {
            red_id = intent.getStringExtra(getPackageName() + "red_id");
            LocalLog.d(TAG, "red_id =" + red_id);
            info = intent.getStringExtra(getPackageName() + "near");
            if (!TextUtils.isEmpty(red_id)) {
                if (TextUtils.isEmpty(info)) {
                    redInfo();
                    redRecListAdapter = new RedRecListAdapter(this, arrayList);
                    recRecycler.setAdapter(redRecListAdapter);
                } else {
                    redNearInfo();
                    redRecListAdapter = new RedRecListAdapter(this, beanArrayList);
                    recRecycler.setAdapter(redRecListAdapter);
                }
            }
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
                            PaoToastUtils.showLongToast(RoundRedRelActivity.this, "没有更多内容");
                            recRecycler.loadMoreFinish(false, true);
                            recRecycler.setLoadMoreView(null);
                            recRecycler.setLoadMoreListener(null);
                            return;
                        }
                    }

                    if (TextUtils.isEmpty(info)) {
                        redInfo();
                        redRecListAdapter = new RedRecListAdapter(RoundRedRelActivity.this, arrayList);
                        recRecycler.setAdapter(redRecListAdapter);
                    } else {
                        redNearInfo();
                        redRecListAdapter = new RedRecListAdapter(RoundRedRelActivity.this, beanArrayList);
                        recRecycler.setAdapter(redRecListAdapter);
                    }

                }
            }, 1000);
        }
    };


    private void redNearInfo() {
        Map<String, String> param = new HashMap<>();
        param.put("red_id", red_id);
        param.put("page", String.valueOf(pageIndex));
        param.put("pagesize", String.valueOf(PAGE_SIZE));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlNearInof, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    NearRedInfoResponse redInfoResponse = new Gson().fromJson(s, NearRedInfoResponse.class);
                    pageCount = redInfoResponse.getData().getReceive_list().getPagenation().getTotalPage();
                    Presenter.getInstance(RoundRedRelActivity.this).getPlaceErrorImage(userHead,
                            redInfoResponse.getData().getAvatar(), R.drawable.default_head_ico,
                            R.drawable.default_head_ico);

                    LocalLog.d(TAG, "领");
                    String income = redInfoResponse.getData().getIncome_money() + "元";
                    SpannableString spannableString = new SpannableString(income);
                    spannableString.setSpan(new AbsoluteSizeSpan(14, true), redInfoResponse.getData().getIncome_money().length(), income.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    pkgMoney.setText(spannableString);

                    redDes.setText("已领取" + redInfoResponse.getData().getReceive_num() + "个" + "，共"
                            + redInfoResponse.getData().getRedpacket_num() + "个");
                    if (pageIndex == 1) {
                        beanArrayList.clear();
                        beanArrayList.addAll(redInfoResponse.getData().getReceive_list().getData());
                        redRecListAdapter.notifyDataSetChanged();
                    } else {
                        beanArrayList.addAll(redInfoResponse.getData().getReceive_list().getData());
                        redRecListAdapter.notifyItemRangeInserted(beanArrayList.size() - redInfoResponse.getData().getReceive_list().getData().size()
                                , redInfoResponse.getData().getReceive_list().getData().size());
                    }
                    recRecycler.loadMoreFinish(false, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pageIndex++;
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    private void redInfo() {
        Map<String, String> param = new HashMap<>();
        param.put("red_id", red_id);
        param.put("page", String.valueOf(pageIndex));
        param.put("pagesize", String.valueOf(PAGE_SIZE));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlRedInfo, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    RoundRedInfoResponse redInfoResponse = new Gson().fromJson(s, RoundRedInfoResponse.class);
                    pageCount = redInfoResponse.getData().getReceive_list().getPagenation().getTotalPage();
                    Presenter.getInstance(RoundRedRelActivity.this).getPlaceErrorImage(userHead,
                            redInfoResponse.getData().getAvatar(), R.drawable.default_head_ico,
                            R.drawable.default_head_ico);

                    LocalLog.d(TAG, "领");
                    String income = redInfoResponse.getData().getIncome_money() + "元";
                    SpannableString spannableString = new SpannableString(income);
                    spannableString.setSpan(new AbsoluteSizeSpan(14, true), redInfoResponse.getData().getIncome_money().length(), income.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    pkgMoney.setText(spannableString);

                    redDes.setText("已领取" + redInfoResponse.getData().getReceive_num() + "个" + "，共"
                            + redInfoResponse.getData().getRedpacket_num() + "个");
                    if (pageIndex == 1) {
                        arrayList.clear();
                        arrayList.addAll(redInfoResponse.getData().getReceive_list().getData());
                        redRecListAdapter.notifyDataSetChanged();
                    } else {
                        arrayList.addAll(redInfoResponse.getData().getReceive_list().getData());
                        redRecListAdapter.notifyItemRangeInserted(arrayList.size() - redInfoResponse.getData().getReceive_list().getData().size()
                                , redInfoResponse.getData().getReceive_list().getData().size());
                    }
                    recRecycler.loadMoreFinish(false, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pageIndex++;
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }
}
