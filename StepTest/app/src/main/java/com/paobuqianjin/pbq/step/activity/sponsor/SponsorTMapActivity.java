package com.paobuqianjin.pbq.step.activity.sponsor;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.SearchPositionAdapter;
import com.paobuqianjin.pbq.step.customview.ProUtils;
import com.paobuqianjin.pbq.step.data.bean.SelectPoisitonListBean;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.base.activity.BaseBarActivity;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.tencent.lbssearch.TencentSearch;
import com.tencent.lbssearch.httpresponse.BaseObject;
import com.tencent.lbssearch.httpresponse.HttpResponseListener;
import com.tencent.lbssearch.object.Location;
import com.tencent.lbssearch.object.param.Geo2AddressParam;
import com.tencent.lbssearch.object.result.Geo2AddressResultObject;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SponsorTMapActivity extends BaseBarActivity implements TencentLocationListener {

    private final static String TAG = SponsorTMapActivity.class.getSimpleName();
    @Bind(R.id.search_location)
    RelativeLayout searchLocation;
    @Bind(R.id.mv_foreground)
    MapView mapView;
    @Bind(R.id.iv_location)
    ImageView ivLocation;
    @Bind(R.id.lv_show)
    ListView lvShow;

    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private boolean isFirstLocation = true;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private PermissionSetting mSetting;
    private boolean isLocSuc;

    private String city;
    private double latitude, longitude;
    private int pageNum = 0;
    private TencentMap tencentMap;
    private String req;
    private TencentLocationManager locationManager;
    private Marker myLocation;
    private TencentSearch tencentSearch;
    private SearchPositionAdapter<SelectPoisitonListBean> adapter;
    private TencentMap.OnMapCameraChangeListener onMapCameraChangeListener = new TencentMap.OnMapCameraChangeListener() {
        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
          /*  if (!isLocEnd) return;
            double latitude = cameraPosition.getTarget().getLatitude();
            double longitude = cameraPosition.getTarget().getLongitude();
            if (!isLocSuc ||
                    ((float) SponsorTMapActivity.this.latitude == (float) latitude &&
                            (float) SponsorTMapActivity.this.longitude == (float) longitude))
                return;*/
            LocalLog.d(TAG, "onChange" + cameraPosition.toString());
            if (isLocSuc) {
                setMark(new LatLng(cameraPosition.getTarget().getLatitude(),
                        cameraPosition.getTarget().getLongitude()));
            }
        }

        @Override
        public void onCameraChangeFinish(CameraPosition cameraPosition) {
            LocalLog.d(TAG, "onFinish" + (float) latitude + "---" + (float) longitude);
            LocalLog.d(TAG, "onFinish2" + (float) mCurrentLat + "---" + (float) mCurrentLon);
            LocalLog.d(TAG, "onFinish" + cameraPosition.toString());
            if (isLocSuc) {
                latitude = cameraPosition.getTarget().getLatitude();
                longitude = cameraPosition.getTarget().getLongitude();
                if ((float) mCurrentLat == (float) cameraPosition.getTarget().getLatitude()
                        && (float) mCurrentLon == (float) cameraPosition.getTarget().getLongitude()) {
                    return;
                }
                setPosition(latitude, longitude, false, true);
            } else {
                if ((float) mCurrentLat == (float) cameraPosition.getTarget().getLatitude()
                        && (float) mCurrentLon == (float) cameraPosition.getTarget().getLongitude()) {
                    latitude = cameraPosition.getTarget().getLatitude();
                    longitude = cameraPosition.getTarget().getLongitude();
                    isLocSuc = true;
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_tmap);
        ButterKnife.bind(this);
        setToolBarListener(new ToolBarListener() {
            @Override
            public void clickLeft() {
                onBackPressed();
            }

            @Override
            public void clickRight() {
                if (TextUtils.isEmpty(city)) {
                    onBackPressed();
                    return;
                }
                LocalLog.d(TAG, city + "----" + mCurrentLat + "----" + mCurrentLon
                        + "----" + latitude + "---" + longitude);
                Intent intent = getIntent();
                intent.putExtra("city", city);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                setResult(TargetPeopleActivity.RES_POSITION, intent);
                finish();
            }
        });
        mSetting = new PermissionSetting(this);
        init();
    }

    @Override
    public Object right() {
        return "确定";
    }

    @OnClick({R.id.search_location})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_location: {

            }
            break;
        }
    }

    private void init() {
        ViewGroup.LayoutParams params = lvShow.getLayoutParams();
        params.height = (int) (ProUtils.getScreenHeight(this) * 2.0 / 5);
        lvShow.setLayoutParams(params);
        adapter = new SearchPositionAdapter<>(this);
        lvShow.setAdapter(adapter);

        lvShow.setOnItemClickListener(itemClickListener);
        //获取TencentMap实例
        tencentMap = mapView.getMap();
        tencentMap.setZoom(16);
        tencentSearch = new TencentSearch(this);
//设置卫星底图
        tencentMap.setSatelliteEnabled(false);
        //设置实时路况开启
        tencentMap.setTrafficEnabled(false);
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
                if (AndPermission.hasAlwaysDeniedPermission(SponsorTMapActivity.this, permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    private void startLocation() {
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME);
        req = request.toString();
        locationManager = TencentLocationManager.getInstance(SponsorTMapActivity.this);
        int error = locationManager.requestLocationUpdates(request, SponsorTMapActivity.this);
        LocalLog.d(TAG, "错误---------" + error);
    }

    private void stopLocation() {
        locationManager.removeUpdates(this);
    }

    @Override
    protected String title() {
        return "位置";
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        super.onStop();
    }

    private void setMark(LatLng latLng) {
        if (myLocation == null) {
            myLocation = tencentMap.addMarker(new MarkerOptions().
                    position(latLng).
                    icon(BitmapDescriptorFactory.fromResource(R.mipmap.navigation)).
                    anchor(0.5f, 0.5f));
        }
        myLocation.setPosition(latLng);
//        myLocation.setRotation(location.getBearing()); //仅当定位来源于gps有效，或者使用方向传感器

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
            this.city = city;
            tencentMap.setOnMapCameraChangeListener(onMapCameraChangeListener);
            setPosition(latitude, longitude, true, true);
        } else {
            ToastUtils.showShortToast(this, reason);
        }
    }

    private void setPosition(double latitude, double longitude, boolean needCenter, boolean needLoad) {
        mCurrentLat = latitude;
        mCurrentLon = longitude;
        LatLng latLng = new LatLng(latitude, longitude);
        if (needCenter) {
            isLocSuc = false;
            tencentMap.setCenter(latLng);
            tencentMap.setZoom(tencentMap.getZoomLevel());
        }
        setMark(latLng);
        if (needLoad) {
            Location loc = new Location((float) latitude, (float) longitude);
//        Nearby nearBy = new Nearby().point(loc).r(1000);
//        SearchParam.Region region = new SearchParam.Region().poi(location.getCity());
            Geo2AddressParam geo2AddressParam = new Geo2AddressParam().location(loc).get_poi(true);
            tencentSearch.geo2address(geo2AddressParam, listener);
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
    }


    private HttpResponseListener listener = new HttpResponseListener() {

        @Override
        public void onSuccess(int i, BaseObject object) {
            LocalLog.d(TAG, "进来");
            if (object != null) {
                LocalLog.d(TAG, "obj！=null");
                //这里的object是所有检索结果的父类
                //用户需要将其转换为其实际类型以获取检索内容
                //这里将其转换为Address2GeoResultObject
                Geo2AddressResultObject oj =
                        (Geo2AddressResultObject) object;
                if (oj.result != null) {
                    city = oj.result.ad_info.city;
                    StringBuilder sb = new StringBuilder();

                    sb.append("\nname：").append(oj.result.ad_info.name);
                    sb.append("\n地址：").append(oj.result.address);
                    sb.append("\nrecommend：").append(oj.result.formatted_addresses.recommend);
                    sb.append("\npois:");
                    List<SelectPoisitonListBean> list = new ArrayList<>();
                    SelectPoisitonListBean bean = new SelectPoisitonListBean();
                    bean.setName(oj.result.formatted_addresses.recommend)
                            .setLat(oj.result.ad_info.location.lat)
                            .setLon(oj.result.ad_info.location.lng);
                    list.add(bean);
                    for (Geo2AddressResultObject.ReverseAddressResult.Poi poi : oj.result.pois) {
                        sb.append("\n\t").append(poi.title);
                        SelectPoisitonListBean position = new SelectPoisitonListBean();
                        position.setName(poi.title).setAddress(poi.address)
                                .setLat(poi.location.lat).setLon(poi.location.lng);
                        list.add(position);
                    }
                    LocalLog.d(TAG, "location:" + sb);
                    adapter.setData(list, 0);
                }
            }
        }

        @Override
        public void onFailure(int i, String s, Throwable throwable) {
            LocalLog.d(TAG, "失败");
        }
    };

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Object o = adapter.getData().get(i);
            adapter.setSelect(i);
            if (o != null) {
                setPosition(((SelectPoisitonListBean) o).getLat(), ((SelectPoisitonListBean) o).getLon(), true, false);
            }
        }
    };


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
                sb.append(poi.getName() + ";");
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
}
