package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleStepRankWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRandWeekResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepRankResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CircleStepDetailDanInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/19.
 */

public class CircleStepDanFragment extends BaseFragment implements CircleStepDetailDanInterface {
    private final static String TAG = CircleStepDanFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.time_go)
    TextView timeGo;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.go_down_span)
    RelativeLayout goDownSpan;
    @Bind(R.id.bg_dan)
    ImageView bgDan;
    @Bind(R.id.king_head_icon)
    CircleImageView kingHeadIcon;
    @Bind(R.id.king_name)
    TextView kingName;
    @Bind(R.id.num_des)
    TextView numDes;
    @Bind(R.id.circle_target)
    TextView circleTarget;
    @Bind(R.id.your_dan)
    TextView yourDan;
    @Bind(R.id.head_icon_user)
    CircleImageView headIconUser;
    @Bind(R.id.user_name_rank)
    TextView userNameRank;
    @Bind(R.id.step_num_my)
    TextView stepNumMy;
    @Bind(R.id.your_dan_layer)
    RelativeLayout yourDanLayer;
    @Bind(R.id.line_dan)
    ImageView lineDan;
    @Bind(R.id.dan_detail_recycler)
    RecyclerView danDetailRecycler;
    @Bind(R.id.buttone_left_bar)
    RelativeLayout buttoneLeftBar;
    @Bind(R.id.rank_icon)
    ImageView rankIcon;
    @Bind(R.id.times_today)
    TextView timesToday;
    @Bind(R.id.vip_flg)
    ImageView vipFlg;
    @Bind(R.id.circle_scroll)
    BounceScrollView circleScroll;
    private int circleId = -1;
    LinearLayoutManager layoutManager;
    private final static String ACTION_CIRCLE_HONOR = "com.paobuqianjin.pbq.ACTION_CIRCLE_HONOR";
    CircleStepRankResponse circleStepRankResponse;
    StepRankResponse stepRankResponse;
    StepRandWeekResponse stepRandWeekResponse;
    CircleStepRankWeekResponse circleStepRankWeekResponse;
    private int pageIndexDay = 1, pageDayCount = 0;
    private int pageIndexWeek = 1, pageWeekCount = 0;
    private final static int PAGE_SIZE = 10;
    private View popCircleOpBar;
    private PopupWindow popupOpWindowTop;
    private TranslateAnimation animationCircleType;
    private HonorDetailAdapter honorDetailAdapter;
    private boolean isLoading = false;

    @Override

    protected int getLayoutResId() {
        return R.layout.circles_step_dan_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
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
        kingHeadIcon = (CircleImageView) viewRoot.findViewById(R.id.king_head_icon);
        kingName = (TextView) viewRoot.findViewById(R.id.king_name);
        yourDan = (TextView) viewRoot.findViewById(R.id.your_dan);
        headIconUser = (CircleImageView) viewRoot.findViewById(R.id.head_icon_user);
        userNameRank = (TextView) viewRoot.findViewById(R.id.user_name_rank);
        stepNumMy = (TextView) viewRoot.findViewById(R.id.step_num_my);
        danDetailRecycler = (RecyclerView) viewRoot.findViewById(R.id.dan_detail_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        danDetailRecycler.setLayoutManager(layoutManager);
        danDetailRecycler.setHasFixedSize(true);
        danDetailRecycler.setNestedScrollingEnabled(false);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTitle.setText("圈子排行榜");
        barReturnDrawable = (ImageView) viewRoot.findViewById(R.id.bar_return_drawable);
        barReturnDrawable.setOnClickListener(onClickListener);

        goDownSpan = (RelativeLayout) viewRoot.findViewById(R.id.go_down_span);
        timeGo = (TextView) viewRoot.findViewById(R.id.time_go);
        goDownSpan.setOnClickListener(onClickListener);
        numDes = (TextView) viewRoot.findViewById(R.id.num_des);
        circleTarget = (TextView) viewRoot.findViewById(R.id.circle_target);
        buttoneLeftBar = (RelativeLayout) viewRoot.findViewById(R.id.buttone_left_bar);
        buttoneLeftBar.setOnClickListener(onClickListener);
        timesToday = (TextView) viewRoot.findViewById(R.id.times_today);
        timesToday.setText(DateTimeUtil.getLocalTime());
        vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
        circleScroll = (BounceScrollView) viewRoot.findViewById(R.id.circle_scroll);
        circleScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                if (topOrBottom == 0) {

                } else if (topOrBottom == 1) {
                    if (timeGo.getText().equals("今日")) {
                        if (pageIndexDay <= pageDayCount && !isLoading) {
                            LocalLog.d(TAG, "今日加载更多!");
                            isLoading = true;
                            Presenter.getInstance(getContext()).getCircleStepRankDay(circleId, pageIndexDay, PAGE_SIZE);
                        }
                    } else {
                        if (pageIndexWeek <= pageWeekCount && !isLoading) {
                            LocalLog.d(TAG, "本周加载更多!");
                            isLoading = true;
                            Presenter.getInstance(getContext()).getCircleStepRankWeek(circleId, pageIndexWeek, PAGE_SIZE);
                        }
                    }
                }
            }
        });
        Intent intent = getActivity().getIntent();
        if (intent != null && ACTION_CIRCLE_HONOR.equals(intent.getAction())) {
            circleId = intent.getIntExtra("circleid", -1);
            if (circleId != -1) {
                Presenter.getInstance(getContext()).getUserCircleRankDetail(circleId);
                Presenter.getInstance(getContext()).getCircleDetailInCircleDan(circleId);
                Presenter.getInstance(getContext()).getCircleStepRankDay(circleId, pageIndexDay, PAGE_SIZE);
                upDateCircleDetail(kingName, kingHeadIcon, null, circleId);
            }
        }

    }

    private void upDateCircleDetail(TextView myName, ImageView imageView, TextView stepNum, int circleId) {
        LocalLog.d(TAG, "upDateCircleDetail() enter" + circleId);
        Presenter.getInstance(getContext()).getCircleDetailView(myName, imageView, stepNum, circleId);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttone_left_bar:
                    getActivity().finish();
                    break;
                case R.id.go_down_span:
                    LocalLog.d(TAG, "弹出查看排行...选择");
                    popWeekDaySelect();
                    break;
                case R.id.today_text:
                    LocalLog.d(TAG, "今日");
                    timeGo.setText("今日");
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    if (stepRankResponse != null && circleStepRankResponse != null) {
                        updateCircleStepRank(circleStepRankResponse);
                        updateDayRank(stepRankResponse);
                    }
                    break;
                case R.id.week_text:
                    LocalLog.d(TAG, "本周");
                    timeGo.setText("本周");
                    if (popupOpWindowTop != null) {
                        popupOpWindowTop.dismiss();
                    }
                    if (circleStepRankWeekResponse == null && stepRandWeekResponse == null) {
                        pageIndexWeek = 1;
                        Presenter.getInstance(getContext()).getCircleRankNum(circleId);
                        Presenter.getInstance(getContext()).getCircleStepRankWeek(circleId, pageIndexWeek, PAGE_SIZE);
                    } else {
                        updateCircleStepWeekRank(circleStepRankWeekResponse);
                        updateWeekRank(stepRandWeekResponse);
                    }
                    break;
            }
        }
    };

    private void popWeekDaySelect() {
        LocalLog.d(TAG, "popWeekDaySelect() enter");
        popCircleOpBar = View.inflate(getContext(), R.layout.week_day_select_layout, null);
        popupOpWindowTop = new PopupWindow(popCircleOpBar,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popCircleOpBar.findViewById(R.id.today_text).setOnClickListener(onClickListener);
        popCircleOpBar.findViewById(R.id.week_text).setOnClickListener(onClickListener);
        popupOpWindowTop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popWindow dismiss() ");
                popupOpWindowTop = null;
            }
        });

        popupOpWindowTop.setFocusable(true);
        popupOpWindowTop.setOutsideTouchable(true);
        popupOpWindowTop.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupOpWindowTop.showAsDropDown(barTvRight, 20, -10);
        popCircleOpBar.startAnimation(animationCircleType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(CircleStepRankResponse circleStepRankResponse) {
        LocalLog.d(TAG, "CircleStepRankResponse() enter " + circleStepRankResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (circleStepRankResponse.getError() == 0) {
            if (this.circleStepRankResponse == null) {
                this.circleStepRankResponse = circleStepRankResponse;
            }
            updateCircleStepRank(circleStepRankResponse);
        } else if (circleStepRankResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    private void updateCircleStepRank(CircleStepRankResponse circleStepRankResponse) {
        if (circleStepRankResponse != null) {
            userNameRank.setText(String.valueOf(circleStepRankResponse.getData().getData().getNickname()));
            yourDan.setText(String.valueOf(circleStepRankResponse.getData().getData().getRank()));
            stepNumMy.setText(String.valueOf(circleStepRankResponse.getData().getData().getStep_number()));
            Presenter.getInstance(getContext()).getImage(headIconUser, circleStepRankResponse.getData().getData().getAvatar());
        }
    }

    @Override
    public void response(ErrorCode errorCode) {

    }

    @Override
    public void response(StepRankResponse stepRankResponse) {
        if (stepRankResponse.getError() == 0 && isAdded()) {
            if (stepRankResponse.getError() == 0) {
                if (this.stepRankResponse == null) {
                    this.stepRankResponse = stepRankResponse;
                }
            }
            numDes.setText("人员数：" + String.valueOf(stepRankResponse.getData().getPagenation().getTotalCount()));
            pageDayCount = stepRankResponse.getData().getPagenation().getTotalPage();
            updateDayRank(stepRankResponse);

        } else if (stepRankResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    private void updateDayRank(StepRankResponse stepRankResponse) {
        if (stepRankResponse != null) {
            if (honorDetailAdapter == null || this.stepRankResponse == stepRankResponse) {
                honorDetailAdapter = new HonorDetailAdapter(getContext(), this.stepRankResponse.getData().getData());
                danDetailRecycler.setAdapter(honorDetailAdapter);
                pageIndexDay++;
            } else {
                this.stepRankResponse.getData().getData().addAll(stepRankResponse.getData().getData());
                honorDetailAdapter.notifyItemRangeInserted(this.stepRankResponse.getData().getData().size() - stepRankResponse.getData().getData().size(),
                        stepRankResponse.getData().getData().size());
                honorDetailAdapter.notifyItemRangeChanged(this.stepRankResponse.getData().getData().size() - stepRankResponse.getData().getData().size(),
                        stepRankResponse.getData().getData().size());
                pageIndexDay++;
                danDetailRecycler.requestLayout();
            }
        }
        isLoading = false;
    }

    @Override
    public void response(CircleDetailResponse circleDetailResponse) {
        if (!isAdded()) {
            return;
        }
        if (circleDetailResponse.getError() == 0) {
            circleTarget.setText("社群目标：" + String.valueOf(circleDetailResponse.getData().getTarget()));
        } else if (circleDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(StepRandWeekResponse stepRandWeekResponse) {
        LocalLog.d(TAG, "圈子用户本周排名");
        if (stepRandWeekResponse.getError() == 0) {
            if (this.stepRandWeekResponse == null && pageIndexWeek == 1) {
                this.stepRandWeekResponse = stepRandWeekResponse;
            }
            pageWeekCount = stepRandWeekResponse.getData().getPagenation().getTotalPage();
            updateWeekRank(stepRandWeekResponse);
        } else if (stepRandWeekResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(CircleStepRankWeekResponse circleStepRankWeekResponse) {
        LocalLog.d(TAG, "当前用户本周排名");
        if (circleStepRankWeekResponse.getError() == 0) {
            if (this.circleStepRankWeekResponse == null) {
                this.circleStepRankWeekResponse = circleStepRankWeekResponse;
            }
            updateCircleStepWeekRank(circleStepRankWeekResponse);
        } else if (circleStepRankWeekResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    private void updateWeekRank(StepRandWeekResponse stepRandWeekResponse) {
        if (stepRandWeekResponse != null) {
            if (honorDetailAdapter == null || this.stepRandWeekResponse == stepRandWeekResponse) {
                honorDetailAdapter = new HonorDetailAdapter(getContext(), this.stepRandWeekResponse.getData().getData().getMember());
                pageIndexWeek++;
                danDetailRecycler.setAdapter(honorDetailAdapter);
            } else {
                this.stepRandWeekResponse.getData().getData().getMember().addAll(stepRandWeekResponse.getData().getData().getMember());
                honorDetailAdapter.notifyItemRangeInserted(this.stepRandWeekResponse.getData().getData().getMember().size() - stepRandWeekResponse.getData().getData().getMember().size(),
                        stepRandWeekResponse.getData().getData().getMember().size());
                honorDetailAdapter.notifyItemRangeChanged(this.stepRandWeekResponse.getData().getData().getMember().size() - stepRandWeekResponse.getData().getData().getMember().size(),
                        stepRandWeekResponse.getData().getData().getMember().size());
                pageIndexWeek++;
                danDetailRecycler.requestLayout();
            }
        }
        isLoading = false;
    }

    private void updateCircleStepWeekRank(CircleStepRankWeekResponse circleStepRankWeekResponse) {
        if (circleStepRankWeekResponse != null) {
            userNameRank.setText(String.valueOf(circleStepRankWeekResponse.getData().getNickname()));
            yourDan.setText(String.valueOf(circleStepRankWeekResponse.getData().getRank()));
            stepNumMy.setText(String.valueOf(circleStepRankWeekResponse.getData().getStep_number()));
            Presenter.getInstance(getContext()).getImage(headIconUser, circleStepRankWeekResponse.getData().getAvatar());
        }
    }
}
