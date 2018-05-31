package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.MyWalletFragment;

/**
 * Created by pbq on 2018/1/15.
 */

public class MyWalletActivity extends BaseActivity {
    private final static String TAG = MyWalletActivity.class.getSimpleName();
    private MyWalletFragment myWalletFragment = new MyWalletFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallet_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.wallet_container, myWalletFragment)
                .show(myWalletFragment)
                .commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("is_refresh",false)) {
            Presenter.getInstance(this).getUserPackageMoney();
        }
    }
}
