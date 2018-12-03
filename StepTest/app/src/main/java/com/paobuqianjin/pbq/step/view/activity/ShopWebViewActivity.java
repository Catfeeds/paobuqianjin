package com.paobuqianjin.pbq.step.view.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/11/13.
 */

/*
@className :ShopWebViewActivity  商城H5 单独处理类，对广告劫持进行处理
*@date 2018/11/13
*@author
*@description
*/
public class ShopWebViewActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    private static final String TAG = ShopWebViewActivity.class.getSimpleName();
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.att_money)
    LinearLayout attMoney;
    private String urlStr;

    @Override
    protected String title() {
        String title = getIntent().getStringExtra("title");
        return TextUtils.isEmpty("title") ? "" : title;
    }

    @Override
    public void clickRight() {

    }

    @Override
    public void clickLeft() {
        if (webview != null && webview.canGoBack()) {
            webview.goBack();
        } else {
            onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_web_view);
        ButterKnife.bind(this);
        setToolBarListener(this);
        showLoadingBar();
        urlStr = getIntent().getStringExtra("url");
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
        webview.setWebViewClient(new WebViewClient() {
            @TargetApi(21)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                final String scheme = request.getUrl().getScheme().trim();
                final String url = request.getUrl().toString();
                LocalLog.d(TAG, "URL = " + url);
                if (!TextUtils.isEmpty(url) && (url.contains("www.runmoney.shop") || url.contains("shop.runmoneyin.com")
                        || url.contains("pbqj") || url.contains("qq.com") || url.contains("wx.qq")
                        || url.contains("weixin") || url.contains("ali")) || url.contains("static.gtimg.com")
                        || url.contains("b.yzcdn.cn") || url.contains("tencent") || url.contains("taobao") || url.contains("rumcdn")) {
                    return super.shouldInterceptRequest(view, request);
                } else {
                    LocalLog.d(TAG, "拦截非法请求!");
                    return new WebResourceResponse(null, null, null);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LocalLog.d(TAG, "url = " + url);
                if (url.startsWith("weixin://") || url.startsWith("alipays://")) {
                    try {
                        LocalLog.d(TAG, "APP");
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        LocalLog.d(TAG, "APP EXCEPTION");
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoadingBar();
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    barTitle.setText(title);
                }
            }
        });
        webview.loadUrl(urlStr);
    }

}
