package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.FriendAddressFragment;

/**
 * Created by pbq on 2018/1/18.
 */

public class FriendAddressActivity extends BaseActivity {
    private final static String TAG = FriendAddressActivity.class.getSimpleName();
    private FriendAddressFragment friendAddressFragment = new FriendAddressFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_address_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.friend_address_container, friendAddressFragment)
                .show(friendAddressFragment)
                .commit();
    }
}
