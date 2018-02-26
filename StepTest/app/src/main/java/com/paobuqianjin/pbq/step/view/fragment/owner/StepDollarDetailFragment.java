package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeSupportDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.StepDollarDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.task.SelectTaskFriendAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/16.
 */

public class StepDollarDetailFragment extends BaseFragment {
    private final static String TAG = StepDollarDetailFragment.class.getSimpleName();
    @Bind(R.id.step_dollar_income_recycler)
    RecyclerView stepDollarIncomeRecycler;
    LinearLayoutManager layoutManager;
    @Override
    protected int getLayoutResId() {
        return R.layout.step_dollor_income_details;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        LocalLog.d(TAG, "initView() enter");
        stepDollarIncomeRecycler = (RecyclerView) viewRoot.findViewById(R.id.step_dollar_income_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    }

    public void setStepDollarDetailAdapter(StepDollarDetailAdapter stepDollarDetailAdapter) {
        LocalLog.d(TAG, "setStepDollarDetailAdapter() enter");
        stepDollarIncomeRecycler.setAdapter(stepDollarDetailAdapter);
        stepDollarIncomeRecycler.setLayoutManager(layoutManager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
