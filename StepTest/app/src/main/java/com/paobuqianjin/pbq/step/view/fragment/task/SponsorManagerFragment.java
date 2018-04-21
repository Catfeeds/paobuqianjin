package com.paobuqianjin.pbq.step.view.fragment.task;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/20.
 */

public class SponsorManagerFragment extends BaseBarStyleTextViewFragment {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.location_des)
    TextView locationDes;
    @Bind(R.id.select_circle)
    ImageView selectCircle;
    @Bind(R.id.delete_sponsor)
    ImageView deleteSponsor;
    @Bind(R.id.delete_sponsor_des)
    TextView deleteSponsorDes;
    @Bind(R.id.edit_sponsor)
    ImageView editSponsor;
    @Bind(R.id.edit_sponsor_des)
    TextView editSponsorDes;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.sponsor_list)
    SwipeMenuRecyclerView sponsorList;
    LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_mananger_fg;
    }

    @Override
    protected String title() {
        return "管理商铺信息";
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
        sponsorList = (SwipeMenuRecyclerView) viewRoot.findViewById(R.id.sponsor_list);
        layoutManager = new LinearLayoutManager(getContext());
        sponsorList.setLayoutManager(layoutManager);
        sponsorList.setAdapter(new SponsorAdapter(getActivity(), null));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
