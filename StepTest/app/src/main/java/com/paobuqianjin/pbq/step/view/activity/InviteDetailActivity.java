package com.paobuqianjin.pbq.step.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.SlidingTabLayout;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.fragment.owner.InviteTeamFragment;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/10/25.
 */

public class InviteDetailActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {
    private final static String USER_INVITE_AGREEMENT_ACTION = "com.paobuqianjin.step.pbq.INVITE_ACTION";
    private final static String TAG = InviteDetailActivity.class.getSimpleName();
    @Bind(R.id.invite_rule)
    LinearLayout inviteRule;
    @Bind(R.id.fill_invite_code)
    LinearLayout fillInviteCode;
    @Bind(R.id.invite_people_total)
    TextView invitePeopleTotal;
    @Bind(R.id.invite_money_total)
    TextView inviteMoneyTotal;
    @Bind(R.id.invite_step_total)
    TextView inviteStepTotal;
    @Bind(R.id.invite_code)
    TextView inviteCode;
    @Bind(R.id.tablayout)
    SlidingTabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private View popupCircleTypeView;
    private PopupWindow popupCircleTypeWindow;
    private TranslateAnimation animationCircleType;
    private SHARE_MEDIA share_media;
    private ProgressDialog dialog;
    private UMWeb web;
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected String title() {
        return "推荐人";
    }

    @Override
    public Object right() {
        return "邀请好友";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_detail_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void clickRight() {
        LocalLog.d(TAG, "邀请好友");
        selectShare();
    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    public void updateInviteMsg(String people, String money, String stepDolor) {
        invitePeopleTotal.setText(people);
        inviteMoneyTotal.setText(money);
        inviteStepTotal.setText(stepDolor);
    }

    @Override
    protected void initView() {
        setToolBarListener(this);
        strings.add("普通用户");
/*        strings.add("金牌会员");
        strings.add("联盟商家");
        strings.add("优选商家");*/
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        tablayout = (SlidingTabLayout)findViewById(R.id.tablayout);
        dialog = new ProgressDialog(this);
        UserInfoResponse.DataBean userInfo = Presenter.getInstance(this).getCurrentUser();
        if (userInfo != null) {
            web = new UMWeb(NetApi.urlShareIc + userInfo.getNo());
            web.setTitle("走路就能领红包的APP");
            web.setThumb(new UMImage(this, R.mipmap.app_icon));
            web.setDescription("邀请好友成功注册,陆续将获得三十元奖励");
        }
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);
        for (int i = 0; i < strings.size(); i++) {
            InviteTeamFragment teamFragment = new InviteTeamFragment();
            fragments.add(teamFragment);
        }
        viewpager.setAdapter(new TabAdapter(InviteDetailActivity.this, getSupportFragmentManager(), fragments, strings.toArray()));
        tablayout.setupWithViewPager(viewpager);
        getMyCode();

    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, share_media.toString() + "开始分享");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            PaoToastUtils.showLongToast(InviteDetailActivity.this, "分享成功");
            if (popupCircleTypeWindow != null) {
                popupCircleTypeWindow.dismiss();
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            PaoToastUtils.showLongToast(InviteDetailActivity.this, "失败");
            if (popupCircleTypeWindow != null) {
                popupCircleTypeWindow.dismiss();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            PaoToastUtils.showLongToast(InviteDetailActivity.this, "取消分享");
            if (popupCircleTypeWindow != null) {
                popupCircleTypeWindow.dismiss();
            }
        }
    };

    @OnClick({R.id.invite_rule, R.id.fill_invite_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.invite_rule:
                startActivity(AgreementActivity.class, null, false, USER_INVITE_AGREEMENT_ACTION);
                break;
            case R.id.fill_invite_code:
                startActivity(FillInviteCodeActivity.class, null);
                break;
        }
    }

    private void selectShare() {
        popupCircleTypeView = View.inflate(this, R.layout.share_pop_window, null);
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


        popupCircleTypeWindow.showAtLocation(findViewById(R.id.invite_detail), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
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
                        PaoToastUtils.showLongToast(InviteDetailActivity.this, "分享失败");
                        return;
                    }
                    new ShareAction(InviteDetailActivity.this).withMedia(web)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    break;
                case R.id.we_chat:
                    share_media = SHARE_MEDIA.WEIXIN;
                    if (web == null) {
                        PaoToastUtils.showLongToast(InviteDetailActivity.this, "分享失败");
                        return;
                    }
                    new ShareAction(InviteDetailActivity.this).withMedia(web)
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

    private void getMyCode() {
        String url = NetApi.urlMyCode + "?userid=" + String.valueOf(Presenter.getInstance(this).getId());
        Presenter.getInstance(this).getPaoBuSimple(url, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    InviteCodeResponse inviteCodeResponse = new Gson().fromJson(s, InviteCodeResponse.class);
                    inviteCode.setText("我的邀请码：" + inviteCodeResponse.getData().getMycode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupCircleTypeWindow != null) {
            popupCircleTypeWindow.dismiss();
            popupCircleTypeWindow = null;
        }

        UMShareAPI.get(this).release();
    }

    /*权限适配*/

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        LocalLog.d(TAG, "获取权限成功");
                        share_media = SHARE_MEDIA.QQ;
                        if (web == null) {
                            PaoToastUtils.showLongToast(InviteDetailActivity.this, "分享失败");
                            return;
                        }
                        new ShareAction(InviteDetailActivity.this).withMedia(web)
                                .setPlatform(share_media)
                                .setCallback(shareListener).share();
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(InviteDetailActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
