package com.paobuqianjin.pbq.step.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.CirclePageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbq on 2017/12/1.
 */

public final class FriendCircleFragment extends BaseFragment {
    private final static String TAG = FriendCircleFragment.class.getSimpleName();
    private android.support.design.widget.TabLayout mCircleTabLayout;
    private ViewPager mCirclePager;
    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        LocalLog.d(TAG, "getLayoutResId() layout R " + R.layout.friend_circle_page);
        return R.layout.friend_circle_page;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mCircleTabLayout = (TabLayout) rootView.findViewById(R.id.circle_item_tab);
        mCirclePager = (ViewPager) rootView.findViewById(R.id.circle_item_page);
        LocalLog.d(TAG,"initView() enter");
        HotCircleFragment hotCircleFragment = new HotCircleFragment();
        AttentionCircleFragment attentionCircleFragment = new AttentionCircleFragment();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(hotCircleFragment);
        fragments.add(attentionCircleFragment);

        CirclePageAdapter pageAdapter = new CirclePageAdapter(mActivity, mActivity.getSupportFragmentManager(), fragments);
        mCirclePager.setAdapter(pageAdapter);
        for (int i = 0; i < mCircleTabLayout.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            mCircleTabLayout.getTabAt(i).setCustomView(pageAdapter.getTabView(i));
        }

        LocalLog.d(TAG,"initView() leave");
    }
}

