package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.honor.NearByFragment;

/**
 * Created by pbq on 2018/8/4.
 */

public class NearByActivity extends BaseBarActivity {
    private NearByFragment nearByFragment = new NearByFragment();

    @Override
    protected String title() {
        return "附近的人";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.near_by_activity_layout);
    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.near_by_container, nearByFragment)
                .show(nearByFragment)
                .commit();
    }
}
