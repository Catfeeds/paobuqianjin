package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleMemberManagerFragment;

/**
 * Created by pbq on 2017/12/18.
 */

public class MemberManagerActivity extends BaseActivity {
    private final static String TAG = MemberManagerActivity.class.getSimpleName();
    private CircleMemberManagerFragment circleMemberManagerFragment = new CircleMemberManagerFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_manager_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.member_manger_container, circleMemberManagerFragment)
                .show(circleMemberManagerFragment).commit();
    }
}
