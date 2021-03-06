package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.activity.SponsorSelectActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorTimeSelectActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/23.
 */

public class SponsorTimeFragment extends BaseFragment {
    @Bind(R.id.button_return_bar)
    TextView buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.work_time_des)
    TextView workTimeDes;
    @Bind(R.id.time_pan)
    RelativeLayout timePan;
    private final static int REQUEST_TIME = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_time_fg;
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
        timePan = (RelativeLayout) viewRoot.findViewById(R.id.time_pan);
        timePan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent intent = new Intent();
                intent.setClass(getContext(), SponsorTimeSelectActivity.class);
                startActivityForResult(intent, REQUEST_TIME);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
