package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.paobuqianjin.pbq.step.customview.ChooseOneItemWheelPopWindow;
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskReleaseParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskReleaseResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.TaskReleaseInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.activity.SelectFriendActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/26.
 */
/*
@className :ReleaseTaskPersonFragment
*@date 2018/1/26
*@author
*@description 发布任务界面
*/
public class ReleaseTaskPersonFragment extends BaseFragment {
    private final static String TAG = ReleaseTaskPersonFragment.class.getSimpleName();

    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static String TASK_NO = "taskno";
    private final static String CIRCLE_RECHARGE = "pay";
    private static final int SELECT_FRIENDS = 0;
    ArrayList<UserFriendResponse.DataBeanX.DataBean> dataBeans = null;
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
    @Bind(R.id.btn_confirm)
    Button confirm;
    @Bind(R.id.tv_calculate)
    TextView tvCalculate;
    private TaskReleaseParam taskReleaseParam = new TaskReleaseParam();
    private String friends;
    LinearLayoutManager layoutManager;
    FriendBundleData friendBundleData = null;
    private final static String ACTION_TASK = "com.paobuqianjin.pbq.step.ACTION_TASK";
    private float totalMoney;
    private ChooseOneItemWheelPopWindow wheelPopWindow;
    private final int DEVALUE_STEP = 10000;//默认步数
    private String[] targetStepArr = {"5000","6000","7000","8000","9000","10000"};

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
        recvRecycler.setNestedScrollingEnabled(false);
        recvRecycler.setHasFixedSize(true);
        recvRecycler.setLayoutManager(layoutManager);

