package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RedRecRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedReleaseDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyTaskDetailStateAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/6/28.
 */

public class MySponsorReleaseFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = MySponsorReleaseFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.red_name)
    TextView redName;
    @Bind(R.id.red_money)
    TextView redMoney;
    @Bind(R.id.release_live_bg)
    RelativeLayout releaseLiveBg;
    @Bind(R.id.status_des)
    TextView statusDes;
    @Bind(R.id.money_ret)
    TextView moneyRet;
    @Bind(R.id.status)
    LinearLayout status;
    @Bind(R.id.release_sample_des)
    TextView releaseSampleDes;
    @Bind(R.id.red_name_des)
    TextView redNameDes;
    @Bind(R.id.release_day)
    TextView releaseDay;
    @Bind(R.id.release_target)
    TextView releaseTarget;
    @Bind(R.id.total_money)
    TextView totalMoney;
    @Bind(R.id.start_time)
    TextView startTime;
    @Bind(R.id.end_time)
    TextView endTime;
    @Bind(R.id.release_live_desc)
    TextView releaseLiveDesc;
    @Bind(R.id.num_1)
    TextView num1;
    @Bind(R.id.red_simple)
    LinearLayout redSimple;
    @Bind(R.id.line1)
    ImageView line1;
    @Bind(R.id.red_record)
    TextView redRecord;
    @Bind(R.id.red_detail_recycler)
    RecyclerView redDetailRecycler;
    @Bind(R.id.detail_scroll)
    BounceScrollView detailScroll;
    private String statusStr = "";
    private int pageIndex = 1, pageCount = 0;
    private int redId = -1;
    private final static int PAGE_SIZE = 10;
    MyTaskDetailStateAdapter myTaskDetailStateAdapter;
    ArrayList<RedRecRecordResponse.DataBeanX.DataBean> recRecordList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    private boolean isLoading = false;

    @Override
    protected String title() {
        return "任务详情";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_red_detail_fg;
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
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            redId = intent.getIntExtra(getContext().getPackageName() + "redid", -1);
            LocalLog.d(TAG, "redid  =  " + redId);
            statusStr = intent.getStringExtra(getContext().getPackageName() + "statusStr");
            LocalLog.d(TAG, "statusStr = " + statusStr);
            redName = (TextView) viewRoot.findViewById(R.id.red_name);
            redMoney = (TextView) viewRoot.findViewById(R.id.red_money);
            statusDes = (TextView) viewRoot.findViewById(R.id.status_des);
            moneyRet = (TextView) viewRoot.findViewById(R.id.money_ret);
            redNameDes = (TextView) viewRoot.findViewById(R.id.red_name_des);
            releaseDay = (TextView) viewRoot.findViewById(R.id.release_day);
            releaseTarget = (TextView) viewRoot.findViewById(R.id.release_target);
            totalMoney = (TextView) viewRoot.findViewById(R.id.total_money);
            startTime = (TextView) viewRoot.findViewById(R.id.start_time);
            endTime = (TextView) viewRoot.findViewById(R.id.end_time);
            redDetailRecycler = (RecyclerView) viewRoot.findViewById(R.id.red_detail_recycler);
            detailScroll = (BounceScrollView) viewRoot.findViewById(R.id.detail_scroll);
            num1 = (TextView) viewRoot.findViewById(R.id.num_1);
            detailScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
                @Override
                public void topBottom(int topOrBottom) {
                    if (topOrBottom == 1) {
                        if (pageIndex <= pageCount && !isLoading) {
                            loadRedList();
                        } else {
                            LocalLog.d(TAG, "正在加载或者没有更多");
                        }
                    }
                }
            });
            layoutManager = new LinearLayoutManager(getContext());
            redDetailRecycler.setLayoutManager(layoutManager);
            loadData(String.valueOf(redId));
        }
    }

    private void loadData(String redId) {
        String urlRedDetail = NetApi.urlRedSponsorDetail + redId;
        Presenter.getInstance(getContext()).getPaoBuSimple(urlRedDetail, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                try {
                    SponsorRedReleaseDetailResponse sponsorResponse = new Gson().fromJson(s, SponsorRedReleaseDetailResponse.class);
                    LocalLog.d(TAG, "sponsorResponse = " + sponsorResponse.toString());
                    if (sponsorResponse.getData() != null) {
                        redName.setText(sponsorResponse.getData().getRed_name());
                        redMoney.setText("奖励金额:¥" + sponsorResponse.getData().getMoney() + "元");
                        if (!TextUtils.isEmpty(statusStr)) {
                            statusDes.setText("活动状态:" + statusStr);
                        }
                        moneyRet.setText("红包余额: " + sponsorResponse.getData().getSurplus() + "元");
                        redNameDes.setText("任务名称:" + sponsorResponse.getData().getRed_name());
                        releaseDay.setText("任务天数:" + sponsorResponse.getData().getDay() + "天");
                        releaseTarget.setText("目标步数:" + sponsorResponse.getData().getStep() + "步");
                        totalMoney.setText("奖励总金额:" + sponsorResponse.getData().getMoney() + "元");
                        num1.setText(Html.fromHtml(sponsorResponse.getData().getExplain()));
                        long startT = sponsorResponse.getData().getS_time();
                        String start_timeStr = DateTimeUtil.formatDateTime(startT * 1000);

                        long endT = sponsorResponse.getData().getE_time();
                        String end_timeStr = DateTimeUtil.formatDateTime(endT * 1000);
                        String dateStartStr = start_timeStr.replace("-", "/");
                        String dateEndStr = end_timeStr.replace("-", "/");
                        startTime.setText(dateStartStr);
                        endTime.setText(dateEndStr);
                        loadRedList();
                    }
                } catch (JsonSyntaxException j) {
                    PaoToastUtils.showLongToast(getActivity(), "数据出错");
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (!isAdded()) {
                    return;
                }
            }
        });

    }

    private void loadRedList() {
        LocalLog.d(TAG, "领取人列表");
        isLoading = true;
        if (redId != -1) {
            String urlList = NetApi.urlRedList + "?redid=" + String.valueOf(redId)
                    + "&page=" + String.valueOf(pageIndex) + "&pagesize=" + String.valueOf(PAGE_SIZE);
            Presenter.getInstance(getContext()).getPaoBuSimple(urlList, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    try {
                        RedRecRecordResponse redRecRecordResponse = new Gson().fromJson(s, RedRecRecordResponse.class);
                        if (redRecRecordResponse.getData() != null
                                && redRecRecordResponse.getData().getData() != null
                                && redRecRecordResponse.getData().getData().size() > 0) {
                            pageCount = redRecRecordResponse.getData().getPagenation().getTotalPage();
                            if (pageIndex != redRecRecordResponse.getData().getPagenation().getPage()) {
                                LocalLog.d(TAG, "非法数据页!");
                                return;
                            }
                            if (pageIndex == 1) {
                                recRecordList.clear();
                                recRecordList.addAll(redRecRecordResponse.getData().getData());
                                if (myTaskDetailStateAdapter == null) {
                                    myTaskDetailStateAdapter = new MyTaskDetailStateAdapter(getActivity(), recRecordList);
                                    redDetailRecycler.setAdapter(myTaskDetailStateAdapter);
                                }
                            } else {
                                recRecordList.addAll(redRecRecordResponse.getData().getData());
                                myTaskDetailStateAdapter.notifyItemRangeInserted(recRecordList.size() - redRecRecordResponse.getData().getData().size(),
                                        redRecRecordResponse.getData().getData().size());
                                redDetailRecycler.requestLayout();
                            }
                        }
                    } catch (JsonSyntaxException j) {
                        j.printStackTrace();
                    }
                    redDetailRecycler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isLoading = false;
                        }
                    }, 200);
                    pageIndex++;
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    isLoading = false;
                    pageIndex++;
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
