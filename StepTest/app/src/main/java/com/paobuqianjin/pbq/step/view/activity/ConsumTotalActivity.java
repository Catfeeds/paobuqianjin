package com.paobuqianjin.pbq.step.view.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorManagerActivity;
import com.paobuqianjin.pbq.step.customview.SlidingTabLayout;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ConSumRedStyleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.ShopToolUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.TabAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.fragment.sponsor.ConsumRedFragment;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pbq on 2018/10/16.
 */

public class ConsumTotalActivity extends BaseBarActivity implements TencentLocationListener {
    private final static String TAG = ConsumTotalActivity.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.time_wait)
    TextView timeWait;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
    @Bind(R.id.tablayout)
    SlidingTabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.linear_history)
    LinearLayout linearHistory;
    @Bind(R.id.iv_send_red_bag)
    TextView ivSendRedBag;
    @Bind(R.id.iv_sponsor)
    ImageView ivSponsor;
    @Bind(R.id.his_span)
    LinearLayout hisSpan;
    @Bind(R.id.red_rule)
    LinearLayout redRule;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<>();
    private List<ConSumRedStyleResponse.DataBean> tabData = new ArrayList<>();
    private final static String TICK_RED_RULE = "com.paobuqianjin.pbq.step.TICK_RED_RULE";
    private String req;//定位类型
    private double[] nowLocation = {0, 0};
    private TencentLocationManager locationManager;
    private boolean isFirstLocation = true;

    @Override
    protected String title() {
        return "消费红包";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consum_activity_layout);
        ButterKnife.bind(this);
        loadBanner();
        requestLocation();
    }

    private void requestLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            requestPermission(permissions);
        } else {
            startLocation();
        }
    }

    private void requestPermission(String... permissions) {
        DefaultRationale mRationale = new DefaultRationale();
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        startLocation();
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(ConsumTotalActivity.this, permissions)) {
                    new PermissionSetting(ConsumTotalActivity.this).showSetting(permissions);
                }
            }
        }).start();
    }

    private void startLocation() {
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_POI);
        request.setAllowGPS(true);
        req = request.toString();
        locationManager = TencentLocationManager.getInstance(this);
        int error = locationManager.requestLocationUpdates(request, this);
        LocalLog.d(TAG, "错误---------" + error);
    }


    private void stopLocation() {
        locationManager.removeUpdates(this);
    }


    private void logMsg(TencentLocation location) {
        StringBuilder sb = new StringBuilder(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\n定位参数 : ");// 定位类型
        sb.append(req);
        sb.append("\nlatitude : ");// 纬度
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");// 经度
        sb.append(location.getLongitude());
        sb.append("\n精度 : ");
        sb.append(location.getAccuracy());
        sb.append("\n来源 : ");// 国家码
        sb.append(location.getProvider());
        sb.append("\n名称 : ");//
        sb.append(location.getName());
        sb.append("\nCountry : ");// 国家名称
        sb.append(location.getCity());
        sb.append("\nprovince:");//省份
        sb.append(location.getProvince());
        sb.append("\ncitycode : ");// 城市编码
        sb.append(location.getCityCode());
        sb.append("\ncity : ");// 城市
        sb.append(location.getCity());
        sb.append("\nDistrict : ");// 区
        sb.append(location.getDistrict());
        sb.append("\nStreet : ");// 街道
        sb.append(location.getStreet());
        sb.append("\naddr : ");// 地址信息
        sb.append(location.getAddress());
        sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
        sb.append(location.getIndoorLocationType());
        sb.append("\nDirection(not all devices have value): ");
        sb.append(location.getDirection());// 方向
        sb.append("\nPoi: ");// POI信息
        if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
            for (int i = 0; i < location.getPoiList().size(); i++) {
                TencentPoi poi = location.getPoiList().get(i);
                sb.append(poi.getName()).append(";");
            }
        }
        if (TencentLocation.GPS_PROVIDER.equals(location.getProvider())) {// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 速度 单位：km/h
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 海拔高度 单位：米
            sb.append("\ngps status : ");
            sb.append(location.getGPSRssi());// *****gps质量判断*****
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");
        } else if (TencentLocation.NETWORK_PROVIDER.equals(location.getProvider())) {// 网络定位结果
            // 运营商信息
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        }
        LocalLog.d(TAG, sb.toString());
    }

    @Override
    public void onLocationChanged(TencentLocation location, int error, String reason) {
        if (error == TencentLocation.ERROR_OK && isFirstLocation) {
            isFirstLocation = false;
            stopLocation();
            logMsg(location);
            String city = location.getCity();
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            this.nowLocation[0] = latitude;
            this.nowLocation[1] = longitude;

        } else {
            PaoToastUtils.showShortToast(this, reason);
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

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
                                        ClipboardManager cmb = (ClipboardManager) ConsumTotalActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(ConsumTotalActivity.this, "微信号复制成功");
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
                                            startActivity(new Intent(ConsumTotalActivity.this, SingleWebViewActivity.class).putExtra("url", targetUrl));
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


    private void getConSumRedStyle() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlConSumStyle, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    ConSumRedStyleResponse conSumRedStyleResponse = new Gson().fromJson(s, ConSumRedStyleResponse.class);
                    int size = conSumRedStyleResponse.getData().size();
                    tabData = conSumRedStyleResponse.getData();
                    if (size == 0) {
                        return;
                    }
                    for (int i = 0; i < size; i++) {
                        ConsumRedFragment fragment = new ConsumRedFragment();
                        fragment.setStyle(tabData.get(i));
                        if (!fragment.isAdded())
                            fragments.add(fragment);
                        strings.add(tabData.get(i).getName());
                    }
                    viewpager.setAdapter(new TabAdapter(ConsumTotalActivity.this, getSupportFragmentManager(), fragments, strings.toArray()));
                    tablayout.setupWithViewPager(viewpager);
                    viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            /*tablayout.redrawIndicator(position, positionOffset);*/

                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                        }
                    });
                    tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            View customView = tab.getCustomView();
                            try {
                                TextView tv = (TextView) customView.findViewById(R.id.tv_tab_title);
                                if (tv != null) {
                                    tv.setTextColor(ContextCompat.getColor(ConsumTotalActivity.this, R.color.color_6c71c4));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {
                            View customView = tab.getCustomView();
                            try {
                                TextView tv = (TextView) customView.findViewById(R.id.tv_tab_title);
                                if (tv != null) {
                                    tv.setTextColor(ContextCompat.getColor(ConsumTotalActivity.this, R.color.color_161727));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                    for (int i = 0; i < tablayout.getTabCount(); i++) {
                        TabLayout.Tab tab = tablayout.getTabAt(i);
                        if (tab != null) {
                            tab.setCustomView(getTabView(i));
                            if (tab.getCustomView() != null) {
                                View tabView = (View) tab.getCustomView().getParent();
                                tabView.setTag(i);
                            }
                        }
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

    @Override
    protected void initView() {
        tablayout = (SlidingTabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        getConSumRedStyle();
    }


    public View getTabView(int position) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab_title);
        ImageView im = (ImageView) view.findViewById(R.id.im_tab);
        if (tabData.size() > 0 && position < tabData.size()) {
            Presenter.getInstance(this).getPlaceErrorImage(im, tabData.get(position).getIcon(), R.drawable.other, R.drawable.other);
            tv.setText(tabData.get(position).getName());
        }
        if (position == 0) {
   /*         view.setGravity(Gravity.RIGHT);
            tv.setPadding(0, 0, 25, 0);*/
            tv.setTextColor(ContextCompat.getColor(this, R.color.color_6c71c4));
        } else if (position == tabData.size() - 1) {
/*            view.setGravity(Gravity.LEFT);
            tv.setPadding(25, 0, 0, 0);*/
        }
        return view;
    }

    @OnClick({R.id.linear_history, R.id.iv_send_red_bag, R.id.iv_sponsor, R.id.red_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.red_rule:
                startActivity(AgreementActivity.class, null, false, TICK_RED_RULE);
                break;
            case R.id.linear_history:
                LocalLog.d(TAG, "查看历史记录");
                startActivity(ConsumHsRedActivity.class, null);
                break;
            case R.id.iv_send_red_bag:
                LocalLog.d(TAG, "发消费红包");
                Intent intent = new Intent(ConsumTotalActivity.this, AddConsumptiveRedBagActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_sponsor:
                LocalLog.d(TAG, "我的店铺");
                Intent intentBuss = new Intent();
                intentBuss.setClass(this, SponsorManagerActivity.class);
                startActivity(intentBuss);
                break;
        }
    }

}
