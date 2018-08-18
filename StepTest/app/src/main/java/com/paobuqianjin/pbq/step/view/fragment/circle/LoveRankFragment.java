package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.RechargeRankBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleRedRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.adapter.RankAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by pbq on 2017/12/29.
 */

public class LoveRankFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = LoveRankFragment.class.getSimpleName();
    LinearLayoutManager layoutManager;
    ArrayList<?> mData = new ArrayList<>();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.rank_recycler)
    RecyclerView rankRecycler;
    @Bind(R.id.love_scroll)
    BounceScrollView loveScroll;
    @Bind(R.id.no_data)
    TextView noData;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 10;
    private int circleId = -1;
    private boolean isLoading = false;
    private RankAdapter rankAdapter;
    private RechargeRankBundleData rechargeRankBundleData;
    private final static String ACTION_STEP_RANK = "com.paobuqian.pbq.step.STEP_ACTION";
    private final static String ACTION_LOVE_RANK = "com.paobuqian.pbq.step.LOVE_ACTION";
    private final static String ACTION_RED_RECORD = "com.paobuqianjin.pbq.step.RED_RECORD_ACTION";
    private String action = "";

    // 设置数据
    public void setRankData(ArrayList<?> data) {
        mData = data;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.love_rank_fg;
    }

    @Override
    protected String title() {
        return "";
    }

    @Override
    protected void initView(View viewRoot) {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (ACTION_LOVE_RANK.equals(intent.getAction()) && intent.getParcelableExtra(getContext().getPackageName() + "circle_detail") != null) {
                rechargeRankBundleData = (RechargeRankBundleData) intent.getParcelableExtra(getContext().getPackageName() + "circle_detail");
                if (rechargeRankBundleData == null) {
                    return;
                } else {
                    setRankData(rechargeRankBundleData.getRechargeRankData());
                }
                setTitle("爱心排行榜");
                action = ACTION_LOVE_RANK;
            } else if (ACTION_STEP_RANK.equals(intent.getAction())) {
                circleId = intent.getIntExtra(getContext().getPackageName() + "circle_detail", -1);
                if (circleId != -1) {
                    Presenter.getInstance(getContext()).getCircleRankMoreDetail(circleId, pageIndex, PAGESIZE, stepRankCallBack);
                }
                setTitle("步数排行榜");
                action = ACTION_STEP_RANK;
            } else if (ACTION_RED_RECORD.equals(intent.getAction())) {
                setTitle("领取记录");
                action = ACTION_RED_RECORD;
                circleId = intent.getIntExtra(getContext().getPackageName() + "circle_detail", -1);
                if (circleId != -1) {
                    loadRedRecord();
                }
            }
        }
        LocalLog.d(TAG, "initView() enter ");
        rankRecycler = (RecyclerView) viewRoot.findViewById(R.id.rank_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rankRecycler.setLayoutManager(layoutManager);
        rankRecycler.setHasFixedSize(true);
        rankRecycler.setNestedScrollingEnabled(false);
        loveScroll = (BounceScrollView) viewRoot.findViewById(R.id.love_scroll);
        loveScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                if (topOrBottom == 0) {

                } else if (topOrBottom == 1) {
                    if (pageIndex <= pageCount && !isLoading)
                        if (ACTION_STEP_RANK.equals(action)) {
                            if (circleId != -1) {
                                isLoading = true;
                                Presenter.getInstance(getContext()).getCircleRankMoreDetail(circleId, pageIndex, PAGESIZE, stepRankCallBack);
                            }
                        } else if (ACTION_RED_RECORD.equals(action)) {
                            isLoading = true;
                            loadRedRecord();
                        }
                }
            }
        });
        if (mData.size() > 0) {
            rankAdapter = new RankAdapter(getContext(), mData);
            rankRecycler.setAdapter(rankAdapter);
        }

        noData = (TextView) viewRoot.findViewById(R.id.no_data);
    }

    private void loadRedRecord() {
        if (circleId != -1) {
            String url = NetApi.urlCircleRedRecord + "?circleid=" + String.valueOf(circleId) + "&page=" + pageIndex + "&pagesize=" + String.valueOf(PAGESIZE);
            Presenter.getInstance(getContext()).getPaoBuSimple(url, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    if (!isAdded()) {
                        return;
                    }
                    try {
                        CircleRedRecordResponse circleRedRecordResponse = new Gson().fromJson(s, CircleRedRecordResponse.class);
                        pageCount = circleRedRecordResponse.getData().getPagenation().getTotalPage();
                        if (pageIndex == 1) {
                            if (mData.size() > 0) {
                                mData.clear();
                            }
                            mData.addAll((ArrayList) (circleRedRecordResponse.getData().getData()));
                            rankAdapter = new RankAdapter(getContext(), mData);
                            rankRecycler.setAdapter(rankAdapter);
                        } else {
                            mData.addAll((ArrayList) (circleRedRecordResponse.getData().getData()));
                            rankAdapter.notifyItemRangeInserted(mData.size() - circleRedRecordResponse.getData().getData().size(),
                                    circleRedRecordResponse.getData().getData().size());
                            rankRecycler.requestLayout();
                        }
                        pageIndex++;
                    } catch (Exception j) {
                        j.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    if (pageIndex == 1) {
                        LocalLog.d(TAG, "无领取记录");
                        noData.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private InnerCallBack stepRankCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {

            } else if (object instanceof StepRankResponse) {
                if (((StepRankResponse) object).getError() == 0 && isAdded()) {
                    pageCount = ((StepRankResponse) object).getData().getPagenation().getTotalPage();
                    if (pageIndex == 1) {
                        if (mData.size() > 0) {
                            mData.clear();
                        }
                        mData.addAll((ArrayList) ((StepRankResponse) object).getData().getData());
                        rankAdapter = new RankAdapter(getContext(), mData);
                        rankRecycler.setAdapter(rankAdapter);
                    } else {
                        mData.addAll((ArrayList) ((StepRankResponse) object).getData().getData());
                        rankAdapter.notifyItemRangeInserted(mData.size() - ((StepRankResponse) object).getData().getData().size(),
                                ((StepRankResponse) object).getData().getData().size());
                        rankRecycler.setAdapter(rankAdapter);
                    }
                    pageIndex++;
                } else if (((StepRankResponse) object).getError() == 100) {
                    exitTokenUnfect();
                }
            }
            isLoading = false;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
