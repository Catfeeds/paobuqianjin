package com.paobuqianjin.pbq.step.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.view.CustomViewPager;
import com.paobuqianjin.pbq.step.view.fragment.owner.StepDollarDetailFragment;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/16.
 */

public class StepDollarActivity extends BaseBarActivity {
    private final static String TAG = StepDollarActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.step_dollar_bg)
    ImageView stepDollarBg;
    @Bind(R.id.step_dollars)
    TextView stepDollars;
    @Bind(R.id.step_dollar_nums)
    TextView stepDollarNums;
    @Bind(R.id.dollar_total)
    RelativeLayout dollarTotal;
    @Bind(R.id.step_dollar_tab)
    TabLayout stepDollarTab;
    @Bind(R.id.step_dollar_viewpager)
    ViewPager stepDollarViewpager;
    @Bind(R.id.step_dollar_span)
    RelativeLayout stepDollarSpan;
    private int mIndex = 0;
    private UserInfoResponse.DataBean userInfo;
    String[] titles = {"步币明细"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_dollars_activity_layout);
    }

    @Override
    protected String title() {
        return "我的步币";
    }

    @Override
    protected void initView() {
        StepDollarDetailFragment stepDollarDetailFragment = new StepDollarDetailFragment();
        stepDollarNums = (TextView) findViewById(R.id.step_dollar_nums);
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(stepDollarDetailFragment);
        TabAdapter tabAdapter = new TabAdapter(this
                , this.getSupportFragmentManager(), fragments, titles);

        stepDollarTab = (TabLayout) findViewById(R.id.step_dollar_tab);
        stepDollarViewpager = (ViewPager) findViewById(R.id.step_dollar_viewpager);
        stepDollarViewpager.setAdapter(tabAdapter);


        stepDollarTab.setupWithViewPager(stepDollarViewpager);
        Intent intent = getIntent();
        if (intent != null) {
            userInfo = (UserInfoResponse.DataBean) intent.getSerializableExtra("userinfo");
            if (userInfo != null) {
                String stepDoll = String.valueOf(userInfo.getCredit()) + "步币";
                SpannableString spannableString = new SpannableString(stepDoll);
                spannableString.setSpan(new AbsoluteSizeSpan(30),String.valueOf(userInfo.getCredit()).length(),stepDoll.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                stepDollarNums.setText(spannableString);
            }
        }
        for (int i = 0; i < stepDollarTab.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            stepDollarTab.getTabAt(i).setCustomView(getTabView(i));
        }

        stepDollarTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(stepDollarTab, 10, 10);
            }
        });


        stepDollarTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        if (tab == null) {
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

    private View getTabView(int position) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.ico_text_tab, null);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        if (position == 0) {
            iconView.setImageDrawable(getDrawableResource(R.drawable.step_dollar));
            textView.setText(titles[0]);
            textView.setTextColor(ContextCompat.getColor(StepDollarActivity.this,R.color.color_golden));
            view.setGravity(Gravity.LEFT);
        } else if (position == 1) {
            iconView.setImageDrawable(getDrawableResource(R.drawable.step_dollar_reg));
            textView.setText(titles[1]);
            view.setGravity(Gravity.RIGHT);
        }
        return view;
    }

}
