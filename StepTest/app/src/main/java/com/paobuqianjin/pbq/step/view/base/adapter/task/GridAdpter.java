package com.paobuqianjin.pbq.step.view.base.adapter.task;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;;import com.paobuqianjin.pbq.step.R;

import java.util.List;

/**
 * Created by pbq on 2018/4/23.
 */

public class GridAdpter extends BaseAdapter {
    private Context context;
    private List<?> mData;

    public GridAdpter(Context context, List<?> data) {
        this.context = context;
        mData = data;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 9;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(98 * 2, 98 * 2));
            imageView.setPadding(0, 15, 15, 0);
            imageView.setImageResource(R.drawable.center_pic);
        } else {
            imageView = (ImageView) convertView;
        }
        return imageView;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }
}
