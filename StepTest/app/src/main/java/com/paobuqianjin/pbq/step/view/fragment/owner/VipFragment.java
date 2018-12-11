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
import com.paobuqianjin.pbq.step.utils.NetApi;
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
    @Bind(R.id.vip_tab_bar)
    TabLayout vipTabBar;
    @Bind(R.id.vip_viewpager)
    ViewPager vipViewpager;
    String[] titles = {"个人会员", "金牌会员", "联盟商家", "优选商家"};
    private int selectPage = 0;

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

        PersonVipFragment personVipFragment = new PersonVipFragment();
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(personVipFragment);
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                H5VipFragment h5VipFragmentA = new H5VipFragment();
                h5VipFragmentA.setUrl(NetApi.urlGoldenH5);
                fragments.add(h5VipFragmentA);
            } else if (i == 1) {
                H5VipFragment h5VipFragmentB = new H5VipFragment();
                h5VipFragmentB.setUrl(NetApi.urlUnionH5);
                fragments.add(h5VipFragmentB);
            } else if (i == 2) {
                H5VipFragment h5VipFragmentC = new H5VipFragment();
                h5VipFragmentC.setUrl(NetApi.urlGoodSelectH5);
                fragments.add(h5VipFragmentC);
            }
        }

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
        vipTabBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LocalLog.d(TAG, "onTabSelected() enter" + tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        selectPage = 0;
                        LocalLog.d(TAG, "onTabSelected() selectPage = " + selectPage);
                        break;
                    case 1:
                        selectPage = 1;
                        LocalLog.d(TAG, "onTabSelected() selectPage = " + selectPage);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
            view.setGravity(Gravity.CENTER);
        } else if (position == 1) {
            textView.setText(titles[1]);
            view.setGravity(Gravity.CENTER);
        } else if (position == 2) {
            textView.setText(titles[2]);
            view.setGravity(Gravity.CENTER);
        } else if (position == 3) {
            textView.setText(titles[3]);
            view.setGravity(Gravity.CENTER);
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
