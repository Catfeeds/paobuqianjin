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

public class MiddleContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = MiddleContentAdapter.class.getSimpleName();
    private final static int defaultCount = 7;
    private Context context;

    public MiddleContentAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MiddleContentViewHolder(LayoutInflater.from(context).inflate(
                R.layout.content_item, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return defaultCount;
    }

    public class MiddleContentViewHolder extends RecyclerView.ViewHolder {
        public MiddleContentViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {

        }
    }
}
