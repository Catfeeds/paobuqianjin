package com.paobuqianjin.pbq.step.view.fragment.owner;

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
import com.paobuqianjin.pbq.step.view.base.adapter.CirclePageAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.CustomViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/3/10.
 */

public class InOutComeFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = InOutComeFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.in_out_come_tab)
    TabLayout inOutComeTab;
    @Bind(R.id.income_pager)
    ViewPager incomePager;
    CrashDetailFragment incomeDetailFragment;
    RechargeDetailFragment rechargeDetailFragment;
    String[] titles = {"交易明细", "提现明细"};

    @Override
    protected int getLayoutResId() {
        return R.layout.in_out_come_fg;
    }

    @Override
    protected String title() {
        return "明细";
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
        incomeDetailFragment = new CrashDetailFragment();
        rechargeDetailFragment = new RechargeDetailFragment();

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(rechargeDetailFragment);
        fragments.add(incomeDetailFragment);
        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);

        inOutComeTab = (TabLayout) viewRoot.findViewById(R.id.in_out_come_tab);
        incomePager = (ViewPager) viewRoot.findViewById(R.id.income_pager);

        incomePager.setAdapter(tabAdapter);

        inOutComeTab.setupWithViewPager(incomePager);

        for (int i = 0; i < inOutComeTab.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            inOutComeTab.getTabAt(i).setCustomView(getTabView(i));
        }

        inOutComeTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(inOutComeTab, 50, 50);
            }
        });


        inOutComeTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LocalLog.d(TAG, "onTabSelected() enter" + tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        LocalLog.d(TAG, "tab 0");

                        break;
                    case 1:
                        LocalLog.d(TAG, "tab 1");
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
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        if (position == 0) {
            textView.setText(titles[0]);
            view.setGravity(Gravity.CENTER);
        } else if (position == 1) {
            textView.setText(titles[1]);
            view.setGravity(Gravity.CENTER);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
