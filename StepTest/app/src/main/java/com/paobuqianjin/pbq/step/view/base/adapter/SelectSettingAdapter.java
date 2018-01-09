package com.paobuqianjin.pbq.step.view.base.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by pbq on 2018/1/5.
 */

public class SelectSettingAdapter extends RecyclerView.Adapter<SelectSettingAdapter.SelectItemViewHolder> {
    private final static String TAG = SelectSettingAdapter.class.getSimpleName();
    private ArrayList<String> data;
    private Context context;
    private int selectPosition = 0;
    private int lastSelectPosition = 0;

    public SelectSettingAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    @TargetApi(23)
    public void onBindViewHolder(SelectItemViewHolder holder, int position) {
 /*       if (selectPosition == position) {
            holder.selectContent.setTextColor(context.getColor(R.color.color_161727));
            holder.selectContent.setTextSize(19.0f);
            holder.selectContent.setText(data.get(position));
        } else {
            holder.selectContent.setTextColor(context.getColor(R.color.color_8a8a8a));
            holder.selectContent.setTextSize(18.0f);
            holder.selectContent.setText(data.get(position));
        }*/
    }

    public void setSelectPosition(int position) {
        lastSelectPosition = selectPosition;
        selectPosition = position;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public String getSelectContent() {
        return data.get(selectPosition);
    }

    @TargetApi(23)
    @Override
    public void onBindViewHolder(SelectItemViewHolder holder, int position, List<Object> payloads) {
        LocalLog.d(TAG, "onBindViewHolder() payloads position " + position + " selectPosition= " + selectPosition);
        if (selectPosition == position) {
            holder.selectContent.setTextColor(context.getColor(R.color.color_161727));
            holder.selectContent.setTextSize(19.0f);
            holder.selectContent.setText(data.get(position));
        } else {
            holder.selectContent.setTextColor(context.getColor(R.color.color_8a8a8a));
            holder.selectContent.setTextSize(18.0f);
            holder.selectContent.setText(data.get(position));
        }
        onBindViewHolder(holder, position);
    }

    @Override
    public SelectItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectItemViewHolder(LayoutInflater.from(context).inflate(R.layout.create_select_list, parent, false), viewType);
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public class SelectItemViewHolder extends RecyclerView.ViewHolder {
        int viewType;
        @Bind(R.id.select_content)
        TextView selectContent;

        @TargetApi(23)
        public SelectItemViewHolder(View view, int ViewType) {
            super(view);
            this.viewType = viewType;
            selectContent = (TextView) view.findViewById(R.id.select_content);
        }
    }
}
