package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.ContentEmptyFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.UserContentFragment;

/**
 * Created by pbq on 2018/1/23.
 */

public class UserCenterContentActivity extends BaseActivity {
    private final static String TAG = UserCenterContentActivity.class.getSimpleName();
    private UserContentFragment userContentFragment = new UserContentFragment();
    private ContentEmptyFragment contentEmptyFragment = new ContentEmptyFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_content_container, userContentFragment)
                .show(userContentFragment)
                .commit();
    }
}
