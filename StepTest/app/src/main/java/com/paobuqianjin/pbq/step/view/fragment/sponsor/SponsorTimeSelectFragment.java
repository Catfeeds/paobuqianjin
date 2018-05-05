package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.adapter.TimeAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/21.
 */

public class SponsorTimeSelectFragment extends BaseBarStyleTextViewFragment {

    LinearLayoutManager layoutManager;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.time_recycler)
    RecyclerView timeRecycler;

    private String[] data = new String[7];

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_time_select_fg;
    }

    @Override
    protected String title() {
        return "自定义";
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
        ButterKnife.bind(this, viewRoot);
        layoutManager = new LinearLayoutManager(getContext());
        timeRecycler.setLayoutManager(layoutManager);
        timeRecycler.setAdapter(new TimeAdapter(getContext(), data));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
