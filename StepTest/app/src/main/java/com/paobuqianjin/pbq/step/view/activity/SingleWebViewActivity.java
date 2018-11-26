package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.j256.ormlite.stmt.query.In;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SingleWebViewActivity extends BaseBarActivity implements BaseBarActivity.ToolBarListener {

    private static final String TAG = SingleWebViewActivity.class.getSimpleName();
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.att_money)
    LinearLayout attMoney;

    private String urlStr;
    private PopupWindow popupRedPkgWindow;
    private View popRedPkgView;
    private ImageView openRedPkgView;
    private String red_id;
    boolean canRec = true;

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

    public void popRoundRedPkg(final String redid) {
        if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
            LocalLog.d(TAG, "红包在显示");
            return;
        }
        popRedPkgView = View.inflate(this, R.layout.round_pkg_pop_window, null);
        ImageView cancle = (ImageView) popRedPkgView.findViewById(R.id.cancel_red);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupRedPkgWindow.dismiss();
            }
        });
        openRedPkgView = (ImageView) popRedPkgView.findViewById(R.id.round_open);
        popupRedPkgWindow = new PopupWindow(popRedPkgView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupRedPkgWindow = null;
            }
        });
        popupRedPkgWindow.setFocusable(true);
        popupRedPkgWindow.setOutsideTouchable(true);
        popupRedPkgWindow.setBackgroundDrawable(new BitmapDrawable());
        TranslateAnimation animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        openRedPkgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "领取遍地红包");
                final Rotate3dAnimation animation = new Rotate3dAnimation(0, 359, view.getWidth() / 2f, view.getHeight() / 2f, 30, true);
                animation.setDuration(500);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setFillAfter(true);
                view.setAnimation(animation);
                view.startAnimation(animation);
                if (openRedPkgView == null) {
                    return;
                }
                openRedPkgView.setEnabled(false);
                pullDownAroundRedBag(redid);
                openRedPkgView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (openRedPkgView != null && openRedPkgView.getVisibility() == View.VISIBLE
                                && popupRedPkgWindow != null
                                && popupRedPkgWindow.isShowing()) {
                            openRedPkgView.clearAnimation();
                            openRedPkgView.setEnabled(true);
                        }
                    }
                }, 2 * 60 * 1000);
                attMoney.setVisibility(View.VISIBLE);

            }
        });
        popupRedPkgWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);
    }

    private void pullDownAroundRedBag(final String redid) {
        if (TextUtils.isEmpty(redid)) return;
        Map<String, String> params = new HashMap<>();
        params.put("redid", redid);
        params.put("is_turn", String.valueOf(1));
        Presenter.getInstance(this).postPaoBuSimple(NetApi.receiveAroundRed, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showShortToast(SingleWebViewActivity.this, "领取成功");
                // TODO: hqp 2018/8/16
                String result = "";
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String allmoney = jsonObj.getString("amount");
                    float redMoney = Float.parseFloat(allmoney);
                    if (redMoney > 0.0f) {
                        result = allmoney;
                    } else {
                        result = getString(R.string.slow_text);
                    }
                } catch (Exception e) {
                    result = getString(R.string.error_red);
                }
                if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
                    popupRedPkgWindow.dismiss();
                }
                //TODO hqp go to red detail
                Intent intent = new Intent();
                intent.setClass(SingleWebViewActivity.this, RoundRedDetailActivity.class);
                intent.putExtra(getPackageName() + "red_id", redid);
                intent.putExtra(getPackageName() + "red_result", result);
                startActivity(intent);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
                    popupRedPkgWindow.dismiss();
                }
                String result = "";
                if (errorBean == null) {
                    result = getString(R.string.error_red);
                } else {
                    if (errorBean.getError() == 2) {
                        LocalLog.d(TAG, "领红包已经达到上限!需要开通会员");
                        PaoToastUtils.showLongToast(SingleWebViewActivity.this, errorBean.getMessage());
                        return;
                    } else {
                        result = getString(R.string.slow_text);
                    }

                }
                Intent intent = new Intent();
                intent.setClass(SingleWebViewActivity.this, RoundRedDetailActivity.class);
                intent.putExtra(getPackageName() + "red_id", redid);
                intent.putExtra(getPackageName() + "red_result", result);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupRedPkgWindow != null) {
            popupRedPkgWindow.dismiss();
            popupRedPkgWindow = null;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_web_view);
        ButterKnife.bind(this);
        setToolBarListener(this);
        urlStr = getIntent().getStringExtra("url");
        red_id = getIntent().getStringExtra("red_id");
        LocalLog.d(TAG, "urlStr: " + urlStr + "red_id =" + red_id);
        if (urlStr.startsWith("https://www.bianxianguanjia.com")
                && !TextUtils.isEmpty(red_id)) {
            attMoney.setVisibility(View.VISIBLE);
            String partA = "点击抽奖键盘，无论抽到什么，点击";
            String partB = "免费领取";
            String partC = "就有红包。每天八次机会，八个红包！";
            String totalStr = partA + partB + partC;
            SpannableString spannableString = new SpannableString(totalStr);
            spannableString.setSpan(new AbsoluteSizeSpan(14, true), 0, partA.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(SingleWebViewActivity.this, R.color.color_golden)),
                    0, partA.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannableString.setSpan(new AbsoluteSizeSpan(17, true), partA.length(), (partA + partB).length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(SingleWebViewActivity.this, R.color.red0)),
                    partA.length(), (partA + partB).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(14, true), (partA + partB).length(), totalStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(SingleWebViewActivity.this, R.color.color_golden)),
                    (partA + partB).length(), totalStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            desc.setText(spannableString);
        }
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
                if (!TextUtils.isEmpty(url) && url.startsWith("https://www.bianxianguanjia.com") && !urlStr.equals(url)) {
                    LocalLog.d(TAG, "非转盘广告，不能抽红包");
                    attMoney.setVisibility(View.GONE);
                    canRec = false;
                }
                if (urlStr.startsWith("https://www.bianxianguanjia.com")
                        && !url.startsWith("https://www.bianxianguanjia.com")
                        && !TextUtils.isEmpty(red_id)) {
                    LocalLog.d(TAG, "点击了广告，去领红包");
                    webview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (canRec) {
                                LocalLog.d(TAG, "转盘广告");
                                popRoundRedPkg(red_id);
                            } else {
                                LocalLog.d(TAG, "非转盘广告");
                            }
                        }
                    }, 2000);

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
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    barTitle.setText(title);
                }
            }
        });
        webview.loadUrl(urlStr);
    }
}
