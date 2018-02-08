package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.paobuqianjin.pbq.step.data.bean.gson.param.ThirdPartyLoginParam;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.BindWeChatActivity;
import com.paobuqianjin.pbq.step.view.activity.LoginActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/2/7.
 */

public class WeChatBindFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = WeChatBindFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.account_crash)
    TextView accountCrash;
    @Bind(R.id.accout_list)
    RecyclerView accoutList;
    @Bind(R.id.add_account)
    ImageView addAccount;
    @Bind(R.id.add_wx)
    TextView addWx;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.add_account_span)
    RelativeLayout addAccountSpan;
    @Bind(R.id.corirm)
    Button corirm;
    private ProgressDialog dialog;

    @Override
    protected String title() {
        return "选择提现账号";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.we_chat_bind_fg;
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
        dialog = new ProgressDialog(getContext());
        if (Build.VERSION.SDK_INT <= 23) {
            LocalLog.d(TAG, "version = 23");
            Config.isNeedAuth = true;
        } else {
            LocalLog.d(TAG, "version > 23");
            UMShareConfig config = new UMShareConfig();
            config.isNeedAuthOnGetUserInfo(true);
            UMShareAPI.get(getContext()).setShareConfig(config);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, "onStart() 开始授权");
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            LocalLog.d(TAG, "授权成功callback:" + share_media.toString());
            SocializeUtils.safeCloseDialog(dialog);
            Bundle bundle = new Bundle();
            String temp = "";
            if (share_media.ordinal() == SHARE_MEDIA.WEIXIN.ordinal()) {
                for (String key : map.keySet()) {
                    temp = temp + key + ":" + map.get(key) + "\n";
                    switch (key) {
                        case "openid":
                            LocalLog.d(TAG, "openid = " + map.get(key));
                            bundle.putString("openid", map.get(key));
                            continue;
                        case "screen_name":
                            LocalLog.d(TAG, "screen_name = " + map.get(key));
                            bundle.putString("screen_name", map.get(key));
                            continue;
                        case "iconurl":

                            continue;
                        case "province":

                            continue;
                        case "city":

                            continue;
                        case "gender":

                            continue;
                        default:
                            continue;
                    }
                }
            }
            startActivity(BindWeChatActivity.class, bundle);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            SocializeUtils.safeCloseDialog(dialog);
        }
    };

    @OnClick({R.id.add_account_span, R.id.corirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_account_span:
                LocalLog.d(TAG, "添加绑定账户");
                UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.corirm:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(getContext()).onSaveInstanceState(outState);
    }
}
