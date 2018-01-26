package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.TaskDetailFragment;

/**
 * Created by pbq on 2018/1/26.
 */

public class TaskDetailActivity extends BaseActivity {
    private final static String TAG = TaskDetailActivity.class.getSimpleName();
    private TaskDetailFragment taskDetailFragment = new TaskDetailFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.task_detail_container, taskDetailFragment)
                .show(taskDetailFragment)
                .commit();
    }
}
