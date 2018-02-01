package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.pay.PayFailedFragment;
import com.paobuqianjin.pbq.step.view.fragment.pay.PaySuccessFragment;

/**
 * Created by pbq on 2018/1/31.
 */

public class PayResultActivity extends BaseActivity {
    private final static String TAG = PayResultActivity.class.getSimpleName();
    private PayFailedFragment payFailedFragment = new PayFailedFragment();
    private PaySuccessFragment paySuccessFragment = new PaySuccessFragment();
    private final static String PAY_RESULT_ACTION = "android.intent.action.paobuqianjin.PAY_RESULT";
    private final static String PAY_ERROR_CODE = "pay_error_code";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result_activity);

    }

    @Override
    protected void initView() {
        super.initView();
        LocalLog.d(TAG, "initView() enter");
        Intent intent = getIntent();
        if (intent != null && PAY_RESULT_ACTION.equals(intent.getAction())) {
            LocalLog.d(TAG, "显示支付结果");
            int errorCode = intent.getIntExtra(PAY_ERROR_CODE, -3);
            if (errorCode == 0) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.pay_result_container, paySuccessFragment)
                        .show(paySuccessFragment)
                        .commit();
            } else if (errorCode == -1) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.pay_result_container, payFailedFragment)
                        .show(payFailedFragment)
                        .commit();
            } else if (errorCode == -2) {
                LocalLog.d(TAG, "充值取消");
            }
        }
    }
}
