package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputCardInfoActivity extends BaseBarActivity {
    private final static String ACTION_BIND_NEW_CARD = "com.paobuqianjin.pbq.step.ACTION_ADD_CARD";
    private static final int REQ_ADD_CARD = 1;
    private final String TAG = getClass().getSimpleName();
    private String cardNum;
    private String personName;

    @Bind(R.id.tv_card_type)
    TextView tvCardType;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.et_person_name)
    EditText etPersonName;
    private NormalDialog dialog;


    @Override
    protected String title() {
        return "填写银行卡信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_card_info);
        ButterKnife.bind(this);

        cardNum = getIntent().getStringExtra("cardNumStr");
        personName = getIntent().getStringExtra("personNameStr");

        if (TextUtils.isEmpty(personName) || TextUtils.isEmpty(cardNum)) {
            LocalLog.e(TAG, getString(R.string.error_param));
            finish();
            return;
        }
        cbAgree.setChecked(Presenter.getInstance(this).getReadCrashProtocol(this));

        Map<String, String> params = new HashMap<>();
        params.put("cardno", cardNum.replaceAll(" ",""));
        Presenter.getInstance(this).getPaoBuSimple(NetApi.URL_GET_CARD_BANK, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String bank = jsonObj.getString("bankname");
                    tvCardType.setText(bank);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showShortToast(InputCardInfoActivity.this, errorBean.getMessage());
                }
            }
        });

    }

    @OnClick({R.id.iv_phone_tips, R.id.tv_protocol, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_phone_tips:
                if (dialog == null) {
                    dialog = new NormalDialog(this);
                    dialog.setTitle(getString(R.string.phone_desc));
                    dialog.setMessage(getString(R.string.bank_card_phone_num_tips));
                    dialog.setSingleBtn(true);
                    dialog.setYesOnclickListener(getString(R.string.i_know), new NormalDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            dialog.dismiss();
                        }
                    });
                }
                dialog.show();
                break;
            case R.id.tv_protocol:
                startActivity(AgreementActivity.class, null, false, UserServiceProtcolFragment.USER_SERVICE_AGREEMENT_ACTION);
                break;
            case R.id.btn_next:
                final String phoneNum = etPersonName.getText().toString();
                if (!filter(phoneNum)) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(IdentityAuth3Activity.KEY_PERSON_NAME, personName);
                bundle.putString(IdentityAuth3Activity.KEY_CARD_NUM, cardNum);
                bundle.putString(IdentityAuth3Activity.KEY_PHONE_NUM, phoneNum);

                Intent intent = new Intent(this, IdentityAuth3Activity.class);
                intent.setAction(ACTION_BIND_NEW_CARD);
                intent.putExtra(getPackageName(), bundle);
                startActivityForResult(intent, REQ_ADD_CARD);
                break;
        }
    }

    private boolean filter(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            PaoToastUtils.showShortToast(this, "手机号码不能为空");
            return false;
        }
        if (phoneNum.length() < 11) {
            PaoToastUtils.showShortToast(this, "手机号码格式不正确，请重新输入");
            return false;
        }
        if (!cbAgree.isChecked()) {
            PaoToastUtils.showShortToast(this, "请认真阅读《用户协议》并确认勾选");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADD_CARD && resultCode == IdentityAuth3Activity.RES_SUC) {
            setResult(IdentityAuth3Activity.RES_SUC);
            finish();
        }
    }
}
