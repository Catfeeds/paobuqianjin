package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.OwnerUiInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.activity.GetMoreMoneyActivity;
import com.paobuqianjin.pbq.step.view.activity.InoutcomDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.InviteDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.MessageActivity;
import com.paobuqianjin.pbq.step.view.activity.MyDynamicActivity;
import com.paobuqianjin.pbq.step.view.activity.MyFriendActivity;
import com.paobuqianjin.pbq.step.view.activity.MyWalletActivity;
import com.paobuqianjin.pbq.step.view.activity.OwnerCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeMakeActivity;
import com.paobuqianjin.pbq.step.view.activity.SettingActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorCollectActivity;
import com.paobuqianjin.pbq.step.view.activity.StepHistoryActivity;
import com.paobuqianjin.pbq.step.view.activity.SuggestionActivity;
import com.paobuqianjin.pbq.step.view.activity.TransferActivity;
import com.paobuqianjin.pbq.step.view.activity.UserInfoSettingActivity;
import com.paobuqianjin.pbq.step.view.activity.VipActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExReleaseHisActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExchangeInActivity;
import com.paobuqianjin.pbq.step.view.activity.exchange.ExchangeOutActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/12/26.
 */

public class OwnerCenterFragment extends BaseFragment {
    private final static String TAG = OwnerCenterFragment.class.getSimpleName();
    @Bind(R.id.qrcode)
    ImageView qrcode;
    @Bind(R.id.qr_linear)
    LinearLayout qrLinear;
    @Bind(R.id.setting_icon)
    ImageView settingIcon;
    @Bind(R.id.setting_span)
    LinearLayout settingSpan;
    @Bind(R.id.qrcode_rel)
    LinearLayout qrcodeRel;
    @Bind(R.id.head_icon)
    CircleImageView headIcon;
    @Bind(R.id.sex)
    ImageView sex;
    @Bind(R.id.user_icon)
    RelativeLayout userIcon;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.golden_sponsor)
    ImageView goldenSponsor;
    @Bind(R.id.user_id)
    TextView userId;
    @Bind(R.id.crash_button)
    Button crashButton;
    @Bind(R.id.user_span)
    LinearLayout userSpan;
    @Bind(R.id.wallet_span_a)
    LinearLayout walletSpanA;
    @Bind(R.id.wallet_span_b)
    LinearLayout walletSpanB;
    @Bind(R.id.friend)
    ImageView friend;
    @Bind(R.id.friend_span)
    LinearLayout friendSpan;
    @Bind(R.id.vip_icon)
    ImageView vipIcon;
    @Bind(R.id.vip_desc)
    TextView vipDesc;
    @Bind(R.id.vip_span)
    LinearLayout vipSpan;
    @Bind(R.id.circle)
    ImageView circle;
    @Bind(R.id.circle_rel)
    LinearLayout circleRel;
    @Bind(R.id.dan_icon)
    ImageView danIcon;
    @Bind(R.id.dan_desc)
    TextView danDesc;
    @Bind(R.id.dynamic_span)
    LinearLayout dynamicSpan;
    @Bind(R.id.invite_people_ico)
    ImageView invitePeopleIco;
    @Bind(R.id.invite_people_des)
    TextView invitePeopleDes;
    @Bind(R.id.invite_people_span)
    LinearLayout invitePeopleSpan;
    @Bind(R.id.money_icon)
    ImageView moneyIcon;
    @Bind(R.id.step_dollar_desc)
    TextView stepDollarDesc;
    @Bind(R.id.money_span)
    LinearLayout moneySpan;
    @Bind(R.id.his_icon)
    ImageView hisIcon;
    @Bind(R.id.wallet_desc)
    TextView walletDesc;
    @Bind(R.id.his_span)
    LinearLayout hisSpan;
    @Bind(R.id.gonggao_icon)
    ImageView gonggaoIcon;
    @Bind(R.id.gonggao_desc)
    TextView gonggaoDesc;
    @Bind(R.id.gonggao_span)
    LinearLayout gonggaoSpan;
    @Bind(R.id.reward_icon)
    ImageView rewardIcon;
    @Bind(R.id.reward_desc)
    TextView rewardDesc;
    @Bind(R.id.reward_span)
    LinearLayout rewardSpan;
    @Bind(R.id.collect_icon)
    ImageView collectIcon;
    @Bind(R.id.collect_desc)
    TextView collectDesc;
    @Bind(R.id.collect_span)
    LinearLayout collectSpan;
    @Bind(R.id.suggestion_icon)
    ImageView suggestionIcon;
    @Bind(R.id.suggestion_desc)
    TextView suggestionDesc;
    @Bind(R.id.suggestion_span)
    LinearLayout suggestionSpan;
    String urlIcon = "";
    @Bind(R.id.wallet_money)
    TextView walletMoney;
    @Bind(R.id.wallet_step)
    TextView walletStep;
    @Bind(R.id.wallet_detail_button)
    TextView walletDetailButton;
    @Bind(R.id.ex_release_num)
    TextView exReleaseNum;
    @Bind(R.id.ex_release)
    LinearLayout exRelease;
    @Bind(R.id.ex_sale_out)
    TextView exSaleOut;
    @Bind(R.id.ex_sale)
    LinearLayout exSale;
    @Bind(R.id.ex_buy_num)
    TextView exBuyNum;
    @Bind(R.id.ex_buy_in)
    LinearLayout exBuyIn;
    @Bind(R.id.ex_save_num)
    TextView exSaveNum;
    @Bind(R.id.ex_save)
    LinearLayout exSave;
    private String userAvatar;
    ImageView friends;
    private int vip = 0;
    private int cVip = 0;
    private int gVip = 0;
    private UserInfoResponse userInfoResponse;
    private final static String CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";
    private final static String ACTION_FRIEND_ACTION = "com.paobuqianjn.step.FRIEND_ACTION";
    private final static String ACTION_FRIEND_HONOR = "com.paobuqianjin.pbq.ACTION_FRIEND_HONOR";
    String money = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_center_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(ownerUiInterface);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Presenter.getInstance(getContext()).getUserInfo(Presenter.getInstance(getContext()).getId());
    }

    @Override
    protected void initView(View viewRoot) {
        headIcon = (CircleImageView) viewRoot.findViewById(R.id.head_icon);
        userIcon = (RelativeLayout) viewRoot.findViewById(R.id.user_icon);
        userName = (TextView) viewRoot.findViewById(R.id.user_name);
        userId = (TextView) viewRoot.findViewById(R.id.user_id);
        circle = (ImageView) viewRoot.findViewById(R.id.circle);
        friends = (ImageView) viewRoot.findViewById(R.id.friend);
        dynamicSpan = (LinearLayout) viewRoot.findViewById(R.id.dynamic_span);
        suggestionSpan = (LinearLayout) viewRoot.findViewById(R.id.suggestion_span);
        goldenSponsor = (ImageView) viewRoot.findViewById(R.id.golden_sponsor);
        walletMoney = (TextView) viewRoot.findViewById(R.id.wallet_money);
        walletStep = (TextView) viewRoot.findViewById(R.id.wallet_step);
        walletDetailButton = (TextView) viewRoot.findViewById(R.id.wallet_detail_button);
        walletDetailButton.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        exReleaseNum = (TextView) viewRoot.findViewById(R.id.ex_release_num);
        exSaleOut = (TextView) viewRoot.findViewById(R.id.ex_sale_out);
        exBuyNum = (TextView) viewRoot.findViewById(R.id.ex_buy_num);
        exSaveNum = (TextView) viewRoot.findViewById(R.id.ex_save_num);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(getContext()).dispatchUiInterface(ownerUiInterface);
    }


    @OnClick({R.id.his_span, R.id.user_icon, R.id.wallet_span_a, R.id.wallet_span_b, R.id.gonggao_span, R.id.dynamic_span, R.id.suggestion_span, R.id.reward_span, R.id.circle_rel, R.id.setting_icon, R.id.vip_span,
            R.id.collect_span, R.id.money_span, R.id.crash_button, R.id.friend_span, R.id.invite_people_span, R.id.qrcode, R.id.wallet_detail_button, R.id.ex_release, R.id.ex_sale, R.id.ex_buy_in, R.id.ex_save})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.user_icon:
                LocalLog.d(TAG, "设置头像");
                if (userInfoResponse != null) {
                    Intent userInfoIntent = new Intent();
                    userInfoIntent.putExtra("userinfo", userInfoResponse.getData());
                    userInfoIntent.setClass(getContext(), UserInfoSettingActivity.class);
                    startActivity(userInfoIntent);
                }
                break;
            case R.id.qrcode:
                if (userInfoResponse != null) {
                    intent.putExtra("usericon", userInfoResponse.getData().getAvatar());
                    intent.putExtra("username", userInfoResponse.getData().getNickname());
                    intent.putExtra("userid", Presenter.getInstance(getContext()).getNo());
                    intent.setClass(getContext(), QrCodeMakeActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.his_span:
                intent.setAction(ACTION_FRIEND_HONOR);
                intent.setClass(getContext(), StepHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.wallet_span_a:
                LocalLog.d(TAG, "钱包");
                if (!TextUtils.isEmpty(money)) {
                    intent.putExtra("total", money);
                }
                intent.setClass(getContext(), MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.wallet_span_b:
                LocalLog.d(TAG, "钱包");
                if (!TextUtils.isEmpty(money)) {
                    intent.putExtra("total", money);
                }
                intent.setClass(getContext(), MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.gonggao_span:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.gitf_span:
                LocalLog.d(TAG, "推荐人");
                intent.setClass(getContext(), InviteDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.invite_people_span:
                LocalLog.d(TAG, "推荐人");
                intent.setClass(getContext(), InviteDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.dynamic_span:
                LocalLog.d(TAG, "我的动态");
                intent.setClass(getContext(), MyDynamicActivity.class);
                startActivity(intent);
                break;
            case R.id.suggestion_span:
                LocalLog.d(TAG, "意见反馈");
                intent.setClass(getContext(), SuggestionActivity.class);
                startActivity(intent);
                break;
            case R.id.friend_span:
                LocalLog.d(TAG, "好友");
                intent.setAction(ACTION_FRIEND_ACTION);
                intent.setClass(getContext(), MyFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.reward_span:
                //抽奖
                startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", NetApi.urlChongjiang));
                break;
            case R.id.circle_rel:
                LocalLog.d(TAG, "圈子");
                intent.setClass(getContext(), OwnerCircleActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_icon:
                if (this.userInfoResponse != null && this.userInfoResponse.getData() != null) {
                    intent.putExtra("userinfo", this.userInfoResponse.getData());
                    intent.setClass(getContext(), SettingActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.vip_span:
                intent.putExtra("vip", vip);
                intent.setClass(getContext(), VipActivity.class);
                startActivity(intent);
                break;
            case R.id.collect_span:
                startActivity(SponsorCollectActivity.class, null);
                break;
            case R.id.money_span:
                startActivity(GetMoreMoneyActivity.class, null);
                break;
            case R.id.crash_button:
                if (!TextUtils.isEmpty(money)) {
                    intent.putExtra("total", money);
                    intent.setAction(CRASH_ACTION);
                    intent.setClass(getContext(), TransferActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.wallet_detail_button:
                startActivity(InoutcomDetailActivity.class, null);
                break;
            case R.id.ex_release:
                startActivity(ExReleaseHisActivity.class, null);
                break;
            case R.id.ex_sale:
                startActivity(ExchangeOutActivity.class, null);
                break;
            case R.id.ex_buy_in:
                startActivity(ExchangeInActivity.class, null);
                break;
            case R.id.ex_save:
                break;
            default:
                break;
        }
    }

    private OwnerUiInterface ownerUiInterface = new OwnerUiInterface() {
        @Override
        public void response(UserInfoResponse userInfoResponse) {
            if (!isAdded()) {
                return;
            }
            if (userInfoResponse.getError() == 0) {
                LocalLog.d(TAG, "UserInfoResponse() enter" + userInfoResponse.toString());
                OwnerCenterFragment.this.userInfoResponse = userInfoResponse;
                userAvatar = userInfoResponse.getData().getAvatar();
                if (headIcon == null) {
                    LocalLog.d(TAG, "vvvvvvvv");
                    return;
                }
                Presenter.getInstance(getContext()).setMobile(getContext(), userInfoResponse.getData().getMobile());
                Presenter.getInstance(getContext()).setCurrentUser(userInfoResponse.getData());
                Presenter.getInstance(getContext()).setTarget(getContext(), userInfoResponse.getData().getTarget_step());
                Presenter.getInstance(getContext()).setAvatar(getContext(), userAvatar);
                Presenter.getInstance(getContext()).setNo(userInfoResponse.getData().getNo());
                Presenter.getInstance(getContext()).setNickName(getContext(), userInfoResponse.getData().getNickname());
                Presenter.getInstance(getContext()).getPlaceErrorImage(headIcon, userInfoResponse.getData().getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                money = userInfoResponse.getData().getBalance();
                String moneyStr = String.valueOf(userInfoResponse.getData().getBalance()) + " 元";
                SpannableString moneyString = new SpannableString(moneyStr);
                moneyString.setSpan(new AbsoluteSizeSpan(12, true), String.valueOf(userInfoResponse.getData().getBalance()).length(), moneyStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                walletMoney.setText(moneyString);
                String creditStr = String.valueOf(userInfoResponse.getData().getCredit()) + " 步币";
                SpannableString creditString = new SpannableString(creditStr);
                creditString.setSpan(new AbsoluteSizeSpan(12, true), String.valueOf(userInfoResponse.getData().getCredit()).length(), creditStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                walletStep.setText(creditString);
                userName.setText(userInfoResponse.getData().getNickname());
                userId.setText("跑步钱进号:" + userInfoResponse.getData().getNo());
                urlIcon = userInfoResponse.getData().getAvatar();
                vip = userInfoResponse.getData().getVip();
                cVip = userInfoResponse.getData().getCusvip();
                gVip = userInfoResponse.getData().getGvip();
                if (vip == 1 || cVip == 1 || gVip == 1) {
                    userIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.golden_back));
                    sex.setVisibility(View.VISIBLE);
                    LocalLog.d(TAG, "当前用户为VIP");
                    if (gVip == 0) {
                        if (cVip == 0) {
                            goldenSponsor.setImageResource(R.drawable.vip_flg);
                            sex.setImageResource(R.drawable.vip_flg);
                        } else {
                            /*goldenSponsor.setImageResource(R.drawable.vip_sponsor);
                            sex.setImageResource(R.drawable.vip_sponsor);*/
                        }

                    } else {
                        sex.setImageResource(R.drawable.golden_little);
                        goldenSponsor.setImageResource(R.drawable.golden_big);
                    }
                    goldenSponsor.setVisibility(View.VISIBLE);

                } else {
                    LocalLog.d(TAG, "非会员显示男女");
                    goldenSponsor.setVisibility(View.INVISIBLE);
                    sex.setVisibility(View.INVISIBLE);
                    userIcon.setBackground(null);
                }
            } else if (userInfoResponse.getError() == 1) {
                exitTokenUnfect();
            } else if (userInfoResponse.getError() == -1) {

            } else if (userInfoResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (!isAdded()) {
                return;
            }
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

    };
}
