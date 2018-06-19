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
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdentityAuth3Activity extends BaseBarActivity {
    private final static String ACTION_FIRST = "com.paobuqianjin.pbq.step.ACTION_FIRSTSET";
    private final static String ACTION_FORGET_REST = "com.paobuqianjin.pbq.setp.ACTION_FORGET_RESET";
    private final static String ACTION_BIND_NEW_CARD = "com.paobuqianjin.pbq.step.ACTION_ADD_CARD";
    private final static String ACTION_FIRST_CARD = "com.paobuqianjin.pbq.step.ACTION_FIRST_CARD";
    private final static String ACTION_RESET_BANK = "com.paobuqianjin.pbq.step.ACTION_RESET_BANK";
    private static final long ALLTIME = 60 * 1000;
    static final java.lang.String KEY_PERSON_ID = "personId";
    static final java.lang.String KEY_PERSON_NAME = "personName";
    static final java.lang.String KEY_PHONE_NUM = "phoneNum";
    static final java.lang.String KEY_CARD_NUM = "cardNum";
    public static final int RES_SUC = 200;
    private final String TAG = getClass().getSimpleName();
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
    private String action = "";
    private String cardId = "";
    public static Class<?> targetActivity = null;

    private boolean isAddCard = false;

    @Override
    protected String title() {
        return "验证手机号";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_auth3);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            action = intent.getAction();
        }
        Bundle bundle = getBundle();
        if (bundle != null) {
            personId = bundle.getString(KEY_PERSON_ID);
            isAddCard = TextUtils.isEmpty(personId) ? true : false;
            personName = bundle.getString(KEY_PERSON_NAME);
            phoneNum = bundle.getString(KEY_PHONE_NUM);
            cardNum = bundle.getString(KEY_CARD_NUM);
            cardId = getBundle().getString("cardid");
        } else {
            LocalLog.e(TAG, getString(R.string.error_param));
            finish();
        }

        tvTargetPhone.setText(getString(R.string.title_code_already_send) + phoneNum.substring(0, 3) + "****" + phoneNum.substring(7));
        getVCode(phoneNum);
    }

    private void getVCode(String phoneNum) {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlSendMsg + phoneNum, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showShortToast(IdentityAuth3Activity.this, "获取验证码成功");
                startCutdownTime();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null)
                    PaoToastUtils.showShortToast(IdentityAuth3Activity.this, errorBean.getMessage());
                tvGetVcode.setEnabled(true);
                tvGetVcode.setText("重新获取");
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
                    if (ACTION_BIND_NEW_CARD.equals(action) || ACTION_FIRST.equals(action)) {
                        etCode.setEnabled(false);
                        Map<String, String> params = new HashMap<>();
                        params.put("phone", phoneNum);
                        params.put("code", etCode.getText().toString());
                        params.put("cardno", cardNum.replaceAll(" ", ""));
                        if (!isAddCard) {
                            params.put("idcard", personId);
                            params.put("account", personName);
                        }
//                    params.put("couplet", personId);//支行地址
                        Presenter.getInstance(this).postPaoBuSimple(NetApi.REAL_AUTH_ALL_INFO, params, new PaoCallBack() {
                            @Override
                            protected void onSuc(String s) {
                                PaoToastUtils.showShortToast(IdentityAuth3Activity.this, isAddCard ? "添加银行卡成功" : "验证成功");
                                if (!isAddCard) {
                                    LocalLog.d(TAG, "设置密码");
                                    Intent intent = new Intent();
                                    intent.setAction(ACTION_FIRST_CARD);
                                    intent.setClass(getApplicationContext(), PayPassWordActivity.class);
                                    startActivity(intent);
                                }
                                setResult(RES_SUC);
                                finish();
                            }

                            @Override
                            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                                if (errorBean != null) {
                                    PaoToastUtils.showShortToast(IdentityAuth3Activity.this, errorBean.getMessage());
                                }
                                etCode.setEnabled(true);
                            }
                        });
                    } else {
                        if (ACTION_FORGET_REST.equals(action)) {
                            LocalLog.d(TAG, "通过银行卡找回密码");
                            etCode.setEnabled(false);
                            Map<String, String> params = new HashMap<>();
                            params.put("phone", phoneNum);
                            params.put("code", etCode.getText().toString());
                            params.put("cardno", cardNum.replaceAll(" ", ""));
                            params.put("idcard", personId);
                            params.put("account", personName);
                            if (!TextUtils.isEmpty(cardId)) {
                                params.put("cardid", cardId);
                            } else {
                                return;
                            }
                            Presenter.getInstance(getApplicationContext()).postPaoBuSimple(NetApi.urlValidateBank, params, new PaoCallBack() {
                                @Override
                                protected void onSuc(String s) {
                                    LocalLog.d(TAG, "验证成功!");
                                    Intent intent = new Intent();
                                    intent.setAction(ACTION_RESET_BANK);
                                    intent.setClass(getApplicationContext(), PayPassWordActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                                    if (errorBean != null) {
                                        if (errorBean.getError() != 100) {
                                            PaoToastUtils.showLongToast(getApplicationContext(), errorBean.getMessage());
                                        }
                                    }
                                }
                            });
                        }
                    }
                }

                break;
        }
    }

    private boolean isFinishiAllInfo() {
        if (TextUtils.isEmpty(etCode.getText().toString())) {
            PaoToastUtils.showShortToast(IdentityAuth3Activity.this, "请输入验证码");
            return false;
        } else {
            return true;
        }
    }

}
