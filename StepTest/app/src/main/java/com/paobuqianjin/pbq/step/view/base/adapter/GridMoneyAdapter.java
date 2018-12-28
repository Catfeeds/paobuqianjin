package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DrawMoneyListResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.List;

/**
 * Created by pbq on 2018/11/1.
 */

public class GridMoneyAdapter extends BaseAdapter {
    private final static String TAG = GridMoneyAdapter.class.getSimpleName();
    List<?> data;
    Context context;

    public GridMoneyAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_money_item, parent, false);
        if (data.get(position) instanceof DrawMoneyListResponse.DataBean) {
            TextView textView = view.findViewById(R.id.tv_select);
            textView.setText(((DrawMoneyListResponse.DataBean) data.get(position)).getMoney());
            if (((DrawMoneyListResponse.DataBean) data.get(position)).getIs_disable() == 1) {
                LocalLog.d(TAG, "该选项被禁用");
                textView.setBackground(ContextCompat.getDrawable(context,R.drawable.rect_out_gray_shape));
                textView.setTextColor(ContextCompat.getColor(context, R.color.color_8a8a8a));
            } else {
                if (((DrawMoneyListResponse.DataBean) data.get(position)).isIs_select()) {
                    textView.setBackground(ContextCompat.getDrawable(context,R.drawable.crash_money_slecter));
                    textView.setTextColor(ContextCompat.getColor(context, R.color.color_161727));
                } else {
                    textView.setBackground(ContextCompat.getDrawable(context,R.drawable.rect_out_gray_line_shape));
                    textView.setTextColor(ContextCompat.getColor(context, R.color.color_161727));
                }
            }
        }
        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}
