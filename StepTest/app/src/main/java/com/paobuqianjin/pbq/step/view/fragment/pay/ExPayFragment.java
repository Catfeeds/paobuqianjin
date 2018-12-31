package com.paobuqianjin.pbq.step.view.fragment.pay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.WalletPassDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExGoodDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ExOrderNumResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayResultResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Base64Util;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.ForgetPayWordActivity;
import com.paobuqianjin.pbq.step.view.activity.IdentifedSetPassActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExpayActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/12/31.
 */

public class ExPayFragment extends BaseBarStyleTextViewFragment implements BaseBarImageViewFragment.ToolBarListener {
    private final static String TAG = ExpayActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.good_picture)
    CircularImageView goodPicture;
    @Bind(R.id.good_name)
    TextView goodName;
    @Bind(R.id.step_dollar)
    TextView stepDollar;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.pay_wallet)
    LinearLayout payWallet;
    @Bind(R.id.pay_wx)
    LinearLayout payWx;
    @Bind(R.id.confirm_pay)
    Button confirmPay;
    @Bind(R.id.wallet_pay_select)
    ImageView walletPaySelect;
    @Bind(R.id.wechat_pay_select)
    ImageView wechatPaySelect;
    private boolean[] selectPay = new boolean[2];
    private ImageView[] selectIcon = new ImageView[2];
    private IWXAPI msgApi;
    private NormalDialog normalDialog, walletLeakDialog, passWordSetDialog;
    private WalletPassDialog walletPassDialog;
    private float balanceWallet = 0.0f;
    private ExGoodDetailResponse.DataBean goodBean;
    private float payFloat = 0.0f;
    private String addr_id;
    private PayReq req;

    @Override
    protected String title() {
        return "立即支付";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.ex_pay_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp("wx1ed4ccc9a2226a73");//必须先register一次
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        setToolBarListener(this);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        stepDollar = (TextView) viewRoot.findViewById(R.id.step_dollar);
        goodPicture = (CircularImageView) viewRoot.findViewById(R.id.good_picture);
        price = (TextView) viewRoot.findViewById(R.id.price);
        goodName = (TextView) viewRoot.findViewById(R.id.good_name);
        wechatPaySelect = (ImageView) viewRoot.findViewById(R.id.wechat_pay_select);
        walletPaySelect = (ImageView) viewRoot.findViewById(R.id.wallet_pay_select);
        selectIcon[0] = wechatPaySelect;
        selectIcon[1] = walletPaySelect;
        walletPaySelect.setImageDrawable(null);
        payWx = (LinearLayout) viewRoot.findViewById(R.id.pay_wx);
        selectPay[0] = true;
        selectIcon[0].setImageDrawable(getDrawableResource(R.drawable.selected_icon));
        selectPay[0] = true;
        getWalletBalance();
        UpdateUnSelect(0);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            goodBean = (ExGoodDetailResponse.DataBean) intent.getSerializableExtra("good_detail");
            addr_id = intent.getStringExtra("addr_id");
            LocalLog.d(TAG, "addr_id =" + addr_id);
            if (goodBean != null && !TextUtils.isEmpty(addr_id)) {
                SpannableString stepDollarSpan = new SpannableString(String.valueOf(goodBean.getCredit()) + "步币");
                stepDollarSpan.setSpan(new AbsoluteSizeSpan(12, true), String.valueOf(goodBean.getCredit()).length(),
                        (String.valueOf(goodBean.getCredit()) + "步币").length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                stepDollar.setText(stepDollarSpan);
                Presenter.getInstance(getContext()).getPlaceErrorImage(goodPicture, goodBean.getImgs_arr().get(0)
                        , R.drawable.default_head_ico, R.drawable.default_head_ico);
                if (Float.parseFloat(goodBean.getExpress_price()) > 0.0f) {
                    price.setText("快递:" + goodBean.getExpress_price() + "元");
                 /*   price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);*/
                } else {
                    payWx.setVisibility(View.GONE);
                }
                goodName.setText(goodBean.getName());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

    private void getWalletBalance() {
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(getContext()), null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String balance = jsonObj.getString("balance");
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

    @Override
    public void clickLeft() {
        if (normalDialog == null) {
            normalDialog = new NormalDialog(getContext());
            normalDialog.setMessage("是否取消支付？");
            normalDialog.setYesOnclickListener(this.getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    normalDialog.dismiss();
                }
            });
            normalDialog.setNoOnclickListener(this.getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
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

    private boolean canUseWallet(float payFloat) {
        boolean walletAble = true;
        if (balanceWallet < payFloat) {
            LocalLog.d(TAG, "余额不足");
            if (walletLeakDialog == null) {
                walletLeakDialog = new NormalDialog(getContext());
                walletLeakDialog.setMessage("钱包余额不足，请选用其它支付方式");
                walletLeakDialog.setYesOnclickListener(this.getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        walletLeakDialog.dismiss();
                    }
                });
                walletLeakDialog.setNoOnclickListener(this.getString(R.string.cancel_no), new NormalDialog.onNoOnclickListener() {
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

    private void payOrder(String orderNo, String money) {
        int style = getSelect();
        Map<String, String> param = new HashMap<>();
        param.put("order_type", "community");
        param.put("userid", String.valueOf(Presenter.getInstance(getContext()).getId()));
        param.put("total_fee", money);
        param.put("comm_order_id", orderNo);
        param.put("comm_order_id", String.valueOf(1));
        String payment_type = "";
        if (style == 0) {
            payment_type = "wx";
            param.put("payment_type", "wx");
        } else if (style == 1) {
            payment_type = "wallet";
            param.put("payment_type", "wallet");
        }
        final String type = payment_type;
        if (TextUtils.isEmpty(type)) {
            PaoToastUtils.showLongToast(getContext(), "请选择支付方式!");
        }
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlPayOrder, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    if (type.equals("wx")) {
                        WxPayOrderResponse wxPayOrderResponse = new Gson().fromJson(s, WxPayOrderResponse.class);
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
                        Presenter.getInstance(getContext()).setTradeStyle("comm_id");
                        Presenter.getInstance(getContext()).setOutTradeNo(wxPayOrderResponse.getData().getOrder_no());
                        msgApi.registerApp(req.appId);
                        msgApi.sendReq(req);
                    } else if (type.equals("wallet")) {
                        Presenter.getInstance(getContext()).setTradeStyle("comm_id");
                        WalletPayOrderResponse walletPayOrderResponse = new Gson().fromJson(s, WalletPayOrderResponse.class);
                        ((ExpayActivity) getActivity()).showPaySuccessWallet(walletPayOrderResponse);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    private void payEx(final String addr_id, final String comm_id) {
        Map<String, String> param = new HashMap<>();
        param.put("addr_id", addr_id);
        param.put("comm_id", comm_id);
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlExOrderNum, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ExOrderNumResponse orderNumResponse = new Gson().fromJson(s, ExOrderNumResponse.class);
                    payOrder(orderNumResponse.getData().getComm_order_id(), orderNumResponse.getData().getShipping_money());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(getContext(), "开小差了，请稍后再试！");
                }
            }
        });
    }

    private void popPayConfirm() {
        if (walletPassDialog == null) {
            walletPassDialog = new WalletPassDialog(getContext());
            walletPassDialog.setPassEditListener(new WalletPassDialog.PassEditListener() {
                @Override
                public void onPassWord(String pass) {
                    LocalLog.d(TAG, "pass =" + pass);
                    walletPassDialog.dismiss();
                    String base64Pass = Base64Util.makeUidToBase64(pass);
                    Map<String, String> params = new HashMap<>();
                    params.put("paypw", base64Pass);
                    Presenter.getInstance(getActivity()).postPaoBuSimple(NetApi.urlPayPass, params, new PaoCallBack() {
                        @Override
                        protected void onSuc(String s) {
                            try {
                                ErrorCode errorCode = new Gson().fromJson(s, ErrorCode.class);
                                if ("密码正确".equals(errorCode.getMessage())) {
                                    payEx(addr_id, String.valueOf(goodBean.getId()));
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
        if (!walletPassDialog.isShowing()) {
            walletPassDialog.clearPassword();
            walletPassDialog.show();
        }
    }

    private void realPay() {
        int style = getSelect();
        if (style == 1) {
            //TODO 判断是否设置过密码
            Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlPassCheck, null, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    try {
                        JSONObject jsonObj = new JSONObject(s);
                        jsonObj = jsonObj.getJSONObject("data");
                        String status = jsonObj.getString("setpw");
                        if (status.equals("1")) {
                            popPayConfirm();
                        } else if (status.equals("0")) {
                            if (passWordSetDialog == null) {
                                passWordSetDialog = new NormalDialog(getContext());
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
            /*payEx(goodBean.getId());*/
            payEx(addr_id, String.valueOf(goodBean.getId()));
        } else if (style == 2) {
            /*payEx();*/
        } else if (style == 3) {
          /*  payEx();*/
        }
    }

    @OnClick({R.id.pay_wallet, R.id.pay_wx, R.id.confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_wallet:
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
            case R.id.pay_wx:
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
            case R.id.confirm_pay:
                if (getSelect() == -1) {
                    PaoToastUtils.showLongToast(getContext(), "请选择支付方式");
                    return;
                }
                if (getSelect() == 1) {
                    if (!canUseWallet(Float.parseFloat(goodBean.getExpress_price()))) {
                        LocalLog.d(TAG, "余额不足");
                        return;
                    }
                }
                realPay();
                break;
        }
    }


}
