package com.paobuqianjin.pbq.step.view.fragment.pay;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.bundle.FriendBundleData;
import com.paobuqianjin.pbq.step.data.bean.gson.param.LoginOutParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PayOrderParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.VipPostParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CvipNoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserFriendResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.VipNoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WalletPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WxPayOrderResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.YsPayOrderResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.presenter.im.PayInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.activity.SelectFriendActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarImageViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.fragment.circle.CirclePayFragment;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigDecimal;
import java.text.ParseException;
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
    private View popCircleOpBar;
    private PopupWindow popupOpWindow;
    TextView cancelText;
    TextView confirmText;
    private TranslateAnimation animationCircleType;
    private final static String ACTION_VIP_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_ACTION";
    private final static String ACTION_VIP_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_ACTION";
    private final static String ACTION_VIP_SPONSOR_SELF = "com.paobuqianjin.pbq.setp.VIP_SELF_SPONSOR_ACTION";
    private final static String ACTION_VIP_SPONSOR_FRIEND = "com.paobuqianjin.pbq.step.VIP_FRIEND_SPONSOR_ACTION";
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
    private final static float VIP_FLOAT = 3.00f;
    private final static float VIP_SPONSOR_FLOAT = 25.00f;
    private ProgressDialog dialog;
    private PayReq req;
    private VipPostParam vipPostParam = new VipPostParam();
    private String userids;
    private String spend;
    private boolean isSelfVipPay = false;
    private int number = 0;
    private final static String ACTION_VIP = "com.paobuqianjin.pbq.step.ACTION_VIP";
    private final static String ACTION_SPONSOR_VIP = "com.paobuqianjin.pbq.step.ACTION_SPONSOR_VIP";
    String action = "";
    private NormalDialog normalDialog, walletLeakDialog;
    public UserInfoResponse.DataBean userInfo;

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
        selectIcon[0] = wechatPaySelect;
        selectIcon[1] = walletPaySelect;
        payMoneyNum = (TextView) viewRoot.findViewById(R.id.pay_money_num);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (ACTION_VIP_SELF.equals(intent.getAction())) {
                LocalLog.d(TAG, "自充个人VIP");
                setVisibleFriendSelectGone();
                payFloat = VIP_FLOAT;
                userids = String.valueOf(Presenter.getInstance(getContext()).getId());
                payMoneyNum.setText("￥" + String.valueOf(payFloat));
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
                payMoneyNum.setText("￥" + String.valueOf(payFloat));
                action = ACTION_VIP_SPONSOR_SELF;
            } else {

            }
        }
        if (payStyles.ordinal() == CirclePayFragment.PayStyles.WxPay.ordinal()) {
            LocalLog.d(TAG, "默认微信支付");
            walletPaySelect.setImageDrawable(null);
            selectPay[0] = true;
        }

        userInfo = Presenter.getInstance(getContext()).getCurrentUser();
    }

    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (object instanceof ErrorCode) {
                LocalLog.e(TAG, ((ErrorCode) object).getMessage());
                Toast.makeText(getContext(), ((ErrorCode) object).getMessage(), Toast.LENGTH_SHORT).show();
                if (dialog != null) {
                    dialog.dismiss();
                }
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
                        if (!canUseWallet(payFloat)) {
                            LocalLog.d(TAG, "余额不足");
                            return;
                        }
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
            } else if (object instanceof CvipNoResponse) {
                LocalLog.d(TAG, ((CvipNoResponse) object).toString());
                if (((CvipNoResponse) object).getError() == 0) {
                    int style = getSelect();
                    if (style == 0) {
                        dialog = ProgressDialog.show(getContext(), "微信支付",
                                "正在提交订单");
                        PayOrderParam wxPayOrderParam = new PayOrderParam();
                        wxPayOrderParam
                                .setPayment_type("wx")
                                .setOrder_type("cvip")
                                .setCvip_no(((CvipNoResponse) object).getData().getCvip_no())
                                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
                        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);

                    } else if (style == 1) {
                        LocalLog.d(TAG, "钱包支付");
                        if (!canUseWallet(payFloat)) {
                            LocalLog.d(TAG, "余额不足");
                            return;
                        }
                        dialog = ProgressDialog.show(getContext(), "钱包支付",
                                "正在提交订单");
                        PayOrderParam wxPayOrderParam = new PayOrderParam();
                        wxPayOrderParam
                                .setPayment_type("wallet")
                                .setOrder_type("cvip")
                                .setCvip_no(((CvipNoResponse) object).getData().getCvip_no())
                                .setUserid(Presenter.getInstance(getContext()).getId()).setTotal_fee(payFloat);
                        Presenter.getInstance(getContext()).postCircleOrder(wxPayOrderParam);
                    } else {
                        Toast.makeText(getContext(), "其他支付方式暂时未开通,请选择微信", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    private boolean canUseWallet(float payFloat) {
        boolean walletAble = true;
        if (userInfo != null && Float.parseFloat(userInfo.getBalance()) < payFloat) {
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
                int style = getSelect();
                if (style == 1) {
                    popPayConfirm(getString(R.string.wallet_pay_confirm));
                } else if (style == 0) {
                    if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                        pay();
                    } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_FRIEND.equals(action)) {
                        paySponsorVip();
                    } else {
                        LocalLog.d(TAG, "Unknown op");
                    }
                }

                break;
        }
    }

    private void popPayConfirm(String string) {
        LocalLog.d(TAG, "popPayConfirm() enter 确认钱包支付");
        popCircleOpBar = View.inflate(getContext(), R.layout.quit_circle_confirm, null);
        TextView textViewTitle = (TextView) popCircleOpBar.findViewById(R.id.quit_title);
        textViewTitle.setText(string);
        popupOpWindow = new PopupWindow(popCircleOpBar, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupOpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupOpWindow = null;
            }
        });

        cancelText = (TextView) popCircleOpBar.findViewById(R.id.cancel_quit_text);
        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "取消支付");
                popupOpWindow.dismiss();
            }
        });
        confirmText = (TextView) popCircleOpBar.findViewById(R.id.confirm_quit_text);
        confirmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupOpWindow.dismiss();
                if (ACTION_VIP_SELF.equals(action) || ACTION_VIP_FRIEND.equals(action)) {
                    pay();
                } else if (ACTION_VIP_SPONSOR_SELF.equals(action) || ACTION_VIP_SPONSOR_FRIEND.equals(action)) {
                    paySponsorVip();
                } else {
                    LocalLog.d(TAG, "Unknown op");
                }
            }
        });


        popupOpWindow.setFocusable(true);
        popupOpWindow.setOutsideTouchable(true);
        popupOpWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new

                AccelerateInterpolator());
        animationCircleType.setDuration(200);

        popupOpWindow.showAtLocation(getActivity().findViewById(R.id.pay_vip_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popCircleOpBar.startAnimation(animationCircleType);
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
        if (dialog != null) {
            dialog.dismiss();
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
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(YsPayOrderResponse ysPayOrderResponse) {

    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() != 100) {
            if (dialog != null) {
                dialog.dismiss();
            }
            Toast.makeText(getContext(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupOpWindow != null) {
            popupOpWindow.dismiss();
            popupOpWindow = null;
        }
    }

}
