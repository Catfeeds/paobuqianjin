package com.paobuqianjin.pbq.step.view.base.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pbq on 2018/12/5.
 */

public class TaoTabAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TabViewHolder extends RecyclerView.ViewHolder {
        public TabViewHolder(View view) {
            super(view);
        }
    }
}
