package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.MyDynamicFragment;

/**
 * Created by pbq on 2018/1/17.
 */

public class MyDynamicActivity extends BaseActivity {
    private final static String TAG = MyDynamicActivity.class.getSimpleName();
    private MyDynamicFragment myDynamicFragment = new MyDynamicFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dynamic_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.my_dynamic_container, myDynamicFragment)
                .show(myDynamicFragment)
                .commit();
    }
}
