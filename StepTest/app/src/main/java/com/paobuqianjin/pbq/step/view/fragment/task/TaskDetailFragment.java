package com.paobuqianjin.pbq.step.view.fragment.task;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

/**
 * Created by pbq on 2018/1/26.
 */

public class TaskDetailFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = TaskDetailFragment.class.getSimpleName();

    @Override
    protected int getLayoutResId() {
        return R.layout.task_detaile_fg;
    }

    @Override
    protected String title() {
        return "任务详情";
    }
}
