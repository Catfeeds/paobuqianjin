package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.task.TaskAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;

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
    @Bind(R.id.unfinish_scroll)
    BounceScrollView unfinishScroll;
    private TaskAdapter adapter;
    TaskFragment.ReloadDataInterface reloadDataInterface;

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
        unfinishScroll = (BounceScrollView) viewRoot.findViewById(R.id.unfinish_scroll);
        unfinishScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                if (topOrBottom == 0) {
                    if (isAdded() && reloadDataInterface != null) {
                        reloadDataInterface.reloadData();
                    }
                } else if (topOrBottom == 1) {

                }
            }
        });
    }

    public void setReloadDataInterface(TaskFragment.ReloadDataInterface reloadDataInterface) {
        this.reloadDataInterface = reloadDataInterface;
    }

    public void setData(List<MyRecTaskRecordResponse.DataBeanX.DataBean> data) {
        LocalLog.d(TAG, "setData() enter");
        if (taskUnfinishedRecycler != null) {
            adapter.notifyDataSetChanged(data);
        }
    }

    public void notifyAddData(MyRecTaskRecordResponse.DataBeanX.DataBean dataBean) {
        if (taskUnfinishedRecycler != null) {
            adapter.notifyAddData(dataBean);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
