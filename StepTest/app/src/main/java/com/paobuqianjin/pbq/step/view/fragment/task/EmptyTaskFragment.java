package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

/**
 * Created by pbq on 2018/1/26.
 */

public class EmptyTaskFragment extends BaseFragment {
    private final static String TAG = EmptyTaskFragment.class.getSimpleName();

    @Override
    protected int getLayoutResId() {
        return R.layout.task_empty_fg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
    }
}
