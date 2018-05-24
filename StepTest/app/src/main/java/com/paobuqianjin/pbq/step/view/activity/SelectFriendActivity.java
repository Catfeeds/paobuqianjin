package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.SelectSponsorVipFragment;
import com.paobuqianjin.pbq.step.view.fragment.task.SelectTaskFriendFragment;
import com.paobuqianjin.pbq.step.view.fragment.task.SelectVipFragment;

/**
 * Created by pbq on 2018/1/26.
 */
/*
@className :SelectFriendActivity
*@date 2018/1/26
*@author
*@description 选择任务发布对象
*/
public class SelectFriendActivity extends BaseActivity {
    private final static String TAG = SelectFriendActivity.class.getSimpleName();
    private final static String ACTION_TASK = "com.paobuqianjin.pbq.step.ACTION_TASK";
    private final static String ACTION_VIP = "com.paobuqianjin.pbq.step.ACTION_VIP";
    private final static String ACTION_SPONSOR_VIP = "com.paobuqianjin.pbq.step.ACTION_SPONSOR_VIP";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_task_friend_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {

            if (ACTION_TASK.equals(intent.getAction())) {
                SelectTaskFriendFragment selectTaskFriendFragment = new SelectTaskFriendFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.select_container, selectTaskFriendFragment)
                        .show(selectTaskFriendFragment)
                        .commit();
            } else if (ACTION_VIP.equals(intent.getAction())) {
                SelectVipFragment selectVipFragment = new SelectVipFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.select_container, selectVipFragment)
                        .show(selectVipFragment)
                        .commit();
            } else if (ACTION_SPONSOR_VIP.equals(intent.getAction())) {
                SelectSponsorVipFragment selectSponsorVipFragment = new SelectSponsorVipFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.select_container, selectSponsorVipFragment)
                        .show(selectSponsorVipFragment)
                        .commit();
            }
        }

    }
}
