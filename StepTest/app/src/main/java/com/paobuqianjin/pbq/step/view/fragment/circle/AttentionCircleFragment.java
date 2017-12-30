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
import com.paobuqianjin.pbq.step.view.base.adapter.AttentionCircleAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/11.
 */

public class AttentionCircleFragment extends BaseFragment {
    private final static String TAG = AttentionCircleFragment.class.getSimpleName();
    RecyclerView dynamicRecyclerView;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.attention_circle_fragment;
    }

    public String getTabLabel() {
        return "关注";
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        dynamicRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.dynamic_recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dynamicRecyclerView.setLayoutManager(layoutManager);
        dynamicRecyclerView.setAdapter(new AttentionCircleAdapter(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
