package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.SponsorGoodsPicLookActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/4/21.
 */

public class SponsorInfoCollectFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = SponsorInfoCollectFragment.class.getSimpleName();
    private final static String ACTION_INNER_PIC = "com.paobuqianjin.pbq.step.INNER_ACTION";
    private final static String ACTION_OUT_PIC = "com.paobuqianjin.pbq.step.OUT_ACTION";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.edit_sponsor_name)
    EditText editSponsorName;
    @Bind(R.id.sponsor_name_pan)
    RelativeLayout sponsorNamePan;
    @Bind(R.id.edit_sponsor_phone)
    EditText editSponsorPhone;
    @Bind(R.id.sponsor_phone_pan)
    RelativeLayout sponsorPhonePan;
    @Bind(R.id.edit_sponsor_time)
    EditText editSponsorTime;
    @Bind(R.id.sponsor_time_pan)
    RelativeLayout sponsorTimePan;
    @Bind(R.id.edit_sponsor_location_pan)
    TextView editSponsorLocationPan;
    @Bind(R.id.sponsor_location_pan)
    RelativeLayout sponsorLocationPan;
    @Bind(R.id.edit_sponsor_location_detail_pan)
    EditText editSponsorLocationDetailPan;
    @Bind(R.id.sponsor_location_detail_pan)
    RelativeLayout sponsorLocationDetailPan;
    @Bind(R.id.edit_sponsor_out_pics)
    TextView editSponsorOutPics;
    @Bind(R.id.sponsor_out_pics_pan)
    RelativeLayout sponsorOutPicsPan;
    @Bind(R.id.edit_sponsor_inner_pics)
    TextView editSponsorInnerPics;
    @Bind(R.id.sponsor_inner_pics_pan)
    RelativeLayout sponsorInnerPicsPan;
    @Bind(R.id.confirm)
    Button confirm;

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_info_collect_fg;
    }

    @Override
    protected String title() {
        return "商铺信息";
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

    @OnClick({R.id.sponsor_time_pan, R.id.sponsor_location_pan, R.id.sponsor_out_pics_pan, R.id.sponsor_inner_pics_pan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sponsor_time_pan:
                LocalLog.d(TAG, "设定营业时间");
                break;
            case R.id.sponsor_location_pan:
                LocalLog.d(TAG, "设定商铺地区");
                break;
            case R.id.sponsor_out_pics_pan:
                LocalLog.d(TAG, "门店照片");
                Intent intent = new Intent();
                intent.setAction(ACTION_INNER_PIC);
                intent.setClass(getContext(), SponsorGoodsPicLookActivity.class);
                startActivity(intent);
                break;
            case R.id.sponsor_inner_pics_pan:
                LocalLog.d(TAG, "店内环境照片");
                Intent intentOut = new Intent();
                intentOut.setAction(ACTION_OUT_PIC);
                intentOut.setClass(getContext(), SponsorGoodsPicLookActivity.class);
                startActivity(intentOut);
                break;
        }
    }
}
