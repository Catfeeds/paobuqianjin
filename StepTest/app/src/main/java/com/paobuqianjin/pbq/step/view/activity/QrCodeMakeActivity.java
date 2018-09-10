package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.qrcode.MyQrCodeFragment;
import com.paobuqianjin.pbq.step.view.fragment.qrcode.QrCodeFragment;


/**
 * Created by pbq on 2018/1/2.
 */

public class QrCodeMakeActivity extends BaseActivity {
    private final static String TAG = QrCodeMakeActivity.class.getSimpleName();
    private QrCodeFragment qrCodeFragment = new QrCodeFragment();

    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";
    private String id;
    private String name;
    private String logo;
    private String pay;
    private String userid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcodde_activity_layout);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(getPackageName());
        if (bundle != null) {
            id = bundle.getString(CIRCLE_ID, "");
            name = bundle.getString(CIRCLE_NAME, "");
            logo = bundle.getString(CIRCLE_LOGO, "");
            pay = bundle.getString(CIRCLE_RECHARGE, "");
        } else {
            name = intent.getStringExtra("username");
            logo = intent.getStringExtra("usericon");
            LocalLog.d(TAG, "name = " + name + "logo = " + logo);
        }
        if (id != null && !id.equals("")) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.qrcode_container, qrCodeFragment)
                    .show(qrCodeFragment).commit();
        } else {
            MyQrCodeFragment myQrCodeFragment = new MyQrCodeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.qrcode_container, myQrCodeFragment)
                    .show(myQrCodeFragment).commit();
        }

    }

}
