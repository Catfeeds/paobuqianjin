package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.stmt.query.In;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CurrentStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.Installation;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.MD5;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.utils.RongYunUserInfoManager;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.today.step.lib.TodayStepManager;
import com.today.step.lib.TodayStepService;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imlib.RongIMClient;

import static com.paobuqianjin.pbq.step.utils.Installation.makeUUid;

/**
 * Created by pbq on 2017/12/7.
 */

public class LoginActivity extends BaseActivity implements LoginSignCallbackInterface {
    private final static String LOGIN_SUCCESS_ACTION = "com.paobuqianjin.pbq.LOGIN_SUCCESS_ACTION";
    private final static String USER_FIT_ACTION_BIND = "com.paobuqianjin.pbq.USER_FIT_ACTION_BIND";
    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    private final static String BIND_PHONE = "com.paobuqianjin.step.BIND_PHONE";
    private final static String ADD_ACCOUNT_ACTION = "com.paobuqianjin.pbq.action.ADD_ACCOUNT";
    private final static String TAG = LoginActivity.class.getSimpleName();
    private final static int REGISTER_REQUEST = 1;
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
    @Bind(R.id.register_eyes)
    ImageView registerEyes;
    @Bind(R.id.go_back_account)
    FrameLayout goBackAccount;
    private boolean bStartAuth = false;

    private boolean showLoginPass = false, showSignPass = false;
    /*默认显示登入界面*/
    private int currentIndex;
    private RelativeLayout.LayoutParams layoutParams, newLayoutParams;

