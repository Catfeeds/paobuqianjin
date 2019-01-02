package com.paobuqianjin.pbq.step.view.base.adapter.exchange;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TriResponse;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2019/1/2.
 */

public class ExTrAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<?> data;

    public ExTrAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExTrViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_tr_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExTrViewHolder exTrViewHolder = (ExTrViewHolder) holder;
        if (data.get(position) instanceof TriResponse.DataBean.TracesBean) {
            if (position == 0) {
                exTrViewHolder.img.setImageResource(R.drawable.er);
            } else {
                exTrViewHolder.img.setImageResource(R.drawable.yi);
            }
            exTrViewHolder.desc.setText(((TriResponse.DataBean.TracesBean) data.get(position)).getAcceptStation());
            exTrViewHolder.timeCreate.setText(((TriResponse.DataBean.TracesBean) data.get(position)).getAcceptStation());
        }
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public static class ExTrViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView desc;
        TextView timeCreate;

        public ExTrViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
            desc = (TextView) view.findViewById(R.id.desc);
            timeCreate = (TextView) view.findViewById(R.id.time_create);
        }
    }
}
