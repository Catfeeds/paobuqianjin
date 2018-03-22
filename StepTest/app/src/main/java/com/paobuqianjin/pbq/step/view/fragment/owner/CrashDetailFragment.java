package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CrashListDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CrashRecordInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.IncomeAdater;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/3/10.
 */

public class CrashDetailFragment extends BaseFragment implements CrashRecordInterface {
    private final static String TAG = CrashDetailFragment.class.getSimpleName();
    @Bind(R.id.income_recycler)
    RecyclerView incomeRecycler;
    LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.income_detail_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(getContext()).attachUiInterface(this);
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
        layoutManager = new LinearLayoutManager(getContext());
        incomeRecycler = (RecyclerView) viewRoot.findViewById(R.id.income_recycler);
        incomeRecycler.setLayoutManager(layoutManager);
        Presenter.getInstance(getContext()).getCrashRecord();
        //incomeRecycler.setAdapter(new IncomeAdater(getContext(), null));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(CrashListDetailResponse crashListDetailResponse) {
        LocalLog.d(TAG, "CrashListDetailResponse() enter " + crashListDetailResponse.toString());
        if (crashListDetailResponse.getError() == 0) {
            incomeRecycler.setAdapter(new IncomeAdater(getContext(), crashListDetailResponse.getData().getData()));
        }
    }
}