    private String[] userInfo;
    private ThirdPartyLoginParam thirdPartyLoginParam;
    LoginResponse loginResponse;
    private final static String START_STEP_ACTION = "com.paobuqianjin.step.START_STEP_ACTION";
    private ProgressDialog progressDialog;
    private final static int REQUEST_BIND_PHONE = 221;
    private ThirdPartyLoginResponse thirdPartyLoginResponseBean;
    private String action = null;
    private String UUID = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        loginAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String loginStr = loginAccount.getText().toString().trim();
                String pwdStr = loginPwd.getText().toString().trim();
                if (loginStr.length() >= 5 && pwdStr.length() >= 6 && pwdStr.length() <= 16) {
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
                if (loginStr.length() >= 5 && pwdStr.length() >= 6 && pwdStr.length() <= 16) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            if (ADD_ACCOUNT_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, "添加账户");
                action = ADD_ACCOUNT_ACTION;
                register = (TextView) findViewById(R.id.register);
                register.setVisibility(View.GONE);
                goBackAccount = (FrameLayout) findViewById(R.id.go_back_account);
                goBackAccount.setVisibility(View.VISIBLE);
            }
        }
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

    private void addPhoneNo(String[] userInfo) {
        if (userInfo.length >= 2) {
            LocalLog.d(TAG, "addPhoneNo() enter");
            Map<String, String> param = new HashMap<>();
            String md5PassWord = MD5.md5Slat(userInfo[1]);
            param.put("action", "mobile");
            param.put("mobile", userInfo[0]);
            param.put("password", md5PassWord);
            String uuId = Installation.id(this);
            if (TextUtils.isEmpty(uuId)) {
                makeUUid(this);
                uuId = Installation.makeUUid(this);
            }
            param.put("uuid", uuId);
            final String keepUuid = uuId;
            String appid = FlagPreference.getAppId(this);
            if (!TextUtils.isEmpty(appid)) {
                param.put("appid", appid);
            }
            LocalLog.d(TAG, "uuId =" + uuId + "appid =" + appid);
            UUID = keepUuid;
            Presenter.getInstance(this).postPaoBuSimple(NetApi.urlMultAccount, param, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    LocalLog.d(TAG, "s =" + s);
                    try {
                        JSONObject jsonObj = new JSONObject(s);
                        jsonObj = jsonObj.getJSONObject("data");
                        String appid = jsonObj.getString("appid");
                        FlagPreference.setAppId(LoginActivity.this, appid);
                        setResult(Activity.RESULT_OK);
                        Installation.writeInstallationId(LoginActivity.this, keepUuid);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    if (errorBean != null) {
                        PaoToastUtils.showLongToast(LoginActivity.this, errorBean.getMessage());
                        finish();
                    }
                }
            });
        }

    }

    public void onTabLogSign(View view) {
        if (view != null) {
            switch (view.getId()) {

                case R.id.btn_login:
                    if (collectLoginUserInfo()[1] == null) {
                        Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    hideSoftInputView();
                    if (ADD_ACCOUNT_ACTION.equals(action)) {
                        addPhoneNo(collectLoginUserInfo());
                        return;
                    }
                    Presenter.getInstance(this).userLoginByPhoneNumber(collectLoginUserInfo());
                    showProgressDialog("正在使用手机号/跑步钱进号登录");
                    break;
                case R.id.register:
                    /*startActivity(RegisterActivity.class, null, false);*/
                    startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_REQUEST);
                    break;
                case R.id.register_eyes:
                    if (!showLoginPass) {
                        LocalLog.d(TAG, " onTabLogin() 设置显示密码!");
                        loginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showLoginPass = true;
                        registerEyes.setImageDrawable(getResources().getDrawable(R.drawable.forgotpwd_eyes));
                    } else {
                        LocalLog.d(TAG, " onTabLogin() 设置不显示密码!");
                        loginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showLoginPass = false;
                        registerEyes.setImageDrawable(getResources().getDrawable(R.drawable.register_eyes));
                    }
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
        userInfo[2] = PushAgent.getInstance(this).getRegistrationId();
        return userInfo;
    }


    InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {

            } else if (object instanceof CurrentStepResponse) {
                LocalLog.d(TAG, "CurrentStepResponse  " + object.toString());
                Intent intent = new Intent();
                if (((CurrentStepResponse) object).getError() == 0 && ((CurrentStepResponse) object).getData() != null) {
                    intent.putExtra("today_step", ((CurrentStepResponse) object).getData().getStep_number());
                }
                intent.setAction(START_STEP_ACTION);
                intent.setClass(LoginActivity.this, TodayStepService.class);
                TodayStepManager.init(LoginActivity.this.getApplication(), intent);
            }
        }
    };

    private void startStep() {
        boolean netAccess = Presenter.getInstance(this).getCurrentStep(innerCallBack);
        if (!netAccess) {
            LocalLog.d(TAG, "未登录无网络");
            Intent intent = new Intent();
            intent.setAction(START_STEP_ACTION);
            intent.setClass(this.getApplication(), TodayStepService.class);
            TodayStepManager.init(this.getApplication(), intent);
        }
    }

    @Override
    public void response(LoginResponse loginResponse) {
        LocalLog.d(TAG, "手机号登入成功! 去获取用户信息!");
        if (loginResponse.getError() == 0) {
            this.loginResponse = loginResponse;
        /*startStep();
        * 暂时不做数据融合*/
            finishLogin2Main(true, loginResponse.getData().getId(), loginResponse.getData().getNo(), loginResponse.getData().getUser_token(), loginResponse.getData().getChat_token(), loginResponse.getData().getMobile(), LOGIN_SUCCESS_ACTION);
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
            thirdPartyLoginResponseBean = thirdPartyLoginResponse;
            finishLogin2Main(true, thirdPartyLoginResponse.getData().getId(), thirdPartyLoginResponse.getData().getNo(), thirdPartyLoginResponse.getData().getUser_token(), thirdPartyLoginResponse.getData().getChat_token(), thirdPartyLoginResponse.getData().getMobile(), LOGIN_SUCCESS_ACTION);
            /*startStep();
            * 暂时不做数据融合*/
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
                finishLogin2Main(true, loginResponse.getData().getId(), null, loginResponse.getData().getUser_token(), loginResponse.getData().getChat_token(), loginResponse.getData().getMobile(), LOGIN_SUCCESS_ACTION);
            }

        }
    }

    private void finishLogin2Main(final boolean isLogin, final int id, final String no, final String token, final String chat_token, final String mobile, final String action) {
        Presenter.getInstance(LoginActivity.this).setId(id);
        if (!TextUtils.isEmpty(no)) Presenter.getInstance(LoginActivity.this).setNo(no);
        if (!TextUtils.isEmpty(token))
            Presenter.getInstance(LoginActivity.this).setToken(LoginActivity.this, token);
        if (!TextUtils.isEmpty(mobile))
            Presenter.getInstance(LoginActivity.this).setMobile(LoginActivity.this, mobile);

        RongYunUserInfoManager.getInstance().getAllUserInfo2DB();
        RongYunUserInfoManager.getInstance().getAllGroupInfo2DB(false);

        SharedPreferencesUtil.put(Constants.SP_RONGYUN_CHAT_TOKEN, token);
        RongYunChatUtils.getInstance().connect(PaoBuApplication.getApplication(), chat_token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                LocalLog.d(TAG, "");
            }

            @Override
            public void onSuccess(String s) {
//

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });


        //
        if (thirdPartyLoginResponseBean != null && thirdPartyLoginResponseBean.getData().getState() == 2) {
            Intent intent = new Intent();
            intent.setAction(BIND_PHONE);
            intent.setClass(LoginActivity.this, BindWeChatActivity.class);
            startActivityForResult(intent, REQUEST_BIND_PHONE);
        } else {
            Presenter.getInstance(LoginActivity.this).steLogFlg(true);
            startActivity(MainActivity.class, null, true, action);
        }

    }

    private void addAccountThird(ThirdPartyLoginParam thirdPartyLoginParam) {
        LocalLog.d(TAG, "addAccountThird() enter");
        Map<String, String> param = new HashMap<>();
        if ("wx".equals(thirdPartyLoginParam.getAction())) {
            param.put("action", "wechat");
        } else if ("qq".equals(thirdPartyLoginParam.getAction())) {
            param.put("action", "qq");
        }
        if (!TextUtils.isEmpty(thirdPartyLoginParam.getOpenid())) {
            param.put("openid", thirdPartyLoginParam.getOpenid());
        }

        if (!TextUtils.isEmpty(thirdPartyLoginParam.getUnionid())) {
            param.put("unionid", thirdPartyLoginParam.getUnionid());
        }
        String uuId = Installation.id(this);
        if (TextUtils.isEmpty(uuId)) {
            makeUUid(this);
            uuId = Installation.makeUUid(this);
        }
        param.put("uuid", uuId);
        final String keepUuid = uuId;
        LocalLog.d(TAG, "uuId =" + uuId);
        String appId = FlagPreference.getAppId(this);
        if (!TextUtils.isEmpty(appId)) {
            param.put("appid", appId);
        }
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlMultAccount, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                LocalLog.d(TAG, "s =" + s);
                try {
                    thirdPartyLoginResponseBean = new Gson().fromJson(s, ThirdPartyLoginResponse.class);
                    if (thirdPartyLoginResponseBean.getData() != null) {
                        if (thirdPartyLoginResponseBean.getData().getState() == 2) {
                            LocalLog.d(TAG, "账号未绑定手机");
                            Intent intent = new Intent();
                            intent.setAction(BIND_PHONE);
                            intent.setClass(LoginActivity.this, BindWeChatActivity.class);
                            startActivityForResult(intent, REQUEST_BIND_PHONE);
                        } else {
                            if (!TextUtils.isEmpty(thirdPartyLoginResponseBean.getData().getAppid())) {
                                FlagPreference.setAppId(LoginActivity.this, thirdPartyLoginResponseBean.getData().getAppid());
                            }
                            setResult(Activity.RESULT_OK);
                            Installation.writeInstallationId(LoginActivity.this, keepUuid);
                            finish();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(LoginActivity.this, errorBean.getMessage());
                    if (progressDialog != null)
                        progressDialog.dismiss();
                    finish();
                }
            }
        });
    }

    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, "授权开始callback:" + share_media.toString());
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            thirdPartyLoginParam = new ThirdPartyLoginParam();
            thirdPartyLoginParam.setSide(2);
            LocalLog.d(TAG, "授权成功callback:" + share_media.toString());
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
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setOpenid(map.get(key));
                            }
                            continue;
                        case "screen_name":
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setNickname(map.get(key));
                            }
                            continue;
                        case "iconurl":
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setAvatar(map.get(key));
                            }
                            continue;
                        case "province":
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setProvince(map.get(key));
                            }
                            continue;
                        case "city":
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setCity(map.get(key));
                            }
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
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setUnionid(map.get(key));
                            }
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
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setOpenid(map.get(key));
                            }
                            continue;
                        case "screen_name":
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setNickname(map.get(key));
                            }
                            continue;
                        case "iconurl":
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setAvatar(map.get(key));
                            }
                            continue;
                        case "province":
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setProvince(map.get(key));
                            }
                            continue;
                        case "city":
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setCity(map.get(key));
                            }
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
                            if (!TextUtils.isEmpty(map.get(key))) {
                                thirdPartyLoginParam.setUnionid(map.get(key));
                            }
                            continue;
                        default:
                            continue;
                    }
                }
            }

            if (ADD_ACCOUNT_ACTION.equals(action)) {
                addAccountThird(thirdPartyLoginParam);
                return;
            }
            Presenter.getInstance(LoginActivity.this).postThirdPartyLogin(thirdPartyLoginParam);
            LocalLog.d(TAG, temp);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(LoginActivity.this, "失败：" + throwable.getMessage(), Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    };

    @OnClick({R.id.login_weixin, R.id.login_qq, R.id.go_back_account})
    public void onClickTab(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.login_weixin:
                    showProgressDialog("正在使用微信登录");
                    LocalLog.d(TAG, "微信三方登录");
                    LocalLog.d(TAG, "xxxxxx install-=" + UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN));
                    UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
                    //UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);

                    break;
                case R.id.login_qq:
                    showProgressDialog("正在使用QQ登录");
                    LocalLog.d(TAG, "xxxxxx install-=" + UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.QQ));
                    UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.QQ, authListener);
                    //UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                    break;
                case R.id.go_back_account:
                    onBackPressed();
                    break;

            }

        }
    }

    private void showProgressDialog(String msg) {
        progressDialog.setMessage(msg);
        if (!progressDialog.isShowing()) progressDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_BIND_PHONE) {
            if (resultCode == Activity.RESULT_OK) {
                LocalLog.d(TAG, "绑定成功");
                if (!ADD_ACCOUNT_ACTION.equals(action)) {
                    ThirdPartyLoginResponse.DataBean bean = thirdPartyLoginResponseBean.getData();
                    int id = bean.getId();
                    String no = bean.getNo();
                    String token = bean.getUser_token();
                    String chat_token = bean.getChat_token();
                    String mobile = bean.getMobile();
                    thirdPartyLoginResponseBean = null;
                    finishLogin2Main(true, id, no, token, chat_token, mobile, LOGIN_SUCCESS_ACTION);
                } else if (ADD_ACCOUNT_ACTION.equals(action)) {
                    LocalLog.d(TAG, "");
                    if (!TextUtils.isEmpty(thirdPartyLoginResponseBean.getData().getAppid()) && !TextUtils.isEmpty(UUID)) {
                        FlagPreference.setAppId(LoginActivity.this, thirdPartyLoginResponseBean.getData().getAppid());
                        Installation.writeInstallationId(LoginActivity.this, UUID);
                    }
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            } else {
                ToastUtils.showShortToast(this, "绑定取消");
                progressDialog.dismiss();
                thirdPartyLoginResponseBean = null;
            }

        } else if (requestCode == REGISTER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                LocalLog.d(TAG, "注册成功");
                int r_id = data.getIntExtra("r_id", 0);
                String r_no = data.getStringExtra("r_no");
                String r_user_token = data.getStringExtra("r_user_token");
                String r_chat_token = data.getStringExtra("r_chat_token");
                finishLogin2Main(true, r_id, r_no, r_user_token, r_chat_token, null, LOGIN_SUCCESS_ACTION);
//                Presenter.getInstance(this).steLogFlg(true);
//                startActivity(MainActivity.class, null, true, LOGIN_SUCCESS_ACTION);
            } else {
                LocalLog.d(TAG, "取消注册或者注册失败!");
            }
        }
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
        progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog.isShowing()) progressDialog.dismiss();
        Presenter.getInstance(this).dispatchUiInterface(this);
        UMShareAPI.get(this).release();
    }
}
