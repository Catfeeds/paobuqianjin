package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.paobuqianjin.pbq.step.R.id.user_read_choose;

public class RegisterActivity extends BaseActivity {
    private final static String TAG = RegisterActivity.class.getSimpleName();
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

    public int T = 60; //倒计时时长
    private Handler mHandler = new Handler();


    private String[] userInfo;
    private boolean isBool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_edit_layout);
        ButterKnife.bind(this);
        newAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "进来了 ");
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
                String newAccountStr = newAccount.getText().toString().trim();
                String newPwdStr = newPwd.getText().toString().trim();
                String indntifyingCodeStr = indntifyingCode.getText().toString().trim();
                if ((newAccountStr.length() == 11) && (newPwdStr.length() >= 6) && (newPwdStr.length() <= 12) && (indntifyingCodeStr.length() == 6)) {
                    newBtnRegister.setEnabled(true);
                } else {
                    newBtnRegister.setEnabled(false);
                }

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
                String newAccountStr = newAccount.getText().toString().trim();
                String newPwdStr = newPwd.getText().toString().trim();
                String indntifyingCodeStr = indntifyingCode.getText().toString().trim();
                if ((newAccountStr.length() == 11) && (newPwdStr.length() == 6) && (indntifyingCodeStr.length() == 6)) {
                    newBtnRegister.setEnabled(true);
                } else {
                    newBtnRegister.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    @OnClick({R.id.register_img_back, R.id.indntifying_code, R.id.read_xieyi, R.id.get_code, R.id.register_eyes, R.id.new_btn_register, R.id.user_read_choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_img_back:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.get_code:
                new Thread(new MyCountDownTimer()).start();//开始执行
                collectSignUserInfo();
                Presenter.getInstance(this).getMsg(userInfo[0]);
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
                        } else {
                            isBool = false;
                            userreadchoose.setBackgroundResource(R.drawable.nochoose);
                            Log.d(TAG, "onCheckedChanged: " + b);
                            Toast.makeText(RegisterActivity.this, "请阅读《跑步钱进服务协议》", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            case R.id.new_btn_register:
                if (!isBool) {
                    Toast.makeText(RegisterActivity.this, "请阅读《跑步钱进服务协议》", Toast.LENGTH_SHORT).show();
                } else {
                    Presenter.getInstance(getApplicationContext()).registerByPhoneNumber(collectSignUserInfo());
                }
                break;
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
