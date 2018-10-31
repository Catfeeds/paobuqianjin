package com.paobuqianjin.pbq.step.activity.base;

import android.content.Context;
import android.webkit.WebView;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by pbq on 2018/7/20.
 */

public class BannerImageLoader extends ImageLoader {
    WebView webView;

    @Override
    public void displayImage(final Context context, final Object path, ImageView imageView) {
        if (path instanceof AdObject && path != null) {
            Presenter.getInstance(context).getPlaceErrorImage(imageView, ((AdObject) path).getImg_url(), R.drawable.null_bitmap, R.drawable.null_bitmap);
            /*imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(((AdObject) path).getTarget_url())) {
                        return;
                    }
                    if (webView == null) {
                        webView = new WebView(context);
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        // 设置可以访问文件
                        webSettings.setAllowFileAccess(true);
                        // 设置支持缩放
                        webSettings.setBuiltInZoomControls(true);
                        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                        // webSettings.setDatabaseEnabled(true);
                        // 使用localStorage则必须打开
                        webSettings.setDomStorageEnabled(true);
                        webSettings.setGeolocationEnabled(true);
                    }
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            return true;
                        }
                    });
                    webView.loadUrl(((AdObject) path).getTarget_url());
                }
            });*/
        }

    }
}
