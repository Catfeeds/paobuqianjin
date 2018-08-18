package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdentityAuth1Activity extends BaseBarActivity {
    private final static String ACTION_AUTH1 = "com.paobuqianjin.pbq.setp.ACTION_AUTH1";
    private final String TAG = this.getClass().getSimpleName();
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
        setContentView(R.layout.activity_identity_auth1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    public void onClick() {

        String cardNum = etCardNumber.getText().toString();
        if (TextUtils.isEmpty(cardNum.trim())) {
            PaoToastUtils.showShortToast(this, "请输入银行卡号");
            return;
        }
        IdentityAuth3Activity.targetActivity = MainActivity.class;
//        btnNext.setEnabled(false);
        Bundle bundle = new Bundle();
        bundle.putString("cardNum", cardNum);
        Intent intent = new Intent(this, IdentityAuth2Activity.class);
        intent.setAction(ACTION_AUTH1);
        intent.putExtra(getPackageName(), bundle);
        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IdentityAuth3Activity.RES_SUC) {
            setResult(resultCode);
            finish();
        }
    }

}
