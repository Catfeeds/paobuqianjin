package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.paobuqianjin.pbq.step.R;

import butterknife.Bind;

/**
 * Created by pbq on 2019/1/8.
 */

public class ExContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private boolean[] data;

    public ExContentAdapter(Context context, boolean[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (data[position]) {
            ((ExContentViewHolder) holder).stars.setImageResource(R.drawable.had_collect);
        } else {
            ((ExContentViewHolder) holder).stars.setImageResource(R.drawable.no_collect);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExContentViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_content_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ExContentViewHolder extends RecyclerView.ViewHolder {
        ImageView stars;

        public ExContentViewHolder(View view) {
            super(view);
            stars = (ImageView) view.findViewById(R.id.stars);
        }

    }
}
