package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayResultResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.WxPayResultQueryInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.fragment.circle.CirclePayFragment;
import com.paobuqianjin.pbq.step.view.fragment.pay.PayFailedFragment;
import com.paobuqianjin.pbq.step.view.fragment.pay.PaySuccessFragment;
import com.paobuqianjin.pbq.step.view.fragment.pay.PayVipFriendFragment;
import com.paobuqianjin.pbq.step.view.fragment.qrcode.QrCodeFragment;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by pbq on 2018/1/27.
 */

public class PaoBuPayActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener, WxPayResultQueryInterface {
    private final static String TAG = PaoBuPayActivity.class.getSimpleName();
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static String QRCODE_ACTION = "android.intent.action.QRCODE";
    private final static String PAY_RECHARGE = "com.paobuqian.pbq.step.PAY_RECHARGE.ACTION";
    private final static String ACTION_VIP_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_ACTION";
    private final static String ACTION_VIP_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_ACTION";
    private final static String ACTION_VIP_SPONSOR_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_SPONSOR_ACTION";
    private final static String ACTION_VIP_SPONSOR_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_SPONSOR_ACTION";
    private SharedPreferences sharedPreferences;
    private final static String PAY_RESULT_ACTION = "android.intent.action.paobuqianjin.PAY_RESULT";
    private CirclePayFragment circlePayFragment = new CirclePayFragment();
    private QrCodeFragment qrCodeFragment = new QrCodeFragment();
    private PayVipFriendFragment payVipFriendFragment = new PayVipFriendFragment();
    private String currentAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pao_bu_pay_activity_layout);
        sharedPreferences = Presenter.getInstance(this).getSharePreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        Presenter.getInstance(this).attachUiInterface(this);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent == null) {
            LocalLog.d(TAG, "intent is null");
            finish();
            return;
        }

        if ((PAY_ACTION.equals(intent.getAction()) || PAY_RECHARGE.equals(intent.getAction()))) {
            LocalLog.d(TAG, "调启支付");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pay_container, circlePayFragment)
                    .show(circlePayFragment)
                    .commit();
            currentAction = intent.getAction();

        } else if (QRCODE_ACTION.equals(intent.getAction())) {
            LocalLog.d(TAG, "显示圈子二维码");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pay_container, qrCodeFragment)
                    .show(qrCodeFragment)
                    .commit();
            currentAction = intent.getAction();
        } else if (ACTION_VIP_SELF.equals(intent.getAction()) || ACTION_VIP_FRIEND.equals(intent.getAction())
                || ACTION_VIP_SPONSOR_FRIEND.equals(intent.getAction())
                || ACTION_VIP_SPONSOR_SELF.equals(intent.getAction())) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pay_container, payVipFriendFragment)
                    .show(payVipFriendFragment)
                    .commit();
            currentAction = intent.getAction();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        Presenter.getInstance(this).dispatchUiInterface(this);
    }

    @Override
    synchronized public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        LocalLog.d(TAG, "onSharedPreferenceChanged() enter");
        if ("errorCode".equals(s)) {
            if (Presenter.getInstance(this).getPayResultCode() != -3) {
                LocalLog.d(TAG, "errorCode 被设置");
                int resultCode = Presenter.getInstance(this).getPayResultCode();
                String outTradeNo = Presenter.getInstance(this).getOutTradeNo();
                if (resultCode == 0) {

                    LocalLog.d(TAG, "查询订单");
                    Presenter.getInstance(this).getWxPayResultByOrderNo(outTradeNo, "wx");
                } else if (resultCode == -1) {

                } else if (resultCode == -2) {
                    LocalLog.d(TAG, "支付被取消!");
                }
                Presenter.getInstance(this).setPayResultCode(-3);
            }
        }
    }

    @Override
    public void response(Object error) {

    }

    public void showRePayFragment(BaseFragment hideFragment) {
        LocalLog.d(TAG, "重新支付");
        getSupportFragmentManager().beginTransaction()
                .hide(hideFragment)
                .add(R.id.pay_container, circlePayFragment)
                .show(circlePayFragment)
                .commit();
    }

    public void showQrCodeFragment(BaseFragment hideFragment) {
        LocalLog.d(TAG, "显示二维码分享界面");
        if (PAY_RECHARGE.equals(currentAction)) {
            onBackPressed();
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .hide(hideFragment)
                .add(R.id.pay_container, qrCodeFragment)
                .show(qrCodeFragment)
                .commit();
    }

    @Override
    public void response(WxPayResultResponse wxPayResultResponse) {
        LocalLog.d(TAG, wxPayResultResponse.toString());
        if (wxPayResultResponse.getError() == 0) {
            Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
            PaySuccessFragment paySuccessFragment = new PaySuccessFragment();
            paySuccessFragment.setPayNum(Float.parseFloat(wxPayResultResponse.getData().getTotal_fee()));
            getSupportFragmentManager().beginTransaction()
                    .hide(circlePayFragment)
                    .add(R.id.pay_container, paySuccessFragment)
                    .show(paySuccessFragment)
                    .commit();
            setResult(RESULT_OK);
        } else {
            LocalLog.d(TAG, "error  ");
        }
    }

    public void showPaySuccessWallet(WalletPayOrderResponse walletPayOrderResponse) {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        PaySuccessFragment paySuccessFragment = new PaySuccessFragment();
        paySuccessFragment.setPayNum(Float.parseFloat(walletPayOrderResponse.getData().getTotal_fee()));
        getSupportFragmentManager().beginTransaction()
                .hide(circlePayFragment)
                .hide(payVipFriendFragment)
                .add(R.id.pay_container, paySuccessFragment)
                .show(paySuccessFragment)
                .commit();
        setResult(RESULT_OK);
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(this).setId(-1);
            Presenter.getInstance(this).steLogFlg(false);
            Presenter.getInstance(this).setToken(this, "");
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onBackPressed() {
        if (circlePayFragment != null && circlePayFragment.isAdded() && circlePayFragment.isVisible()) {
            circlePayFragment.getmToolBarListener().clickLeft();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
