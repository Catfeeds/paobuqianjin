package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

import butterknife.Bind;

/**
 * Created by pbq
 * on 2018/4/23.
 */

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {
    private boolean[] data = new boolean[7];
    private Context context;

    public TimeAdapter(Context context, boolean[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, int position) {
        String timeString = "";
        switch (position) {
            case 0:
                timeString = "周一";
                break;
            case 1:
                timeString = "周二";
                break;
            case 2:
                timeString = "周三";
                break;
            case 3:
                timeString = "周四";
                break;
            case 4:
                timeString = "周五";
                break;
            case 5:
                timeString = "周六";
                break;
            case 6:
                timeString = "周日";
                break;
            default:
                break;
        }

        holder.timeDays.setText(timeString);
        if (data[position]) {
            holder.selectIco.setImageResource(R.mipmap.selected1);
        } else {
            holder.selectIco.setImageResource(R.mipmap.unchecked);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data[holder.getAdapterPosition()]) {
                    holder.selectIco.setImageResource(R.mipmap.unchecked);
                    data[holder.getAdapterPosition()] = false;
                } else {
                    holder.selectIco.setImageResource(R.mipmap.selected1);
                    data[holder.getAdapterPosition()] = true;
                }
            }
        });
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimeViewHolder(LayoutInflater.from(context).inflate(R.layout.work_time_item, parent, false));
    }

    class TimeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.time_days)
        TextView timeDays;
        @Bind(R.id.select_ico)
        ImageView selectIco;

        TimeViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            timeDays = (TextView) view.findViewById(R.id.time_days);
            selectIco = (ImageView) view.findViewById(R.id.select_ico);
        }
    }
}
