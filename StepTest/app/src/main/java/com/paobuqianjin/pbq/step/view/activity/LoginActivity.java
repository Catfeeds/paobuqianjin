package com.paobuqianjin.pbq.step.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.SoftKeyboardStateHelper;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
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

public class LoginActivity extends BaseActivity implements SoftKeyboardStateHelper.SoftKeyboardStateListener, LoginSignCallbackInterface {
    private final static String LOGIN_SUCCESS_ACTION = "com.paobuqianjin.pbq.LOGIN_SUCCESS_ACTION";
    private final static String USER_FIT_ACTION_BIND = "com.paobuqianjin.pbq.USER_FIT_ACTION_BIND";
    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    private final static String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.wenxin)
    ImageView wenxin;
    @Bind(R.id.qq)
    ImageView qq;
    @Bind(R.id.sina)
    ImageView sina;
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
    private ProgressDialog dialog;
    private ThirdPartyLoginParam thirdPartyLoginParam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sign_layout);
        ButterKnife.bind(this);
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
        Toast.makeText(LoginActivity.this, "弹出", Toast.LENGTH_SHORT).show();
        newLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        newLayoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin - 100, layoutParams.rightMargin, layoutParams.bottomMargin);

        loginPan.setLayoutParams(newLayoutParams);

    }

    public void onSoftKeyboardClosed() {
        Toast.makeText(LoginActivity.this, "隐藏", Toast.LENGTH_SHORT).show();
        loginPan.setLayoutParams(layoutParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface(this);
        UMShareAPI.get(this).release();
    }

    @Override
    protected void initView() {
        super.initView();

        dialog = new ProgressDialog(this);
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
                    startActivity(LoginForgetPassActivity.class, null, false);
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
    public void response(LoginResponse loginResponse) {
        LocalLog.d(TAG, "手机号登入成功! 去获取用户信息!");
        Presenter.getInstance(this).steLogFlg(true);
        Presenter.getInstance(this).setId(loginResponse.getData().getId());
        Presenter.getInstance(this).setMobile(this, loginResponse.getData().getMobile());
        //startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
        //Presenter.getInstance(this).getUserInfo(loginResponse.getData().getId());
        Presenter.getInstance(this).getLoginRecord(String.valueOf(loginResponse.getData().getId()));
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
                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            } else {
                Intent intent = new Intent();
                intent.setClass(this, UserFitActivity.class);
                intent.setAction(USER_FIT_ACTION_BIND);
                intent.putExtra("userinfo", thirdPartyLoginResponse.getData());
                startActivity(intent);
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
            startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
        }
    }

    @Override
    public void response(SignUserResponse signUserResponse) {
        Toast.makeText(this, signUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
        if (signUserResponse.getError() == 0) {
            LocalLog.d(TAG, "注册成功! 去登陆页");
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

    @OnClick({R.id.wenxin, R.id.qq, R.id.sina})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wenxin:
                LocalLog.d(TAG, "微信三方登录");
                LocalLog.d(TAG, "xxxxxx install-=" + UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN));
                //UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);

                break;
            case R.id.qq:
                LocalLog.d(TAG, "xxxxxx install-=" + UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.QQ));
                UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.QQ, authListener);
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.sina:
                UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
                break;
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
}
