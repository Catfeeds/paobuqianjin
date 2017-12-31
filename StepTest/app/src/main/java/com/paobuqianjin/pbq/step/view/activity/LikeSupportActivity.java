package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.LikeSupportFragment;

/**
 * Created by pbq on 2017/12/30.
 */

public class LikeSupportActivity extends BaseActivity {
    private LikeSupportFragment likeSupportFragment = new LikeSupportFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.like_support_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.like_support_container, likeSupportFragment)
                .show(likeSupportFragment).commit();
    }
}
