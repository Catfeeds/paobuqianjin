package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.TaskFragment;

public class TaskActivity extends BaseActivity {

    TaskFragment taskFragment = new TaskFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.task_container, taskFragment)
                .show(taskFragment)
                .commit();
    }
}
