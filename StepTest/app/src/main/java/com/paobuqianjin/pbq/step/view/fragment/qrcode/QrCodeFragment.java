package com.paobuqianjin.pbq.step.view.fragment.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

/**
 * Created by pbq on 2018/1/2.
 */

public class QrCodeFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = QrCodeFragment.class.getSimpleName();
    @Bind(R.id.circle_logo)
    ImageView circleLogo;
    @Bind(R.id.circle_name)
    TextView circleName;
    @Bind(R.id.circle_id)
    TextView circleId;
    @Bind(R.id.circle_simple_info)
    RelativeLayout circleSimpleInfo;
    @Bind(R.id.qrcode_img)
    ImageView qrcodeImg;
/*    @Bind(R.id.desc_qr_code)
    TextView descQrCode;
    @Bind(R.id.text_share_text)
    TextView textShareText;*/

    @Override
    protected int getLayoutResId() {
        return R.layout.qrcode_make_fg;
    }

    @Override
    protected String title() {
        return "圈子二维码";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        //ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        qrcodeImg = (ImageView) viewRoot.findViewById(R.id.qrcode_img);
        qrcodeImg.setImageBitmap(encodeBitmap("HelloWorld"));
    }

    private Bitmap encodeBitmap(String url) {
        Bitmap bitmap = null;
        BitMatrix result = null;

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
       /* try {
            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 175, 175);
            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        } catch (WriterException e) {
            LocalLog.e(TAG, e.getMessage());
        }*/

        try {
            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 175, 175);
            // 使用 ZXing Android Embedded 要写的代码
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(result);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            return null;
        }
        return bitmap;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //ButterKnife.unbind(this);
    }
}
