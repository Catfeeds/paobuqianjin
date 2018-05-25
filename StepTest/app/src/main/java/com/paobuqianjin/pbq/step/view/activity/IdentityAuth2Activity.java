package com.paobuqianjin.pbq.step.view.activity;

import android.os.Bundle;
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
import okhttp3.Call;

public class IdentityAuth2Activity extends BaseBarActivity {

    private static final String TAG = IdentityAuth2Activity.class.getSimpleName();
    @Bind(R.id.tv_target_phone)
    TextView tvTargetPhone;
    @Bind(R.id.tv_card_type)
    TextView tvCardType;
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

    private String cardNum;

    @Override
    protected String title() {
        return "填写银行卡信息";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_auth2);
        ButterKnife.bind(this);

        cardNum = getBundle().getString("cardNum");
        getCardType();
        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Presenter.getInstance(IdentityAuth2Activity.this).setReadCrashProtocol(IdentityAuth2Activity.this, isChecked);
            }
        });
        cbAgree.setChecked(Presenter.getInstance(IdentityAuth2Activity.this).getReadCrashProtocol(IdentityAuth2Activity.this));
    }

    private void getCardType() {
        Map<String, String> params = new HashMap<>();
        params.put("key", NetApi.MOB_APPKEY);
        params.put("card", cardNum.replaceAll(" ",""));
        Presenter.getInstance(this).getPaoBuSimple(NetApi.URL_GET_CARD_BANK, params, new PaoCallBack() {

            /*       {
  "msg": "success",
  "result": {
    "bank": "工商银行",
    "bin": "622202",
    "binNumber": 6,
    "cardName": "E时代卡",
    "cardNumber": 19,
    "cardType": "借记卡"
  },
  "retCode": "200"
}*/
            @Override
            public void onResponse(String s, int i) {
                super.onResponse(s,i);
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("result");
                    String bank = jsonObj.getString("bank");
                    // TODO: 2018/5/18 paobu test data
                    tvCardType.setText(bank);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Call call, Exception e, int i, Object o) {
                super.onError(call, e, i, o);

            }

            @Override
            protected void onSuc(String s) {
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
            }
        });
    }

    @OnClick({R.id.tv_protocol, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_protocol:
                startActivity(AgreementActivity.class, null, false, UserServiceProtcolFragment.USER_CRASH_ACTION);
                break;
            case R.id.btn_next:
                final String personId = etPersonId.getText().toString();
                final String personName = etPersonName.getText().toString();
                final String phoneNum = etPhoneNum.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("card_id", cardNum);
                params.put("personId", personId);
                params.put("personName", personName);
                params.put("phoneNum", phoneNum);
                Presenter.getInstance(this).postPaoBuSimple(NetApi.BIND_CARD_PERSON_INFO, params, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        try {
                            JSONObject jsonObj = new JSONObject(s);
                            // TODO: 2018/5/18 paobu test data
                            Bundle bundle = getBundle();
                            bundle.putString("personId", personId);
                            bundle.putString("personName", personName);
                            bundle.putString("phoneNum", phoneNum);
                            bundle.putString("cardNum", cardNum);
                            startActivity(IdentityAuth3Activity.class, bundle);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                        if (errorBean != null)
                            PaoToastUtils.showShortToast(IdentityAuth2Activity.this, errorBean.getMessage());
                    }
                });
                break;
        }
    }
}