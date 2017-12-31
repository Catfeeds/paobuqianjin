package com.paobuqianjin.pbq.step.view.fragment.circle;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeSupportDetailAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2017/12/30.
 */

public class LikeSupportFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = LikeSupportFragment.class.getSimpleName();
    @Bind(R.id.like_support_recycler)
    RecyclerView likeSupportRecycler;
    private LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.like_support_fg;
    }

    @Override
    protected String title() {
        return "点赞";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        likeSupportRecycler = (RecyclerView) viewRoot.findViewById(R.id.like_support_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        likeSupportRecycler.setLayoutManager(layoutManager);
        likeSupportRecycler.setAdapter(new LikeSupportDetailAdapter(getContext()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
