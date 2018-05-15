package com.paobuqianjin.pbq.step.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lwkandroid.imagepicker.data.ImageBean;
import com.paobuqianjin.pbq.step.activity.base.ImageFragment;

import java.util.List;

public class ImageViewAdapter extends FragmentStatePagerAdapter {

    private List<ImageBean> mDatas;

    public ImageViewAdapter(FragmentManager fm, List<ImageBean> data) {
        super(fm);
        mDatas = data;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(position, mDatas);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
