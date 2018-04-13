package com.paobuqianjin.pbq.step.view.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassFragment extends BaseFragment implements ForgetPassWordInterface {
    private final static String TAG = ForgotPasswordActivity.class.getSimpleName();

    private boolean showLoginPass = false, showSignPass = false;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.forgotpwd_account)
    EditText forgotpwd_account;
    @Bind(R.id.forgotpwd_indntifying_code)
    EditText forgotpwd_indntifying_code;
    @Bind(R.id.forgotpwd_getCode)
    Button forgotpwd_getCode;
    @Bind(R.id.forgotpwd_pwd)
    EditText forgotpwd_pwd;
    @Bind(R.id.forgotPwd_eyes)
    ImageView forgotPwd_eyes;
    @Bind(R.id.forgotpwd_ok)
    Button forgotpwd_ok;
    private String[] userInfo;

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
        return rootView;
    }



    @Override
    public void response(PassWordResponse passWordResponse) {
        if (passWordResponse.getError() == 0) {
            Toast.makeText(getContext(), "密码修改成功，可以用新密码登录了", Toast.LENGTH_SHORT).show();
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
            LocalLog.d(TAG, "验证码发送成功");
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
                    LocalLog.d(TAG, "onTabLogin() 请求验证码!");
                    Presenter.getInstance(getContext()).getSignCodePassWord(forgotpwd_account.getText().toString());
                    break;
                case R.id.forgotPwd_eyes:
                    if (!showLoginPass) {
                        LocalLog.d(TAG, " onTabLogin() 设置显示密码!");
                        forgotpwd_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        showLoginPass = true;
                        forgotPwd_eyes.setImageDrawable(getResources().getDrawable(R.drawable.forgotpwd_eyes));
                    } else {
                        LocalLog.d(TAG, " onTabLogin() 设置不显示密码!");
                        forgotpwd_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        showLoginPass = false;
                        forgotPwd_eyes.setImageDrawable(getResources().getDrawable(R.drawable.register_eyes));
                    }
                    break;

                case R.id.forgotpwd_ok:
                    postPassWordParam = new PostPassWordParam();
                    postPassWordParam.setCode(forgotpwd_indntifying_code.getText().toString())
                            .setMobile(forgotpwd_account.getText().toString())
                            .setPassword(forgotpwd_pwd.getText().toString());
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
        }
    }

}












