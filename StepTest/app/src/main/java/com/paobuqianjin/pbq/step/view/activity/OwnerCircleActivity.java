package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.MyCircleBundleData;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.OwnerCreateJoinFragment;


/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCircleActivity extends BaseActivity {
    private final static String TAG = OwnerCircleActivity.class.getSimpleName();
    private OwnerCreateJoinFragment ownerCircleFragment = new OwnerCreateJoinFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_circle_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        LocalLog.d(TAG, "initView() enter");
        Intent intent = getIntent();
        if (intent != null && intent.getParcelableExtra(getPackageName()) != null) {
            MyCircleBundleData myCircleBundleData = (MyCircleBundleData) intent.getParcelableExtra(getPackageName());
            ownerCircleFragment.setOwnerCreateCircleData(myCircleBundleData.getMyCircleData());
        } else {
            ownerCircleFragment.setOwnerCreateCircleData(null);
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.owner_circle_container, ownerCircleFragment)
                .show(ownerCircleFragment).commit();
    }
}
