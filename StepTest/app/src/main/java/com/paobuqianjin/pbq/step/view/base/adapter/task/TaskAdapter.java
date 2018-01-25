package com.paobuqianjin.pbq.step.view.base.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by pbq on 2018/1/25.
 */

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

        }
    }
}
