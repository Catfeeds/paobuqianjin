package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.VipFragment;

/**
 * Created by pbq on 2018/4/25.
 */

public class VipActivity extends BaseActivity {
    private final static String TAG = VipActivity.class.getSimpleName();
    private VipFragment vipFragment = new VipFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vip_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.vip_container, vipFragment)
                .show(vipFragment).commit();
    }
}
