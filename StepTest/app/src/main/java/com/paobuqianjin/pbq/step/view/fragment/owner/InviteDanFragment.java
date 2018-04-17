package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.InviteDanResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyInviteResponse;
import com.paobuqianjin.pbq.step.presenter.im.InviteInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.InviteDanAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/2/27.
 */

public class InviteDanFragment extends BaseFragment {
    @Bind(R.id.invite_dan_recycler)
    RecyclerView inviteDanRecycler;
    LinearLayoutManager layoutManager;
    InviteDanAdapter inviteDanAdapter;

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

    public void setDanAdapter(InviteDanAdapter inviteDanAdapter) {
        if (inviteDanAdapter != null) {
            this.inviteDanAdapter = inviteDanAdapter;
        }
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        inviteDanRecycler = (RecyclerView) viewRoot.findViewById(R.id.invite_dan_recycler);
        inviteDanRecycler.setLayoutManager(layoutManager);
        if (inviteDanAdapter != null) {
            inviteDanRecycler.setAdapter(inviteDanAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
