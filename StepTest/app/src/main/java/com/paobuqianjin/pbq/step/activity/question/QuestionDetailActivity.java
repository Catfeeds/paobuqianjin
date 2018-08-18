package com.paobuqianjin.pbq.step.activity.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.j256.ormlite.stmt.query.In;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.owner.AccountChargeFragment;
import com.paobuqianjin.pbq.step.view.fragment.owner.StepOrFriendFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/7/10.
 */

public class QuestionDetailActivity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.container_question)
    LinearLayout containerQuestion;
    private final static String ACTION_STEP_HELP = "com.paobuqianjin.pbq.step.STEP_HELP";
    private final static String ACTION_FRIEND_HELP = "com.paobuqianjin.pbq.step.FRIEND_HELP";
    private final static String ACTION_RECHARGE_HELP = "com.paobuqianjin.pbq.step.RECHARGE_HELP";
    private final static String ACTION_ACCOUNT_HELP = "com.paobuqianjin.pbq.step.ACCOUNT_HELP";

    @Override
    protected String title() {
        return "问题详情";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            if (ACTION_STEP_HELP.equals(intent.getAction()) || ACTION_FRIEND_HELP.equals(intent.getAction())) {
                StepOrFriendFragment stepOrFriendFragment = new StepOrFriendFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container_question, stepOrFriendFragment)
                        .show(stepOrFriendFragment)
                        .commit();
            } else if (ACTION_RECHARGE_HELP.equals(intent.getAction()) || ACTION_ACCOUNT_HELP.equals(intent.getAction())) {
                AccountChargeFragment accountChargeFragment = new AccountChargeFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container_question, accountChargeFragment)
                        .show(accountChargeFragment)
                        .commit();
            }
        }
    }
}
