package com.paobuqianjin.pbq.step.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.SignUserResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.SoftKeyboardStateHelper;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

/**
 * Created by pbq on 2017/12/7.
 */

public class LoginActivity extends BaseActivity implements SoftKeyboardStateHelper.SoftKeyboardStateListener, LoginSignCallbackInterface {
    private final static String LOGIN_SUCCESS_ACTION = "com.paobuqianjin.pbq.LOGIN_SUCCESS_ACTION";
    private final static String TAG = LoginActivity.class.getSimpleName();
    private boolean showLoginPass = false, showSignPass = false;
    /*默认显示登入界面*/
    private int currentIndex;
    private RelativeLayout loginPan;
    private RelativeLayout.LayoutParams layoutParams, newLayoutParams;
    private RelativeLayout loginLayout;
    private RelativeLayout signLayout;
    private ImageView blueLoginLine, blueSignLine;
    private ImageView passWordOpenIV;
    private ImageView passWordSignOpenIV;
    private EditText useNameTV, phoneNumTV, passWordTV, signCodeTV, passWordSignTV;
    private TextView loginOrSignTV;
    private TextView userReadTV, signRequestTV;
    private TextView findPassTV;
    private RelativeLayout backGround;
    private String[] userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sign_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        detectKeyBoardHide();
        Presenter.getInstance(this).attachUiInterface(this);
    }


    private void detectKeyBoardHide() {
        final SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(findViewById(R.id.background_login_sign));
        softKeyboardStateHelper.addSoftKeyboardStateListener(this);
    }

    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        //Toast.makeText(LoginActivity.this, "弹出", Toast.LENGTH_SHORT).show();
        newLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        newLayoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin - 100, layoutParams.rightMargin, layoutParams.bottomMargin);
/*        TranslateAnimation translateAnimation = new TranslateAnimation(layoutParams.leftMargin, newLayoutParams.leftMargin, layoutParams.topMargin, newLayoutParams.topMargin);
        translateAnimation.setDuration(500);
        loginPan.startAnimation(translateAnimation);*/
        loginPan.setLayoutParams(newLayoutParams);

    }

    public void onSoftKeyboardClosed() {
        //Toast.makeText(LoginActivity.this, "隐藏", Toast.LENGTH_SHORT).show();
        loginPan.setLayoutParams(layoutParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface();
    }

    @Override
    protected void initView() {
        super.initView();

        loginPan = (RelativeLayout) findViewById(R.id.login_pan);
        layoutParams = (RelativeLayout.LayoutParams) loginPan.getLayoutParams();
        backGround = (RelativeLayout) findViewById(R.id.background_login_sign);
        loginLayout = (RelativeLayout) findViewById(R.id.log_pan);
        signLayout = (RelativeLayout) findViewById(R.id.sign_pan);
        blueLoginLine = (ImageView) findViewById(R.id.blue_line_login);
        blueSignLine = (ImageView) findViewById(R.id.blue_line_sign);
        loginOrSignTV = (TextView) findViewById(R.id.reg_login_des);

        phoneNumTV = (EditText) signLayout.findViewById(R.id.phone);
        signCodeTV = (EditText) signLayout.findViewById(R.id.sign_code);
        passWordSignTV = (EditText) signLayout.findViewById(R.id.password);
        signRequestTV = (TextView) signLayout.findViewById(R.id.sign_code_request);
        userReadTV = (TextView) signLayout.findViewById(R.id.xie_yi);
        passWordSignOpenIV = (ImageView) signLayout.findViewById(R.id.password_open_sign);


        useNameTV = (EditText) loginLayout.findViewById(R.id.login_user_name);
        passWordTV = (EditText) loginLayout.findViewById(R.id.login_user_password);
        passWordOpenIV = (ImageView) loginLayout.findViewById(R.id.password_open);
        findPassTV = (TextView) loginLayout.findViewById(R.id.wang_ji);

    }


    private String[] collectLoginUserInfo() {
        userInfo = new String[3];
        userInfo[0] = useNameTV.getText().toString();
        userInfo[1] = passWordTV.getText().toString();
        return userInfo;
    }

    private String[] collectSignUserInfo() {
        userInfo = new String[3];
        userInfo[0] = phoneNumTV.getText().toString();
        userInfo[1] = signCodeTV.getText().toString();
        userInfo[2] = passWordSignTV.getText().toString();
        return userInfo;
    }

    public void onTabLogin(View view) {
        LocalLog.d(TAG, "onTabLogin() enter");
        if (view != null) {
            switch (view.getId()) {
                case R.id.password_open:
                    if (!showLoginPass) {
                        LocalLog.d(TAG, " onTabLogin() 设置显示密码!");
                        passWordTV.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showLoginPass = true;
                        passWordOpenIV.setImageDrawable(getResources().getDrawable(R.drawable.pass_eye_yes));
                    } else {
                        LocalLog.d(TAG, " onTabLogin() 设置不显示密码!");
                        passWordTV.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showLoginPass = false;
                        passWordOpenIV.setImageDrawable(getResources().getDrawable(R.drawable.pass_eye_no));
                    }
                    break;
                case R.id.wang_ji:
                    //TODO
                    LocalLog.d(TAG, "onTabLogin() 忘记密码");
                    break;
                case R.id.sign_code_request:
                    LocalLog.d(TAG, "onTabLogin() 请求验证码!");
                    collectSignUserInfo();
                    Presenter.getInstance(this).getMsg(userInfo[0]);
                    break;
                case R.id.password_open_sign:
                    if (!showSignPass) {
                        LocalLog.d(TAG, " onTabLogin() 设置显示密码!");
                        passWordSignTV.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showSignPass = true;
                        passWordSignOpenIV.setImageDrawable(getResources().getDrawable(R.drawable.pass_eye_yes));
                    } else {
                        LocalLog.d(TAG, " onTabLogin() 设置不显示密码!");
                        passWordSignTV.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showSignPass = false;
                        passWordSignOpenIV.setImageDrawable(getResources().getDrawable(R.drawable.pass_eye_no));
                    }
                    break;
                default:
                    break;
            }
        }
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
                        Presenter.getInstance(this).userLoginByPhoneNumber(collectLoginUserInfo());
                    } else if (currentIndex == 1) {
                        Presenter.getInstance(this).registerByPhoneNumber(collectSignUserInfo());
                    }
                    break;
            }
        }
    }

    @Override
    public void requestPhoneLoginCallback(LoginResponse loginResponse) {
        LocalLog.d(TAG, "手机号登入成功! 去获取用户信息!");
        Presenter.getInstance(this).steLogFlg(true);
        startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
        Presenter.getInstance(this).getUserInfo(loginResponse.getData().getId());
    }

    @Override
    public void requestPhoneSignCodeCallBack(int signCode) {
        signCodeTV.setText(String.valueOf(signCode));
    }

    @Override
    public void requestThirdLoginCallBack() {

    }

    @Override
    public void registerByPhoneCallBack(SignUserResponse signUserResponse) {
        Toast.makeText(this, signUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
