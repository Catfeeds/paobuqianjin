package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.DanFragment;

/**
 * Created by pbq on 2018/1/17.
 */

public class DanActivity extends BaseActivity {
    private final static String TAG = DanActivity.class.getSimpleName();
    private DanFragment danFragment = new DanFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dan_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.dan_container, danFragment)
                .show(danFragment)
                .commit();
    }
}
