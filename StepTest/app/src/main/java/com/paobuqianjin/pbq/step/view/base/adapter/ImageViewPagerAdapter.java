package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by pbq on 2017/12/31.
 */

public class ImageViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<View> viewData;

    public ImageViewPagerAdapter(Context context, List<View> viewData) {
        this.context = context;
        this.viewData = viewData;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewData.get(position), 0);
        return viewData.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewData.get(position));
    }

    @Override
    public int getCount() {
        if (viewData != null) {
            return viewData.size();
        } else {
            return 0;
        }
    }
}
