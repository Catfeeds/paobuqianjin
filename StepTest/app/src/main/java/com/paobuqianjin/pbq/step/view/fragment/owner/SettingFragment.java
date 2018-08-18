package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.question.QuestionActivity;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.table.ChatUserInfo;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.CacheCleanManager;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.RongYunUserInfoManager;
import com.paobuqianjin.pbq.step.view.activity.AboutActivity;
import com.paobuqianjin.pbq.step.view.activity.AccoutManagerActivity;
import com.paobuqianjin.pbq.step.view.activity.ChangeAccountActivity;
import com.paobuqianjin.pbq.step.view.activity.MainActivity;
import com.paobuqianjin.pbq.step.view.activity.UserInfoSettingActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.tencent.bugly.beta.Beta;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/3/30.
 */

public class SettingFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = SettingFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.user_ico)
    CircleImageView userIco;
    @Bind(R.id.vip_flg)
    ImageView vipFlg;
    @Bind(R.id.go_pic)
    ImageView goPic;
    @Bind(R.id.user_head_icon_change)
    RelativeLayout userHeadIconChange;
    @Bind(R.id.line_change_male)
    ImageView lineChangeMale;
    @Bind(R.id.account_third)
    TextView accountThird;
    @Bind(R.id.go_pic1)
    ImageView goPic1;
    @Bind(R.id.account_third_line)
    ImageView accountThirdLine;
    @Bind(R.id.user_name_change)
    RelativeLayout userNameChange;
    @Bind(R.id.problem)
    TextView problem;
    @Bind(R.id.go_pic_problem)
    ImageView goPicProblem;
    @Bind(R.id.line_problem)
    ImageView lineProblem;
    @Bind(R.id.some_problems)
    RelativeLayout someProblems;
    @Bind(R.id.cache)
    TextView cache;
    @Bind(R.id.cache_size)
    TextView cacheSize;
    @Bind(R.id.go_pic2)
    ImageView goPic2;
    @Bind(R.id.cache_clean)
    ImageView cacheClean;
    @Bind(R.id.change_male)
    RelativeLayout changeMale;
    @Bind(R.id.account_change)
    TextView accountChange;
    @Bind(R.id.go_account)
    ImageView goAccount;
    @Bind(R.id.account_change_span)
    RelativeLayout accountChangeSpan;
    @Bind(R.id.line_location_name)
    ImageView lineLocationName;
    @Bind(R.id.go_pic3)
    ImageView goPic3;
    @Bind(R.id.change_birth)
    RelativeLayout changeBirth;
    @Bind(R.id.line_update)
    ImageView lineUpdate;
    @Bind(R.id.go_pic4)
    ImageView goPic4;
    @Bind(R.id.update_check)
    RelativeLayout updateCheck;
    @Bind(R.id.exit)
    Button exit;

    private UserInfoResponse.DataBean userInfo;
    private ProgressDialog dialog;
    private final static int REQUEST_ICON = 204;
    private NormalDialog normalDialog;
    private final static int ACCOUNT_CHANGE = 305;

    @Override
    protected int getLayoutResId() {
        return R.layout.setting_fg;
    }

    @Override
    protected String title() {
        return "设置";
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
        userIco = (CircleImageView) viewRoot.findViewById(R.id.user_ico);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            userInfo = (UserInfoResponse.DataBean) intent.getSerializableExtra("userinfo");
            if (userInfo != null) {
                Presenter.getInstance(getContext()).getPlaceErrorImage(userIco, userInfo.getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                if (userInfo.getVip() == 1) {
                    vipFlg = (ImageView) viewRoot.findViewById(R.id.vip_flg);
                    /*vipFlg.setVisibility(View.VISIBLE);*/
                }
            }
        }
        cacheSize = (TextView) viewRoot.findViewById(R.id.cache_size);
        File file = new File(getContext().getExternalCacheDir().getPath());
        try {
            cacheSize.setText(CacheCleanManager.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialog = new ProgressDialog(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.user_head_icon_change, R.id.user_name_change, R.id.change_male, R.id.change_birth, R.id.exit, R.id.update_check, R.id.some_problems, R.id.account_change_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_head_icon_change:
                LocalLog.d(TAG, "个人资料");
                if (userInfo != null) {
                    Intent userInfoIntent = new Intent();
                    userInfoIntent.putExtra("userinfo", userInfo);
                    userInfoIntent.setClass(getContext(), UserInfoSettingActivity.class);
                    startActivityForResult(userInfoIntent, REQUEST_ICON);
                }
                break;
            case R.id.user_name_change:
                LocalLog.d(TAG, "账号设置");
                if (userInfo != null) {
                    Intent userInfoIntent = new Intent();
                    userInfoIntent.putExtra("userinfo", userInfo);
                    userInfoIntent.setClass(getContext(), AccoutManagerActivity.class);
                    startActivity(userInfoIntent);
                }
                break;
            case R.id.change_male:
                LocalLog.d(TAG, "清除缓存");
                CacheCleanManager.cleanInternalCache(getContext());
                File file = new File(getContext().getExternalCacheDir().getPath());
                try {
                    cacheSize.setText(CacheCleanManager.getCacheSize(file));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.change_birth:
                LocalLog.d(TAG, "关于我们");
                startActivity(AboutActivity.class, null);
                break;
            case R.id.exit:
                LocalLog.d(TAG, "退出APP");
                if (normalDialog == null) {
                    normalDialog = new NormalDialog(getContext());
                    normalDialog.setMessage("确定退出当前账号!");
                    normalDialog.setYesOnclickListener("确定", new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            normalDialog.dismiss();
                            quitApp();
                        }
                    });
                    normalDialog.setNoOnclickListener("取消", new NormalDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            normalDialog.dismiss();
                        }
                    });
                }
                if (isAdded() && !normalDialog.isShowing()) {
                    normalDialog.show();
                }
                break;
            case R.id.update_check:
                Beta.checkUpgrade();
                break;
            case R.id.some_problems:
                LocalLog.d(TAG, "常见问题!");
                startActivity(QuestionActivity.class, null);
                break;
            case R.id.account_change_span:
                if (userInfo != null) {
                    Intent userInfoIntent = new Intent();
                    userInfoIntent.putExtra("userinfo", userInfo);
                    userInfoIntent.setClass(getContext(), ChangeAccountActivity.class);
                    startActivityForResult(userInfoIntent, ACCOUNT_CHANGE);
                }
            default:
                break;
        }
    }

    private void quitApp() {
        Map<String, String> param = new HashMap<>();
        param.put("userid", Presenter.getInstance(getActivity()).getId() + "");
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlLogOut, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                       /* final boolean isauthWx = UMShareAPI.get(getContext()).isAuthorize(getActivity(), SHARE_MEDIA.WEIXIN);
                        final boolean isauthQq = UMShareAPI.get(getContext()).isAuthorize(getActivity(), SHARE_MEDIA.QQ);
                        if (isauthWx) {
                            LocalLog.d(TAG, "解除微信授权");
                            UMShareAPI.get(getActivity()).deleteOauth(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                            return;
                        }

                        if (isauthQq) {
                            LocalLog.d(TAG, "解除QQ授权");
                            UMShareAPI.get(getActivity()).deleteOauth(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                            return;
                        }
                        Presenter.getInstance(getContext()).setId(-1);
                        Presenter.getInstance(getContext()).steLogFlg(false);
                        Presenter.getInstance(getContext()).setToken(getContext(), "");
                        getActivity().finish();
                        System.exit(0);*/
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
        final boolean isauthWx = UMShareAPI.get(getContext()).isAuthorize(getActivity(), SHARE_MEDIA.WEIXIN);
        final boolean isauthQq = UMShareAPI.get(getContext()).isAuthorize(getActivity(), SHARE_MEDIA.QQ);
        if (isauthWx) {
            LocalLog.d(TAG, "解除微信授权");
            UMShareAPI.get(getActivity()).deleteOauth(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
            return;
        }

        if (isauthQq) {
            LocalLog.d(TAG, "解除QQ授权");
            UMShareAPI.get(getActivity()).deleteOauth(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
            return;
        }
        Presenter.getInstance(getContext()).setId(-1);
        Presenter.getInstance(getContext()).steLogFlg(false);
        Presenter.getInstance(getContext()).setToken(getContext(), "");
        //getActivity().finish();
        //System.exit(0);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setAction("login_other_phone");
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_ICON) {
                    if (data != null) {
                        String ico = data.getStringExtra(getContext().getPackageName() + "avatar");
                        LocalLog.d(TAG, "ico = " + ico);
                        if (!TextUtils.isEmpty(ico)) {
                            Presenter.getInstance(getContext()).getImage(ico, userIco);
                        }
                        UserInfoResponse.DataBean dataBean = (UserInfoResponse.DataBean) data.getSerializableExtra("userinfo");
                        if (dataBean != null) {
                            userInfo = dataBean;
                            try {
                                ChatUserInfo chatUserInfo = new ChatUserInfo(dataBean.getId() + "", dataBean.getNickname(), "", dataBean.getAvatar());
                                RongYunUserInfoManager.getInstance().refreshUserInfoDBAndCache(chatUserInfo);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (requestCode == ACCOUNT_CHANGE) {
                    LocalLog.d(TAG, "进行过账号切换!");
                    getUserInfo();
                }
                break;
            default:
                break;
        }
    }

    private void getUserInfo() {
        Presenter.getInstance(getActivity()).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(getContext()), null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    userInfo = userInfoResponse.getData();
                    if (userInfo != null) {
                        Presenter.getInstance(getContext()).getPlaceErrorImage(userIco, userInfo.getAvatar(), R.drawable.default_head_ico, R.drawable.default_head_ico);
                    }
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean == null) {

                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(getContext()).release();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(getContext()).onSaveInstanceState(outState);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            LocalLog.d(TAG, "解除授权成功");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(getContext(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(getContext(), "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
