package com.paobuqianjin.pbq.step.view.fragment.pay;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.WalletPassDialog;
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.VipPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CvipNoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SevenAliPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.VipNoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.YsPayOrderResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
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
import com.paobuqianjin.pbq.step.view.activity.SelectFriendActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.fragment.circle.CirclePayFragment;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/4/26.
 */

public class PayVipFriendFragment extends BaseBarStyleTextViewFragment implements PayInterface {
    private final static String TAG = PayVipFriendFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.pay_money)
    TextView payMoney;
    @Bind(R.id.pay_money_num)
    TextView payMoneyNum;
    @Bind(R.id.pay_vip_pan)
    RelativeLayout payVipPan;
    @Bind(R.id.task_recv_ico)
    ImageView taskRecvIco;
    @Bind(R.id.task_recv)
    TextView taskRecv;
    @Bind(R.id.add_friend_text)
    TextView addFriendText;
    @Bind(R.id.add_recv)
    ImageView addRecv;
    @Bind(R.id.add_task_friend)
    RelativeLayout addTaskFriend;
    @Bind(R.id.add_friend_des)
    TextView addFriendDes;
    @Bind(R.id.recv_recycler)
    RecyclerView recvRecycler;
    @Bind(R.id.add_ico)
    ImageView addIco;
    @Bind(R.id.line)
    ImageView line;
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
    @Bind(R.id.pay_vip_fg)
    RelativeLayout payVipFg;
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
    private WalletPassDialog walletPassDialog;
    private final static String ACTION_VIP_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_ACTION";
    private final static String ACTION_VIP_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_ACTION";
    private final static String ACTION_VIP_SPONSOR_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_SPONSOR_ACTION";
    private final static String ACTION_VIP_SPONSOR_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_SPONSOR_ACTION";
    FriendBundleData friendBundleData = null;
    private static final int SELECT_FRIENDS = 0;
    ArrayList<UserFriendResponse.DataBeanX.DataBean> dataBeans = null;
    private LinearLayoutManager layoutManager;
    private String friends;
    private boolean[] selectPay = new boolean[4];
    private ImageView[] selectIcon = new ImageView[4];
    private IWXAPI msgApi;
    private PayStyles payStyles = PayStyles.WxPay;
    private float payFloat = 0;
    private final static float VIP_FLOAT = 3.00f;
    private final static float VIP_SPONSOR_FLOAT = 25.00f;
    private PayReq req;
    private VipPostParam vipPostParam = new VipPostParam();
    private String userids;
    private String spend;
    private boolean isSelfVipPay = false;
    private int number = 0;
    private final static String ACTION_VIP = "com.paobuqianjin.pbq.step.ACTION_VIP";
    private final static String ACTION_SPONSOR_VIP = "com.paobuqianjin.pbq.step.ACTION_SPONSOR_VIP";
    String action = "";
    private NormalDialog normalDialog, walletLeakDialog, passWordSetDialog, identifyDialog;
    public UserInfoResponse.DataBean userInfo;
    private String serverMode = "00";
    WebView webview;
    private boolean isSenPayAli = false;
    private float balanceWallet = 0.0f;

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
        return R.layout.pay_vip_fg;
    }

    @Override
    protected String title() {
        return "支付";
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    public void payRetry() {
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        setToolBarListener(new BaseBarImageViewFragment.ToolBarListener() {
            @Override
            public void clickLeft() {
                if (normalDialog == null) {
                    normalDialog = new NormalDialog(getActivity());
                    normalDialog.setMessage("是否取消VIP充值");
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
        addTaskFriend = (RelativeLayout) viewRoot.findViewById(R.id.add_task_friend);
        addFriendDes = (TextView) viewRoot.findViewById(R.id.add_friend_des);
        recvRecycler = (RecyclerView) viewRoot.findViewById(R.id.recv_recycler);
        addIco = (ImageView) viewRoot.findViewById(R.id.add_ico);
        line = (ImageView) viewRoot.findViewById(R.id.line);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recvRecycler.setNestedScrollingEnabled(false);
        recvRecycler.setHasFixedSize(true);
        recvRecycler.setLayoutManager(layoutManager);

        wechatPaySelect = (ImageView) viewRoot.findViewById(R.id.wechat_pay_select);
        walletPaySelect = (ImageView) viewRoot.findViewById(R.id.wallet_pay_select);
        yunsPaySelect = (ImageView) viewRoot.findViewById(R.id.yuns_pay_select);
        aliPaySelect = (ImageView) viewRoot.findViewById(R.id.ali_pay_select);
        selectIcon[0] = wechatPaySelect;
        selectIcon[1] = walletPaySelect;
        selectIcon[2] = yunsPaySelect;
        selectIcon[3] = aliPaySelect;
        payMoneyNum = (TextView) viewRoot.findViewById(R.id.pay_money_num);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (ACTION_VIP_SELF.equals(intent.getAction())) {
                LocalLog.d(TAG, "自充个人VIP");
                setVisibleFriendSelectGone();
                payFloat = VIP_FLOAT;
                userids = String.valueOf(Presenter.getInstance(getContext()).getId());
                String moneyFormat = String.format(getString(R.string.month_income), payFloat);
                payMoneyNum.setText(moneyFormat);
                action = ACTION_VIP_SELF;
            } else if (ACTION_VIP_FRIEND.equals(intent.getAction())) {
                LocalLog.d(TAG, "替充个人VIP");
                action = ACTION_VIP_FRIEND;
            } else if (ACTION_VIP_SPONSOR_FRIEND.equals(intent.getAction())) {
                action = ACTION_VIP_SPONSOR_FRIEND;
            } else if (ACTION_VIP_SPONSOR_SELF.equals(intent.getAction())) {
                userids = String.valueOf(Presenter.getInstance(getContext()).getId());
                LocalLog.d(TAG, "自充商家VIP");
                setVisibleFriendSelectGone();
                payFloat = VIP_SPONSOR_FLOAT;
                userids = String.valueOf(Presenter.getInstance(getContext()).getId());
                String moneyFormat = String.format(getString(R.string.month_income), payFloat);
                payMoneyNum.setText(moneyFormat);
                action = ACTION_VIP_SPONSOR_SELF;
            } else {

            }
        }
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

    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {
                LocalLog.e(TAG, ((ErrorCode) object).getMessage());
                Toast.makeText(getContext(), ((ErrorCode) object).getMessage(), Toast.LENGTH_SHORT).show();
            } else if (object instanceof VipNoResponse) {
                LocalLog.d(TAG, ((VipNoResponse) object).toString());
                if (((VipNoResponse) object).getError() == 0) {
                    int style = getSelect();
                    if (style == 0) {
                        PayOrderParam wxPayOrderParam = new PayOrderParam();
                        wxPayOrderParam
                                .setPayment_type("wx")
                                .setOrder_type("vip")
                                .setVip_no(((VipNoResponse) object).getData().getVip_no())
                                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
                        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);

                    } else if (style == 1) {
                        LocalLog.d(TAG, "钱包支付");
                        PayOrderParam wxPayOrderParam = new PayOrderParam();
                        wxPayOrderParam
                                .setPayment_type("wallet")
                                .setOrder_type("vip")
                                .setVip_no(((VipNoResponse) object).getData().getVip_no())
                                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
                        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                    } else if (style == 2) {
                        LocalLog.d(TAG, "云闪付");
                        payYunSanRequest("vip", ((VipNoResponse) object).getData().getVip_no());
                    } else if (style == 3) {
                        LocalLog.d(TAG, "七分钱支付宝");
                        sevenPayAli("vip", ((VipNoResponse) object).getData().getVip_no());
                    } else {
                        Toast.makeText(getContext(), "其他支付方式暂时未开通,请选择微信", Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (object instanceof CvipNoResponse) {
                LocalLog.d(TAG, ((CvipNoResponse) object).toString());
                if (((CvipNoResponse) object).getError() == 0) {
                    int style = getSelect();
                    if (style == 0) {
                        PayOrderParam wxPayOrderParam = new PayOrderParam();
                        wxPayOrderParam
                                .setPayment_type("wx")
                                .setOrder_type("cvip")
                                .setCvip_no(((CvipNoResponse) object).getData().getCvip_no())
                                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
                        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);

                    } else if (style == 1) {
                        LocalLog.d(TAG, "钱包支付");
                        PayOrderParam wxPayOrderParam = new PayOrderParam();
                        wxPayOrderParam
                                .setPayment_type("wallet")
                                .setOrder_type("cvip")
                                .setCvip_no(((CvipNoResponse) object).getData().getCvip_no())
                                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
                        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                    } else if (style == 2) {
                        LocalLog.d(TAG, "云闪付");
                        payYunSanRequest("cvip", ((CvipNoResponse) object).getData().getCvip_no());
                    } else if (style == 3) {
                        LocalLog.d(TAG, "七分钱支付宝");
                        sevenPayAli("cvip", ((CvipNoResponse) object).getData().getCvip_no());
                    } else {
                        Toast.makeText(getContext(), "其他支付方式暂时未开通,请选择微信", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        LocalLog.d(TAG, "onResume() action =" + action);
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
                    if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                        Presenter.getInstance(getContext()).setTradeStyle("vip");
                    } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_SELF.equals(action)) {
                        Presenter.getInstance(getContext()).setTradeStyle("cvip");
                    } else {
                        LocalLog.d(TAG, "Unknown op");
                    }
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

    private void sevenPayAli(String vipType, String vipTypeNo) {
        PayOrderParam wxPayOrderParam = new PayOrderParam();
        if ("vip".equals(vipType)) {
            wxPayOrderParam
                    .setPayment_type("sevenpay")
                    .setOrder_type("vip")
                    .setVip_no(vipTypeNo)
                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
        } else if ("cvip".equals(vipType)) {
            wxPayOrderParam
                    .setPayment_type("sevenpay")
                    .setOrder_type("cvip")
                    .setCvip_no(vipTypeNo)
                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
        }
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

    private void payForYunSan(String terminalId, String orderType, String cOrVipNo) {
        PayOrderParam wxPayOrderParam = new PayOrderParam();
        if ("vip".equals(orderType)) {
            wxPayOrderParam
                    .setPayment_type("yspay")
                    .setOrder_type("vip")
                    .setVip_no(cOrVipNo)
                    .setTerminalId(terminalId)
                    .setDeviceType("1")
                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
        } else if ("cvip".equals(orderType)) {
            wxPayOrderParam
                    .setPayment_type("yspay")
                    .setOrder_type("cvip")
                    .setCvip_no(cOrVipNo)
                    .setTerminalId(terminalId)
                    .setDeviceType("1")
                    .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
            Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
        }
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

    private void setVisibleFriendSelectGone() {
        addTaskFriend.setVisibility(View.GONE);
        addFriendDes.setVisibility(View.GONE);
        recvRecycler.setVisibility(View.GONE);
        addIco.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocalLog.d(TAG, "onActivityResult() enter");
        switch (requestCode) {
            case SELECT_FRIENDS:
                LocalLog.d(TAG, "添加数据成功");
                if (data != null) {
                    friendBundleData = (FriendBundleData) data.getParcelableExtra(getActivity().getPackageName());
                    if (friendBundleData == null) {
                        return;
                    }
                    dataBeans = friendBundleData.getFriendData();
                    recvRecycler.setAdapter(new LikeUserAdapter(getContext(), dataBeans));
                    String friends = "";
                    String sFriendFormat = getContext().getResources().getString(R.string.add_friend);
                    String sFriendNum = String.format(sFriendFormat, dataBeans.size());
                    addFriendDes.setText(sFriendNum);
                    for (int i = 0; i < dataBeans.size(); i++) {
                        if (i == dataBeans.size() - 1) {
                            friends += String.valueOf(dataBeans.get(i).getId());
                        } else {
                            friends += String.valueOf(dataBeans.get(i).getId()) + ",";
                        }

                    }
                    number = dataBeans.size();

                    if (!TextUtils.isEmpty(action)) {
                        if (ACTION_VIP_FRIEND.equals(action)) {
                            payFloat = VIP_FLOAT * number;

                        } else if (ACTION_VIP_SPONSOR_FRIEND.equals(action)) {
                            payFloat = VIP_SPONSOR_FLOAT * number;

                        }
                    } else {
                        LocalLog.d(TAG, "未知操作!");
                        return;
                    }
                    BigDecimal bd = new BigDecimal((double) payFloat);
                    bd = bd.setScale(2, 4);
                    payFloat = bd.floatValue();
                    String moneyFormat = String.format(getString(R.string.month_income), payFloat);
                    payMoneyNum.setText(moneyFormat);
                    this.friends = friends;
                    userids = friends;
                    LocalLog.d(TAG, friends);
                }
                break;
        }
    }

    @OnClick({R.id.add_task_friend, R.id.choice_ico_span, R.id.bank_ico_span, R.id.bank_pay_span, R.id.yuns_ico_span, R.id.confirm_pay, R.id.ali_ico_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_task_friend:
                LocalLog.d(TAG, "添加互相关注的好友");
                Intent intent = new Intent();
                intent.putExtra(getActivity().getPackageName(), friendBundleData);
                intent.setClass(getActivity(), SelectFriendActivity.class);
                if (!TextUtils.isEmpty(action)) {
                    if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                        intent.setAction(ACTION_VIP);
                    } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_FRIEND.equals(action)) {
                        intent.setAction(ACTION_SPONSOR_VIP);
                    } else {
                        LocalLog.d(TAG, "Unknown op");
                    }
                }

                startActivityForResult(intent, SELECT_FRIENDS);
                break;
            case R.id.choice_ico_span:
                if (TextUtils.isEmpty(payMoneyNum.getText().toString())) {
                    return;
                }
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
                if (TextUtils.isEmpty(payMoneyNum.getText().toString())) {
                    return;
                }
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
                if (TextUtils.isEmpty(payMoneyNum.getText().toString())) {
                    return;
                }
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
                if (payFloat < 0) {
                    PaoToastUtils.showLongToast(getActivity(), "金额不能为空");
                    return;
                }
                if (getSelect() == -1) {
                    PaoToastUtils.showLongToast(getActivity(), "请选择支付方式");
                    return;
                }
                if (getSelect() == 1) {
                    if (!canUseWallet(payFloat)) {
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
            if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                pay();
            } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_FRIEND.equals(action)) {
                paySponsorVip();
            } else {
                LocalLog.d(TAG, "Unknown op");
            }
        } else if (style == 2) {
            LocalLog.d(TAG, "使用云闪付!");
            if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                pay();
            } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_FRIEND.equals(action)) {
                paySponsorVip();
            } else {
                LocalLog.d(TAG, "Unknown op");
            }
        } else if (style == 3) {
            LocalLog.d(TAG, "七分钱支付");
            if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                pay();
            } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_FRIEND.equals(action)) {
                paySponsorVip();
            } else {
                LocalLog.d(TAG, "Unknown op");
            }
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
                    confirmPay.setBackground(getDrawableResource(R.drawable.rect_no_corner));
                    confirmPay.setEnabled(true);
                }
            });
        }

        if (!identifyDialog.isShowing())
            identifyDialog.show();

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
                                    if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                                        pay();
                                    } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_FRIEND.equals(action)) {
                                        paySponsorVip();
                                    } else {
                                        LocalLog.d(TAG, "Unknown op");
                                    }
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

    public void pay() {
        vipPostParam.setUserids(userids).setSpend(String.valueOf(payFloat));
        LocalLog.d(TAG, "参数检查");
        if (TextUtils.isEmpty(userids)) {
            Toast.makeText(getContext(), "请至少选择一个好友", Toast.LENGTH_SHORT).show();
            return;
        }
        Presenter.getInstance(getContext()).postVipNo(vipPostParam, innerCallBack);
    }

    public void paySponsorVip() {
        vipPostParam.setUserids(userids).setSpend(String.valueOf(payFloat));
        LocalLog.d(TAG, "参数检查");
        if (TextUtils.isEmpty(userids)) {
            Toast.makeText(getContext(), "请至少选择一个好友", Toast.LENGTH_SHORT).show();
            return;
        }
        Presenter.getInstance(getContext()).postVipSponsorNo(vipPostParam, innerCallBack);
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

            if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                Presenter.getInstance(getContext()).setTradeStyle("vip");
            } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_SELF.equals(action)) {
                Presenter.getInstance(getContext()).setTradeStyle("cvip");
            } else {
                LocalLog.d(TAG, "Unknown op");
            }
            msgApi.registerApp(req.appId);
            msgApi.sendReq(req);
        } else if (wxPayOrderResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(WalletPayOrderResponse walletPayOrderResponse) {
        if (!isAdded()) {
            return;
        }
        if (walletPayOrderResponse.getError() == 0) {
            LocalLog.d(TAG, walletPayOrderResponse.toString());
            if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                Presenter.getInstance(getContext()).setTradeStyle("vip");
            } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_SELF.equals(action)) {
                Presenter.getInstance(getContext()).setTradeStyle("cvip");
            } else {
                LocalLog.d(TAG, "Unknown op");
            }
            ((PaoBuPayActivity) getActivity()).showPaySuccessWallet(walletPayOrderResponse);
        } else if (walletPayOrderResponse.getError() == -100) {
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
                    if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                        Presenter.getInstance(getContext()).setTradeStyle("vip");
                    } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_SELF.equals(action)) {
                        Presenter.getInstance(getContext()).setTradeStyle("cvip");
                    } else {
                        LocalLog.d(TAG, "Unknown op");
                    }
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
                if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                    Presenter.getInstance(getContext()).setTradeStyle("vip");
                } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_SELF.equals(action)) {
                    Presenter.getInstance(getContext()).setTradeStyle("cvip");
                } else {
                    LocalLog.d(TAG, "Unknown op");
                }
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
                    LocalLog.d(TAG, "有支付宝客户端");
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

    private static boolean checkAlipayInstalled(Context context) {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() != 100) {
            Toast.makeText(getContext(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
