package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
    private String[] userInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_edit_layout);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.register_img_back,R.id.read_xieyi,R.id.get_code,R.id.register_eyes, R.id.new_btn_register,R.id.user_read_choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_img_back:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.get_code:
                LocalLog.d(TAG, "onTabLogin() 请求验证码!");
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
                            userreadchoose.setBackgroundResource(R.drawable.choose);
                            newBtnRegister.setBackgroundColor(Color.parseColor("#6c71c4"));

                        } else{
                            userreadchoose.setBackgroundResource(R.drawable.nochoose);
                            newBtnRegister.setBackgroundColor(Color.parseColor("#b8bbbd"));

                            Toast.makeText(RegisterActivity.this, "请阅读《跑步钱进服务协议》", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            case R.id.new_btn_register:


                Presenter.getInstance(getApplicationContext()).registerByPhoneNumber(collectSignUserInfo());
                break;
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
