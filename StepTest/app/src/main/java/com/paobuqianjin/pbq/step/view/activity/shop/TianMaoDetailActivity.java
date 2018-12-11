package com.paobuqianjin.pbq.step.view.activity.shop;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.GoodDetailAdapter;
import com.paobuqianjin.pbq.step.customview.CircularImageView;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.GoodDetailResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.ImageViewPagerAdapter;
import com.paobuqianjin.pbq.step.view.base.view.PaoImageSpan;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/11/30.
 */

public class TianMaoDetailActivity extends BaseBarActivity {
    private final static String TAG = TianMaoDetailActivity.class.getSimpleName();
    @Bind(R.id.sponsor_images)
    ViewPager sponsorImages;
    @Bind(R.id.current_pic)
    TextView currentPic;
    @Bind(R.id.pic_index)
    RelativeLayout picIndex;
    @Bind(R.id.good_titles)
    TextView goodTitles;
    @Bind(R.id.final_price)
    TextView finalPrice;
    @Bind(R.id.taoquan_tv)
    TextView taoquanTv;
    @Bind(R.id.quan_span)
    FrameLayout quanSpan;
    @Bind(R.id.sale_numbers)
    TextView saleNumbers;
    @Bind(R.id.quan_after_pic)
    TextView quanAfterPic;
    @Bind(R.id.shop_logo)
    CircularImageView shopLogo;
    @Bind(R.id.shop_name)
    TextView shopName;
    @Bind(R.id.good_pic_more)
    RecyclerView goodPicMore;
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    List<View> Mview = new ArrayList<>();
    List<String> urlImage = new ArrayList<>();
    @Bind(R.id.buy_buttone)
    Button buyButtone;
    @Bind(R.id.quan_liner)
    LinearLayout quanLiner;
    private String coupon_info;
    private String coupon_click_url;
    private String click_url;
    private String clickUrl;

