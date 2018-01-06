package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.UserCenterFragment;

/**
 * Created by pbq on 2018/1/6.
 */

public class UserCenterActivity extends BaseActivity {
    private final static String TAG = UserCenterActivity.class.getSimpleName();
    private UserCenterFragment userCenterFragment = new UserCenterFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_center_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_center_fg_container, userCenterFragment)
                .show(userCenterFragment)
                .commit();
    }
}
