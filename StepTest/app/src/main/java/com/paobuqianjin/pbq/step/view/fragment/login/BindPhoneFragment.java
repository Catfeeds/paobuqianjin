package com.paobuqianjin.pbq.step.view.fragment.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostWxQqBindPhoneParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LogBindPhoneResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.LoginBindPhoneInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/31.
 */

public class BindPhoneFragment extends BaseBarStyleTextViewFragment implements LoginBindPhoneInterface {
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
    /*    @Bind(R.id.sign_code_t)
        TextView signCodeT;*/
    @Bind(R.id.pass_word_edit)
    EditText passWordEdit;
    @Bind(R.id.pass_y)
    ImageView passY;
    @Bind(R.id.phone_edit)
    EditText phoneEdit;
    @Bind(R.id.sign_code_edit)
    EditText signCodeEdit;
    @Bind(R.id.forgotpwd_getCode)
    Button forgotpwdGetCode;
    private boolean showSignPass = false;
    private Thread thread;
    public int T = 60; //倒计时时长
    private Handler mHandler = new Handler();

    @Override
    protected String title() {
        return "绑定手机号";
    }

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
        phoneEdit = (EditText) viewRoot.findViewById(R.id.phone_edit);
        confirm = (Button) viewRoot.findViewById(R.id.btn_confirm);
        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String forgotpwdAccountStr = phoneEdit.getText().toString().trim();
                if (forgotpwdAccountStr.length() == 11) {
                    forgotpwdGetCode.setEnabled(true);
                } else {
                    forgotpwdGetCode.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        signCodeEdit = (EditText) viewRoot.findViewById(R.id.sign_code_edit);
        passWordEdit = (EditText) viewRoot.findViewById(R.id.pass_word_edit);
        signCodeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkConfirmEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passWordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkConfirmEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void checkConfirmEnable() {
        String passWord = passWordEdit.getText().toString().trim();
        String signcode = signCodeEdit.getText().toString().trim();
        String forgotpwdAccountStr = phoneEdit.getText().toString().trim();
        if (forgotpwdAccountStr.length() == 11 && signcode.length() == 6 && passWord.length() >= 8 && passWord.length() <= 16) {
            confirm.setEnabled(true);
            confirm.setBackground(getDrawableResource(R.drawable.rect_angle_background));
        } else {
            confirm.setEnabled(false);
            confirm.setBackground(getDrawableResource(R.drawable.rect_angle_diss_bt));
        }
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
            PaoToastUtils.showShortToast(getContext(), "验证码发送成功");
        }
    }

    @Override
    public void response(LogBindPhoneResponse logBindPhoneResponse) {
        if (logBindPhoneResponse.getError() == 0) {
            PaoToastUtils.showShortToast(getContext(), "绑定手机号成功");
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().onBackPressed();
        } else if (logBindPhoneResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            PaoToastUtils.showShortToast(getContext(), logBindPhoneResponse.getMessage());
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

    @OnClick({R.id.pass_y, R.id.btn_confirm, R.id.forgotpwd_getCode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pass_y:
                if (!showSignPass) {
                    LocalLog.d(TAG, " onClick() 设置显示密码!");
                    passWordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showSignPass = true;
                    passY.setImageDrawable(getResources().getDrawable(R.drawable.forgotpwd_eyes));
                } else {
                    LocalLog.d(TAG, " onClick() 设置不显示密码!");
                    passWordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showSignPass = false;
                    passY.setImageDrawable(getResources().getDrawable(R.drawable.register_eyes));
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
                if (TextUtils.isEmpty(passWordEdit.getText().toString())) {
                    PaoToastUtils.showLongToast(getContext(), "密码必填");
                    return;
                }
                postWxQqBindPhoneParam.setCode(signCodeEdit.getText().toString())
                        .setMobile(phoneEdit.getText().toString()).setPassword(passWordEdit.getText().toString());

                Presenter.getInstance(getContext()).bindLoginPhone(postWxQqBindPhoneParam);
                break;
            case R.id.forgotpwd_getCode:
                LocalLog.d(TAG, "获取验证码");
                if (Presenter.getInstance(getContext()).getSignCodeLoginBind(phoneEdit.getText().toString())) {
                    if (thread != null && thread.isAlive()) {
                        return;
                    } else {
                        thread = new Thread(new MyCountDownTimer());
                        thread.start();
                    }
                }
                break;
        }
    }


    private class MyCountDownTimer implements Runnable {
        public void run() {

            //倒计时开始，循环
            while (T > 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (forgotpwdGetCode != null) {
                            forgotpwdGetCode.setClickable(false);
                            forgotpwdGetCode.setText(T + "秒");
                        }
                    }
                });
                try {
                    Thread.sleep(1000); //强制线程休眠1秒，就是设置倒计时的间隔时间为1秒。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                T--;
            }
            //倒计时结束，也就是循环结束
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (forgotpwdGetCode != null) {
                        forgotpwdGetCode.setClickable(true);
                        forgotpwdGetCode.setText("获取验证码");
                    }
                }
            });
            T = 60; //最后再恢复倒计时时长
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (thread != null) {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            PaoToastUtils.showShortToast(getActivity(), errorCode.getMessage());
        }
    }
}
