package com.paobuqianjin.pbq.step.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.BaseFragment;

/**
 * Created by pbq on 2017/12/1.
 */

public final class FriendCircleFragment extends BaseFragment {
    private TableLayout mCircleTabLayout;
    private ViewPager mCirclePager;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.friend_circle_page;
    }

    @Override
    protected void initView() {
        super.initView();
        mCircleTabLayout = rootView.findViewById(R.id.circle_item_tab);
        mCirclePager = rootView.findViewById(R.id.circle_item_page);
        HotCircleFragment hotCircleFragment = new HotCircleFragment();
        AttentionCircleFragment attentionCircleFragment = new AttentionCircleFragment();
    }
}

