package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.QueryFollowStateParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.NetStringCallBack;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.fragment.home.HomePageFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by pbq on 2018/5/3.
 */

public class LiveDetailFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = LiveDetailFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.btn_status)
    Button btnStatus;

    /**
     * 活动id
     */
    private int target;
    private int actid;

    @Override
    protected int getLayoutResId() {
        return R.layout.live_detail_fg;
    }

    @Override
    protected String title() {
        return "活动详情";
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
        btnStatus = (Button) viewRoot.findViewById(R.id.btn_status);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            final String url = intent.getStringExtra(getPackageName());
            int is_process = intent.getIntExtra("is_process",0);
            int is_receive = intent.getIntExtra("is_receive",0);
            actid = intent.getIntExtra("actid",0);
            target = intent.getIntExtra("target",0);

            switch (is_process) {
                case 0:
                    btnStatus.setText("未开始");
                    break;
                case 1:
                    btnStatus.setText("进行中");
                    if (HomePageFragment.lastStep >= target) {
                        if (is_receive == 0) {
                            btnStatus.setText("领取奖励");
                            btnStatus.setEnabled(true);
                        } else {
                            btnStatus.setText("已领取");
                        }
                    }
                    break;
                case 2:
                    btnStatus.setText("已结束");
                    break;
                case 3:
                    btnStatus.setText("奖励已经被领完");
                    break;
            }

            LocalLog.d(TAG, "");
            if (!TextUtils.isEmpty(url)) {
                webView = (WebView) viewRoot.findViewById(R.id.webView);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
                webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
                webSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
                webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
                webSettings.setAllowFileAccess(true); // 允许访问文件
                webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
                webSettings.setSupportZoom(true); // 支持缩放

                //主要用于平板，针对特定屏幕代码调整分辨率
                DisplayMetrics metrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int mDensity = metrics.densityDpi;
                if (mDensity == 240) {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                } else if (mDensity == 160) {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
                } else if (mDensity == 120) {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
                } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                } else if (mDensity == DisplayMetrics.DENSITY_TV) {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                } else {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
                }

                /**
                 * 用WebView显示图片，可使用这个参数 设置网页布局类型：
                 * 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小
                 * 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
                 */
                webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

                webView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //WebView加载web资源
                        if (webView == null) {
                            return;
                        }
                        webView.loadUrl(url);
                        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                                view.loadUrl(url);
                                return true;
                            }
                        });
                    }
                }, 200);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_status)
    public void onClick() {
        Map<String, String> params = new HashMap<>();
        params.put("actid", actid+"");
        params.put("userid", Presenter.getInstance(getActivity()).getCurrentUser().getId()+"");
        getRedBagMoney(params);
    }

    public void getRedBagMoney(Map<String,String> map) {
        LocalLog.d(TAG, "getRedBagMoney() "+new Gson().toJson(map));
        OkHttpUtils
                .post()
                .addHeader("headtoken", Engine.getEngine(getActivity()).getToken(getActivity()))
                .url(NetApi.urlLiveGetRed)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        LocalLog.d(TAG,"result----------> "+s);
                        ErrorCode result = new Gson().fromJson(s, ErrorCode.class);
                        if (result.getError() == 0) {
                            PaoToastUtils.showShortToast(getActivity(), "领取成功");
                            btnStatus.setEnabled(false);
                            EventBus.getDefault().post(new LiveFragment.PaoMessageEvent(1));
                        }else{
                            PaoToastUtils.showShortToast(getActivity(),result.getMessage());
                        }
                    }
                });
    }
}
