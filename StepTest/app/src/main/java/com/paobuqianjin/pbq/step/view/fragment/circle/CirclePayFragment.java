package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
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
 * Created by pbq on 2018/1/30.
 */

public class CirclePayFragment extends BaseBarStyleTextViewFragment implements PayInterface {
    private final static String TAG = CirclePayFragment.class.getSimpleName();
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
    @Bind(R.id.choice_ico_span)
    RelativeLayout choiceIcoSpan;
    @Bind(R.id.wechat_pay_span)
    RelativeLayout wechatPaySpan;
    @Bind(R.id.bank_pay_icon)
    ImageView bankPayIcon;
    @Bind(R.id.bank_des)
    TextView bankDes;
    @Bind(R.id.bank_ico_span)
    RelativeLayout bankIcoSpan;
    @Bind(R.id.bank_pay_span)
    RelativeLayout bankPaySpan;
    @Bind(R.id.pay_num_des)
    TextView payNumDes;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.yuan_flag)
    ImageView yuanFlag;
    @Bind(R.id.money_num)
    TextView moneyNum;
    @Bind(R.id.confirm_pay)
    Button confirmPay;
    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private final static String PAY_ERROR_CODE = "pay_error_code";
    private final static String ORDER_TRADE_NO = "";
    @Bind(R.id.wechat_pay_select)
    ImageView wechatPaySelect;
    @Bind(R.id.wallet_pay_select)
    ImageView walletPaySelect;
    private String id;
    private String name;
    private String logo;
    private String pay;
    private String payAction;
    private ProgressDialog dialog;
    private PayReq req;
    private PayStyles payStyles = PayStyles.WxPay;
    private boolean[] selectPay = new boolean[2];
    private ImageView[] selectIcon = new ImageView[2];
    private IWXAPI msgApi;


    public enum PayStyles {
        WxPay,//微信支付
        AliPay;//支付宝
        private static String payStr = "wxPay";

        public static String getPayStr(PayStyles payStyles) {
            switch (payStyles) {
                case WxPay:
                    payStr = "wxPay";
                    break;
                case AliPay:
                    payStr = "aliPay";
                    break;
            }
            return payStr;
        }
    }

    @Override
    protected String title() {
        return "支付";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_pay_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LocalLog.d(TAG, "先注册一次");
        msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp("wx1ed4ccc9a2226a73");//必须先register一次
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        req = new PayReq();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra(getContext().getPackageName());
        id = bundle.getString(CIRCLE_ID, "");
        name = bundle.getString(CIRCLE_NAME, "");
        logo = bundle.getString(CIRCLE_LOGO, "");
        pay = bundle.getString(CIRCLE_RECHARGE, "");
        payAction = bundle.getString(PAY_FOR_STYLE, "");
        LocalLog.d(TAG, "id = " + id + " name = "
                + name + " logo= " + logo + " pay= " + pay + " payAction = " + payAction);
        wechatPaySelect = (ImageView) viewRoot.findViewById(R.id.wechat_pay_select);
        walletPaySelect = (ImageView) viewRoot.findViewById(R.id.wallet_pay_select);
        moneyNum = (TextView) viewRoot.findViewById(R.id.money_num);
        moneyNum.setText(pay);
        selectIcon[0] = wechatPaySelect;
        selectIcon[1] = walletPaySelect;
        if (payStyles.ordinal() == PayStyles.WxPay.ordinal()) {
            LocalLog.d(TAG, "默认微信支付");
            walletPaySelect.setImageDrawable(null);
            selectPay[0] = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void UpdateUnSelect(int i) {
        for (int j = 0; j < selectPay.length; j++) {
            if (j != i) {
                if (selectPay[j] = true) {
                    selectPay[j] = false;
                    selectIcon[j].setImageDrawable(null);
                }
            }
        }
    }

    private int getSelect() {
        for (int i = 0; i < selectPay.length; i++) {
            if (selectPay[i]) {
                return i;
            }
        }
        LocalLog.d(TAG, "error:没有选择");
        return -1;
    }

    @OnClick({R.id.choice_ico_span, R.id.bank_ico_span, R.id.confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choice_ico_span:
                if (selectPay[0]) {
                    LocalLog.d(TAG, "取消微信支付");
                    selectPay[0] = false;
                    selectIcon[0].setImageDrawable(null);
                } else {
                    LocalLog.d(TAG, "选择微信,设置其他为未选中状态");
                    UpdateUnSelect(0);
                    selectIcon[0].setImageDrawable(getDrawableResource(R.drawable.selected_icon));
                    selectPay[0] = true;
                }
                break;
            case R.id.bank_ico_span:
                LocalLog.d(TAG, "点击选择钱包");
                if (selectPay[1]) {
                    LocalLog.d(TAG, "点击选择钱包");
                    selectPay[1] = false;
                    selectIcon[1].setImageDrawable(null);
                } else {
                    LocalLog.d(TAG, "选择钱包,设置其他为未选中状态");
                    UpdateUnSelect(1);
                    selectIcon[1].setImageDrawable(getDrawableResource(R.drawable.selected_icon));
                    selectPay[1] = true;
                }
                break;
            case R.id.confirm_pay:
                LocalLog.d(TAG, "确认支付");
                int style = getSelect();
                if (style == 0) {
                    dialog = ProgressDialog.show(getContext(), "提示" + "支付方式" + String.valueOf(style),
                            "正在提交订单");
                    PayOrderParam wxPayOrderParam = new PayOrderParam();
                    wxPayOrderParam.setCircleid(Integer.parseInt(id))
                            .setPayment_type("wx")
                            .setOrder_type(payAction)
                            .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(0.01f);
                    Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                } else {
                    Toast.makeText(getContext(), "其他支付方式暂时未开通,请选择微信", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void response(WxPayOrderResponse wxPayOrderResponse) {
        LocalLog.d(TAG, "订单结果");
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

        Presenter.getInstance(getContext()).setOutTradeNo(wxPayOrderResponse.getData().getOrder_no());
        msgApi.registerApp(req.appId);
        msgApi.sendReq(req);
    }
}
