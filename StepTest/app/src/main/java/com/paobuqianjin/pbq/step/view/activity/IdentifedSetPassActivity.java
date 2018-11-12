package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.MD5;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.login.BindPhoneFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/6/8.
 */

public class IdentifedSetPassActivity extends BaseBarActivity {
    private final static String TAG = IdentityAuth2Activity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.pass_word)
    EditText passWord;
    @Bind(R.id.pass_word_again)
    EditText passWordAgain;
    @Bind(R.id.sign_code_edit)
    EditText signCodeEdit;
    @Bind(R.id.sign_code_span)
    LinearLayout signCodeSpan;
    @Bind(R.id.btn_code)
    Button btnCode;
    private NormalDialog normalDialog;
    private Thread thread;
    public int T = 60; //倒计时时长
    private Handler mHandler = new Handler();

    @Override
    protected String title() {
        return "设置钱包支付密码";
    }

    @Override
    public Object right() {
        return "确定";
    }

    @Override
    public void onBackPressed() {
        if (normalDialog == null) {
            normalDialog = new NormalDialog(this);
            normalDialog.setMessage("提现和支付需要钱包密码，确定要取消密码设置？");
            normalDialog.setYesOnclickListener(getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    normalDialog.dismiss();
                    finish();
                }
            });
            normalDialog.setNoOnclickListener(getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    normalDialog.dismiss();
                }
            });
        }
        if (!normalDialog.isShowing() && !isFinishing()) {
            normalDialog.show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_ed_setpass_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        setToolBarListener(new ToolBarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {
                if (!passWord.getText().toString().equals(passWordAgain.getText().toString())) {
                    PaoToastUtils.showShortToast(getApplicationContext(), "两次输入的密码不一致");
                    return;
                }
                if (TextUtils.isEmpty(signCodeEdit.getText().toString().trim())) {
                    PaoToastUtils.showShortToast(getApplicationContext(), "请输入验证码");
                    return;
                }
                String psw = passWordAgain.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("paypw", Base64Util.makeUidToBase64(psw));
                params.put("code", signCodeEdit.getText().toString().trim());
                Presenter.getInstance(IdentifedSetPassActivity.this).postPaoBuSimple(NetApi.urlAddPassIdentify, params, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        PaoToastUtils.showLongToast(getApplicationContext(), "密码设置成功!");
                        finish();
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                        if (errorBean != null) {
                            PaoToastUtils.showLongToast(getApplicationContext(), errorBean.getMessage());
                        }
                    }
                });
            }
        });
        passWordAgain = (EditText) findViewById(R.id.pass_word_again);
        passWordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passWord.getText().toString().length() == 6
                        && passWordAgain.getText().toString().length() == 6) {

                } else {

                }
            }
        });
    }


    private class MyCountDownTimer implements Runnable {
        public void run() {

            //倒计时开始，循环
            while (T > 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (btnCode != null) {
                            btnCode.setClickable(false);
                            btnCode.setText(T + "秒");
                        }
                    }
                });
                try {
                    Thread.sleep(1000); //强制线程休眠1秒，就是设置倒计时的间隔时间为1秒。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                T--;
            }
            //倒计时结束，也就是循环结束
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (btnCode != null) {
                        btnCode.setClickable(true);
                        btnCode.setText("获取验证码");
                    }
                }
            });
            T = 60; //最后再恢复倒计时时长
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (thread != null) {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        }
    }

    private String keyStr(String phone) {
        String timeStemp = String.valueOf(System.currentTimeMillis() / 1000);
        return "&term=app&app_sign=" + MD5.md5Slat(Utils.KEY_SIGN + phone + timeStemp) + "&timestamp=" + timeStemp;
    }

    @OnClick(R.id.btn_code)
    public void onClick() {
        LocalLog.d(TAG, "获取验证码");
        UserInfoResponse.DataBean userInfoResponse = Presenter.getInstance(this).getCurrentUser();
        if (userInfoResponse == null || TextUtils.isEmpty(userInfoResponse.getMobile())) {
            if (TextUtils.isEmpty(userInfoResponse.getMobile())) {
                PaoToastUtils.showLongToast(this, "账号绑定手机号异常，请联系客服!");
            }
            return;
        }
        String url = NetApi.urlSendMsg + userInfoResponse.getMobile() + keyStr(userInfoResponse.getMobile());
        Presenter.getInstance(this).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showLongToast(IdentifedSetPassActivity.this, "验证码发送成功");
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(IdentifedSetPassActivity.this, errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(IdentifedSetPassActivity.this, "开小差了，请稍后再试");
                }
            }
        });
        if (thread != null && thread.isAlive()) {
            return;
        } else {
            thread = new Thread(new MyCountDownTimer());
            thread.start();
        }
    }
}
