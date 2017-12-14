package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.BaseBarActivity;

/**
 * Created by pbq on 2017/12/14.
 */

public class CreateCircleActivity extends BaseBarActivity {
    @Override
    protected String title() {
        return "创建圈子";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_circle_layout);
    }
}
