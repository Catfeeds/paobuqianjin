package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.honor.CircleStepDanFragment;
import com.paobuqianjin.pbq.step.view.fragment.honor.FriendStepsDanFragment;

/**
 * Created by pbq on 2018/1/19.
 */

public class FriendStepDanActivity extends BaseActivity {
    private final static String TAG = FriendStepDanActivity.class.getSimpleName();
    private FriendStepsDanFragment friendStepsDanFragment = new FriendStepsDanFragment();
    private CircleStepDanFragment circleStepDanFragment = new CircleStepDanFragment();
    private final static String ACTION_FRIEND_HONOR = "com.paobuqianjin.pbq.ACTION_FRIEND_HONOR";
    private final static String ACTION_CIRCLE_HONOR = "com.paobuqianjin.pbq.ACTION_CIRCLE_HONOR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_step_dan_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            if (ACTION_FRIEND_HONOR.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.step_dan_fg_container, friendStepsDanFragment)
                        .show(friendStepsDanFragment)
                        .commit();
            } else if (ACTION_CIRCLE_HONOR.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.step_dan_fg_container, circleStepDanFragment)
                        .show(circleStepDanFragment)
                        .commit();
            }
        }
    }
}
