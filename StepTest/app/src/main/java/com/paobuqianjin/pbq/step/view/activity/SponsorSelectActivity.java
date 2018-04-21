package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.SponsorManagerFragment;
import com.paobuqianjin.pbq.step.view.fragment.task.TargetPeopleFragment;

/**
 * Created by pbq on 2018/4/21.
 */

public class SponsorSelectActivity extends BaseActivity {
    private final static String TAG = SponsorSelectActivity.class.getSimpleName();
    private TargetPeopleFragment targetPeopleFragment = new TargetPeopleFragment();
    private SponsorManagerFragment sponsorManagerFragment = new SponsorManagerFragment();
    private static String TARGET_PEOPLE_ACTION = "com.paobuqianjin.pbq.step.TARGET_ACTION";
    private static String SPONSOR_INFO_ACTION = "com.paobuqianjin.pbq.step.SPONSOR_INFO_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_select_activity);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            if (TARGET_PEOPLE_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, "选择目标人群");
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.select_container, targetPeopleFragment)
                        .show(targetPeopleFragment).commit();
            } else if (SPONSOR_INFO_ACTION.equals(intent.getAction())) {
                LocalLog.d(TAG, "商铺信息");
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.select_container, sponsorManagerFragment)
                        .show(sponsorManagerFragment).commit();
            }
        }
    }
}
