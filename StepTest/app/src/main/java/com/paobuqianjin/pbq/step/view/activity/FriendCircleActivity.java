package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.FriendCircleFragment;

/**
 * Created by pbq on 2018/12/12.
 */

public class FriendCircleActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_circle_activity_layout);
        FriendCircleFragment friendCircleFragment = new FriendCircleFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.friend_circle_container, friendCircleFragment)
                .show(friendCircleFragment)
                .commit();
    }
}
