package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.paobuqianjin.pbq.step.R;
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

public class IdentityAuth2Activity extends BaseBarActivity {
    private static final String TAG = IdentityAuth2Activity.class.getSimpleName();
    private final static String ACTION_AUTH1 = "com.paobuqianjin.pbq.setp.ACTION_AUTH1";
    private final static String ACTION_FORGET_PSW = "com.paobuqianjin.pbq.setp.ACTION_FORGET_PSW";
    private final static String ACTION_FIRST = "com.paobuqianjin.pbq.step.ACTION_FIRSTSET";
    private final static String ACTION_FORGET_REST = "com.paobuqianjin.pbq.setp.ACTION_FORGET_RESET";
    @Bind(R.id.tv_target_phone)
    TextView tvTargetPhone;
    @Bind(R.id.tv_card_type)
    EditText tvCardType;
    @Bind(R.id.tv_protocol)
    TextView tvProtocol;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.et_person_name)
    EditText etPersonName;
    @Bind(R.id.et_person_id)
    EditText etPersonId;
    @Bind(R.id.et_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.textView7)
    TextView textView7;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.bar_title)
    TextView barTitle;

    private String cardNum;
    private String action = "";
    private String cardId = "";

    @Override
    protected String title() {
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_auth2);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            if (ACTION_AUTH1.equals(intent.getAction())) {
                barTitle.setText("填写银行卡信息");
                action = ACTION_AUTH1;
                tvCardType.setHint("--");
                cardNum = getBundle().getString("cardNum");
                tvCardType.setFocusable(false);
                tvCardType.setFocusableInTouchMode(false);
                getCardType();
            } else if (ACTION_FORGET_PSW.equals(intent.getAction())) {
                barTitle.setText("忘记支付密码");
                textView7.setVisibility(View.GONE);
                tvTargetPhone.setText("请输入持卡人本人卡号");
                action = ACTION_FORGET_PSW;
                String cardInfo = getBundle().getString("cardinfo");
                cardId = getBundle().getString("cardid");
                LocalLog.d(TAG, "cardinfo = " + cardInfo);
                if (!TextUtils.isEmpty(cardInfo)) {
                    tvCardType.setHint(cardInfo);
                    tvCardType.setFocusable(true);
                    tvCardType.setFocusableInTouchMode(true);
                    tvCardType.requestFocus();
                    tvCardType.setInputType(InputType.TYPE_CLASS_NUMBER);
                    tvCardType.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            cardNum = s.toString().trim();
                        }
                    });
                }
                textView4.setText("卡号");
            }
        }
        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Presenter.getInstance(IdentityAuth2Activity.this).setReadCrashProtocol(IdentityAuth2Activity.this, isChecked);
            }
        });
        cbAgree.setChecked(Presenter.getInstance(IdentityAuth2Activity.this).getReadCrashProtocol(IdentityAuth2Activity.this));
    }

    private void getCardType() {
        if (TextUtils.isEmpty(cardNum)) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("cardno", cardNum.replaceAll(" ", ""));
        Presenter.getInstance(this).getPaoBuSimple(NetApi.URL_GET_CARD_BANK, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String bank = jsonObj.getString("bankname");
                    // TODO: 2018/5/18 paobu test data
                    tvCardType.setText(bank);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showShortToast(IdentityAuth2Activity.this, errorBean.getMessage());
                }
            }
        });
    }

    @OnClick({R.id.tv_protocol, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_protocol:
                startActivity(AgreementActivity.class, null, false, UserServiceProtcolFragment.USER_SERVICE_AGREEMENT_ACTION);
                break;
            case R.id.btn_next:

                if (!cbAgree.isChecked()) {
                    PaoToastUtils.showShortToast(this, "请认真阅读" + getString(R.string.user_protcol) + "并确认勾选");
                    return;
                }
                final String personId = etPersonId.getText().toString();
                final String personName = etPersonName.getText().toString();
                final String phoneNum = etPhoneNum.getText().toString();
                if (TextUtils.isEmpty(personId) || TextUtils.isEmpty(personName) || TextUtils.isEmpty(phoneNum)
                        || TextUtils.isEmpty(cardNum)) {
                    PaoToastUtils.showLongToast(this, "请填写完信息");
                    return;
                }
                Bundle bundle = getBundle();
                bundle.putString("personId", personId);
                bundle.putString("personName", personName);
                bundle.putString("phoneNum", phoneNum);
                bundle.putString("cardNum", cardNum);
                Intent intent = null;
                if (action.equals(ACTION_AUTH1)) {
                    intent = new Intent(IdentityAuth2Activity.this, IdentityAuth3Activity.class);
                    intent.setAction(ACTION_FIRST);
                    intent.putExtra(getPackageName(), bundle);
                    startActivityForResult(intent, 200);
                } else if (action.equals(ACTION_FORGET_PSW)) {
                    bundle.putString("cardid", cardId);
                    intent = new Intent(IdentityAuth2Activity.this, IdentityAuth3Activity.class);
                    intent.setAction(ACTION_FORGET_REST);
                    intent.putExtra(getPackageName(), bundle);
                    startActivityForResult(intent, 200);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IdentityAuth3Activity.RES_SUC && requestCode == 200) {
            if (action.equals(ACTION_AUTH1)) {
                setResult(IdentityAuth3Activity.RES_SUC);
                finish();
            } else if (action.equals(ACTION_FORGET_PSW)) {
                finish();
            }
        }
    }
}