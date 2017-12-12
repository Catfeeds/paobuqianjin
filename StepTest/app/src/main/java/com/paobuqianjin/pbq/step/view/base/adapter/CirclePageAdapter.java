package com.paobuqianjin.pbq.step.view.base.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.paobuqianjin.pbq.step.view.base.CircleTabView;
import com.paobuqianjin.pbq.step.view.fragment.AttentionCircleFragment;
import com.paobuqianjin.pbq.step.view.fragment.HotCircleFragment;

import java.util.List;

/**
 * Created by pbq on 2017/12/11.
 */

public class CirclePageAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments;

    public CirclePageAdapter(Context context, FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    public View getTabView(int position) {
        CircleTabView tabView = new CircleTabView(mContext);
        Fragment fragment = getItem(position);
        if (fragment instanceof HotCircleFragment ) {
            tabView.setData(((HotCircleFragment)fragment).getTabLabel());
        }else if(fragment instanceof AttentionCircleFragment){
            tabView.setData(((AttentionCircleFragment)fragment).getTabLabel());
        }
        return tabView;
    }
}
