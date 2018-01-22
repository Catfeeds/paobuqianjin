package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.DynamicCreateFragment;

/**
 * Created by pbq on 2018/1/22.
 */

public class CreateDynamicActivity extends BaseActivity {
    private final static String TAG = CreateDynamicActivity.class.getSimpleName();
    private DynamicCreateFragment dynamicCreateFragment = new DynamicCreateFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_create_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.dynamic_create_container, dynamicCreateFragment)
                .show(dynamicCreateFragment)
                .commit();
    }
}
