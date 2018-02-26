package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepDollarDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.StepDollarDetailInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.StepDollarDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/16.
 */

public class StepDollarFragment extends BaseBarStyleTextViewFragment implements StepDollarDetailInterface {
    private final static String TAG = StepDollarFragment.class.getSimpleName();
    StepDollarDetailFragment stepDollarDetailFragment;
    StepDollarDetailFragment stepDollarDetailFragment1;
    String[] titles = {"步币明细", "兑换记录"};
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
    @Bind(R.id.crash_step_dol)
    Button crashStepDol;
    @Bind(R.id.dollar_total)
    RelativeLayout dollarTotal;
    @Bind(R.id.step_dollar_tab)
    TabLayout stepDollarTab;
    /*    @Bind(R.id.step_dollar_viewpager)
        ViewPager stepDollarViewpager;*/
    @Bind(R.id.step_dollar_span)
    RelativeLayout stepDollarSpan;
    private int mIndex = 0;
    Fragment[] fragments;

    @Override
    protected String title() {
        return "我的步币";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.step_dollar_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Presenter.getInstance(getContext()).getUserCredit();
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        stepDollarDetailFragment = new StepDollarDetailFragment();
        stepDollarDetailFragment1 = new StepDollarDetailFragment();
        fragments = new Fragment[2];

        fragments[0] = stepDollarDetailFragment;
        fragments[1] = stepDollarDetailFragment1;
        TabAdapter tabAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, titles);

        stepDollarTab = (TabLayout) viewRoot.findViewById(R.id.step_dollar_tab);
/*
        stepDollarViewpager = (ViewPager) viewRoot.findViewById(R.id.step_dollar_viewpager);
        stepDollarViewpager.setAdapter(tabAdapter);
*/

 /*       stepDollarTab.setupWithViewPager(stepDollarViewpager);*/

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.step_dollar_span, stepDollarDetailFragment)
                .add(R.id.step_dollar_span, stepDollarDetailFragment1)
                .show(stepDollarDetailFragment)
                .hide(stepDollarDetailFragment1)
                .commit();
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
                        /*if (iCamemaView.getVisibility() == View.VISIBLE) {
                            iCamemaView.setVisibility(View.GONE);
                        }
                        if (iScanView.getVisibility() == View.GONE) {
                            iScanView.setVisibility(View.VISIBLE);
                        }*/
                        if (mIndex == 0) {

                        } else {
                            onTabIndex(0);
                        }
                        mIndex = 0;
                        break;
                    case 1:
                        /*if (iCamemaView.getVisibility() == View.GONE) {
                            iCamemaView.setVisibility(View.VISIBLE);
                        }
                        if (iScanView.getVisibility() == View.VISIBLE) {
                            iScanView.setVisibility(View.GONE);
                        }*/
                        if (mIndex == 1) {

                        } else {
                            onTabIndex(1);
                        }

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

    private void onTabIndex(int fragmentIndex) {
        LocalLog.d(TAG, "onTabIndex() enter mIndex " + fragmentIndex);
        if (mIndex != fragmentIndex) {
            FragmentTransaction trx = getActivity().getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[mIndex]);
            if (!fragments[fragmentIndex].isAdded()) {
                trx.add(R.id.step_dollar_span, fragments[fragmentIndex]);
            }
            trx.show(fragments[fragmentIndex]).commit();
        }
        mIndex = fragmentIndex;
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
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.ico_text_tab, null);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        if (position == 0) {
            iconView.setImageDrawable(getDrawableResource(R.drawable.step_dollar));
            textView.setText(titles[0]);
            view.setGravity(Gravity.LEFT);
        } else if (position == 1) {
            iconView.setImageDrawable(getDrawableResource(R.drawable.step_dollar_reg));
            textView.setText(titles[1]);
            view.setGravity(Gravity.RIGHT);
        }
        return view;
    }

    @Override
    public void response(StepDollarDetailResponse stepDollarDetailResponse) {
        LocalLog.d(TAG, "StepDollarDetailResponse() enter " + stepDollarDetailResponse.toString());
        stepDollarDetailFragment.setStepDollarDetailAdapter(new StepDollarDetailAdapter(getContext(), stepDollarDetailResponse.getData().getData()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }
}
