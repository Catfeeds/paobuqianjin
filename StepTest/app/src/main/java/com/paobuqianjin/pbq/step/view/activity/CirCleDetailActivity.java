package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleDetailAdminFragment;
import com.paobuqianjin.pbq.step.view.fragment.circle.CircleDetailNoAdminMainFragment;

/**
 * Created by pbq on 2017/12/29.
 */

public class CirCleDetailActivity extends BaseActivity {
    private final static String TAG = CirCleDetailActivity.class.getSimpleName();
    private CircleDetailAdminFragment circleDetailAdminFragment = new CircleDetailAdminFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_detail_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.circle_detail_container, circleDetailAdminFragment)
                .show(circleDetailAdminFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
