package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.MyLikeFriendFragment;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyLikeFriendActivity extends BaseActivity {
    private final static String TAG = MyLikeFriendActivity.class.getSimpleName();
    private MyLikeFriendFragment myLikeFriendFragment = new MyLikeFriendFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_like_activity_layout);
    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.my_like_container, myLikeFriendFragment)
                .show(myLikeFriendFragment)
                .commit();
    }
}
