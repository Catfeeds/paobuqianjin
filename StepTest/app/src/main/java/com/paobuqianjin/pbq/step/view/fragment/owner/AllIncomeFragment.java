package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.WalletRedPkgIncomeAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/2/6.
 */

public class AllIncomeFragment extends BaseFragment {
    private final static String TAG = AllIncomeFragment.class.getSimpleName();
    @Bind(R.id.all_income_recycler)
    RecyclerView allIncomeRecycler;
    private LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.income_all_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setData(AllIncomeResponse allIncomeResponse) {
        if (allIncomeRecycler != null) {
            allIncomeRecycler.setAdapter(new WalletRedPkgIncomeAdapter(getContext(),allIncomeResponse.getData().getData()));
        }
    }
    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        LocalLog.d(TAG,"initView() enter");
        layoutManager = new LinearLayoutManager(getContext());
        allIncomeRecycler = (RecyclerView) viewRoot.findViewById(R.id.all_income_recycler);
        allIncomeRecycler.setLayoutManager(layoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
