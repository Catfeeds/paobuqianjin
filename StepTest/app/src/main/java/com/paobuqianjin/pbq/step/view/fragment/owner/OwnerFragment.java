package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.paobuqianjin.pbq.step.view.activity.InviteActivity;
import com.paobuqianjin.pbq.step.view.activity.MessageActivity;
import com.paobuqianjin.pbq.step.view.activity.MyDynamicActivity;
import com.paobuqianjin.pbq.step.view.activity.MyFriendActivity;
import com.paobuqianjin.pbq.step.view.activity.MyReleaseActivity;
import com.paobuqianjin.pbq.step.view.activity.MyWalletActivity;
import com.paobuqianjin.pbq.step.view.activity.OwnerCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeMakeActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeScanActivity;
import com.paobuqianjin.pbq.step.view.activity.SettingActivity;
import com.paobuqianjin.pbq.step.view.activity.StepDollarActivity;
import com.paobuqianjin.pbq.step.view.activity.SuggestionActivity;
import com.paobuqianjin.pbq.step.view.activity.UserCenterActivity;
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
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    ImageView barTvRight;
    @Bind(R.id.head_icon)
    CircleImageView headIcon;
    @Bind(R.id.man_woman)
    ImageView manWoman;
    @Bind(R.id.user_icon)
    RelativeLayout userIcon;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_id)
    TextView userId;
    @Bind(R.id.dear_name)
    RelativeLayout dearName;
    @Bind(R.id.go_to_bar)
    ImageView goToBar;
    @Bind(R.id.user_span)
    RelativeLayout userSpan;
    @Bind(R.id.line_1)
    ImageView line1;
    @Bind(R.id.circle)
    TextView circle;
    @Bind(R.id.friend)
    TextView friends;
    @Bind(R.id.line_2)
    ImageView line2;
    @Bind(R.id.wallet_icon)
    ImageView walletIcon;
    @Bind(R.id.wallet_desc)
    TextView walletDesc;
    @Bind(R.id.go_to_wallet)
    ImageView goToWallet;
    @Bind(R.id.wallet_span)
    RelativeLayout walletSpan;
    @Bind(R.id.step_dollar_icon)
    ImageView stepDollarIcon;
    @Bind(R.id.step_dollar_desc)
    TextView stepDollarDesc;
    @Bind(R.id.go_to_step_dollar)
    ImageView goToStepDollar;
    @Bind(R.id.step_dollar_span)
    RelativeLayout stepDollarSpan;
    @Bind(R.id.gift_icon)
    ImageView giftIcon;
    @Bind(R.id.gift_desc)
    TextView giftDesc;
    @Bind(R.id.go_to_gift)
    ImageView goToGift;
    @Bind(R.id.gitf_span)
    RelativeLayout gitfSpan;
    @Bind(R.id.line_3)
    ImageView line3;
    @Bind(R.id.dynamic_icon)
    ImageView dynamicIcon;
    @Bind(R.id.dynamic_desc)
    TextView dynamicDesc;
    @Bind(R.id.go_to_dymamic)
    ImageView goToDymamic;
    @Bind(R.id.dynamic_span)
    RelativeLayout dynamicSpan;
    @Bind(R.id.dan_icon)
    ImageView danIcon;
    @Bind(R.id.dan_desc)
    TextView danDesc;
    @Bind(R.id.go_to_dan)
    ImageView goToDan;
    @Bind(R.id.dan_span)
    RelativeLayout danSpan;
    @Bind(R.id.suggestion_icon)
    ImageView suggestionIcon;
    @Bind(R.id.suggestion_desc)
    TextView suggestionDesc;
    @Bind(R.id.go_to_suggestion)
    ImageView goToSuggestion;
    @Bind(R.id.suggestion_span)
    RelativeLayout suggestionSpan;
    @Bind(R.id.friend_rel)
    RelativeLayout messageRel;
    @Bind(R.id.circle_rel)
    RelativeLayout friendRel;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.man)
    ImageView man;
    @Bind(R.id.bank_icon)
    ImageView bankIcon;
    @Bind(R.id.bank_desc)
    TextView bankDesc;
    @Bind(R.id.go_to_bank)
    ImageView goToBank;
    @Bind(R.id.bank_span)
    RelativeLayout bankSpan;
    @Bind(R.id.task_release_icon)
    ImageView taskReleaseIcon;
    @Bind(R.id.task_release_desc)
    TextView taskReleaseDesc;
    @Bind(R.id.go_to_task)
    ImageView goToTask;
    @Bind(R.id.task_release_span)
    RelativeLayout taskReleaseSpan;
    String urlIcon = "";
    @Bind(R.id.line_4)
    ImageView line4;
    @Bind(R.id.setting_icon)
    ImageView settingIcon;
    @Bind(R.id.setting_desc)
    TextView settingDesc;
    @Bind(R.id.go_to_setting)
    ImageView goToSetting;
    @Bind(R.id.setting_span)
    RelativeLayout settingSpan;
    @Bind(R.id.qrcode)
    ImageView qrcode;
    @Bind(R.id.qrcode_rel)
    RelativeLayout qrcodeRel;
    @Bind(R.id.friend_scan)
    RelativeLayout friendScan;
    @Bind(R.id.vip_icon)
    ImageView vipIcon;
    @Bind(R.id.vip_desc)
    TextView vipDesc;
    @Bind(R.id.go_to_vip)
    ImageView goToVip;
    @Bind(R.id.vip_span)
    RelativeLayout vipSpan;
    @Bind(R.id.vip_flg)
    ImageView vipFlg;
    private String userAvatar;
    private int vip = 0;
    private UserInfoResponse userInfoResponse;

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
        super.initView(viewRoot);
        LocalLog.d(TAG, "initView() enter");
        barReturnDrawable = (ImageView) viewRoot.findViewById(R.id.bar_return_drawable);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTitle.setText("我的");
        headIcon = (CircleImageView) viewRoot.findViewById(R.id.head_icon);
        userIcon = (RelativeLayout) viewRoot.findViewById(R.id.user_icon);
        userName = (TextView) viewRoot.findViewById(R.id.user_name);
        userId = (TextView) viewRoot.findViewById(R.id.user_id);
        circle = (TextView) viewRoot.findViewById(R.id.circle);
        friends = (TextView) viewRoot.findViewById(R.id.friend);
        walletSpan = (RelativeLayout) viewRoot.findViewById(R.id.wallet_span);
        stepDollarSpan = (RelativeLayout) viewRoot.findViewById(R.id.step_dollar_span);
        gitfSpan = (RelativeLayout) viewRoot.findViewById(R.id.gitf_span);
        dynamicSpan = (RelativeLayout) viewRoot.findViewById(R.id.dynamic_span);
        danSpan = (RelativeLayout) viewRoot.findViewById(R.id.dan_span);
        suggestionSpan = (RelativeLayout) viewRoot.findViewById(R.id.suggestion_span);
        if (headIcon != null) {
            LocalLog.d(TAG, "###############");
        }

        vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        LocalLog.d(TAG, "onDestroyView() enter");
        Presenter.getInstance(getContext()).dispatchUiInterface(ownerUiInterface);
    }

    @OnClick({R.id.bar_tv_right, R.id.user_span, R.id.wallet_span, R.id.step_dollar_span, R.id.gitf_span, R.id.dynamic_span,
            R.id.dan_span, R.id.suggestion_span, R.id.friend_rel, R.id.circle_rel,
            R.id.bar_return_drawable, R.id.task_release_span, R.id.setting_span, R.id.qrcode_rel, R.id.vip_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.bar_tv_right:
                LocalLog.d(TAG, "扫码");
                new IntentIntegrator(getActivity())
                        .setOrientationLocked(false)
                        .setCaptureActivity(QrCodeScanActivity.class)
                        .initiateScan();
                break;
            case R.id.user_span:
                LocalLog.d(TAG, "设置头像");
                intent.putExtra("userid", Presenter.getInstance(getContext()).getId());
                intent.setClass(getContext(), UserCenterActivity.class);
                startActivity(intent);
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
            case R.id.friend_rel:
                LocalLog.d(TAG, "关注");
                intent.setClass(getContext(), MyFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.circle_rel:
                LocalLog.d(TAG, "圈子");
                intent.setClass(getContext(), OwnerCircleActivity.class);
                startActivity(intent);
                break;
            case R.id.bar_return_drawable:
                LocalLog.d(TAG, "消息");
                intent.setClass(getContext(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.task_release_span:
                LocalLog.d(TAG, "我的发布");
                startActivity(MyReleaseActivity.class, null);
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
            case R.id.qrcode_rel:
                LocalLog.d(TAG, "生成二维码");
                intent.putExtra("usericon", urlIcon);
                intent.putExtra("username", userName.getText().toString());
                intent.putExtra("userid", Presenter.getInstance(getContext()).getNo());
                intent.setClass(getContext(), QrCodeMakeActivity.class);
                startActivity(intent);
                break;
            case R.id.vip_span:
                intent.putExtra("vip", vip);
                intent.setClass(getContext(), VipActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    private OwnerUiInterface ownerUiInterface = new OwnerUiInterface() {
        @Override
        public void response(UserInfoResponse userInfoResponse) {
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
                userName.setText(userInfoResponse.getData().getNickname());
                userId.setText("跑步钱进号:" + userInfoResponse.getData().getNo());
                circle.setText(String.valueOf(userInfoResponse.getData().getCircleCount()));
                friends.setText(String.valueOf(userInfoResponse.getData().getFollowCount()));
                urlIcon = userInfoResponse.getData().getAvatar();
                vip = userInfoResponse.getData().getVip();
                if (vip == 1) {
                    LocalLog.d(TAG, "当前用户为VIP");
                    vipFlg.setVisibility(View.VISIBLE);
                }
                if (userInfoResponse.getData().getSex() == 1) {
                    userIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.man_head_back));
                    manWoman.setVisibility(View.GONE);
                    man.setVisibility(View.VISIBLE);
                } else if (userInfoResponse.getData().getSex() == 2) {
                    userIcon.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.women_back));
                    manWoman.setVisibility(View.VISIBLE);
                    man.setVisibility(View.GONE);
                }
            } else if (userInfoResponse.getError() == 1) {

            } else if (userInfoResponse.getError() == -1) {

            } else if (userInfoResponse.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

        @Override
        public void response(ErrorCode errorCode) {
            if (errorCode.getError() == -100) {
                LocalLog.d(TAG, "Token 过期!");
                exitTokenUnfect();
            }
        }

    };
/*    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view != null) {
                switch (view.getId()) {
                    case R.id.login_out:
                        Presenter.getInstance(getContext()).steLogFlg(false);
                        getActivity().finish();
                        break;
                }
            }

        }
    };*/
}
