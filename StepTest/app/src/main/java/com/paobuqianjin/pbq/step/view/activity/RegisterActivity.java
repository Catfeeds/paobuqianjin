package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.PhoneSignInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.MD5;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.paobuqianjin.pbq.step.R.id.user_read_choose;

public class RegisterActivity extends BaseActivity implements PhoneSignInterface {
    private final static String TAG = RegisterActivity.class.getSimpleName();
    @Bind(R.id.invite_code)
    EditText inviteCode;
    private boolean showLoginPass = false, showSignPass = false;

    private final static String USER_SERVICE_AGREEMENT_ACTION = "com.paobuqianjin.pbq.step.SERVICE_ACTION";
    @Bind(R.id.register_img_back)
    ImageView register_img_back;
    @Bind(R.id.text_tile_login)
    TextView textTileLogin;
    @Bind(R.id.new_account)
    EditText newAccount;
    @Bind(R.id.indntifying_code)
    EditText indntifyingCode;
    @Bind(R.id.get_code)
    Button getCode;
    @Bind(R.id.new_pwd)
    EditText newPwd;
    @Bind(R.id.new_btn_register)
    Button newBtnRegister;
    @Bind(user_read_choose)
    ToggleButton userreadchoose;
    @Bind(R.id.register_eyes)
    ImageView openPwd;
    @Bind(R.id.read_xieyi)
    TextView read_xieyi;
    Thread thread;

    public int T = 60; //倒计时时长
    private Handler mHandler = new Handler();


