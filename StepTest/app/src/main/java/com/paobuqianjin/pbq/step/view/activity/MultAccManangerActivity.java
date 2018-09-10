package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MultAccountResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Installation;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MultAccountAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.MultManagerAdapter;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/8/1.
 */

public class MultAccManangerActivity extends BaseBarActivity {
    private final static String TAG = MultAccManangerActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.account_list)
    RecyclerView accountList;
    LinearLayoutManager layoutManager;
    private ArrayList<Object> accountArray = new ArrayList<>();
    private NormalDialog normalDialog;
    private ProgressDialog dialog;

    @Override
    protected String title() {
        return "管理账户";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_manager_activity_layout);
        ButterKnife.bind(this);
        setToolBarListener(new ToolBarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {
                onBackPressed();
            }
        });
    }

    @Override
    public Object right() {
        return "完成";
    }

    @Override
    protected void initView() {
        accountList = (RecyclerView) findViewById(R.id.account_list);
        layoutManager = new LinearLayoutManager(this);
        accountList.setLayoutManager(layoutManager);
        dialog = new ProgressDialog(this);
        accountList.addOnItemTouchListener(new RecyclerItemClickListener(this, accountList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                LocalLog.d(TAG, "删除账号!");
                if (position < accountArray.size()) {
                    if (accountArray.get(position) instanceof UserInfoResponse.DataBean) {
                        LocalLog.d(TAG, "不操作");
                        deleteAccount(((UserInfoResponse.DataBean) accountArray.get(position)).getId());
                        return;
                    } else if (accountArray.get(position) instanceof MultAccountResponse.DataBean) {
                        deleteAccount(((MultAccountResponse.DataBean) accountArray.get(position)).getId(), ((MultAccountResponse.DataBean) accountArray.get(position)).getUser_id());
                    }
                }
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        }));
        initLocalEv();
    }

    private void deleteAccount(final int userId) {
        if (userId <= 0) {
            return;
        }
        if (userId == Presenter.getInstance(this).getId()) {
            if (normalDialog == null) {
                normalDialog = new NormalDialog(this);
                normalDialog.setTitle("退出登录");
                normalDialog.setMessage("退出后你无法收到别人的信息\n是否继续？");
                normalDialog.setYesOnclickListener("确定", new NormalDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        normalDialog.dismiss();
                        logOut();
                    }
                });

                normalDialog.setNoOnclickListener("取消", new NormalDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        normalDialog.dismiss();
                    }
                });
            }

            if (!normalDialog.isShowing() && !this.isFinishing())
                normalDialog.show();
        }
    }

    private void deleteAccount(final int id, final int userId) {
        if (id <= 0 || userId <= 0) {
            return;
        }
        if (userId == Presenter.getInstance(this).getId()) {
            if (normalDialog == null) {
                normalDialog = new NormalDialog(this);
                normalDialog.setTitle("退出登录");
                normalDialog.setMessage("退出后你无法收到别人的信息\n是否继续？");
                normalDialog.setYesOnclickListener("确定", new NormalDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        normalDialog.dismiss();
                        logOut();
                    }
                });

                normalDialog.setNoOnclickListener("取消", new NormalDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        normalDialog.dismiss();
                    }
                });
            }
            if (!normalDialog.isShowing() && !this.isFinishing())
                normalDialog.show();
        } else {
            realDelete(id);
        }
    }

    private void realDelete(int id) {
        String url = NetApi.urlDeleteAcc;
        Map<String, String> param = new HashMap<>();
        param.put("ids", String.valueOf(id));
        final String appId = FlagPreference.getAppId(this);
        param.put("appid", appId);
        Presenter.getInstance(this).deletePaoBuSimple(url, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!TextUtils.isEmpty(appId)) {
                    getAccountList(appId);
                }
                setResult(Activity.RESULT_OK);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    private void logOut() {
        final boolean isauthWx = UMShareAPI.get(this).isAuthorize(this, SHARE_MEDIA.WEIXIN);
        final boolean isauthQq = UMShareAPI.get(this).isAuthorize(this, SHARE_MEDIA.QQ);
        if (isauthQq || isauthWx) {
            if (isauthWx) {
                LocalLog.d(TAG, "解除微信授权");
                UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, authListener);
                return;
            }

            if (isauthQq) {
                LocalLog.d(TAG, "解除QQ授权");
                UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, authListener);
                return;
            }
        } else {
            Presenter.getInstance(this).setId(-1);
            Presenter.getInstance(this).steLogFlg(false);
            Presenter.getInstance(this).setToken(this, "");
            Installation.writeInstallationId(MultAccManangerActivity.this, "");

            Intent intent = new Intent(this, MainActivity.class);
            intent.setAction("login_other_phone");
            startActivity(intent);
        }

    }


    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            LocalLog.d(TAG, "解除授权成功");
            Presenter.getInstance(MultAccManangerActivity.this).setId(-1);
            Presenter.getInstance(MultAccManangerActivity.this).steLogFlg(false);
            Presenter.getInstance(MultAccManangerActivity.this).setToken(MultAccManangerActivity.this, "");
            Installation.writeInstallationId(MultAccManangerActivity.this, "");

            Intent intent = new Intent(MultAccManangerActivity.this, MainActivity.class);
            intent.setAction("login_other_phone");
            startActivity(intent);
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(MultAccManangerActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(MultAccManangerActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

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

    private void getAccountList(String appId) {
        LocalLog.d(TAG, "APPID = " + appId);
        if (!TextUtils.isEmpty(appId)) {
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
                            MultManagerAdapter multAccountAdapter = new MultManagerAdapter(MultAccManangerActivity.this, accountArray);
                            accountList.setAdapter(multAccountAdapter);
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

    private void getUserInfo() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(this), null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    if (userInfoResponse.getData() != null) {
                        accountArray.clear();
                        accountArray.add((Object) userInfoResponse.getData());
                        MultManagerAdapter accountAdapter = new MultManagerAdapter(MultAccManangerActivity.this, accountArray);
                        accountList.setAdapter(accountAdapter);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
