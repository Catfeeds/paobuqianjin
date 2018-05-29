package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CrashToParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BindCardListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CrashInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.AgreementActivity;
import com.paobuqianjin.pbq.step.view.activity.CrashActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pbq on 2018/1/17.
 */

public class CrashFragment extends BaseBarStyleTextViewFragment implements CrashInterface {
    private final static String TAG = CrashFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.crash_style)
    TextView crashStyle;
    @Bind(R.id.wechat_pay_icon)
    ImageView wechatPayIcon;
    @Bind(R.id.wx_dear_name)
    TextView wxDearName;
    @Bind(R.id.go_to_wx)
    ImageView goToWx;
    @Bind(R.id.wechat_pay)
    RelativeLayout wechatPay;
    @Bind(R.id.money_ico)
    ImageView moneyIco;
    @Bind(R.id.crash_span)
    RelativeLayout crashSpan;
    @Bind(R.id.protocl_text)
    TextView protoclText;
    @Bind(R.id.protocl_pay)
    RelativeLayout protoclPay;
    @Bind(R.id.confirm_crash)
    Button confirmCrash;
    @Bind(R.id.can_crash)
    EditText canCrash;
    @Bind(R.id.select_icon)
    ImageView selectIcon;
    private float canCrashNum;
    private final static String CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";
    private final static int CRASH_PROTOCAL = 206;
    private boolean isauthWx;
    private ProgressDialog dialog;
    private String action = "";
    CrashToParam crashToParam = new CrashToParam();

    @Override
    protected String title() {
        return "提现";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.crash_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);
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
        canCrash = (EditText) viewRoot.findViewById(R.id.can_crash);
        protoclText = (TextView) viewRoot.findViewById(R.id.protocl_text);
        confirmCrash = (Button) viewRoot.findViewById(R.id.confirm_crash);
        selectIcon = (ImageView) viewRoot.findViewById(R.id.select_icon);
        dialog = new ProgressDialog(getContext());
        wxDearName = (TextView) viewRoot.findViewById(R.id.wx_dear_name);
        if (!Presenter.getInstance(getActivity()).getReadCrashProtocol(getActivity())) {
            LocalLog.d(TAG, "未阅读过提现协议");
            confirmCrash.setEnabled(false);
            confirmCrash.setBackgroundColor(getResources().getColor(R.color.color_8a8a8a));
            selectIcon.setImageDrawable(null);
        } else {
            LocalLog.d(TAG, "已阅读");
            confirmCrash.setEnabled(true);
            selectIcon.setImageResource(R.drawable.selected_icon);
        }
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            canCrashNum = intent.getFloatExtra("total", 0.0f);
            String canCrashStrFormat = getActivity().getString(R.string.can_crash);
            String canCrashStr = String.format(canCrashStrFormat, canCrashNum);
            canCrash.setHint(canCrashStr);
        }
        String part1 = "请认真阅读", part2 = "《提现协议》";
        String protoclStr = part1 + part2;
        SpannableString spannableString = new SpannableString(protoclStr);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.color_6c71c4));
        spannableString.setSpan(colorSpan, part1.length(), protoclStr.length()
                , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        protoclText.setText(spannableString);

        isauthWx = UMShareAPI.get(getContext()).isAuthorize(getActivity(), SHARE_MEDIA.WEIXIN);
        if (isauthWx) {
            UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        UMShareAPI.get(getContext()).release();
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick({R.id.wechat_pay, R.id.confirm_crash, R.id.protocl_pay, R.id.select_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wechat_pay:
                LocalLog.d(TAG, "绑定微信或者更换绑定的微信");
                if (!isauthWx) {
                    LocalLog.d(TAG, "授权");
                    UMShareAPI.get(getActivity()).doOauthVerify(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
                }
                break;
            case R.id.confirm_crash:
                LocalLog.d(TAG, "确认转出");
                if (TextUtils.isEmpty(canCrash.getText().toString())) {
                    LocalLog.e(TAG, "填入提现金额");
                    return;
                }
                if (Float.parseFloat(canCrash.getText().toString()) > canCrashNum) {
                    ToastUtils.showLongToast(getActivity(), "可用余额不足");
                    return;
                }
                action = "wx";
                if ("wx".equals(action)) {
                    if (TextUtils.isEmpty(crashToParam.getWx_openid())) {
                        ToastUtils.showLongToast(getContext(), "至少绑定一个微信");
                        return;
                    }
                }
                crashToParam.setAmount(canCrash.getText().toString()).setTypeid(String.valueOf(1));
                Presenter.getInstance(getContext()).postCrashTo(crashToParam);
                break;
            case R.id.protocl_pay:
                Intent intent = new Intent();
                intent.setClass(getActivity(), AgreementActivity.class);
                intent.setAction(CRASH_ACTION);
                startActivityForResult(intent, CRASH_PROTOCAL);
                break;
            case R.id.select_icon:
                boolean isCurrentProtocalPayState = Presenter.getInstance(getActivity()).getReadCrashProtocol(getActivity());
                isCurrentProtocalPayState = !isCurrentProtocalPayState;
                Presenter.getInstance(getActivity()).setReadCrashProtocol(getActivity(), isCurrentProtocalPayState);

                if (!isCurrentProtocalPayState) {
//                    LocalLog.d(TAG, "未阅读过提现协议");
                    confirmCrash.setEnabled(false);
                    confirmCrash.setBackgroundColor(getResources().getColor(R.color.color_8a8a8a));
                    selectIcon.setImageDrawable(null);
                } else {
//                    LocalLog.d(TAG, "已阅读");
                    confirmCrash.setEnabled(true);
                    confirmCrash.setBackgroundColor(getResources().getColor(R.color.color_6c71c4));
                    selectIcon.setImageResource(R.drawable.selected_icon);
                }
                break;
        }
    }

    @Override
    public void response(BindCardListResponse bindCardListResponse) {
        LocalLog.d(TAG, "BindCardListResponse() enter " + bindCardListResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (bindCardListResponse.getError() == 0) {
        } else if (bindCardListResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            Toast.makeText(getContext(), bindCardListResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void response(CrashResponse crashResponse) {
        LocalLog.d(TAG, "CrashResponse()  enter  " + crashResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (crashResponse.getError() == 0) {
            ToastUtils.showLongToast(getActivity(), crashResponse.getMessage());
            ((CrashActivity) getActivity()).showCrashResult(crashResponse);
        } else if (crashResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            ToastUtils.showLongToast(getActivity(), crashResponse.getMessage());
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, "授权开始callback:" + share_media.toString());
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            LocalLog.d(TAG, "授权成功callback:" + share_media.toString());
            String temp = "";
            if (share_media.ordinal() == SHARE_MEDIA.WEIXIN.ordinal()) {
                if (!isauthWx) {
                    UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, this);
                    isauthWx = true;
                    return;
                }
                for (String key : map.keySet()) {
                    temp = temp + key + ":" + map.get(key) + "\n";
                    switch (key) {
                        case "openid":
                            crashToParam.setWx_openid(map.get(key));
                            break;
                        case "screen_name":
                            wxDearName.setText(map.get(key));
                            continue;
                        case "iconurl":

                            continue;
                        case "province":

                            continue;
                        case "city":

                            continue;
                        case "gender":

                            continue;
                        case "unionid":

                            break;
                        default:
                            continue;
                    }
                }
                SocializeUtils.safeCloseDialog(dialog);
            }

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(getContext()).onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CRASH_PROTOCAL) {
//                confirmCrash.setEnabled(true);
//                confirmCrash.setBackgroundColor(getResources().getColor(R.color.color_6c71c4));
//                selectIcon.setImageResource(R.drawable.selected_icon);
            }
        }
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

}
