package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorInfoActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorManagerActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.TargetPeopleActivity;
import com.paobuqianjin.pbq.step.customview.ChooseOneItemWheelPopWindow;
import com.paobuqianjin.pbq.step.customview.LimitLengthFilter;
import com.paobuqianjin.pbq.step.customview.LooperTextView;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.GetUserBusinessParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.TaskSponsorParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetUserBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorLabelResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskSponsorRespone;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ConfirmResult;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.presenter.im.TaskSponsorInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on
 * 2018/4/19.
 */

public class ReleaseTaskSponsorFragment extends BaseFragment implements TaskSponsorInterface, InnerCallBack {
    private final static String TAG = ReleaseTaskSponsorFragment.class.getSimpleName();
    private final static int REQUEST_TARGET_PEOPLE = 0;
    public final static int REQUEST_SPONSOR_MSG = 1;
    public final static int RESULT_SPONSOR_MSG = 2;
    private static final int REQUEST_SPONSOR_INFO = 3;
    public static final int RESULT_NO_SPONSOR = 9;
    public static final int RESULT_DELETE_SPONSOR = 8;
    public static final int REQUEST_PAY_SPONSOR_PKG = 9;
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
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String PAY_ACTION = "android.intent.action.PAY";
    Button btnConfirm;
    @Bind(R.id.top_text)
    LooperTextView topText;
    private boolean isFirstLocal = true;
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private TaskSponsorParam taskSponsorParam;
    private String sexStr;
    private String ageMinStr;
    private String ageMaxStr;
    private double longitudeStr;
    private double latitudeStr;
    private String distanceStr;
    private String city;
    private String cityCode;
    private String address;
    private boolean hasBusiness;
    private int businessId = -1;
    private NormalDialog dialog;
    private LimitLengthFilter filter;

    private ChooseOneItemWheelPopWindow wheelPopWindow;
    private final int DEVALUE_STEP = 10000;//默认步数
    /* private String[] targetStepArr = {"3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000"};*/
    private ArrayList<String> targetStepArr = new ArrayList<>();
    ConfirmResult confirmResult;

