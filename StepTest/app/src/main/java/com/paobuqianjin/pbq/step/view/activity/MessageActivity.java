package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.MsgSpanFragment;

/**
 * Created by pbq on 2018/1/17.
 */

public class MessageActivity extends BaseActivity {
    private final static String TAG = MessageActivity.class.getSimpleName();
    private MsgSpanFragment msgSpanFragment = new MsgSpanFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.message_container, msgSpanFragment)
                .show(msgSpanFragment)
                .commit();
    }
}
