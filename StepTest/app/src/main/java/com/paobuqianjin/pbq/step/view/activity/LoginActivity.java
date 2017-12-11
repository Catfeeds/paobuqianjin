package com.paobuqianjin.pbq.step.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.BaseActivity;

/**
 * Created by pbq on 2017/12/7.
 */

public class LoginActivity extends BaseActivity {
    private final static String TAG = LoginActivity.class.getSimpleName();
    /*默认显示登入界面*/
    private int currentIndex;
    private RelativeLayout loginLayout;
    private RelativeLayout signLayout;
    private ImageView blueLoginLine, blueSignLine;
    private EditText useNameTV, passWordTV, signCodeTV;
    private TextView loginOrSignTV;
    private TextView userReadTV;
    private RelativeLayout backGround;
    private String[] userInfo = new String[3];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sign_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        backGround = (RelativeLayout) findViewById(R.id.background_login_sign);
        loginLayout = (RelativeLayout) findViewById(R.id.log_pan);
        signLayout = (RelativeLayout) findViewById(R.id.sign_pan);
        blueLoginLine = (ImageView) findViewById(R.id.blue_line_login);
        blueSignLine = (ImageView) findViewById(R.id.blue_line_sign);
        loginOrSignTV = findViewById(R.id.reg_login_des);

        useNameTV = signLayout.findViewById(R.id.phone);
        signCodeTV = signLayout.findViewById(R.id.sign_code);
        passWordTV = signLayout.findViewById(R.id.password);
        userReadTV = signLayout.findViewById(R.id.xie_yi);


        useNameTV = loginLayout.findViewById(R.id.login_user_name);
        passWordTV = loginLayout.findViewById(R.id.login_user_password);
    }

    private String[] collectLoginUserInfo() {
        userInfo[0] = useNameTV.getText().toString();
        userInfo[1] = passWordTV.getText().toString();
        LocalLog.d(TAG, "userInfo name:" + userInfo[0] + " ,signCode: " + userInfo[1]);
        return userInfo;
    }

    private String[] collectSignUserInfo() {
        userInfo[0] = useNameTV.getText().toString();
        userInfo[1] = signCodeTV.getText().toString();
        userInfo[2] = passWordTV.getText().toString();

        LocalLog.d(TAG, "userInfo name:" + userInfo[0] + " ,passWord: " + userInfo[1] + ", passWord: " + userInfo[2]);
        return userInfo;
    }

    public void onTabLogin(View view) {
        LocalLog.d(TAG, "onTabLogin() enter");

    }

    public void onTabLogSign(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.login_page_sign:
                    LocalLog.d(TAG, "onTabLogSign() 点击注册");
                    if (currentIndex == 1) {
                        LocalLog.e(TAG, "已经是注册页了！");
                    } else {
                        backGround.setBackgroundResource(R.drawable.background_sign);
                        loginLayout.setVisibility(View.GONE);
                        signLayout.setVisibility(View.VISIBLE);
                        blueLoginLine.setVisibility(View.GONE);
                        blueSignLine.setVisibility(View.VISIBLE);
                        currentIndex = 1;

                        String strPart1 = "点击注册，即表示你已阅读并同意";
                        String strPart2 = "《跑步钱进服务协议》";
                        SpannableStringBuilder style = new SpannableStringBuilder(strPart1 + strPart2);
                        style.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffffff")), 0, strPart1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        style.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6c71c4")), strPart1.length(), (strPart1 + strPart2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        userReadTV.setText(style);
                        loginOrSignTV.setText(getResources().getText(R.string.desc_sign));

                    }

                    break;
                case R.id.sign_page_login:
                    LocalLog.d(TAG, "onTabLogSign() 点击登入");
                    if (currentIndex == 0) {
                        LocalLog.e(TAG, "已经是登陆页了！");
                    } else {
                        backGround.setBackgroundResource(R.drawable.background_login);
                        signLayout.setVisibility(View.GONE);
                        loginLayout.setVisibility(View.VISIBLE);
                        currentIndex = 0;
                        blueSignLine.setVisibility(View.GONE);
                        blueLoginLine.setVisibility(View.VISIBLE);

                        loginOrSignTV.setText(getResources().getText(R.string.desc_login));
                    }
                    break;

                case R.id.btn_sign_foot:
                    LocalLog.d(TAG, "currentIndex = " + currentIndex);
                    if (currentIndex == 0) {
                        collectLoginUserInfo();
                    } else if (currentIndex == 1) {
                        collectSignUserInfo();
                    }
                    break;
            }
        }
    }
}
