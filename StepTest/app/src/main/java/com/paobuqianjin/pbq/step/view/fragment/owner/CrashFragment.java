package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CrashToParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.BindCardListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CrashInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.BindWeChatActivity;
import com.paobuqianjin.pbq.step.view.activity.CrashActivity;
import com.paobuqianjin.pbq.step.view.activity.UserBindBankListActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.umeng.socialize.utils.DeviceConfig.context;

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

    private int selectBankId;

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
        Presenter.getInstance(getContext()).getUserBankCard();
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
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @OnClick({R.id.wechat_pay, R.id.confirm_crash})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wechat_pay:
                LocalLog.d(TAG, "绑定微信或者更换绑定的微信");
                startActivity(UserBindBankListActivity.class, null, false, null);
                break;
            case R.id.confirm_crash:
                LocalLog.d(TAG, "确认转出");
                CrashToParam crashToParam = new CrashToParam();
                crashToParam.setAmount(String.valueOf(1.0f)).setId(selectBankId);
                Presenter.getInstance(getContext()).postCrashTo(crashToParam);
                break;
        }
    }

    @Override
    public void response(BindCardListResponse bindCardListResponse) {
        LocalLog.d(TAG, "BindCardListResponse() enter " + bindCardListResponse.toString());
        if (bindCardListResponse.getError() == 0) {
            selectBankId = bindCardListResponse.getData().get(0).getId();
        } else if (bindCardListResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }

    }

    @Override
    public void response(CrashResponse crashResponse) {
        LocalLog.d(TAG, "CrashResponse()  enter  " + crashResponse.toString());
        if (crashResponse.getError() == 0) {
            Toast.makeText(getContext(), crashResponse.getMessage(), Toast.LENGTH_SHORT).show();
            ((CrashActivity) getActivity()).showCrashResult(crashResponse);
        } else if (crashResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
