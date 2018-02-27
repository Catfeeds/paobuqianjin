package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.ReleaseFragment;

/**
 * Created by pbq on 2018/1/25.
 */
/*
@className :MyReleaseDetailActivity
*@date 2018/1/25
*@author
*@description    发布任务的详情
*/
public class MyReleaseDetailActivity extends BaseActivity {
    private final static String TAG = MyReleaseDetailActivity.class.getSimpleName();
    private ReleaseFragment releaseFragment = new ReleaseFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_detail_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.release_container, releaseFragment)
                .show(releaseFragment)
                .commit();
    }
}
