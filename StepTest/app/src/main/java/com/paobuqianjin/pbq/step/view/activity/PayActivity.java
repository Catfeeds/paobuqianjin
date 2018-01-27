package com.paobuqianjin.pbq.step.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CircleOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/27.
 */

public class PayActivity extends BaseActivity implements PayInterface {
    private final static String TAG = PayActivity.class.getSimpleName();
    @Bind(R.id.titles)
    TextView titles;
    @Bind(R.id.bt_submit_order)
    Button btSubmitOrder;
    @Bind(R.id.bt_corfirm)
    Button btCorfirm;
    @Bind(R.id.tv_prepay_id)
    TextView tvPrepayId;
    private ProgressDialog dialog;

    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
    private PayReq req;
    private StringBuffer sb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity_layout);
        ButterKnife.bind(this);
        Presenter.getInstance(this).attachUiInterface(this);
    }

    @Override
    protected void initView() {
        super.initView();
        sb = new StringBuffer();
        req = new PayReq();
        msgApi.registerApp("wx1ed4ccc9a2226a73");
    }

    @OnClick({R.id.titles, R.id.bt_submit_order, R.id.bt_corfirm, R.id.tv_prepay_id})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titles:
                break;
            case R.id.bt_submit_order:
                LocalLog.d(TAG, "统一下单");
                dialog = ProgressDialog.show(PayActivity.this, "提示", "正在提交订单");
                Presenter.getInstance(this).postCircleOrder(new CircleOrderParam());
                break;
            case R.id.bt_corfirm:
                break;
            case R.id.tv_prepay_id:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface(this);
    }

    @Override
    public void response(WxPayOrderResponse wxPayOrderResponse) {
        if (dialog != null) {
            dialog.dismiss();
        }
        LocalLog.d(TAG, wxPayOrderResponse.toString());
        req.appId = wxPayOrderResponse.getData().getAppid();
        req.partnerId = wxPayOrderResponse.getData().getPartnerid();
        req.prepayId = wxPayOrderResponse.getData().getPrepayid();
        req.packageValue = wxPayOrderResponse.getData().getPackageX();
        req.nonceStr = wxPayOrderResponse.getData().getNoncestr();
        req.sign = wxPayOrderResponse.getData().getSign();
        req.timeStamp = String.valueOf(wxPayOrderResponse.getData().getTimestamp());

        msgApi.registerApp(req.appId);
        msgApi.sendReq(req);
    }
}
