package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.honor.FriendStepsDanFragment;

/**
 * Created by pbq on 2018/1/19.
 */

public class FriendStepDanActivity extends BaseActivity {
    private final static String TAG = FriendStepDanActivity.class.getSimpleName();
    private FriendStepsDanFragment friendStepsDanFragment = new FriendStepsDanFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_step_dan_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_dan_fg_container, friendStepsDanFragment)
                .show(friendStepsDanFragment)
                .commit();
    }
}
