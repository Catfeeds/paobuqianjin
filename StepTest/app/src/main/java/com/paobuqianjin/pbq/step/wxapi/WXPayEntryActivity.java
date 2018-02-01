package com.paobuqianjin.pbq.step.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.PayResultActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = WXPayEntryActivity.class.getSimpleName();
    private final static String PAY_ERROR_CODE = "pay_error_code";
    private final static String PAY_RESULT_ACTION = "android.intent.action.paobuqianjin.PAY_RESULT";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result_activity);
        LocalLog.d(TAG, "onCreate() enter");
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        LocalLog.d(TAG, "onPayFinish, errCode = " + resp.errCode + " type = " + resp.getType());
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
/*            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("微信支付结果" + String.valueOf(resp.errCode));
            builder.show();*/
        }
        LocalLog.d(TAG, "onResp()  finish");
        Presenter.getInstance(this).setPayResultCode(resp.errCode);
        finish();
    }
}