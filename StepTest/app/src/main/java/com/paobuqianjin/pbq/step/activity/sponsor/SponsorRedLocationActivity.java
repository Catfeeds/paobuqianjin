package com.paobuqianjin.pbq.step.activity.sponsor;

import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
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
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.ChooseListPop;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.BaiduMapInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
    @Bind(R.id.iv_location)
    ImageView ivLocation;

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
    private int pageNum = 0;

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
        initListener();
    }

    private void initListener() {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(poiLister);
        searchCircleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(city)) {
                        pageNum = 0;
                        mPoiSearch.searchInCity(new PoiCitySearchOption().city(city)
                                .keyword(searchCircleText.getText().toString()).pageNum(pageNum));
                    }
                    return true;
                }
                return false;
            }
        });
        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFirstLocation = true;
                Presenter.getInstance(SponsorRedLocationActivity.this).startService(null, LocalBaiduService.class);
            }
        });
    }

    private void init() {
        mBaiduMap = mvForeground.getMap();
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.target_location);
        if (mBaiduMap != null) {
            Presenter.getInstance(this).startService(null, LocalBaiduService.class);
            mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    setPosition(latLng);
                }

                @Override
                public boolean onMapPoiClick(MapPoi mapPoi) {
                    setPosition(mapPoi.getPosition());
                    LocalLog.d(TAG, mapPoi.getName() + "----" + mapPoi.getUid());
                    return false;
                }
            });
        }
    }

    private void setPosition(LatLng latLng) {
        LocalLog.d(TAG, ",latitude:" + latLng.latitude + ";longitude:" + latLng.longitude);
        latitude = latLng.latitude;
        longitude = latLng.longitude;
        mBaiduMap.clear();
        LatLng point = new LatLng(latitude, longitude);
        MarkerOptions options = new MarkerOptions().position(point)
                .icon(bitmap).anchor(0.5f, 1f);
        mBaiduMap.addOverlay(options);
        // 实例化一个地理编码查询对象
        Geocoder geocoder = new Geocoder(SponsorRedLocationActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude,
                    longitude, 1);
            StringBuilder sb = new StringBuilder();
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append(" ");
                }
                sb.append(address.getLocality()).append(" ");
                Log.i("location", "address.getLocality()==" + address.getLocality());//城市名
                sb.append(address.getSubLocality());
                Log.i("location", "address.getLocality()=2=" + address.getSubLocality());//---区名
                Log.i("location", "address.getLocality()=3=" + sb.toString());//---区名
            }
        } catch (IOException e) {
            LocalLog.d("address.getLocality()", e.getMessage());
            e.printStackTrace();
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
    }

    @Override
    public void response(BDLocation location) {
        String city = location.getCity();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LocalLog.d(TAG, "city:" + city + ",latitude:" + latitude + ";longitude:" + longitude);
        if (isFirstLocation) {
            this.city = city;
            this.latitude = latitude;
            this.longitude = longitude;
            mCurrentLat = latitude;
            mCurrentLon = longitude;
            setMarker(latitude, longitude);
            setUserMapCenter(latitude, longitude);
            isFirstLocation = false;
            pageNum = 0;

        }
    }

    private ChooseListPop pop;
    OnGetPoiSearchResultListener poiLister = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            LocalLog.d(TAG, "获得POI结果");
            if (poiResult.getAllPoi() == null || poiResult.getAllPoi().size() == 0) {
                ToastUtils.showShortToast(SponsorRedLocationActivity.this, "暂无搜索结果");
                if (pageNum != 0) {
                    pop.setLoad(false);
                }
                return;
            }
            if (pageNum == 0) {
                if (pop == null) {
                    pop = new ChooseListPop<PoiInfo>(SponsorRedLocationActivity.this, new ChooseListPop.OnListSelectListener() {
                        @Override
                        public void onListSelect(Object info) {
                            SponsorRedLocationActivity.this.city = ((PoiInfo)info).city;
                            SponsorRedLocationActivity.this.latitude = ((PoiInfo)info).location.latitude;
                            SponsorRedLocationActivity.this.longitude = ((PoiInfo)info).location.longitude;
                            setMarker(latitude, longitude);
                            setUserMapCenter(latitude, longitude);
                        }


                        @Override
                        public void onBottom() {
                            if (!TextUtils.isEmpty(city)) {
                                pageNum++;
                                mPoiSearch.searchInCity(new PoiCitySearchOption().city(city)
                                        .keyword(searchCircleText.getText().toString()).pageNum(pageNum));
                            }
                        }
                    });
                }
                pop.setData(poiResult.getAllPoi());
                pop.show(searchCircleText);
            } else {
                pop.setMoreData(poiResult.getAllPoi());
            }
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
        mBaiduMap.clear();
        OverlayOptions options = new MarkerOptions()
                .position(point)
                .icon(bitmap)
                .anchor(0.5f, 1.0f);
        mBaiduMap.addOverlay(options);
    }


    @Override
    public void response(ErrorCode errorCode) {

    }
}
