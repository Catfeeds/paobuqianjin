package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.BaiduMapInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.fragment.task.TargetPeopleFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SponsorRedLocationActivity extends BaseBarActivity implements BaiduMapInterface {
    private final static String TAG = SponsorRedLocationActivity.class.getSimpleName();
    @Bind(R.id.search_icon)
    RelativeLayout searchIcon;
    @Bind(R.id.search_circle_text)
    EditText searchCircleText;
    @Bind(R.id.mv_foreground)
    MapView mvForeground;
    BaiduMap mBaiduMap;
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private boolean isFirstLocation = true;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private BitmapDescriptor bitmap;
    private PoiSearch mPoiSearch;

    private String city;
    private double latitude, longitude;

    @Override
    protected String title() {
        return "位置";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_red_location_fg);
        ButterKnife.bind(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCATION_ACTION);
        registerReceiver(stepLocationReciver, intentFilter);
        Presenter.getInstance(this).attachUiInterface(this);
        init();
    }

    private void init() {
        mBaiduMap = mvForeground.getMap();
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.target_location);
        if (mBaiduMap != null) {
            Presenter.getInstance(this).startService(null, LocalBaiduService.class);
            mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    LocalLog.d(TAG, ",latitude:" + latLng.latitude + ";longitude:" + latLng.longitude);
                    mCurrentLat = latLng.latitude;
                    mCurrentLon = latLng.longitude;
                    mBaiduMap.clear();
                    LatLng point = new LatLng(mCurrentLat, mCurrentLon);
                    MarkerOptions options = new MarkerOptions().position(point)
                            .icon(bitmap).anchor(0.5f, 0.5f);
                    mBaiduMap.addOverlay(options);

                    GeoPoint geoPoint = new GeoPoint(mCurrentLat, mCurrentLon);
                    // 实例化一个地理编码查询对象
                    GeoCoder geoCoder = GeoCoder.newInstance();
                    // 设置反地理编码位置坐标
                    ReverseGeoCodeOption op = new ReverseGeoCodeOption();
                    op.location(latLng);

                    geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

                        @Override
                        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
                            // 获取点击的坐标地址
                            arg0.getAddress();
                            arg0.getAddressDetail();
                            LocalLog.d(TAG, "onGetReverseGeoCodeResult() enter " + arg0.getAddress() + "," + arg0.getAddressDetail());
                        }

                        @Override
                        public void onGetGeoCodeResult(GeoCodeResult arg0) {
                            LocalLog.d(TAG, "onGetReverseGeoCodeResult() enter " + arg0.getAddress() + ",");
                        }
                    });
                    // 发起反地理编码请求(经纬度->地址信息)
                    geoCoder.reverseGeoCode(op);
                }

                @Override
                public boolean onMapPoiClick(MapPoi mapPoi) {
                    LocalLog.d(TAG, mapPoi.getName() + "----" + mapPoi.getUid());
                    return false;
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mvForeground.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mvForeground.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mvForeground != null) {
            mBaiduMap.setMyLocationEnabled(false);
            mvForeground.onDestroy();
            if (mPoiSearch != null) {
                mPoiSearch.destroy();
            }
        }
        mvForeground = null;
        unregisterReceiver(stepLocationReciver);
        Presenter.getInstance(this).dispatchUiInterface(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("city", city);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        setResult(TargetPeopleActivity.RES_POSITION, intent);
        finish();
    }

    @Override
    public void response(String city, double latitude, double longitude) {
        LocalLog.d(TAG, "city:" + city + ",latitude:" + latitude + ";longitude:" + longitude);
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        setMarker(latitude, longitude);
        setUserMapCenter(latitude, longitude);
        if (isFirstLocation) {
            isFirstLocation = false;
            mPoiSearch = PoiSearch.newInstance();
            mPoiSearch.setOnGetPoiSearchResultListener(poiLister);
            mPoiSearch.searchInCity(new PoiCitySearchOption().city(city)
                    .keyword("火车站").pageNum(20));
        }
    }

    OnGetPoiSearchResultListener poiLister = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            LocalLog.d(TAG, "获得POI结果");

            for (int i = 0; i < poiResult.getAllPoi().size(); i++) {
                LocalLog.d(TAG, poiResult.getAllPoi().get(i).name);
                LocalLog.d(TAG, "la = " + poiResult.getAllPoi().get(i).location.latitude);
                LocalLog.d(TAG, "lb = " + poiResult.getAllPoi().get(i).location.longitude);
            }

        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            LocalLog.d(TAG, "获取Place详情页检索结果");
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    private void setUserMapCenter(double la, double lb) {
        LatLng cenpt = new LatLng(la, lb);
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }

    private void setMarker(double la, double lb) {
        LatLng point = new LatLng(la, lb);

        OverlayOptions options = new MarkerOptions()
                .position(point)
                .icon(bitmap)
                .anchor(0.5f, 0.5f);
        mBaiduMap.addOverlay(options);
    }


    @Override
    public void response(ErrorCode errorCode) {

    }
}
