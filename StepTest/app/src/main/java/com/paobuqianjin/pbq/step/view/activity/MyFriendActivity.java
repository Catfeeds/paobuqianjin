package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.FollowOtoFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.MyFriendFragment;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyFriendActivity extends BaseActivity {
    private final static String TAG = MyFriendActivity.class.getSimpleName();
    private final static String ACTION_STRANGE_ACTION = "com.paobuqianjin.pbq.step.STRANGE_ACTION";
    private final static String ACTION_FRIEND_ACTION = "com.paobuqianjn.step.FRIEND_ACTION";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_friend_activity_layout);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            MyFriendFragment myFriendFragment = new MyFriendFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.my_friend_container, myFriendFragment)
                    .show(myFriendFragment)
                    .commit();
        }
    }
}