        addFriendDes = (TextView) viewRoot.findViewById(R.id.add_friend_des);
        tvCalculate = (TextView) viewRoot.findViewById(R.id.tv_calculate);
        recvRecycler.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(10));
        targetTaskDayNum.setSelection(targetTaskDayNum.getText().toString().length());
        targetTaskMoneyNum.setSelection(targetTaskMoneyNum.getText().toString().length());
        targetTaskStepNum.setSelection(targetTaskStepNum.getText().toString().length());
        targetTaskMoneyNum.addTextChangedListener(textWatcher);
        targetTaskDayNum.addTextChangedListener(textWatcher);
        targetTaskStepNum.setText(DEVALUE_STEP + "");
        calculateResultMoney();
    }

    private TaskReleaseInterface taskReleaseInterface = new TaskReleaseInterface() {
        @Override
        public void response(TaskReleaseResponse taskReleaseResponse) {
            LocalLog.d(TAG, "TaskReleaseResponse() enter");
            if (taskReleaseResponse.getError() == 0) {
                LocalLog.d(TAG, "任务生成，去充值");
                //getActivity().finish();
                Bundle bundle = new Bundle();
                bundle.putString(PAY_FOR_STYLE, "task");
                bundle.putString(TASK_NO, taskReleaseResponse.getData().getTask_no());
                bundle.putString(CIRCLE_RECHARGE, String.format("%.2f",totalMoney));
                startActivity(PaoBuPayActivity.class, bundle, true, PAY_ACTION);
            } else if (taskReleaseResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            } else {
                Toast.makeText(getContext(), taskReleaseResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void response(ErrorCode errorCode) {
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }
    };

    @OnClick({R.id.target_task_span, R.id.target_task_step_num, R.id.add_task_friend, R.id.add_ico, R.id.btn_confirm})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.target_task_span:
            case R.id.target_task_step_num:
                LocalLog.d(TAG, "设置任务目标步数");
                if (wheelPopWindow == null) {
                    wheelPopWindow = new ChooseOneItemWheelPopWindow(getActivity(), Arrays.asList(targetStepArr));
                    wheelPopWindow.setItemConfirmListener(new ChooseOneItemWheelPopWindow.OnWheelItemConfirmListener() {
                        @Override
                        public void onItemSelectLis(String result) {
                            targetTaskStepNum.setText(result);
                        }
                    });
                }
                if (wheelPopWindow.isShowing()) {
                    wheelPopWindow.cancel();
                    return;
                }
                wheelPopWindow.setCurrentSelectValue(targetTaskStepNum.getText().toString());
                wheelPopWindow.show();
                break;
            case R.id.add_task_friend:
                LocalLog.d(TAG, "添加任务好友");
                intent.putExtra(getActivity().getPackageName(), friendBundleData);
                intent.setAction(ACTION_TASK);
                intent.setClass(getActivity(), SelectFriendActivity.class);
                startActivityForResult(intent, SELECT_FRIENDS);
                break;
            case R.id.add_ico:
                LocalLog.d(TAG, "查看所有接入任务的好友");
                intent.putExtra(getActivity().getPackageName(), friendBundleData);
                intent.setClass(getActivity(), SelectFriendActivity.class);
                intent.setAction(ACTION_TASK);
                startActivityForResult(intent, SELECT_FRIENDS);
                break;
            case R.id.btn_confirm:
                LocalLog.d(TAG, "发布任务");
                if (checkReleaseParams()) {
                    taskReleaseParam
                            .setTask_days(Integer.parseInt(targetTaskDayNum.getText().toString()))
                            .setReward_amount(Float.parseFloat(targetTaskMoneyNum.getText().toString()))
                            .setTarget_step(Integer.parseInt(targetTaskStepNum.getText().toString()))
                            .setTo_userid(friends)
                            .setUserid(Presenter.getInstance(getContext()).getId());
                    Presenter.getInstance(getContext()).taskRelease(taskReleaseParam);
                }
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

        if (targetTaskDayNum.getText().toString().equals("0")) {
            Toast.makeText(getContext(), "任务天数不能为0", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(friends)) {
            Toast.makeText(getContext(), "请选择好友", Toast.LENGTH_SHORT).show();
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
                    friendBundleData = (FriendBundleData) data.getParcelableExtra(getActivity().getPackageName());
                    if (friendBundleData == null) {
                        return;
                    }
                    dataBeans = friendBundleData.getFriendData();
                    recvRecycler.setAdapter(new LikeUserAdapter(getContext(), dataBeans));
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
                    calculateResultMoney();
                }
                break;
        }
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            calculateResultMoney();
        }
    };

    /**
     * 计算红包总额
     */
    private void calculateResultMoney() {
        int dayNum = 0;
        float dailyMoney = 0f;
        int friendsNum = 0;
        if (targetTaskMoneyNum.getText() == null || targetTaskMoneyNum.getText().toString().equals("")) {
//            Toast.makeText(getContext(), "请输入奖励金额", Toast.LENGTH_SHORT).show();
            dailyMoney = 0f;
        }else{
            dailyMoney = Float.parseFloat(targetTaskMoneyNum.getText().toString());
        }

        if (targetTaskDayNum.getText() == null || targetTaskDayNum.getText().toString().equals("")) {
//            Toast.makeText(getContext(), "请输入任务天数", Toast.LENGTH_SHORT).show();
            dayNum = 0;
        }else{
            dayNum = Integer.parseInt(targetTaskDayNum.getText().toString());
        }

        if (TextUtils.isEmpty(friends) && dataBeans==null || dataBeans.size()==0) {
//            Toast.makeText(getContext(), "请选择好友", Toast.LENGTH_SHORT).show();
            friendsNum = 0;
        }else{
            friendsNum = dataBeans.size();
        }

        totalMoney = dailyMoney * dayNum * friendsNum;
        String dailyMoneyStr = String.format("%.2f", dailyMoney);
        String dayNumStr = dayNum+"";
        String personNumStr = friendsNum+"";
        String allMoneyStr = String.format("%.2f", totalMoney);
        tvCalculate.setText(getString(R.string.calculate_person_send_rbag,allMoneyStr,dailyMoneyStr,dayNumStr,personNumStr));
    }
}
