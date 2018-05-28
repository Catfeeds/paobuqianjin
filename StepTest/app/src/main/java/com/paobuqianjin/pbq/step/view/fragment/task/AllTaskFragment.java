package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReceiveTaskResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.ReceiveTaskInterface;
import com.paobuqianjin.pbq.step.presenter.im.ReflashInterface;
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

public class AllTaskFragment extends BaseFragment implements ReceiveTaskInterface {
    private final static String TAG = AllTaskFragment.class.getSimpleName();
    @Bind(R.id.all_task_recycler)
    RecyclerView allTaskRecycler;
    @Bind(R.id.task_scroll)
    BounceScrollView taskScroll;
    private LinearLayoutManager layoutManager;
    private TaskAdapter adapter;
    ReflashInterface reflashInterface;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_all_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
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
        allTaskRecycler = (RecyclerView) viewRoot.findViewById(R.id.all_task_recycler);
        allTaskRecycler.setLayoutManager(layoutManager);
        adapter = new TaskAdapter(getContext());
        adapter.setReceiveTaskInterface(this);
        allTaskRecycler.setAdapter(adapter);
        taskScroll = (BounceScrollView) viewRoot.findViewById(R.id.task_scroll);
        taskScroll.setTopBottomListener(new BounceScrollView.TopBottomListener() {
            @Override
            public void topBottom(int topOrBottom) {
                if (topOrBottom == 0) {

                } else if (topOrBottom == 1) {

                }
            }
        });
    }

    public void setData(List<MyRecTaskRecordResponse.DataBeanX.DataBean> data) {
        if (allTaskRecycler != null) {
            adapter.notifyDataSetChanged(data);
        }
    }

    public void notifyAddData(MyRecTaskRecordResponse.DataBeanX.DataBean dataBean) {
        if (allTaskRecycler != null) {
            adapter.notifyAddData(dataBean);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(ReceiveTaskResponse receiveTaskResponse) {
        LocalLog.d(TAG, "ReceiveTaskResponse() enter " + receiveTaskResponse.toString());
        if (receiveTaskResponse.getError() == 0) {
            LocalLog.d(TAG, "领取任务成功");
            if (this.reflashInterface != null) {
                this.reflashInterface.notifyReflash(receiveTaskResponse);
            }
        } else if (receiveTaskResponse.getError() == 1) {
            Toast.makeText(getContext(), receiveTaskResponse.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (receiveTaskResponse.getError() == -1) {
            Toast.makeText(getContext(), receiveTaskResponse.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (receiveTaskResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void receiveTask(int taskId, ReflashInterface reflashInterface) {
        LocalLog.d(TAG, "领取任务 id = " + taskId);
        this.reflashInterface = reflashInterface;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }
}
