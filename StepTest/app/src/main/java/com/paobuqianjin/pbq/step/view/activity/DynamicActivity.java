package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.DynamicDetailFragment;

/**
 * Created by pbq on 2017/12/29.
 */

public class DynamicActivity extends BaseActivity {
    private DynamicDetailFragment dynamicDetailFragment = new DynamicDetailFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_detail_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_dynamic_detail, dynamicDetailFragment)
                .show(dynamicDetailFragment).commit();
    }
}
