package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/25.
 */

public class VipFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = VipFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.vip_banner)
    ImageView vipBanner;
    @Bind(R.id.vip_tab_bar)
    TabLayout vipTabBar;
    @Bind(R.id.vip_viewpager)
    ViewPager vipViewpager;
    String[] titles = {"普通会员", "商家会员"};

    @Override
    protected int getLayoutResId() {
        return R.layout.vip_fg;
    }

    @Override
    protected String title() {
        return "会员专享";
    }

    @Override
    protected void initView(final View viewRoot) {
        super.initView(viewRoot);
        SponsorVipFragment sponsorVipFragment = new SponsorVipFragment();
        PersonVipFragment personVipFragment = new PersonVipFragment();
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(personVipFragment);
        fragments.add(sponsorVipFragment);

        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);
        vipTabBar = (TabLayout) viewRoot.findViewById(R.id.vip_tab_bar);
        vipViewpager = (ViewPager) viewRoot.findViewById(R.id.vip_viewpager);
        vipViewpager.setAdapter(tabAdapter);
        vipTabBar.setupWithViewPager(vipViewpager);
        for (int i = 0; i < vipTabBar.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            vipTabBar.getTabAt(i).setCustomView(getTabView(i));
        }

        vipTabBar.post(new Runnable() {
            @Override
            public void run() {
                if (vipTabBar != null) {
                    setIndicator(vipTabBar, 20, 20);
                }
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

    private View getTabView(int position) {
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.text_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        if (position == 0) {
            textView.setText(titles[0]);
            view.setGravity(Gravity.LEFT);
        } else if (position == 1) {
            textView.setText(titles[1]);
            view.setGravity(Gravity.RIGHT);
        }
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
