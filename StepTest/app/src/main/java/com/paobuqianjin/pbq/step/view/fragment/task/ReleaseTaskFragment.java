package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.LikeBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskReleaseParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicLikeListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskReleaseResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.TaskReleaseInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SelectFriendForTaskActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;

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
    TextView goToTargetStep;
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
    @Bind(R.id.confirm)
    Button confirm;

    private static final int SELECT_FRIENDS = 0;
    ArrayList<UserFriendResponse.DataBeanX.DataBean> dataBeans = null;
    private TaskReleaseParam taskReleaseParam = new TaskReleaseParam();
    private String friends;
    LinearLayoutManager layoutManager;

    @Override
    protected String title() {
        return "发布任务";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.task_release_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(taskReleaseInterface);
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
        Presenter.getInstance(getContext()).dispatchUiInterface(taskReleaseInterface);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        targetTaskStepNum = (EditText) viewRoot.findViewById(R.id.target_task_step_num);
        targetTaskMoneyNum = (EditText) viewRoot.findViewById(R.id.target_task_money_num);
        targetTaskDayNum = (EditText) viewRoot.findViewById(R.id.target_task_day_num);
        recvRecycler = (RecyclerView) viewRoot.findViewById(R.id.recv_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recvRecycler.setLayoutManager(layoutManager);

        addFriendDes = (TextView) viewRoot.findViewById(R.id.add_friend_des);
    }

    private TaskReleaseInterface taskReleaseInterface = new TaskReleaseInterface() {
        @Override
        public void response(TaskReleaseResponse taskReleaseResponse) {
            LocalLog.d(TAG, "TaskReleaseResponse() enter");
            if (taskReleaseResponse.getError() == 0) {
                LocalLog.d(TAG, "任务发布成功");
                getActivity().finish();
            }

        }
    };

    @OnClick({R.id.target_task_span, R.id.add_task_friend, R.id.add_ico, R.id.confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.target_task_span:
                LocalLog.d(TAG, "设置任务目标步数");
                break;
            case R.id.add_task_friend:
                LocalLog.d(TAG, "添加任务好友");
                Intent intent = new Intent();
                intent.setClass(getActivity(), SelectFriendForTaskActivity.class);
                startActivityForResult(intent, SELECT_FRIENDS);
                break;
            case R.id.add_ico:
                LocalLog.d(TAG, "查看所有接入任务的好友");
                break;
            case R.id.confirm:
                LocalLog.d(TAG, "发布任务");
                if (checkReleaseParams()) {
                    taskReleaseParam
                            .setTask_days(Integer.parseInt(targetTaskDayNum.getText().toString()))
                            .setReward_amount(Float.parseFloat(targetTaskMoneyNum.getText().toString()))
                            .setTarget_step(Integer.parseInt(targetTaskStepNum.getText().toString()))
                            .setTo_userid(friends)
                            .setUserid(Presenter.getInstance(getContext()).getId());
                }
                Presenter.getInstance(getContext()).taskRelease(taskReleaseParam);
                break;
            default:
                break;
        }
    }

    private boolean checkReleaseParams() {
        if (targetTaskStepNum.getText() == null || targetTaskStepNum.getText().toString().equals("")) {
            Toast.makeText(getContext(), "请输入目标步数", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (targetTaskMoneyNum.getText() == null || targetTaskMoneyNum.getText().toString().equals("")) {
            Toast.makeText(getContext(), "请输入奖励金额", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (targetTaskDayNum.getText() == null || targetTaskDayNum.getText().toString().equals("")) {
            Toast.makeText(getContext(), "请输入任务天数", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (friends.equals("")) {
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter");
        switch (requestCode) {
            case SELECT_FRIENDS:
                LocalLog.d(TAG, "添加数据成功");
                if (data != null) {
                    FriendBundleData friendBundleData = (FriendBundleData) data.getParcelableExtra(getActivity().getPackageName());
                    dataBeans = friendBundleData.getFriendData();
                    recvRecycler.setAdapter(new LikeUserAdapter(getContext(), dataBeans));
                    recvRecycler.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(10));
                    String friends = "";
                    String sFriendFormat = getContext().getResources().getString(R.string.add_friend);
                    String sFriendNum = String.format(sFriendFormat, dataBeans.size());
                    addFriendDes.setText(sFriendNum);
                    for (int i = 0; i < dataBeans.size(); i++) {
                        if (i == dataBeans.size() - 1) {
                            friends += String.valueOf(dataBeans.get(i).getId());
                        } else {
                            friends += String.valueOf(dataBeans.get(i).getId()) + ",";
                        }

                    }
                    this.friends = friends;
                    LocalLog.d(TAG, friends);
                }
                break;
        }
    }
}
