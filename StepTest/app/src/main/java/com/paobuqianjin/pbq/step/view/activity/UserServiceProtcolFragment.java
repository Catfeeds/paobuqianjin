package com.paobuqianjin.pbq.step.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ProtocolResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ProtocolInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/2.
 */

public class UserServiceProtcolFragment extends BaseBarStyleTextViewFragment implements ProtocolInterface {
    private final static String TAG = UserServiceProtcolFragment.class.getSimpleName();
    public final static String USER_SERVICE_AGREEMENT_ACTION = "com.paobuqianjin.pbq.step.SERVICE_ACTION";
    public final static String USER_CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";
    private final static String USER_INVITE_AGREEMENT_ACTION = "com.paobuqianjin.step.pbq.INVITE_ACTION";
    private final static String TICK_RED_RULE = "com.paobuqianjin.pbq.step.TICK_RED_RULE";
    private final static String PERSON_RED_RULE = "com.paobuqianjin.pbq.step.PERSON_RED_RULE";
    private final static String ROUND_RED_RULE = "com.paobuqianjin.pbq.step.ROUND_RED_RULE";
    private final static String NEAR_RED_RULE = "com.paobuqianjin.pbq.step.NEAR_RED_RULE";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.webview)
    WebView webview;
    private String action = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.user_protcol_fg;
    }

    @Override
    protected String title() {
        return "";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
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
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (USER_SERVICE_AGREEMENT_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, "用户服务协议");
                Presenter.getInstance(getContext()).protocol("1");
                action = "1";
            } else if (USER_CRASH_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, "用户提现协议");
                Presenter.getInstance(getContext()).protocol("4");
                action = "4";
            } else if (USER_INVITE_AGREEMENT_ACTION.equals(intent.getAction())) {
                Presenter.getInstance(getContext()).protocol("2");
                action = "2";
            } else if (TICK_RED_RULE.equals(intent.getAction())) {
                Presenter.getInstance(getContext()).protocol("8");
                action = "8";
            } else if (PERSON_RED_RULE.equals(intent.getAction())) {
                Presenter.getInstance(getContext()).protocol("7");
                action = "7";
            } else if (ROUND_RED_RULE.equals(intent.getAction())) {
                Presenter.getInstance(getContext()).protocol("5");
                action = "5";
            } else if (NEAR_RED_RULE.equals(intent.getAction())) {
                Presenter.getInstance(getContext()).protocol("6");
                action = "6";
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(ErrorCode errorCode) {

    }

    @Override
    public void response(ProtocolResponse protocolResponse) {
        if (protocolResponse.getError() == 0) {
            if (webview == null) {
                return;
            }
            if ("4".equals(action)) {
                getActivity().setResult(Activity.RESULT_OK);
            }
            setTitle(protocolResponse.getData().getTitle());
            String content = protocolResponse.getData().getContent();

            //支持javascript
            webview.getSettings().setJavaScriptEnabled(true);
// 设置可以支持缩放
            webview.getSettings().setSupportZoom(true);
// 设置出现缩放工具
            webview.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
//            webview.getSettings().setUseWideViewPort(true);
//自适应屏幕
//            webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//            webview.getSettings().setLoadWithOverviewMode(true)
            webview.getSettings().setDefaultTextEncodingName("utf-8");
            webview.loadDataWithBaseURL(null, content, "text/html", "utf-8", null); // 加载定义的代码，并设定编码格式和字符集。
        } else if (protocolResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

}
