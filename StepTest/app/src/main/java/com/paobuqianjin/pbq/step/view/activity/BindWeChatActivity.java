package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

/**
 * Created by pbq on 2018/2/6.
 */

public class BindWeChatActivity extends BaseActivity {
    private final static String TAG = BindWeChatActivity.class.getSimpleName();
    private BindSignCodeFragment bindSignCodeFragment = new BindSignCodeFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_wx_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.bind_container, bindSignCodeFragment)
                .show(bindSignCodeFragment)
                .commit();
    }
}
