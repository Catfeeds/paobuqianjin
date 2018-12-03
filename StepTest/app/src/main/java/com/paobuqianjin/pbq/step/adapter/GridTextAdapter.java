package com.paobuqianjin.pbq.step.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CouponCateListResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbq on 2018/12/3.
 */

public class GridTextAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private Context mContext;
    private int mMaxSize = 12;
    private List<CouponCateListResponse.DataBean> mData = new ArrayList<>();


    public GridTextAdapter(Context context, int maxSize) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        mMaxSize = maxSize;
    }

    public void setData(CouponCateListResponse.DataBean bean) {
        mData.add(bean);
        notifyDataSetChanged();
    }

    public void setDatas(List<CouponCateListResponse.DataBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public List<CouponCateListResponse.DataBean> getData() {
        return mData;
    }

    public int getCount() {
        if (mData.size() >= mMaxSize) {
            return mMaxSize;
        }
        return (mData.size() + 1);
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int arg0) {
        return arg0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        convertView = inflater.inflate(R.layout.care_id_item, parent, false);
        holder = new ViewHolder(convertView);
        holder.tv.setText(mData.get(position).getCate_name());
        return convertView;
    }


    public class ViewHolder {
        public TextView tv;

        public ViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.care_name);
        }
    }

}
