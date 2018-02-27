package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.FillInCodeFragment;

/**
 * Created by pbq on 2018/2/27.
 */

public class FillInviteCodeActivity extends BaseActivity {
    private final static String TAG = FillInviteCodeActivity.class.getSimpleName();
    private FillInCodeFragment fillInCodeFragment = new FillInCodeFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_invite_code_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fill_code_container, fillInCodeFragment)
                .show(fillInCodeFragment)
                .commit();
    }
}
