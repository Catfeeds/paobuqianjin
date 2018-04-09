package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReceiveTaskResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskRecDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ReceiveTaskInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashInterface;
import com.paobuqianjin.pbq.step.presenter.im.TaskDetailRecInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/26.
 */

public class TaskDetailFragment extends BaseBarStyleTextViewFragment implements TaskDetailRecInterface {
    private final static String TAG = TaskDetailFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.target_step)
    TextView targetStep;
    @Bind(R.id.target_money)
    TextView targetMoney;
    @Bind(R.id.release_use_ico)
    CircleImageView releaseUseIco;
    @Bind(R.id.dear_name)
    TextView dearName;
    @Bind(R.id.dear_id)
    TextView dearId;
    @Bind(R.id.create_message)
    RelativeLayout createMessage;
    @Bind(R.id.release_live_bg)
    RelativeLayout releaseLiveBg;
    @Bind(R.id.button_1)
    Button button1;
    @Bind(R.id.current_step)
    TextView currentStep;
    @Bind(R.id.man_run_ico)
    ImageView manRunIco;
    @Bind(R.id.process_run)
    ImageView processRun;
    @Bind(R.id.button_2)
    Button button2;
    @Bind(R.id.task_tells)
    TextView taskTells;
    @Bind(R.id.step_target)
    TextView stepTarget;
    @Bind(R.id.target_moneys)
    TextView targetMoneys;
    @Bind(R.id.try_days_des)
    TextView tryDaysDes;
    @Bind(R.id.try_target)
    TextView tryTarget;
    @Bind(R.id.task_rules)
    TextView taskRules;
    @Bind(R.id.task_desc)
    TextView taskDesc;
    @Bind(R.id.flag_1)
    TextView flag1;
    @Bind(R.id.num_1)
    RelativeLayout num1;
    @Bind(R.id.flag_2)
    TextView flag2;
    @Bind(R.id.num_2)
    RelativeLayout num2;
    @Bind(R.id.button_action)
    Button buttonAction;
    @Bind(R.id.step_des_run)
    TextView stepDesRun;
    private int taskId = -1;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_detaile_fg;
    }

    @Override
    protected String title() {
        return "任务详情";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);


        Intent intent = getActivity().getIntent();
        if (intent != null) {
            taskId = intent.getIntExtra("taskid", -1);
            if (taskId != -1) {
                LocalLog.d(TAG, "获取 " + taskId + "任务详情");
                Presenter.getInstance(getContext()).getTaskDetailRec(taskId);
            }
        }
        targetStep = (TextView) viewRoot.findViewById(R.id.target_step);
        targetMoney = (TextView) viewRoot.findViewById(R.id.target_money);
        releaseUseIco = (CircleImageView) viewRoot.findViewById(R.id.release_use_ico);
        dearName = (TextView) viewRoot.findViewById(R.id.dear_name);
        dearId = (TextView) viewRoot.findViewById(R.id.dear_id);
        currentStep = (TextView) viewRoot.findViewById(R.id.current_step);
        stepTarget = (TextView) viewRoot.findViewById(R.id.step_target);
        targetMoneys = (TextView) viewRoot.findViewById(R.id.target_moneys);
        tryDaysDes = (TextView) viewRoot.findViewById(R.id.try_days_des);
        tryTarget = (TextView) viewRoot.findViewById(R.id.try_target);
        buttonAction = (Button) viewRoot.findViewById(R.id.button_action);
        stepDesRun = (TextView) viewRoot.findViewById(R.id.step_des_run);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(TaskRecDetailResponse taskRecDetailResponse) {
        LocalLog.d(TAG, "TaskRecDetailResponse() enter " + taskRecDetailResponse.toString());
        if (taskRecDetailResponse.getError() == 0) {
            targetStep.setText(taskRecDetailResponse.getData().getTask_name());
            targetMoney.setText("奖励金额: " + String.valueOf(taskRecDetailResponse.getData().getReward_amount()));
            Presenter.getInstance(getContext()).getImage(releaseUseIco, taskRecDetailResponse.getData().getAvatar());
            dearName.setText(taskRecDetailResponse.getData().getNickname());
            dearId.setText(String.valueOf(taskRecDetailResponse.getData().getUserid()));
            stepDesRun.setText(String.valueOf(taskRecDetailResponse.getData().getUser_step()) + "/" + String.valueOf(taskRecDetailResponse.getData().getTarget_step()));

            long startTime = taskRecDetailResponse.getData().getActivity_start_time();
            String start_timeStr = DateTimeUtil.formatDateTime(startTime * 1000);

            long endTime = taskRecDetailResponse.getData().getActivity_end_time();
            String end_timeStr = DateTimeUtil.formatDateTime(endTime * 1000);
            String dateStartStr = start_timeStr.replace("-", "/");
            String dateEndStr = end_timeStr.replace("-", "/");
            stepTarget.setText("开始时间: " + dateStartStr);
            targetMoneys.setText("结束时间:" + dateEndStr);
            tryDaysDes.setText("目标步数: " + taskRecDetailResponse.getData().getTarget_step() + " 步");
            tryTarget.setText("奖励金额: " + taskRecDetailResponse.getData().getReward_amount() + "元");
            if (taskRecDetailResponse.getData().getIs_finished() == 1) {
                buttonAction.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_e4393c));
                buttonAction.setOnClickListener(onClickListener);
            }
        } else if (taskRecDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_action:
                    LocalLog.d(TAG, "领取奖励");
                    if (taskId != -1) {
                        Presenter.getInstance(getContext()).putTask("receive_reward", taskId);
                    }
                    break;
            }
        }
    };

    @Override
    public void response(RecPayResponse recPayResponse) {
        LocalLog.d(TAG, "RecPayResponse() " + recPayResponse.toString());
        if (recPayResponse.getError() == 0) {
            buttonAction.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_b8bbbd));
        } else if (recPayResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
