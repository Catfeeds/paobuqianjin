package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.j256.ormlite.stmt.query.In;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.login.BindPhoneFragment;

/**
 * Created by pbq on 2018/2/6.
 */

public class BindWeChatActivity extends BaseActivity {
    private final static String TAG = BindWeChatActivity.class.getSimpleName();
    private BindSignCodeFragment bindSignCodeFragment = new BindSignCodeFragment();
    private BindPhoneFragment bindPhoneFragment = new BindPhoneFragment();
    private final static String BIND_WECHAT_ACTION = "com.paobuqianjin.step.BIND_WECHAT";
    private final static String BIND_PHONE = "com.paobuqianjin.step.BIND_PHONE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_wx_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();

        if (intent != null) {
            if (BIND_WECHAT_ACTION.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.bind_container, bindSignCodeFragment)
                        .show(bindSignCodeFragment)
                        .commit();
            } else if (BIND_PHONE.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.bind_container, bindPhoneFragment)
                        .show(bindPhoneFragment)
                        .commit();
            }
        }
    }
}
