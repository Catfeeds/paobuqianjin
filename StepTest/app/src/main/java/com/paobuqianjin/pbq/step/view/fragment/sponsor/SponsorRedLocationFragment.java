package com.paobuqianjin.pbq.step.view.fragment.sponsor;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
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
import com.paobuqianjin.pbq.step.view.base.fragment.BaseBarStyleTextViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pbq on 2018/4/22.散发地区选择
 */

public class SponsorRedLocationFragment extends BaseBarStyleTextViewFragment implements BaiduMapInterface {
    private final static String TAG = SponsorRedLocationFragment.class.getSimpleName();
    @Bind(R.id.bar_return_drawable)
    ImageView barReturnDrawable;
    @Bind(R.id.button_return_bar)
    RelativeLayout buttonReturnBar;
    @Bind(R.id.bar_title)
    TextView barTitle;
    @Bind(R.id.bar_tv_right)
    TextView barTvRight;
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

    @Override
    protected String title() {
        return "位置";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.sponsor_red_location_fg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCATION_ACTION);
        getContext().registerReceiver(stepLocationReciver, intentFilter);
        Presenter.getInstance(getContext()).attachUiInterface(this);
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        mvForeground = (MapView) viewRoot.findViewById(R.id.mv_foreground);
        mBaiduMap = mvForeground.getMap();
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.target_location);
        if (mBaiduMap != null) {
            Presenter.getInstance(getContext()).startService(null, LocalBaiduService.class);
            mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    mCurrentLat = latLng.latitude;
                    mCurrentLon = latLng.longitude;

                    mBaiduMap.clear();
                    LatLng point = new LatLng(mCurrentLat, mCurrentLon);
                    MarkerOptions options = new MarkerOptions().position(point)
                            .icon(bitmap).anchor(0.5f, 0.5f);
                    mBaiduMap.addOverlay(options);

                    // 实例化一个地理编码查询对象
                    GeoCoder geoCoder = GeoCoder.newInstance();
                    // 设置反地理编码位置坐标
                    ReverseGeoCodeOption op = new ReverseGeoCodeOption();
                    op.location(latLng);
                    // 发起反地理编码请求(经纬度->地址信息)
                    geoCoder.reverseGeoCode(op);
                    geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

                        @Override
                        public void onGetReverseGeoCodeResult(
                                ReverseGeoCodeResult arg0) {
                            // 获取点击的坐标地址
                            arg0.getAddress();
                            arg0.getAddressDetail();
                            LocalLog.d(TAG, "onGetReverseGeoCodeResult() enter " + arg0.getAddress() + "," + arg0.getAddressDetail());
                        }

                        @Override
                        public void onGetGeoCodeResult(GeoCodeResult arg0) {

                        }
                    });
                }

                @Override
                public boolean onMapPoiClick(MapPoi mapPoi) {
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mvForeground != null) {
            mBaiduMap.setMyLocationEnabled(false);
            mvForeground.onDestroy();
            if (mPoiSearch != null) {
                mPoiSearch.destroy();
            }
        }
        mvForeground = null;
        getContext().unregisterReceiver(stepLocationReciver);
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
    }

    @Override
    public void response(String city, double latitude, double longitude) {

        if (isFirstLocation) {
            isFirstLocation = false;
            setMarker(latitude, longitude);
            setUserMapCenter(latitude, longitude);
            mPoiSearch = PoiSearch.newInstance();
            mPoiSearch.setOnGetPoiSearchResultListener(poiLister);
            mPoiSearch.searchInCity(new PoiCitySearchOption().city(city)
                    .keyword("美食").pageNum(10));
        }
    }

    OnGetPoiSearchResultListener poiLister = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            LocalLog.d(TAG, "获得POI结果" + poiResult.toString());
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
