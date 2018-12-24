package com.paobuqianjin.pbq.step.view.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorSearchPositionActivity;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AroundRedBagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.utils.ShopToolUtil;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
import com.paobuqianjin.pbq.step.view.fragment.home.HomeFragment;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.tencent.mapsdk.raster.model.BitmapDescriptor;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.tencent.tencentmap.mapsdk.map.UiSettings;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsumptiveRedBag2Activity extends BaseBarActivity implements TencentLocationListener, TencentMap.OnMarkerClickListener {
    private static final String TAG = ConsumptiveRedBag2Activity.class.getSimpleName();
    private final static String SPOSNOR_ACTION = "com.paobuqianjin.person.SPONSOR_ACTION";
    private final static String ROUND_ACTION = "com.paobuqianjin.pbq.ROUND_PKG.ACTION";
    private final static String PKG_ACTION = "com.paobuqianjin.person.PKG_ACTION";
    private final static String SEND_ACTION = "com.paobuqianin.pbq.step.SEND";//发红包
    private final static String NEAR_RED_RULE = "com.paobuqianjin.pbq.step.NEAR_RED_RULE";
    @Bind(R.id.mapview)
    MapView mapview;
    @Bind(R.id.iv_location)
    ImageView ivLocation;
    @Bind(R.id.tv_cutdown_time)
    TextView tvCutdownTime;
    @Bind(R.id.rl_search)
    RelativeLayout rlSearch;
    @Bind(R.id.linear_history)
    LinearLayout linearHistory;
    @Bind(R.id.iv_send_red_bag)
    TextView ivSendRedBag;
    @Bind(R.id.red_rule)
    LinearLayout redRule;
    private int currentPage = 1;
    //发完红包之后立即刷新一次
    boolean isRefreshOne = false;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData = new ArrayList<>();
    private List<AroundRedBagResponse.AroundRedBagBean> listAroundRedBagBean = new ArrayList<>();
    private TencentLocationManager locationManager;
    private boolean isFirstLocation = true;
    /**
     *
     */
    private double[] currentLocation = {0, 0};
    private double[] nowLocation = {0, 0};
    private double[] cameraLocation = {0, 0};
    private String city;
    private String req;//定位类型
    private String currentAddrName;
    private TencentMap tencentMap;
    private List<Marker> consumptiveRBMarkerList = new ArrayList<>();
    private List<Marker> aourndRBMarkerList = new ArrayList<>();
    private ShopSendedRedBagResponse.LatComparator cLat;
    private ShopSendedRedBagResponse.LngComparator cLng;

    private CountDownTimer countDownTimer;
    ImageView openRedPkgView;
    private View popRedPkgView;
    private PopupWindow popupRedPkgWindow;
    private View vipView;
    private PopupWindow vipPopWnd;
    private TranslateAnimation animationCircleType;
    private boolean isNoConsumptive;
    Banner banner;
    private ArrayList<AdObject> adList;
    private boolean canRev = true;
    private final static int REQUEST_AROUND = 101;
    private final static int REQUEST_VIP = 102;
    private final static int AD_RED = 103;
    String vip_message = "";
    private boolean isVip;
    private String current_rec_id = "";
    private ErrorBean errorBean = new ErrorBean();

    @Override
    protected String title() {
        isNoConsumptive = getIntent().getBooleanExtra("isNoConsumptive", false);
        if (isNoConsumptive) {
            return "精准红包";
        } else {
            return getString(R.string.red_bag_map);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumptive_red_bag2);
        ButterKnife.bind(this);
        banner = (Banner) findViewById(R.id.banner);
        isNoConsumptive = getIntent().getBooleanExtra("isNoConsumptive", false);
        if (isNoConsumptive) {
            banner.setVisibility(View.VISIBLE);
            rlSearch.setVisibility(View.GONE);
        } else {
            linearHistory.setVisibility(View.GONE);
            ivSendRedBag.setVisibility(View.GONE);
        }
        initMapView(savedInstanceState);
        currentLocation = Presenter.getInstance(this).getLocation();
        loadBanner();
    }

    private void getVipStatus() {
        Presenter.getInstance(ConsumptiveRedBag2Activity.this).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(ConsumptiveRedBag2Activity.this), null, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
                    isVip = true;
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

        });
    }

    private static class ErrorBean {
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getErr() {
            return err;
        }

        public void setErr(int err) {
            this.err = err;
        }

        private int err;
    }

    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=red_map" + Presenter.getInstance(this).getLocationStrFormat();
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(ConsumptiveRedBag2Activity.this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
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
                                        ClipboardManager cmb = (ClipboardManager) ConsumptiveRedBag2Activity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                                        ClipData textClipData = ClipData.newPlainText("Label", getString(R.string.wx_code));
                                        cmb.setPrimaryClip(textClipData);
                                        LocalLog.d(TAG, "  msg = " + cmb.getText());
                                        PaoToastUtils.showLongToast(ConsumptiveRedBag2Activity.this, "微信号复制成功");
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
                                            startActivity(new Intent(ConsumptiveRedBag2Activity.this, SingleWebViewActivity.class).putExtra("url", targetUrl));
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

    private void initMapView(Bundle savedInstanceState) {
        mapview.onCreate(savedInstanceState);
        //获取TencentMap实例
        tencentMap = mapview.getMap();
//设置卫星底图
//        tencentMap.setSatelliteEnabled(true);
//设置实时路况开启
//        tencentMap.setTrafficEnabled(true);
//设置地图中心点
        tencentMap.setCenter(new LatLng(Presenter.getInstance(this).getLocation()[1], Presenter.getInstance(this).getLocation()[0]));
//设置缩放级别
        tencentMap.setZoom(11);
//        tencentMap.zoomToSpan(Presenter.getInstance(this).getLocation()[1], Presenter.getInstance(this).getLocation()[0]);
        //获取UiSettings实例
        UiSettings uiSettings = mapview.getUiSettings();
//设置logo到屏幕底部中心
        //uiSettings.setLogoPosition(UiSettings.LOGO_POSITION_CENTER_BOTTOM);
//设置比例尺到屏幕右下角
        uiSettings.setScaleViewPosition(UiSettings.SCALEVIEW_POSITION_RIGHT_BOTTOM);
//启用缩放手势(更多的手势控制请参考开发手册)
        uiSettings.setZoomGesturesEnabled(true);

//        addRedBagView();
        tencentMap.setOnMarkerClickListener(this);
        showLoadingBar();
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
        setDefaultLocation();
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
                if (AndPermission.hasAlwaysDeniedPermission(ConsumptiveRedBag2Activity.this, permissions)) {
                    new PermissionSetting(ConsumptiveRedBag2Activity.this).showSetting(permissions);
                }
            }
        }).start();
    }

    private BitmapDescriptor mapIcon(AroundRedBagResponse.AroundRedBagBean bagBean) {
        if (bagBean != null) {
            if (bagBean.getUserid().equals(String.valueOf(Presenter.getInstance(this).getId()))) {
                if (bagBean.getStatus() == 1) {
                    return BitmapDescriptorFactory.fromResource(R.mipmap.ic_around_red_self_recved);
                } else {
                    return BitmapDescriptorFactory.fromResource(R.mipmap.ic_around_red_self);
                }
            } else {
                if (bagBean.getStatus() == 1) {
                    return BitmapDescriptorFactory.fromResource(R.mipmap.ic_ardoun_red_recved);
                } else {
                    return BitmapDescriptorFactory.fromResource(R.mipmap.ic_around_red_bag);
                }
            }

        } else {
            return BitmapDescriptorFactory.fromResource(R.mipmap.ic_around_red_bag);
        }
    }

    //只更新界面
    private void followCameraRed(double latitude, double longitude) {
        if (aourndRBMarkerList.size() == 0) {
            return;
        }
        int size = aourndRBMarkerList.size();
        for (int i = 0; i < size; i++) {
            aourndRBMarkerList.get(i).remove();
        }
        aourndRBMarkerList.clear();
        for (int i = 0; i < listAroundRedBagBean.size(); i++) {
            final AroundRedBagResponse.AroundRedBagBean redBagBean = listAroundRedBagBean.get(i);
            double offSetY = getRandomOffSet(0.21);
            double offSetX = getRandomOffSet(0.16);
            Marker markerRedBag = tencentMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude + offSetY, longitude + offSetX))
                    .anchor(0.5f, 0.5f)
                    .snippet("around:" + i)
                    .icon(mapIcon(redBagBean))
                    .draggable(false));
            aourndRBMarkerList.add(markerRedBag);
        }
    }

    private void addAroundRedBagMark() {
        int size = aourndRBMarkerList.size();
        for (int i = 0; i < size; i++) {
            aourndRBMarkerList.get(i).remove();
        }
        aourndRBMarkerList.clear();
        for (int i = 0; i < listAroundRedBagBean.size(); i++) {
            final AroundRedBagResponse.AroundRedBagBean redBagBean = listAroundRedBagBean.get(i);
            double offSetY = getRandomOffSet(0.02);
            double offSetX = getRandomOffSet(0.01);
            Marker markerRedBag = tencentMap.addMarker(new MarkerOptions()
                    .position(new LatLng(currentLocation[0] + offSetY, currentLocation[1] + offSetX))
                    .anchor(0.5f, 0.5f)
                    .snippet("around:" + i)
                    .icon(mapIcon(redBagBean))
                    .draggable(false));
            aourndRBMarkerList.add(markerRedBag);
        }
        //tencentMap.setZoom(12);
    }

    private double getRandomOffSet(double right) {
        return Math.random() * right * (Math.random() > 0.5 ? 1 : -1);
    }


    private void addMyLocation() {
        Marker marker = mapview.getMap().addMarker(new MarkerOptions()
                .position(new LatLng(nowLocation[0], nowLocation[1]))
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .defaultMarker())
                .draggable(false));
        if (!TextUtils.isEmpty(currentAddrName)) {
            marker.setTitle("我在 " + currentAddrName + " 附近>");
            marker.showInfoWindow();// 设置默认显示一个infoWindow
        }
    }

    /**
     * 添加现金红包地图标记
     *
     * @param isAddNowCurrentText
     */
    private void addConsumptiveRedBagMark(boolean isAddNowCurrentText) {
        for (int i = 0; i < consumptiveRBMarkerList.size(); i++) {
            consumptiveRBMarkerList.get(i).remove();
        }
        for (int i = 0; i < listData.size(); i++) {
            ShopSendedRedBagResponse.ShopSendedRedBagBean redBagBean = listData.get(i);
            Marker markerRedBag = tencentMap.addMarker(new MarkerOptions()
                    .position(new LatLng(redBagBean.getLatitude(), redBagBean.getLongitude()))
                    .anchor(0.5f, 0.5f)
                    .snippet("" + i)
                    .icon(BitmapDescriptorFactory.fromResource(redBagBean.getStatus() == 0 ? R.mipmap.ic_get_consumptive_rb1 : R.mipmap.ic_get_consumptive_rb0))
                    .draggable(false));
            consumptiveRBMarkerList.add(markerRedBag);
        }
        Marker markerCurrent = tencentMap.addMarker(new MarkerOptions()
                .position(new LatLng(currentLocation[0], currentLocation[1]))
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .defaultMarker())
                .draggable(false));
        consumptiveRBMarkerList.add(markerCurrent);
        if (isAddNowCurrentText) {
            if (!TextUtils.isEmpty(currentAddrName)) {
                markerCurrent.setTitle("我在 " + currentAddrName + " 附近>");
                markerCurrent.showInfoWindow();// 设置默认显示一个infoWindow
            }
            ivLocation.setImageResource(R.mipmap.nocurrent);
//            ivLocation.setImageResource(R.mipmap.current_position);
            ivLocation.setTag("0");
            //tencentMap.setZoom(12);
        } else {
            ivLocation.setTag("1");
            ivLocation.setImageResource(R.mipmap.nocurrent);
            //tencentMap.setZoom(12);
        }

       /* if (listData.size() > 0) {
            double offset = 0.01;
            if (cLat == null) cLat = new ShopSendedRedBagResponse.LatComparator();
            if (cLng == null) cLng = new ShopSendedRedBagResponse.LngComparator();
            LatLng leftTopPosition = new LatLng(Collections.min(listData, cLat).getLatitude() - offset, Collections.max(listData, cLng).getLongitude() + offset);
            LatLng rightBottomPosition = new LatLng(Collections.max(listData, cLat).getLatitude() + offset, Collections.min(listData, cLng).getLongitude() - offset);
            tencentMap.zoomToSpan(leftTopPosition, rightBottomPosition);
        }*/
    }

    @Override
    protected void onResume() {
        mapview.onResume();
        super.onResume();
        Object tag = ivLocation.getTag();
        if (tag != null) {
            if (tag.equals("0")) {
                getPageData(1, true);
            } else {
                getPageData(1, false);
            }
        }
        getVipStatus();
        //startCountDown(2 * 60 * 60 * 1000);
    }

    @Override
    protected void onPause() {
        mapview.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mapview.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mapview.onDestroy();
        if (countDownTimer != null) countDownTimer.cancel();
        if (popupRedPkgWindow != null) {
            popupRedPkgWindow = null;
        }
        if (vipPopWnd != null) {
            vipPopWnd = null;
        }
        super.onDestroy();
    }

    /*@desc
    *@function  检查当天是否提示过该提醒
    *@param
    *@return 
    */
    private boolean checkShowedToday(String title, int errorCode) {
        if (errorCode == 0) {
            return false;
        }
        String historyDays = (String) SharedPreferencesUtil.get("around_error" + String.valueOf(errorCode), "");
        String today = DateTimeUtil.getCurrentTime();
        if (historyDays.equals(today)) {
            return true;
        }
        return false;
    }

    public void popVipWindow(String title, int errorCode) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        if (vipPopWnd != null && vipPopWnd.isShowing()) {
            LocalLog.d(TAG, "在显示");
            return;
        }
        vipView = View.inflate(this, R.layout.target_dest_popwindow, null);
        vipPopWnd = new PopupWindow(vipView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        vipPopWnd.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                vipPopWnd = null;
            }
        });
        vipPopWnd.setFocusable(true);
        vipPopWnd.setOutsideTouchable(true);
        vipPopWnd.setBackgroundDrawable(new BitmapDrawable());

        TextView textTile = (TextView) vipView.findViewById(R.id.quit_title);
        TextView textDes = (TextView) vipView.findViewById(R.id.read_des);
        TextView textLeft = (TextView) vipView.findViewById(R.id.read_des_left);
        ImageView centerLine = (ImageView) vipView.findViewById(R.id.center_line);
        LocalLog.d(TAG, "error_code = " + errorCode);
        switch (errorCode) {
            case -1:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("发红包");
                centerLine.setVisibility(View.VISIBLE);
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        LocalLog.d(TAG, "发遍地红包");
                        Intent intentAround = new Intent();
                        intentAround.setAction(SEND_ACTION);
                        intentAround.setClass(ConsumptiveRedBag2Activity.this, AddAroundRedBagActivity.class);
                        startActivityForResult(intentAround, REQUEST_AROUND);
                    }
                });
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 0:
                SpannableString spannableString = new SpannableString(title);
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.color_e4393c)),
                        0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textTile.setText(spannableString);
                textTile.setGravity(Gravity.CENTER);

                textDes.setText("去开通");
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                centerLine.setVisibility(View.VISIBLE);
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        Intent intent = new Intent();
                        intent.setClass(ConsumptiveRedBag2Activity.this, VipActivity.class);
                        startActivityForResult(intent, REQUEST_VIP);
                    }
                });
                break;
            case 2:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("发红包");
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        LocalLog.d(TAG, "发遍地红包");
                        Intent intentAround = new Intent();
                        intentAround.setAction(SEND_ACTION);
                        intentAround.setClass(ConsumptiveRedBag2Activity.this, AddAroundRedBagActivity.class);
                        startActivityForResult(intentAround, REQUEST_AROUND);
                    }
                });
                centerLine.setVisibility(View.VISIBLE);
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 3:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("发红包");
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        LocalLog.d(TAG, "发附近红包");
                        Intent intent = new Intent();
                        intent.setAction(SPOSNOR_ACTION);
                        intent.setClass(ConsumptiveRedBag2Activity.this, TaskReleaseActivity.class);
                        startActivity(intent);
                    }
                });
                centerLine.setVisibility(View.VISIBLE);
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 4:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("发红包");
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        LocalLog.d(TAG, "发专享红包");
                        Intent intent = new Intent();
                        intent.setAction(PKG_ACTION);
                        intent.setClass(ConsumptiveRedBag2Activity.this, TaskReleaseActivity.class);
                        startActivity(intent);
                    }
                });
                centerLine.setVisibility(View.VISIBLE);
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 5:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("去开通");
                if (isVip) {
                    textDes.setText("发红包");
                }
                centerLine.setVisibility(View.VISIBLE);
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        LocalLog.d(TAG, "办理金牌会员");
                        if (isVip) {
                            Intent intent = new Intent();
                            intent.setClass(ConsumptiveRedBag2Activity.this, AddConsumptiveRedBagActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(ConsumptiveRedBag2Activity.this, GoldenSponsoractivity.class);
                            startActivity(intent);
                        }
                    }
                });
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 6:
                centerLine.setVisibility(View.VISIBLE);
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("发红包");
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        LocalLog.d(TAG, "发消费红包");
                        Intent intent = new Intent();
                        intent.setClass(ConsumptiveRedBag2Activity.this, AddConsumptiveRedBagActivity.class);
                        startActivity(intent);
                    }
                });
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 7:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("好的");
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                    }
                });
                break;
            case 8:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("好的");
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                    }
                });
                break;
            default:
                PaoToastUtils.showLongToast(ConsumptiveRedBag2Activity.this, title);
                break;
        }

        textLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vipPopWnd.dismiss();
            }
        });
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        vipPopWnd.showAtLocation(findViewById(R.id.consum_red_bag2), Gravity.CENTER, 0, 0);

        vipView.startAnimation(animationCircleType);
        SharedPreferencesUtil.put("around_error" + String.valueOf(errorCode), DateTimeUtil.getCurrentTime());
    }

    public void popRoundRedPkg(final int position) {
        if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
            LocalLog.d(TAG, "红包在显示");
            return;
        }
        if (!TextUtils.isEmpty(current_rec_id)) {
            PaoToastUtils.showLongToast(this, "网络拥堵，请稍候...！");
            return;
        } else {
            if (listAroundRedBagBean.get(position) != null)
                current_rec_id = listAroundRedBagBean.get(position).getRed_id();
        }
        popRedPkgView = View.inflate(this, R.layout.round_pkg_pop_window, null);
        ImageView cancle = (ImageView) popRedPkgView.findViewById(R.id.cancel_red);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupRedPkgWindow.dismiss();
            }
        });
        openRedPkgView = (ImageView) popRedPkgView.findViewById(R.id.round_open);
        popupRedPkgWindow = new PopupWindow(popRedPkgView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupRedPkgWindow = null;
                current_rec_id = "";
            }
        });
        popupRedPkgWindow.setFocusable(true);
        popupRedPkgWindow.setOutsideTouchable(true);
        popupRedPkgWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        openRedPkgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "领取遍地红包");
                final Rotate3dAnimation animation = new Rotate3dAnimation(0, 359, view.getWidth() / 2f, view.getHeight() / 2f, 30, true);
                animation.setDuration(500);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setFillAfter(true);
                view.setAnimation(animation);
                view.startAnimation(animation);
                if (openRedPkgView == null) {
                    return;
                }
                openRedPkgView.setEnabled(false);
                pullDownAroundRedBag(position);
                openRedPkgView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (openRedPkgView != null && openRedPkgView.getVisibility() == View.VISIBLE
                                && popupRedPkgWindow != null
                                && popupRedPkgWindow.isShowing()) {
                            openRedPkgView.clearAnimation();
                            openRedPkgView.setEnabled(true);
                        }
                    }
                }, 2 * 60 * 1000);

            }
        });
        popupRedPkgWindow.showAtLocation(findViewById(R.id.consum_red_bag2), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);
    }

    private void pullDownAroundRedBag(final int position) {
        if (listAroundRedBagBean.size() < position) return;
        Map<String, String> params = new HashMap<>();
        params.put("redid", listAroundRedBagBean.get(position).getRed_id());
        final String busId = listAroundRedBagBean.get(position).getBusinessid();
        Presenter.getInstance(this).postPaoBuSimple(NetApi.receiveAroundRed, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                current_rec_id = "";
                PaoToastUtils.showShortToast(ConsumptiveRedBag2Activity.this, "领取成功");
                // TODO: hqp 2018/8/16
                String result = "";
                int type = -1;
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String allmoney = jsonObj.getString("amount");
                    type = jsonObj.getInt("type");
                    LocalLog.d(TAG, "type = " + type);
                    float redMoney = Float.parseFloat(allmoney);
                    if (redMoney > 0.0f) {
                        result = allmoney;
                    } else {
                        result = getString(R.string.slow_text);
                    }
                } catch (Exception e) {
                    result = getString(R.string.error_red);
                }
                if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
                    popupRedPkgWindow.dismiss();
                }
                //TODO hqp go to red detail
                if (listAroundRedBagBean.size() < position) return;
                Intent intent = new Intent();
                intent.setClass(ConsumptiveRedBag2Activity.this, RoundRedDetailActivity.class);
                intent.putExtra(getPackageName() + "red_id", listAroundRedBagBean.get(position).getRed_id());
                intent.putExtra(getPackageName() + "red_result", result);
                intent.putExtra(getPackageName() + "type", type);
                startActivity(intent);
                listAroundRedBagBean.remove(position);
                aourndRBMarkerList.get(position).remove();
                getAroundRedBag();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                current_rec_id = "";
                if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
                    popupRedPkgWindow.dismiss();
                }
                String result = "";
                if (errorBean == null) {
                    result = getString(R.string.error_red);
                } else {
                    if (errorBean.getError() == 2) {
                        LocalLog.d(TAG, "领红包已经达到上限!需要开通会员");
                        getAroundRedBag();
                        return;
                    } else {
                        result = getString(R.string.slow_text);
                    }

                }
                Intent intent = new Intent();
                intent.setClass(ConsumptiveRedBag2Activity.this, RoundRedDetailActivity.class);
                intent.putExtra(getPackageName() + "red_id", listAroundRedBagBean.get(position).getRed_id());
                intent.putExtra(getPackageName() + "red_result", result);
                startActivity(intent);
                getAroundRedBag();
            }
        });
    }

    /**
     * @param page
     * @param isAddMark 是否需要添加标记
     */
    private void getPageData(final int page, final boolean isAddMark) {
        if (isNoConsumptive == true) return;
        currentPage = page;
        Map<String, String> params = new HashMap<>();
        params.put("page", currentPage + "");
        params.put("pagesize", "30");
        params.put("latitude", currentLocation[0] + "");
        params.put("longitude", currentLocation[1] + "");
//        params.put("distance", "50000");

        Presenter.getInstance(this).getPaoBuSimple(NetApi.getVoucherList, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                    hideLoadingBar();
                }
                ShopSendedRedBagResponse redBagResponse = new Gson().fromJson(s, ShopSendedRedBagResponse.class);
                if (redBagResponse.getData().getData().size() > 0) {
                    listData.addAll(redBagResponse.getData().getData());
                }
                addConsumptiveRedBagMark(isAddMark);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                hideLoadingBar();
                listData.clear();
                addConsumptiveRedBagMark(isAddMark);
            }
        });
    }

    /*@desc
    *@function 是否可继续领：-1.无可领红包 0.办理个人员 1.不限领取 2.发遍地包 3.发附近包 4.发专享包 5.办理金牌员 6.发消费包 7.不能领取(限制)
    *@param
    *@return
    */
    private void getAroundRedBag() {
        if (!isNoConsumptive) return;
        Map<String, String> param = new HashMap<>();
        param.put("latitude", String.valueOf(Presenter.getInstance(this).getLocation()[0]));
        param.put("longitude", String.valueOf(Presenter.getInstance(this).getLocation()[1]));
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlGetRedpacketMap, param, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                hideLoadingBar();
                AroundRedBagResponse aroundRedBagResponse = new Gson().fromJson(s, AroundRedBagResponse.class);
                switch (aroundRedBagResponse.getData().getIs_receive()) {
                    case 0:
                        /*canRev = false;*/
                        vip_message = aroundRedBagResponse.getData().getMessage();
                        /*popVipWindow(aroundRedBagResponse.getMessage(), aroundRedBagResponse.getData().getIs_receive());*/
                        break;
                    case 1:
                        /*canRev = true;*/
                        LocalLog.d(TAG, "无限制领取");
                        break;
                    case -1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        /*canRev = true;*/
                        if (checkShowedToday(aroundRedBagResponse.getData().getMessage(), aroundRedBagResponse.getData().getIs_receive())) {

                        } else {
                            popVipWindow(aroundRedBagResponse.getData().getMessage(), aroundRedBagResponse.getData().getIs_receive());
                        }
                        break;
                    default:
                        if (checkShowedToday(aroundRedBagResponse.getData().getMessage(), aroundRedBagResponse.getData().getIs_receive())) {

                        } else {
                            popVipWindow(aroundRedBagResponse.getData().getMessage(), aroundRedBagResponse.getData().getIs_receive());
                        }
                        break;
                }

                errorBean.setTitle(aroundRedBagResponse.getData().getMessage());
                errorBean.setErr(aroundRedBagResponse.getData().getIs_receive());
                listAroundRedBagBean.clear();
                listAroundRedBagBean.addAll(aroundRedBagResponse.getData().getRedpacket_list());
               /* if (aroundRedBagResponse.getData().getRemain_time() > 0) {
                    tvCutdownTime.setVisibility(View.VISIBLE);
                    startCountDown(aroundRedBagResponse.getData().getRemain_time() * 1000);
                } else if (aroundRedBagResponse.getData().getRemain_time() < 0) {
                    tvCutdownTime.setVisibility(View.VISIBLE);
                    tvCutdownTime.setText("下一个广告红包\n" +
                            "明天08：00");
                } else {
                    tvCutdownTime.setVisibility(View.GONE);
                }*/
                if (cameraLocation[0] != 0 && !isRefreshOne) {
                    LocalLog.d(TAG, "followCameraRed() enter");
                    followCameraRed(cameraLocation[0], cameraLocation[1]);
                } else {
                    LocalLog.d(TAG, "addAroundRedBagMark() enter");
                    addAroundRedBagMark();
                    if (tencentMap != null) {
                        tencentMap.setOnMapCameraChangeListener(onMapCameraChangeListener);
                    }
                    isRefreshOne = false;
                }
                addMyLocation();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                hideLoadingBar();
                if (errorBean != null) {
                    switch (errorBean.getError()) {
                        /*-1.无可领红包 0.办理个人员 1.不限领取 2.发遍地包 3.发附近包 4.发专享包 5.办理金牌员 6.发消费包 7.不能领取(限制)*/
                        case -1:
                            PaoToastUtils.showLongToast(ConsumptiveRedBag2Activity.this, "小主，一大批红包正在急速赶来的路上，请耐心等待哦。");
                            break;
                        case 0:
                            break;
                    }
                }
            }
        });
    }

    /**
     * 开始定位
     */
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
            this.currentLocation[0] = latitude;
            this.currentLocation[1] = longitude;
            this.nowLocation[0] = latitude;
            this.nowLocation[1] = longitude;
            this.city = city;
            if (location.getPoiList() != null && location.getPoiList().size() > 0) {
                this.currentAddrName = location.getPoiList().get(0).getName();
            }
