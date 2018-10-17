package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.SlidingTabLayout;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.ConsumRedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/10/16.
 */

public class ConsumTotalActivity extends BaseBarActivity {
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
    SlidingTabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<>();
    private String[] tabTitles = {"美食", "休闲娱乐", "美容美发", "学习培训", "其他"};
    private int[] drawables = {R.drawable.food, R.drawable.like_van, R.drawable.hear_spa, R.drawable.study, R.drawable.other};

    @Override
    protected String title() {
        return "消费红包";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consum_activity_layout);
        ButterKnife.bind(this);
        initFragments();
        initView();
    }


    @Override
    protected void initView() {
        tablayout = (SlidingTabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(new TabAdapter(this, getSupportFragmentManager(),fragments,strings.toArray()));
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabTextColors(getResources().getColor(R.color.color_161727), getResources().getColor(R.color.color_6c71c4));

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tablayout.redrawIndicator(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
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

    private void initFragments() {
        for (String tabTitle : tabTitles) {
            ConsumRedFragment fragment = new ConsumRedFragment();
            fragment.setTitle(tabTitle);
            fragments.add(fragment);
            strings.add(tabTitle);
        }
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab_title);
        ImageView im = (ImageView) view.findViewById(R.id.im_tab);
        im.setImageResource(drawables[position]);
        tv.setText(tabTitles[position]);
        return view;
    }
}
