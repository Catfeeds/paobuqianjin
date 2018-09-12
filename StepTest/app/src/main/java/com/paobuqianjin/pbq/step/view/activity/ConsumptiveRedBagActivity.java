package com.paobuqianjin.pbq.step.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.activity.base.BannerImageLoader;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PagenationBean;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopSendedRedBagResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.Constants;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.owner.ConsumptiveRedBagListAdapter;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.DefineLoadMoreView;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
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
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
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

public class ConsumptiveRedBagActivity extends BaseBarActivity implements SwipeMenuRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener, TencentLocationListener, TencentMap.OnMapClickListener, TencentMap.OnMarkerClickListener, BaseBarActivity.ToolBarListener {

    private static final String TAG = ConsumptiveRedBagActivity.class.getSimpleName();
    @Bind(R.id.rv_coupon)
    SwipeMenuRecyclerView rvCoupon;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.linear_empty)
    LinearLayout linearEmpty;
    @Bind(R.id.mapview)
    MapView mapview;
    private int currentPage = 1;
    private double[] location;
    private List<ShopSendedRedBagResponse.ShopSendedRedBagBean> listData = new ArrayList<>();
    private ConsumptiveRedBagListAdapter adapter;
    private TencentLocationManager locationManager;
    private boolean isFirstLocation = true;
    private double[] nowLocation = {0, 0};
    private String city;
    private String req;//定位类型
    private String currentAddrName;
    List<Marker> listMark = new ArrayList<>();
    private PopupWindow popOpWindowRedButtonHori;
    Banner banner;
    private ArrayList<AdObject> adList;

    @Override
    protected String title() {
        return getString(R.string.get_consumptive_red_bag);
    }

    @Override
    public Object right() {
        return "";
    }

    @Override
    public void clickRight() {

    }

    @Override
    public void clickLeft() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumptive_red_bag);
        ButterKnife.bind(this);
        initMapView(savedInstanceState);
        location = Presenter.getInstance(this).getLocation();

        adapter = new ConsumptiveRedBagListAdapter(this, listData);
        rvCoupon.setLayoutManager(new LinearLayoutManager(this));
