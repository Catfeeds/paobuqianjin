package com.paobuqianjin.pbq.step.view.fragment.owner;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

/**
 * Created by pbq on 2018/1/25.
 */
/*
@className :ReleaseRecordActivity
*@date 2018/1/25
*@author
*@description  任务发布记录
*/
public class ReleaseRecordActivity extends BaseActivity {
    private final static String TAG = ReleaseRecordActivity.class.getSimpleName();
    private ReleaseRecordFragment releaseRecordFragment = new ReleaseRecordFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_record_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.release_record_container, releaseRecordFragment)
                .show(releaseRecordFragment)
                .commit();
    }
}
