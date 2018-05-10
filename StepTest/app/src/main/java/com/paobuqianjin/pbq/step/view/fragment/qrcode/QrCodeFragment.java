package com.paobuqianjin.pbq.step.view.fragment.qrcode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.l.okhttppaobu.okhttp.utils.L;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Hashtable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pbq on 2018/1/2.
 */

public class QrCodeFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = QrCodeFragment.class.getSimpleName();
    @Bind(R.id.circle_logo)
    CircleImageView circleLogo;
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

    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.qr_bg)
    ImageView qrBg;
    @Bind(R.id.desc_qr_code)
    TextView descQrCode;
    @Bind(R.id.text_share_text)
    TextView textShareText;
    @Bind(R.id.weixin_circle)
    TextView weixinCircle;
    @Bind(R.id.weixin)
    TextView weixin;
    @Bind(R.id.qq)
    TextView qq;
    private String id;
    private String name;
    private String logo;
    private String pay;
    private String userid;
    private SHARE_MEDIA share_media;
    private ProgressDialog dialog;
    private UMImage imageCircleQr;
    private String title = "";
    private int imgWidth;//二维码宽度

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
        setTitle(title);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        qrcodeImg = (ImageView) viewRoot.findViewById(R.id.qrcode_img);
        descQrCode = (TextView) viewRoot.findViewById(R.id.desc_qr_code);
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra(getContext().getPackageName());
        if (bundle != null) {
            id = bundle.getString(CIRCLE_ID, "");
            name = bundle.getString(CIRCLE_NAME, "");
            logo = bundle.getString(CIRCLE_LOGO, "");
            pay = bundle.getString(CIRCLE_RECHARGE, "");
        } else {
            name = intent.getStringExtra("username");
            logo = intent.getStringExtra("usericon");
            LocalLog.d(TAG, "name = " + name + "logo = " + logo);
        }

        imgWidth = (int) (Utils.getScreenWidthHight(getActivity())[0]*(350/750f));

        String codeInfo = "";
        if (id != null && !id.equals("")) {
            codeInfo = "circleid:" + id;
            title = "圈子二维码";
        } else {
            userid = String.valueOf(Presenter.getInstance(getContext()).getId());
            codeInfo = "userid:" + userid;
            descQrCode.setText("用“跑步钱进”APP扫描二维码关注我");
            title = "我的二维码";
        }

        qrcodeImg.setImageBitmap(encodeBitmap(codeInfo));

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) qrcodeImg.getLayoutParams();
        params.height = imgWidth;
        params.width = imgWidth;
        qrcodeImg.setLayoutParams(params);

        LocalLog.d(TAG, "id = " + id + " name = "
                + name + " logo= " + logo + " pay= " + pay);
        weixinCircle = (TextView) viewRoot.findViewById(R.id.weixin_circle);
        weixin = (TextView) viewRoot.findViewById(R.id.weixin);
        qq = (TextView) viewRoot.findViewById(R.id.qq);
        initTextViewIcon();
        circleLogo = (CircleImageView) viewRoot.findViewById(R.id.circle_logo);
        circleName = (TextView) viewRoot.findViewById(R.id.circle_name);
        circleId = (TextView) viewRoot.findViewById(R.id.circle_id);
        if (logo != null && !logo.equals("")) {
            Presenter.getInstance(getContext()).getImage(circleLogo, logo);
        }
        circleName.setText(name);
        if (id != null && !id.equals("")) {
            circleId.setText("ID:" + id);
        } else {
            circleId.setText("ID:" + userid);
        }

        dialog = new ProgressDialog(getContext());
    }

    //更改图片大小
    private void initTextViewIcon() {
        Drawable circle = getResources().getDrawable(R.drawable.circle_friend);
        Drawable weichat = getResources().getDrawable(R.drawable.wet_char_ico);
        Drawable qq_icon = getResources().getDrawable(R.drawable.qq_ico);


        circle.setBounds(0, 0, 80, 80);
        weixinCircle.setCompoundDrawables(null, circle, null, null);
        weichat.setBounds(0, 0, 80, 80);
        weixin.setCompoundDrawables(null, weichat, null, null);
        qq_icon.setBounds(0, 0, 80, 80);
        qq.setCompoundDrawables(null, qq_icon, null, null);
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
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            // 内容所使用字符集编码
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0);

            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, imgWidth, imgWidth,hints);
//            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 175, 175);
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
        ButterKnife.unbind(this);
    }

    public Bitmap getFragmentBitmap(Fragment fragment) {
        LocalLog.d(TAG, "fragment 截图");
        View v = fragment.getView().findViewById(R.id.desc_qr_span);
        v.buildDrawingCache(false);
        return v.getDrawingCache();
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, share_media.toString() + "开始分享");
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(getContext(), "分享成功", Toast.LENGTH_SHORT).show();
            SocializeUtils.safeCloseDialog(dialog);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(getContext(), "失败" + throwable.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(getContext(), "取消分享", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(getContext()).release();
    }

    @OnClick({R.id.weixin_circle, R.id.weixin, R.id.qq})
    public void onClick(View view) {
        Bitmap bitmap = getFragmentBitmap(QrCodeFragment.this);
        switch (view.getId()) {
            case R.id.weixin_circle:
                LocalLog.d(TAG, "分享到朋友圈");
                share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                imageCircleQr = new UMImage(getContext(), bitmap);
                new ShareAction(getActivity()).withMedia(imageCircleQr)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                break;
            case R.id.weixin:
                LocalLog.d(TAG, "分享到微信");
                share_media = SHARE_MEDIA.WEIXIN;
                imageCircleQr = new UMImage(getContext(), bitmap);
                new ShareAction(getActivity()).withMedia(imageCircleQr)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                break;
            case R.id.qq:
                LocalLog.d(TAG, "分享到qq");
                share_media = SHARE_MEDIA.QQ;
                imageCircleQr = new UMImage(getContext(), bitmap);
                new ShareAction(getActivity()).withMedia(imageCircleQr)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                break;
        }
    }
}
