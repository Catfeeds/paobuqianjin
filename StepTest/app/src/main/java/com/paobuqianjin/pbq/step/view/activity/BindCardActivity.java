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
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class BindCardActivity extends BaseBarActivity {

    private static final int REQ_ADD_CARD = 1;
    private static final int MY_SCAN_REQUEST_CODE = 2;
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
            startActivityForResult(intent, 1);
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

    public void onScanPress(View v) {
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "zh-Hans");//使用中文
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, true);//不需要确认activity
//        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);//禁止键盘输入
//        scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_INSTRUCTIONS, "请将银行卡至于此处\n系统将自动扫描识别");
        scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_OVERLAY_LAYOUT_ID, false);

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
                etCardNumber.setText(scanResult.getFormattedCardNumber());
                LocalLog.d(TAG, resultDisplayStr);
                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            }else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
        } else if (requestCode == REQ_ADD_CARD && resultCode == IdentityAuth3Activity.RES_SUC) {
            finish();
        }
    }
}
