package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FollowUserResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/3/1.
 */

public class FollowMeFragment extends BaseFragment {
    private final static String TAG = FollowMeFragment.class.getSimpleName();
    @Bind(R.id.invite_dan_recycler)
    RecyclerView inviteDanRecycler;
    LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.invite_dan_fg;
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

        inviteDanRecycler = (RecyclerView) viewRoot.findViewById(R.id.invite_dan_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        inviteDanRecycler.setLayoutManager(layoutManager);
        if (adapter != null) {
            inviteDanRecycler.setAdapter(adapter);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        LocalLog.d(TAG, "setAdapter() ");
        this.adapter = adapter;
        if (inviteDanRecycler != null) {
            inviteDanRecycler.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
