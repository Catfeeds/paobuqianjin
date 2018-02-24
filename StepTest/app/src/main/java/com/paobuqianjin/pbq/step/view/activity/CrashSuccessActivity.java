package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.CrashSuccessFragment;

/**
 * Created by pbq on 2018/2/24.
 */

public class CrashSuccessActivity extends BaseActivity {
    private final static String TAG = CrashSuccessActivity.class.getSimpleName();
    private CrashSuccessFragment crashSuccessFragment = new CrashSuccessFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crash_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.crash_result_container, crashSuccessFragment)
                .show(crashSuccessFragment)
                .commit();
    }
}
