package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.RechargeRankBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleRedRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.adapter.RankAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/11/1.
 */

public class LoveStepFragment extends BaseBarStyleTextViewFragment implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private final static String TAG = LoveStepFragment.class.getSimpleName();
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
    SwipeMenuRecyclerView rankRecycler;
    @Bind(R.id.love_scroll)
    SwipeRefreshLayout loveScroll;
    @Bind(R.id.no_data)
    TextView noData;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 10;
    private int circleId = -1;
    private RankAdapter rankAdapter;
    private RechargeRankBundleData rechargeRankBundleData;
    private final static String ACTION_STEP_RANK = "com.paobuqian.pbq.step.STEP_ACTION";
    private final static String ACTION_LOVE_RANK = "com.paobuqian.pbq.step.LOVE_ACTION";
    private final static String ACTION_RED_RECORD = "com.paobuqianjin.pbq.step.RED_RECORD_ACTION";
    private String action = "";
    private int currentPage = 1;

    // 设置数据
    public void setRankData(ArrayList<?> data) {
        mData = data;
    }

    @Override
    protected String title() {
        return "";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.love_step_rank_fg;
    }

    @Override
    protected void initView(View viewRoot) {
        rankRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.rank_recycler);
        layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rankRecycler.setLayoutManager(layoutManager);
        rankRecycler.setHasFixedSize(true);
        rankRecycler.setNestedScrollingEnabled(false);
        loveScroll = (SwipeRefreshLayout) viewRoot.findViewById(R.id.love_scroll);
        loveScroll.setOnRefreshListener(this);
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getActivity());
        rankRecycler.addFooterView(loadMoreView); // 添加为Footer。
        rankRecycler.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        rankRecycler.setLoadMoreListener(this);

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
                    getCircleStepRank(1);
                }
                setTitle("步数排行榜");
                action = ACTION_STEP_RANK;
            } else if (ACTION_RED_RECORD.equals(intent.getAction())) {
                setTitle("领取记录");
                action = ACTION_RED_RECORD;
                circleId = intent.getIntExtra(getContext().getPackageName() + "circle_detail", -1);
                if (circleId != -1) {
                    loadRedRecord(1);
                }
            }
        }
        rankAdapter = new RankAdapter(getContext(), mData);
        rankRecycler.setAdapter(rankAdapter);
        rankRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                loveScroll.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        noData = (TextView) viewRoot.findViewById(R.id.no_data);
    }


    private void getCircleStepRank(final int page) {
        if (!isAdded())
            return;
        currentPage = page;
        Map<String, String> param = new HashMap<>();
        param.put("circleid", String.valueOf(circleId));
        param.put("action", "step");
        param.put("page", String.valueOf(page));
        param.put("pagesize", String.valueOf(PAGESIZE));
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlCircleRank, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                loveScroll.setRefreshing(false);
                try {
                    StepRankResponse stepRankResponse = new Gson().fromJson(s, StepRankResponse.class);
                    pageCount = stepRankResponse.getData().getPagenation().getTotalPage();
                    if (page == 1) {
                        mData.clear();
                        rankRecycler.scrollToPosition(0);
                    }
                    rankRecycler.loadMoreFinish(false, true);
                    if (stepRankResponse.getData() != null && stepRankResponse.getData().getData() != null && stepRankResponse.getData().getData().size() > 0) {
                        mData.addAll((ArrayList) stepRankResponse.getData().getData());
                        rankAdapter.notifyItemRangeInserted(mData.size() - stepRankResponse.getData().getData().size(),
                                stepRankResponse.getData().getData().size());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (!isAdded())
                    return;
                loveScroll.setRefreshing(false);
            }
        });
    }


    @Override
    public void onRefresh() {
        if (!isAdded()) return;
        if (ACTION_STEP_RANK.equals(action)) {
            if (circleId != -1) {
                getCircleStepRank(1);
            }
        } else if (ACTION_RED_RECORD.equals(action)) {
            loadRedRecord(1);
        } else if (ACTION_LOVE_RANK.equals(action)) {
            loveScroll.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMore() {
        if (!isAdded()) return;
        if (currentPage > pageCount) {
            PaoToastUtils.showLongToast(getContext(), "没有更多内容");
            rankRecycler.loadMoreFinish(false, true);
            rankRecycler.setLoadMoreView(null);
            rankRecycler.setLoadMoreListener(null);
            return;
        }
        if (ACTION_STEP_RANK.equals(action)) {
            if (circleId != -1) {
                getCircleStepRank(currentPage + 1);
            }
        } else if (ACTION_RED_RECORD.equals(action)) {
            loadRedRecord(currentPage + 1);
        }
    }

    private void loadRedRecord(final int page) {
        if (circleId != -1) {
            currentPage = page;
            String url = NetApi.urlCircleRedRecord + "?circleid=" + String.valueOf(circleId) + "&page=" + page + "&pagesize=" + String.valueOf(PAGESIZE);
            Presenter.getInstance(getContext()).getPaoBuSimple(url, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    if (!isAdded()) {
                        return;
                    }
                    loveScroll.setRefreshing(false);
                    try {
                        CircleRedRecordResponse circleRedRecordResponse = new Gson().fromJson(s, CircleRedRecordResponse.class);
                        pageCount = circleRedRecordResponse.getData().getPagenation().getTotalPage();
                        rankRecycler.loadMoreFinish(false, true);
                        if (page == 1) {
                            if (mData.size() > 0) {
                                mData.clear();
                            }
                            rankRecycler.scrollToPosition(0);
                            mData.addAll((ArrayList) (circleRedRecordResponse.getData().getData()));
                        } else {
                            mData.addAll((ArrayList) (circleRedRecordResponse.getData().getData()));
                            rankAdapter.notifyItemRangeInserted(mData.size() - circleRedRecordResponse.getData().getData().size(),
                                    circleRedRecordResponse.getData().getData().size());
                        }
                    } catch (Exception j) {
                        j.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    if (!isAdded()) return;
                    loveScroll.setRefreshing(false);
                    if (pageIndex == 1) {
                        LocalLog.d(TAG, "无领取记录");
                        noData.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
