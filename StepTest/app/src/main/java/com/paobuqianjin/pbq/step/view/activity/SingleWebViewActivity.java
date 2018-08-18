package com.paobuqianjin.pbq.step.view.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SingleWebViewActivity extends BaseBarActivity {

    private static final String TAG = SingleWebViewActivity.class.getSimpleName();
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.bar_title)
    TextView barTitle;

    private String urlStr;

    @Override
    protected String title() {
        String title = getIntent().getStringExtra("title");
        return TextUtils.isEmpty("title") ? "" : title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_web_view);
        ButterKnife.bind(this);
        urlStr = getIntent().getStringExtra("url");

        LocalLog.d(TAG,"urlStr: "+urlStr);

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
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return super.shouldOverrideUrlLoading(view,url);
            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    barTitle.setText(title);
                }
            }
        });
        webview.loadUrl(urlStr);
    }
}
