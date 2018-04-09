package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecPayResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RechargeDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.RechargeDetailInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.IncomeAdater;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/3/10.
 */

public class RechargeDetailFragment extends BaseFragment implements RechargeDetailInterface {
    private final static String TAG = RechargeDetailFragment.class.getSimpleName();
    @Bind(R.id.out_detail_recycler)
    RecyclerView outDetailRecycler;
    LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.outcome_detail_fg;
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
        outDetailRecycler = (RecyclerView) viewRoot.findViewById(R.id.out_detail_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        outDetailRecycler.setLayoutManager(layoutManager);
        //outDetailRecycler.setAdapter(new IncomeAdater(getContext(), null));
        Presenter.getInstance(getContext()).getRechargeRecord();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(RechargeDetailResponse rechargeDetailResponse) {
        LocalLog.d(TAG, "RechargeDetailResponse() enter  " + rechargeDetailResponse.toString());
        if (rechargeDetailResponse.getError() == 0) {
            outDetailRecycler.setAdapter(new IncomeAdater(getContext(), rechargeDetailResponse.getData().getData()));
        } else if (rechargeDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }

    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }
}
