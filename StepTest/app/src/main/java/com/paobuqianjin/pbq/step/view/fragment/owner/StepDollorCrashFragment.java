package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/5/15.
 */

public class StepDollorCrashFragment extends BaseFragment {
    @Bind(R.id.step_dollar_income_recycler)
    SwipeMenuRecyclerView stepDollarIncomeRecycler;
    @Bind(R.id.crash_record)
    TextView crashRecord;

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        crashRecord = (TextView) viewRoot.findViewById(R.id.crash_record);
        stepDollarIncomeRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.step_dollar_income_recycler);
        crashRecord.setVisibility(View.VISIBLE);
        stepDollarIncomeRecycler.setVisibility(View.GONE);

    }
}
