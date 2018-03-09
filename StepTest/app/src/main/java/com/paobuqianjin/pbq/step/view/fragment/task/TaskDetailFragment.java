package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskRecDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.TaskDetailRecInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
            int taskId = intent.getIntExtra("taskid", -1);
            if (taskId != -1) {
                LocalLog.d(TAG, "获取 " + taskId + "任务详情");
                Presenter.getInstance(getContext()).getTaskDetailRec(taskId);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick(R.id.button_action)
    public void onClick() {
        LocalLog.d(TAG, "领取奖励");
    }

    @Override
    public void response(TaskRecDetailResponse taskRecDetailResponse) {
        LocalLog.d(TAG, "TaskRecDetailResponse() enter " + taskRecDetailResponse.toString());
    }
}
