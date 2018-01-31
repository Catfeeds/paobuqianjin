package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.CirclePayFragment;
import com.paobuqianjin.pbq.step.view.fragment.qrcode.QrCodeFragment;

/**
 * Created by pbq on 2018/1/27.
 */

public class PaoBuPayActivity extends BaseActivity {
    private final static String TAG = PaoBuPayActivity.class.getSimpleName();
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static String QRCODE_ACTION = "android.intent.action.QRCODE";
    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pao_bu_pay_activity_layout);
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

        if (intent.getAction() != null && intent.getAction().equals(PAY_ACTION)) {
            LocalLog.d(TAG, "调启支付");
            CirclePayFragment circlePayFragment = new CirclePayFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pay_container, circlePayFragment)
                    .show(circlePayFragment)
                    .commit();
        } else if (intent.getAction() != null && intent.getAction().equals(QRCODE_ACTION)) {
            LocalLog.d(TAG, "显示圈子二维码");
            QrCodeFragment qrCodeFragment = new QrCodeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pay_container, qrCodeFragment)
                    .show(qrCodeFragment)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
