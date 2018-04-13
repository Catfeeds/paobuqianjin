package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassWordParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PassWordResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity {
    private final static String TAG = ForgotPasswordActivity.class.getSimpleName();

    private boolean showLoginPass = false, showSignPass = false;
   @Bind(R.id.img_back)
   ImageView img_back;
    @Bind(R.id.forgotpwd_account)
    EditText forgotpwd_account;
    @Bind(R.id.forgotpwd_indntifying_code)
    EditText forgotpwd_indntifying_code;
    @Bind(R.id.forgotpwd_getCode)
    Button forgotpwd_getCode;
    @Bind(R.id.forgotpwd_pwd)
    EditText forgotpwd_pwd;
    @Bind(R.id.forgotPwd_eyes)
    ImageView forgotPwd_eyes;
    @Bind(R.id.forgotpwd_ok)
    Button forgotpwd_ok;
    private String[] userInfo;

    PostPassWordParam postPassWordParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
    }



    @OnClick({R.id.img_back,R.id.forgotpwd_getCode,R.id.forgotPwd_eyes,R.id.forgotpwd_ok})
    public void onTabLogin(View view) {
        if (view!=null){
            switch (view.getId()){
                case R.id.img_back:
                    startActivity(new Intent(this,LoginActivity.class));
                    break;
                case R.id.forgotpwd_getCode:
                    LocalLog.d(TAG, "onTabLogin() 请求验证码!");
                    collectSignUserInfo();
                    Presenter.getInstance(this).getMsg(userInfo[0]);
                    break;
                case R.id.forgotPwd_eyes:
                    if (!showLoginPass) {
                        LocalLog.d(TAG, " onTabLogin() 设置显示密码!");
                        forgotpwd_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showLoginPass = true;
                        forgotPwd_eyes.setImageDrawable(getResources().getDrawable(R.drawable.forgotpwd_eyes));
                    } else {
                        LocalLog.d(TAG, " onTabLogin() 设置不显示密码!");
                        forgotpwd_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showLoginPass = false;
                        forgotPwd_eyes.setImageDrawable(getResources().getDrawable(R.drawable.register_eyes));
                    }
                    break;

                case R.id.forgotpwd_ok:
                    postPassWordParam = new PostPassWordParam();
                    postPassWordParam.setCode(forgotpwd_getCode.getText().toString())
                            .setMobile(forgotpwd_account.getText().toString())
                            .setPassword(forgotpwd_pwd.getText().toString());
                    Presenter.getInstance(this).postNewPassWord(postPassWordParam);
                    break;

            }

        }

    }


    private String[] collectSignUserInfo() {
        userInfo = new String[3];
        userInfo[0] = forgotpwd_account.getText().toString();
        userInfo[1] = forgotpwd_getCode.getText().toString();
        userInfo[2] = forgotpwd_pwd.getText().toString();
        return userInfo;
    }

    private String[] collectLoginUserInfo() {
        userInfo = new String[3];
        userInfo[0] = forgotpwd_account.getText().toString();
        userInfo[1] = forgotpwd_pwd.getText().toString();
        return userInfo;
    }


}
