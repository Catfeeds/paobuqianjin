package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseActivity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/1/2.
 */

public class QrCodeScanActivity extends BaseActivity implements DecoratedBarcodeView.TorchListener {
    private final static String TAG = QrCodeScanActivity.class.getSimpleName();
    @Bind(R.id.btn_switch)
    Button swichLight;
    @Bind(R.id.btn_hint1)
    Button btnHint1;
    @Bind(R.id.btn_hint2)
    Button btnHint2;
    @Bind(R.id.dbv_custom)
    DecoratedBarcodeView dbvCustom;
    private BeepManager beepManager;
    private boolean isLightOn = false;
    private String lastText;
    public static final int REQUEST_CODE = 0x0000c0de; // Only use bottom 16 bits

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            LocalLog.d(TAG, "result = " + result.getText());
            if (result.getText() == null || result.getText().equals(lastText)) {
                // Prevent duplicate scans
                return;
            }
            lastText = result.getText();
            beepManager.playBeepSoundAndVibrate();
            if (!result.getText().startsWith("userid:") && !result.getText().startsWith("circleid:")) {
                Toast.makeText(getApplicationContext(), "请对准正确的二维码", Toast.LENGTH_SHORT).show();
                return;
            } else {
                LocalLog.d(TAG, "返回结果!");
                Intent intent = new Intent();
                intent.putExtra(getPackageName() + "scanresult", result.getText());
                setResult(REQUEST_CODE, intent);
                finish();
            }


        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        dbvCustom.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbvCustom.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return dbvCustom.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_scan_activity_layout);
        ButterKnife.bind(this);

        dbvCustom.setTorchListener(this);

        // 如果没有闪光灯功能，就去掉相关按钮
        if (!hasFlash()) {
            swichLight.setVisibility(View.GONE);
        }

        //重要代码，初始化捕获
        beepManager = new BeepManager(this);
        Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39);
        dbvCustom.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats, null, null));
        dbvCustom.decodeContinuous(callback);
    }

    // torch 手电筒
    @Override
    public void onTorchOn() {
        Toast.makeText(this, "torch on", Toast.LENGTH_LONG).show();
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        Toast.makeText(this, "torch off", Toast.LENGTH_LONG).show();
        isLightOn = false;
    }

    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    // 点击切换闪光灯
    @OnClick(R.id.btn_switch)
    public void swichLight() {
        if (isLightOn) {
            dbvCustom.setTorchOff();
        } else {
            dbvCustom.setTorchOn();
        }
    }
}
