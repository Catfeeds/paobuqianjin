package com.paobuqianjin.pbq.step.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;

/**
 * Created by pbq on 2018/1/2.
 */

public class QrCodeScanActivity extends BaseBarActivity implements DecoratedBarcodeView.TorchListener, BaseBarActivity.ToolBarListener {
    private final static String TAG = QrCodeScanActivity.class.getSimpleName();
    private static final int START_ALBUM_CODE = 1;
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
    private Rationale mRationale;
    private PermissionSetting mSetting;
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
            if (!result.getText().startsWith(NetApi.urlShareIc) && !result.getText().startsWith(NetApi.urlShareCd)) {
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
    private String photo_path;
    private ProgressDialog mProgress;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mProgress.dismiss();
            if (msg.what == 1) {
                Intent intent = new Intent();
                intent.putExtra(getPackageName() + "scanresult", (String) msg.obj);
                setResult(REQUEST_CODE, intent);
                finish();
            }
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
    protected String title() {
        return "扫描二维码";
    }

    @Override
    public Object right() {
        return "相册";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_scan_activity_layout);
        ButterKnife.bind(this);
        setToolBarListener(this);

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
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(QrCodeScanActivity.this);

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

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    public void clickRight() {
        /**
         * 获取带二维码的相片进行扫描
         */
        requestPermission(Permission.Group.STORAGE);
    }

/*权限适配*/

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        LocalLog.d(TAG, "获取权限成功");
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        QrCodeScanActivity.this.startActivityForResult(intent, START_ALBUM_CODE);
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(QrCodeScanActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {   //成功且有数据
            switch (requestCode) {
                case START_ALBUM_CODE://不同手机返回的照片路径可能不同
                    try {   //情况一,不是绝对路径的时候,游标获取路径
                        Uri uri = data.getData();
                        if (!TextUtils.isEmpty(uri.getAuthority())) {
                            Cursor cursor = getContentResolver().query(uri,
                                    new String[]{MediaStore.Images.Media.DATA},
                                    null, null, null);
                            if (null == cursor) {
                                Toast.makeText(this, "图片没找到", Toast.LENGTH_SHORT)
                                        .show();
                                return;
                            }
                            cursor.moveToFirst();
                            photo_path = cursor.getString(cursor
                                    .getColumnIndex(MediaStore.Images.Media.DATA));
                            cursor.close();
                        } else {//情况2,绝对路径直接获取
                            photo_path = data.getData().getPath();
                        }
                        LocalLog.e(TAG, "###############" + photo_path);
                        mProgress = new ProgressDialog(QrCodeScanActivity.this);
                        //一些progressbar
                        mProgress.setMessage("正在扫描...");
                        mProgress.setCancelable(false);
                        mProgress.show();

                        /**重点,开启线程,解析图片返回code*/
                        new Thread(new Runnable() {
                            /**重点方法下面详细分析其实和我上面分析的差不多*/
                            @Override
                            public void run() {
                                String result = QRCodeDecoder.syncDecodeQRCode(photo_path);
                                if (result != null) {
                                    Message m = mHandler.obtainMessage();
                                    m.what = 1;
                                    m.obj = result;

                                    mHandler.sendMessage(m);
                                } else {
                                    Message m = mHandler.obtainMessage();
                                    m.what = 2;
                                    m.obj = "识别失败";
                                    mHandler.sendMessage(m);
                                }
                            }


                        }).start();
                    } catch (Exception e) {
                        Toast.makeText(QrCodeScanActivity.this, "解析错误！",
                                Toast.LENGTH_LONG).show();
                    }

                    break;
                default:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
