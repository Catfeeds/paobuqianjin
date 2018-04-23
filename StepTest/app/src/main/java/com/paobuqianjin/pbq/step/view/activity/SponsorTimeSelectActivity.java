package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.SponsorTimeSelectFragment;

/**
 * Created by pbq on 2018/4/23.
 */

public class SponsorTimeSelectActivity extends BaseActivity {
    private final static String TAG = SponsorSelectActivity.class.getSimpleName();
    private SponsorTimeSelectFragment sponsorTimeSelectFragment = new SponsorTimeSelectFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_time_select_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.sponsor_time_container, sponsorTimeSelectFragment)
                .show(sponsorTimeSelectFragment).commit();
    }
}
