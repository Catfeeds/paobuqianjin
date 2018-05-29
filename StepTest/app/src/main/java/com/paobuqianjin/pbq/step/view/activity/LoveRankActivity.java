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
    private RechargeRankBundleData rechargeRankBundleData;
    private StepBundleData stepBundleData;
    private final static String ACTION_STEP_RANK = "com.paobuqian.pbq.step.STEP_ACTION";
    private final static String ACTION_LOVE_RANK = "com.paobuqian.pbq.step.LOVE_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love_rank_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            if (ACTION_LOVE_RANK.equals(intent.getAction()) && intent.getParcelableExtra(getPackageName() + "circle_detail") != null) {
                rechargeRankBundleData = (RechargeRankBundleData) intent.getParcelableExtra(getPackageName() + "circle_detail");
                if (rechargeRankBundleData == null) {
                    return;
                } else {
                    loveRankFragment.setRankData(rechargeRankBundleData.getRechargeRankData());
                }
            } else if (ACTION_STEP_RANK.equals(intent.getAction())) {

            }
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.love_rank_container, loveRankFragment)
                .show(loveRankFragment).commit();
    }
}
