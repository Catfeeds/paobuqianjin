package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.RechargeWalletFragment;

/**
 * Created by pbq on 2018/2/6.
 */
/*
@className :MyWalletPayActivity
*@date 2018/2/6
*@author
*@description
*/
public class MyWalletPayActivity extends BaseActivity {
    private final static String TAG = MyWalletPayActivity.class.getSimpleName();
    private RechargeWalletFragment rechargeWalletFragment = new RechargeWalletFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_wallet_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.recharge_fg_container, rechargeWalletFragment)
                .show(rechargeWalletFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
