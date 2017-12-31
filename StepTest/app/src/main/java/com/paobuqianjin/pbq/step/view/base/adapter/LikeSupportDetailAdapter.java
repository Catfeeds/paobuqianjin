package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;

/**
 * Created by pbq on 2017/12/31.
 */

public class LikeSupportDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = LikeSupportDetailAdapter.class.getSimpleName();
    private final static int defaultCount = 8;
    private Context context;

    public LikeSupportDetailAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikeSupportViewHolder(LayoutInflater.from(context).inflate(R.layout.live_support_list, parent,
                false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    public class LikeSupportViewHolder extends RecyclerView.ViewHolder {
        public LikeSupportViewHolder(View view) {
            super(view);
        }
    }
}
