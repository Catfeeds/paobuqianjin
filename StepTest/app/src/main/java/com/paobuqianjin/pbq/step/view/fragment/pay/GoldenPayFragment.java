package com.paobuqianjin.pbq.step.view.fragment.pay;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.RedPkgAnimation;
import com.paobuqianjin.pbq.step.customview.WalletPassDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SevenAliPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TwentyOneResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.VipNoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.YsPayOrderResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.OnIdentifyLis;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.ForgetPayWordActivity;
import com.paobuqianjin.pbq.step.view.activity.GoldenSponsoractivity;
import com.paobuqianjin.pbq.step.view.activity.IdentifedSetPassActivity;
import com.paobuqianjin.pbq.step.view.activity.IdentityAuth1Activity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.activity.SelectFriendActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
import com.paobuqianjin.pbq.step.view.fragment.circle.CirclePayFragment;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/7/18.
 */

public class GoldenPayFragment extends BaseBarStyleTextViewFragment implements PayInterface {
    private final static String TAG = GoldenPayFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.golden_times)
    TextView goldenTimes;
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
    @Bind(R.id.ali_pay_icon)
    ImageView aliPayIcon;
    @Bind(R.id.ali_pay_des)
    TextView aliPayDes;
    @Bind(R.id.ali_pay_select)
    ImageView aliPaySelect;
    @Bind(R.id.ali_ico_span)
    RelativeLayout aliIcoSpan;
    @Bind(R.id.ali_pay_span)
    RelativeLayout aliPaySpan;
    @Bind(R.id.bank_pay_icon)
    ImageView bankPayIcon;
    @Bind(R.id.bank_des)
    TextView bankDes;
    @Bind(R.id.wallet_pay_select)
    ImageView walletPaySelect;
    @Bind(R.id.bank_ico_span)
    RelativeLayout bankIcoSpan;
    @Bind(R.id.bank_pay_span)
    RelativeLayout bankPaySpan;
    @Bind(R.id.yuns_pay_icon)
    ImageView yunsPayIcon;
    @Bind(R.id.yuns_des)
    TextView yunsDes;
    @Bind(R.id.yuns_pay_select)
    ImageView yunsPaySelect;
    @Bind(R.id.yuns_ico_span)
    RelativeLayout yunsIcoSpan;
    @Bind(R.id.yuns_pay_span)
    RelativeLayout yunsPaySpan;
    @Bind(R.id.confirm_pay)
    Button confirmPay;
    private NormalDialog normalDialog, walletLeakDialog, passWordSetDialog, identifyDialog;
    private WalletPassDialog walletPassDialog;
    private CirclePayFragment.PayStyles payStyles = CirclePayFragment.PayStyles.WxPay;
    private boolean[] selectPay = new boolean[4];
    private ImageView[] selectIcon = new ImageView[4];
    private IWXAPI msgApi;
    private PayReq req;
    public UserInfoResponse.DataBean userInfo;
    private String serverMode = "00";
    WebView webview;
    private boolean isSenPayAli = false;
    private float balanceWallet = 0.0f;
    private final float GOLDEN_VIP = 365.0f;

    public enum PayStyles {
        WxPay,//微信支付
        AliPay;//支付宝
        private static String payStr = "wxPay";

        public static String getPayStr(CirclePayFragment.PayStyles payStyles) {
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
    protected int getLayoutResId() {
        return R.layout.golden_pay_fg;
    }

    @Override
    protected String title() {
        return "开通商家金牌会员";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LocalLog.d(TAG, "先注册一次");
        msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp("wx1ed4ccc9a2226a73");//必须先register一次
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    protected void initView(View viewRoot) {
        setToolBarListener(new BaseBarImageViewFragment.ToolBarListener() {
            @Override
            public void clickLeft() {
                if (normalDialog == null) {
                    normalDialog = new NormalDialog(getActivity());
                    normalDialog.setMessage("是否取消商家金牌会员充值");
                    normalDialog.setYesOnclickListener(getContext().getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            normalDialog.dismiss();
                            getActivity().finish();
                        }
                    });
                    normalDialog.setNoOnclickListener(getContext().getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            normalDialog.dismiss();
                        }
                    });
                }
                normalDialog.show();
            }

            @Override
            public void clickRight() {

            }
        });

        wechatPaySelect = (ImageView) viewRoot.findViewById(R.id.wechat_pay_select);
        walletPaySelect = (ImageView) viewRoot.findViewById(R.id.wallet_pay_select);
        yunsPaySelect = (ImageView) viewRoot.findViewById(R.id.yuns_pay_select);
        aliPaySelect = (ImageView) viewRoot.findViewById(R.id.ali_pay_select);
        selectIcon[0] = wechatPaySelect;
        selectIcon[1] = walletPaySelect;
        selectIcon[2] = yunsPaySelect;
        selectIcon[3] = aliPaySelect;

        if (payStyles.ordinal() == CirclePayFragment.PayStyles.WxPay.ordinal()) {
            LocalLog.d(TAG, "默认微信支付");
            walletPaySelect.setImageDrawable(null);
            selectPay[0] = true;
        }
        UpdateUnSelect(0);
        getWalletBalance();
    }

    private void getWalletBalance() {
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(getContext()), null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String balance = jsonObj.getString("balance");
                    bankDes.setText("钱包（余额" + balance + ")");
                    balanceWallet = Float.parseFloat(balance);
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean == null) {
                    PaoToastUtils.showLongToast(getContext(), getString(R.string.error_red));
                }
            }
        });
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

    @OnClick({R.id.choice_ico_span, R.id.bank_ico_span, R.id.bank_pay_span, R.id.yuns_ico_span, R.id.confirm_pay, R.id.ali_ico_span})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.choice_ico_span:
                LocalLog.d(TAG, "微信支付");
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
                LocalLog.d(TAG, "钱包支付");
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
            case R.id.yuns_ico_span:
                LocalLog.d(TAG, "云闪付");
                if (selectPay[2]) {
                    LocalLog.d(TAG, "点击选择钱包");
                    selectPay[2] = false;
                    selectIcon[2].setImageDrawable(null);
                } else {
                    LocalLog.d(TAG, "选择钱包,设置其他为未选中状态");
                    UpdateUnSelect(2);
                    selectIcon[2].setImageDrawable(getDrawableResource(R.drawable.selected_icon));
                    selectPay[2] = true;
                }
                break;
            case R.id.confirm_pay:
                LocalLog.d(TAG, "确认支付");
                if (getSelect() == -1) {
                    PaoToastUtils.showLongToast(getActivity(), "请选择支付方式");
                    return;
                }
                if (getSelect() == 1) {
                    if (!canUseWallet(GOLDEN_VIP)) {
                        LocalLog.d(TAG, "余额不足");
                        return;
                    }
                }
                confirmPay.setBackground(getDrawableResource(R.color.color_b8bbbd));
                confirmPay.setEnabled(false);
                confirmPay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isAdded() && confirmPay != null) {
                            confirmPay.setBackground(getDrawableResource(R.drawable.rect_no_corner));
                            confirmPay.setEnabled(true);
                        }
                    }
                }, 15000);
                int style = getSelect();
                if (style == 1) {
                    Presenter.getInstance(getContext()).getIdentifyStatu(getActivity(), new OnIdentifyLis() {
                        @Override
                        public void onIdentifed() {
                            realPay();
                        }

                        @Override
                        public void onUnidentify() {
                            LocalLog.d(TAG, "onUnidentify()");
                            identifyDialog();
                        }

                        @Override
                        public void onGetIdentifyStatusError() {

                        }
                    });
                } else {
                    realPay();
                }
                break;
            case R.id.ali_ico_span:
                if (selectPay[3]) {
                    LocalLog.d(TAG, "取消支付宝支付");
                    selectPay[3] = false;
                    selectIcon[3].setImageDrawable(null);
                } else {
                    LocalLog.d(TAG, "选择微信,设置其他为未选中状态");
                    UpdateUnSelect(3);
                    selectIcon[3].setImageDrawable(getDrawableResource(R.drawable.selected_icon));
                    selectPay[3] = true;
                }
                break;
        }
    }


    private void realPay() {
        int style = getSelect();
        if (style == 1) {
            //TODO 判断是否设置过密码
            Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlPassCheck, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    if (!isAdded()) return;
                    try {
                        JSONObject jsonObj = new JSONObject(s);
                        jsonObj = jsonObj.getJSONObject("data");
                        String status = jsonObj.getString("setpw");
                        if (status.equals("1")) {
                            popPayConfirm();
                        } else if (status.equals("0")) {
                            if (passWordSetDialog == null) {
                                passWordSetDialog = new NormalDialog(getActivity());
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

        } else if (style == 0) {
            payForGoldenvipNo();
        } else if (style == 2) {
            LocalLog.d(TAG, "使用云闪付!");
            payForGoldenvipNo();
        } else if (style == 3) {
            LocalLog.d(TAG, "七分钱支付");
            payForGoldenvipNo();
        }
    }

    private void popPayConfirm() {
        if (walletPassDialog == null) {
            walletPassDialog = new WalletPassDialog(getActivity());
            walletPassDialog.setPassEditListener(new WalletPassDialog.PassEditListener() {
                @Override
                public void onPassWord(String pass) {
                    LocalLog.d(TAG, "pass =" + pass);
                    walletPassDialog.dismiss();
                    String base64Pass = Base64Util.makeUidToBase64(pass);
                    Map<String, String> params = new HashMap<>();
                    params.put("paypw", base64Pass);
                    Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlPayPass, params, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(s, ErrorCode.class);
                                if ("密码正确".equals(errorCode.getMessage())) {
                                    payForGoldenvipNo();
                                } else {

                                }
                            } catch (JsonSyntaxException j) {
                                LocalLog.d(TAG, "error data format!");
                            }
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                            if (errorBean.getError() != 100) {
                                PaoToastUtils.showShortToast(getContext(), errorBean.getMessage());
                            }
                        }
                    });

                }
            });

            walletPassDialog.setForgetPassOnclickListener(new WalletPassDialog.ForgetPassOnclickListener() {
                @Override
                public void onForgetPassClick() {
                    LocalLog.d(TAG, "忘记支付密码");
                    startActivity(ForgetPayWordActivity.class, null);
                }
            });
        }
        if (!walletPassDialog.isShowing() && isAdded()) {
            walletPassDialog.clearPassword();
            walletPassDialog.show();
        }
    }

    private void payForGoldenvipNo() {
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlGoldenVip, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String tradeOrderNo = jsonObj.getString("gvip_no");
                    LocalLog.d(TAG, "tradeOrderNo = " + tradeOrderNo);
                    int style = getSelect();
                    if (!TextUtils.isEmpty(tradeOrderNo)) {
                        if (style == 1) {
                            LocalLog.d(TAG, "钱包支付");
                            PayOrderParam wxPayOrderParam = new PayOrderParam();
                            wxPayOrderParam
                                    .setPayment_type("wallet")
                                    .setOrder_type("gvip")
                                    .setGvip_no(tradeOrderNo)
                                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(GOLDEN_VIP);
                            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                        } else if (style == 2) {
                            payYunSanRequest("gvip", tradeOrderNo);
                        } else if (style == 3) {
                            LocalLog.d(TAG, "七分钱支付");
                            sevenPayAli("gvip", tradeOrderNo);
                        } else if (style == 0) {
                            LocalLog.d(TAG, "微信支付");
                            PayOrderParam wxPayOrderParam = new PayOrderParam();
                            wxPayOrderParam
                                    .setPayment_type("wx")
                                    .setOrder_type("gvip")
                                    .setGvip_no(tradeOrderNo)
                                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(GOLDEN_VIP);
                            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    if (errorBean.getError() != -100) {
                        PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                    }
                }
            }
        });
    }

    private void payForYunSan(String terminalId, String orderType, String cOrVipNo) {
        PayOrderParam wxPayOrderParam = new PayOrderParam();
        wxPayOrderParam
                .setPayment_type("yspay")
                .setOrder_type(orderType)
                .setGvip_no(cOrVipNo)
                .setTerminalId(terminalId)
                .setDeviceType("1")
                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(GOLDEN_VIP);
        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
    }

    private void sevenPayAli(String vipType, String vipTypeNo) {
        PayOrderParam wxPayOrderParam = new PayOrderParam();
        wxPayOrderParam
                .setPayment_type("sevenpay")
                .setOrder_type(vipType)
                .setGvip_no(vipTypeNo)
                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(GOLDEN_VIP);
        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
    }


    private void payYunSanRequest(String orderType, String cOvip) {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        requestPermission(orderType, cOvip, permissions);
    }


    private void requestPermission(final String orderType, final String cOvipNo, String... permissions) {
        DefaultRationale mRationale = new DefaultRationale();
        final PermissionSetting mSetting = new PermissionSetting(getActivity());
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        String terminalId = Utils.getIMEI(getActivity());
                        LocalLog.d(TAG, "terminalId = " + terminalId);
                        if (!TextUtils.isEmpty(terminalId)) {
                            payForYunSan(terminalId, orderType, cOvipNo);
                        } else {
                            PaoToastUtils.showShortToast(getActivity(), "获取手机IMEI失败无法使用云闪付");
                        }
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }


    private boolean canUseWallet(float payFloat) {
        boolean walletAble = true;
        if (balanceWallet < payFloat) {
            LocalLog.d(TAG, "余额不足");
            if (walletLeakDialog == null) {
                walletLeakDialog = new NormalDialog(getActivity());
                walletLeakDialog.setMessage("钱包余额不足，请选用其它支付方式");
                walletLeakDialog.setYesOnclickListener(getContext().getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        walletLeakDialog.dismiss();
                    }
                });
                walletLeakDialog.setNoOnclickListener(getContext().getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        walletLeakDialog.dismiss();
                    }
                });
            }
            if (!walletLeakDialog.isShowing()) {
                walletLeakDialog.show();
            }
            walletAble = false;
        }

        return walletAble;
    }

    private void identifyDialog() {
        if (identifyDialog == null) {
            identifyDialog = new NormalDialog(getActivity());
            identifyDialog.setMessage(getString(R.string.identify_tips));
            identifyDialog.setYesOnclickListener("去认证", new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    identifyDialog.dismiss();
                    Intent intent = new Intent(getActivity(), IdentityAuth1Activity.class);
                    startActivityForResult(intent, 1);
                }
            });
            identifyDialog.setNoOnclickListener("取消支付", new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    identifyDialog.dismiss();
                    confirmPay.setBackground(getDrawableResource(R.drawable.rect_no_corner));
                    confirmPay.setEnabled(true);
                }
            });
        }

        if (!identifyDialog.isShowing())
            identifyDialog.show();

    }

    @Override
    public void response(ErrorCode errorCode) {

    }

    @Override
    public void response(WxPayOrderResponse wxPayOrderResponse) {
        LocalLog.d(TAG, "订单结果");
        if (!isAdded()) {
            return;
        }
        if (wxPayOrderResponse.getError() == 0) {
            req = new PayReq();
            LocalLog.d(TAG, "微信支付返回");
            LocalLog.d(TAG, wxPayOrderResponse.toString());
            req.appId = wxPayOrderResponse.getData().getAppid();
            req.partnerId = wxPayOrderResponse.getData().getPartnerid();
            req.prepayId = wxPayOrderResponse.getData().getPrepayid();
            req.packageValue = wxPayOrderResponse.getData().getPackageX();
            req.nonceStr = wxPayOrderResponse.getData().getNoncestr();
            req.sign = wxPayOrderResponse.getData().getSign();
            req.timeStamp = String.valueOf(wxPayOrderResponse.getData().getTimestamp());

            Presenter.getInstance(getContext()).setOutTradeNo(wxPayOrderResponse.getData().getOrder_no());
            Presenter.getInstance(getContext()).setTradeStyle("gvip");
            msgApi.registerApp(req.appId);
            msgApi.sendReq(req);
        } else if (wxPayOrderResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(YsPayOrderResponse ysPayOrderResponse) {
        if (isAdded()) {
            if (ysPayOrderResponse.getError() == 0) {
                if (ysPayOrderResponse.getData() != null) {
                    String prePayId = ysPayOrderResponse.getData().getPrePayId();
                    Presenter.getInstance(getContext()).setTradeStyle("gvip");
                    if (!TextUtils.isEmpty(prePayId)) {
                        int result = UPPayAssistEx.startPay(getActivity(), null, null, prePayId, serverMode);
                        if (UPPayAssistEx.PLUGIN_VALID == result) {
                            LocalLog.d(TAG, "已经安装控件，并启动控件 ");
                        } else if (UPPayAssistEx.PLUGIN_NOT_FOUND == result) {
                            LocalLog.d(TAG, "尚未安装支付控件，需要先安装支付控件 ");
                            PaoToastUtils.showShortToast(getActivity(), "未安装云闪付控件");
                        }
                    }
                }

            }
        }
    }

    @Override
    public void response(SevenAliPayResponse sevenAliPayResponse) {
        if (sevenAliPayResponse.getError() == 0 && isAdded()) {
            if (sevenAliPayResponse.getData().getPayUrl() != null) {
                Presenter.getInstance(getContext()).setOutTradeNo(sevenAliPayResponse.getData().getMchOrderId());
                Presenter.getInstance(getContext()).setTradeStyle("gvip");
                if (webview == null) {
                    webview = new WebView(getActivity());
                    WebSettings webSettings = webview.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    // 设置可以访问文件
                    webSettings.setAllowFileAccess(true);
                    // 设置支持缩放
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                    // webSettings.setDatabaseEnabled(true);
                    // 使用localStorage则必须打开
                    webSettings.setDomStorageEnabled(true);
                    webSettings.setGeolocationEnabled(true);
                }
                LocalLog.d(TAG, "PAYURL =" + sevenAliPayResponse.getData().getPayUrl());
                if (checkAlipayInstalled(getContext())) {
                    webview.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            LocalLog.d(TAG, "url = " + url);
                            if (url.contains("platformapi/startapp")) {
                                try {
                                    Intent intent;
                                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                                    intent.addCategory("android.intent.category.BROWSABLE");
                                    intent.setComponent(null);
                                    startActivity(intent);
                                    isSenPayAli = true;
                                } catch (Exception e) {

                                }
                            } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                                    && (url.contains("platformapi") && url.contains("startapp"))) {
                                try {
                                    Intent intent;
                                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                                    intent.addCategory("android.intent.category.BROWSABLE");
                                    intent.setComponent(null);
                                    startActivity(intent);
                                    isSenPayAli = true;
                                } catch (Exception e) {

                                }
                            } else {
                                view.loadUrl(url);
                                isSenPayAli = true;
                            }
                            return true;
                        }
                    });
                } else {
                    LocalLog.d(TAG, "无支付宝客户端");
                }
                webview.loadUrl(sevenAliPayResponse.getData().getPayUrl());
            }
        }
    }

    @Override
    public void response(WalletPayOrderResponse walletPayOrderResponse) {
        if (!isAdded()) {
            return;
        }
        if (walletPayOrderResponse.getError() == 0) {
            LocalLog.d(TAG, walletPayOrderResponse.toString());
            Presenter.getInstance(getContext()).setTradeStyle("gvip");
            ((PaoBuPayActivity) getActivity()).showPaySuccessWallet(walletPayOrderResponse);
        } else if (walletPayOrderResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSenPayAli) {
            LocalLog.d(TAG, "支付完成，查询结果");
            querySevenPayOrder();
            isSenPayAli = false;
        }
    }

    private void querySevenPayOrder() {
        Map<String, String> params = new HashMap<>();
        String outTradeNo = Presenter.getInstance(getContext()).getOutTradeNo();
        params.put("order_no", outTradeNo);
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlSevenPayResultOrderNo, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String payResult = jsonObj.getString("total_fee");
                    LocalLog.d(TAG, "payResult = " + payResult);
                    Presenter.getInstance(getContext()).setTradeStyle("gvip");
                    ((PaoBuPayActivity) getActivity()).showPaySuccessSeven(payResult);
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "查单失败！");
                if (errorBean != null && !"交易不成功".equals(errorBean.getMessage())) {
                    ((PaoBuPayActivity) getActivity()).showPaySuccessSeven(null);
                }
            }
        });
    }

    private static boolean checkAlipayInstalled(Context context) {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

}
