package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.SponsorDetailFragment;

/**
 * Created by pbq on 2018/1/23.
 */
/*
@className :SponsorDetailActivity
*@date 2018/1/23
*@author
*@description  赞助商详情，从红包点击进入
*/
public class SponsorDetailActivity extends BaseActivity {
    private final static String TAG = SponsorDetailActivity.class.getSimpleName();
    private SponsorDetailFragment sponsorDetailFragment = new SponsorDetailFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.sponsor_container, sponsorDetailFragment)
                .show(sponsorDetailFragment)
                .commit();
    }
}
