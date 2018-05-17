package com.paobuqianjin.pbq.step.view.base.adapter.task;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;;import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

/**
 * Created by pbq on 2018/4/23.
 */

public class GridAdpter extends BaseAdapter {
    private Context context;
    private List<?> mData;
    private int mImageLayoutSize;

    public GridAdpter(Context context, List<?> data, int layoutSize) {
        this.context = context;
        mData = data;
        this.mImageLayoutSize = layoutSize;
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
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mImageLayoutSize));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (mData.get(position) instanceof SponsorDetailResponse.DataBean.EnvironmentImgsBean) {
                Presenter.getInstance(context).getImage(imageView, ((SponsorDetailResponse.DataBean.EnvironmentImgsBean) mData.get(position)).getUrl());
            }
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
