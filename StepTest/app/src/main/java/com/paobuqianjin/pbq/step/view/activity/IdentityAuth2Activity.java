package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
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
        params.put("cardno", cardNum.replaceAll(" ",""));
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

                Bundle bundle = getBundle();
                bundle.putString("personId", personId);
                bundle.putString("personName", personName);
                bundle.putString("phoneNum", phoneNum);
                bundle.putString("cardNum", cardNum);

                Intent intent = new Intent(IdentityAuth2Activity.this, IdentityAuth3Activity.class);
                intent.putExtra(getPackageName(),bundle);
                startActivityForResult(intent, 200);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IdentityAuth3Activity.RES_SUC && requestCode == 200) {
            setResult(IdentityAuth3Activity.RES_SUC);
            finish();
        }
    }
}