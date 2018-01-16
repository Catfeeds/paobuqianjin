package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.StepDollarFragment;

/**
 * Created by pbq on 2018/1/16.
 */

public class StepDollarActivity extends BaseActivity {
    private final static String TAG = StepDollarActivity.class.getSimpleName();
    private StepDollarFragment stepDollarFragment = new StepDollarFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_dollar_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_dollar_container, stepDollarFragment)
                .show(stepDollarFragment)
                .commit();
    }
}
