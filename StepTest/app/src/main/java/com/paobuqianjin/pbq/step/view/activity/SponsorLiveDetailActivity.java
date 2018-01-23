package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.SponsorLiveDetailFragment;

/**
 * Created by pbq on 2018/1/23.
 */
/*
@className :SponsorLiveDetailActivity
*@date 2018/1/23
*@author
*@description  赞助商活动详情
*/
public class SponsorLiveDetailActivity extends BaseActivity {
    private final static String TAG = SponsorLiveDetailActivity.class.getSimpleName();
    private SponsorLiveDetailFragment sponsorLiveDetailFragment = new SponsorLiveDetailFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_live_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
    }
}
