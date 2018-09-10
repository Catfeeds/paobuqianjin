package com.paobuqianjin.pbq.step.view.fragment.qrcode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.l.okhttppaobu.okhttp.callback.BitmapCallback;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.BitmapUtil;
import com.paobuqianjin.pbq.step.utils.Defaultcontent;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.util.Hashtable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * Created by pbq on 2018/9/7.
 */

public class MyQrCodeFragment extends BaseBarStyleTextViewFragment {
    private final static String TAG = QrCodeFragment.class.getSimpleName();

    private final static String CIRCLE_ID = "id";
    private final static String CIRCLE_NAME = "name";
    private final static String CIRCLE_LOGO = "logo";
    private final static String CIRCLE_RECHARGE = "pay";
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.qr_bg)
    ImageView qrBg;
    @Bind(R.id.linear_qr)
    LinearLayout linearQr;
    @Bind(R.id.qrcode_img)
    ImageView qrcodeImg;
    @Bind(R.id.weixin_circle)
    ImageView weixinCircle;
    @Bind(R.id.weixin)
    ImageView weixin;
    @Bind(R.id.qq)
    ImageView qq;
    @Bind(R.id.share_pan)
    LinearLayout sharePan;
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
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private Bitmap bitmap = null, logoBitmap;

    @Override
    protected int getLayoutResId() {
        return R.layout.my_qr_layout;
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
        qrcodeImg = (ImageView) viewRoot.findViewById(R.id.qrcode_img);
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

        imgWidth = (int) (Utils.getScreenWidthHight(getActivity())[0] * (350 / 750f));
        String codeInfo = "";
        if (id != null && !id.equals("")) {
            codeInfo = NetApi.urlShareCd + id;
            title = "圈子二维码";
        } else {
            userid = Presenter.getInstance(getContext()).getNo();
            codeInfo = NetApi.urlShareIc + userid;
            title = "我的二维码";
        }

        final String info = codeInfo;
        LocalLog.d(TAG, "id = " + id + " name = "
                + name + " logo= " + logo + " pay= " + pay);
        weixinCircle = (ImageView) viewRoot.findViewById(R.id.weixin_circle);
        weixin = (ImageView) viewRoot.findViewById(R.id.weixin);
        qq = (ImageView) viewRoot.findViewById(R.id.qq);
        if (!TextUtils.isEmpty(logo)) {
            if (logo.startsWith("/")) {
                LocalLog.d(TAG, "本地路径");

            } else {
                OkHttpUtils.get().url(logo).build().execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i, Object o) {

                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int i) {
                        if (bitmap != null) {
                            logoBitmap = bitmap;
                            encodeBitmap(info);
                        }
                    }
                });
            }
        }
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getContext());
        dialog = new ProgressDialog(getContext());


        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) qrcodeImg.getLayoutParams();
        params.height = imgWidth;
        params.width = imgWidth;
        qrcodeImg.setLayoutParams(params);

    }


    private void encodeBitmap(String url) {
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

            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, imgWidth, imgWidth, hints);
//            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 175, 175);
            // 使用 ZXing Android Embedded 要写的代码
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            final Bitmap bitmap = barcodeEncoder.createBitmap(result);
            if (logoBitmap == null) {
                qrcodeImg.setImageBitmap(bitmap);
            } else {
                qrcodeImg.setImageBitmap(BitmapUtil.addLogo(bitmap, logoBitmap));
            }

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            return;
        }
        return;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //ButterKnife.unbind(this);
        ButterKnife.unbind(this);
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
                        share_media = SHARE_MEDIA.QQ;
                        imageCircleQr = new UMImage(getContext(), bitmap);
                        new ShareAction(getActivity()).withMedia(imageCircleQr)
                                .setPlatform(share_media)
                                .setCallback(shareListener).share();

                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    public Bitmap getFragmentBitmap(Fragment fragment) {
        LocalLog.d(TAG, "fragment 截图");
        View v = fragment.getView();
        v.buildDrawingCache(false);
        return v.getDrawingCache();
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LocalLog.d(TAG, share_media.toString() + "开始分享");
//            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(getContext(), "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(getContext(), "失败" + throwable.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
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
        bitmap = null;
        UMShareAPI.get(getContext()).release();
    }

    @OnClick({R.id.weixin_circle, R.id.weixin, R.id.qq})
    public void onClick(View view) {
        bitmap = getFragmentBitmap(MyQrCodeFragment.this);
        if (bitmap == null) {
            return;
        }
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

                //shareXiaoChengXu();
                break;
            case R.id.qq:
                LocalLog.d(TAG, "分享到qq");
                requestPermission(Permission.Group.STORAGE);
                break;
        }
    }

    /**
     * 分享小程序
     */
    private void shareXiaoChengXu() {
        UMImage testThumb = new UMImage(getContext(), R.mipmap.app_icon);
        UMMin umMin = new UMMin(Defaultcontent.url);
//兼容低版本的网页链接
        umMin.setThumb(testThumb);
// 小程序消息封面图片
        umMin.setTitle("跑步钱进");
// 小程序消息title
        umMin.setDescription("大道之行，跑步钱进");
// 小程序消息描述
        umMin.setPath("pages/home/home");
//小程序页面路径
        umMin.setUserName("gh_564a5200b41c");
// 小程序原始id,在微信平台查询
        new ShareAction(getActivity())
                .withMedia(umMin)
                .setPlatform(share_media)
                .setCallback(shareListener).share();
    }
}
