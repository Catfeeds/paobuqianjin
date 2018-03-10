package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.InOutComeFragment;

/**
 * Created by pbq on 2018/3/10.
 */
/*
@className :InoutcomDetailActivity
*@date 2018/3/10
*@author
*@description 钱包明细
*/
public class InoutcomDetailActivity extends BaseActivity {
    private final static String TAG = InoutcomDetailActivity.class.getSimpleName();
    private InOutComeFragment inOutComeFragment = new InOutComeFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_out_come_layout_activity);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.in_out_come_container, inOutComeFragment)
                .show(inOutComeFragment)
                .commit();
    }
}
