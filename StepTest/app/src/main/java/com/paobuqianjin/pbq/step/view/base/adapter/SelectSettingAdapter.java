package com.paobuqianjin.pbq.step.view.base.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by pbq on 2018/1/5.
 */

public class SelectSettingAdapter extends RecyclerView.Adapter<SelectSettingAdapter.SelectItemViewHolder> {
    @Bind(R.id.select_content)
    TextView selectContent;
    private ArrayList<String> data;
    private Context context;
    private int selectPosition = 0;

    public SelectSettingAdapter(Context context, ArrayList<String> data) {
        this.context = context;
    }

    @Override
    @TargetApi(23)
    public void onBindViewHolder(SelectItemViewHolder holder, int position) {
        if (selectPosition == position) {
            selectContent.setTextColor(context.getColor(R.color.color_161727));
            selectContent.setTextSize(19.0f);
        } else {
            selectContent.setTextColor(context.getColor(R.color.color_8a8a8a));
            selectContent.setTextSize(18.0f);
        }
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

    public interface OnItemClickListener {
        void OnItemClick(View view, SelectItemViewHolder selectItemViewHolder, int position);
    }

    public class SelectItemViewHolder extends RecyclerView.ViewHolder {
        int viewType;
        private OnItemClickListener onItemClickListener;

        @TargetApi(23)
        public SelectItemViewHolder(View view, int ViewType) {
            super(view);
            this.viewType = viewType;
            selectContent = (TextView) view.findViewById(R.id.select_content);
        }


        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
    }
}
