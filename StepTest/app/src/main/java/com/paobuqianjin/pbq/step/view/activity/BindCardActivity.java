package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindCardActivity extends BaseBarActivity {

    @Bind(R.id.tv_person_name)
    TextView tvPersonName;
    @Bind(R.id.et_card_number)
    EditText etCardNumber;
    @Bind(R.id.btn_next)
    Button btnNext;

    @Override
    protected String title() {
        return "绑定银行卡";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        startActivity(InputCardInfoActivity.class, null);
    }
}
