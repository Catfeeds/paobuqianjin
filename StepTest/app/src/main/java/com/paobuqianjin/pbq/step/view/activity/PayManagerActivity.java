package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.OnIdentifyLis;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/6/7.
 */

public class PayManagerActivity extends BaseBarActivity {
    private final static String TAG = PayManagerActivity.class.getSimpleName();
    private final static String ACTION_RESET = "com.paobuqianjin.pbq.step.ACTION_RESET";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.pass_forget_image)
    ImageView passForgetImage;
    @Bind(R.id.pass_forget_des)
    TextView passForgetDes;
    @Bind(R.id.pass_word_forget)
    RelativeLayout passWordForget;
    @Bind(R.id.pass_word_rest_image)
    ImageView passWordRestImage;
    @Bind(R.id.pass_word_rest_des)
    TextView passWordRestDes;
    @Bind(R.id.pass_word_rest)
    RelativeLayout passWordRest;
    @Bind(R.id.address_mail)
    ImageView addressMail;
    @Bind(R.id.phone_address)
    TextView phoneAddress;
    @Bind(R.id.auth_real_person)
    RelativeLayout passWordLayer;
    @Bind(R.id.auth_done)
    TextView authDone;
    @Bind(R.id.go_to)
    ImageView goTo;
    private boolean isIdentify = false;
    private NormalDialog passWordSetDialog;

    @Override
    protected String title() {
        return "支付管理";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_manager_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        passWordLayer = (RelativeLayout) findViewById(R.id.auth_real_person);
        passWordLayer.setEnabled(false);
        getIdentifyStatus();
        authDone = (TextView) findViewById(R.id.auth_done);
    }

    private void getIdentifyStatus() {
        Presenter.getInstance(this).getIdentifyStatu(this, new OnIdentifyLis() {
            @Override
            public void onIdentifed() {
                authDone.setText("已认证");
                passWordLayer.setEnabled(false);
                isIdentify = true;
            }

            @Override
            public void onUnidentify() {
                if (passWordLayer != null) {
                    passWordLayer.setEnabled(true);
                }
                authDone.setText("未认证");
                isIdentify = false;
            }

            @Override
            public void onGetIdentifyStatusError() {
            }
        });
    }

    @OnClick({R.id.pass_word_forget, R.id.pass_word_rest, R.id.auth_real_person})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pass_word_forget:
                if (!isIdentify) {
                    LocalLog.d(TAG, "未认证");
                    PaoToastUtils.showLongToast(getApplicationContext(), "请先去认证");
                    return;
                }
                //TODO 判断是否设置过密码
                Presenter.getInstance(this).getPaoBuSimple(NetApi.urlPassCheck, null, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        try {
                            JSONObject jsonObj = new JSONObject(s);
                            jsonObj = jsonObj.getJSONObject("data");
                            String status = jsonObj.getString("setpw");
                            if (status.equals("1")) {
                                startActivity(ForgetPayWordActivity.class, null);
                                LocalLog.d(TAG, "已经设置密码");
                            } else if (status.equals("0")) {
                                LocalLog.d(TAG, "还没有设置密码");
                                if (passWordSetDialog == null) {
                                    passWordSetDialog = new NormalDialog(PayManagerActivity.this);
                                }
                                passWordSetDialog.setMessage("您还未设置支付密码，去上设置支付密码?");
                                passWordSetDialog.setYesOnclickListener("去设置", new NormalDialog.onYesOnclickListener() {
                                    @Override
                                    public void onYesClick() {
                                        startActivity(IdentifedSetPassActivity.class, null);
                                        if (passWordSetDialog != null)
                                            passWordSetDialog.dismiss();
                                    }
                                });
                                passWordSetDialog.setNoOnclickListener("不设置", new NormalDialog.onNoOnclickListener() {
                                    @Override
                                    public void onNoClick() {
                                        if (passWordSetDialog != null)
                                            passWordSetDialog.dismiss();
                                    }
                                });
                                if (!passWordSetDialog.isShowing())
                                    passWordSetDialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                    }
                });
                break;
            case R.id.pass_word_rest:
                if (!isIdentify) {
                    LocalLog.d(TAG, "未认证");
                    PaoToastUtils.showLongToast(getApplicationContext(), "请先去认证");
                    return;
                }
                //TODO 判断是否设置过密码
                Presenter.getInstance(this).getPaoBuSimple(NetApi.urlPassCheck, null, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        try {
                            JSONObject jsonObj = new JSONObject(s);
                            jsonObj = jsonObj.getJSONObject("data");
                            String status = jsonObj.getString("setpw");
                            if (status.equals("1")) {
                                Intent intentReset = new Intent();
                                intentReset.setClass(getApplicationContext(), PayPassWordActivity.class);
                                intentReset.setAction(ACTION_RESET);
                                startActivity(intentReset);
                            } else if (status.equals("0")) {
                                if (passWordSetDialog == null) {
                                    passWordSetDialog = new NormalDialog(PayManagerActivity.this);
                                }
                                passWordSetDialog.setMessage("您还未设置支付密码，去上设置支付密码?");
                                passWordSetDialog.setYesOnclickListener("去设置", new NormalDialog.onYesOnclickListener() {
                                    @Override
                                    public void onYesClick() {
                                        startActivity(IdentifedSetPassActivity.class, null);
                                        if (passWordSetDialog != null)
                                            passWordSetDialog.dismiss();
                                    }
                                });
                                passWordSetDialog.setNoOnclickListener("不设置", new NormalDialog.onNoOnclickListener() {
                                    @Override
                                    public void onNoClick() {
                                        if (passWordSetDialog != null)
                                            passWordSetDialog.dismiss();
                                    }
                                });
                                if (!passWordSetDialog.isShowing())
                                    passWordSetDialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

                    }
                });
                break;
            case R.id.auth_real_person:
                LocalLog.d(TAG, "未认证");
                Intent intent = getIntent();
                intent.setClass(PayManagerActivity.this, IdentityAuth1Activity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            getIdentifyStatus();
        }
    }
}
