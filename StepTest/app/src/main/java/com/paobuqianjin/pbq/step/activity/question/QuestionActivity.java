package com.paobuqianjin.pbq.step.activity.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/7/10.
 */

public class QuestionActivity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.step_zero)
    TextView stepZero;
    @Bind(R.id.step_zero_span)
    LinearLayout stepZeroSpan;
    @Bind(R.id.invite_friend)
    TextView inviteFriend;
    @Bind(R.id.invite_friend_span)
    LinearLayout inviteFriendSpan;
    @Bind(R.id.recharge_question)
    TextView rechargeQuestion;
    @Bind(R.id.recharge_question_span)
    LinearLayout rechargeQuestionSpan;
    @Bind(R.id.qq_wx_question)
    TextView qqWxQuestion;
    @Bind(R.id.qq_wx_question_span)
    LinearLayout qqWxQuestionSpan;
    private final static String ACTION_STEP_HELP = "com.paobuqianjin.pbq.step.STEP_HELP";
    private final static String ACTION_FRIEND_HELP = "com.paobuqianjin.pbq.step.FRIEND_HELP";
    private final static String ACTION_RECHARGE_HELP = "com.paobuqianjin.pbq.step.RECHARGE_HELP";
    private final static String ACTION_ACCOUNT_HELP = "com.paobuqianjin.pbq.step.ACCOUNT_HELP";

    @Override
    protected String title() {
        return "常见问题";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.step_zero_span, R.id.invite_friend_span, R.id.recharge_question_span, R.id.qq_wx_question_span})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.step_zero_span:
                intent.setAction(ACTION_STEP_HELP);
                intent.setClass(this, QuestionDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.invite_friend_span:
                intent.setAction(ACTION_FRIEND_HELP);
                intent.setClass(this, QuestionDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.recharge_question_span:
                intent.setAction(ACTION_RECHARGE_HELP);
                intent.setClass(this, QuestionDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.qq_wx_question_span:
                intent.setAction(ACTION_ACCOUNT_HELP);
                intent.setClass(this, QuestionDetailActivity.class);
                startActivity(intent);
                break;
        }
    }
}
