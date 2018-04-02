package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

/**
 * Created by pbq on 2018/4/2.
 */

public class AgreementActivity extends BaseActivity {
    private UserServiceProtcolFragment userServiceProtcolFragment = new UserServiceProtcolFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agreement_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.agreement_container, userServiceProtcolFragment)
                .show(userServiceProtcolFragment)
                .commit();
    }
}
