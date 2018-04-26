package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.pay.PayVipFriendFragment;

/**
 * Created by pbq on 2018/4/26.
 */

public class VipPayFriendActivity extends BaseActivity {
    private final static String TAG = VipPayFriendActivity.class.getSimpleName();
    private PayVipFriendFragment payVipFriendFragment = new PayVipFriendFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_vip_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.pay_vip_container, payVipFriendFragment)
                .show(payVipFriendFragment)
                .commit();
    }
}
