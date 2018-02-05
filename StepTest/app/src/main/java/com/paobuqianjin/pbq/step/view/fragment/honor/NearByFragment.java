package com.paobuqianjin.pbq.step.view.fragment.honor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.NearByResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.NearByInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.dan.NearByAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/9.
 */

public class NearByFragment extends BaseFragment implements NearByInterface {
    private final static String TAG = NearByFragment.class.getSimpleName();
    @Bind(R.id.near_by_recycler)
    RecyclerView nearByRecycler;
    private LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.near_by_fg;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Presenter.getInstance(context).attachUiInterface(this);
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
        nearByRecycler = (RecyclerView) viewRoot.findViewById(R.id.near_by_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        nearByRecycler.setLayoutManager(layoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(NearByResponse nearByResponse) {
        LocalLog.d(TAG, "NearByResponse() enter");

        nearByRecycler.setAdapter(new NearByAdapter(getContext(),nearByResponse.getData()));
    }
}
