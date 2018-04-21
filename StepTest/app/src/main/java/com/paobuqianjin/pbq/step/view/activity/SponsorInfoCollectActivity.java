package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.SponsorInfoCollectFragment;

/**
 * Created by pbq on 2018/1/23.
 */
/*
@className :SponsorInfoCollectActivity
*@date 2018/1/23
*@author
*@description 商家信息采集
*/
public class SponsorInfoCollectActivity extends BaseActivity {
    private final static String TAG = SponsorInfoCollectActivity.class.getSimpleName();
    private SponsorInfoCollectFragment sponsorInfoCollectFragment = new SponsorInfoCollectFragment();
    private final static String SPONSOR_INFO_ACTION = "com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_info_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            if (SPONSOR_INFO_ACTION.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.sponsor_info_container, sponsorInfoCollectFragment)
                        .show(sponsorInfoCollectFragment)
                        .commit();
            }
        }
    }
}
