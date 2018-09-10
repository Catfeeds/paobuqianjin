package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepDollarDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.StepDollarDetailInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.StepDollarDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/16.
 */

public class StepDollarDetailFragment extends BaseFragment implements StepDollarDetailInterface {
    private final static String TAG = StepDollarDetailFragment.class.getSimpleName();
    @Bind(R.id.step_dollar_income_recycler)
    SwipeMenuRecyclerView stepDollarIncomeRecycler;
    LinearLayoutManager layoutManager;
    @Bind(R.id.crash_record)
    TextView crashRecord;
    private int pageIndex = 1, PAGESIZE = 100;

    @Override
    protected int getLayoutResId() {
        return R.layout.step_dollor_income_details;
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
        LocalLog.d(TAG, "initView() enter");
        stepDollarIncomeRecycler = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.step_dollar_income_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Presenter.getInstance(getContext()).getUserCredit(pageIndex, PAGESIZE);
        stepDollarIncomeRecycler.setLayoutManager(layoutManager);
        crashRecord = (TextView) viewRoot.findViewById(R.id.crash_record);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(StepDollarDetailResponse stepDollarDetailResponse) {
        LocalLog.d(TAG, "StepDollarDetailResponse() enter " + stepDollarDetailResponse.toString());
        if (!isAdded()) {
            return;
        }
        if (stepDollarDetailResponse.getError() == 0) {
            if (crashRecord == null) {
                return;
            }
            if (pageIndex == 1) {
                crashRecord.setVisibility(View.GONE);
                stepDollarIncomeRecycler.setVisibility(View.VISIBLE);
            }

            stepDollarIncomeRecycler.setAdapter(new StepDollarDetailAdapter(getContext(), stepDollarDetailResponse.getData().getData()));
        } else if (stepDollarDetailResponse.getError() == 1) {
            if (pageIndex == 1) {
                if (crashRecord == null) {
                    return;
                }
                crashRecord.setVisibility(View.VISIBLE);
                crashRecord.setText("暂无积分明细");
                stepDollarIncomeRecycler.setVisibility(View.GONE);
            }
        } else if (stepDollarDetailResponse.getError() == -1) {

        } else if (stepDollarDetailResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }
    }
}
