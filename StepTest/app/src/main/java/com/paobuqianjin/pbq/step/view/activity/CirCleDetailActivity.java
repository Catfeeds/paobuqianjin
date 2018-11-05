package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleDetailFragment;

/**
 * Created by pbq on 2017/12/29.
 */

public class CirCleDetailActivity extends BaseActivity {
    private final static String TAG = CirCleDetailActivity.class.getSimpleName();
    /*private CircleDetailAdminFragment circleDetailAdminFragment = new CircleDetailAdminFragment();*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_detail_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        CircleDetailFragment circleDetailFragment = new CircleDetailFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.circle_detail_container, circleDetailFragment)
                .show(circleDetailFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
