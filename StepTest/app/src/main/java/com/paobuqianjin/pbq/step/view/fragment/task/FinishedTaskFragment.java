package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.adapter.task.TaskAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/25.
 */

public class FinishedTaskFragment extends BaseFragment {
    private final static String TAG = FinishedTaskFragment.class.getSimpleName();
    @Bind(R.id.finished_task_recycler)
    RecyclerView finishedTaskRecycler;
    private LinearLayoutManager layoutManager;
    private TaskAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_finished_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        layoutManager = new LinearLayoutManager(getContext());
        finishedTaskRecycler = (RecyclerView) viewRoot.findViewById(R.id.finished_task_recycler);
        finishedTaskRecycler.setLayoutManager(layoutManager);
        adapter = new TaskAdapter(getContext());
        finishedTaskRecycler.setAdapter(adapter);
    }

    public void setData(List<?> data) {
        if (finishedTaskRecycler != null) {
            adapter.notifyDataSetChanged(data);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
