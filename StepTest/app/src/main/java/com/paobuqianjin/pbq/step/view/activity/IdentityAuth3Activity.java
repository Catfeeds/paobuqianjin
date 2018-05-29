package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdentityAuth3Activity extends BaseBarActivity {

    private static final long ALLTIME = 60 * 1000;
    @Bind(R.id.tv_target_phone)
    TextView tvTargetPhone;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_get_vcode)
    TextView tvGetVcode;
    @Bind(R.id.btn_next)
    Button btnNext;
    private CountDownTimer timer;
    private String personId;
    private String personName;
    private String phoneNum;
    private String cardNum;

    public static Class<?> targetActivity = null;

    @Override
    protected String title() {
        return "验证手机号";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_auth3);
        ButterKnife.bind(this);

//        bundle.putString("personId", personId);
//        bundle.putString("personName", personName);
//        bundle.putString("phoneNum", phoneNum);
        Bundle bundle = getBundle();
        Map<String, String> dataMap = (Map<String, String>) bundle.getSerializable("dataMap");

        if (bundle != null) {
            personId = bundle.getString("personId");
            personName = bundle.getString("personName");
            phoneNum = bundle.getString("phoneNum");
            cardNum = bundle.getString("cardNum");
        }
        getVCode(phoneNum);
    }

    private void getVCode(String phoneNum) {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlSendMsg + phoneNum, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showShortToast(IdentityAuth3Activity.this,"获取验证码成功");
                startCutdownTime();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if(errorBean!=null) PaoToastUtils.showShortToast(IdentityAuth3Activity.this, errorBean.getMessage());
                tvGetVcode.setEnabled(true);
            }
        });
    }

    private long currentSeconds;
    private Runnable dountDownRunnable = new Runnable() {
        @Override
        public void run() {
            tvGetVcode.setEnabled(false);
            tvGetVcode.setText(currentSeconds / 1000 + "s");
        }
    };

    private void startCutdownTime() {
        currentSeconds = ALLTIME;
        if (timer == null) {
            timer = new CountDownTimer(ALLTIME, 1 * 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    currentSeconds = millisUntilFinished;
                    tvGetVcode.post(dountDownRunnable);
                }

                @Override
                public void onFinish() {
                    tvGetVcode.post(new Runnable() {
                        @Override
                        public void run() {
                            tvGetVcode.setEnabled(true);
                            tvGetVcode.setText("重新获取");
                        }
                    });
                }
            };
        }
        timer.cancel();
        timer.start();
    }


    @OnClick({R.id.tv_get_vcode, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_vcode:
                tvGetVcode.setEnabled(false);
                getVCode(phoneNum);
                break;
            case R.id.btn_next:
                if (isFinishiAllInfo()) {
                    btnNext.setEnabled(false);
                    etCode.setEnabled(false);
                    Map<String, String> params = new HashMap<>();
                    params.put("phone", phoneNum);
                    params.put("code", etCode.getText().toString());
                    params.put("account", personName);
                    params.put("cardno", cardNum.replaceAll(" ", ""));
                    params.put("idcard", personId);
//                    params.put("couplet", personId);//支行地址
                    Presenter.getInstance(this).postPaoBuSimple(NetApi.REAL_AUTH_ALL_INFO, params, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            PaoToastUtils.showShortToast(IdentityAuth3Activity.this, "验证成功");
//                            Intent intent = new Intent(IdentityAuth3Activity.this, targetActivity);
//                            startActivity(intent);
                            setResult(200);
                            finish();
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                            if (errorBean != null) {
                                PaoToastUtils.showShortToast(IdentityAuth3Activity.this, errorBean.getMessage());
                            }
                            btnNext.setEnabled(true);
                            etCode.setEnabled(true);
                        }
                    });
                }

                break;
        }
    }

    private boolean isFinishiAllInfo() {
        if (TextUtils.isEmpty(etCode.getText().toString())) {
            PaoToastUtils.showShortToast(IdentityAuth3Activity.this, "请输入验证码");
            return false;
        }else{
            return true;
        }

    }

}
