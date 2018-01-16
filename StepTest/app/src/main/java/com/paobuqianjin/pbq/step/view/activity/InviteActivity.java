package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.InviteFragment;

/**
 * Created by pbq on 2018/1/16.
 */

public class InviteActivity extends BaseActivity {
    private final static String TAG = InviteActivity.class.getSimpleName();
    private InviteFragment inviteFragment = new InviteFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.invite_container,inviteFragment)
                .show(inviteFragment)
                .commit();
    }
}
