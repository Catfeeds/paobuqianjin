package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/19.
 */

public class ReleaseTaskSponsorFragment extends BaseFragment {
    @Bind(R.id.target_step_des)
    TextView targetStepDes;
    @Bind(R.id.target_task_step_num)
    EditText targetTaskStepNum;
    @Bind(R.id.target_task_span)
    RelativeLayout targetTaskSpan;
    @Bind(R.id.target_money_des)
    TextView targetMoneyDes;
    @Bind(R.id.task_pay)
    TextView taskPay;
    @Bind(R.id.target_task_money_num)
    EditText targetTaskMoneyNum;
    @Bind(R.id.money_task_span)
    RelativeLayout moneyTaskSpan;
    @Bind(R.id.target_day_des)
    TextView targetDayDes;
    @Bind(R.id.task_day)
    TextView taskDay;
    @Bind(R.id.target_task_day_num)
    EditText targetTaskDayNum;
    @Bind(R.id.day_task_span)
    RelativeLayout dayTaskSpan;
    @Bind(R.id.pack_day_des)
    TextView packDayDes;
    @Bind(R.id.pack_num_day_des)
    TextView packNumDayDes;
    @Bind(R.id.pack_day_num)
    EditText packDayNum;
    @Bind(R.id.day_pack_num_span)
    RelativeLayout dayPackNumSpan;
    @Bind(R.id.target_step_day_des)
    TextView targetStepDayDes;
    @Bind(R.id.target_step_num_des)
    TextView targetStepNumDes;
    @Bind(R.id.target_step_day_num)
    EditText targetStepDayNum;
    @Bind(R.id.day_step_target_span)
    RelativeLayout dayStepTargetSpan;
    @Bind(R.id.target_people_day_des)
    TextView targetPeopleDayDes;
    @Bind(R.id.people_target_span)
    RelativeLayout peopleTargetSpan;
    @Bind(R.id.sponor_msg_des)
    TextView sponorMsgDes;
    @Bind(R.id.sponor_msg_span)
    RelativeLayout sponorMsgSpan;
    @Bind(R.id.confirm)
    Button confirm;

    @Override
    protected int getLayoutResId() {
        return R.layout.release_task_sponor_fg;
    }

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
