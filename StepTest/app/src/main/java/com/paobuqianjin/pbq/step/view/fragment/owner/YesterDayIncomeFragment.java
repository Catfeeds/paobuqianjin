package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.WalletRedPkgIncomeAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/2/6.
 */

public class YesterDayIncomeFragment extends BaseFragment {
    private final static String TAG = YesterDayIncomeFragment.class.getSimpleName();
    @Bind(R.id.yesterday_income_recycler)
    RecyclerView yesterdayIncomeRecycler;
    private LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.income_yesterday_fg;
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
        LocalLog.d(TAG, "initView() enter");
        super.initView(viewRoot);
        layoutManager = new LinearLayoutManager(getContext());
        yesterdayIncomeRecycler = (RecyclerView) viewRoot.findViewById(R.id.yesterday_income_recycler);
        yesterdayIncomeRecycler.setLayoutManager(layoutManager);

    }

    public void setData(IncomeResponse incomeResponse) {
        if (yesterdayIncomeRecycler != null) {
            yesterdayIncomeRecycler.setAdapter(new WalletRedPkgIncomeAdapter(getContext(),incomeResponse.getData().getData()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
