package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.qrcode.QrCodeFragment;


/**
 * Created by pbq on 2018/1/2.
 */

public class QrCodeMakeActivity extends BaseActivity {
    private final static String TAG = QrCodeMakeActivity.class.getSimpleName();
    private QrCodeFragment qrCodeFragment = new QrCodeFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcodde_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.qrcode_container, qrCodeFragment)
                .show(qrCodeFragment).commit();
    }

}
