package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskContainFragment;
import com.paobuqianjin.pbq.step.view.fragment.task.ReleaseTaskSponsorFragment;

/**
 * Created by pbq on 2018/1/26.
 */

public class TaskReleaseActivity extends BaseActivity {
    private final static String TAG = TaskReleaseActivity.class.getSimpleName();
    private ReleaseTaskContainFragment releaseTaskContainFragment = new ReleaseTaskContainFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_release_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.task_release_container, releaseTaskContainFragment)
                .show(releaseTaskContainFragment)
                .commit();
    }
}
