package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.DynamicCommentResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.DynamicCommentUiInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.circle.DynamicDetailFragment;

/**
 * Created by pbq on 2017/12/29.
 */

public class DynamicActivity extends BaseActivity {
    private final static String TAG = DynamicActivity.class.getSimpleName();
    private DynamicDetailFragment dynamicDetailFragment = new DynamicDetailFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_detail_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            int dynamicid = intent.getIntExtra(getPackageName() + "dynamicid", -1);
            if (dynamicid != -1) {
                dynamicDetailFragment.setDynamicid(dynamicid);
            }
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_dynamic_detail, dynamicDetailFragment)
                .show(dynamicDetailFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
