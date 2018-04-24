package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ReChargeRankResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.adapter.RankAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pbq on 2017/12/29.
 */

public class LoveRankFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = LoveRankFragment.class.getSimpleName();
    RecyclerView rankRecycler;
    LinearLayoutManager layoutManager;
    List<?> mData;

    // 设置数据
    public void setRankData(List<?> data) {
        mData = data;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.love_rank_fg;
    }

    @Override
    protected String title() {
        return "爱心排行榜";
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        LocalLog.d(TAG, "initView() enter ");
        rankRecycler = (RecyclerView) viewRoot.findViewById(R.id.rank_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rankRecycler.setLayoutManager(layoutManager);
        rankRecycler.setAdapter(new RankAdapter(getContext(), mData));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
