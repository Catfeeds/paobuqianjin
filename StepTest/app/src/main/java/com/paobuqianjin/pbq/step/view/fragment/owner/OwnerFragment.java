package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.OwnerUiInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.DanActivity;
import com.paobuqianjin.pbq.step.view.activity.GetMoreMoneyActivity;
import com.paobuqianjin.pbq.step.view.activity.InoutcomDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.InviteActivity;
import com.paobuqianjin.pbq.step.view.activity.MessageActivity;
import com.paobuqianjin.pbq.step.view.activity.MyConsumptiveRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.MyDynamicActivity;
import com.paobuqianjin.pbq.step.view.activity.MyFriendActivity;
import com.paobuqianjin.pbq.step.view.activity.MyWalletActivity;
import com.paobuqianjin.pbq.step.view.activity.NearByActivity;
import com.paobuqianjin.pbq.step.view.activity.OwnerCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeMakeActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeScanActivity;
import com.paobuqianjin.pbq.step.view.activity.SettingActivity;
import com.paobuqianjin.pbq.step.view.activity.ShopRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorCollectActivity;
import com.paobuqianjin.pbq.step.view.activity.StepDollarActivity;
import com.paobuqianjin.pbq.step.view.activity.SuggestionActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskActivity;
import com.paobuqianjin.pbq.step.view.activity.TransferActivity;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;
import com.paobuqianjin.pbq.step.view.activity.UserInfoSettingActivity;
import com.paobuqianjin.pbq.step.view.activity.VipActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2017/12/1.
 */

