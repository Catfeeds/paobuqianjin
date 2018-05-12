package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.FeedBackParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SuggestResponse;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.SuggestInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/16.
 */

public class SuggestionActivity extends BaseBarActivity implements SuggestInterface {
    private final static String TAG = SuggestionActivity.class.getSimpleName();
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
    EditText connectUs;
    @Bind(R.id.suggetion_btn)
    Button suggetionBtn;
    @Bind(R.id.help)
    TextView help;
    FeedBackParam feedBackParam = new FeedBackParam();

    @Override
    protected String title() {
        return "意见反馈";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_activity_layout);
        ButterKnife.bind(this);
        Presenter.getInstance(this).attachUiInterface(this);
    }

    @Override
    protected void initView() {
        super.initView();
        contentEdit = (EditText) findViewById(R.id.content_edit);
        contentEdit.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(this).dispatchUiInterface(this);
    }

    @OnClick(R.id.suggetion_btn)
    public void onClick() {
        LocalLog.d(TAG, "提交");
        feedBackParam.setContent(contentEdit.getText().toString());
        feedBackParam.setMobile(connectUs.getText().toString());
        if (feedBackParam.getContent() == null || feedBackParam.getContent().equals("")) {
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Presenter.getInstance(this).postFeedBack(feedBackParam);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (contentEdit.getText().toString().length() > 0) {
                suggetionBtn.setEnabled(true);
            } else {
                suggetionBtn.setEnabled(false);
            }
            contentBound.setText(s.length() + "/200");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void response(SuggestResponse suggestResponse) {
        if (suggestResponse.getError() == 0) {
            LocalLog.d(TAG, "SuggestResponse() enter");
            Toast.makeText(this, "意见反馈成功，感谢你的支持", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        Toast.makeText(this, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
