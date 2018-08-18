package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.MyReleaseDetailFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.MySponsorReleaseFragment;

/**
 * Created by pbq on 2018/1/25.
 */
/*
@className :MyReleaseDetailActivity
*@date 2018/1/25
*@author
*@description    发布任务的详情
*/
public class MyReleaseDetailActivity extends BaseActivity {
    private final static String PERSON_TASK_ACTION = "com.paobuqianjin.pbq.step.PERSON_ACTION";
    private final static String SPONSOR_TASK_ACTION = "com.paobuqianjin.pbq.step.SPONSOR_ACTION";
    private MyReleaseDetailFragment releaseFragment = new MyReleaseDetailFragment();
    private MySponsorReleaseFragment mySponsorReleaseFragment = new MySponsorReleaseFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_detail_activity_layout);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null && intent.getAction() != null) {
            if (PERSON_TASK_ACTION.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.release_container, releaseFragment)
                        .show(releaseFragment)
                        .commit();
            } else if (SPONSOR_TASK_ACTION.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.release_container, mySponsorReleaseFragment)
                        .show(mySponsorReleaseFragment)
                        .commit();
            }
        }
    }
}
