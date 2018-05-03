package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorManagerActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.TargetPeopleActivity;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskSponsorParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskSponsorRespone;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.TaskSponsorInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SponsorSelectActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/4/19.
 */

public class ReleaseTaskSponsorFragment extends BaseFragment implements TaskSponsorInterface {
    private final static String TAG = ReleaseTaskSponsorFragment.class.getSimpleName();
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
    @Bind(R.id.target_people_detail)
    TextView targetPeopleDetail;
    @Bind(R.id.sponor_msg_des_detail)
    TextView sponorMsgDesDetail;

    private static String TARGET_PEOPLE_ACTION = "com.paobuqianjin.pbq.step.TARGET_ACTION";
    private static String SPONSOR_INFO_ACTION = "com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION";
    private final static int REQUEST_TARGET_PEOPLE = 0;
    public final static int REQUEST_SPONSOR_MSG = 1;
    public final static int RESULT_SPONSOR_MSG = 2;
    private TaskSponsorParam taskSponsorParam;
    private String sexStr;
    private String ageMinStr;
    private String ageMaxStr;
    private double longitudeStr;
    private double latitudeStr;
    private String distanceStr;
    private String city;
    private int businessId = -1;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick({R.id.people_target_span, R.id.sponor_msg_span, R.id.btn_confirm})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.people_target_span:
                LocalLog.d(TAG, "目标人群筛选");
                intent.setClass(getContext(), TargetPeopleActivity.class);
                intent.putExtra("sexStr", sexStr);
                intent.putExtra("minAgeStr", ageMinStr);
                intent.putExtra("maxAgeStr", ageMaxStr);
                intent.putExtra("longitudeStr", longitudeStr);
                intent.putExtra("latitudeStr", latitudeStr);
                intent.putExtra("targetSelectStr", distanceStr);
                intent.putExtra("city", city);
                intent.setAction(TARGET_PEOPLE_ACTION);
                startActivityForResult(intent, REQUEST_TARGET_PEOPLE);
                break;
            case R.id.sponor_msg_span:
                LocalLog.d(TAG, "商铺信息");
                intent.setClass(getContext(), SponsorManagerActivity.class);
                intent.setAction(SPONSOR_INFO_ACTION);
                startActivityForResult(intent, REQUEST_SPONSOR_MSG);
                break;
            case R.id.btn_confirm:
                LocalLog.d(TAG, "确定");
                //任务名称
                String targetTaskStepNumStr = targetTaskStepNum.getText().toString().trim();
                String targetTaskMoneyNumStr = targetTaskMoneyNum.getText().toString().trim();
                //任务天数
                String targetTaskDayNumStr = targetTaskDayNum.getText().toString().trim();
                //每日红包个数
                String packDayNumStr = packDayNum.getText().toString().trim();
                //目标步数
                String targetStepDayNumStr = targetStepDayNum.getText().toString().trim();
                //目标人群

                if (TextUtils.isEmpty(targetTaskStepNumStr)
                        || TextUtils.isEmpty(targetTaskMoneyNumStr)
                        || TextUtils.isEmpty(targetTaskDayNumStr)
                        || TextUtils.isEmpty(packDayNumStr)
                        || TextUtils.isEmpty(targetStepDayNumStr)
                        || 0 == latitudeStr
                        || 0 == longitudeStr
                        || businessId == -1
                        ) {
                    Toast.makeText(getActivity(), "请完善消息！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (taskSponsorParam == null) {
                    taskSponsorParam = new TaskSponsorParam();
                }
                taskSponsorParam.setUserid(Presenter.getInstance(getActivity()).getId() + "");
                taskSponsorParam.setNumber(Integer.valueOf(packDayNumStr));
                taskSponsorParam.setMoney(targetTaskMoneyNumStr);
                taskSponsorParam.setDay(targetTaskDayNumStr);
                taskSponsorParam.setRed_name(targetTaskStepNumStr);
                taskSponsorParam.setStep(targetStepDayNumStr);
                taskSponsorParam.setLatitude(((float) latitudeStr));
                taskSponsorParam.setLongitude(((float) longitudeStr));
                taskSponsorParam.setBusinessid(businessId + "");
                if (!TextUtils.isEmpty(sexStr))
                    taskSponsorParam.setSex(sexStr);
                if (!TextUtils.isEmpty(distanceStr))
                    taskSponsorParam.setDistance(distanceStr);
                if (!TextUtils.isEmpty(ageMaxStr))
                    taskSponsorParam.setAge_max(ageMaxStr);
                if (!TextUtils.isEmpty(ageMinStr))
                    taskSponsorParam.setAge_min(ageMinStr);
                if (!TextUtils.isEmpty(city))
                    taskSponsorParam.setCity(city);
                Presenter.getInstance(getContext()).postTaskSponsorRelease(taskSponsorParam);
                break;
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        ToastUtils.showShortToast(getActivity(), errorCode.getMessage());
    }

    @Override
    public void response(TaskSponsorRespone taskSponsorRespone) {
        ToastUtils.showShortToast(getActivity(), taskSponsorRespone.getMessage());
        if (taskSponsorRespone.getError() == 0) {
            getActivity().finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TARGET_PEOPLE && resultCode != 0) {
           /* intentResult.putExtra("sexStr", sexStr);
                intentResult.putExtra("minAgeStr", minAgeStr);
                intentResult.putExtra("maxAgeStr", maxAgeStr);
                intentResult.putExtra("targetSelectStr", targetSelectStr);
                if(!TextUtils.isEmpty(longitudeStr)) intentResult.putExtra("targetSelectStr", longitudeStr);
                if(!TextUtils.isEmpty(latitudeStr)) intentResult.putExtra("targetSelectStr", latitudeStr);*/
            sexStr = data.getStringExtra("sexStr");
            ageMinStr = data.getStringExtra("minAgeStr");
            ageMaxStr = data.getStringExtra("maxAgeStr");
            longitudeStr = data.getDoubleExtra("longitudeStr", 0);
            latitudeStr = data.getDoubleExtra("latitudeStr", 0);
            distanceStr = data.getStringExtra("targetSelectStr");
            city = data.getStringExtra("city");
            LocalLog.d(TAG, sexStr + " "
                    + ageMinStr + "\n"
                    + ageMaxStr + "\n"
                    + longitudeStr + "\n"
                    + latitudeStr + "\n"
                    + city + "\n"
                    + distanceStr + "\n"
            );
            targetPeopleDetail.setText("已筛选");
        } else {
            if (requestCode == REQUEST_SPONSOR_MSG &&
                    resultCode == ReleaseTaskSponsorFragment.RESULT_SPONSOR_MSG) {
                int businessId = data.getIntExtra("businessId", -1);
                if (businessId != -1) {
                    this.businessId = businessId;
                    sponorMsgDesDetail.setText("已完善");
                }
            }
        }
    }

}
