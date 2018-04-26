package com.paobuqianjin.pbq.step.view.fragment.pay;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.VipPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.VipNoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.activity.SelectFriendActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.fragment.circle.CirclePayFragment;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigDecimal;
import java.util.ArrayList;

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
    private final static String ACTION_VIP_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_ACTION";
    private final static String ACTION_VIP_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_ACTION";
    FriendBundleData friendBundleData = null;
    private static final int SELECT_FRIENDS = 0;
    ArrayList<UserFriendResponse.DataBeanX.DataBean> dataBeans = null;
    private LinearLayoutManager layoutManager;
    private String friends;
    private boolean[] selectPay = new boolean[2];
    private ImageView[] selectIcon = new ImageView[2];
    private IWXAPI msgApi;
    private PayStyles payStyles = PayStyles.WxPay;
    private float payFloat = 0;
    private final static float VIP_FLOAT = 1.99f;
    private ProgressDialog dialog;
    private PayReq req;
    private VipPostParam vipPostParam = new VipPostParam();
    private String userids;
    private String spend;
    private boolean isSelfVipPay = false;
    private int number = 0;

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

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);

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
        selectIcon[0] = wechatPaySelect;
        selectIcon[1] = walletPaySelect;
        payMoneyNum = (TextView) viewRoot.findViewById(R.id.pay_money_num);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (ACTION_VIP_SELF.equals(intent.getAction())) {
                LocalLog.d(TAG, "自充VIP");
                setVisibleFriendSelectGone();
                payFloat = VIP_FLOAT;
                isSelfVipPay = true;
                userids = String.valueOf(Presenter.getInstance(getContext()).getId());
                payMoneyNum.setText("￥" + String.valueOf(payFloat));
            } else if (ACTION_VIP_FRIEND.equals(intent.getAction())) {
                LocalLog.d(TAG, "替充VIP");
            }
        }
        if (payStyles.ordinal() == CirclePayFragment.PayStyles.WxPay.ordinal()) {
            LocalLog.d(TAG, "默认微信支付");
            walletPaySelect.setImageDrawable(null);
            selectPay[0] = true;
        }
    }

    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {
                LocalLog.e(TAG, ((ErrorCode) object).getMessage());

            } else if (object instanceof VipNoResponse) {
                LocalLog.d(TAG, ((VipNoResponse) object).toString());
                if (((VipNoResponse) object).getError() == 0) {
                    int style = getSelect();
                    if (style == 0) {
                        dialog = ProgressDialog.show(getContext(), "微信支付",
                                "正在提交订单");
                        PayOrderParam wxPayOrderParam = new PayOrderParam();
                        wxPayOrderParam
                                .setPayment_type("wx")
                                .setOrder_type("vip")
                                .setVip_no(((VipNoResponse) object).getData().getVip_no())
                                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
                        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);

                    } else if (style == 1) {
                        LocalLog.d(TAG, "钱包支付");
                        dialog = ProgressDialog.show(getContext(), "钱包支付",
                                "正在提交订单");
                        PayOrderParam wxPayOrderParam = new PayOrderParam();
                        wxPayOrderParam
                                .setPayment_type("wallet")
                                .setOrder_type("vip")
                                .setVip_no(((VipNoResponse) object).getData().getVip_no())
                                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
                        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                    } else {
                        Toast.makeText(getContext(), "其他支付方式暂时未开通,请选择微信", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

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

                    payFloat = 1.99f * number;
                    BigDecimal bd = new BigDecimal((double) payFloat);
                    bd = bd.setScale(2, 4);
                    payFloat = bd.floatValue();
                    payMoneyNum.setText("￥" + String.valueOf(payFloat));
                    this.friends = friends;
                    userids = friends;
                    LocalLog.d(TAG, friends);
                }
                break;
        }
    }

    @OnClick({R.id.add_task_friend, R.id.choice_ico_span, R.id.bank_ico_span, R.id.bank_pay_span, R.id.confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_task_friend:
                LocalLog.d(TAG, "添加互相关注的好友");
                Intent intent = new Intent();
                intent.putExtra(getActivity().getPackageName(), friendBundleData);
                intent.setClass(getActivity(), SelectFriendActivity.class);
                startActivityForResult(intent, SELECT_FRIENDS);
                break;
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
                vipPostParam.setUserids(userids).setSpend(String.valueOf(payFloat));
                Presenter.getInstance(getContext()).postVipNo(vipPostParam, innerCallBack);
                break;
        }
    }


    @Override
    public void response(WxPayOrderResponse wxPayOrderResponse) {
        LocalLog.d(TAG, "订单结果");
        if (dialog != null) {
            dialog.dismiss();
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
            Presenter.getInstance(getContext()).setTradeStyle("vip");
            msgApi.registerApp(req.appId);
            msgApi.sendReq(req);
        } else if (wxPayOrderResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(WalletPayOrderResponse walletPayOrderResponse) {
        if (dialog != null) {
            dialog.dismiss();
        }
        if (walletPayOrderResponse.getError() == 0) {
            LocalLog.d(TAG, walletPayOrderResponse.toString());
            ((PaoBuPayActivity) getActivity()).showPaySuccessWallet(walletPayOrderResponse);
        } else if (walletPayOrderResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(ErrorCode errorCode) {

    }
}
