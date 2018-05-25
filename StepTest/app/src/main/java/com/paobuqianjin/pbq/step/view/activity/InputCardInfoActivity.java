package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputCardInfoActivity extends BaseBarActivity {

    @Bind(R.id.tv_card_type)
    TextView tvCardType;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.btn_next)
    Button btnNext;

    @Override
    protected String title() {
        return "填写银行卡信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_card_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_phone_tips, R.id.tv_protocol, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_phone_tips:
                break;
            case R.id.tv_protocol:
                break;
            case R.id.btn_next:
                startActivity(IdentityAuth3Activity.class, null);
                break;
        }
    }
}
