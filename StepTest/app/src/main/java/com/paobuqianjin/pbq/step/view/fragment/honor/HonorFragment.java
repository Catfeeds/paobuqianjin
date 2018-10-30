package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.UnScrollViewPager;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/1.
 */

public final class HonorFragment extends BaseFragment {
    private final static String TAG = HonorFragment.class.getSimpleName();
    @Bind(R.id.person_circle_nearby)
    TabLayout personCircleNearby;
    @Bind(R.id.table_layout_honor)
    RelativeLayout tableLayoutHonor;
    @Bind(R.id.honor_viewpage)
    UnScrollViewPager honorViewpage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.honor_page;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        PersonHonorFragment personHonorFragment = new PersonHonorFragment();
        CircleHonorIndexFragment circleHonorIndexFragment = new CircleHonorIndexFragment();
        NearByFragment nearByFragment = new NearByFragment();

        String[] titles = {"个人", "社群", "附近"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(personHonorFragment);
        fragments.add(circleHonorIndexFragment);
        fragments.add(nearByFragment);

        personCircleNearby = (TabLayout) viewRoot.findViewById(R.id.person_circle_nearby);
        honorViewpage = (UnScrollViewPager) viewRoot.findViewById(R.id.honor_viewpage);
        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);
        honorViewpage.setAdapter(tabAdapter);
        personCircleNearby.setupWithViewPager(honorViewpage);

        for (int i = 0; i < personCircleNearby.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);

        }
        personCircleNearby.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(personCircleNearby, 40, 40);
            }
        });

    }

    public void setIndicator(TabLayout tab, int leftDip, int rightDip) {
        if(tab == null){
            return;
        }
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
        if (personCircleNearby != null) {
            personCircleNearby.setupWithViewPager(null);
        }
    }
}
