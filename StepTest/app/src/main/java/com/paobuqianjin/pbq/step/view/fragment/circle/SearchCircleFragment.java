package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.BaseBarFragment;
import com.paobuqianjin.pbq.step.view.base.adapter.SearchCircleAdapter;

/**
 * Created by pbq on 2017/12/15.
 */

public class SearchCircleFragment extends BaseBarFragment {
    private final static String TAG = SearchCircleFragment.class.getSimpleName();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.search_hot_circle_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocalLog.d(TAG, "");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        recyclerView =  (RecyclerView)viewRoot.findViewById(R.id.search_choose_circle_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SearchCircleAdapter(this.getContext()));
    }

    @Override
    protected String title() {
        return "精选圈子";
    }
}
