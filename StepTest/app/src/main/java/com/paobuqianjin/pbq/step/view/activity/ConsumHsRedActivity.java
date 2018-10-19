package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.fragment.record.DispatchConsumRecordFg;
import com.paobuqianjin.pbq.step.view.fragment.record.RecConsumFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbq on 2018/10/18.
 * 消费红包历史纪录
 */

public class ConsumHsRedActivity extends BaseBarActivity {
    TabLayout redHisTab;
    ViewPager redRecordPager;

    @Override
    protected String title() {
        return "历史记录";
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_history_record_layout);
    }

    @Override
    protected void initView() {
        redHisTab = (TabLayout) findViewById(R.id.red_his_tab);
        redRecordPager = (ViewPager) findViewById(R.id.red_record_pager);
        Intent intent = getIntent();
        if (intent != null) {
            String selectAction = intent.getStringExtra("select");
            String[] titles = null;
            List<Fragment> fragments = new ArrayList<>();
            if (selectAction != null) {
                titles = new String[]{"已发红包"};
                redHisTab.setVisibility(View.GONE);
                DispatchConsumRecordFg dispatchRecordFragment = new DispatchConsumRecordFg();
                fragments.add(dispatchRecordFragment);
            } else {
                titles = new String[]{"已抢红包", "已发红包"};
                RecConsumFragment revRecordFragment = new RecConsumFragment();
                DispatchConsumRecordFg dispatchRecordFragment = new DispatchConsumRecordFg();
                fragments.add(revRecordFragment);
                fragments.add(dispatchRecordFragment);
            }

            TabAdapter tabAdapter = new TabAdapter(this
                    , getSupportFragmentManager(), fragments, titles);
            redRecordPager.setAdapter(tabAdapter);
            redHisTab.setupWithViewPager(redRecordPager);
            redHisTab.post(new Runnable() {
                @Override
                public void run() {
                    if (redHisTab != null) {
                        setIndicator(redHisTab, 50, 50);
                    }
                }
            });

        }
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
}
