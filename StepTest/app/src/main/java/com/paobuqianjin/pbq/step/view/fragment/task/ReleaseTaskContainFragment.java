package com.paobuqianjin.pbq.step.view.fragment.task;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/4/19.
 */

public class ReleaseTaskContainFragment extends BaseFragment {
    private final static String TAG = ReleaseTaskContainFragment.class.getSimpleName();
    @Bind(R.id.release_choice_tab)
    TabLayout releaseChoiceTab;
    @Bind(R.id.bar)
    RelativeLayout bar;
    @Bind(R.id.release_choice_pager)
    ViewPager releaseChoicePager;
    @Bind(R.id.buttone_left_bar)
    RelativeLayout buttoneLeftBar;
    private ReleaseTaskSponsorFragment releaseTaskSponsorFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.task_release_container_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        ReleaseTaskPersonFragment releaseTaskPersonFragment = new ReleaseTaskPersonFragment();
        releaseTaskSponsorFragment = new ReleaseTaskSponsorFragment();
        String[] titles = {"个人", "商家"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(releaseTaskPersonFragment);
        fragments.add(releaseTaskSponsorFragment);
        releaseChoiceTab = (TabLayout) viewRoot.findViewById(R.id.release_choice_tab);
        releaseChoicePager = (ViewPager) viewRoot.findViewById(R.id.release_choice_pager);

        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);

        releaseChoicePager.setAdapter(tabAdapter);
        releaseChoiceTab.setupWithViewPager(releaseChoicePager);
        for (int i = 0; i < releaseChoiceTab.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);

        }
        releaseChoiceTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(releaseChoiceTab, 60, 60);
            }
        });
    }

    public void setIndicator(TabLayout tab, int leftDip, int rightDip) {
        Class<?> tabLayout = tab.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout IITab = null;
        try {
            IITab = (LinearLayout) tabStrip.get(tab);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < IITab.getChildCount(); i++) {
            View child = IITab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.buttone_left_bar)
    public void onClick() {
        getActivity().finish();
    }
}
