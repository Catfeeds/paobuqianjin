package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
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
    private boolean bStartAuth = false;

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

        loginAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String loginStr = loginAccount.getText().toString().trim();
                String pwdStr = loginPwd.getText().toString().trim();
                if (loginStr.length() == 11 && pwdStr.length() >= 6 && pwdStr.length() <= 12) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        loginPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pwdStr = loginPwd.getText().toString().trim();
                String loginStr = loginAccount.getText().toString().trim();
                if (loginStr.length() == 11 && pwdStr.length() >= 6 && pwdStr.length() <= 12) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

                case R.id.forgot_pwd:
                    //TODO
                    LocalLog.d(TAG, "onTabLogin() 忘记密码");
                    startActivity(LoginForgetPassActivity.class, null, false);
                    break;

                default:
                    break;
            }
        }
    }

    public void onTabLogSign(View view) {
        if (view != null) {
            switch (view.getId()) {

                case R.id.btn_login:
                    Presenter.getInstance(this).userLoginByPhoneNumber(collectLoginUserInfo());
                    break;
                case R.id.register:
                    startActivity(RegisterActivity.class, null, false);
                    break;


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
            Toast.makeText(this, "获取验证码成功", Toast.LENGTH_SHORT).show();
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
           /* if (thirdPartyLoginResponse.getData().getMobile() != null && !"".equals(thirdPartyLoginResponse.getData().getMobile())) {
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
                intent.setAction(USER_FIT_ACTION_SETTING);
                intent.putExtra("thirdinfo", thirdPartyLoginResponse.getData());
                startActivity(intent);
                finish();
            }*/
            /*if (thirdPartyLoginResponse.getData().getIs_perfect() == 0) {
                LocalLog.d(TAG, "没有绑定过手机");
                Intent intent = new Intent();
                intent.setClass(this, UserFitActivity.class);
                intent.setAction(USER_FIT_ACTION_SETTING);
                intent.putExtra("thirdinfo", thirdPartyLoginResponse.getData());
                startActivity(intent);
                finish();
            } else {*/
            Presenter.getInstance(this).steLogFlg(true);
            Presenter.getInstance(this).setId(thirdPartyLoginResponse.getData().getId());
            Presenter.getInstance(this).setMobile(this, thirdPartyLoginResponse.getData().getMobile());
            Presenter.getInstance(this).setToken(this, thirdPartyLoginResponse.getData().getUser_token());
            startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
          /*  }*/
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
           /* Intent intent = new Intent();
            intent.setClass(this, UserFitActivity.class);
            intent.setAction(USER_FIT_ACTION_SETTING);
            intent.putExtra("signinfo", signUserResponse.getData());
            startActivity(intent);
            finish();*/
            Presenter.getInstance(this).steLogFlg(true);
            Presenter.getInstance(this).setId(signUserResponse.getData().getUserid());
            Presenter.getInstance(this).setToken(this, signUserResponse.getData().getUser_token());
            startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);

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
            thirdPartyLoginParam.setSide(2);
            LocalLog.d(TAG, "授权成功callback:" + share_media.toString());
            Toast.makeText(LoginActivity.this, "成功：", Toast.LENGTH_LONG).show();
            String temp = "";
            if (share_media.ordinal() == SHARE_MEDIA.WEIXIN.ordinal()) {
                if (!bStartAuth) {
                    UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                    bStartAuth = true;
                    return;
                }
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
                                thirdPartyLoginParam.setSex(1);
                            } else if (map.get(key).equals("女")) {
                                thirdPartyLoginParam.setSex(2);
                            } else {
                                thirdPartyLoginParam.setSex(0);
                            }
                            continue;
                        case "unionid":
                            thirdPartyLoginParam.setUnionid(map.get(key));
                            continue;
                        default:
                            continue;
                    }
                }

            } else if (share_media.ordinal() == SHARE_MEDIA.QQ.ordinal()) {
                if (!bStartAuth) {
                    UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                    bStartAuth = true;
                    return;
                }
                thirdPartyLoginParam.setAction("qq");
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
                                thirdPartyLoginParam.setSex(1);
                            } else if (map.get(key).equals("女")) {
                                thirdPartyLoginParam.setSex(2);
                            } else {
                                thirdPartyLoginParam.setSex(0);
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
                    UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
                    //UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);

                    break;
                case R.id.login_qq:
                    LocalLog.d(TAG, "xxxxxx install-=" + UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.QQ));
                    UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.QQ, authListener);
                    //UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                    break;

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
