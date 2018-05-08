package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.SelectPoisitonListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * on 2018/5/4.
 */

public class SearchPositionAdapter<T> extends BaseAdapter {

    private LayoutInflater inflater;
    private List<T> data = new ArrayList<>();
    private int position = -1;

    public SearchPositionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public SearchPositionAdapter(Context context, List<T> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<T> data, int position) {
        this.data.clear();
        this.data.addAll(data);
        this.position = position;
        notifyDataSetChanged();
    }

    public void setSelect(int position) {
        this.position = position;
        notifyDataSetChanged();
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
        if (data.get(i) instanceof PoiInfo) {
            PoiInfo bean = (PoiInfo) data.get(i);
            holder.tvPosition.setText(bean.name);
            if (TextUtils.isEmpty(bean.address)) {
                holder.tvInfo.setVisibility(View.GONE);
            } else {
                holder.tvInfo.setVisibility(View.VISIBLE);
                holder.tvInfo.setText(bean.address);
            }
        } else if (data.get(i) instanceof SelectPoisitonListBean) {
            SelectPoisitonListBean bean = (SelectPoisitonListBean) data.get(i);
            holder.tvPosition.setText(bean.getName());
            if (TextUtils.isEmpty(bean.getAddress())) {
                holder.tvInfo.setVisibility(View.GONE);
            } else {
                holder.tvInfo.setVisibility(View.VISIBLE);
                holder.tvInfo.setText(bean.getAddress());
            }
        }

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
