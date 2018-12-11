package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.activity.PaoBuPayActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/12/10.
 */

public class H5VipFragment extends BaseFragment {
    private final static String TAG = H5VipFragment.class.getSimpleName();
    @Bind(R.id.webview)
    WebView webview;
    String Url;
    @Bind(R.id.vip_friend)
    Button vipFriend;
    @Bind(R.id.vip_self)
    Button vipSelf;
    @Bind(R.id.pay_button)
    LinearLayout payButton;
    private final static String ACTION_GOLDEN_VIP = "com.paobuqianjin.pbq.step.VIP_GOLDEN_ACTION";
    private final static int PAY_VIP_SPONSOR_SELF_RESULT = 501;
    private UserInfoResponse.DataBean currentUser;

    public void setUrl(String url) {
        this.Url = url;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.h5_vip_fg;
    }

    @Override
    protected void initView(View viewRoot) {
        webview = (WebView) viewRoot.findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // webSettings.setDatabaseEnabled(true);
        // 使用localStorage则必须打开
        webSettings.setDomStorageEnabled(true);
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setGeolocationEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        if (!TextUtils.isEmpty(Url)) {
            if (Url.equals(NetApi.urlGoldenH5)) {
                payButton = (LinearLayout) viewRoot.findViewById(R.id.pay_button);
                payButton.setVisibility(View.VISIBLE);
                currentUser = Presenter.getInstance(getContext()).getCurrentUser();
                vipSelf = (Button) viewRoot.findViewById(R.id.vip_self);
                if (currentUser == null) {
                    LocalLog.d(TAG, "未能获取UserInfo！");

                } else {
                    if (currentUser.getGvip() == 1) {
                        vipSelf.setText("已购买");
                    }
                }
            }
            webview.loadUrl(Url);
        }
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

    @OnClick(R.id.vip_self)
    public void onClick() {
        LocalLog.d(TAG, "pay for  self  sponsor vip");
        if (currentUser.getGvip() == 1) {
            LocalLog.d(TAG, "已经是商家VIP");
            return;
        }
        Intent intent = new Intent();
        intent.setAction(ACTION_GOLDEN_VIP);
        intent.setClass(getContext(), PaoBuPayActivity.class);
        startActivityForResult(intent, PAY_VIP_SPONSOR_SELF_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PAY_VIP_SPONSOR_SELF_RESULT) {
                LocalLog.d(TAG, "购买商家金牌会员成功！");
                vipSelf.setText("已购买");
                vipSelf.setEnabled(false);
            }
        }
    }
}
