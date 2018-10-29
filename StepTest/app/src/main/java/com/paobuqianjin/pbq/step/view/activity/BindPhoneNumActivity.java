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
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.MD5;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindPhoneNumActivity extends BaseActivity {
    private final static String TAG = BindPhoneNumActivity.class.getSimpleName();

    private boolean showLoginPass = false, showSignPass = false;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.bind_account)
    EditText bind_account;
//    @Bind(R.id.bind_indntifying_code)
//    EditText forgotpwd_indntifying_code;
    @Bind(R.id.bind_indntifying_code)
    Button bind_indntifying_code;
    @Bind(R.id.bind_pwd)
    EditText bind_pwd;
    @Bind(R.id.bind_eyes)
    ImageView bind_eyes;
    @Bind(R.id.bind_ok)
    Button bind_ok;
    private String[] userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
    }

    private String keyStr(String phone) {
        String timeStemp = String.valueOf(System.currentTimeMillis() / 1000);
        return "&term=app&app_sign=" + MD5.md5Slat(Utils.KEY_SIGN + phone + timeStemp) + "&timestamp=" + timeStemp;
    }

    private void getSignCode(String phone) {
        if (!Utils.isMobile(phone)) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = NetApi.urlSendMsg + phone + keyStr(phone);
        Presenter.getInstance(this).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showLongToast(BindPhoneNumActivity.this, "验证码发送成功");
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(BindPhoneNumActivity.this, errorBean.getMessage());
                }else{
                    PaoToastUtils.showLongToast(BindPhoneNumActivity.this, "开小差了，请稍后再试");
                }
            }
        });
    }

    @OnClick({R.id.img_back, R.id.bind_getCode, R.id.bind_eyes})
    public void onTabLogin(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.img_back:
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
                case R.id.bind_indntifying_code:
                    LocalLog.d(TAG, "onTabLogin() 请求验证码!");
                    collectSignUserInfo();
                    /*Presenter.getInstance(this).getMsg(userInfo[0]);*/
                    getSignCode(userInfo[0]);
                    break;
                case R.id.bind_eyes:
                    if (!showLoginPass) {
                        LocalLog.d(TAG, " onTabLogin() 设置显示密码!");
                        bind_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showLoginPass = true;
                        bind_eyes.setImageDrawable(getResources().getDrawable(R.drawable.forgotpwd_eyes));
                    } else {
                        LocalLog.d(TAG, " onTabLogin() 设置不显示密码!");
                        bind_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showLoginPass = false;
                        bind_eyes.setImageDrawable(getResources().getDrawable(R.drawable.register_eyes));
                    }
                    break;

            }

        }

    }

    private String[] collectSignUserInfo() {
        userInfo = new String[3];
        userInfo[0] = bind_account.getText().toString();
        userInfo[1] = bind_indntifying_code.getText().toString();
        userInfo[2] = bind_pwd.getText().toString();
        return userInfo;
    }

}