    @Override
    protected String title() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail_activity_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        if (intent != null) {
            String num_iid = intent.getStringExtra(getPackageName() + "num_id");
            coupon_info = intent.getStringExtra(getPackageName() + "coupon_info");
            click_url = intent.getStringExtra(getPackageName() + "click_url");
            coupon_click_url = intent.getStringExtra(getPackageName() + "coupon_click_url");
            getGoodDeatil(num_iid);
        }
    }

    private void getGoodDeatil(final String num_iid) {
        if (TextUtils.isEmpty(num_iid)) {
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("num_iid", num_iid);
        param.put("term_id", "1");

        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlGoodsDetail, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    GoodDetailResponse goodDetailResponse = new Gson().fromJson(s, GoodDetailResponse.class);


                    if (goodDetailResponse.getData().getN_tbk_item().getUser_type() == 0) {
                        Drawable drawable = ContextCompat.getDrawable(TianMaoDetailActivity.this, R.drawable.tao_ic);
                        drawable.setBounds(0, 0, 30, 30);
                        PaoImageSpan imageSpan = new PaoImageSpan(drawable);
                        String titleTao = "    " + goodDetailResponse.getData().getN_tbk_item().getTitle();
                        SpannableString showSpanTao = new SpannableString(titleTao);
                        showSpanTao.setSpan(imageSpan, 0, "    ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        goodTitles.setText(showSpanTao);
                        finalPrice.setText("淘宝原价：¥" + goodDetailResponse.getData().getN_tbk_item().getZk_final_price());
                    } else if (goodDetailResponse.getData().getN_tbk_item().getUser_type() == 1) {
                        Drawable drawable = ContextCompat.getDrawable(TianMaoDetailActivity.this, R.drawable.tian_ic);
                        drawable.setBounds(0, 0, 30, 30);
                        PaoImageSpan imageSpan = new PaoImageSpan(drawable);
                        String titleTian = "  " + goodDetailResponse.getData().getN_tbk_item().getTitle();
                        SpannableString showSpanTian = new SpannableString(titleTian);
                        showSpanTian.setSpan(imageSpan, 0, "  ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        goodTitles.setText(showSpanTian);
                        finalPrice.setText("天猫原价：¥" + goodDetailResponse.getData().getN_tbk_item().getZk_final_price());
                    } else {
                        goodTitles.setText(goodDetailResponse.getData().getN_tbk_item().getTitle());
                        finalPrice.setText("原价：¥" + goodDetailResponse.getData().getN_tbk_item().getZk_final_price());
                    }

                    saleNumbers.setText("销量 " + goodDetailResponse.getData().getN_tbk_item().getVolume());
                    if (!TextUtils.isEmpty(coupon_info)) {
                        String[] result = new String[2];
                        if (!TextUtils.isEmpty(coupon_info) && coupon_info.startsWith("满")) {
                            result = coupon_info.split("减");
                        }
                        LocalLog.d(TAG, "result[0]= " + result[0] + ",result[1] =" + result[1]);
                        result = result[1].split("元");
                        LocalLog.d(TAG, "result[0] =" + result[0]);
                        String quanDeStr = "¥" + result[0] + " 券";
                        SpannableString quanDeStrString = new SpannableString(quanDeStr);
                        quanDeStrString.setSpan(new AbsoluteSizeSpan(12, true), 0, ("¥" + result[0]).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        taoquanTv.setText(quanDeStrString);
                        float afterPrice = Float.parseFloat(goodDetailResponse.getData().getN_tbk_item().getZk_final_price())
                                - Float.parseFloat(result[0]);
                        String moneyFormat = String.format(getString(R.string.month_income), afterPrice);
                        SpannableString spannableString = new SpannableString(moneyFormat);
                        spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, "¥".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        quanAfterPic.setText(spannableString);
                        quanLiner.setVisibility(View.VISIBLE);
                    } else {
                        quanSpan.setVisibility(View.GONE);
                        quanLiner.setVisibility(View.GONE);
                    }
                    clickUrl = goodDetailResponse.getData().getN_tbk_item().getItem_url();
                    Presenter.getInstance(TianMaoDetailActivity.this).getPlaceErrorImage(shopLogo, goodDetailResponse.getData().getN_tbk_item().getPict_url(),
                            R.drawable.null_bitmap, R.drawable.null_bitmap);
                    try {
                        String nickName = new JSONObject(s).getJSONObject("data").getJSONObject("n_tbk_item").getString("nick");
                        shopName.setText(nickName);
                    } catch (JSONException e) {
                        shopName.setText(null);
                    }
                    try {
                        GoodDetailResponse.DataBean.NTbkItemBean.SmallImagesBean  smallImagesBean = new Gson().fromJson(s,GoodDetailResponse.DataBean.NTbkItemBean.SmallImagesBean.class);
                        List<String> image = smallImagesBean.getString();
                        if (image != null) {
                            int size = image.size();
                            for (int j = 0; j < size; j++) {
                                urlImage.add(image.get(j));
                                View view = LayoutInflater.from(TianMaoDetailActivity.this)
                                        .inflate(R.layout.red_iamge_view, null);
                                ImageView imageView = (ImageView) view.findViewById(R.id.red_content_img);
                                Presenter.getInstance(TianMaoDetailActivity.this).getPlaceErrorImage(imageView, image.get(j)
                                        , R.drawable.null_bitmap, R.drawable.null_bitmap);
                                Mview.add(view);
                            }
                            if (Mview.size() > 0) {
                                if (Mview.size() == 1) {
                                    picIndex.setVisibility(View.GONE);
                                } else {
                                    picIndex.setVisibility(View.VISIBLE);
                                }
                                currentPic.setText(String.valueOf(1) + "/" + Mview.size());
                                sponsorImages.setAdapter(new ImageViewPagerAdapter(TianMaoDetailActivity.this, Mview));
                                sponsorImages.addOnPageChangeListener(onPageChangeListener);
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TianMaoDetailActivity.this);
                            goodPicMore.setLayoutManager(linearLayoutManager);
                            goodPicMore.setAdapter(new GoodDetailAdapter(TianMaoDetailActivity.this, urlImage));
                            goodPicMore.setNestedScrollingEnabled(false);
                        } else {
                            urlImage.add(goodDetailResponse.getData().getN_tbk_item().getPict_url());
                            View view = LayoutInflater.from(TianMaoDetailActivity.this)
                                    .inflate(R.layout.red_iamge_view, null);
                            ImageView imageView = (ImageView) view.findViewById(R.id.red_content_img);
                            Presenter.getInstance(TianMaoDetailActivity.this).getPlaceErrorImage(imageView, goodDetailResponse.getData().getN_tbk_item().getPict_url()
                                    , R.drawable.null_bitmap, R.drawable.null_bitmap);
                            Mview.add(view);
                            if (Mview.size() > 0) {
                                if (Mview.size() == 1) {
                                    picIndex.setVisibility(View.GONE);
                                } else {
                                    picIndex.setVisibility(View.VISIBLE);
                                }
                                currentPic.setText(String.valueOf(1) + "/" + Mview.size());
                                sponsorImages.setAdapter(new ImageViewPagerAdapter(TianMaoDetailActivity.this, Mview));
                                sponsorImages.addOnPageChangeListener(onPageChangeListener);
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TianMaoDetailActivity.this);
                            goodPicMore.setLayoutManager(linearLayoutManager);
                            goodPicMore.setAdapter(new GoodDetailAdapter(TianMaoDetailActivity.this, urlImage));
                            goodPicMore.setNestedScrollingEnabled(false);
                        }
                    } catch (Exception e) {
                        String image = new JSONObject(s).getJSONObject("data").getJSONObject("n_tbk_item").getJSONObject("small_images").getString("string");
                        image = TextUtils.isEmpty(image) ? goodDetailResponse.getData().getN_tbk_item().getPict_url() : image;
                        urlImage.add(image);
                        View view = LayoutInflater.from(TianMaoDetailActivity.this)
                                .inflate(R.layout.red_iamge_view, null);
                        ImageView imageView = (ImageView) view.findViewById(R.id.red_content_img);
                        Presenter.getInstance(TianMaoDetailActivity.this).getPlaceErrorImage(imageView, image
                                , R.drawable.null_bitmap, R.drawable.null_bitmap);
                        Mview.add(view);
                        if (Mview.size() > 0) {
                            if (Mview.size() == 1) {
                                picIndex.setVisibility(View.GONE);
                            } else {
                                picIndex.setVisibility(View.VISIBLE);
                            }
                            currentPic.setText(String.valueOf(1) + "/" + Mview.size());
                            sponsorImages.setAdapter(new ImageViewPagerAdapter(TianMaoDetailActivity.this, Mview));
                            sponsorImages.addOnPageChangeListener(onPageChangeListener);
                        }

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TianMaoDetailActivity.this);
                        goodPicMore.setLayoutManager(linearLayoutManager);
                        goodPicMore.setAdapter(new GoodDetailAdapter(TianMaoDetailActivity.this, urlImage));
                        goodPicMore.setNestedScrollingEnabled(false);
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(TianMaoDetailActivity.this, errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(TianMaoDetailActivity.this, "开小差了，请稍后再试");
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

    @OnClick(R.id.buy_buttone)
    public void onClick() {
        if (!TextUtils.isEmpty(coupon_click_url)) {
            clickUrl = coupon_click_url;
        } else if (!TextUtils.isEmpty(click_url)) {
            clickUrl = click_url;
        }
        startActivity(new Intent(this, SingleWebViewActivity.class).putExtra("url", clickUrl));
    }
}
