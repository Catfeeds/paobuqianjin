package com.paobuqianjin.pbq.step.view.fragment.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostWxQqBindPhoneParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LogBindPhoneResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ThirdPartyLoginResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.LoginBindPhoneInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/31.
 */

public class BindPhoneFragment extends BaseFragment implements LoginBindPhoneInterface {
    private final static String TAG = BindPhoneFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.phone_num)
    TextView phoneNum;
    @Bind(R.id.phone_span)
    RelativeLayout phoneSpan;
    @Bind(R.id.sign_code_num)
    TextView signCodeNum;
    @Bind(R.id.sign_code_span)
    RelativeLayout signCodeSpan;
    @Bind(R.id.pass_word)
    TextView passWord;
    @Bind(R.id.password_span)
    RelativeLayout passwordSpan;
    @Bind(R.id.btn_confirm)
    Button confirm;
    @Bind(R.id.sign_code_t)
    TextView signCodeT;
    @Bind(R.id.pass_word_edit)
    EditText passWordEdit;
    @Bind(R.id.pass_y)
    ImageView passY;
    @Bind(R.id.phone_edit)
    EditText phoneEdit;
    @Bind(R.id.sign_code_edit)
    EditText signCodeEdit;
    private boolean showSignPass = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.login_bind_phone_fg;
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
        barReturnDrawable = (ImageView) viewRoot.findViewById(R.id.bar_return_drawable);
        barReturnDrawable.setVisibility(View.GONE);
        barTitle = (TextView) viewRoot.findViewById(R.id.bar_title);
        barTitle.setText("绑定手机号");


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(GetSignCodeResponse getSignCodeResponse) {
        if (getSignCodeResponse.getError() == 0) {
            ToastUtils.showShortToast(getContext(), getSignCodeResponse.getMessage());
        }
    }

    @Override
    public void response(LogBindPhoneResponse logBindPhoneResponse) {
        if (logBindPhoneResponse.getError() == 0) {
            ToastUtils.showShortToast(getContext(), "绑定手机号成功");
            getActivity().setResult(Activity.RESULT_OK);
        } else if (logBindPhoneResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        } else {
            ToastUtils.showShortToast(getContext(), logBindPhoneResponse.getMessage());
        }
    }

    @Override
    public void response(CheckSignCodeResponse checkSignCodeResponse) {
        if (checkSignCodeResponse.getError() == 0) {
            /*PostWxQqBindPhoneParam postWxQqBindPhoneParam = new PostWxQqBindPhoneParam();
            if (dataBean.getQq_openid().equals("")) {
                if (!dataBean.getWx_openid().equals("")) {
                    postWxQqBindPhoneParam.setAction("wx");
                    postWxQqBindPhoneParam.setMobile(phoneEdit.getText().toString()).setOpenid(dataBean.getWx_openid());
                }
            } else {
                if (dataBean.getWx_openid().equals("")) {
                    postWxQqBindPhoneParam.setAction("qq");
                    postWxQqBindPhoneParam.setMobile(phoneEdit.getText().toString()).setOpenid(dataBean.getQq_openid());
                }
            }

            Presenter.getInstance(getContext()).bindLoginPhone(postWxQqBindPhoneParam);*/
        }
    }

    @OnClick({R.id.pass_y, R.id.btn_confirm, R.id.sign_code_t})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pass_y:
                if (!showSignPass) {
                    LocalLog.d(TAG, " onClick() 设置显示密码!");
                    passWordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showSignPass = true;
                    passY.setImageDrawable(getResources().getDrawable(R.drawable.pass_eye_yes));
                } else {
                    LocalLog.d(TAG, " onClick() 设置不显示密码!");
                    passWordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showSignPass = false;
                    passY.setImageDrawable(getResources().getDrawable(R.drawable.pass_eye_no));
                }
                break;
            case R.id.btn_confirm:
                LocalLog.d(TAG, "确认");
                /*CheckSignCodeParam checkSignCodeParam = new CheckSignCodeParam();
                if (signCodeEdit.getText().toString() == null || signCodeEdit.getText().toString().equals("")) {
                    LocalLog.d(TAG, "验证码不能为空");
                    return;
                }
                checkSignCodeParam.setCode(signCodeEdit.getText().toString());
                checkSignCodeParam.setUserid(dataBean.getId());
                Presenter.getInstance(getContext()).checkLoginBindPhone(checkSignCodeParam);*/
                PostWxQqBindPhoneParam postWxQqBindPhoneParam = new PostWxQqBindPhoneParam();
                postWxQqBindPhoneParam.setCode(signCodeEdit.getText().toString());

                Presenter.getInstance(getContext()).bindLoginPhone(postWxQqBindPhoneParam);
                break;
            case R.id.sign_code_t:
                LocalLog.d(TAG, "获取验证码");
                Presenter.getInstance(getContext()).getSignCodeLoginBind(phoneEdit.getText().toString());
                break;
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
        } else {
            ToastUtils.showShortToast(getActivity(), errorCode.getMessage());
        }
    }
}
