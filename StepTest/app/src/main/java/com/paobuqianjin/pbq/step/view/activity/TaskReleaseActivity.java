package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskPersonFragment;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskSponsorFragment;

/**
 * Created by pbq on 2018/1/26.
 */

public class TaskReleaseActivity extends BaseActivity {
    private final static String TAG = TaskReleaseActivity.class.getSimpleName();
    private ReleaseTaskSponsorFragment releaseTaskSponsorFragment;
    private ReleaseTaskPersonFragment releaseTaskPersonFragment;
    private final static String PKG_ACTION = "com.paobuqianjin.person.PKG_ACTION";
    private final static String SPOSNOR_ACTION = "com.paobuqianjin.person.SPONSOR_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_release_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        if (PKG_ACTION.equals(intent.getAction())) {
            int style = intent.getIntExtra(getPackageName() + "style", -1);
            releaseTaskPersonFragment = new ReleaseTaskPersonFragment();
            releaseTaskPersonFragment.setStyle(style);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.task_release_container, releaseTaskPersonFragment)
                    .show(releaseTaskPersonFragment)
                    .commit();
        } else if (SPOSNOR_ACTION.equals(intent.getAction())) {
            releaseTaskSponsorFragment = new ReleaseTaskSponsorFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.task_release_container, releaseTaskSponsorFragment)
                    .show(releaseTaskSponsorFragment)
                    .commit();
        } else {
            LocalLog.d(TAG, "illeag action");
            return;
        }

    }
}
