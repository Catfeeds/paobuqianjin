package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.SystemMsgFragment;

/**
 * Created by pbq on 2018/1/23.
 */

public class SystemMsgActivity extends BaseActivity {
    private final static String TAG = SystemMsgActivity.class.getSimpleName();
    private SystemMsgFragment systemMsgFragment = new SystemMsgFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_msg_activity_layout);

    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.system_msg_container, systemMsgFragment)
                .show(systemMsgFragment)
                .commit();
    }
}
