package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.CrashFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.CrashSuccessFragment;

/**
 * Created by pbq on 2018/1/17.
 */
public class CrashActivity extends BaseActivity {
    private final static String TAG = CrashActivity.class.getSimpleName();
    private CrashFragment crashFragment = new CrashFragment();
    private CrashSuccessFragment crashSuccessFragment = new CrashSuccessFragment();
    private final static String CRASH_ACTION = "com.paobuqianjin.pbq.step.CRASH_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crash_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null && CRASH_ACTION.equals(intent.getAction())) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.crash_container, crashFragment)
                    .show(crashFragment)
                    .commit();
        }
    }

    public void showCrashResult(CrashResponse crashResponse) {
        LocalLog.d(TAG, "showCrashResult() enter ");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.crash_container, crashSuccessFragment)
                .hide(crashFragment)
                .show(crashSuccessFragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
