package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.WalletPassDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SevenAliPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
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
import com.paobuqianjin.pbq.step.view.activity.IdentifedSetPassActivity;
import com.paobuqianjin.pbq.step.view.activity.IdentityAuth1Activity;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
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
    @Bind(R.id.circle_pay_fg)
    RelativeLayout circlePayFg;
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
    @Bind(R.id.rl_money)
    RelativeLayout rlMoney;
    private WalletPassDialog walletPassDialog;
    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";
    private final static String TASK_NO = "taskno";
    private final static String PAY_ERROR_CODE = "pay_error_code";
    private final static String ORDER_TRADE_NO = "";
    @Bind(R.id.wechat_pay_select)
    ImageView wechatPaySelect;
    @Bind(R.id.wallet_pay_select)
    ImageView walletPaySelect;
    @Bind(R.id.recharge_edit)
    EditText rechargeEdit;
    private String id;
    private String name;
    private String logo;
    private String pay;
    private String payAction;
    private String taskno;
    private ProgressDialog dialog;
    private PayReq req;
    private PayStyles payStyles = PayStyles.WxPay;
    private boolean[] selectPay = new boolean[4];
    private ImageView[] selectIcon = new ImageView[4];
    private IWXAPI msgApi;
    private final static String PAY_ACTION_EDIT = "android.intent.action.PAY_EDIT";
    private final static String PAY_RESULT_ACTION = "android.intent.action.paobuqianjin.PAY_RESULT";
    private final static String PAY_ACTION = "android.intent.action.PAY";
    private final static String PAY_RECHARGE = "com.paobuqian.pbq.step.PAY_RECHARGE.ACTION";
    private final static String PAY_FOR_STYLE = "pay_for_style";
    private NormalDialog normalDialog, walletLeakDialog, passWordSetDialog, identifyDialog;
    public UserInfoResponse.DataBean userInfo;

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

    private String serverMode = "00";
    WebView webview;
    private boolean isSenPayAli = false;
    private float balanceWallet = 0.0f;

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

    public void payRetry() {
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    protected void initView(View viewRoot) {
        bankDes = (TextView) viewRoot.findViewById(R.id.bank_des);
        setToolBarListener(new BaseBarImageViewFragment.ToolBarListener() {
            @Override
            public void clickLeft() {
                if (normalDialog == null) {
                    normalDialog = new NormalDialog(getActivity());
                    if (!TextUtils.isEmpty(payAction)) {
                        LocalLog.d(TAG, "payAction = " + payAction);
                        switch (payAction) {
                            case "task":
                                normalDialog.setMessage("是否取消此次任务的发布");
                                break;
                            case "user":
                                normalDialog.setMessage("确定取消充值到钱包");
                                break;
                            case "circle":
                                normalDialog.setMessage("确定取消充值到圈子");
                                break;
                            case "redpacket":
                                normalDialog.setMessage("是否取消商家红包的发布");
                                break;
                            case "red_map":
                                normalDialog.setMessage("是否取消遍地红包的发布");
                                break;
                            default:
                                break;
                        }
                    }
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
        req = new PayReq();
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra(getContext().getPackageName());
        id = bundle.getString(CIRCLE_ID, "");
        name = bundle.getString(CIRCLE_NAME, "");
        logo = bundle.getString(CIRCLE_LOGO, "");
        pay = bundle.getString(CIRCLE_RECHARGE, "");
        payAction = bundle.getString(PAY_FOR_STYLE, "");
        taskno = bundle.getString(TASK_NO, "");

        LocalLog.d(TAG, "id = " + id + " ,name = "
                + name + " ,logo= " + logo + ", pay= " + pay + " ,payAction = " + payAction + ",taskno = " + taskno);
        wechatPaySelect = (ImageView) viewRoot.findViewById(R.id.wechat_pay_select);
        walletPaySelect = (ImageView) viewRoot.findViewById(R.id.wallet_pay_select);
        yunsPaySelect = (ImageView) viewRoot.findViewById(R.id.yuns_pay_select);
        aliPaySelect = (ImageView) viewRoot.findViewById(R.id.ali_pay_select);
        moneyNum = (TextView) viewRoot.findViewById(R.id.money_num);
        rechargeEdit = (EditText) viewRoot.findViewById(R.id.recharge_edit);
        rechargeEdit.addTextChangedListener(textWatcher);
        if (PAY_RECHARGE.equals(intent.getAction())) {
            LocalLog.d(TAG, PAY_RECHARGE + "ENTER");
            moneyNum.setVisibility(View.GONE);
            rechargeEdit.setVisibility(View.VISIBLE);
        } else if (PAY_ACTION.equals(intent.getAction())) {
            String format = String.format(getString(R.string.pay_money), Float.parseFloat(pay));
            moneyNum.setText(format);
            LocalLog.d(TAG, PAY_ACTION + "ENTER");
        } else if (PAY_ACTION_EDIT.equals(intent.getAction())) {
            String format = String.format(getString(R.string.pay_money), Float.parseFloat(pay));
            moneyNum.setText(format);
            LocalLog.d(TAG, PAY_ACTION_EDIT + "ENTER");
        }

        selectIcon[0] = wechatPaySelect;
        selectIcon[1] = walletPaySelect;
        selectIcon[2] = yunsPaySelect;
        selectIcon[3] = aliPaySelect;
        if (payStyles.ordinal() == PayStyles.WxPay.ordinal()) {
            LocalLog.d(TAG, "默认微信支付");
            walletPaySelect.setImageDrawable(null);
            selectPay[0] = true;
        }

        if ("user".equals(payAction)) {
            LocalLog.d(TAG, "用户充值到钱包");
            bankPaySpan = (RelativeLayout) viewRoot.findViewById(R.id.bank_pay_span);
            bankPaySpan.setVisibility(View.GONE);
        }

        userInfo = Presenter.getInstance(getContext()).getCurrentUser();
        UpdateUnSelect(0);
        getWalletBalance();
        confirmPay = (Button) viewRoot.findViewById(R.id.confirm_pay);
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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String temp = editable.toString();
            int posDot = temp.indexOf(".");
            if (posDot <= 0) return;
            if (temp.length() - posDot - 1 > 2) {
                editable.delete(posDot + 3, posDot + 4);
            }

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (webview != null) {
            webview = null;
        }
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

    @OnClick({R.id.choice_ico_span, R.id.bank_ico_span, R.id.confirm_pay, R.id.yuns_ico_span, R.id.ali_ico_span})
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
                if (TextUtils.isEmpty(rechargeEdit.getText().toString()) && TextUtils.isEmpty(moneyNum.getText())) {
                    PaoToastUtils.showLongToast(getActivity(), "金额不能为空");
                    return;
                }
                if (getSelect() == -1 ) {
                    PaoToastUtils.showLongToast(getActivity(), "请选择支付方式");
                    return;
                }
                if (getSelect() == 1) {
                    float money = 0.0f;
                    if (rechargeEdit.getVisibility() == View.VISIBLE) {
                        try {
                            money = Float.parseFloat(rechargeEdit.getText().toString());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (money <= 0) {
                            Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        money = Float.parseFloat(pay);
                    }
                    if (!canUseWallet(money)) {
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
            case R.id.yuns_ico_span:
                if (selectPay[2]) {
                    LocalLog.d(TAG, "取消微信支付");
                    selectPay[2] = false;
                    selectIcon[2].setImageDrawable(null);
                } else {
                    LocalLog.d(TAG, "选择微信,设置其他为未选中状态");
                    UpdateUnSelect(2);
                    selectIcon[2].setImageDrawable(getDrawableResource(R.drawable.selected_icon));
                    selectPay[2] = true;
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


    //不检查实名或者已经实名过了之后的支付
    private void realPay() {
        int style = getSelect();
        if (style == 0) {
            float money = 0.0f;
            if (rechargeEdit.getVisibility() == View.VISIBLE) {
                try {
                    money = Float.parseFloat(rechargeEdit.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (money <= 0) {
                    Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                money = Float.parseFloat(pay);
            }
/*                    dialog = ProgressDialog.show(getContext(), "微信支付",
                            "正在提交订单");*/

            PayOrderParam wxPayOrderParam = new PayOrderParam();
            if ("circle".equals(payAction)) {
                LocalLog.d(TAG, "圈子支付");
                if (!"".equals(id)) {
                    wxPayOrderParam.setCircleid(Integer.parseInt(id))
                            .setPayment_type("wx")
                            .setOrder_type(payAction)
                            .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                    Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                }
            } else if ("user".equals(payAction)) {
                LocalLog.d(TAG, "用户订单");
                wxPayOrderParam
                        .setPayment_type("wx")
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            } else if ("task".equals(payAction)) {
                LocalLog.d(TAG, "任务订单");
                if (!"".equals(taskno)) {
                    wxPayOrderParam
                            .setPayment_type("wx")
                            .setOrder_type(payAction)
                            .setTaskno(taskno)
                            .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                    Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                }
            } else if ("redpacket".equals(payAction)) {
                LocalLog.d(TAG, "红包订单");
                if (!"".equals(id)) {
                    wxPayOrderParam.setRed_id(Integer.parseInt(id))
                            .setPayment_type("wx")
                            .setOrder_type(payAction)
                            .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                    Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                }
            } else if ("red_map".equals(payAction)) {
                LocalLog.d(TAG, "遍地红包订单 id " + id);
                if (!TextUtils.isEmpty(id)) {
                    wxPayOrderParam.setRed_map_id(id)
                            .setPayment_type("wx")
                            .setOrder_type(payAction)
                            .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                    Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                }
            }

        } else if (style == 1) {
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

        } else if (style == 2) {
            LocalLog.d(TAG, "使用云闪付");
            payYunSanRequest();
        } else if (style == 3) {
            LocalLog.d(TAG, "七分钱支付宝");
            sevenPayAli();
        } else {
            Toast.makeText(getContext(), "请选择一种支付方式", Toast.LENGTH_SHORT).show();
        }

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
                }
            });
        }

        if (!identifyDialog.isShowing())
            identifyDialog.show();

    }

    private void sevenPayAli() {
        LocalLog.d(TAG, "云闪支付");
        float money = 0.00f;
        if (rechargeEdit.getVisibility() == View.VISIBLE) {
            try {
                money = Float.parseFloat(rechargeEdit.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                return;
            }
            if (money <= 0) {
                Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            money = Float.parseFloat(pay);
        }

        PayOrderParam wxPayOrderParam = new PayOrderParam();
        if ("circle".equals(payAction)) {
            LocalLog.d(TAG, "圈子支付");
            if (!"".equals(id)) {
                wxPayOrderParam.setCircleid(Integer.parseInt(id))
                        .setPayment_type("sevenpay")
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("user".equals(payAction)) {
            LocalLog.d(TAG, "用户订单");
            wxPayOrderParam
                    .setPayment_type("sevenpay")
                    .setOrder_type(payAction)
                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
        } else if ("task".equals(payAction)) {
            LocalLog.d(TAG, "任务订单");
            if (!"".equals(taskno)) {
                wxPayOrderParam
                        .setPayment_type("sevenpay")
                        .setOrder_type(payAction)
                        .setDeviceType("1")
                        .setTaskno(taskno)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("redpacket".equals(payAction)) {
            LocalLog.d(TAG, "红包订单");
            if (!"".equals(id)) {
                wxPayOrderParam.setRed_id(Integer.parseInt(id))
                        .setPayment_type("sevenpay")
                        .setDeviceType("1")
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("red_map".equals(payAction)) {
            if (!TextUtils.isEmpty(id)) {
                wxPayOrderParam.setRed_map_id(id)
                        .setPayment_type("sevenpay")
                        .setDeviceType("1")
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        }
    }

    private void payYunSanRequest() {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        requestPermission(permissions);
    }


    private void payForYunSan(String terminalId) {
        LocalLog.d(TAG, "云闪支付");
        float money = 0.00f;
        if (rechargeEdit.getVisibility() == View.VISIBLE) {
            try {
                money = Float.parseFloat(rechargeEdit.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                return;
            }
            if (money <= 0) {
                Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            money = Float.parseFloat(pay);
        }
/*        dialog = ProgressDialog.show(getContext(), "云闪付",
                "正在提交订单");*/
        PayOrderParam wxPayOrderParam = new PayOrderParam();
        if ("circle".equals(payAction)) {
            LocalLog.d(TAG, "圈子支付");
            if (!"".equals(id)) {
                wxPayOrderParam.setCircleid(Integer.parseInt(id))
                        .setPayment_type("yspay")
                        .setDeviceType("1")
                        .setTerminalId(terminalId)
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("user".equals(payAction)) {
            LocalLog.d(TAG, "用户订单");
            wxPayOrderParam
                    .setPayment_type("yspay")
                    .setOrder_type(payAction)
                    .setDeviceType("1")
                    .setTerminalId(terminalId)
                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
        } else if ("task".equals(payAction)) {
            LocalLog.d(TAG, "任务订单");
            if (!"".equals(taskno)) {
                wxPayOrderParam
                        .setPayment_type("yspay")
                        .setOrder_type(payAction)
                        .setDeviceType("1")
                        .setTaskno(taskno)
                        .setTerminalId(terminalId)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("redpacket".equals(payAction)) {
            LocalLog.d(TAG, "红包订单");
            if (!"".equals(id)) {
                wxPayOrderParam.setRed_id(Integer.parseInt(id))
                        .setPayment_type("yspay")
                        .setDeviceType("1")
                        .setTerminalId(terminalId)
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("red_map".equals(payAction)) {
            LocalLog.d(TAG, "红包订单");
            if (!TextUtils.isEmpty(id)) {
                wxPayOrderParam.setRed_map_id(id)
                        .setPayment_type("yspay")
                        .setDeviceType("1")
                        .setTerminalId(terminalId)
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        }
    }

    private void requestPermission(String... permissions) {
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
                            payForYunSan(terminalId);
                        } else {
                            PaoToastUtils.showShortToast(getActivity(), "获取手机IMEI失败无法进行云闪付");
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

    private void payWallet() {
        LocalLog.d(TAG, "钱包支付");
        float money = 0.0f;
        if (rechargeEdit.getVisibility() == View.VISIBLE) {
            try {
                money = Float.parseFloat(rechargeEdit.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                return;
            }
            if (money <= 0) {
                Toast.makeText(getContext(), "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            money = Float.parseFloat(pay);
            if (!canUseWallet(money)) {
                LocalLog.d(TAG, "余额不足");
                return;
            }
        }
/*        dialog = ProgressDialog.show(getContext(), "钱包支付",
                "正在提交订单");*/
        PayOrderParam wxPayOrderParam = new PayOrderParam();
        if ("circle".equals(payAction)) {
            LocalLog.d(TAG, "圈子支付");
            if (!"".equals(id)) {
                wxPayOrderParam.setCircleid(Integer.parseInt(id))
                        .setPayment_type("wallet")
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("user".equals(payAction)) {
            LocalLog.d(TAG, "用户订单");
            wxPayOrderParam
                    .setPayment_type("wallet")
                    .setOrder_type(payAction)
                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
        } else if ("task".equals(payAction)) {
            LocalLog.d(TAG, "任务订单");
            if (!"".equals(taskno)) {
                wxPayOrderParam
                        .setPayment_type("wallet")
                        .setOrder_type(payAction)
                        .setTaskno(taskno)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("redpacket".equals(payAction)) {
            LocalLog.d(TAG, "红包订单");
            if (!"".equals(id)) {
                wxPayOrderParam.setRed_id(Integer.parseInt(id))
                        .setPayment_type("wallet")
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
        } else if ("red_map".equals(payAction)) {
            LocalLog.d(TAG, "遍地红包订单");
            if (!TextUtils.isEmpty(id)) {
                wxPayOrderParam.setRed_map_id(id)
                        .setPayment_type("wallet")
                        .setOrder_type(payAction)
                        .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(money);
                Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
            }
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
                                    payWallet();
                                } else {

                                }
                            } catch (JsonSyntaxException j) {
                                LocalLog.d(TAG, "error data format!");
                            }
                        }

                        @Override
                        protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                            if (errorBean != null && errorBean.getError() != 100) {
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

    @Override
    public void response(WxPayOrderResponse wxPayOrderResponse) {
        LocalLog.d(TAG, "订单结果");
/*        if (dialog != null) {
            dialog.dismiss();
        }*/
        if (!isAdded()) {
            return;
        }
        if (wxPayOrderResponse.getError() == 0) {
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
            Presenter.getInstance(getContext()).setTradeStyle(payAction);
            msgApi.registerApp(req.appId);
            msgApi.sendReq(req);
        } else if (wxPayOrderResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            PaoToastUtils.showShortToast(getContext(), wxPayOrderResponse.getMessage());
        }

    }

    @Override
    public void response(WalletPayOrderResponse walletPayOrderResponse) {
        LocalLog.d(TAG, "WalletPayOrderResponse() enter" + walletPayOrderResponse.toString());
/*        if (dialog != null) {
            dialog.dismiss();
        }*/
        if (!isAdded()) {
            return;
        }
        if (walletPayOrderResponse.getError() == 0) {
            Presenter.getInstance(getContext()).setTradeStyle(payAction);
            ((PaoBuPayActivity) getActivity()).showPaySuccessWallet(walletPayOrderResponse);
        } else if (walletPayOrderResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            PaoToastUtils.showShortToast(getContext(), walletPayOrderResponse.getMessage());
        }
    }


    @Override
    public void response(YsPayOrderResponse ysPayOrderResponse) {
        LocalLog.d(TAG, "云闪付订单");
        if (isAdded()) {
            if (ysPayOrderResponse.getError() == 0) {
                if (ysPayOrderResponse.getData() != null) {
                    String prePayId = ysPayOrderResponse.getData().getPrePayId();
                    Presenter.getInstance(getContext()).setOutTradeNo(ysPayOrderResponse.getData().getOrderNum());
                    Presenter.getInstance(getContext()).setTradeStyle(payAction);
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

            } else if (ysPayOrderResponse.getError() == 100) {
                exitTokenUnfect();
            } else {
                PaoToastUtils.showShortToast(getContext(), ysPayOrderResponse.getMessage());
            }
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
    public void onResume() {
        super.onResume();
        LocalLog.d(TAG, "onResume() payAction =" + payAction);
        if (isSenPayAli) {
            LocalLog.d(TAG, "支付完成，查询结果");
            querySevenPayOrder();
            isSenPayAli = false;
        }
    }

    @Override
    public void response(SevenAliPayResponse sevenAliPayResponse) {
        if (sevenAliPayResponse.getError() == 0 && isAdded()) {
            if (sevenAliPayResponse.getData().getPayUrl() != null) {
                Presenter.getInstance(getContext()).setOutTradeNo(sevenAliPayResponse.getData().getMchOrderId());
                Presenter.getInstance(getContext()).setTradeStyle(payAction);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
/*            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }*/
        }
    }

}