    @Override
    protected int getLayoutResId() {
        return R.layout.release_task_sponor_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        getDefaultBusiness();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCATION_ACTION);
        getContext().registerReceiver(stepLocationReciver, intentFilter);
        return rootView;
    }

    private void getDefaultBusiness() {
        LocalLog.d(TAG, "获取默认的店铺!");
        GetUserBusinessParam param = new GetUserBusinessParam();
        param.setUserid(Presenter.getInstance(getContext()).getId()).setPage(1);
        Presenter.getInstance(getContext()).getUserBusiness(param, this);
    }


    @Override
    protected void initView(View viewRoot) {
        targetStepDayNum = (EditText) viewRoot.findViewById(R.id.target_step_day_num);
        targetStepDayNum.setText(DEVALUE_STEP + "");
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlTarget, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                LocalLog.d(TAG, "");
                try {
                    CircleTargetResponse circleTargetResponse = new Gson().fromJson(s, CircleTargetResponse.class);
                    if (circleTargetResponse.getData() != null && circleTargetResponse.getData().size() > 0) {
                        for (int i = 0; i < circleTargetResponse.getData().size(); i++) {
                            targetStepArr.add(String.valueOf(circleTargetResponse.getData().get(i).getTarget()));
                        }
                    }
                } catch (JsonSyntaxException j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });

        topText = (LooperTextView) viewRoot.findViewById(R.id.top_text);
        getTopText();
    }

    private void getTopText() {
        String urlTipList = NetApi.urlRedNewTipList + "page=1&pagesize=10";
        Presenter.getInstance(getContext()).getPaoBuSimple(urlTipList, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                try {
                    SponsorLabelResponse sponsorLabelResponse = new Gson().fromJson(s, SponsorLabelResponse.class);
                    if (sponsorLabelResponse.getData() != null) {
                        int size = sponsorLabelResponse.getData().size();
                        if (size > 0) {
                            List<String> list = new ArrayList<>();
                            for (int i = 0; i < size; i++) {
                                String buName = sponsorLabelResponse.getData().get(i).getBusinessname();
                                if (!TextUtils.isEmpty(buName)) {
                                    if (buName.length() > 2) {
                                        buName = buName.substring(0, 2) + "***";
                                    } else {
                                        buName += "***";
                                    }
                                } else {
                                    buName = "***";
                                }
                                String listItem = buName + "发布了" + sponsorLabelResponse.getData().get(i).getMoney() + "元红包任务";
                                list.add(listItem);
                            }
                            topText.setTipList(list);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    public void confirm(ConfirmResult confirmResult) {
        if (isAdded()) {
            this.confirmResult = confirmResult;
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
            if (TextUtils.isEmpty(targetTaskStepNumStr.trim()) || filter.calculateLength(targetTaskStepNumStr) < 4
                    || filter.calculateLength(targetTaskStepNumStr) > 32) {
                if (dialog == null) {
                    dialog = new NormalDialog(getContext());
                    dialog.setMessage("请输入2-16位任务名称");
                    dialog.setSingleBtn(true);
                    dialog.setYesOnclickListener("确定", new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            dialog.dismiss();
                        }
                    });
                }
                dialog.show();
                return;
            }
            try {
/*                    if (TextUtils.isEmpty(targetTaskMoneyNumStr.trim()) || Integer.parseInt(targetTaskMoneyNumStr) < 10) {
                        PaoToastUtils.showShortToast(getActivity(), "商家每次发红包金额不低于10元");
                        return;
                    }*/
                if (TextUtils.isEmpty(targetTaskMoneyNumStr.trim())) {
                    PaoToastUtils.showLongToast(getActivity(), "请输入红包金额");
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(targetTaskDayNumStr.trim())) {
                Toast.makeText(getActivity(), "请输入任务天数", Toast.LENGTH_SHORT).show();
                return;
            }
            if (targetTaskDayNumStr.trim().equals("0")) {
                Toast.makeText(getActivity(), "任务天数不能为0", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(packDayNumStr.trim())) {
                Toast.makeText(getActivity(), "请输入每日红包个数", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(targetStepDayNumStr.trim())) {
                Toast.makeText(getActivity(), "请输入目标步数", Toast.LENGTH_SHORT).show();
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
            if (latitudeStr != 0d)
                taskSponsorParam.setLatitude(((float) latitudeStr));
            if (longitudeStr != 0d)
                taskSponsorParam.setLongitude(((float) longitudeStr));
            if (businessId != -1)
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
            if (!TextUtils.isEmpty(cityCode))
                taskSponsorParam.setCity_code(cityCode);
            if (!TextUtils.isEmpty(address))
                taskSponsorParam.setTrading_area(address);
            confirmResult.result(false);
            Presenter.getInstance(getContext()).postTaskSponsorRelease(taskSponsorParam);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filter = new LimitLengthFilter();
        targetTaskStepNum.setFilters(new InputFilter[]{filter});
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
        getActivity().unregisterReceiver(stepLocationReciver);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick({R.id.day_step_target_span, R.id.target_step_day_num, R.id.people_target_span, R.id.sponor_msg_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.day_step_target_span:
            case R.id.target_step_day_num:
                LocalLog.d(TAG, "商家设置任务目标步数");
                if (wheelPopWindow == null && targetStepArr.size() > 0) {
                    wheelPopWindow = new ChooseOneItemWheelPopWindow(getActivity(), targetStepArr);
                    wheelPopWindow.setItemConfirmListener(new ChooseOneItemWheelPopWindow.OnWheelItemConfirmListener() {
                        @Override
                        public void onItemSelectLis(String result) {
                            targetStepDayNum.setText(result);
                        }
                    });

                }
                if (wheelPopWindow.isShowing()) {
                    wheelPopWindow.cancel();
                    return;
                }
                wheelPopWindow.setCurrentSelectValue(targetStepDayNum.getText().toString());
                wheelPopWindow.show();
                break;

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
                intent.putExtra("cityCode", cityCode);
                intent.putExtra("address", address);
                intent.setAction(TARGET_PEOPLE_ACTION);
                startActivityForResult(intent, REQUEST_TARGET_PEOPLE);
                break;
            case R.id.sponor_msg_span:
                if (hasBusiness) {
                    LocalLog.d(TAG, "商铺信息");
                    intent.setClass(getContext(), SponsorManagerActivity.class);
                    intent.putExtra("businessId", businessId);
                    intent.setAction(SPONSOR_INFO_ACTION);
                    startActivityForResult(intent, REQUEST_SPONSOR_MSG);
                } else {
                    LocalLog.d(TAG, "添加商铺");
                    intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                    intent.setClass(getContext(), SponsorInfoActivity.class);
                    startActivityForResult(intent, REQUEST_SPONSOR_INFO);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        PaoToastUtils.showShortToast(getActivity(), errorCode.getMessage());
        if (confirmResult != null) {
            confirmResult.result(true);
        }
    }

    @Override
    public void response(TaskSponsorRespone taskSponsorRespone) {
        if (!isAdded()) {
            return;
        }
        if (taskSponsorRespone.getError() == 0) {
            Bundle bundle = new Bundle();
            bundle.putString(CIRCLE_ID, taskSponsorRespone.getData().getRed_id());
            bundle.putString(CIRCLE_NAME, targetTaskStepNum.getText().toString());
            LocalLog.d(TAG, "创建成功,跳转支付");
            bundle.putString(PAY_FOR_STYLE, "redpacket");
            bundle.putString(CIRCLE_RECHARGE, targetTaskMoneyNum.getText().toString());
            Intent intent = new Intent();
            intent.setClass(getContext(), PaoBuPayActivity.class);
            intent.putExtra(getActivity().getPackageName(), bundle);
            intent.setAction(PAY_ACTION);
            startActivityForResult(intent, REQUEST_PAY_SPONSOR_PKG);
        } else {
            PaoToastUtils.showShortToast(getContext(), taskSponsorRespone.getMessage());
            if (confirmResult != null) {
                confirmResult.result(true);
            }
        }

    }

    @Override
    public void responseLocation(String city, double latitude, double longitude) {
        if (!isAdded()) {
            return;
        }
        if (isFirstLocal) {
            latitudeStr = latitude;
            longitudeStr = longitude;
            this.city = city;
            isFirstLocal = false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TARGET_PEOPLE && resultCode != 0) {
            sexStr = data.getStringExtra("sexStr");
            ageMinStr = data.getStringExtra("minAgeStr");
            ageMaxStr = data.getStringExtra("maxAgeStr");
            longitudeStr = data.getDoubleExtra("longitudeStr", 0);
            latitudeStr = data.getDoubleExtra("latitudeStr", 0);
            distanceStr = data.getStringExtra("targetSelectStr");
            city = data.getStringExtra("city");
            cityCode = data.getStringExtra("cityCode");
            address = data.getStringExtra("address");

            LocalLog.d(TAG, sexStr + " " + ageMinStr + "\n" + ageMaxStr + "\n" +
                    cityCode + "\n" + address + "\n" + city + "\n"
                    + longitudeStr + "\n" + latitudeStr + "\n" + distanceStr + "\n"
            );
            targetPeopleDetail.setText("已筛选");
        } else if (requestCode == REQUEST_SPONSOR_MSG) {
            LocalLog.d(TAG, "resultCode == " + resultCode);
            if (resultCode == ReleaseTaskSponsorFragment.RESULT_SPONSOR_MSG) {
                int businessId = data.getIntExtra("businessId", -1);
                if (businessId != -1) {
                    this.businessId = businessId;
                    sponorMsgDesDetail.setText(data.getStringExtra("name"));
                }
            } else if (resultCode == ReleaseTaskSponsorFragment.RESULT_NO_SPONSOR) {
                hasBusiness = false;
                businessId = -1;
                sponorMsgDesDetail.setText("");
            } else if (resultCode == ReleaseTaskSponsorFragment.RESULT_DELETE_SPONSOR) {
                businessId = -1;
                sponorMsgDesDetail.setText("");
            } else {
                getDefaultBusiness();
            }
        } else if (requestCode == REQUEST_SPONSOR_INFO && resultCode > 0) {
            int businessId = data.getIntExtra("businessId", -1);
            if (businessId != -1) {
                hasBusiness = true;
                this.businessId = businessId;
                sponorMsgDesDetail.setText(data.getStringExtra("name"));
            }
        } else if (requestCode == REQUEST_PAY_SPONSOR_PKG) {
            if (resultCode == RESULT_OK) {
                LocalLog.d(TAG, "支付完成");
                getActivity().finish();
            } else {
                if (confirmResult != null) {
                    confirmResult.result(true);
                }
            }
        }
    }

    @Override
    public void innerCallBack(Object object) {
        if (object instanceof GetUserBusinessResponse) {
            if (((GetUserBusinessResponse) object).getError() == 0) {
                hasBusiness = true;
                if (((GetUserBusinessResponse) object).getData().getData().size() > 0) {
                    GetUserBusinessResponse.DataBeanX.DataBean bean = ((GetUserBusinessResponse) object).getData().getData().get(0);
                    if (bean.getDefaultX() == 1) {
                        businessId = bean.getBusinessid();
                        if (isAdded() && sponorMsgDesDetail != null) {
                            sponorMsgDesDetail.setText(bean.getName());
                        }
                    }
                }
            }
        }
    }
}