public final class OwnerFragment extends BaseFragment {
    private final static String TAG = OwnerFragment.class.getSimpleName();
    String urlIcon = "";
    @Bind(R.id.user_back)
    ImageView userBack;
    @Bind(R.id.own_title)
    TextView ownTitle;
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
    @Bind(R.id.qrcode)
    ImageView qrcode;
    @Bind(R.id.qrcode_rel)
    LinearLayout qrcodeRel;
    @Bind(R.id.user_span)
    LinearLayout userSpan;
    @Bind(R.id.circle)
    TextView circle;
    @Bind(R.id.circle_rel)
    LinearLayout circleRel;
    @Bind(R.id.friend)
    TextView friend;
    @Bind(R.id.friend_span)
    LinearLayout friendSpan;
    @Bind(R.id.like_num)
    TextView likeNum;
    @Bind(R.id.like_span)
    LinearLayout likeSpan;
    @Bind(R.id.near_by)
    ImageView nearBy;
    @Bind(R.id.near_by_span)
    LinearLayout nearBySpan;
    @Bind(R.id.wallet_money)
    TextView walletMoney;
    @Bind(R.id.crash_button)
    Button crashButton;
    @Bind(R.id.wallet_detail_button)
    Button walletDetailButton;
    @Bind(R.id.center_part)
    LinearLayout centerPart;
    @Bind(R.id.wallet_icon)
    ImageView walletIcon;
    @Bind(R.id.wallet_desc)
    TextView walletDesc;
    @Bind(R.id.wallet_span)
    LinearLayout walletSpan;
    @Bind(R.id.vip_icon)
    ImageView vipIcon;
    @Bind(R.id.vip_desc)
    TextView vipDesc;
    @Bind(R.id.vip_span)
    LinearLayout vipSpan;
    @Bind(R.id.step_dollar_icon)
    ImageView stepDollarIcon;
    @Bind(R.id.step_dollar_desc)
    TextView stepDollarDesc;
    @Bind(R.id.step_dollar_span)
    LinearLayout stepDollarSpan;
    @Bind(R.id.iv_consumptive_red_bag)
    ImageView ivConsumptiveRedBag;
    @Bind(R.id.tv_consumptive_red_bag)
    TextView tvConsumptiveRedBag;
    @Bind(R.id.linear_consumptive_red_bag)
    LinearLayout linearConsumptiveRedBag;
    @Bind(R.id.wallet_dollor_span)
    LinearLayout walletDollorSpan;
    @Bind(R.id.gitf_span)
    ImageView gitfSpan;
    @Bind(R.id.task_recv_icon)
    ImageView taskRecvIcon;
    @Bind(R.id.task_recv_desc)
    TextView taskRecvDesc;
    @Bind(R.id.task_recv)
    LinearLayout taskRecv;
    @Bind(R.id.task_release_icon)
    ImageView taskReleaseIcon;
    @Bind(R.id.task_release_desc)
    TextView taskReleaseDesc;
    @Bind(R.id.task_release_span)
    LinearLayout taskReleaseSpan;
    @Bind(R.id.iv_shop_red_bag)
    ImageView ivShopRedBag;
    @Bind(R.id.tv_shop_red_bag)
    TextView tvShopRedBag;
    @Bind(R.id.linear_shop_red_bag)
    LinearLayout linearShopRedBag;
    @Bind(R.id.dan_icon)
    ImageView danIcon;
    @Bind(R.id.dan_desc)
    TextView danDesc;
    @Bind(R.id.dynamic_span)
    LinearLayout dynamicSpan;
    @Bind(R.id.dynamic_icon)
    ImageView dynamicIcon;
    @Bind(R.id.dynamic_desc)
    TextView dynamicDesc;
    @Bind(R.id.dan_span)
    LinearLayout danSpan;
    @Bind(R.id.task_dan)
    LinearLayout taskDan;
    @Bind(R.id.collect_icon)
    ImageView collectIcon;
    @Bind(R.id.collect_desc)
    TextView collectDesc;
    @Bind(R.id.collect_span)
    LinearLayout collectSpan;
    @Bind(R.id.money_icon)
    ImageView moneyIcon;
    @Bind(R.id.money_desc)
    TextView moneyDesc;
    @Bind(R.id.money_span)
    LinearLayout moneySpan;
    @Bind(R.id.suggestion_icon)
    ImageView suggestionIcon;
    @Bind(R.id.suggestion_desc)
    TextView suggestionDesc;
    @Bind(R.id.suggestion_span)
    LinearLayout suggestionSpan;
    @Bind(R.id.setting_icon)
    ImageView settingIcon;
    @Bind(R.id.setting_desc)
    TextView settingDesc;
    @Bind(R.id.setting_span)
    LinearLayout settingSpan;
    @Bind(R.id.collect_sponsor_span)
    LinearLayout collectSponsorSpan;
    private String userAvatar;
    TextView friends;
    private int vip = 0;
    private int cVip = 0;
    private int gVip = 0;
    private UserInfoResponse userInfoResponse;
    private final static String CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";
    private final static String ACTION_STRANGE_ACTION = "com.paobuqianjin.pbq.step.STRANGE_ACTION";
    private final static String ACTION_FRIEND_ACTION = "com.paobuqianjn.step.FRIEND_ACTION";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(ownerUiInterface);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_page;
    }

    @Override
    public void onResume() {
        super.onResume();
        Presenter.getInstance(getContext()).getUserInfo(Presenter.getInstance(getContext()).getId());
    }

    @Override
    protected void initView(View viewRoot) {
        LocalLog.d(TAG, "initView() enter");
        headIcon = (CircleImageView) viewRoot.findViewById(R.id.head_icon);
        userIcon = (RelativeLayout) viewRoot.findViewById(R.id.user_icon);
        userName = (TextView) viewRoot.findViewById(R.id.user_name);
        userId = (TextView) viewRoot.findViewById(R.id.user_id);
        circle = (TextView) viewRoot.findViewById(R.id.circle);
        friends = (TextView) viewRoot.findViewById(R.id.friend);
        likeNum = (TextView) viewRoot.findViewById(R.id.like_num);
        walletSpan = (LinearLayout) viewRoot.findViewById(R.id.wallet_span);
        stepDollarSpan = (LinearLayout) viewRoot.findViewById(R.id.step_dollar_span);
        gitfSpan = (ImageView) viewRoot.findViewById(R.id.gitf_span);
        dynamicSpan = (LinearLayout) viewRoot.findViewById(R.id.dynamic_span);
        danSpan = (LinearLayout) viewRoot.findViewById(R.id.dan_span);
        suggestionSpan = (LinearLayout) viewRoot.findViewById(R.id.suggestion_span);
        goldenSponsor = (ImageView) viewRoot.findViewById(R.id.golden_sponsor);
        walletMoney = (TextView) viewRoot.findViewById(R.id.wallet_money);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        LocalLog.d(TAG, "onDestroyView() enter");
        Presenter.getInstance(getContext()).dispatchUiInterface(ownerUiInterface);
    }

    @OnClick({R.id.user_span, R.id.wallet_span, R.id.step_dollar_span, R.id.linear_consumptive_red_bag, R.id.linear_shop_red_bag, R.id.gitf_span, R.id.dynamic_span,
            R.id.dan_span, R.id.suggestion_span, R.id.like_span, R.id.circle_rel, R.id.task_release_span, R.id.task_recv, R.id.setting_span, R.id.vip_span,
            R.id.collect_span, R.id.money_span, R.id.near_by_span, R.id.crash_button, R.id.wallet_detail_button, R.id.friend_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.user_span:
                LocalLog.d(TAG, "设置头像");
                if (userInfoResponse != null) {
                    Intent userInfoIntent = new Intent();
                    userInfoIntent.putExtra("userinfo", userInfoResponse.getData());
                    userInfoIntent.setClass(getContext(), UserInfoSettingActivity.class);
                    startActivity(userInfoIntent);
                }
                break;
            case R.id.wallet_span:
                LocalLog.d(TAG, "钱包");
                intent.setClass(getContext(), MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.step_dollar_span:
                LocalLog.d(TAG, "步币");
                if (this.userInfoResponse != null && this.userInfoResponse.getData() != null) {
                    intent.putExtra("userinfo", this.userInfoResponse.getData());
                    intent.setClass(getContext(), StepDollarActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.linear_consumptive_red_bag:
                LocalLog.d(TAG, "消费红包");
                if (this.userInfoResponse != null && this.userInfoResponse.getData() != null) {
                    intent.putExtra("userinfo", this.userInfoResponse.getData());
                    intent.setClass(getContext(), MyConsumptiveRedBagActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.linear_shop_red_bag:
                LocalLog.d(TAG, "店铺红包");
                if (this.userInfoResponse != null && this.userInfoResponse.getData() != null) {
                    intent.putExtra("userinfo", this.userInfoResponse.getData());
                    intent.setClass(getContext(), ShopRedBagActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.gitf_span:
                LocalLog.d(TAG, "邀请有礼");
                intent.setClass(getContext(), InviteActivity.class);
                startActivity(intent);
                break;
            case R.id.dynamic_span:
                LocalLog.d(TAG, "我的动态");
                intent.setClass(getContext(), MyDynamicActivity.class);
                startActivity(intent);
                break;
            case R.id.dan_span:
                LocalLog.d(TAG, "我的段位");
                intent.putExtra("usericon", urlIcon);
                intent.setClass(getContext(), DanActivity.class);
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
            case R.id.like_span:
                LocalLog.d(TAG, "关注");
                intent.setAction(ACTION_STRANGE_ACTION);
                intent.setClass(getContext(), MyFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.circle_rel:
                LocalLog.d(TAG, "圈子");
                intent.setClass(getContext(), OwnerCircleActivity.class);
                startActivity(intent);
                break;
            case R.id.task_release_span:
                LocalLog.d(TAG, "我的发布");
                /*startActivity(MyReleaseActivity.class, null);*/
                /*delete my release ,go to ReleaseRecordActivity*/
                startActivity(ReleaseRecordActivity.class, null);
                break;
            case R.id.task_recv:
                LocalLog.d(TAG, "我的任务");
                startActivity(TaskActivity.class, null);
                break;
            case R.id.setting_span:
                if (this.userInfoResponse != null && this.userInfoResponse.getData() != null) {
                    intent.putExtra("userinfo", this.userInfoResponse.getData());
                    intent.setClass(getContext(), SettingActivity.class);
                    startActivity(intent);
                }
/*                intent.setClass(getContext(), UserInfoSettingActivity.class);
                startActivity(intent);*/
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
                if (!TextUtils.isEmpty(walletMoney.getText().toString().trim())) {
                    intent.putExtra("total", Float.parseFloat(walletMoney.getText().toString().trim()));
                    intent.setAction(CRASH_ACTION);
                    intent.setClass(getContext(), TransferActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.wallet_detail_button:
                startActivity(InoutcomDetailActivity.class, null);
                break;
            case R.id.near_by_span:
                startActivity(NearByActivity.class, null);
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
                OwnerFragment.this.userInfoResponse = userInfoResponse;
                userAvatar = userInfoResponse.getData().getAvatar();
                if (headIcon == null) {
                    LocalLog.d(TAG, "vvvvvvvv");
                    return;
                }
                Presenter.getInstance(getContext()).setCurrentUser(userInfoResponse.getData());
                Presenter.getInstance(getContext()).setTarget(getContext(), userInfoResponse.getData().getTarget_step());
                Presenter.getInstance(getContext()).setAvatar(getContext(), userAvatar);
                Presenter.getInstance(getContext()).setNo(userInfoResponse.getData().getNo());
                Presenter.getInstance(getContext()).setNickName(getContext(), userInfoResponse.getData().getNickname());
                Presenter.getInstance(getContext()).getPlaceErrorImage(headIcon, userInfoResponse.getData().getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                walletMoney.setText(userInfoResponse.getData().getBalance());
                userName.setText(userInfoResponse.getData().getNickname());
                userId.setText("跑步钱进号:" + userInfoResponse.getData().getNo());
                circle.setText(String.valueOf(userInfoResponse.getData().getCircleCount()));
                friends.setText(String.valueOf(userInfoResponse.getData().getFriendCount()));
                likeNum.setText(String.valueOf(userInfoResponse.getData().getFollowCount()));
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
                            goldenSponsor.setImageResource(R.drawable.vip_sponsor);
                            sex.setImageResource(R.drawable.vip_sponsor);
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
