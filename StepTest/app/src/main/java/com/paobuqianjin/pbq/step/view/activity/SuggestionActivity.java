package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/1/16.
 */

public class SuggestionActivity extends BaseBarActivity {
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.content_text)
    TextView contentText;
    @Bind(R.id.content_edit)
    EditText contentEdit;
    @Bind(R.id.content_bound)
    TextView contentBound;
    @Bind(R.id.content_span)
    RelativeLayout contentSpan;
    @Bind(R.id.phone_num)
    TextView phoneNum;
    @Bind(R.id.connect_us)
    TextView connectUs;
    @Bind(R.id.suggetion_btn)
    Button suggetionBtn;
    @Bind(R.id.help)
    TextView help;

    @Override
    protected String title() {
        return "意见反馈";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }
}
