package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.im.DanCircleInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.CircleIndexFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/10.
 */

public class CircleHonorIndexFragment extends BaseFragment implements DanCircleInterface{
    private final static String TAG = CircleHonorFragment.class.getSimpleName();
    @Bind(R.id.index_pager)
    ViewPager indexPager;
    List<View> mView = new ArrayList<>();
    private ViewGroup container;

    @Override
    protected int getLayoutResId() {
        return R.layout.circle_honor_index_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        this.container = container;
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        indexPager = (ViewPager) viewRoot.findViewById(R.id.index_pager);
        View circleIndex0 = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        View circleIndex1 = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        View circleIndex2 = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        View circleIndex3 = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        View circleIndex4 = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        View circleIndex5 = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        View circleIndex6 = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        View circleIndex7 = LayoutInflater.from(getContext()).inflate(R.layout.circle_honor_fg, container, false);
        mView.add(circleIndex0);
        mView.add(circleIndex1);
        mView.add(circleIndex2);
        mView.add(circleIndex3);
        mView.add(circleIndex4);
        mView.add(circleIndex5);
        mView.add(circleIndex6);
        mView.add(circleIndex7);
        LocalLog.d(TAG, "initView() enter");
        indexPager.setAdapter(new ImageViewPagerAdapter(getContext(), mView));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
