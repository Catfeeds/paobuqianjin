package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;
import com.paobuqianjin.pbq.step.view.base.adapter.task.TaskAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

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
    @Bind(R.id.finish_scroll)
    BounceScrollView finishScroll;
    private LinearLayoutManager layoutManager;
    private TaskAdapter adapter;
    TaskFragment.ReloadDataInterface reloadDataInterface;

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

    public void setReloadDataInterface(TaskFragment.ReloadDataInterface reloadDataInterface) {
        this.reloadDataInterface = reloadDataInterface;
    }
    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        layoutManager = new LinearLayoutManager(getContext());
        finishedTaskRecycler = (RecyclerView) viewRoot.findViewById(R.id.finished_task_recycler);
        finishedTaskRecycler.setLayoutManager(layoutManager);
        adapter = new TaskAdapter(getContext());
        finishedTaskRecycler.setAdapter(adapter);
        finishScroll = (BounceScrollView) viewRoot.findViewById(R.id.finish_scroll);
        finishScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                if (topOrBottom == 0) {

                } else if (topOrBottom == 1) {
                    if (isAdded() && reloadDataInterface != null) {
                        reloadDataInterface.reloadData();
                    }
                }
            }
        });
    }

    public void setData(List<MyRecTaskRecordResponse.DataBeanX.DataBean> data) {
        if (finishedTaskRecycler != null) {
            adapter.notifyDataSetChanged(data);
        }
    }

    public void notifyAddData(MyRecTaskRecordResponse.DataBeanX.DataBean dataBean) {
        if (finishedTaskRecycler != null) {
            adapter.notifyAddData(dataBean);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
