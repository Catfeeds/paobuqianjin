package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2017/12/31.
 */

public class MiddleContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = MiddleContentAdapter.class.getSimpleName();
    private final static int defaultCount = 7;

    private Context context;
    List<?> mData;

    public MiddleContentAdapter(Context context, List<?> data) {
        super();
        this.context = context;
        mData = data;
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
        @Bind(R.id.item_content)
        TextView itemContent;

        public MiddleContentViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            itemContent = (TextView) view.findViewById(R.id.item_content);
        }
    }
}
