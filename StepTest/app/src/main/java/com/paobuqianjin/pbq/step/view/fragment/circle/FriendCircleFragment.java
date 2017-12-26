package com.paobuqianjin.pbq.step.view.fragment.circle;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.adapter.CirclePageAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.UnScrollViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pbq on 2017/12/1.
 */

public final class FriendCircleFragment extends BaseFragment {
    private final static String TAG = FriendCircleFragment.class.getSimpleName();
    private android.support.design.widget.TabLayout mCircleTabLayout;
    private ViewPager mCirclePager;
    private BaseActivity mActivity;
    private Context mContext;
    private int currentIndexFriend = 0;
    private int mIndexFriend = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
        mContext = context;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Presenter.getInstance(mContext).dispatchUiInterface(uiHotCircleInterface);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_circle_page;
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
    protected void initView(View rootView) {
        super.initView(rootView);
        mCircleTabLayout = (TabLayout) rootView.findViewById(R.id.circle_item_tab);
        mCirclePager = (UnScrollViewPager) rootView.findViewById(R.id.circle_item_page);
        LocalLog.d(TAG, "initView() enter");
        HotCircleFragment hotCircleFragment = new HotCircleFragment();
        AttentionCircleFragment attentionCircleFragment = new AttentionCircleFragment();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(hotCircleFragment);
        fragments.add(attentionCircleFragment);

        CirclePageAdapter pageAdapter = new CirclePageAdapter(mActivity, mActivity.getSupportFragmentManager(), fragments);
        mCirclePager.setAdapter(pageAdapter);
        mCircleTabLayout.setupWithViewPager(mCirclePager);
        for (int i = 0; i < mCircleTabLayout.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            mCircleTabLayout.getTabAt(i).setCustomView(pageAdapter.getTabView(i));
        }
        mCircleTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mCircleTabLayout, 30, 30);
            }
        });
        LocalLog.d(TAG, "initView() leave");
        Presenter.getInstance(mContext).attachUiInterface(uiHotCircleInterface);
        loadingData();
    }

    private void loadingData() {
        Presenter.getInstance(mContext).getCircleMy();
        Presenter.getInstance(mContext).getCircleChoice();

    }

    private UiHotCircleInterface uiHotCircleInterface = new UiHotCircleInterface() {
        @Override
        public void response(MyHotCircleResponse myHotCircleResponse) {
            LocalLog.d(TAG, myHotCircleResponse.toString());
        }

        @Override
        public void response(ChoiceCircleResponse choiceCircleResponse) {
            LocalLog.d(TAG, choiceCircleResponse.toString());
        }
    };
}

