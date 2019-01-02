package com.paobuqianjin.pbq.step.view.activity.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.fragment.exchange.ExOutFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/12/29.
 */

public class ExchangeInActivity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private final String[] titles = {"全部", "待付款", "待发货", "待收货", "待评价", "退款"};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected String title() {
        return "买到管理";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_out_manager_activity_layout);
        ButterKnife.bind(this);
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < titles.length; i++) {
            ExOutFragment fragment = new ExOutFragment();
            if (!fragment.isAdded())
                fragment.setAction(titles[i]);
            fragments.add(fragment);
        }
        viewpager.setAdapter(new TabAdapter(ExchangeInActivity.this, getSupportFragmentManager(), fragments, titles));
        tablayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            /*tablayout.redrawIndicator(position, positionOffset);*/

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                try {
                    TextView tv = (TextView) customView.findViewById(R.id.tv_tab_title);
                    if (tv != null) {
                        tv.setTextColor(ContextCompat.getColor(ExchangeInActivity.this, R.color.color_232433));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                try {
                    TextView tv = (TextView) customView.findViewById(R.id.tv_tab_title);
                    if (tv != null) {
                        tv.setTextColor(ContextCompat.getColor(ExchangeInActivity.this, R.color.color_161727));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                }
            }
        }
    }

    public View getTabView(int position) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_oly_text, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab_title);
        if (titles.length > 0 && position < titles.length) {
            tv.setText(titles[position]);
        }
        return view;
    }
}
