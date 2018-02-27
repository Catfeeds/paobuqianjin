package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.MyReleaseFragment;

/**
 * Created by pbq on 2018/1/24.
 */

public class MyReleaseActivity extends BaseActivity {
    private final static String TAG = MyReleaseActivity.class.getSimpleName();
    private MyReleaseFragment myReleaseFragment = new MyReleaseFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_release_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.my_release_container, myReleaseFragment)
                .show(myReleaseFragment)
                .commit();
    }
}
