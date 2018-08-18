package com.paobuqianjin.pbq.step.view.base.adapter.owner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.CircleTypeActivity;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleType2Response;

import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */

public class CircleTagAdapter extends RecyclerView.Adapter<CircleTagAdapter.ViewHolder> {

    private Context context;
    private List<CircleType2Response.DataBean> list;

    public CircleTagAdapter(Context context, List<CircleType2Response.DataBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_circle_type, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTag.setText(list.get(position).getName());
        holder.tvTag.setBackgroundResource(list.get(position).isSelect() ? R.drawable.tag_back_ground_select : R.drawable.shape_14_dp_gray);
        holder.tvTag.setTextColor(context.getResources().getColor(list.get(position).isSelect() ? R.color.white : R.color.color_5a5a5a));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTag;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_tag);
        }
    }
}
