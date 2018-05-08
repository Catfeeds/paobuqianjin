package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReceiveTaskResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskRecDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
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
    @Bind(R.id.vip_flg)
    ImageView vipFlg;
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
    @Bind(R.id.step_des_run)
    TextView stepDesRun;
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
    @Bind(R.id.task_ruls_detail)
    TextView taskRulsDetail;
    @Bind(R.id.button_action)
    Button buttonAction;

    private int taskId = -1;
    private LocalBroadcastManager localBroadcastManager;
    private final static String REC_TASK_ACTION = "com.paobuqianjin.pbq.step.REC_TASK_ACTION";
    private final static String REC_GIFT_ACTION = "com.paobuqianjin.pbq.step.REC_GIFT_ACTION";

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
        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());

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
        vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
        taskRulsDetail = (TextView) viewRoot.findViewById(R.id.task_ruls_detail);
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
            if (targetStep == null) {
                return;
            }

            if (taskRecDetailResponse.getData().getIs_receive() == 1) {
                if (taskRecDetailResponse.getData().getIs_finished() == 1) {
                    buttonAction.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_e4393c));
                    buttonAction.setEnabled(true);
                    buttonAction.setText("领取奖励");
                } else {
                    buttonAction.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_b8bbbd));
                    buttonAction.setText("进行中");
                    buttonAction.setEnabled(false);
                }
            } else {
                buttonAction.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_e4393c));
                buttonAction.setText("领取任务");
                buttonAction.setEnabled(true);
            }
            targetStep.setText(taskRecDetailResponse.getData().getTask_name());
            targetMoney.setText("奖励金额: " + String.valueOf(taskRecDetailResponse.getData().getReward_amount()));
            Presenter.getInstance(getContext()).getImage(releaseUseIco, taskRecDetailResponse.getData().getAvatar());
            dearName.setText(taskRecDetailResponse.getData().getNickname());
            dearId.setText("ID:" + String.valueOf(taskRecDetailResponse.getData().getUserid()));
            stepDesRun.setText(String.valueOf(taskRecDetailResponse.getData().getUser_step()) + "/" + String.valueOf(taskRecDetailResponse.getData().getTarget_step()));
            if (taskRecDetailResponse.getData().getVip() == 1) {
                vipFlg.setVisibility(View.VISIBLE);
            }
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
            taskRulsDetail.setText(Html.fromHtml(taskRecDetailResponse.getData().getTask_desc()));

            buttonAction.setOnClickListener(onClickListener);
        } else if (taskRecDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        } else {
            Toast.makeText(getContext(), taskRecDetailResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {
                LocalLog.d(TAG, "领取任务或者奖励出错");
            } else if (object instanceof ReceiveTaskResponse) {
                LocalLog.d(TAG, "领取任务成功");
                if (((ReceiveTaskResponse) object).getError() == 0) {
                    buttonAction.setText("进行中");
                    buttonAction.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_b8bbbd));
                    buttonAction.setEnabled(false);
                    Intent intent = new Intent();
                    intent.setAction(REC_TASK_ACTION);
                    intent.putExtra("taskid", taskId);
                    localBroadcastManager.sendBroadcast(intent);

                }
            } else if (object instanceof RecPayResponse) {
                LocalLog.d(TAG, "领取奖励成功");
                if (((RecPayResponse) object).getError() == 0) {
                    buttonAction.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_b8bbbd));
                    buttonAction.setEnabled(false);
                    Intent intent = new Intent();
                    intent.setAction(REC_GIFT_ACTION);
                    intent.putExtra("taskid", taskId);
                    localBroadcastManager.sendBroadcast(intent);
                }
            }
        }
    };
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_action:
                    if (buttonAction.getText().toString().equals("领取奖励")) {
                        if (taskId != -1) {
                            Presenter.getInstance(getContext()).putTask("receive_reward", taskId, innerCallBack);
                        }
                    } else if (buttonAction.getText().toString().equals("领取任务")) {
                        if (taskId != -1) {
                            Presenter.getInstance(getContext()).putTask("receive_task", taskId, innerCallBack);
                        }
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
        } else {
            Toast.makeText(getContext(), recPayResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
