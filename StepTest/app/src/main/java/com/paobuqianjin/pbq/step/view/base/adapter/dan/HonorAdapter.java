package com.paobuqianjin.pbq.step.view.base.adapter.dan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by pbq on 2018/3/16.
 */

public class HonorAdapter extends RecyclerView.Adapter<HonorAdapter.HonorAdapterViewHolder> {
    private final static String TAG = HonorAdapter.class.getSimpleName();
    Context context;
    List<?> mData;

    public HonorAdapter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public void onBindViewHolder(HonorAdapterViewHolder holder, int position) {
        updateListItem(holder, position);
    }

    private void updateListItem(HonorAdapterViewHolder holder, int position) {

    }

    @Override
    public HonorAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HonorAdapterViewHolder extends RecyclerView.ViewHolder {
        public HonorAdapterViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {

        }
    }
}
