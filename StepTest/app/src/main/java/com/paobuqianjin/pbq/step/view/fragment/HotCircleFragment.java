package com.paobuqianjin.pbq.step.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.adapter.MyHotCircleAdapter;

/**
 * Created by pbq on 2017/12/11.
 */

public class HotCircleFragment extends BaseFragment {
    private final static String TAG = HotCircleFragment.class.getSimpleName();
    private RecyclerView myHotRecyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerView systemLiveRecyclerView;
    private RecyclerView allHotRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocalLog.d(TAG, "onCreateView() enter");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        LocalLog.d(TAG, "getLayoutResId() layout R " + R.layout.hot_circle_fragment);
        return R.layout.hot_circle_fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        LocalLog.d(TAG, "initView() enter");
        myHotRecyclerView = (RecyclerView) rootView.findViewById(R.id.hot_my_circle_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myHotRecyclerView.setLayoutManager(layoutManager);
        myHotRecyclerView.addItemDecoration(new MyHotCircleAdapter.SpaceItemDecoration(50));
        myHotRecyclerView.setAdapter(new MyHotCircleAdapter(getContext()));
    }

    /*@desc  返回Fragment标签
    *@function getTabLabel
    *@param
    *@return
    */
    public String getTabLabel() {
        return "热门";
    }
}
