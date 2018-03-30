package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.content.Intent;
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
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.HonorDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

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
        super.initView(viewRoot);

        kingHeadIcon = (CircleImageView) viewRoot.findViewById(R.id.king_head_icon);
        kingName = (TextView) viewRoot.findViewById(R.id.king_name);
        yourDan = (TextView) viewRoot.findViewById(R.id.your_dan);
        headIconUser = (CircleImageView) viewRoot.findViewById(R.id.head_icon_user);
        userNameRank = (TextView) viewRoot.findViewById(R.id.user_name_rank);
        stepNumMy = (TextView) viewRoot.findViewById(R.id.step_num_my);
        danDetailRecycler = (RecyclerView) viewRoot.findViewById(R.id.dan_detail_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        danDetailRecycler.setLayoutManager(layoutManager);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTitle.setText("圈子排行榜");
        barReturnDrawable = (ImageView) viewRoot.findViewById(R.id.bar_return_drawable);
        barReturnDrawable.setOnClickListener(onClickListener);

        goDownSpan = (RelativeLayout) viewRoot.findViewById(R.id.go_down_span);
        timeGo = (TextView) viewRoot.findViewById(R.id.time_go);
        goDownSpan.setOnClickListener(onClickListener);
        numDes = (TextView) viewRoot.findViewById(R.id.num_des);
        circleTarget = (TextView) viewRoot.findViewById(R.id.circle_target);
        Intent intent = getActivity().getIntent();
        if (intent != null && ACTION_CIRCLE_HONOR.equals(intent.getAction())) {
            circleId = intent.getIntExtra("circleid", -1);
            if (circleId != -1) {
                Presenter.getInstance(getContext()).getUserCircleRankDetail(circleId);
                Presenter.getInstance(getContext()).getCircleDetailInCircleDan(circleId);
                Presenter.getInstance(getContext()).getCircleStepRankDay(circleId, pageIndexDay, PAGE_SIZE);

            }
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bar_return_drawable:
                    getActivity().finish();
                    break;
                case R.id.go_down_span:
                    LocalLog.d(TAG, "弹出查看排行...选择");
                    popWeekDaySelect();
                    break;
                case R.id.today_text:
                    LocalLog.d(TAG, "今日");
                    timeGo.setText("今日");
                    updateCircleStepRank(circleStepRankResponse);
                    updateDayRank(stepRankResponse);
                    break;
                case R.id.week_text:
                    LocalLog.d(TAG, "本周");
                    timeGo.setText("本周");
                    updateCircleStepWeekRank(circleStepRankWeekResponse);
                    updateWeekRank(stepRandWeekResponse);
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
        if (circleStepRankResponse.getError() == 0) {
            if (this.circleStepRankResponse == null) {
                this.circleStepRankResponse = circleStepRankResponse;
            }
            updateCircleStepRank(circleStepRankResponse);
        }
    }

    private void updateCircleStepRank(CircleStepRankResponse circleStepRankResponse) {
        if (circleStepRankResponse != null) {
            userNameRank.setText(String.valueOf(circleStepRankResponse.getData().getNickname()));
            yourDan.setText(String.valueOf(circleStepRankResponse.getData().getRank()));
            stepNumMy.setText(String.valueOf(circleStepRankResponse.getData().getStep_number()));
            Presenter.getInstance(getContext()).getImage(headIconUser, circleStepRankResponse.getData().getAvatar());
        }
    }

    @Override
    public void response(ErrorCode errorCode) {

    }

    @Override
    public void response(StepRankResponse stepRankResponse) {
        if (stepRankResponse.getError() == 0) {
            if (stepRankResponse.getError() == 0) {
                this.stepRankResponse = stepRankResponse;
            }
            numDes.setText("人员数：" + String.valueOf(stepRankResponse.getData().getData().size()));
            updateDayRank(stepRankResponse);
            Presenter.getInstance(getContext()).getCircleRankNum(circleId);
            Presenter.getInstance(getContext()).getCircleStepRankWeek(circleId, pageIndexWeek, PAGE_SIZE);
        }
    }

    private void updateDayRank(StepRankResponse stepRankResponse) {
        if (stepRankResponse != null) {
            if (stepRankResponse.getData().getPagenation().getTotalCount() > 0) {
                Presenter.getInstance(getContext()).getImage(kingHeadIcon, stepRankResponse.getData().getData().get(0).getAvatar());
                kingName.setText(stepRankResponse.getData().getData().get(0).getNickname());
            }
            danDetailRecycler.setAdapter(new HonorDetailAdapter(getContext(), stepRankResponse.getData().getData()));
        }
    }

    @Override
    public void response(CircleDetailResponse circleDetailResponse) {
        if (circleDetailResponse.getError() == 0) {
            circleTarget.setText("圈子目标：" + String.valueOf(circleDetailResponse.getData().getTarget()));
        }
    }

    @Override
    public void response(StepRandWeekResponse stepRandWeekResponse) {
        LocalLog.d(TAG, "圈子用户本周排名");
        if (stepRandWeekResponse.getError() == 0) {
            if (this.stepRandWeekResponse == null) {
                this.stepRandWeekResponse = stepRandWeekResponse;
            }
            //updateWeekRank(stepRandWeekResponse);
        }
    }

    private void updateWeekRank(StepRandWeekResponse stepRandWeekResponse) {
        if (stepRandWeekResponse != null) {
            if (stepRandWeekResponse.getData().getPagenation().getTotalCount() > 0) {
                Presenter.getInstance(getContext()).getImage(kingHeadIcon, stepRandWeekResponse.getData().getData().get(0).getAvatar());
                kingName.setText(stepRandWeekResponse.getData().getData().get(0).getNickname());
            }
            danDetailRecycler.setAdapter(new HonorDetailAdapter(getContext(), stepRandWeekResponse.getData().getData()));
        }
    }

    @Override
    public void response(CircleStepRankWeekResponse circleStepRankWeekResponse) {
        LocalLog.d(TAG, "当前用户本周排名");
        if (circleStepRankWeekResponse.getError() == 0) {
            if (this.circleStepRankWeekResponse == null) {
                this.circleStepRankWeekResponse = circleStepRankWeekResponse;
            }
            //updateCircleStepWeekRank(circleStepRankWeekResponse);
        }
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
