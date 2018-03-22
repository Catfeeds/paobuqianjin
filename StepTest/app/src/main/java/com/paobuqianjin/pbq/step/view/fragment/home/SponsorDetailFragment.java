package com.paobuqianjin.pbq.step.view.fragment.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/23.
 */

public class SponsorDetailFragment extends BaseBarStyleTextViewFragment {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.sponsor_images)
    ViewPager sponsorImages;
    @Bind(R.id.pic_sample)
    ImageView picSample;
    @Bind(R.id.current_pic)
    TextView currentPic;
    @Bind(R.id.sponsor_face_span)
    RelativeLayout sponsorFaceSpan;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.sponsor_tel_icon)
    ImageView sponsorTelIcon;
    @Bind(R.id.sponsor_tel_num)
    TextView sponsorTelNum;
    @Bind(R.id.sponsor_tel_span)
    RelativeLayout sponsorTelSpan;
    @Bind(R.id.sponsor_time_icon)
    ImageView sponsorTimeIcon;
    @Bind(R.id.sponsor_time_num)
    TextView sponsorTimeNum;
    @Bind(R.id.sponsor_time_span)
    RelativeLayout sponsorTimeSpan;
    @Bind(R.id.sponsor_location_icon)
    ImageView sponsorLocationIcon;
    @Bind(R.id.sponsor_location_num)
    TextView sponsorLocationNum;
    @Bind(R.id.sponsor_location_span)
    RelativeLayout sponsorLocationSpan;
    @Bind(R.id.line2)
    ImageView line2;
    @Bind(R.id.sponsor_have)
    TextView sponsorHave;
    @Bind(R.id.go_to)
    ImageView goTo;
    @Bind(R.id.goto_sponsor)
    RelativeLayout gotoSponsor;
    @Bind(R.id.center_pic)
    ImageView centerPic;

    @Override
    protected String title() {
        return "商家详情";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_detail_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
