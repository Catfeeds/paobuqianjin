package com.paobuqianjin.pbq.step.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.lwkandroid.imagepicker.data.ImageDataModel;
import com.lwkandroid.imagepicker.utils.ImagePickerComUtils;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.BigImageView;
import com.paobuqianjin.pbq.step.customview.ImageViewPager;
import com.paobuqianjin.pbq.step.data.bean.bundle.GoodImageData;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RoundHisResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.LikeUserAdapter;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorContentAdapter;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.CustomEdit;
import com.paobuqianjin.pbq.step.view.base.view.RecyclerItemClickListener;
import com.paobuqianjin.pbq.step.view.emoji.EmotionKeyboard;
import com.paobuqianjin.pbq.step.view.emoji.EmotionLayout;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionExtClickListener;
import com.paobuqianjin.pbq.step.view.emoji.IEmotionSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.paobuqianjin.pbq.step.view.emoji.EmotionViewPagerAdapter.numToHex8;

/**
 * Created by pbq on 2018/8/17.
 */

public class RedInfoActivity extends BaseBarActivity {
    private final static String TAG = RedInfoActivity.class.getSimpleName();
    @Bind(R.id.recv_person)
    TextView recvPerson;
    @Bind(R.id.head_recycler)
    RecyclerView headRecycler;
    @Bind(R.id.sponsor_describe)
    TextView sponsorDescribe;
    @Bind(R.id.sponsor_images)
    ViewPager sponsorImages;
    @Bind(R.id.pic_sample)
    ImageView picSample;
    @Bind(R.id.current_pic)
    TextView currentPic;
    @Bind(R.id.sponsor_name)
    TextView sponsorName;
    @Bind(R.id.tel_text)
    TextView telText;
    @Bind(R.id.sponsors_text)
    TextView sponsors;
    @Bind(R.id.sponsor_pic)
    TextView sponsorPic;
    @Bind(R.id.sponsor_more)
    TextView sponsor_more;
    @Bind(R.id.image_a)
    ImageView imageA;
    @Bind(R.id.image_b)
    ImageView imageB;
    @Bind(R.id.image_c)
    ImageView imageC;
    @Bind(R.id.like_sponsor_icon)
    ImageView likeSponsorIcon;
    @Bind(R.id.like_sponsor_desc)
    TextView likeSponsorDesc;
    @Bind(R.id.like_sponsor_span)
    LinearLayout likeSponsorSpan;
    @Bind(R.id.sponsor_content)
    ImageView sponsorContent;
    @Bind(R.id.sponsor_content_num)
    TextView sponsorContentNum;
    @Bind(R.id.sponsor_content_span)
    LinearLayout sponsorContentSpan;
    @Bind(R.id.sponsor_opreation)
    LinearLayout sponsorOpreation;
    @Bind(R.id.new_content)
    TextView newContent;
    @Bind(R.id.line_content)
    ImageView lineContent;
    @Bind(R.id.content_recycler)
    RecyclerView contentRecycler;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    String red_id = "";
    String result_str = "";
    ArrayList<RoundHisResponse.DataBean.ReceiverListBean> arrayRecList = new ArrayList<>();
    List<View> Mview = new ArrayList<>();
    @Bind(R.id.v_empty)
    View vEmpty;
    @Bind(R.id.edit_expression)
    ImageView editExpression;
    @Bind(R.id.content_text)
    CustomEdit contentText;
    @Bind(R.id.send_content)
    Button sendContent;
    @Bind(R.id.edit_content)
    LinearLayout editContent;
    @Bind(R.id.elEmotion)
    EmotionLayout elEmotion;
    ArrayList<RoundHisResponse.DataBean.CommentListBean> arrayList = new ArrayList<>();
    SponsorContentAdapter sponsorContentAdapter;
    LinearLayoutManager layoutManager;
    boolean is_vote = false;
    @Bind(R.id.address_text)
    TextView addressText;
    @Bind(R.id.list_reds)
    LinearLayout listReds;
    @Bind(R.id.sponsor_pic_linear)
    LinearLayout sponsorPicLinear;
    @Bind(R.id.go_to)
    LinearLayout goTo;
    @Bind(R.id.red_success)
    TextView redSuccess;
    @Bind(R.id.red_result)
    TextView redResult;
    @Bind(R.id.into_wallet)
    TextView intoWallet;
    @Bind(R.id.scroll_info)
    BounceScrollView scrollInfo;
    @Bind(R.id.red_info_layout)
    RelativeLayout redInfoLayout;
    @Bind(R.id.pic_index)
    RelativeLayout picIndex;
    @Bind(R.id.qrcode)
    ImageView qrcode;
    @Bind(R.id.circle_qr)
    RelativeLayout circleQr;
    @Bind(R.id.notify)
    TextView notifyText;
    @Bind(R.id.tick_money)
    TextView tickMoney;
    @Bind(R.id.tick_condition)
    TextView tickCondition;
    @Bind(R.id.tick_time)
    TextView tickTime;
    @Bind(R.id.recv_tick)
    TextView recvTick;
    @Bind(R.id.ticket_s)
    RelativeLayout ticketS;
    private int localVoteNum = 0;
    private int localCommentNum = 0;
    CustomEdit commentEditText;
    private EmotionKeyboard mEmotionKeyboard;
    LinearLayout editStill;
    LinearLayout goMore;
    private final static String SHOW_SPONSOR_PICS_ACTION = "com.paobuqianjin.pbq.step.SHOW_PIC_ACTION";
    ArrayList<SponsorDetailResponse.DataBean.EnvironmentImgsBean> goodsImgsBeans = new ArrayList<>();
    private int currentImage = 0;
    private TranslateAnimation animationCircleType;
    private View popBirthSelectView;
    private PopupWindow popupSelectWindow;
    private int mScreenWidth;
    private int mScreenHeight;
    List<String> urlImage = new ArrayList<>();
    private View selectMapView;
    private PopupWindow selectMapWindow;
    List<String> mapList = new ArrayList<>();

