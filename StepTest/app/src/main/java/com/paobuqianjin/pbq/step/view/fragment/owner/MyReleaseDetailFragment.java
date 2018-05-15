package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyReleaseTaskDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyReleaseTaskDetailInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MyTaskDetailStateAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/25.
 */

public class MyReleaseDetailFragment extends BaseBarStyleTextViewFragment implements MyReleaseTaskDetailInterface {
    private final static String TAG = MyReleaseDetailFragment.class.getSimpleName();
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
    @Bind(R.id.release_live_bg)
    RelativeLayout releaseLiveBg;
    @Bind(R.id.release_sample_des)
    TextView releaseSampleDes;
    @Bind(R.id.release_target_days)
    TextView releaseTargetDays;
    @Bind(R.id.release_target)
    TextView releaseTarget;
    @Bind(R.id.release_total_money)
    TextView releaseTotalMoney;
    @Bind(R.id.release_live_rules)
    TextView releaseLiveRules;
    @Bind(R.id.release_live_desc)
    TextView releaseLiveDesc;
    @Bind(R.id.num_1)
    TextView num1;
    /*    @Bind(R.id.num_2)
        TextView num2;*/
    @Bind(R.id.task_days)
    TextView taskDays;
    @Bind(R.id.task_state)
    TextView taskState;
    @Bind(R.id.task_detail_recycler)
    RecyclerView taskDetailRecycler;
    LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.release_detail_fg;
    }

    @Override
    protected String title() {
        return "任务详情";
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
        targetStep = (TextView) viewRoot.findViewById(R.id.target_step);
        targetMoney = (TextView) viewRoot.findViewById(R.id.target_money);
        releaseTargetDays = (TextView) viewRoot.findViewById(R.id.release_target_days);
        releaseTarget = (TextView) viewRoot.findViewById(R.id.release_target);
        releaseTotalMoney = (TextView) viewRoot.findViewById(R.id.release_total_money);
        releaseLiveRules = (TextView) viewRoot.findViewById(R.id.release_live_rules);
        num1 = (TextView) viewRoot.findViewById(R.id.num_1);
      /*  num2 = (TextView) viewRoot.findViewById(R.id.num_2);*/
        taskDetailRecycler = (RecyclerView) viewRoot.findViewById(R.id.task_detail_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        taskDetailRecycler.setLayoutManager(layoutManager);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            int taskId = intent.getIntExtra("taskid", -1);
            if (taskId != -1) {
                LocalLog.d(TAG, "获取 " + taskId + "任务详情");
                Presenter.getInstance(getContext()).getTaskDetail(taskId);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "ErrorCode() enter");
    }

    @Override
    public void response(MyReleaseTaskDetailResponse myReleaseTaskDetailResponse) {
        if (myReleaseTaskDetailResponse.getError() == 0) {
            LocalLog.d(TAG, "MyReleaseTaskDetailResponse() enter " + myReleaseTaskDetailResponse.toString());
            targetStep.setText(myReleaseTaskDetailResponse.getData().getTask().getTask_name());
            String targetMoneyStrFormat = getString(R.string.target_money);
            String targetMoneyStr = String.format(targetMoneyStrFormat, myReleaseTaskDetailResponse.getData().getTask().getAllmoney());
            targetMoney.setText(targetMoneyStr);
            String targetDayStrFormat = getString(R.string.task_days);
            String targetDayStr = String.format(targetDayStrFormat, myReleaseTaskDetailResponse.getData().getTask().getTask_days());
            releaseTargetDays.setText(targetDayStr);
            releaseTotalMoney.setText(targetMoneyStr);
            String releaseTargetFormat = getString(R.string.task_step_release);
            String releaseTargetStr = String.format(releaseTargetFormat, myReleaseTaskDetailResponse.getData().getTask().getTarget_step());
            releaseTarget.setText(releaseTargetStr);

            releaseLiveRules.setText("任务规则:" + myReleaseTaskDetailResponse.getData().getTask().getTask_rule());
            num1.setText(Html.fromHtml(myReleaseTaskDetailResponse.getData().getTask().getTask_desc()));
            taskDetailRecycler.setAdapter(new MyTaskDetailStateAdapter(getContext(), myReleaseTaskDetailResponse.getData().getTask_record()));
        } else if (myReleaseTaskDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}
