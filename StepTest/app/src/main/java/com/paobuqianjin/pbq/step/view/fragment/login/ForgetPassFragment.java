package com.paobuqianjin.pbq.step.view.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassWordParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PassWordResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ForgetPassWordInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.ForgotPasswordActivity;
import com.paobuqianjin.pbq.step.view.activity.LoginActivity;
import com.paobuqianjin.pbq.step.view.activity.RegisterActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassFragment extends BaseFragment implements ForgetPassWordInterface {
    private static final String TAG = "ForgetPassFragment";

    private boolean showLoginPass = false, showSignPass = false;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.forgotpwd_account)
    EditText forgotpwdAccount;
    @Bind(R.id.forgotpwd_indntifying_code)
    EditText forgotpwdIindntifyingCode;
    @Bind(R.id.forgotpwd_getCode)
    Button forgotpwdGetCode;
    @Bind(R.id.forgotpwd_pwd)
    EditText forgotpwdPwd;
    @Bind(R.id.forgotPwd_eyes)
    ImageView forgotPwd_eyes;
    @Bind(R.id.forgotpwd_ok)
    Button forgotpwdOk;
    private String[] userInfo;
    public int T = 60; //倒计时时长
    private Handler mHandler = new Handler();
    private Thread thread;

    PostPassWordParam postPassWordParam;

    @Override
    protected int getLayoutResId() {
        return R.layout.log_forget_password;
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
        forgotpwdAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String forgotpwdAccountStr = forgotpwdAccount.getText().toString().trim();
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

        forgotpwdPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String forgotpwdAccountStr = forgotpwdAccount.getText().toString().trim();
                String forgotpwdPwdStr = forgotpwdPwd.getText().toString().trim();
                String forgotpwdIindntifyingCodeStr = forgotpwdIindntifyingCode.getText().toString().trim();
                if ((forgotpwdAccountStr.length() == 11) && (forgotpwdPwdStr.length() >= 6) && (forgotpwdPwdStr.length() <= 12) && (forgotpwdIindntifyingCodeStr.length() == 6)) {
                    forgotpwdOk.setEnabled(true);
                } else {
                    forgotpwdOk.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        forgotpwdIindntifyingCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String forgotpwdAccountStr = forgotpwdAccount.getText().toString().trim();
                String forgotpwdPwdStr = forgotpwdPwd.getText().toString().trim();
                String forgotpwdIindntifyingCodeStr = forgotpwdIindntifyingCode.getText().toString().trim();
                if ((forgotpwdAccountStr.length() == 11) && (forgotpwdPwdStr.length() == 6) && (forgotpwdIindntifyingCodeStr.length() == 6)) {
                    forgotpwdOk.setEnabled(true);
                } else {
                    forgotpwdOk.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return rootView;
    }


    @Override
    public void response(PassWordResponse passWordResponse) {
        if (passWordResponse.getError() == 0) {
            Toast.makeText(getContext(), "密码修改成功，请重新登录", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        } else if (passWordResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }


    @Override
    public void response(GetSignCodeResponse getSignCodeResponse) {
        LocalLog.d(TAG, "GetSignCodeResponse() enter " + getSignCodeResponse.toString());
        if (getSignCodeResponse.getError() == 0) {
            Toast.makeText(getContext(), "验证码发送成功", Toast.LENGTH_SHORT).show();
        } else if (getSignCodeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void response(CheckSignCodeResponse checkSignCodeResponse) {
        if (checkSignCodeResponse.getError() == 0) {

        } else if (checkSignCodeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }


    @OnClick({R.id.img_back, R.id.forgotpwd_getCode, R.id.forgotPwd_eyes, R.id.forgotpwd_ok})
    public void onClickTab(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.img_back:
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    break;
                case R.id.forgotpwd_getCode:

                    if (Presenter.getInstance(getContext()).getSignCodePassWord(forgotpwdAccount.getText().toString())) {
                        if (thread != null && thread.isAlive()) {
                            return;
                        } else {
                            thread = new Thread(new MyCountDownTimer());
                            thread.start();
                        }
                    }
                    break;
                case R.id.forgotPwd_eyes:
                    if (!showLoginPass) {
                        LocalLog.d(TAG, " onTabLogin() 设置显示密码!");
                        forgotpwdPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showLoginPass = true;
                        forgotPwd_eyes.setImageDrawable(getResources().getDrawable(R.drawable.forgotpwd_eyes));
                    } else {
                        LocalLog.d(TAG, " onTabLogin() 设置不显示密码!");
                        forgotpwdPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showLoginPass = false;
                        forgotPwd_eyes.setImageDrawable(getResources().getDrawable(R.drawable.register_eyes));
                    }
                    break;

                case R.id.forgotpwd_ok:
                    postPassWordParam = new PostPassWordParam();
                    postPassWordParam.setCode(forgotpwdIindntifyingCode.getText().toString())
                            .setMobile(forgotpwdAccount.getText().toString())
                            .setPassword(forgotpwdPwd.getText().toString());
                    Presenter.getInstance(getContext()).postNewPassWord(postPassWordParam);
                    break;

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
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        } else {
            Toast.makeText(getContext(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private class MyCountDownTimer implements Runnable {
        public void run() {

            //倒计时开始，循环
            while (T > 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (forgotpwdPwd != null) {
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
    }
}