    @Override
    protected String title() {
        return "红包信息";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_info_styletw_activity_layout);
        ButterKnife.bind(this);
    }

    public interface PopBigImageInterface {
        public void popImageView(String url);

        public void popImageView(List<String> images, int index);
    }

    private PopBigImageInterface popBigImageInterface = new PopBigImageInterface() {
        @Override
        public void popImageView(String url) {
            LocalLog.d(TAG, "查看大图");
            popBirthSelectView = View.inflate(RedInfoActivity.this, R.layout.image_big_view, null);
            PhotoView photoView = (PhotoView) popBirthSelectView.findViewById(R.id.photo_view);
            ImageDataModel.getInstance().getDisplayer().display(RedInfoActivity.this, url, photoView, mScreenWidth, mScreenHeight);
            popupSelectWindow = new PopupWindow(popBirthSelectView,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    LocalLog.d(TAG, "popImageVie dismiss() ");
                    popupSelectWindow = null;
                }
            });

            popupSelectWindow.setFocusable(true);
            popupSelectWindow.setOutsideTouchable(true);
            popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new AccelerateInterpolator());
            animationCircleType.setDuration(200);


            popupSelectWindow.showAtLocation(findViewById(R.id.red_info_layout), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }

        public void popImageView(List<String> images, int index) {
            if (images == null) {
                return;
            }
            LocalLog.d(TAG, "查看大图 index = " + index);
            popBirthSelectView = View.inflate(RedInfoActivity.this, R.layout.big_image_view_pager, null);
            ImageViewPager bigImageViewPager = (ImageViewPager) popBirthSelectView.findViewById(R.id.big_image_viewpager);
            List<View> bigImageViews = new ArrayList<>();
            for (String url : images) {
                BigImageView bigImageView = new BigImageView(RedInfoActivity.this);
                bigImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                bigImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ImageDataModel.getInstance().getDisplayer().display(RedInfoActivity.this, url, bigImageView, mScreenWidth, mScreenHeight);
                bigImageViews.add(bigImageView);
            }
            ImageViewPagerAdapter pagerAdapter = new ImageViewPagerAdapter(RedInfoActivity.this, bigImageViews);
            bigImageViewPager.setAdapter(pagerAdapter);
            bigImageViewPager.setCurrentItem(index, false);
            popupSelectWindow = new PopupWindow(popBirthSelectView,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            popupSelectWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    LocalLog.d(TAG, "popImageVie dismiss() ");
                    popupSelectWindow = null;
                }
            });

            popupSelectWindow.setFocusable(true);
            popupSelectWindow.setOutsideTouchable(true);
            popupSelectWindow.setBackgroundDrawable(new BitmapDrawable());

            animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleType.setInterpolator(new AccelerateInterpolator());
            animationCircleType.setDuration(200);


            popupSelectWindow.showAtLocation(findViewById(R.id.red_info_layout), Gravity.CENTER, 0, 0);
            popBirthSelectView.startAnimation(animationCircleType);
        }
    };

    private void encodeBitmap(ImageView imageView, String url, int mgWidth, int imgWidth) {
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

            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 116, 116, hints);
