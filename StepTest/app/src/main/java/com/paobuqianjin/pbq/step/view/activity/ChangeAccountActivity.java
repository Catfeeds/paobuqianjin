package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.j256.ormlite.stmt.query.In;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MultAccountResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
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
import com.paobuqianjin.pbq.step.view.base.PaoBuApplication;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MultAccountAdapter;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
                            changeLogin((MultAccountResponse.DataBean) accountArray.get(position));
                        }
                    }
                }
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        }));
        initLocalEv();
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


    private synchronized void changeLogin(MultAccountResponse.DataBean dataBean) {
        String uuid = Installation.id(this);
        if (!TextUtils.isEmpty(uuid)) {
            if (!TextUtils.isEmpty(dataBean.getAppid()) && dataBean.getAppid().equals(FlagPreference.getAppId(this))) {
                if (!TextUtils.isEmpty(dataBean.getMtoken())
                        && dataBean.getUser_id() != -1) {
                    Map<String, String> param = new HashMap<>();
                    param.put("uuid", uuid);
                    param.put("appid", dataBean.getAppid());
                    param.put("user_id", String.valueOf(dataBean.getUser_id()));
                    param.put("mtoken", dataBean.getMtoken());

                    Presenter.getInstance(this).postPaoBuSimple(NetApi.urlMultLogin, param, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            try {
                                LoginResponse loginResponse = new Gson().fromJson(s, LoginResponse.class);
                                ChangeAccountActivity.this.loginResponse = loginResponse;
                                finishLogin2Main(true, loginResponse.getData().getId(), loginResponse.getData().getNo(),
                                        loginResponse.getData().getUser_token(), loginResponse.getData().getChat_token(),
                                        loginResponse.getData().getMobile(), null, loginResponse.getData().getState(),
                                        loginResponse.getData().getStatus());
                                PaoToastUtils.showLongToast(ChangeAccountActivity.this,"切换账号成功");
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
    }

    private void finishLogin2Main(final boolean isLogin, final int id, final String no, final String token,
                                  final String chat_token, final String mobile, final String action, int states, int status) {
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
            String uuuId = Installation.id(this);
            LocalLog.d(TAG, "uuuId = " + uuuId);
            if (!TextUtils.isEmpty(uuuId))
                getAccountList(uuuId);
        }

    }

    private void getAccountList(String uuId) {
        String appId = FlagPreference.getAppId(this);
        LocalLog.d(TAG, "APPID = " + appId);
        if (!TextUtils.isEmpty(appId)) {
            String url = NetApi.urlAccountList + "appid=" + appId + "&uuid=" + uuId;
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
    }

    //进行过添加账户的操作之后才能获取列表
    private void initLocalEv() {
        LocalLog.d(TAG, "initLocalEv() .....");
        String uuuId = Installation.id(this);
        if (!TextUtils.isEmpty(uuuId)) {
            getAccountList(uuuId);
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
        }else{
            PaoToastUtils.showLongToast(this,"添加账号数量已达上限");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter requestCode = " + requestCode + ",resultCode = " + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            //刷新账号列表
            if (requestCode == ADD_ACCOUNT) {
                String uuId = Installation.id(this);
                if (!TextUtils.isEmpty(uuId)) {
                    LocalLog.d(TAG, "UUID = " + uuId);
                    getAccountList(uuId);
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