//            tencentMap.setOnMapCameraChangeListener(onMapCameraChangeListener);
//            tencentMap.zoomToSpan(new LatLng(currentLocation[0] - 0.01, currentLocation[1] - 0.01), new LatLng(currentLocation[0] + 0.01, currentLocation[1] + 0.01));
            tencentMap.setCenter(new LatLng(currentLocation[0], currentLocation[1]));
//            tencentMap.animateTo(new LatLng(currentLocation[0], currentLocation[1]));
            getPageData(1, true);
/*            setPosition(latitude, longitude, true, true);*/
            getAroundRedBag();
        } else {
            //PaoToastUtils.showShortToast(this, reason);
            LocalLog.e(TAG, reason);
        }
    }


    private TencentMap.OnMapCameraChangeListener onMapCameraChangeListener = new TencentMap.OnMapCameraChangeListener() {
        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
            LocalLog.d(TAG, "onCameraChange() " + cameraPosition.getTarget().getLatitude()
                    + "," + cameraPosition.getTarget().getLongitude());
            //当前相机中心位置
            if (cameraLocation[0] == 0 && cameraLocation[1] == 0) {
                cameraLocation[0] = cameraPosition.getTarget().getLatitude();
                cameraLocation[1] = cameraPosition.getTarget().getLongitude();
            } else {
                if (Math.abs(cameraPosition.getTarget().getLatitude() - cameraLocation[0]) < 0.01 &&
                        Math.abs(cameraPosition.getTarget().getLongitude() - cameraLocation[1]) < 0.01) {
                    LocalLog.d(TAG, "不更新红包");
                    return;
                }
                cameraLocation[0] = cameraPosition.getTarget().getLatitude();
                cameraLocation[1] = cameraPosition.getTarget().getLongitude();
            }
            followCameraRed(cameraLocation[0], cameraLocation[1]);
        }

        @Override
        public void onCameraChangeFinish(CameraPosition cameraPosition) {

        }
    };


    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String markerIdStr = marker.getSnippet();
        if (!TextUtils.isEmpty(markerIdStr)) {
            if (markerIdStr.contains("around")) {
                if (tvCutdownTime.getVisibility() == View.GONE) {
                    markerIdStr = markerIdStr.split(":")[1];
                    int position = Integer.parseInt(markerIdStr);
                    if (position < listAroundRedBagBean.size()) {
                        final AroundRedBagResponse.AroundRedBagBean redBagBean = listAroundRedBagBean.get(position);
                        if (redBagBean.getStatus() == 0) {
                            if (canRev) {
                                if (TextUtils.isEmpty(listAroundRedBagBean.get(position).getAd_url())) {
                                    if (errorBean != null && errorBean.getErr() >= 7) {
                                        popVipWindow(errorBean.getTitle(), errorBean.getErr());
                                        return true;
                                    } else {
                                        popRoundRedPkg(position);
                                    }

                                } else {
                                    startActivityForResult(new Intent(ConsumptiveRedBag2Activity.this, SingleWebViewActivity.class)
                                            .putExtra("url", listAroundRedBagBean.get(position).getAd_url())
                                            .putExtra("red_id", listAroundRedBagBean.get(position).getRed_id())
                                            .putExtra("title", listAroundRedBagBean.get(position).getAd_title()), AD_RED);
                                }

                            } else {
                                LocalLog.d(TAG, "不能领取");
                                popVipWindow(vip_message, 0);
                            }
                        } else if (redBagBean.getStatus() == 1) {
                            Intent intent = new Intent();
                            intent.setClass(ConsumptiveRedBag2Activity.this, RoundRedDetailActivity.class);
                            intent.putExtra(getPackageName() + "red_id", listAroundRedBagBean.get(position).getRed_id());
                            startActivity(intent);
                        }
                    }
                } else {
                    if (tvCutdownTime.getText().toString().contains("明天")) {
                        PaoToastUtils.showShortToast(this, "您的手气真好,明天再来试试吧");
                    } else {
                        PaoToastUtils.showShortToast(this, "您的手气真好,等会再来试试吧");
                    }
                }
                return true;
            } else {
                //消费红包
                int position = Integer.parseInt(marker.getSnippet());
                ShopSendedRedBagResponse.ShopSendedRedBagBean bean = listData.get(position);
                LocalLog.d(TAG, bean.getVoucherid() + "");
                go2NetGetConsumptiveRBActivity(bean);
                return true;
            }

        }

        return false;
    }

    private void go2NetGetConsumptiveRBActivity(ShopSendedRedBagResponse.ShopSendedRedBagBean bean) {
        Intent intent = new Intent(this, GetConsumptiveRBResultActivity.class);
        intent.putExtra("idStr", bean.getVoucherid());
        intent.putExtra("status", bean.getStatus());
        intent.putExtra("nowLocation", nowLocation);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 & resultCode == 2) {
            ivLocation.setTag("1");
            setPosition(data.getFloatExtra("lat", (float) currentLocation[0]),
                    data.getFloatExtra("lng", (float) currentLocation[1]));
        }
        if (requestCode == REQUEST_AROUND || requestCode == REQUEST_VIP || requestCode == AD_RED) {
            LocalLog.d(TAG, "刷新红包列表");
            getAroundRedBag();
            isRefreshOne = true;
        }
    }

    private void setPosition(float lat, float lng) {
        currentLocation[0] = lat;
        currentLocation[1] = lng;
        LatLng latLng = new LatLng(lat, lng);
        tencentMap.animateTo(latLng);
        //getPageData(1, false);
    }

    private void startCountDown(long second) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(second, 1000) {
            @Override
            public void onTick(long l) {
                int hour = (int) (l / 1000 / 3600);
                int min = (int) ((l - hour * 3600 * 1000) / 1000 / 60);
                int second = (int) (l / 1000 % 60);

                tvCutdownTime.setText("下一个广告红包\n"
                        + String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", second));
            }

            @Override
            public void onFinish() {
                tvCutdownTime.setVisibility(View.GONE);
                getAroundRedBag();
            }
        };
        countDownTimer.start();
    }

    @OnClick({R.id.search_circle_text, R.id.iv_location, R.id.iv_history, R.id.iv_send_red_bag, R.id.red_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.red_rule:
                LocalLog.d(TAG, "查看红包规则");
                startActivity(AgreementActivity.class, null, false, NEAR_RED_RULE);
                break;
            case R.id.search_circle_text:
                Intent intent = new Intent(this,
                        SponsorSearchPositionActivity.class);
                intent.putExtra("city", city);
                startActivityForResult(intent, 1);
                break;
            case R.id.iv_location:
                isFirstLocation = true;
                startLocation();
                break;
            case R.id.iv_history:
                // TODO: hqp
                Intent hisIntent = new Intent(this, RedHsRecordActivity.class);
                hisIntent.setAction(ROUND_ACTION);
                startActivity(hisIntent);
                break;
            case R.id.iv_send_red_bag:
                Intent intentAround = new Intent();
                intentAround.setClass(ConsumptiveRedBag2Activity.this, AddAroundRedBagActivity.class);
                intentAround.setAction(SEND_ACTION);
                startActivityForResult(intentAround, REQUEST_AROUND);
                break;
        }
    }

    private void setDefaultLocation() {
        LocalLog.d(TAG, "default location");
        String city = "深圳市";
        double latitude = 22.548826;
        double longitude = 113.930819;
        this.currentLocation[0] = latitude;
        this.currentLocation[1] = longitude;
        this.nowLocation[0] = latitude;
        this.nowLocation[1] = longitude;
        this.city = city;
        tencentMap.setCenter(new LatLng(currentLocation[0], currentLocation[1]));
        getPageData(1, true);
        getAroundRedBag();
    }
}
