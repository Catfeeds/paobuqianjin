package com.paobuqianjin.pbq.step.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.sponsor.SponsorSearchPositionActivity;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AroundRedBagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.tencent.tencentmap.mapsdk.map.UiSettings;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.json.JSONException;
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
    @Bind(R.id.mapview)
    MapView mapview;
    @Bind(R.id.iv_location)
    ImageView ivLocation;
    @Bind(R.id.tv_cutdown_time)
    TextView tvCutdownTime;
    private int currentPage = 1;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData = new ArrayList<>();
    private List<AroundRedBagResponse.AroundRedBagBean> listAroundRedBagBean = new ArrayList<>();
    private TencentLocationManager locationManager;
    private boolean isFirstLocation = true;
    /**
     *
     */
    private double[] currentLocation = {0, 0};
    private double[] nowLocation = {0, 0};
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
    private TranslateAnimation animationCircleType;

    @Override
    protected String title() {
        return getString(R.string.red_bag_map);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumptive_red_bag2);
        ButterKnife.bind(this);
        initMapView(savedInstanceState);
        currentLocation = Presenter.getInstance(this).getLocation();
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
        tencentMap.setZoom(14);
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
                if (AndPermission.hasAlwaysDeniedPermission(ConsumptiveRedBag2Activity.this, permissions)) {
                    new PermissionSetting(ConsumptiveRedBag2Activity.this).showSetting(permissions);
                }
            }
        }).start();
    }

    private void addAroundRedBagMark() {
        for (int i = 0; i < aourndRBMarkerList.size(); i++) {
            aourndRBMarkerList.get(i).remove();
        }
        for (int i = 0; i < listAroundRedBagBean.size(); i++) {
            AroundRedBagResponse.AroundRedBagBean redBagBean = listAroundRedBagBean.get(i);
            double offSetY = getRandomOffSet(0.02);
            double offSetX = getRandomOffSet(0.01);
            Marker markerRedBag = tencentMap.addMarker(new MarkerOptions()
                    .position(new LatLng(currentLocation[0] + offSetY, currentLocation[1] + offSetX))
                    .anchor(0.5f, 0.5f)
                    .snippet("around:" + i)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_around_red_bag))
                    .draggable(false));
            aourndRBMarkerList.add(markerRedBag);
        }
        //tencentMap.setZoom(12);
    }

    private double getRandomOffSet(double right) {
        return Math.random() * right * (Math.random() > 0.5 ? 1 : -1);
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
            markerCurrent.setTitle("我在 " + currentAddrName + " 附近>");
            if (!TextUtils.isEmpty(currentAddrName)) {
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
        super.onDestroy();
    }

    public void popRoundRedPkg(final int position) {
        if (position >= listAroundRedBagBean.size()) {
            PaoToastUtils.showLongToast(this, getString(R.string.error_red));
            return;
        }
        if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
            LocalLog.d(TAG, "红包在显示");
            return;
        }

        popRedPkgView = View.inflate(this, R.layout.round_pkg_pop_window, null);
        openRedPkgView = (ImageView) popRedPkgView.findViewById(R.id.round_open);
        popupRedPkgWindow = new PopupWindow(popRedPkgView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupRedPkgWindow = null;
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
        if (position >= listAroundRedBagBean.size()) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        final String redId = listAroundRedBagBean.get(position).getRed_id();
        params.put("redid", redId);
        Presenter.getInstance(this).postPaoBuSimple(NetApi.receiveAroundRed, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showShortToast(ConsumptiveRedBag2Activity.this, "领取成功");
                listAroundRedBagBean.remove(position);
                aourndRBMarkerList.get(position).remove();
                // TODO: hqp 2018/8/16
                getAroundRedBag();
                String result = "";
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String allmoney = jsonObj.getString("amount");
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
                Intent intent = new Intent();
                intent.putExtra(getPackageName() + "red_id", redId);
                intent.putExtra(getPackageName() + "red_result", result);
                intent.setClass(ConsumptiveRedBag2Activity.this, RoundRedDetailActivity.class);
                startActivity(intent);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
                    popupRedPkgWindow.dismiss();
                }
                String result = "";
                if (errorBean == null) {
                    result = getString(R.string.error_red);
                } else {
                    result = getString(R.string.slow_text);
                }
                //TODO hqp go to red detail
                Intent intent = new Intent();
                intent.putExtra(getPackageName() + "red_id", redId);
                intent.putExtra(getPackageName() + "red_result", result);
                intent.setClass(ConsumptiveRedBag2Activity.this, RoundRedDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * @param page
     * @param isAddMark 是否需要添加标记
     */
    private void getPageData(final int page, final boolean isAddMark) {
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


    private void getAroundRedBag() {
        Presenter.getInstance(this).getPaoBuSimple(NetApi.urlGetRedpacketMap, null, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                hideLoadingBar();
                AroundRedBagResponse aroundRedBagResponse = new Gson().fromJson(s, AroundRedBagResponse.class);
                listAroundRedBagBean.clear();
                listAroundRedBagBean.addAll(aroundRedBagResponse.getData().getRedpacket_list());
                if (aroundRedBagResponse.getData().getRemain_time() > 0) {
                    tvCutdownTime.setVisibility(View.VISIBLE);
                    startCountDown(aroundRedBagResponse.getData().getRemain_time() * 1000);
                } else {
                    tvCutdownTime.setVisibility(View.GONE);
                }
                addAroundRedBagMark();
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                hideLoadingBar();
            }
        });
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
            getAroundRedBag();
        } else {
            PaoToastUtils.showShortToast(this, reason);
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String markerIdStr = marker.getSnippet();
        if (!TextUtils.isEmpty(markerIdStr)) {
            if (markerIdStr.contains("around")) {
                markerIdStr = markerIdStr.split(":")[1];
                int position = Integer.parseInt(markerIdStr);
                popRoundRedPkg(position);
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
                long hour = (l / 1000 / 3600);
                long min = ((l - hour * 3600 * 1000) / 1000 / 60);
                long second = (l / 1000 % 60);
                tvCutdownTime.setText("下一个广告红包\n"
                        + hour + ":" + min + ":" + second);
            }

            @Override
            public void onFinish() {
                tvCutdownTime.setVisibility(View.GONE);
                getAroundRedBag();
            }
        };
        countDownTimer.start();
    }

    @OnClick({R.id.search_circle_text, R.id.iv_location, R.id.iv_history, R.id.iv_send_red_bag})
    public void onClick(View view) {
        switch (view.getId()) {
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
                startActivity(hisIntent);
                break;
            case R.id.iv_send_red_bag:
                startActivity(AddAroundRedBagActivity.class, null);
                break;
        }
    }
}
