package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.MyCreateCircleBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.MyJoinCreateCircleBudleData;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.OwnerCreateJoinFragment;


/**
 * Created by pbq on 2017/12/28.
 */

public class OwnerCircleActivity extends BaseActivity {
    private final static String TAG = OwnerCircleActivity.class.getSimpleName();
    private OwnerCreateJoinFragment ownerCircleFragment = new OwnerCreateJoinFragment();
    private int my_create_total_page = 0;
    private int my_join_total_page = 0;

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
        MyCreateCircleBundleData myCreateCircleBundleData = null;
        MyJoinCreateCircleBudleData myJoinCreateCircleBudleData = null;
        if (intent != null && intent.getParcelableExtra(getPackageName() + "my_create") != null) {
            myCreateCircleBundleData = (MyCreateCircleBundleData) intent.getParcelableExtra(getPackageName() + "my_create");
        }
        if (intent != null && intent.getParcelableExtra(getPackageName() + "my_join") != null) {
            myJoinCreateCircleBudleData = (MyJoinCreateCircleBudleData) intent.getParcelableExtra(getPackageName() + "my_join");
        }

        ownerCircleFragment.setOwnerCreateCircleData(myCreateCircleBundleData.getMyCreateCircleData(), myJoinCreateCircleBudleData.getMyJoinCircleData());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.owner_circle_container, ownerCircleFragment)
                .show(ownerCircleFragment).commit();
    }
}
