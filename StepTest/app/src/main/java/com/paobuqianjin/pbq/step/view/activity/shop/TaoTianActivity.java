package com.paobuqianjin.pbq.step.view.activity.shop;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.adapter.GridAddPic2Adapter;
import com.paobuqianjin.pbq.step.adapter.GridTextAdapter;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CouponCateListResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.FavorColResponse;
import com.paobuqianjin.pbq.step.data.bean.table.SelectPicBean;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.ShopToolUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.fragment.shop.ShopStyleListFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2018/12/3.
 */

public class TaoTianActivity extends BaseBarActivity {
    private final static String TAG = TaoTianActivity.class.getSimpleName();
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.more)
    ImageView more;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.more_grid_view)
    RongGridView moreGridView;
    @Bind(R.id.part_one_span)
    LinearLayout partOneSpan;
    @Bind(R.id.part_two_span)
    LinearLayout partTwoSpan;
    @Bind(R.id.part_three_span)
    LinearLayout partThreeSpan;
    @Bind(R.id.part_for_span)
    LinearLayout partForSpan;
    @Bind(R.id.part_fiv_span)
    LinearLayout partFivSpan;
    @Bind(R.id.part_six_span)
    LinearLayout partSixSpan;
    @Bind(R.id.title_1)
    TextView title1;
    @Bind(R.id.pic_1)
    ImageView pic1;
    @Bind(R.id.title_2)
    TextView title2;
    @Bind(R.id.pic_2)
    ImageView pic2;
    @Bind(R.id.title_3)
    TextView title3;
    @Bind(R.id.pic_3)
    ImageView pic3;
    @Bind(R.id.title_4)
    TextView title4;
    @Bind(R.id.pic_4)
    ImageView pic4;
    @Bind(R.id.title_5)
    TextView title5;
    @Bind(R.id.pic_5)
    ImageView pic5;
    @Bind(R.id.title_6)
    TextView title6;
    @Bind(R.id.pic_6)
    ImageView pic6;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<>();
    private List<CouponCateListResponse.DataBean> tabData = new ArrayList<>();
    GridTextAdapter adapter;
    private final static int MAX_SIZE = 16;

    @Override
    protected String title() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tao_quan_activity_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        loadBanner();
        getFavorColumn();
        getCouponCateList();
    }

    private void initPartI(int i, final FavorColResponse.DataBean dataBean) {
        if (i == 3) {
            partOneSpan.setVisibility(View.VISIBLE);
            title1.setText(dataBean.getFavorites_title());
            Presenter.getInstance(this).getPlaceErrorImage(pic1, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
            if (TextUtils.isEmpty(dataBean.getAndroid())) {
                if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                    partOneSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                            startFavorIdActivity(dataBean.getFavorites_id());
                        }
                    });
                }
            } else {
                LocalLog.d(TAG, "打开H5链接");
                partOneSpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(TaoTianActivity.this, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                    }
                });

            }
        } else if (i == 0) {
            partTwoSpan.setVisibility(View.VISIBLE);
            title2.setText(dataBean.getFavorites_title());
            Presenter.getInstance(this).getPlaceErrorImage(pic2, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
            if (TextUtils.isEmpty(dataBean.getAndroid())) {
                if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                    partTwoSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                            startFavorIdActivity(dataBean.getFavorites_id());
                        }
                    });
                }
            } else {
                LocalLog.d(TAG, "打开H5链接");
                partTwoSpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(TaoTianActivity.this, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                    }
                });

            }
        } else if (i == 2) {
            partThreeSpan.setVisibility(View.VISIBLE);
            title3.setText(dataBean.getFavorites_title());
            Presenter.getInstance(this).getPlaceErrorImage(pic3, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
            if (TextUtils.isEmpty(dataBean.getAndroid())) {
                if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                    partThreeSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                            startFavorIdActivity(dataBean.getFavorites_id());
                        }
                    });
                }
            } else {
                LocalLog.d(TAG, "打开H5链接");
                partThreeSpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(TaoTianActivity.this, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                    }
                });

            }
        } else if (i == 3) {
            partForSpan.setVisibility(View.VISIBLE);
            title4.setText(dataBean.getFavorites_title());
            Presenter.getInstance(this).getPlaceErrorImage(pic4, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
            if (TextUtils.isEmpty(dataBean.getAndroid())) {
                if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                    partForSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                            startFavorIdActivity(dataBean.getFavorites_id());
                        }
                    });
                }
            } else {
                LocalLog.d(TAG, "打开H5链接");
                partForSpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(TaoTianActivity.this, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                    }
                });

            }
        } else if (i == 1) {
            partFivSpan.setVisibility(View.VISIBLE);
            title5.setText(dataBean.getFavorites_title());
            Presenter.getInstance(this).getPlaceErrorImage(pic5, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
            if (TextUtils.isEmpty(dataBean.getAndroid())) {
                if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                    partFivSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LocalLog.d(TAG, "favorites_id = " + dataBean.getAndroid());
                            startFavorIdActivity(dataBean.getFavorites_id());
                        }
                    });
                }
            } else {
                LocalLog.d(TAG, "打开H5链接");
                partFivSpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(TaoTianActivity.this, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                    }
                });

            }
        } else if (i == 5) {
            partSixSpan.setVisibility(View.VISIBLE);
            title6.setText(dataBean.getFavorites_title());
            Presenter.getInstance(this).getPlaceErrorImage(pic6, dataBean.getIcon_url(), R.drawable.bitmap_null, R.drawable.bitmap_null);
            if (TextUtils.isEmpty(dataBean.getAndroid())) {
                if (!TextUtils.isEmpty(dataBean.getFavorites_id())) {
                    partSixSpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LocalLog.d(TAG, "favorites_id = " + dataBean.getFavorites_id());
                            startFavorIdActivity(dataBean.getFavorites_id());
                        }
                    });
                }
            } else {
                LocalLog.d(TAG, "打开H5链接");
                partSixSpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(TaoTianActivity.this, SingleWebViewActivity.class).putExtra("url", dataBean.getAndroid()));
                    }
                });
            }
        }
    }

    private void startFavorIdActivity(final String favorId) {
        LocalLog.d(TAG, "favorId = " + favorId);
        Intent intent = new Intent();
        intent.putExtra(getPackageName() + "favor_id", favorId);
        intent.setClass(this, FavorIdActivity.class);
        startActivity(intent);
    }

    /*1表示Android */
    private void getFavorColumn() {
        Map<String, String> param = new HashMap<>();
        param.put("term_id", "1");
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlFavorColumnH5, param, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    FavorColResponse colResponse = new Gson().fromJson(s, FavorColResponse.class);
                    if (colResponse.getData() != null) {
                        int size = colResponse.getData().size();
                        LocalLog.d(TAG, "parts  size = " + size);
                        for (int i = 0; i < size; i++) {
                            if (i == 5)
                                break;
                            initPartI(i, colResponse.getData().get(i));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    PaoToastUtils.showLongToast(TaoTianActivity.this, "开小差了，请稍后再试");
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(TaoTianActivity.this, errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(TaoTianActivity.this, "开小差了，请稍后再试");
                }
            }
        });
    }

    private void initGo() {
        moreGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridTextAdapter(this, MAX_SIZE);
        moreGridView.setAdapter(adapter);
        moreGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (view instanceof TextView) {
                        view.setBackgroundColor(ContextCompat.getColor(TaoTianActivity.this, R.color.red0));
                    }
                    tablayout.getTabAt(position).select();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        adapter.setDatas(tabData);
    }

    private void getCouponCateList() {
        Presenter.getInstance(this).postPaoBuSimple(NetApi.urlCouponCateStyleS, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    CouponCateListResponse couponCateListResponse = new Gson().fromJson(s, CouponCateListResponse.class);
                    if (couponCateListResponse.getData() != null) {
                        int size = couponCateListResponse.getData().size();
                        tabData = couponCateListResponse.getData();
                        if (size == 0) {
                            return;
                        }
                        for (int i = 0; i < size; i++) {
                            ShopStyleListFragment shopStyleListFragment = new ShopStyleListFragment();
                            shopStyleListFragment.setStyle(tabData.get(i));
                            if (!shopStyleListFragment.isAdded()) {
                                fragments.add(shopStyleListFragment);
                                strings.add(tabData.get(i).getCate_name());
                            }
                            viewPager.setAdapter(new TabAdapter(TaoTianActivity.this,
                                    getSupportFragmentManager(), fragments, strings.toArray()));
                            tablayout.setupWithViewPager(viewPager);
                        }
                        initGo();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(TaoTianActivity.this, errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(TaoTianActivity.this, "开小差了，请稍后再试");
                }
            }
        });
    }

    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=red_voucher" + Presenter.getInstance(this).getLocationStrFormat();
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                    final ArrayList<AdObject> adList = new ArrayList<>();
                    if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                        int size = adresponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            if (adresponse.getData().get(i).getImgs() != null
                                    && adresponse.getData().get(i).getImgs().size() > 0) {
                                int imgSize = adresponse.getData().get(i).getImgs().size();
                                for (int j = 0; j < imgSize; j++) {
                                    AdObject adObject = new AdObject();
                                    adObject.setRid(Integer.parseInt(adresponse.getData().get(i).getRid()));
                                    adObject.setImg_url(adresponse.getData().get(i).getImgs().get(j).getImg_url());
                                    adObject.setTarget_url(adresponse.getData().get(i).getTarget_url());
                                    adList.add(adObject);
                                }
                            }
                        }
                    }
                    banner.setImageLoader(new BannerImageLoader())
                            .setImages(adList)
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                            .setBannerAnimation(Transformer.Default)
                            .isAutoPlay(true)
                            .setDelayTime(2000)
                            .setIndicatorGravity(BannerConfig.CENTER)
                            .setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    if (adList.get(position).getRid() == 0) {
                                        LocalLog.d(TAG, "复制微信号");
                                        ClipboardManager cmb = (ClipboardManager) TaoTianActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(TaoTianActivity.this, "微信号复制成功");
                                    }
                                    String targetUrl = adList.get(position).getTarget_url();
                                    String result = ShopToolUtil.taoBaoString(targetUrl);
                                    if (!TextUtils.isEmpty(result)) {
                                        if (result.startsWith(ShopToolUtil.TaoBaoSchema)
                                                && Utils.checkPackage(getApplicationContext(), ShopToolUtil.TaoBao)) {
                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        } else {
                                            startActivity(new Intent(TaoTianActivity.this, SingleWebViewActivity.class).putExtra("url", targetUrl));
                                        }
                                    }
                                }
                            })
                            .start();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "获取失败!");
            }
        });
    }

    @OnClick(R.id.more)
    public void onClick() {
        moreGridView.setVisibility(View.VISIBLE);
    }
}
