package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.login.ForgetPassFragment;

/**
 * Created by pbq on 2018/1/25.
 */

public class LoginForgetPassActivity extends BaseActivity {
    private final static String TAG = LoginForgetPassActivity.class.getSimpleName();
    private ForgetPassFragment forgetPassFragment = new ForgetPassFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_forget_password_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.forget_password_fg_container, forgetPassFragment)
                .show(forgetPassFragment)
                .commit();
    }
}
