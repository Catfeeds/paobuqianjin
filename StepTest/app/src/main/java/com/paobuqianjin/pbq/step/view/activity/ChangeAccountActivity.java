package com.paobuqianjin.pbq.step.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MultAccountResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.Installation;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.utils.RongYunUserInfoManager;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MultAccountAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by pbq on 2018/7/30.
 */

public class ChangeAccountActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String ADD_ACCOUNT_ACTION = "com.paobuqianjin.pbq.action.ADD_ACCOUNT";
    private final static String TAG = ChangeAccountActivity.class.getSimpleName();
    private final static int ADD_ACCOUNT = 1001;
    private final static int MANAGER_ACCOUNT = 1002;
    private final static int BIND_PHONE_REQUEST = 1003;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.account_des)
    TextView accountDes;
    @Bind(R.id.account_recycler)
    SwipeMenuRecyclerView accountRecycler;
    @Bind(R.id.add_account)
    LinearLayout addAccount;
    private LinearLayoutManager layoutManager;
    private ArrayList<Object> accountArray = new ArrayList<>();
    private final static String BIND_PHONE = "com.paobuqianjin.step.BIND_PHONE";
    private LoginResponse loginResponse;
    private boolean isInGroup = false;

    @Override

    protected String title() {
        return "切换账号";
    }

    @Override
    public Object right() {
        return "管理";
    }


    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        LocalLog.d(TAG, "");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_change_activity_layout);
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this);
        accountRecycler = (SwipeMenuRecyclerView) findViewById(R.id.account_recycler);
        accountRecycler.setLayoutManager(layoutManager);
        accountRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, accountRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                int id = -1;
                String no = "";
                if (position < accountArray.size()) {
                    if (accountArray.get(position) instanceof UserInfoResponse.DataBean) {
                        LocalLog.d(TAG, "不操作");
                        return;
                    } else if (accountArray.get(position) instanceof MultAccountResponse.DataBean) {
                        id = ((MultAccountResponse.DataBean) accountArray.get(position)).getUser_id();
                        no = ((MultAccountResponse.DataBean) accountArray.get(position)).getNo();
                        if (id != Presenter.getInstance(ChangeAccountActivity.this).getId()
                                && !no.equals(Presenter.getInstance(ChangeAccountActivity.this).getNo())) {
                            LocalLog.d(TAG, "切换账号");
                            //TODO 新接口切换账号
                            changeLogin((MultAccountResponse.DataBean) accountArray.get(position));
                        }
                    }
                }
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        }));
        //TODO 检查当前账号是否在某个组
        getDeviceInfo();
        setToolBarListener(new ToolBarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {
                Intent intent = new Intent();
                intent.setClass(ChangeAccountActivity.this, MultAccManangerActivity.class);
                startActivityForResult(intent, MANAGER_ACCOUNT);
            }
        });
    }

    /*@desc
    *@function status 1 查询
    *@param
    *@return 
    */
    private void makeOrQueryGroup(final int status) {
        Map<String, String> param = new HashMap<>();
        param.put("uuid", Installation.readInstallationId(this));
        param.put("status", String.valueOf(status));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlCreateGroup, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (status == 0) {
                    //创建或者获取组APPID
                    try {
                        JSONObject jsonObj = new JSONObject(s);
                        jsonObj = jsonObj.getJSONObject("data");
                        String appId = jsonObj.getString("appid");
                        if (!TextUtils.isEmpty(appId)) {
                            FlagPreference.setAppId(ChangeAccountActivity.this, appId);
                            getAccountList(appId);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (status == 1) {
                    try {
                        JSONObject jsonObj = new JSONObject(s);
                        jsonObj = jsonObj.getJSONObject("data");
                        int iSInGroup = jsonObj.getInt("status");
                        if (iSInGroup == 1) {
                            LocalLog.d(TAG, "当前账号在组");
                            isInGroup = true;
                            //获取AppId
                            makeOrQueryGroup(0);
                        } else if (iSInGroup == 0) {
                            LocalLog.d(TAG, "当前账号不在组");
                            isInGroup = false;
                            getUserInfo();
                        }
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }


    private void getDeviceInfo() {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        requestPermission(permissions);
    }

    private void requestPermission(String... permissions) {
        DefaultRationale mRationale = new DefaultRationale();
        final PermissionSetting mSetting = new PermissionSetting(this);
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        String terminalId = null;
                        terminalId = Installation.readInstallationId(ChangeAccountActivity.this);
                        if (TextUtils.isEmpty(terminalId)) {
                            terminalId = Utils.getIMEI(ChangeAccountActivity.this);
                            Installation.writeInstallationId(ChangeAccountActivity.this, terminalId);
                        }
                        LocalLog.d(TAG, "terminalId = " + terminalId);
                        if (!TextUtils.isEmpty(terminalId)) {
                            makeOrQueryGroup(1);
                        } else {
                            PaoToastUtils.showShortToast(ChangeAccountActivity.this, "授权失败");
                        }
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(ChangeAccountActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    private synchronized void changeLogin(MultAccountResponse.DataBean dataBean) {
        if (!TextUtils.isEmpty(dataBean.getAppid()) && dataBean.getAppid().equals(FlagPreference.getAppId(this))) {
            if (dataBean.getUser_id() != -1) {
                Map<String, String> param = new HashMap<>();
                param.put("appid", dataBean.getAppid());
                param.put("otherid", String.valueOf(dataBean.getUser_id()));

                Presenter.getInstance(this).postPaoBuSimple(NetApi.urlGroupAccChange, param, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        try {
                            LoginResponse loginResponse = new Gson().fromJson(s, LoginResponse.class);
                            ChangeAccountActivity.this.loginResponse = loginResponse;
                            finishLogin2Main(true, loginResponse.getData().getId(), loginResponse.getData().getNo(),
                                    loginResponse.getData().getUser_token(), loginResponse.getData().getChat_token(),
                                    loginResponse.getData().getMobile(), null, loginResponse.getData().getState(),
                                    loginResponse.getData().getStatus());
                            PaoToastUtils.showLongToast(ChangeAccountActivity.this, "切换账号成功");
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                    }
                });
            }
        }
    }

    private void finishLogin2Main(final boolean isLogin, final int id, final String no, final String token,
                                  final String chat_token, final String mobile, final String action, int states, int status) {
        RongIM.getInstance().logout();
        Presenter.getInstance(ChangeAccountActivity.this).setId(id);
        if (!TextUtils.isEmpty(no)) Presenter.getInstance(ChangeAccountActivity.this).setNo(no);
        if (!TextUtils.isEmpty(token))
            Presenter.getInstance(ChangeAccountActivity.this).setToken(ChangeAccountActivity.this, token);
        if (!TextUtils.isEmpty(mobile))
            Presenter.getInstance(ChangeAccountActivity.this).setMobile(ChangeAccountActivity.this, mobile);

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

        if (status != 0) {
            Intent intent = new Intent();
            intent.setAction(BIND_PHONE);
            intent.setClass(ChangeAccountActivity.this, BindWeChatActivity.class);
            startActivityForResult(intent, BIND_PHONE_REQUEST);
        } else {
            setResult(Activity.RESULT_OK);
            Presenter.getInstance(ChangeAccountActivity.this).steLogFlg(true);
            String appId = FlagPreference.getAppId(this);
            LocalLog.d(TAG, "uuuId = " + appId);
            if (!TextUtils.isEmpty(appId))
                getAccountList(appId);
        }

    }

    private void getAccountList(String appId) {
        String url = NetApi.urlGroupList + appId;
        LocalLog.d(TAG, "URL = " + url);
        Presenter.getInstance(this).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    MultAccountResponse multAccountResponse = new Gson().fromJson(s, MultAccountResponse.class);
                    if (multAccountResponse.getData() != null && multAccountResponse.getData().size() > 0) {
                        accountArray.clear();
                        accountArray.addAll((ArrayList) multAccountResponse.getData());
                        MultAccountAdapter multAccountAdapter = new MultAccountAdapter(ChangeAccountActivity.this, accountArray);
                        accountRecycler.setAdapter(multAccountAdapter);
                        accountRecycler.requestLayout();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    //进行过添加账户的操作之后才能获取列表
    private void initLocalEv() {
        LocalLog.d(TAG, "initLocalEv() .....");
        String appId = FlagPreference.getAppId(this);
        if (!TextUtils.isEmpty(appId)) {
            getAccountList(appId);
        } else {
            Intent intent = getIntent();
            if (intent != null) {
                getUserInfo();
            } else {
                LocalLog.d(TAG, "intent is null");
            }
        }
    }

    private void getUserInfo() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(this), null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    if (userInfoResponse.getData() != null) {
                        accountArray.clear();
                        accountArray.add((Object) userInfoResponse.getData());
                        MultAccountAdapter accountAdapter = new MultAccountAdapter(ChangeAccountActivity.this, accountArray);
                        accountRecycler.setAdapter(accountAdapter);
                    } else {

                    }
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean == null) {

                }
            }
        });
    }


    @OnClick(R.id.add_account)
    public void onClick() {
        if (accountArray.size() < 5) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            intent.setAction(ADD_ACCOUNT_ACTION);
            startActivityForResult(intent, ADD_ACCOUNT);
        } else {
            PaoToastUtils.showLongToast(this, "添加账号数量已达上限");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter requestCode = " + requestCode + ",resultCode = " + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            //刷新账号列表
            if (requestCode == ADD_ACCOUNT) {
                String appId = FlagPreference.getAppId(this);
                if (!TextUtils.isEmpty(appId)) {
                    LocalLog.d(TAG, "appid = " + appId);
                    getAccountList(appId);
                }
            } else if (requestCode == MANAGER_ACCOUNT) {
                initLocalEv();
            } else if (requestCode == BIND_PHONE_REQUEST) {
                if (loginResponse != null) {
                    LoginResponse.DataBean bean = loginResponse.getData();
                    int id = bean.getId();
                    String no = bean.getNo();
                    String token = bean.getUser_token();
                    String chat_token = bean.getChat_token();
                    String mobile = bean.getMobile();
                    finishLogin2Main(true, id, no, token, chat_token, mobile, null, 1, 0);
                }
            }
        }
    }
}
