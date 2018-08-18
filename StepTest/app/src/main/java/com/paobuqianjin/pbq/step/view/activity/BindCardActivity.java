package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindCardActivity extends BaseBarActivity {

    private static final int REQ_ADD_CARD = 1;
    private final String TAG = this.getClass().getSimpleName();

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

        String nameStr = getIntent().getStringExtra("name");
        if (TextUtils.isEmpty(nameStr)) {
            Intent intent = new Intent();
            intent.setClass(BindCardActivity.this, IdentityAuth1Activity.class);
            startActivityForResult(intent, REQ_ADD_CARD);
            finish();
        }
        tvPersonName.setText(nameStr);
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        String cardNum = etCardNumber.getText().toString();
        if (TextUtils.isEmpty(cardNum)) {
            PaoToastUtils.showShortToast(this,"请输入卡号");
            return;
        }
        Intent intent = getIntent();
        intent.setClass(this, InputCardInfoActivity.class);
        intent.putExtra("personNameStr", tvPersonName.getText().toString());
        intent.putExtra("cardNumStr", cardNum);
        startActivityForResult(intent, REQ_ADD_CARD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (requestCode == REQ_ADD_CARD && resultCode == IdentityAuth3Activity.RES_SUC) {
            if(this!=null) finish();
        }
    }
}
