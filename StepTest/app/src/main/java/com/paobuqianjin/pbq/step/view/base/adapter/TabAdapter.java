package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by pbq on 2018/2/26.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private Context context;
    private Object[] tableTitle;

    public TabAdapter(Context context, FragmentManager fm, List<Fragment> fragments, Object[] title) {
        super(fm);
        this.context = context;
        tableTitle = title;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        try {
            return (String) tableTitle[position];
        } catch (Exception e) {
            return null;
        }
    }
}
