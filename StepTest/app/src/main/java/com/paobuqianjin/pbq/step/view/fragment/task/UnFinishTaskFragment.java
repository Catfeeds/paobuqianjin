package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.task.TaskAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/25.
 */

public class UnFinishTaskFragment extends BaseFragment {
    private final static String TAG = UnFinishTaskFragment.class.getSimpleName();
    @Bind(R.id.task_unfinished_recycler)
    RecyclerView taskUnfinishedRecycler;
    LinearLayoutManager layoutManager;
    private TaskAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_unfinish_fg;
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
        taskUnfinishedRecycler = (RecyclerView) viewRoot.findViewById(R.id.task_unfinished_recycler);
        taskUnfinishedRecycler.setLayoutManager(layoutManager);
        adapter = new TaskAdapter(getContext());
        taskUnfinishedRecycler.setAdapter(adapter);

    }


    public void setData(List<?> data) {
        LocalLog.d(TAG,"setData() enter");
        if (taskUnfinishedRecycler != null) {
            adapter.notifyDataSetChanged(data);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
