package com.paobuqianjin.pbq.step.view.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.CheckSignCodeParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostPassWordParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CheckSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GetSignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PassWordResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ForgetPassWordInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/25.
 */

public class ForgetPassFragment extends BaseBarStyleTextViewFragment implements ForgetPassWordInterface {
    private final static String TAG = ForgetPassFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.sign_code)
    EditText signCode;
    @Bind(R.id.sign_code_request)
    TextView signCodeRequest;
    @Bind(R.id.new_password)
    EditText newPassword;
    @Bind(R.id.confirm_password)
    EditText confirmPassword;
    @Bind(R.id.confirm)
    Button confirm;
    PostPassWordParam postPassWordParam;

    @Override
    protected int getLayoutResId() {
        return R.layout.log_forget_password;
    }

    @Override
    protected String title() {
        return "忘记密码";
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
    }

    @Override
    public void response(PassWordResponse passWordResponse) {
        if (passWordResponse.getError() == 0) {
            Toast.makeText(getContext(), "密码修改成功，可以用新密码登录了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(GetSignCodeResponse getSignCodeResponse) {
        LocalLog.d(TAG, "GetSignCodeResponse() enter " + getSignCodeResponse.toString());
        if (getSignCodeResponse.getError() == 0) {
            LocalLog.d(TAG, "验证码发送成功");
        }
    }

    @Override
    public void response(CheckSignCodeResponse checkSignCodeResponse) {
        if (checkSignCodeResponse.getError() == 0) {
            Presenter.getInstance(getContext()).postNewPassWord(postPassWordParam);
        }
    }

    @OnClick({R.id.sign_code_request, R.id.confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_code_request:
                LocalLog.d(TAG, "获取验证码");
                Presenter.getInstance(getContext()).getSignCodePassWord(phone.getText().toString());
                break;
            case R.id.confirm:
                postPassWordParam = new PostPassWordParam();
                postPassWordParam.setCode(signCode.getText().toString())
                        .setMobile(phone.getText().toString())
                        .setPassword(newPassword.getText().toString());
                if (newPassword.getText().toString() == null || !newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(getContext(), "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
               /* *//*发送验证码之后必须携带用户ID*//*
                CheckSignCodeParam checkSignCodeParam = new CheckSignCodeParam();
                checkSignCodeParam.setCode(signCode.getText().toString());
                checkSignCodeParam.setUserid(Presenter.getInstance(getContext()).getId());
                Presenter.getInstance(getContext()).checkSignCodePassWord(checkSignCodeParam);*/
                break;
        }
    }
}