    private String[] userInfo;
    private boolean isBool;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_edit_layout);
        ButterKnife.bind(this);
        Presenter.getInstance(this).attachUiInterface(this);
        progressDialog = new ProgressDialog(this);
        newAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String newAccountStr = newAccount.getText().toString().trim();
                if (newAccountStr.length() == 11) {
                    getCode.setEnabled(true);
                } else {
                    getCode.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        newPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkConfirmEnable();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        indntifyingCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkConfirmEnable();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        read_xieyi = (TextView) findViewById(R.id.read_xieyi);
        String readText = "请认真阅读";
        String protocol = "《跑步钱进用户协议》";
        SpannableStringBuilder style = new SpannableStringBuilder(readText + protocol);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), readText.length()
                , (readText + protocol).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        read_xieyi.setText(style);
    }

    private void showProgressDialog(String msg) {
        progressDialog.setMessage(msg);
        if (!progressDialog.isShowing()) progressDialog.show();
    }

    private void checkConfirmEnable() {
        String newAccountStr = newAccount.getText().toString().trim();
        String newPwdStr = newPwd.getText().toString().trim();
        String indntifyingCodeStr = indntifyingCode.getText().toString().trim();
        if ((newAccountStr.length() == 11) && (newPwdStr.length() >= 8) && (newPwdStr.length() <= 16) && (indntifyingCodeStr.length() == 6) && isBool) {
            newBtnRegister.setEnabled(true);
        } else {
            newBtnRegister.setEnabled(false);
        }
    }

    private String keyStr(String phone) {
        String timeStemp = String.valueOf(System.currentTimeMillis() / 1000);
        return "&term=app&app_sign=" + MD5.md5Slat(Utils.KEY_SIGN + phone + timeStemp) + "&timestamp=" + timeStemp;
    }

    private boolean getSignCode(String phone) {
        if (!Utils.isMobile(phone)) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        String url = NetApi.urlSendMsg + phone + keyStr(phone);
        Presenter.getInstance(this).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showLongToast(RegisterActivity.this, "验证码发送成功");
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(RegisterActivity.this, errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(RegisterActivity.this, "开小差了，请稍后再试");
                }
            }
        });
        return true;
    }

    @OnClick({R.id.register_img_back, R.id.indntifying_code, R.id.read_xieyi, R.id.get_code, R.id.register_eyes, R.id.new_btn_register, R.id.user_read_choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_img_back:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.get_code:
                collectSignUserInfo();
                if (getSignCode(userInfo[0])) {
                    if (thread != null && thread.isAlive()) {
                        return;
                    } else {
                        thread = new Thread(new MyCountDownTimer());
                        thread.start();
                    }
                }
                break;

            case R.id.register_eyes:
                if (!showLoginPass) {
                    LocalLog.d(TAG, " onTabLogin() 设置显示密码!");
                    newPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showLoginPass = true;
                    openPwd.setImageDrawable(getResources().getDrawable(R.drawable.forgotpwd_eyes));
                } else {
                    LocalLog.d(TAG, " onTabLogin() 设置不显示密码!");
                    newPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showLoginPass = false;
                    openPwd.setImageDrawable(getResources().getDrawable(R.drawable.register_eyes));
                }
                break;
            case R.id.read_xieyi:
                startActivity(AgreementActivity.class, null, false, USER_SERVICE_AGREEMENT_ACTION);
                break;
            case user_read_choose:
                userreadchoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            isBool = true;
                            userreadchoose.setBackgroundResource(R.drawable.choose);
                            Log.d(TAG, "onChecked: " + b);
                            newBtnRegister.setEnabled(true);
                        } else {
                            isBool = false;
                            newBtnRegister.setEnabled(false);
                            userreadchoose.setBackgroundResource(R.drawable.nochoose);
                        }
                    }
                });
                break;

            case R.id.new_btn_register:
                newBtnRegister.setBackground(ContextCompat.getDrawable(this, R.drawable.rect_angle_diss_bt));
                newBtnRegister.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (newBtnRegister != null) {
                            checkConfirmEnable();
                        }
                    }
                }, 15000);
                if (!isBool) {
                    PaoToastUtils.showLongToast(this, "请阅读《跑步钱进服务协议》");
                } else {
                    showProgressDialog("请稍等");
                    Presenter.getInstance(getApplicationContext()).registerByPhoneNumber(collectSignUserInfo(),inviteCode.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    public void response(SignUserResponse signUserResponse) {
        newBtnRegister.setEnabled(true);
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                newBtnRegister.setBackground(ContextCompat.getDrawable(RegisterActivity.this, R.drawable.change_color_btn));
            }
        }
        if (signUserResponse.getError() == 0) {
            LocalLog.d(TAG, "注册成功! 去登陆页");
            PaoToastUtils.showLongToast(this, "注册成功!");
            Presenter.getInstance(this).steLogFlg(true);
            Presenter.getInstance(this).setId(signUserResponse.getData().getId());
            Presenter.getInstance(this).setNo(signUserResponse.getData().getNo());
            Presenter.getInstance(this).setToken(this, signUserResponse.getData().getUser_token());
            Intent intent = new Intent();
            intent.putExtra("r_id", signUserResponse.getData().getId());
            intent.putExtra("r_no", signUserResponse.getData().getNo());
            intent.putExtra("r_user_token", signUserResponse.getData().getUser_token());
            intent.putExtra("r_chat_token", signUserResponse.getData().getChat_token());
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            PaoToastUtils.showLongToast(this, signUserResponse.getMessage());
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        PaoToastUtils.showLongToast(this, errorCode.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface(this);
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    /**
     * 自定义倒计时类，实现Runnable接口
     */

    class MyCountDownTimer implements Runnable {


        public void run() {

            //倒计时开始，循环
            while (T > 0) {
                mHandler.post(new Runnable() {
                    @Override


                    public void run() {
                        getCode.setClickable(false);
                        getCode.setText(T + "秒");

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
                    getCode.setClickable(true);
                    getCode.setText("获取验证码");

                }

            });
            T = 60; //最后再恢复倒计时时长

        }

    }


    private String[] collectSignUserInfo() {
        userInfo = new String[3];
        userInfo[0] = newAccount.getText().toString();
        userInfo[1] = indntifyingCode.getText().toString();
        userInfo[2] = newPwd.getText().toString();
        return userInfo;
    }
}