//        rvCoupon.setAutoLoadMore(true);
//        rvCoupon.useDefaultLoadMore();
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(this);
        loadMoreView.setCustomEmptyView(linearEmpty);
        rvCoupon.addFooterView(loadMoreView); // 添加为Footer。
        rvCoupon.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        rvCoupon.setLoadMoreListener(this);

        rvCoupon.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, final int position) {
                String bId = listData.get(position).getBusinessid();
                if (!TextUtils.isEmpty(bId)) {
                    LocalLog.d(TAG, "businessid =" + bId);
                    Intent intent = new Intent();
                    intent.putExtra(getPackageName() + "businessid", Integer.parseInt(bId));
                    intent.setClass(ConsumptiveRedBagActivity.this, SponsorDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
        adapter.setMyCustomClickLis(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (listData.get(position).getStatus() == 0) {
                    go2NetGetConsumptiveRBActivity(listData.get(position));
//                    pullDownConsumptiveRedBag(position);
                }
            }
        });
        refreshLayout.setOnRefreshListener(this);
        rvCoupon.setAdapter(adapter);
        setToolBarListener(this);
        banner = (Banner) findViewById(R.id.banner);
        loadBanner();
        banner.post(new Runnable() {
            @Override
            public void run() {
                try {
                    popRedPkgButton();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=task_list";
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(this).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                    adList = new ArrayList<>();
                    if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                        int size = adresponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            if (adresponse.getData().get(i).getImgs() != null
                                    && adresponse.getData().get(i).getImgs().size() > 0) {
                                int imgSize = adresponse.getData().get(i).getImgs().size();
                                for (int j = 0; j < imgSize; j++) {
                                    AdObject adObject = new AdObject();
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
                                    String targetUrl = adList.get(position).getTarget_url();
                                    if (!TextUtils.isEmpty(targetUrl))
                                        startActivity(new Intent(ConsumptiveRedBagActivity.this, SingleWebViewActivity.class).putExtra("url", targetUrl));
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

    private void initMapView(Bundle savedInstanceState) {
        mapview.onCreate(savedInstanceState);
        //获取TencentMap实例
        TencentMap tencentMap = mapview.getMap();
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
        tencentMap.setOnMapClickListener(this);
        tencentMap.setOnMarkerClickListener(this);
        requestLocation();
    }

    public void popRedPkgButton() {
        LocalLog.d(TAG, "popRedPkgButton() 弹出红包");
        if (popOpWindowRedButtonHori != null && popOpWindowRedButtonHori.isShowing()) {
            return;
        }
        if (popOpWindowRedButtonHori == null) {
            View popCircleOpBarHori = View.inflate(ConsumptiveRedBagActivity.this, R.layout.near_by_red_pop_window, null);
            popOpWindowRedButtonHori = new PopupWindow(popCircleOpBarHori,
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            final Button button = (Button) popCircleOpBarHori.findViewById(R.id.red_pkg_button);
            button.setBackgroundResource(R.drawable.sponsor_pkg);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ConsumptiveRedBagActivity.this, AddConsumptiveRedBagActivity.class);
                    startActivity(intent);
                }
            });
            popOpWindowRedButtonHori.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    LocalLog.d(TAG, "popRedPkgButton dismiss() ");
                }
            });

            popOpWindowRedButtonHori.setBackgroundDrawable(new BitmapDrawable());
            TranslateAnimation animationCircleTypeHori = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                    0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                    1, Animation.RELATIVE_TO_PARENT, 0);
            animationCircleTypeHori.setInterpolator(new AccelerateInterpolator());
            animationCircleTypeHori.setDuration(200);


            popOpWindowRedButtonHori.showAtLocation(getView(R.id.consum_red_bag), Gravity.BOTTOM | Gravity.RIGHT, 0, 400);
            popCircleOpBarHori.startAnimation(animationCircleTypeHori);
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
                if (AndPermission.hasAlwaysDeniedPermission(ConsumptiveRedBagActivity.this, permissions)) {
                    new PermissionSetting(ConsumptiveRedBagActivity.this).showSetting(permissions);
                }
            }
        }).start();
    }

    private void addMark() {
        for (int i = 0; i < listMark.size(); i++) {
            listMark.get(i).remove();
        }
        listMark.clear();
        for (int i = 0; i < 5; i++) {
            double offSetY = getRandomOffSet(0.01);
            double offSetX = getRandomOffSet(0.008);
            Marker marker1 = mapview.getMap().addMarker(new MarkerOptions()
                    .position(new LatLng(nowLocation[0] + offSetY, nowLocation[1] + offSetX))
                    .anchor(0.5f, 0.5f)
                    .snippet(i + "")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_get_consumptive_rb0))
                    .draggable(false));
            listMark.add(marker1);
        }
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

    private double getRandomOffSet(double right) {
        return Math.random() * right * (Math.random() > 0.5 ? 1 : -1);
    }

    @Override
    protected void onResume() {
        mapview.onResume();
        super.onResume();
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
        if (popOpWindowRedButtonHori != null) {
            popOpWindowRedButtonHori.dismiss();
            popOpWindowRedButtonHori = null;
        }
    }

    private void pullDownConsumptiveRedBag(final int position) {
        Map<String, String> params = new HashMap<>();
        params.put("voucherid", listData.get(position).getVoucherid());
        params.put("longitude", listData.get(position).getVoucherid());
        params.put("latitude", listData.get(position).getVoucherid());
        Presenter.getInstance(this).postPaoBuSimple(NetApi.receiveVoucher, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                listData.get(position).setStatus(2);
                adapter.notifyItemChanged(position);
                PaoToastUtils.showShortToast(ConsumptiveRedBagActivity.this, "领取成功");
            }
        });
    }

    private void getPageData(final int page) {
        currentPage = page;
        Map<String, String> params = new HashMap<>();
        params.put("page", currentPage + "");
        params.put("pagesize", Constants.PAGE_SIZE + "");
        params.put("shadow", "1");
        params.put("latitude", location[0] + "");
        params.put("longitude", location[1] + "");

        Presenter.getInstance(this).getPaoBuSimple(NetApi.getVoucherList, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (page == 1) {
                    listData.clear();
                    rvCoupon.loadMoreFinish(false, true);
                    hideLoadingBar();
                }
                ShopSendedRedBagResponse redBagResponse = new Gson().fromJson(s, ShopSendedRedBagResponse.class);
                if (redBagResponse.getData().getData().size() > 0) {
                    listData.addAll(redBagResponse.getData().getData());
                    adapter.notifyDataSetChanged();
                    rvCoupon.loadMoreFinish(false, true);
                    //addMark();
                } else {
                    rvCoupon.loadMoreFinish(false, false);
                }

                PagenationBean pagenationBean = redBagResponse.getData().getPagenation();
//                rvCoupon.loadMoreFinish(redBagResponse.getData().getData().size() < 1, pagenationBean.getPage() < pagenationBean.getTotalPage());
                refreshLayout.setRefreshing(false);
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                hideLoadingBar();
                if (errorBean != null && errorStr.contains("Not Found Data")) {
                    rvCoupon.loadMoreError(0, "暂无数据");
                }
                refreshLayout.setRefreshing(false);

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
    public void onLoadMore() {
        getPageData(currentPage + 1);
    }

    @Override
    public void onRefresh() {
        getPageData(1);
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
            this.city = city;
            if (location.getPoiList() != null && location.getPoiList().size() > 0) {
                this.currentAddrName = location.getPoiList().get(0).getName();
            }
            TencentMap tencentMap = mapview.getMap();
//            tencentMap.setOnMapCameraChangeListener(onMapCameraChangeListener);
//            setPosition(latitude, longitude, true, true);
            tencentMap.zoomToSpan(new LatLng(nowLocation[0] - 0.01, nowLocation[1] - 0.01), new LatLng(nowLocation[0] + 0.01, nowLocation[1] + 0.01));
            tencentMap.setCenter(new LatLng(nowLocation[0], nowLocation[1]));
            addMyLocation();

            showLoadingBar();
            getPageData(1);
        } else {
            PaoToastUtils.showShortToast(this, reason);
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    private void go2NetGetConsumptiveRBActivity(ShopSendedRedBagResponse.ShopSendedRedBagBean bean) {
        Intent intent = new Intent(this, GetConsumptiveRBResultActivity.class);
        intent.putExtra("idStr", bean.getVoucherid());
        intent.putExtra("status", bean.getStatus());
        intent.putExtra("nowLocation", nowLocation);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        startActivity(new Intent(this, ConsumptiveRedBag2Activity.class));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        startActivity(new Intent(this, ConsumptiveRedBag2Activity.class));
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPageData(1);
    }
}
