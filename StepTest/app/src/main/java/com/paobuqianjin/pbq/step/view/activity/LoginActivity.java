package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.login.ForgetPassFragment;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2017/12/7.
 */

public class LoginActivity extends BaseActivity implements LoginSignCallbackInterface {
    private final static String LOGIN_SUCCESS_ACTION = "com.paobuqianjin.pbq.LOGIN_SUCCESS_ACTION";
    private final static String USER_FIT_ACTION_BIND = "com.paobuqianjin.pbq.USER_FIT_ACTION_BIND";
    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    private final static String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.login_weixin)
    ImageView login_weixin;
    @Bind(R.id.login_qq)
    ImageView login_qq;
    @Bind(R.id.login_account)
    EditText loginAccount;
    //    @Bind(R.id.text_tile_login)
//    TextView mTextView;
    @Bind(R.id.login_pwd)
    EditText loginPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.forgot_pwd)
    TextView forgotPwd;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.other_login)
    TextView otherLogin;


    private boolean showLoginPass = false, showSignPass = false;
    /*默认显示登入界面*/
    private int currentIndex;
    private RelativeLayout.LayoutParams layoutParams, newLayoutParams;

    private String[] userInfo;
    private ProgressDialog dialog;
    private ThirdPartyLoginParam thirdPartyLoginParam;
    LoginResponse loginResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //detectKeyBoardHide();
        Presenter.getInstance(this).attachUiInterface(this);
    }


    @Override
    protected void initView() {
        super.initView();
        dialog = new ProgressDialog(this);
    }


    public void onTabLogin(View view) {
        LocalLog.d(TAG, "onTabLogin() enter");
        if (view != null) {
            switch (view.getId()) {
                /*case R.id.password_open:
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
                    break;*/
                case R.id.forgot_pwd:
                    //TODO
                    LocalLog.d(TAG, "onTabLogin() 忘记密码");
                    startActivity(LoginForgetPassActivity.class, null, false);
                    break;
                /*case R.id.sign_code_request:
                    LocalLog.d(TAG, "onTabLogin() 请求验证码!");
                    collectSignUserInfo();
                    Presenter.getInstance(this).getMsg(userInfo[0]);
                    break;*/
                /*case R.id.password_open_sign:
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
                    break;*/
                default:
                    break;
            }
        }
    }

    public void onTabLogSign(View view) {
        if (view != null) {
            switch (view.getId()) {
                /*case R.id.login_page_sign:
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
                    break;*/
                case R.id.btn_login:
                    LocalLog.d(TAG, "currentIndex = " + currentIndex);
                    if (currentIndex == 0) {
                        Presenter.getInstance(this).userLoginByPhoneNumber(collectLoginUserInfo());
                    } else if (currentIndex == 1) {
                        Presenter.getInstance(this).registerByPhoneNumber(collectSignUserInfo());
                    }
                    break;
                case R.id.register:
                    startActivity(RegisterActivity.class, null, false);
                    //Presenter.getInstance(this).registerByPhoneNumber(collectSignUserInfo());
                    break;


//                case R.id.btn_sign_foot:
//                    LocalLog.d(TAG, "currentIndex = " + currentIndex);
//                    if (currentIndex == 0) {
//                        Presenter.getInstance(this).userLoginByPhoneNumber(collectLoginUserInfo());
//                    } else if (currentIndex == 1) {
//                        Presenter.getInstance(this).registerByPhoneNumber(collectSignUserInfo());
//                    }
//                    break;
            }
        }
    }


    private String[] collectSignUserInfo() {
        userInfo = new String[3];
        userInfo[0] = loginAccount.getText().toString();
        userInfo[1] = loginPwd.getText().toString();

        return userInfo;
    }


    private String[] collectLoginUserInfo() {
        userInfo = new String[3];
        userInfo[0] = loginAccount.getText().toString();
        userInfo[1] = loginPwd.getText().toString();
        return userInfo;
    }

    /*private String[] collectSignUserInfo() {
        userInfo = new String[3];
        userInfo[0] = phoneNumTV.getText().toString();
        userInfo[1] = signCodeTV.getText().toString();
        userInfo[2] = passWordSignTV.getText().toString();
        return userInfo;
    }*/

    @Override
    public void response(LoginResponse loginResponse) {
        LocalLog.d(TAG, "手机号登入成功! 去获取用户信息!");
        this.loginResponse = loginResponse;
        if (!"".equals(loginResponse.getData().getQq_openid()) || !"".equals(loginResponse.getData().getWx_openid())) {
            LocalLog.d(TAG, "绑定过QQ或者微信");
            Presenter.getInstance(this).steLogFlg(true);
            Presenter.getInstance(this).setToken(this, loginResponse.getData().getUser_token());
            Presenter.getInstance(this).setId(loginResponse.getData().getId());
            Presenter.getInstance(this).setMobile(this, loginResponse.getData().getMobile());
            startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
        } else {
            if (loginResponse.getData().getIs_perfect() == 0) {
                LocalLog.d(TAG, "没有完善信息");
                Intent intent = new Intent();
                intent.setClass(this, UserFitActivity.class);
                intent.setAction(USER_FIT_ACTION_SETTING);
                intent.putExtra("userinfo", loginResponse.getData());
                startActivity(intent);
            } else {
                Presenter.getInstance(this).steLogFlg(true);
                Presenter.getInstance(this).setId(loginResponse.getData().getId());
                Presenter.getInstance(this).setToken(this, loginResponse.getData().getUser_token());
                Presenter.getInstance(this).setMobile(this, loginResponse.getData().getMobile());
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            }
        }

    }

    @Override
    public void response(GetSignCodeResponse getSignCodeResponse) {
        if (getSignCodeResponse.getError() == 0) {
            Toast.makeText(this, getSignCodeResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(ThirdPartyLoginResponse thirdPartyLoginResponse) {
        if (thirdPartyLoginResponse.getError() == 0) {
            if (thirdPartyLoginResponse.getData().getId() == 0) {
                LocalLog.d(TAG, "首次登录,再次登录获取userid");
                Presenter.getInstance(LoginActivity.this).postThirdPartyLogin(thirdPartyLoginParam);
                return;
            }
            if (thirdPartyLoginResponse.getData().getMobile() != null && !"".equals(thirdPartyLoginResponse.getData().getMobile())) {
                LocalLog.d(TAG, "登录成功已经绑定过手机号码");
                Presenter.getInstance(this).steLogFlg(true);
                Presenter.getInstance(this).setId(thirdPartyLoginResponse.getData().getId());
                Presenter.getInstance(this).setMobile(this, thirdPartyLoginResponse.getData().getMobile());
                Presenter.getInstance(this).setToken(this, thirdPartyLoginResponse.getData().getUser_token());
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            } else {
                LocalLog.d(TAG, "没有绑定过手机");
                Intent intent = new Intent();
                intent.setClass(this, UserFitActivity.class);
                intent.setAction(USER_FIT_ACTION_BIND);
                intent.putExtra("userinfo", thirdPartyLoginResponse.getData());
                startActivity(intent);
                finish();
            }
        }

    }

    @Override
    public void response(LoginRecordResponse loginRecordResponse) {
        LocalLog.d(TAG, "LoginRecordResponse() enter " + loginRecordResponse.toString());
        if (loginRecordResponse.getError() == 1) {
            LocalLog.d(TAG, "没有登录过");
            startActivity(UserFitActivity.class, null, true, USER_FIT_ACTION_SETTING);
        } else {
            if (loginResponse != null) {
                Presenter.getInstance(this).steLogFlg(true);
                Presenter.getInstance(this).setToken(this, loginResponse.getData().getUser_token());
                Presenter.getInstance(this).setId(loginResponse.getData().getId());
                Presenter.getInstance(this).setMobile(this, loginResponse.getData().getMobile());
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            }

        }
    }

    @Override
    public void response(SignUserResponse signUserResponse) {
        Toast.makeText(this, signUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
        if (signUserResponse.getError() == 0) {
            LocalLog.d(TAG, "注册成功! 去登陆页");
            Intent intent = new Intent();
            intent.setClass(this, UserFitActivity.class);
            intent.setAction(USER_FIT_ACTION_SETTING);
            intent.putExtra("signinfo", signUserResponse.getData());
            startActivity(intent);
            finish();
/*            if (currentIndex == 0) {
                LocalLog.e(TAG, "已经是登陆页了！");
            } else {
                backGround.setBackgroundResource(R.drawable.background_login);
                signLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
                currentIndex = 0;
                blueSignLine.setVisibility(View.GONE);
                blueLoginLine.setVisibility(View.VISIBLE);

                loginOrSignTV.setText(getResources().getText(R.string.desc_login));
            }*/
        }
    }

    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, "授权开始callback:" + share_media.toString());
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            thirdPartyLoginParam = new ThirdPartyLoginParam();
            LocalLog.d(TAG, "授权成功callback:" + share_media.toString());
            Toast.makeText(LoginActivity.this, "成功：", Toast.LENGTH_LONG).show();
            String temp = "";
            if (share_media.ordinal() == SHARE_MEDIA.WEIXIN.ordinal()) {
                thirdPartyLoginParam.setAction("wx");
                for (String key : map.keySet()) {
                    temp = temp + key + ":" + map.get(key) + "\n";
                    switch (key) {
                        case "openid":
                            thirdPartyLoginParam.setOpenid(map.get(key));
                            continue;
                        case "screen_name":
                            thirdPartyLoginParam.setNickname(map.get(key));
                            continue;
                        case "iconurl":
                            thirdPartyLoginParam.setAvatar(map.get(key));
                            continue;
                        case "province":
                            thirdPartyLoginParam.setProvince(map.get(key));
                            continue;
                        case "city":
                            thirdPartyLoginParam.setCity(map.get(key));
                            continue;
                        case "gender":
                            if (map.get(key).equals("男")) {
                                thirdPartyLoginParam.setSex(0);
                            } else {
                                thirdPartyLoginParam.setSex(1);
                            }
                            continue;
                        case "unionid":
                            thirdPartyLoginParam.setUnionid(map.get(key));
                            continue;
                        default:
                            continue;
                    }
                }

            }
            Presenter.getInstance(LoginActivity.this).postThirdPartyLogin(thirdPartyLoginParam);
            LocalLog.d(TAG, temp);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "失败：" + throwable.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @OnClick({R.id.login_weixin, R.id.login_qq})
    public void onClickTab(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.login_weixin:
                    LocalLog.d(TAG, "微信三方登录");
                    LocalLog.d(TAG, "xxxxxx install-=" + UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN));
                    //UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
                    UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);

                    break;
                case R.id.login_qq:
                    //  LocalLog.d(TAG, "xxxxxx install-=" + UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.QQ));
                    UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.QQ, authListener);
                    UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                    break;
            /*case R.id.sina:
                UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
                break;*/
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    public void response(ErrorCode errorCode) {
        LocalLog.d(TAG, "error: " + errorCode.toString());
        Toast.makeText(this, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface(this);
        UMShareAPI.get(this).release();
    }
}
