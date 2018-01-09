package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleTagFragment;

/**
 * Created by pbq on 2018/1/8.
 */

public class CirCleTagActivity extends BaseActivity {
    private final static String TAG = CirCleTagActivity.class.getSimpleName();
    private CircleTagFragment circleTagFragment = new CircleTagFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_activity_layout);
        
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.tag_container, circleTagFragment)
                .show(circleTagFragment)
                .commit();
    }
}
