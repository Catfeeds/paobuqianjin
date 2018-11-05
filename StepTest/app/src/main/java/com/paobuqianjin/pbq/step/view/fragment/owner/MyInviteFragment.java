package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.AgreementActivity;
import com.paobuqianjin.pbq.step.view.activity.FillInviteCodeActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/27.
 */

public class MyInviteFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = MyInviteFragment.class.getSimpleName();
    private final static String USER_INVITE_AGREEMENT_ACTION = "com.paobuqianjin.step.pbq.INVITE_ACTION";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.invite_code_text)
    TextView inviteCodeText;
    @Bind(R.id.invite_code_span)
    RelativeLayout inviteCodeSpan;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.invite_pkg)
    ImageView invitePkg;
    @Bind(R.id.invit_num)
    TextView invitNum;
    @Bind(R.id.invite_num_des)
    TextView inviteNumDes;
    @Bind(R.id.invit_money)
    TextView invitMoney;
    @Bind(R.id.invite_money_des)
    TextView inviteMoneyDes;
    @Bind(R.id.invit_step_dollar)
    TextView invitStepDollar;
    @Bind(R.id.invite_step_des)
    TextView inviteStepDes;
    @Bind(R.id.invite_result_pan)
    LinearLayout inviteResultPan;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.invite_rule)
    TextView inviteRule;
    @Bind(R.id.go_rule_span)
    RelativeLayout goRuleSpan;
    @Bind(R.id.invite_code)
    TextView inviteCode;
    @Bind(R.id.invite_swipe_layout)
    SwipeRefreshLayout inviteSwipeLayout;
    @Bind(R.id.invite_btn)
    Button inviteBtn;
    @Bind(R.id.my_invite_fg)
    RelativeLayout myInviteFg;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private TranslateAnimation animationCircleType;
    private SHARE_MEDIA share_media;
    private ProgressDialog dialog;
    private UMWeb web;
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private int pageIndex = 1, pageCount = 0;
    private final static int PAGESIZE = 200;

    @Override
    protected int getLayoutResId() {
        return R.layout.my_invite_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected String title() {
        return "邀请好友";
    }

    @Override
    protected void initView(View viewRoot) {
        inviteCode = (TextView) viewRoot.findViewById(R.id.invite_code);
        invitNum = (TextView) viewRoot.findViewById(R.id.invit_num);
        invitMoney = (TextView) viewRoot.findViewById(R.id.invit_money);
        invitStepDollar = (TextView) viewRoot.findViewById(R.id.invit_step_dollar);
        goRuleSpan = (RelativeLayout) viewRoot.findViewById(R.id.go_rule_span);
        goRuleSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AgreementActivity.class, null, false, USER_INVITE_AGREEMENT_ACTION);
            }
        });
        dialog = new ProgressDialog(getContext());
        UserInfoResponse.DataBean userInfo = Presenter.getInstance(getContext()).getCurrentUser();
        if (userInfo != null) {
            web = new UMWeb(NetApi.urlShareIc + userInfo.getNo());
            web.setTitle("走路就能领红包的APP");
            web.setThumb(new UMImage(getContext(), R.mipmap.app_icon));
            web.setDescription("邀请好友成功注册,陆续将获得三十元奖励");
        }
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getContext());
        inviteSwipeLayout = (SwipeRefreshLayout) viewRoot.findViewById(R.id.invite_swipe_layout);
        inviteSwipeLayout.setRefreshing(false);
        inviteSwipeLayout.setOnRefreshListener(mRefreshListener);
        update();
    }

    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (inviteSwipeLayout != null && isAdded()) {
                update();
            }
            inviteSwipeLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (inviteSwipeLayout != null) {
                        inviteSwipeLayout.setRefreshing(false);
                    }
                }
            }, 2000);
        }
    };

    public void update() {
        if (isAdded()) {
            Presenter.getInstance(getContext()).getMyInviteMsg(innerCallBack, pageIndex, PAGESIZE);
            Presenter.getInstance(getContext()).getMyCode(innerCallBack);
        }
    }


    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, share_media.toString() + "开始分享");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(getContext(), "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(getContext(), "失败" + throwable.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(getContext(), "取消分享", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

    private void selectShare() {
        popupCircleTypeView = View.inflate(getContext(), R.layout.share_pop_window, null);
        popupCircleTypeWindow = new PopupWindow(popupCircleTypeView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupCircleTypeWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "popupCircleTypeWindow onDismiss() enter");
                popupCircleTypeWindow = null;
            }
        });
        RelativeLayout friendCircle = (RelativeLayout) popupCircleTypeView.findViewById(R.id.friend_circle);
        RelativeLayout wechat = (RelativeLayout) popupCircleTypeView.findViewById(R.id.we_chat);
        RelativeLayout qq_icon = (RelativeLayout) popupCircleTypeView.findViewById(R.id.qq_icon);
        friendCircle.setOnClickListener(onClickListener);
        wechat.setOnClickListener(onClickListener);
        qq_icon.setOnClickListener(onClickListener);
        popupCircleTypeWindow.setFocusable(true);
        popupCircleTypeWindow.setOutsideTouchable(true);
        popupCircleTypeWindow.setBackgroundDrawable(new BitmapDrawable());

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        popupCircleTypeWindow.showAtLocation(getActivity().findViewById(R.id.my_invite_fg), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        popupCircleTypeView.startAnimation(animationCircleType);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.friend_circle:
                    share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                    if (web == null) {
                        PaoToastUtils.showLongToast(getContext(), "分享失败");
                        return;
                    }
                    new ShareAction(getActivity()).withMedia(web)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    break;
                case R.id.we_chat:
                    share_media = SHARE_MEDIA.WEIXIN;
                    if (web == null) {
                        PaoToastUtils.showLongToast(getContext(), "分享失败");
                        return;
                    }
                    new ShareAction(getActivity()).withMedia(web)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    break;
                case R.id.qq_icon:
                    requestPermission(Permission.Group.STORAGE);
                    break;
                default:
                    break;
            }
        }
    };
 /*权限适配*/

    private void requestPermission(String... permissions) {
        if (isAdded()) {
            AndPermission.with(this)
                    .permission(permissions)
                    .rationale(mRationale)
                    .onGranted(new Action() {
                        @Override
                        public void onAction(List<String> permissions) {
                            LocalLog.d(TAG, "获取权限成功");
                            share_media = SHARE_MEDIA.QQ;
                            if (web == null) {
                                PaoToastUtils.showLongToast(getContext(), "分享失败");
                                return;
                            }
                            new ShareAction(getActivity()).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(shareListener).share();
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
    }


    @OnClick({R.id.invite_code_span, R.id.invite_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.invite_code_span:
                LocalLog.d(TAG, "填写邀请码");
                startActivity(FillInviteCodeActivity.class, null);
                break;
            case R.id.invite_btn:
                LocalLog.d(TAG, "邀请好友");
                selectShare();
                break;
        }
    }

    private InnerCallBack innerCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (!isAdded()) {
                return;
            }
            if (object instanceof ErrorCode) {
                PaoToastUtils.showLongToast(getContext(), ((ErrorCode) object).getMessage());
            } else if (object instanceof InviteCodeResponse) {
                if (inviteCode != null) {
                    if (((InviteCodeResponse) object).getError() == 0) {
                        if (((InviteCodeResponse) object).getData() != null) {
                            inviteCode.setText("我的邀请码:" + ((InviteCodeResponse) object).getData().getMycode());
                            LocalLog.d(TAG,"CODE =" + ((InviteCodeResponse) object).getData().getMycode());
                        }
                    }
                }

            } else if (object instanceof MyInviteResponse) {
                if (((MyInviteResponse) object).getError() == 100) {
                    exitTokenUnfect();
                } else if (((MyInviteResponse) object).getError() == 0) {
                    if (invitNum != null && ((MyInviteResponse) object).getData() != null) {
                        invitNum.setText(String.valueOf(((MyInviteResponse) object).getData().getInumber()));
                        invitMoney.setText(String.valueOf(((MyInviteResponse) object).getData().getImoney()));
                        invitStepDollar.setText(String.valueOf(((MyInviteResponse) object).getData().getIcredit()));
                    }
                } else {
                    PaoToastUtils.showLongToast(getContext(), ((MyInviteResponse) object).getMessage());
                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
