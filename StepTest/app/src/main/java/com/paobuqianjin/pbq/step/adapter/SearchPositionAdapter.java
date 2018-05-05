package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.paobuqianjin.pbq.step.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * on 2018/5/4.
 */

public class SearchPositionAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<PoiInfo> data = new ArrayList<>();
    private int position = -1;

    public SearchPositionAdapter(Context context, List<PoiInfo> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public void setSelect(int position) {
        this.position = position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_search_position, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvPosition.setText(data.get(i).name);
        holder.tvInfo.setText(data.get(i).address);
        if (position == i) {
            holder.ivSelect.setVisibility(View.VISIBLE);
        } else {
            holder.ivSelect.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    private class ViewHolder {

        private ImageView ivSelect;
        private TextView tvPosition;
        private TextView tvInfo;

        public ViewHolder(View view) {
            tvPosition = ((TextView) view.findViewById(R.id.tv_position));
            ivSelect = ((ImageView) view.findViewById(R.id.iv_select));
            tvInfo = (TextView) view.findViewById(R.id.tv_info);
        }
    }
}
