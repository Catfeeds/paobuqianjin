package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.SelectTaskFriendFragment;

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
    private SelectTaskFriendFragment selectTaskFriendFragment = new SelectTaskFriendFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_task_friend_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.select_container, selectTaskFriendFragment)
                .show(selectTaskFriendFragment)
                .commit();
    }
}
