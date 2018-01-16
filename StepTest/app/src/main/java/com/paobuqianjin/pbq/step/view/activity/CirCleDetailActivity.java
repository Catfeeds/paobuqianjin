package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleDetailFragment;

/**
 * Created by pbq on 2017/12/29.
 */

public class CirCleDetailActivity extends BaseActivity {
    private CircleDetailFragment circleDetailFragment = new CircleDetailFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_detail_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            int circleId = intent.getIntExtra(getPackageName() + "circleid", -1);
            int memberNum = intent.getIntExtra(getPackageName() + "membernum", -1);
            if (circleId != -1 && memberNum > 0) {
                circleDetailFragment.setCircleId(this, circleId, memberNum);
            }
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.circle_detail_container, circleDetailFragment)
                .show(circleDetailFragment)
                .commit();
    }
}
