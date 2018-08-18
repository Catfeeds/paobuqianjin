package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorInfoActivity;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorManagerActivity;
import com.paobuqianjin.pbq.step.customview.ChooseOneItemWheelPopWindow;
import com.paobuqianjin.pbq.step.customview.LimitLengthFilter;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.SponsorDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetUserBusinessResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskSponsorFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddConsumptiveRedBagActivity extends BaseBarActivity {


    private static final String TAG = AddConsumptiveRedBagActivity.class.getSimpleName();
    private static final int REQUEST_CHANGE = 1;
    private static final int REQUEST_ADD = 2;
    @Bind(R.id.et_red_bag_name)
    EditText etRedBagName;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.et_limite_money)
    EditText etLimiteMoney;
    @Bind(R.id.et_day_num)
    EditText etDayNum;
    @Bind(R.id.et_num)
    EditText etNum;
    @Bind(R.id.switch_stand)
    ImageView switchStand;
    @Bind(R.id.tv_step)
    TextView tvStep;
    @Bind(R.id.stand_circle_pan)
    RelativeLayout standCirclePan;
    @Bind(R.id.sponor_msg_des_detail)
    TextView sponorMsgDesDetail;
    @Bind(R.id.sponor_msg_span)
    RelativeLayout sponorMsgSpan;

    private final int DEVALUE_STEP = 10000;//默认步数
    @Bind(R.id.linear_open_vip)
    LinearLayout linearOpenVip;

    private ArrayList<String> targetStepArr = new ArrayList<>();
    private ChooseOneItemWheelPopWindow wheelPopWindow;
    private boolean hasBusiness;
    //    private GetUserBusinessResponse.DataBeanX.DataBean shopBean;
    private int businessId = -1;
    private boolean isVip;
    private LimitLengthFilter limitLengthFilter;
    SponsorDialog sponsorApplyDialog, sponsorDialogSuccess;

    @Override
    protected String title() {
        return getString(R.string.add_consumptive_red_bag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consumptive_red_bag);
        ButterKnife.bind(this);

        limitLengthFilter = new LimitLengthFilter();
        etRedBagName.setFilters(new InputFilter[]{limitLengthFilter});
        tvStep.setText(DEVALUE_STEP + "");
        initData();
    }

    private void initData() {
        //目标步数
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlTarget, null, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
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
        });

        //获取默认商家
        getDefaultBusiness();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取金牌会员状态
        getVipStatus();
    }

    private void getVipStatus() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(this), null, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    isVip = userInfoResponse.getData().getGvip() == 1;
                    linearOpenVip.setVisibility(isVip ? View.GONE : View.VISIBLE);
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

        });
    }

    private void applyGolden() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlTryGvip, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                isVip = true;
                if (sponsorDialogSuccess == null) {
                    sponsorDialogSuccess = new SponsorDialog(AddConsumptiveRedBagActivity.this);
                    sponsorDialogSuccess.setTitle("申请成功");
                    sponsorDialogSuccess.setMessage(getString(R.string.golden_dialog_message_b));

                    sponsorDialogSuccess.setNoOnclickListener("取消", new SponsorDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            sponsorDialogSuccess.dismiss();
                        }
                    });

                    sponsorDialogSuccess.setYesOnclickListener("去发消费红包", new SponsorDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            sponsorDialogSuccess.dismiss();
                            LocalLog.d(TAG, "去发消费红包");
                        }
                    });
                }
                if (!isFinishing() && !sponsorApplyDialog.isShowing())
                    sponsorDialogSuccess.show();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    private void goldenFreeApply() {
        if (sponsorApplyDialog == null) {
            sponsorApplyDialog = new SponsorDialog(this);
            sponsorApplyDialog.setTitle("申请免费试用");
            sponsorApplyDialog.setMessage(getString(R.string.golden_dialog_message_a));
            sponsorApplyDialog.setNoOnclickListener("取消", new SponsorDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    sponsorApplyDialog.dismiss();
                }
            });

            sponsorApplyDialog.setYesOnclickListener("试用", new SponsorDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    sponsorApplyDialog.dismiss();
                    LocalLog.d(TAG, "申请试用");
                    applyGolden();

                }
            });
        }
        if (!sponsorApplyDialog.isShowing())
            sponsorApplyDialog.show();
    }

    @OnClick({R.id.stand_circle_pan, R.id.sponor_msg_span, R.id.btn_confirm, R.id.linear_open_vip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stand_circle_pan:
                if (wheelPopWindow == null && targetStepArr.size() > 0) {
                    wheelPopWindow = new ChooseOneItemWheelPopWindow(this, targetStepArr);
                    wheelPopWindow.setItemConfirmListener(new ChooseOneItemWheelPopWindow.OnWheelItemConfirmListener() {
                        @Override
                        public void onItemSelectLis(String result) {
                            tvStep.setText(result);
                        }
                    });

                }
                if (wheelPopWindow.isShowing()) {
                    wheelPopWindow.cancel();
                    return;
                }
                wheelPopWindow.setCurrentSelectValue(tvStep.getText().toString());
                wheelPopWindow.show();
                break;
            case R.id.sponor_msg_span:
                Intent intent = new Intent();
                if (hasBusiness) {
                    LocalLog.d(TAG, "商铺信息");
                    intent.setClass(this, SponsorManagerActivity.class);
                    intent.putExtra("businessId", businessId);
//                    intent.setAction(SPONSOR_INFO_ACTION);
                    startActivityForResult(intent, REQUEST_CHANGE);
                } else {
                    LocalLog.d(TAG, "添加商铺");
                    intent.setAction("com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION");
                    intent.setClass(this, SponsorInfoActivity.class);
                    startActivityForResult(intent, REQUEST_ADD);
                }
                break;
            case R.id.btn_confirm:
                if (!isVip) {
                    goldenFreeApply();
                    return;
                }
                if (fillter()) {
                    Map<String, String> params = new HashMap<>();
                    params.put("busid", businessId + "");
                    params.put("vname", etRedBagName.getText().toString());
                    params.put("amount", etNum.getText().toString());
                    params.put("condition", etLimiteMoney.getText().toString());
                    params.put("money", etMoney.getText().toString());
                    params.put("day", etDayNum.getText().toString());
                    params.put("step", tvStep.getText().toString());
                    //params.put("distance", tvStep.getText().toString());
                    Presenter.getInstance(this).postPaoBuSimple(NetApi.getMySendVoucher, params, new PaoTipsCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            ToastUtils.showShortToast(AddConsumptiveRedBagActivity.this, "添加成功");
                            setResult(4);
                            finish();
                        }
                    });
                }
                break;
            case R.id.linear_open_vip:
                startActivity(GoldenSponsoractivity.class, null);
                break;
        }
    }

    private boolean fillter() {
        String redBagNmae = etRedBagName.getText().toString();

        if (TextUtils.isEmpty(redBagNmae.trim())) {
            PaoToastUtils.showShortToast(this, "请输入消费红包名称");
            return false;
        }
        if (limitLengthFilter.calculateLength(redBagNmae) > 32) {
            PaoToastUtils.showShortToast(this, "消费红包名称不能大于32个字符");
            return false;
        }
        if (TextUtils.isEmpty(etMoney.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入消费红包金额");
            return false;
        }
        if (TextUtils.isEmpty(etLimiteMoney.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入使用消费红包的最低金额");
            return false;
        }
        if (TextUtils.isEmpty(etDayNum.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入有效天数");
            return false;
        }
        if (TextUtils.isEmpty(etNum.getText().toString())) {
            PaoToastUtils.showShortToast(this, "请输入使用消费红包的数量");
            return false;
        }
        if (businessId < 1) {
            PaoToastUtils.showShortToast(this, "请选择您的商铺");
            return false;
        }
        if (!isVip) {
            final NormalDialog dialog = new NormalDialog(this);
            dialog.setMessage("创建消费红包失败,账户还未开通金牌商家会员,是否要开通商家金牌会员?");
            dialog.setYesOnclickListener("去申请", new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    startActivity(GoldenSponsoractivity.class, null);
                    dialog.dismiss();
                }
            });
            dialog.setNoOnclickListener("取消申请", new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    dialog.dismiss();
                }
            });
            dialog.show();
            return false;
        }
        return true;
    }

    public void getDefaultBusiness() {
        //商铺信息
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("pagesize", "1");
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlGetUserBusiness, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    GetUserBusinessResponse businessResponse = new Gson().fromJson(s, GetUserBusinessResponse.class);
                    if (businessResponse.getError() == 0) {
                        hasBusiness = true;
                        if (businessResponse.getData().getData().size() > 0) {
                            GetUserBusinessResponse.DataBeanX.DataBean shopBean = businessResponse.getData().getData().get(0);
                            if (shopBean.getDefaultX() == 1) {
                                if (sponorMsgDesDetail != null) {
                                    businessId = shopBean.getBusinessid();
                                    sponorMsgDesDetail.setText(shopBean.getName());
                                }
                            }
                        }
                    }
                } catch (JsonSyntaxException j) {
                    j.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHANGE) {
            LocalLog.d(TAG, "resultCode == " + resultCode);
            if (resultCode == ReleaseTaskSponsorFragment.RESULT_SPONSOR_MSG) {
                businessId = data.getIntExtra("businessId", -1);
                if (businessId != -1) {
                    sponorMsgDesDetail.setText(data.getStringExtra("name"));
                }
            } else {
                getDefaultBusiness();
            }
        } else if (requestCode == REQUEST_ADD) {
            if (resultCode > 0) {
                int businessId = data.getIntExtra("businessId", -1);
                if (businessId != -1) {
                    hasBusiness = true;
                    this.businessId = businessId;
                    sponorMsgDesDetail.setText(data.getStringExtra("name"));
                }
            }
        }
    }
}
