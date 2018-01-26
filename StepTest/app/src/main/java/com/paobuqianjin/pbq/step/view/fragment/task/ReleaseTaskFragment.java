package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SelectFriendForTaskActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/26.
 */
/*
@className :ReleaseTaskFragment
*@date 2018/1/26
*@author
*@description 发布任务界面
*/
public class ReleaseTaskFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = ReleaseTaskFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.line1)
    ImageView line1;
    @Bind(R.id.target_step_des)
    TextView targetStepDes;
    @Bind(R.id.go_to_target_step)
    ImageView goToTargetStep;
    @Bind(R.id.target_task_step_num)
    TextView targetTaskStepNum;
    @Bind(R.id.target_task_span)
    RelativeLayout targetTaskSpan;
    @Bind(R.id.target_money_des)
    TextView targetMoneyDes;
    @Bind(R.id.target_task_money_num)
    TextView targetTaskMoneyNum;
    @Bind(R.id.money_task_span)
    RelativeLayout moneyTaskSpan;
    @Bind(R.id.target_day_des)
    TextView targetDayDes;
    @Bind(R.id.target_task_day_num)
    EditText targetTaskDayNum;
    @Bind(R.id.day_task_span)
    RelativeLayout dayTaskSpan;
    @Bind(R.id.line2)
    ImageView line2;
    @Bind(R.id.task_recv_ico)
    ImageView taskRecvIco;
    @Bind(R.id.task_recv)
    TextView taskRecv;
    @Bind(R.id.add_friend_text)
    TextView addFriendText;
    @Bind(R.id.add_recv)
    ImageView addRecv;
    @Bind(R.id.add_task_friend)
    RelativeLayout addTaskFriend;
    @Bind(R.id.add_friend_des)
    TextView addFriendDes;
    @Bind(R.id.recv_recycler)
    RecyclerView recvRecycler;
    @Bind(R.id.add_ico)
    ImageView addIco;
    @Bind(R.id.attention)
    TextView attention;

    @Override
    protected String title() {
        return "发布任务";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.task_release_fg;
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

    @OnClick({R.id.target_task_span, R.id.add_task_friend, R.id.add_ico})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.target_task_span:
                LocalLog.d(TAG, "设置任务目标步数");
                break;
            case R.id.add_task_friend:
                LocalLog.d(TAG, "添加任务好友");
                startActivity(SelectFriendForTaskActivity.class,null);
                break;
            case R.id.add_ico:
                LocalLog.d(TAG, "查看所有接入任务的好友");
                break;
            default:
                break;
        }
    }
}
