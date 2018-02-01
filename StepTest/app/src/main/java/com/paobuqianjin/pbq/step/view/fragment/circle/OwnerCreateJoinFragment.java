package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.design.widget.TabLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyJoinCircleResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.MyCreatCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.MyJoinCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.CirclePageAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCreateJoinFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = OwnerCreateJoinFragment.class.getSimpleName();
    ViewPager ownerCreateJoinPager;

    private android.support.design.widget.TabLayout createOrJoin;
    private OwnerCreateFragment ownerCreateFragment = new OwnerCreateFragment();
    private OwnerJoinFragment ownerJoinFragment = new OwnerJoinFragment();

    @Override
    protected int getLayoutResId() {
        return R.layout.owner_circle_fg;
    }

    @Override
    protected String title() {
        return "我的圈子";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        String[] title = {"我创建的", "我加入的"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ownerCreateFragment);
        fragments.add(ownerJoinFragment);
        createOrJoin = (TabLayout) viewRoot.findViewById(R.id.create_or_join);
        ownerCreateJoinPager = (ViewPager) viewRoot.findViewById(R.id.owner_create_join_pager);
        TabAdapter pageAdapter = new TabAdapter(getContext()
                , getActivity().getSupportFragmentManager(), fragments, title);
        ownerCreateJoinPager.setAdapter(pageAdapter);
        createOrJoin.setupWithViewPager(ownerCreateJoinPager);

        for (int i = 0; i < createOrJoin.getTabCount(); i++) {
            LocalLog.d(TAG, "initView() i = " + i);
            //createOrJoin.getTabAt(i).setCustomView(pageAdapter.getTabView(i));
        }
        createOrJoin.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(createOrJoin, 50, 50);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override

    public void onDestroyView() {
        super.onDestroyView();
    }

}