//            result = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 175, 175);
            // 使用 ZXing Android Embedded 要写的代码
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            final Bitmap bitmap = barcodeEncoder.createBitmap(result);
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            return;
        }
        return;
    }

    @Override
    protected void initView() {
        mScreenWidth = ImagePickerComUtils.getScreenWidth(this);
        mScreenHeight = ImagePickerComUtils.getScreenHeight(this);
        headRecycler = (RecyclerView) findViewById(R.id.head_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headRecycler.setLayoutManager(linearLayoutManager);
        headRecycler.addItemDecoration(new LikeUserAdapter.SpaceItemDecoration(30));
        sponsorDescribe = (TextView) findViewById(R.id.sponsor_describe);
        likeSponsorIcon = (ImageView) findViewById(R.id.like_sponsor_icon);
        likeSponsorDesc = (TextView) findViewById(R.id.like_sponsor_desc);
        sponsorContentNum = (TextView) findViewById(R.id.sponsor_content_num);
        likeSponsorSpan = (LinearLayout) findViewById(R.id.like_sponsor_span);
        sponsorName = (TextView) findViewById(R.id.sponsor_name);
        telText = (TextView) findViewById(R.id.tel_text);
        sponsors = (TextView) findViewById(R.id.sponsors_text);
        addressText = (TextView) findViewById(R.id.address_text);
        imageA = (ImageView) findViewById(R.id.image_a);
        imageB = (ImageView) findViewById(R.id.image_b);
        imageC = (ImageView) findViewById(R.id.image_c);
        Intent intent = getIntent();
        if (intent != null) {
            red_id = intent.getStringExtra(getPackageName() + "red_id");
            LocalLog.d(TAG, "red_id =" + red_id);
            if (!TextUtils.isEmpty(red_id)) {
                requestPostRedDetail(red_id);
            }
        }
        contentRecycler = (RecyclerView) findViewById(R.id.content_recycler);
        contentRecycler.setNestedScrollingEnabled(false);
        contentRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        contentRecycler.setLayoutManager(layoutManager);
        sponsorContentAdapter = new SponsorContentAdapter(this, arrayList);
        contentRecycler.setAdapter(sponsorContentAdapter);
        editStill = (LinearLayout) findViewById(R.id.linear_edit);
        goMore = (LinearLayout) findViewById(R.id.go_to);
        initEditView(null, -1, null);
        contentRecycler.addOnItemTouchListener(new RecyclerItemClickListener(RedInfoActivity.this, contentRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemLongClick(View view, int position) {

            }

            @Override
            public void OnItemClick(View view, final int position) {
                if (position < arrayList.size() && position >= 0) {
                    initEditView(String.valueOf(arrayList.get(position).getEid()), arrayList.get(position).getUserid(), arrayList.get(position).getNickname());
                }
            }
        }));

        if (Utils.isHaveBaiduMap()) {
            mapList.add("百度");
        }

        if (Utils.isHaveGaodeMap()) {
            mapList.add("高德");
        }

        if (Utils.isHaveTencentMap()) {
            mapList.add("腾讯");
        }
    }

    private void selectMap(List<String> strings, final String dqAddress, final String gotoAddress,
                           final String gotoLatitude, final String gotoLongitude) {
        selectMapView = View.inflate(this, R.layout.map_source_pop_select, null);
        selectMapWindow = new PopupWindow(selectMapView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        selectMapWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LocalLog.d(TAG, "selectMapWindow onDismiss() enter");
                selectMapWindow = null;
            }
        });

        if (strings.contains("腾讯")) {
            TextView tencent = (TextView) selectMapView.findViewById(R.id.tencent_maps);
            tencent.setVisibility(View.VISIBLE);
            tencent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectMapWindow.dismiss();
                    Utils.openTencentMap(RedInfoActivity.this, null, gotoAddress, gotoLatitude, gotoLongitude);
                }
            });
        }

        if (strings.contains("百度")) {
            TextView baidu = (TextView) selectMapView.findViewById(R.id.baidu_map);
            baidu.setVisibility(View.VISIBLE);
            baidu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectMapWindow.dismiss();
                    Utils.openBaiduMap(RedInfoActivity.this, dqAddress, gotoAddress, String.valueOf(Presenter.getInstance(RedInfoActivity.this).getLocation()[0]),
                            String.valueOf(Presenter.getInstance(RedInfoActivity.this).getLocation()[1]), gotoLatitude, gotoLongitude);
                }
            });
        }
        if (strings.contains("高德")) {
            TextView gaode = (TextView) selectMapView.findViewById(R.id.gaode_map);
            gaode.setVisibility(View.VISIBLE);
            gaode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectMapWindow.dismiss();
                    Utils.openGaoDeMap(RedInfoActivity.this, dqAddress, gotoAddress, gotoLatitude, gotoLongitude);
                }
            });
        }
        selectMapWindow.setFocusable(true);
        selectMapWindow.setOutsideTouchable(true);
        selectMapWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                , 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);


        selectMapWindow.showAtLocation(findViewById(R.id.red_info_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
                , 0, 0);
        selectMapView.startAnimation(animationCircleType);
    }


    private void redRelInfo(final String redId) {
        Intent intent = new Intent();
        intent.setClass(RedInfoActivity.this, RoundRedRelActivity.class);
        intent.putExtra(getPackageName() + "red_id", redId);
        startActivity(intent);
    }

    private void onRedInfo() {
        if (!TextUtils.isEmpty(red_id))
            redRelInfo(red_id);
    }

    ;

    private void requestPostRedDetail(final String redId) {
        Map<String, String> param = new HashMap<>();
        param.put("red_id", redId);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlRedHisDetail, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    final RoundHisResponse roundHisResponse = new Gson().fromJson(s, RoundHisResponse.class);
                    LocalLog.d(TAG, "当前为领红包详情");

                    if (TextUtils.isEmpty(result_str) && Double.parseDouble(roundHisResponse.getData().getIncome_money()) > 0.0d) {
                        String showResult = "￥" + roundHisResponse.getData().getIncome_money() + "元";
                        redSuccess.setText("你已经领取过");
                        redSuccess.setVisibility(View.VISIBLE);
                        SpannableString spannableString = new SpannableString(showResult);
                        spannableString.setSpan(new AbsoluteSizeSpan(14, true), ("￥" + roundHisResponse.getData().getIncome_money()).length(), showResult.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        redResult.setText(spannableString);
                        intoWallet.setVisibility(View.VISIBLE);
                    } else {
                        if (Double.parseDouble(roundHisResponse.getData().getIncome_money()) <= 0.0d) {
                            redSuccess.setText("还未领取过该红包");
                            redSuccess.setVisibility(View.VISIBLE);
                        }
                    }
                    if (!TextUtils.isEmpty(roundHisResponse.getData().getCircleid())) {
                        try {
                            if (Integer.parseInt(roundHisResponse.getData().getCircleid()) >= 1) {
                                circleQr.setVisibility(View.VISIBLE);
                                qrcode.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        try {
                                            Intent intent = new Intent();
                                            intent.setClass(RedInfoActivity.this, CirCleDetailActivity.class);
                                            intent.putExtra(getPackageName() + "circleid", Integer.parseInt(roundHisResponse.getData().getCircleid()));
                                            startActivity(intent);
                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                        }
                                        return false;
                                    }
                                });
                                final String circleUlr = NetApi.urlShareCd + roundHisResponse.getData().getCircleid();
                                encodeBitmap(qrcode, circleUlr, 1, 1);
                                TextView pswTv = (TextView) circleQr.findViewById(R.id.circle_pwd);
                                if (!TextUtils.isEmpty(roundHisResponse.getData().getCircle_pwd())) {
                                    pswTv.setText("社群密码：" + roundHisResponse.getData().getCircle_pwd());
                                    pswTv.setVisibility(View.VISIBLE);
                                } else {
                                    pswTv.setVisibility(View.GONE);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    /*优惠券*/
                    if (roundHisResponse.getData().getVoucherid() > 0) {
                        ticketS.setVisibility(View.VISIBLE);
                        String money = "￥" + roundHisResponse.getData().getVoucher().getMoney() + "店铺优惠券";
                        SpannableString spannableString = new SpannableString(money);
                        spannableString.setSpan(new AbsoluteSizeSpan(28, true), "￥".length(), ("￥" + roundHisResponse.getData().getVoucher().getMoney()).length(),
                                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        spannableString.setSpan(new TypefaceSpan("bold"), "￥".length(), ("￥" + roundHisResponse.getData().getVoucher().getMoney()).length(),
                                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        tickMoney.setText(spannableString);
                        tickCondition.setText("订单满" + roundHisResponse.getData().getVoucher().getCondition() + "元可用");
                        String time = DateTimeUtil.formatDateTime(roundHisResponse.getData().getVoucher().getE_time() * 1000, DateTimeUtil.DF_YYYY_MM_DD);
                        tickTime.setText("有效期至:" + time.replace("-", "."));
                        if (roundHisResponse.getData().getVoucher().getStatus() == 0) {
                            recvTick.setText("立即领取");
                            recvTick.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if ("立即领取".equals(recvTick.getText().toString()))
                                        pullDownConsumptiveRedBag(String.valueOf(roundHisResponse.getData().getVoucher().getVid()));
                                }
                            });
                        } else if (roundHisResponse.getData().getVoucher().getStatus() == 1) {
                            recvTick.setText("已领取");
                        } else if (roundHisResponse.getData().getVoucher().getStatus() == 2) {
                            recvTick.setText("已下架");
                        } else if (roundHisResponse.getData().getVoucher().getStatus() == 3) {
                            recvTick.setText("已过期");
                        } else if (roundHisResponse.getData().getVoucher().getStatus() == 4) {
                            recvTick.setText("已领完");
                        }
                    } else {
                        ticketS.setVisibility(View.GONE);
                    }
                    /*优惠券*/
                    if (roundHisResponse.getData().getIs_zan() == 1) {
                        is_vote = true;
                    } else {
                        is_vote = false;
                    }

                    if (roundHisResponse.getData().getReceiver_list() != null) {
                        int size = roundHisResponse.getData().getReceiver_list().size();
                        for (int i = 0; i < size; i++) {
                            if (i < 5) {
                                arrayRecList.add(roundHisResponse.getData().getReceiver_list().get(i));
                            } else {
                                break;
                            }
                        }
                        if (arrayRecList.size() > 0) {
                            LocalLog.d(TAG, "#########");
                            LikeUserAdapter likeUserAdapter = new LikeUserAdapter(RedInfoActivity.this, arrayRecList);
                            headRecycler.setAdapter(likeUserAdapter);
                        }
                        sponsorDescribe.setText(roundHisResponse.getData().getContent());
                        final String tarUrl = roundHisResponse.getData().getTarget_url();
                        //红包活动图片
                        if (roundHisResponse.getData().getRed_img() != null) {
                            int imgSize = roundHisResponse.getData().getRed_img().size();
                            for (int j = 0; j < imgSize; j++) {
                                urlImage.add(roundHisResponse.getData().getRed_img().get(j).getUrl());
                                View view = LayoutInflater.from(RedInfoActivity.this)
                                        .inflate(R.layout.red_iamge_view, null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.red_content_img);
                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageView, roundHisResponse.getData().getRed_img()
                                        .get(j).getUrl(), R.drawable.null_bitmap, R.drawable.null_bitmap);
                                if (!TextUtils.isEmpty(tarUrl)) {
                                    notifyText.setVisibility(View.VISIBLE);
                                }
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (!TextUtils.isEmpty(tarUrl)) {
                                            startActivity(new Intent(RedInfoActivity.this, SingleWebViewActivity.class).putExtra("url", tarUrl));
                                        } else {
                                            LocalLog.d(TAG, "查看大图 currentImage = " + currentImage);
                                            if (popBigImageInterface != null && urlImage.size() >= 1) {
                                                popBigImageInterface.popImageView(urlImage, currentImage);
                                            }
                                        }
                                    }
                                });
                                Mview.add(view);
                            }
                        }
                        if (Mview.size() > 0) {
                            if (Mview.size() == 1) {
                                picIndex.setVisibility(View.GONE);
                            } else {
                                picIndex.setVisibility(View.VISIBLE);
                            }
                            currentPic.setText(String.valueOf(1) + "/" + Mview.size());
                            sponsorImages.setAdapter(new ImageViewPagerAdapter(RedInfoActivity.this, Mview));
                            sponsorImages.addOnPageChangeListener(onPageChangeListener);
                        }

                        //环境照
                        if (roundHisResponse.getData().getBusiness_img() != null) {
                            final int sizeImag = roundHisResponse.getData().getBusiness_img().size();
                            LocalLog.d(TAG, "size = " + size);
                            if (sizeImag <= 0) {
                                return;
                            }
                            for (int i = 0; i < sizeImag; i++) {
                                SponsorDetailResponse.DataBean.EnvironmentImgsBean data = new SponsorDetailResponse.DataBean.EnvironmentImgsBean();
                                data.setUrl(roundHisResponse.getData().getBusiness_img().get(i).getUrl());
                                goodsImgsBeans.add(data);
                            }

                            sponsor_more.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (sizeImag > 0) {
                                        Intent intent = new Intent();
                                        intent.setAction(SHOW_SPONSOR_PICS_ACTION);
                                        if (goodsImgsBeans.size() <= 0) {
                                            return;
                                        }
                                        GoodImageData goodImageData = new GoodImageData(goodsImgsBeans);
                                        intent.putExtra(getPackageName() + "goods", goodImageData);
                                        intent.setClass(RedInfoActivity.this, SponsorGoodsPicLookActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                            if (sizeImag == 1) {
                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageA, roundHisResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                imageA.setVisibility(View.VISIBLE);
                            } else if (sizeImag == 2) {

                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageA, roundHisResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageB, roundHisResponse.getData().getBusiness_img().get(1).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                imageA.setVisibility(View.VISIBLE);
                                imageB.setVisibility(View.VISIBLE);
                            } else if (sizeImag >= 3) {
                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageA, roundHisResponse.getData().getBusiness_img().get(0).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageB, roundHisResponse.getData().getBusiness_img().get(1).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Presenter.getInstance(RedInfoActivity.this).getPlaceErrorImage(imageC, roundHisResponse.getData().getBusiness_img().get(2).getUrl(),
                                        R.drawable.null_bitmap, R.drawable.null_bitmap);
                                imageA.setVisibility(View.VISIBLE);
                                imageC.setVisibility(View.VISIBLE);
                                imageB.setVisibility(View.VISIBLE);
                            }
                        }
                        //商户信息
                        sponsorName.setText(roundHisResponse.getData().getName());
                        telText.setText("商家电话:" + roundHisResponse.getData().getTel());
                        telText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                intent1.setData(Uri.parse("tel:" + roundHisResponse.getData().getTel()));
                                startActivity(intent1);
                            }
                        });
                        /*String workTimeStr = roundHisResponse.getData().getDo_day();
                        String displayTime = "";
                        if (workTimeStr != null) {
                            String[] workTimeStrList = workTimeStr.split(",");
                            if (workTimeStrList != null) {
                                if (workTimeStrList.length >= 7) {
                                    displayTime = "周一至周日 " + roundHisResponse.getData().getS_do_time() + "-" + roundHisResponse.getData().getE_do_time();
                                } else if (workTimeStrList.length > 0) {
                                    displayTime = workTimeStr + " " + roundHisResponse.getData().getS_do_time() + "-" + roundHisResponse.getData().getE_do_time();
                                }
                            }
                        } else {
                            displayTime = "周一至周日 " + "-" + roundHisResponse.getData().getE_do_time();
                        }*/
                        String scope = "经营范围:";
                        if (!TextUtils.isEmpty(roundHisResponse.getData().getScope())) {
                            scope = scope + roundHisResponse.getData().getScope();

                        }
                        sponsors.setText(scope);
                        String locationDetail = "";
                        if (!TextUtils.isEmpty(roundHisResponse.getData().getAddra())) {
                            locationDetail = roundHisResponse.getData().getAddra();
                        }
                        if (!TextUtils.isEmpty(roundHisResponse.getData().getAddress())) {
                            if (!TextUtils.isEmpty(locationDetail)) {
                                locationDetail += roundHisResponse.getData().getAddress();
                            } else {
                                locationDetail = roundHisResponse.getData().getAddress();
                            }
                        }
                        locationDetail = locationDetail.replace("/", "");
                        addressText.setText("商铺地址:" + locationDetail);
                        addressText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!TextUtils.isEmpty(roundHisResponse.getData().getAddra())) {
                                    if (mapList.size() > 0) {
                                        selectMap(mapList, null, roundHisResponse.getData().getAddra(), roundHisResponse.getData().getLatitude(), roundHisResponse.getData().getLongitude());
                                    }
                                }
                            }
                        });

                        //点赞和评论
                        if (roundHisResponse.getData().getIs_zan() == 0) {
                            likeSponsorIcon.setImageResource(R.drawable.un_like);
                        } else {
                            likeSponsorIcon.setImageResource(R.drawable.like_sponsor);
                        }
                        localVoteNum = roundHisResponse.getData().getZan_count();
                        if (localVoteNum > 0) {
                            likeSponsorDesc.setText(String.valueOf(localVoteNum));
                        } else {
                            likeSponsorDesc.setText("0");
                        }
                        localCommentNum = roundHisResponse.getData().getComment_count();
                        if (localCommentNum > 0) {
                            sponsorContentNum.setText(String.valueOf(roundHisResponse.getData().getComment_count()));
                        } else {
                            sponsorContentNum.setText("0");
                        }
                        //评论列表
                        if (roundHisResponse.getData().getComment_list() == null) {
                            return;
                        }

                        arrayList.addAll(roundHisResponse.getData().getComment_list());
                        sponsorContentAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    /**
     * 领取消费红包
     */
    private void pullDownConsumptiveRedBag(String idStr) {
        Map<String, String> params = new HashMap<>();
        params.put("voucherid", idStr);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.receiveVoucher, params, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                recvTick.setText("已领取");
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorStr.contains("距离")) {

                } else {

                }

            }
        });
    }


    private void initEditView(final String evaluateid, final int userid, final Object object) {
        LocalLog.d(TAG, "initEditView() enter evaluateid " + evaluateid + "name =" + (String) object);
        if (commentEditText == null) {
            commentEditText = (CustomEdit) editStill.findViewById(R.id.content_text);
            ImageView buttonIcon = (ImageView) editStill.findViewById(R.id.edit_expression);
            LinearLayout mLlContent = (LinearLayout) editStill.findViewById(R.id.edit_content);
            EmotionLayout mElEmotion = (EmotionLayout) editStill.findViewById(R.id.elEmotion);
            mEmotionKeyboard = EmotionKeyboard.with(RedInfoActivity.this);
            mEmotionKeyboard.bindToContent(mLlContent);
            mEmotionKeyboard.bindToEmotionButton(buttonIcon);
            mEmotionKeyboard.bindToEditText(commentEditText);
            mEmotionKeyboard.setEmotionLayout(mElEmotion);

            mElEmotion.attachEditText(commentEditText);
            mElEmotion.setEmotionAddVisiable(false);
            mElEmotion.setEmotionSettingVisiable(false);
            mElEmotion.setEmotionExtClickListener(new IEmotionExtClickListener() {
                @Override
                public void onEmotionAddClick(View view) {
                    Toast.makeText(RedInfoActivity.this, "add", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onEmotionSettingClick(View view) {
                    Toast.makeText(RedInfoActivity.this, "setting", Toast.LENGTH_SHORT).show();
                }
            });


            mElEmotion.setEmotionSelectedListener(new IEmotionSelectedListener() {
                @Override
                public void onEmojiSelected(String key) {
                    LocalLog.d(TAG, "onEmojiSelected() " + key);
                }

                @Override
                public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
                    String stickerPath = stickerBitmapPath;
                    Toast.makeText(RedInfoActivity.this, stickerPath, Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (object == null && evaluateid == null) {
            commentEditText.setHint("输入评论");
        } else {
            LocalLog.d(TAG, "回复用户!");
            commentEditText.setHint("回复:" + (String) object);
        }

        Button button = (Button) editStill.findViewById(R.id.send_content);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (userid == Presenter.getInstance(RedInfoActivity.this).getId()) {
                    PaoToastUtils.showLongToast(RedInfoActivity.this, "不能评论自己");
                    return;
                }
                view.setEnabled(false);
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (view != null) {
                            view.setEnabled(true);
                        }
                    }
                }, 2000);
                String content = commentEditText.getText().toString();
                LocalLog.d(TAG, "content = " + content);
                int[] emj = getResources().getIntArray(R.array.emjio_list);
                if (content != null) {
                    for (int i = 0; i < emj.length; i++) {
                        content = content.replace(Utils.getEmojiStringByUnicode(emj[i]), "[0x" + numToHex8(emj[i]) + "]");
                    }
                }

                if (!TextUtils.isEmpty(content)) {
                    saveContent(evaluateid, content, object);
                }
            }
        });

    }

    private void resetCommentEdittext() {
        PaoToastUtils.showShortToast(RedInfoActivity.this, "评论成功");
        commentEditText.setText("");
        hideSoftInput(commentEditText.getWindowToken());
    }

    /*用户评价*/
    private void saveContent(final String evaluateid, final String content, final Object object) {
        LocalLog.d(TAG, "saveContent() enter evaluateid " + evaluateid);
        Map<String, String> param = new HashMap<>();
        if (TextUtils.isEmpty(content)) {
            return;
        } else {
            param.put("content", content);
        }
        if (!TextUtils.isEmpty(evaluateid)) {
            param.put("father", evaluateid);
        }
        param.put("type", "4");
        param.put("red_map_id", red_id);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlSaveContent, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                resetCommentEdittext();
                /*插入评论*/
                LocalLog.d(TAG, "s = " + s);
                localCommentNum++;
                sponsorContentNum.setText(String.valueOf(localCommentNum));
                if (object == null && evaluateid == null) {
                    //插入一层评论
                    RoundHisResponse.DataBean.CommentListBean commentarrayBean = new RoundHisResponse.DataBean.CommentListBean();
                    commentarrayBean.setAvatar(Presenter.getInstance(RedInfoActivity.this).getAvatar(RedInfoActivity.this));
                    commentarrayBean.setNickname(Presenter.getInstance(RedInfoActivity.this).getNickName(RedInfoActivity.this));
                    commentarrayBean.setUserid(Presenter.getInstance(RedInfoActivity.this).getId());
                    commentarrayBean.setContent(content);
                    try {
                        long create_time = DateTimeUtil.currentDateParserLong() / 1000;
                        LocalLog.d(TAG, "create_time = " + create_time);
                        commentarrayBean.setCreate_time(create_time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (arrayList.size() > 0) {
                        arrayList.add(0, commentarrayBean);
                        sponsorContentAdapter.notifyItemRangeInserted(0, 1);
                    } else {
                        arrayList.add(0, commentarrayBean);
                        sponsorContentAdapter.notifyDataSetChanged();
                    }
                } else {
                    //插入二层评论
                    RoundHisResponse.DataBean.CommentListBean commentarrayBean = new RoundHisResponse.DataBean.CommentListBean();
                    commentarrayBean.setAvatar(Presenter.getInstance(RedInfoActivity.this).getAvatar(RedInfoActivity.this));
                    commentarrayBean.setNickname(Presenter.getInstance(RedInfoActivity.this).getNickName(RedInfoActivity.this));
                    commentarrayBean.setContent(content);
                    commentarrayBean.setFather_nickname((String) object);
                    commentarrayBean.setUserid(Presenter.getInstance(RedInfoActivity.this).getId());
                    try {
                        long create_time = DateTimeUtil.currentDateParserLong() / 1000;
                        LocalLog.d(TAG, "create_time = " + create_time);
                        commentarrayBean.setCreate_time(create_time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    arrayList.add(0, commentarrayBean);
                    sponsorContentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(RedInfoActivity.this, errorBean.getMessage());
                }
            }
        });
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            LocalLog.d(TAG, "position = " + position);
            currentPic.setText(String.valueOf(position + 1) + "/" + Mview.size());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    /*点赞*/
    private void voteSponsor(final int status) {
        Map<String, String> param = new HashMap<>();
        param.put("status", String.valueOf(status));
        param.put("type", "4");
        param.put("red_map_id", red_id);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlVoteSponsor, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (status == 1) {
                    LocalLog.d(TAG, "点赞成功!");
                    is_vote = true;
                    likeSponsorIcon.setImageResource(R.drawable.like_sponsor);
                    localVoteNum++;
                } else if (status == 2) {
                    LocalLog.d(TAG, "取消点赞!");
                    is_vote = false;
                    likeSponsorIcon.setImageResource(R.drawable.un_like);
                    localVoteNum--;

                }
                likeSponsorDesc.setText(String.valueOf(localVoteNum));
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    @OnClick({R.id.list_reds, R.id.head_recycler, R.id.like_sponsor_span, R.id.sponsor_content_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.list_reds:
            case R.id.head_recycler:
            case R.id.go_to:
                LocalLog.d(TAG, "详情");
                onRedInfo();
                break;
            case R.id.like_sponsor_span:
                LocalLog.d(TAG, "点赞或者取消点赞");
                likeSponsorSpan.setEnabled(false);
                likeSponsorSpan.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (likeSponsorSpan != null) {
                            likeSponsorSpan.setEnabled(true);
                        }
                    }
                }, 2000);
                if (is_vote) {
                    voteSponsor(2);
                } else {
                    voteSponsor(1);
                }
                break;
            case R.id.sponsor_content_span:
                LocalLog.d(TAG, "评论");
                initEditView(null, -1, null);
                break;
        }
    }
}
