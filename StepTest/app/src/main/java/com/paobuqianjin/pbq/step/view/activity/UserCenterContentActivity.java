package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.j256.ormlite.stmt.query.In;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageContentBundleData;
import com.paobuqianjin.pbq.step.data.bean.bundle.MessageLikeBundleData;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.ContentEmptyFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.SystemMsgFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.UserContentFragment;

/**
 * Created by pbq on 2018/1/23.
 */

public class UserCenterContentActivity extends BaseActivity {
    private final static String TAG = UserCenterContentActivity.class.getSimpleName();
    private UserContentFragment userContentFragment = new UserContentFragment();
    private ContentEmptyFragment contentEmptyFragment = new ContentEmptyFragment();
    private SystemMsgFragment systemMsgFragment = new SystemMsgFragment();
    private final static String LIKE_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.LIKE_MESSAGE_ACTION";
    private final static String CONTENT_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.CONTENT_MESSAGE_ACTION";
    private final static String SYS_MESSAGE_ACTION = "com.paobuqianjin.pbq.step.SYS_MESSAGE_ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_layout);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if (intent != null) {
            if (LIKE_MESSAGE_ACTION.equals(intent.getAction())) {
                MessageLikeBundleData messageLikeBundleData = (MessageLikeBundleData) intent.getParcelableExtra(getPackageName());
                userContentFragment.setData(messageLikeBundleData);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.user_content_container, userContentFragment)
                        .show(userContentFragment)
                        .commit();
            } else if (CONTENT_MESSAGE_ACTION.equals(intent.getAction())) {
                MessageContentBundleData messageContentBundleData = (MessageContentBundleData) intent.getParcelableExtra(getPackageName());
                userContentFragment.setData(messageContentBundleData);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.user_content_container, userContentFragment)
                        .show(userContentFragment)
                        .commit();
            } else if (SYS_MESSAGE_ACTION.equals(intent.getAction())) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.user_content_container, systemMsgFragment)
                        .show(systemMsgFragment)
                        .commit();
            }
        }

    }
}
