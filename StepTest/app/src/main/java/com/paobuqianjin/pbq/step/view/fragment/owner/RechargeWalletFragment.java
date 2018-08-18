package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SevenAliPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.YsPayOrderResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/6.
 */

public class RechargeWalletFragment extends BaseBarStyleTextViewFragment implements PayInterface {
    private final static String TAG = RechargeWalletFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.pay_style)
    TextView payStyle;
    @Bind(R.id.wechat_pay_icon)
    ImageView wechatPayIcon;
    @Bind(R.id.pay_des)
    TextView payDes;
    @Bind(R.id.wechat_pay_select)
    ImageView wechatPaySelect;
    @Bind(R.id.choice_ico_span)
    RelativeLayout choiceIcoSpan;
    @Bind(R.id.wechat_pay_span)
    RelativeLayout wechatPaySpan;
    @Bind(R.id.pay_num_des)
    TextView payNumDes;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.yuan_flag)
    ImageView yuanFlag;
    @Bind(R.id.money_num)
    EditText moneyNum;
    @Bind(R.id.yuan)
    TextView yuan;
    @Bind(R.id.confirm_pay)
    Button confirmPay;
    private ProgressDialog dialog;
    private PayReq req;
    private IWXAPI msgApi;

    @Override
    protected String title() {
        return "支付";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.recharge_to_wallet_fg;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        msgApi = WXAPIFactory.createWXAPI(getContext(), null);
        msgApi.registerApp("wx1ed4ccc9a2226a73");//必须先register一次
        req = new PayReq();
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick({R.id.wechat_pay_span, R.id.confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wechat_pay_span:
                break;
            case R.id.confirm_pay:
                LocalLog.d(TAG, "支付");
                dialog = ProgressDialog.show(getContext(), "提示" + "支付方式" + String.valueOf(1),
                        "正在提交订单");
                PayOrderParam wxPayOrderParam = new PayOrderParam();
                wxPayOrderParam
                        .setPayment_type("wx")
                        .setOrder_type("user")
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(0.01f);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                break;
        }
    }

    @Override
    public void response(WxPayOrderResponse wxPayOrderResponse) {
        if (!isAdded()) {
            return;
        }
        LocalLog.d(TAG, "订单结果");
        if (dialog != null) {
            dialog.dismiss();
        }
        if (wxPayOrderResponse.getError() == 0) {
            LocalLog.d(TAG, wxPayOrderResponse.toString());
            req.appId = wxPayOrderResponse.getData().getAppid();
            req.partnerId = wxPayOrderResponse.getData().getPartnerid();
            req.prepayId = wxPayOrderResponse.getData().getPrepayid();
            req.packageValue = wxPayOrderResponse.getData().getPackageX();
            req.nonceStr = wxPayOrderResponse.getData().getNoncestr();
            req.sign = wxPayOrderResponse.getData().getSign();
            req.timeStamp = String.valueOf(wxPayOrderResponse.getData().getTimestamp());

            Presenter.getInstance(getContext()).setOutTradeNo(wxPayOrderResponse.getData().getOrder_no());
            msgApi.registerApp(req.appId);
            msgApi.sendReq(req);
        } else if (wxPayOrderResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(WalletPayOrderResponse walletPayOrderResponse) {

    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(YsPayOrderResponse ysPayOrderResponse) {

    }

    @Override
    public void response(SevenAliPayResponse sevenAliPayResponse) {

    }
}
