package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.PassAccountManagerFragment;

/**
 * Created by pbq on 2018/4/2.
 */

public class AccoutManagerActivity extends BaseActivity {
    private final static String TAG = AccoutManagerActivity.class.getSimpleName();
    private PassAccountManagerFragment passAccountManagerFragment = new PassAccountManagerFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accout_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.accout_manager_conatiner, passAccountManagerFragment)
                .show(passAccountManagerFragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
