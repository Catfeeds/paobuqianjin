package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.AboutActivity;
import com.paobuqianjin.pbq.step.view.activity.CirCleDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.InviteActivity;
import com.paobuqianjin.pbq.step.view.activity.MyWalletActivity;
import com.paobuqianjin.pbq.step.view.activity.OwnerCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.StepDollarActivity;
import com.paobuqianjin.pbq.step.view.activity.SuggestionActivity;
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
    @Bind(R.id.like_user)
    TextView likeUser;
    @Bind(R.id.like_rel)
    RelativeLayout likeRel;
    @Bind(R.id.fiends)
    TextView fiends;
    @Bind(R.id.message)
    TextView message;
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
    @Bind(R.id.circle_icon)
    ImageView circleIcon;
    @Bind(R.id.cirlce_desc)
    TextView cirlceDesc;
    @Bind(R.id.go_to_cirlce)
    ImageView goToCirlce;
    @Bind(R.id.circle_span)
    RelativeLayout circleSpan;
    @Bind(R.id.dan_icon)
    ImageView danIcon;
    @Bind(R.id.dan_desc)
    TextView danDesc;
    @Bind(R.id.go_to_dan)
    ImageView goToDan;
    @Bind(R.id.dan_span)
    RelativeLayout danSpan;
    @Bind(R.id.line_4)
    ImageView line4;
    @Bind(R.id.about_icon)
    ImageView aboutIcon;
    @Bind(R.id.about_desc)
    TextView aboutDesc;
    @Bind(R.id.go_to_we)
    ImageView goToWe;
    @Bind(R.id.about_span)
    RelativeLayout aboutSpan;
    @Bind(R.id.suggestion_icon)
    ImageView suggestionIcon;
    @Bind(R.id.suggestion_desc)
    TextView suggestionDesc;
    @Bind(R.id.go_to_suggestion)
    ImageView goToSuggestion;
    @Bind(R.id.suggestion_span)
    RelativeLayout suggestionSpan;

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
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        headIcon = (CircleImageView) viewRoot.findViewById(R.id.head_icon);
        userIcon = (RelativeLayout) viewRoot.findViewById(R.id.user_icon);
        userName = (TextView) viewRoot.findViewById(R.id.user_name);
        userId = (TextView) viewRoot.findViewById(R.id.user_id);
        likeUser = (TextView) viewRoot.findViewById(R.id.like_user);
        fiends = (TextView) viewRoot.findViewById(R.id.fiends);
        message = (TextView) viewRoot.findViewById(R.id.message);
        walletSpan = (RelativeLayout) viewRoot.findViewById(R.id.wallet_span);
        stepDollarSpan = (RelativeLayout) viewRoot.findViewById(R.id.step_dollar_span);
        gitfSpan = (RelativeLayout) viewRoot.findViewById(R.id.gitf_span);
        dynamicSpan = (RelativeLayout) viewRoot.findViewById(R.id.dynamic_span);
        circleSpan = (RelativeLayout) viewRoot.findViewById(R.id.circle_span);
        danSpan = (RelativeLayout) viewRoot.findViewById(R.id.dan_span);
        aboutSpan = (RelativeLayout) viewRoot.findViewById(R.id.about_span);
        suggestionSpan = (RelativeLayout) viewRoot.findViewById(R.id.suggestion_span);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bar_tv_right, R.id.user_icon, R.id.wallet_span, R.id.step_dollar_span, R.id.gitf_span, R.id.dynamic_span, R.id.circle_span, R.id.dan_span, R.id.about_span, R.id.suggestion_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.bar_tv_right:
                LocalLog.d(TAG, "设置");
                break;
            case R.id.user_icon:
                LocalLog.d(TAG, "设置头像");
                break;
            case R.id.wallet_span:
                LocalLog.d(TAG, "钱包");
                intent.setClass(getContext(), MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.step_dollar_span:
                LocalLog.d(TAG, "步币");
                intent.setClass(getContext(), StepDollarActivity.class);
                startActivity(intent);
                break;
            case R.id.gitf_span:
                LocalLog.d(TAG, "邀请有礼");
                intent.setClass(getContext(), InviteActivity.class);
                startActivity(intent);
                break;
            case R.id.dynamic_span:
                LocalLog.d(TAG, "我的动态");
                break;
            case R.id.circle_span:
                LocalLog.d(TAG, "我的圈子");
                intent.setClass(getContext(), OwnerCircleActivity.class);
                //With Action
                getContext().startActivity(intent);
                break;
            case R.id.dan_span:
                LocalLog.d(TAG, "我的段位");
                break;
            case R.id.about_span:
                LocalLog.d(TAG, "关于我们");
                intent.setClass(getContext(), AboutActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.suggestion_span:
                LocalLog.d(TAG, "意见反馈");
                intent.setClass(getContext(), SuggestionActivity.class);
                getContext().startActivity(intent);
                break;
            default:
                break;
        }
    }

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
