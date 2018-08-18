package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.MyCreateCircleBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.RechargeRankBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.StepBundleData;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.LoveRankFragment;

/**
 * Created by pbq on 2017/12/29.
 */

public class LoveRankActivity extends BaseActivity {
    private final static String TAG = LoveRankActivity.class.getSimpleName();
    private LoveRankFragment loveRankFragment = new LoveRankFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love_rank_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.love_rank_container, loveRankFragment)
                .show(loveRankFragment).commit();
    }
}
