package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.WeChatBindFragment;

/**
 * Created by pbq on 2018/2/7.
 */
/*
@className :UserBindBankListActivity
*@date 2018/2/7
*@author
*@description 提现账户选择列表
*/
public class UserBindBankListActivity extends BaseActivity {
    private final static String TAG = UserBindBankListActivity.class.getSimpleName();
    private WeChatBindFragment weChatBindFragment = new WeChatBindFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_bind_bank_list_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.accout_list_container, weChatBindFragment)
                .show(weChatBindFragment)
                .commit();
    }
}
