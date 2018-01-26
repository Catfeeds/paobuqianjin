package com.paobuqianjin.pbq.step.view.base.adapter.task;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.TaskDetailActivity;

/**
 * Created by pbq on 2018/1/25.
 */

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = TaskAdapter.class.getSimpleName();
    private final static int defaultCount = 5;
    private Context context;

    public TaskAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        public TaskViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            view.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.task_list_item:
                        LocalLog.d(TAG, "Item 被点击" + this.toString());
                        Intent intent = new Intent();
                        intent.setClass(context, TaskDetailActivity.class);
                        context.startActivity(intent);
                        break;
                }
            }
        };
    }
}
