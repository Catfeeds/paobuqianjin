package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.EditCircleFragment;

/**
 * Created by pbq on 2018/3/28.
 */

public class EditCircleActivity extends BaseActivity {
    private final static String TAG = EditCircleActivity.class.getSimpleName();
    private EditCircleFragment editCircleFragment = new EditCircleFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_circle_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.edit_circle_container, editCircleFragment)
                .show(editCircleFragment)
                .commit();
    }
}
