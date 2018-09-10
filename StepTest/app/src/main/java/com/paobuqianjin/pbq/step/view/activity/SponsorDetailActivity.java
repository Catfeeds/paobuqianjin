package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.RedDetailFragment;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_activity_layout);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            int red_id = intent.getIntExtra(getPackageName() + "red_id", -1);
            if (red_id == -1) {
                SponsorDetailFragment sponsorDetailFragment = new SponsorDetailFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.sponsor_container, sponsorDetailFragment)
                        .show(sponsorDetailFragment)
                        .commit();
            } else {
                RedDetailFragment redDetailFragment = new RedDetailFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.sponsor_container, redDetailFragment)
                        .show(redDetailFragment)
                        .commit();
            }
        }
    }
}
