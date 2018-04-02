package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.OldPassChangeFragment;

/**
 * Created by pbq on 2018/4/2.
 */

public class OlderPassChangeActivity extends BaseActivity {
    private final static String TAG = OlderPassChangeActivity.class.getSimpleName();
    private OldPassChangeFragment oldPassChangeFragment = new OldPassChangeFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_pass_cahnge_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.older_pass_container, oldPassChangeFragment)
                .show(oldPassChangeFragment)
                .commit();
    }
}
