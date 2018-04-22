package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.SponsorGoodFragment;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.SponsorRedLocationFragment;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.SponsorUpPicFragment;

/**
 * Created by pbq on 2018/1/23.
 */
/*
@className :SponsorGoodsPicLookActivity
*@date 2018/1/23
*@author
*@description 商品大图片展示
*/
public class SponsorGoodsPicLookActivity extends BaseActivity {
    private final static String TAG = SponsorGoodsPicLookActivity.class.getSimpleName();
    private final static String SHOW_SPONSOR_PICS_ACTION = "com.paobuqianjin.pbq.step.SHOW_PIC_ACTION";
    private final static String ACTION_INNER_PIC = "com.paobuqianjin.pbq.step.INNER_ACTION";
    private final static String ACTION_OUT_PIC = "com.paobuqianjin.pbq.step.OUT_ACTION";
    private final static String ACTION_RED_PACK_LOCATION = "com.paobuqianjin.pbq.step.RED_PKG_ACTION";
    private SponsorUpPicFragment sponsorUpPicFragment = new SponsorUpPicFragment();
    private SponsorGoodFragment sponsorGoodFragment = new SponsorGoodFragment();
    private SponsorRedLocationFragment sponsorRedLocationFragment = new SponsorRedLocationFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_goods_big_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            if (ACTION_INNER_PIC.equals(intent.getAction()) || ACTION_OUT_PIC.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.sponsor_goods_container, sponsorUpPicFragment)
                        .show(sponsorUpPicFragment)
                        .commit();
            } else if (SHOW_SPONSOR_PICS_ACTION.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.sponsor_goods_container, sponsorGoodFragment)
                        .show(sponsorGoodFragment)
                        .commit();
            } else if (ACTION_RED_PACK_LOCATION.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.sponsor_goods_container, sponsorRedLocationFragment)
                        .show(sponsorRedLocationFragment)
                        .commit();
            }
        }
    }
}
