package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.AddFriendFragment;

/**
 * Created by pbq on 2018/1/18.
 */

public class AddFriendAddressActivity extends BaseActivity {
    private final static String TAG = AddFriendAddressActivity.class.getSimpleName();
    private AddFriendFragment addFriendFragment = new AddFriendFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_wx_qq_phone_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.add_friend_container, addFriendFragment)
                .show(addFriendFragment)
                .commit();
    }
}
