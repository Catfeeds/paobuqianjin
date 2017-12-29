package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by pbq on 2017/12/29.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private Context context;
    private String[] tableTitle;

    public TabAdapter(Context context, FragmentManager fm, List<Fragment> fragments, String[] title) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        tableTitle = title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tableTitle[position];
    }
}
