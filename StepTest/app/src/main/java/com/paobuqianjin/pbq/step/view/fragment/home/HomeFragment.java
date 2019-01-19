package com.paobuqianjin.pbq.step.view.fragment.home;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.j256.ormlite.stmt.query.In;
import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.l.okhttppaobu.okhttp.callback.BitmapCallback;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.LooperTextView;
import com.paobuqianjin.pbq.step.customview.RedPkgAnimation;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AroundRedBagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PreliveLegResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ShopRedResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepReWardResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskNumResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WeatherResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.presenter.im.HomeRecInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.SharedPreferencesUtil;
import com.paobuqianjin.pbq.step.view.activity.AddAroundRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.AddConsumptiveRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.ConsumptiveRedBag2Activity;
import com.paobuqianjin.pbq.step.view.activity.GoldenSponsoractivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeScanActivity;
import com.paobuqianjin.pbq.step.view.activity.RedHsRecordActivity;
import com.paobuqianjin.pbq.step.view.activity.RoundRedDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.activity.VipActivity;
import com.paobuqianjin.pbq.step.view.activity.shop.ShopEntryActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.RedDataBean;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.tencent.mapsdk.raster.model.BitmapDescriptor;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.tencent.tencentmap.mapsdk.map.UiSettings;
import com.today.step.lib.TodayStepService;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * Created by pbq on 2018/11/14.
 */

public class HomeFragment extends BaseFragment implements HomePageInterface, SharedPreferences.OnSharedPreferenceChangeListener, HomeRecInterface, TencentMap.OnMapClickListener, TencentMap.OnMarkerClickListener, TencentLocationListener {
    private final static String TAG = HomeFragment.class.getSimpleName();
    @Bind(R.id.mapview)
    MapView mapview;
    @Bind(R.id.today_step_label)
    TextView todayStepLabel;
    @Bind(R.id.toay_step)
    TextView toayStep;
    @Bind(R.id.today_doll_label)
    TextView todayDollLabel;
    @Bind(R.id.toay_step_dollor)
    TextView toayStepDollor;
    @Bind(R.id.today_crash_label)
    TextView todayCrashLabel;
    @Bind(R.id.today_income_num)
    TextView todayIncomeNum;
    @Bind(R.id.home_data_linear)
    LinearLayout homeDataLinear;
    @Bind(R.id.push_red_pkg)
    TextView pushRedPkg;
    @Bind(R.id.history_record)
    TextView historyRecord;
    @Bind(R.id.push_rec)
    LinearLayout pushRec;
    @Bind(R.id.dibiao)
    ImageView dibiao;
    @Bind(R.id.red_pkg_sq)
    RelativeLayout redPkgSq;
    @Bind(R.id.older_img)
    ImageView olderImg;
    @Bind(R.id.older_task_num)
    TextView olderTaskNum;
    @Bind(R.id.older_red)
    LinearLayout olderRed;
    @Bind(R.id.parent_img)
    ImageView parentImg;
    @Bind(R.id.parent_task_num)
    TextView parentTaskNum;
    @Bind(R.id.parent_red)
    LinearLayout parentRed;
    @Bind(R.id.love_img)
    ImageView loveImg;
    @Bind(R.id.love_task_num)
    TextView loveTaskNum;
    @Bind(R.id.love_red)
    LinearLayout loveRed;
    @Bind(R.id.baby_img)
    ImageView babyImg;
    @Bind(R.id.baby_task_num)
    TextView babyTaskNum;
    @Bind(R.id.baby_red)
    LinearLayout babyRed;
    @Bind(R.id.friend_img)
    ImageView friendImg;
    @Bind(R.id.fiend_task_num)
    TextView fiendTaskNum;
    @Bind(R.id.friend_red)
    LinearLayout friendRed;
    @Bind(R.id.new_s)
    TextView newS;
    @Bind(R.id.home_ad_text)
    LooperTextView homeAdText;
    @Bind(R.id.home_ad)
    LinearLayout homeAd;
    @Bind(R.id.city_name)
    TextView cityName;
    @Bind(R.id.app_name)
    TextView appName;
    @Bind(R.id.scan_mark_home)
    ImageView scanMarkHome;
    @Bind(R.id.home_page_v1)
    RelativeLayout homePageV1;
    @Bind(R.id.step_ex)
    View stepEx;
    @Bind(R.id.shop_entry)
    View shopEntry;
    @Bind(R.id.new_reward)
    View newReward;
    @Bind(R.id.join_us)
    View joinUs;
    @Bind(R.id.spring_red)
    ImageView springRed;
    @Bind(R.id.select_down)
    ImageView selectDown;

    private PopupWindow popupRedPkgWindow, vipPopWnd;
    private TranslateAnimation animationCircleType;

    private static Map<String, Integer> weatherMap = new LinkedHashMap<>();
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private final static String STEP_ACTION = "com.paobuqianjian.intent.ACTION_STEP";
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private final static String SEND_ACTION = "com.paobuqianin.pbq.step.SEND";//发红包
    private final static String ROUND_ACTION = "com.paobuqianjin.pbq.ROUND_PKG.ACTION";
    private final static String GOLDEN_VIP_ACTION = "com.paobuqianjin.pbq.step.GODEN_VIP_ACTION";
    private final static String RELEASE_ACTION = "com.paobuqianjin.pbq.step.RELEASE_ACTION";
    private final static int REQUEST_AROUND = 101;
    public static int lastStep = 0;
    private int showStep = 0;
    private final static int MSG_UPDATE_STEP = 0;
    private final static int MSG_UPDATE_STEP_LOCAL = 1;
    private final static int MSG_UPDATE_STEP_TODAY = 2;
    private UpdateHandler updateHandler = new UpdateHandler(this);
    private int stepAnimation = 0;
    private int SPEED_DEFAULT = 40;
    private static int DEFAULT_COUNT = 25;//默认动画25
    private View popRedPkgView;
    ImageView openRedPkgView;
    private LinearLayout homeAdLinear;
    private LooperTextView homaAd;
    private ArrayList<AdObject> adList;
    private SharedPreferences sharedPreferences;
    private boolean isBind = false;
    private LinearLayout barHome;
    private String shopUrl = null;
    RelativeLayout redLayout;
    ArrayList<RedDataBean> redArray = new ArrayList<>();
    //1.朋友专享 2.长辈专享 3.夫妻专享 4.孩子专享 5.父母专享
    private ArrayList<TaskNumResponse.DataBean> arrayList = new ArrayList<>();
    private final static int REQUEST_VIP = 102;
    private final static String SPOSNOR_ACTION = "com.paobuqianjin.person.SPONSOR_ACTION";
    private final static String PKG_ACTION = "com.paobuqianjin.person.PKG_ACTION";
    private boolean isVip = true;
    private String urlShopApply;
    private boolean isFirstLocation = true;
    private TencentLocationManager locationManager;
    private String req;//定位类型
    private double[] currentLocation = {0, 0};
    private double[] nowLocation = {0, 0};
    private TencentMap tencentMap;
    private final static int PRE_LEG = -111;
    private final static int EX_GOOD_DETAIL = 2019;
    private final static int EX_GOOD_RELEASE = 2020;
    private final static int SHOP_ENTRY = 2021;
    private List<Marker> aroundShopMarkerList = new ArrayList<>();
    private List<View> shopImageList = new ArrayList<>();
    private String currentCity;
    private final static String COUNTRY = "全国";
    private TextToSpeech textToSpeech = null;
    private Vibrator mVibrator;
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

    private ErrorBean errorBean = new ErrorBean();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_page_v1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        //rectRoundBitmap();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(STEP_ACTION);
        intentFilter.addAction(LOCATION_ACTION);
        getContext().registerReceiver(stepLocationReciver, intentFilter);
        initTTS();
        mVibrator =(Vibrator)getContext().getSystemService(Service.VIBRATOR_SERVICE);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        Presenter.getInstance(getContext()).getHomePageIncome("today", 1, 1);
        initMapView(savedInstanceState);
        return view;
    }


    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        startActivity(new Intent(getContext(), ConsumptiveRedBag2Activity.class).putExtra("isNoConsumptive", true));
    }


    private void startAuto(String data) {
        if (!TextUtils.isEmpty(data) && textToSpeech != null) {
            textToSpeech.setPitch(1.0f);
            textToSpeech.setSpeechRate(1.0f);
            textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void initTTS() {
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS) {
                    textToSpeech.setPitch(1.0f);
                    textToSpeech.setSpeechRate(1.0f);

                    int result1 = textToSpeech.setLanguage(Locale.US);
                    int result2 = textToSpeech.setLanguage(Locale.SIMPLIFIED_CHINESE);

                    boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
                    boolean b = (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED);

                    LocalLog.d(TAG, "US支持否？" + a + "\nzh-cn支持否" + b);
                } else {
                    PaoToastUtils.showLongToast(getContext(), "数据丢失或不支持！");
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
        locationManager = TencentLocationManager.getInstance(getContext());
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
    public void onLocationChanged(TencentLocation location, int error, String s) {
        if (error == TencentLocation.ERROR_OK && isFirstLocation) {
            isFirstLocation = false;
            stopLocation();
            logMsg(location);
            String city = location.getCity();
            currentCity = city;
            cityName.setText(city);
            selectDown.setVisibility(View.VISIBLE);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Presenter.getInstance(getContext()).setLocationAction(latitude, longitude);
            this.currentLocation[0] = latitude;
            this.currentLocation[1] = longitude;
            this.nowLocation[0] = latitude;
            this.nowLocation[1] = longitude;
//            tencentMap.setOnMapCameraChangeListener(onMapCameraChangeListener);
//            tencentMap.zoomToSpan(new LatLng(currentLocation[0] - 0.01, currentLocation[1] - 0.01), new LatLng(currentLocation[0] + 0.01, currentLocation[1] + 0.01));
            tencentMap.setCenter(new LatLng(currentLocation[0], currentLocation[1]));
//            tencentMap.animateTo(new LatLng(currentLocation[0], currentLocation[1]));
/*            setPosition(latitude, longitude, true, true);*/
            //getAroundRedBag();
            getNearShopAndRed();
        } else {
            //PaoToastUtils.showShortToast(this, reason);
            LocalLog.e(TAG, s);
        }
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
        tencentMap.setCenter(new LatLng(Presenter.getInstance(getContext()).getLocation()[1], Presenter.getInstance(getContext()).getLocation()[0]));
//设置缩放级别
        tencentMap.setZoom(13);
//        tencentMap.zoomToSpan(Presenter.getInstance(this).getLocation()[1], Presenter.getInstance(this).getLocation()[0]);
        //获取UiSettings实例
        UiSettings uiSettings = mapview.getUiSettings();
//设置logo到屏幕底部中心
        //uiSettings.setLogoPosition(UiSettings.LOGO_POSITION_CENTER_BOTTOM);
//设置比例尺到屏幕右下角
        uiSettings.setScaleViewPosition(UiSettings.SCALEVIEW_POSITION_RIGHT_BOTTOM);
//启用缩放手势(更多的手势控制请参考开发手册)
        uiSettings.setZoomGesturesEnabled(false);
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setScaleControlsEnabled(false);

//        addRedBagView();
        tencentMap.setOnMapClickListener(this);
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
        setDefaultLocation();
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
        tencentMap.setCenter(new LatLng(currentLocation[0], currentLocation[1]));
        //getAroundRedBag();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        getContext().unregisterReceiver(stepLocationReciver);
        if (isBind) {
            Presenter.getInstance(getContext()).unbindStepService();
            isBind = false;
        }
    }

    @Override
    public void onResume() {
        mapview.onResume();
        super.onResume();
        Presenter.getInstance(getContext()).refreshStep();
        updateIncome();
        switch (cityName.getText().toString().trim()) {
            case COUNTRY:
                getAroundRedBag();
                break;
            default:
                getNearShopAndRed();
                break;
        }

        getTaskNum();
        //updateUiTime();
    }

    @Override
    public void onStop() {
        mapview.onStop();
        super.onStop();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onPause() {
        mapview.onPause();
        super.onPause();
    }

    @Override
    protected void initView(View viewRoot) {
        currentLocation = Presenter.getInstance(getContext()).getLocation();
        mapview = (MapView) viewRoot.findViewById(R.id.mapview);
        appName = (TextView) viewRoot.findViewById(R.id.app_name);
        sharedPreferences = Presenter.getInstance(getContext()).getSharePreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        toayStep = (TextView) viewRoot.findViewById(R.id.toay_step);
        todayIncomeNum = (TextView) viewRoot.findViewById(R.id.today_income_num);
        pushRedPkg = (TextView) viewRoot.findViewById(R.id.push_red_pkg);
        historyRecord = (TextView) viewRoot.findViewById(R.id.history_record);
        isBind = Presenter.getInstance(getContext()).bindService(null, TodayStepService.class);
        barHome = (LinearLayout) viewRoot.findViewById(R.id.bar_home);
        toayStepDollor = (TextView) viewRoot.findViewById(R.id.toay_step_dollor);
        olderTaskNum = (TextView) viewRoot.findViewById(R.id.older_task_num);
        parentTaskNum = (TextView) viewRoot.findViewById(R.id.parent_task_num);
        fiendTaskNum = (TextView) viewRoot.findViewById(R.id.fiend_task_num);
        babyTaskNum = (TextView) viewRoot.findViewById(R.id.baby_task_num);
        loveTaskNum = (TextView) viewRoot.findViewById(R.id.love_task_num);
        todayStepLabel = (TextView) viewRoot.findViewById(R.id.today_step_label);
        todayDollLabel = (TextView) viewRoot.findViewById(R.id.today_doll_label);
        todayCrashLabel = (TextView) viewRoot.findViewById(R.id.today_crash_label);
        scanMarkHome = (ImageView) viewRoot.findViewById(R.id.scan_mark_home);
        cityName = (TextView) viewRoot.findViewById(R.id.city_name);
        selectDown = (ImageView) viewRoot.findViewById(R.id.select_down);
        for (int i = 1; i <= 5; i++) {
            TaskNumResponse.DataBean dataBean = new TaskNumResponse.DataBean();
            dataBean.setType(i);
            dataBean.setCount(0);
            arrayList.add(dataBean);
        }
        redLayout = (RelativeLayout) viewRoot.findViewById(R.id.red_pkg_sq);
        loadTextBanner();
        shopApplyUrl();
        canReleasePkg(false);
    }

    private void shopApplyUrl() {
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlShopApplyRes, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    urlShopApply = new JSONObject(s).getJSONObject("data").getString("url");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {

            }
        });
    }

    private void updateUiTime() {
        if (isWhite()) {
            LocalLog.d(TAG, "白天");
            todayIncomeNum.setTextColor(ContextCompat.getColor(getContext(), R.color.color_f8));
            todayIncomeNum.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            toayStep.setTextColor(ContextCompat.getColor(getContext(), R.color.color_f8));
            toayStep.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            toayStepDollor.setTextColor(ContextCompat.getColor(getContext(), R.color.color_f8));
            toayStepDollor.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            pushRedPkg.setTextColor(ContextCompat.getColor(getContext(), R.color.color_f8));
            pushRedPkg.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            historyRecord.setTextColor(ContextCompat.getColor(getContext(), R.color.color_f8));
            historyRecord.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            setLabelColor(ContextCompat.getColor(getContext(), R.color.color_161727));
        } else {
            LocalLog.d(TAG, "黑夜");
            todayIncomeNum.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_moon));
            todayIncomeNum.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_bg_moon));
            toayStep.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_moon));
            toayStep.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_bg_moon));
            toayStepDollor.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_moon));
            toayStepDollor.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_bg_moon));
            pushRedPkg.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_moon));
            pushRedPkg.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_bg_moon));
            historyRecord.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_moon));
            historyRecord.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_bg_moon));
            setLabelColor(ContextCompat.getColor(getContext(), R.color.color_f8));
        }
    }

    private void setLabelColor(int Color) {
        todayStepLabel.setTextColor(Color);
        todayDollLabel.setTextColor(Color);
        todayCrashLabel.setTextColor(Color);
    }

    @Override
    public void onDestroy() {
        if (mapview != null) {
            mapview.onDestroy();
        }
        super.onDestroy();
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        updateHandler.removeCallbacksAndMessages(null);
        LocalLog.d(TAG, "onDestroy() enter");
        showStep = 0;
        redArray.clear();
        shopImageList.clear();
        aroundShopMarkerList.clear();
        if (popupRedPkgWindow != null) {
            popupRedPkgWindow.dismiss();
            popupRedPkgWindow = null;
        }
        if (vipPopWnd != null) {
            vipPopWnd.dismiss();
            vipPopWnd = null;
        }
        if(mVibrator != null){
            mVibrator.cancel();
        }
    }

    /*权限适配*/
    private void requestPermission(String... permissions) {
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
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    public void requestPermissionScan(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        new IntentIntegrator(getActivity())
                                .setBeepEnabled(true)
                                .setOrientationLocked(false)
                                .setCaptureActivity(QrCodeScanActivity.class)
                                .initiateScan();
                        LocalLog.d(TAG, "扫码");
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
                    mSetting.showSetting(permissions);
                }
            }
        }).start();
    }

    @Override
    public void responseStepToday(int stepToday) {
        if (showStep == 0) {
            LocalLog.d(TAG, "新进入Homepage 数字变化动画 ");
            lastStep = stepToday;
            stepAnimation = lastStep;
            toayStep.setText(String.valueOf(lastStep));
            toayStep.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateHandler.sendEmptyMessageDelayed(MSG_UPDATE_STEP_TODAY, SPEED_DEFAULT);
                }
            }, 500);
            showStep = stepToday;

            LocalLog.d(TAG, "stepToday = " + stepToday);
            Message messageNet = Message.obtain();
            messageNet.what = MSG_UPDATE_STEP;
            messageNet.arg1 = stepToday;
            updateHandler.sendMessageDelayed(messageNet, 10000);
        } else {
            toayStep.setText(String.valueOf(stepToday));
            if (lastStep < stepToday) {
                toayStep.setText(String.valueOf(stepToday));
            }
            if (stepToday - lastStep > 100) {
                LocalLog.d(TAG, "stepToday = " + stepToday);
                Message messageNet = Message.obtain();
                messageNet.what = MSG_UPDATE_STEP;
                messageNet.arg1 = stepToday;
                updateHandler.sendMessageDelayed(messageNet, 10000);
                lastStep = stepToday;
            }
        }
    }

    @Override
    public void responseTodayIncome(IncomeResponse incomeResponse) {
        if (!isAdded()) {
            return;
        }
        if (incomeResponse.getError() == 0) {
            LocalLog.d(TAG, "responseTodayIncome() enter " + incomeResponse.toString());
            if (getContext() == null) {
                return;
            }
            String moneyFormat = getContext().getResources().getString(R.string.today_income);
            String moneyStr = String.format(moneyFormat, incomeResponse.getData().getTotal_amount());
            SpannableString spannableString = new SpannableString(moneyStr);
            spannableString.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            LocalLog.d(TAG, "responseAllIncome() " + moneyStr);
            todayIncomeNum.setText(spannableString);
            toayStepDollor.setText(String.valueOf(incomeResponse.getData().getCurr_credit()));
        } else if (incomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else if (incomeResponse.getError() == 1) {
            String moneyFormat = getContext().getResources().getString(R.string.today_income);
            String moneyStr = String.format(moneyFormat, 0.00d);
            SpannableString spannableString = new SpannableString(moneyStr);
            spannableString.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            LocalLog.d(TAG, "responseAllIncome() " + moneyStr);
            todayIncomeNum.setText(spannableString);
            toayStepDollor.setText(String.valueOf(0));
        }

    }

    public void updateIncome() {
        Presenter.getInstance(getContext()).getHomePageIncome("today", 1, 1);
    }


    @Override
    public void responseLocation(String city, double latitude, double longitude) {
        LocalLog.d(TAG, "responseLocation() enter city =" + city + " ,latitude = " + latitude
                + ",longitude= " + longitude);
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            if (!TextUtils.isEmpty(errorCode.getMessage()) && errorCode.getMessage().contains("红包")) {

            } else {
                if (errorCode.getMessage().contains("城市")) {
                    return;
                }
                PaoToastUtils.showLongToast(getContext(), errorCode.getMessage());
            }
        }
    }

    @Override
    public void response(SponsorRedPkgResponse sponsorRedPkgResponse) {

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    private static class UpdateHandler extends Handler {
        WeakReference<HomeFragment> weakReference;

        public UpdateHandler(HomeFragment homePageFragment) {
            weakReference = new WeakReference<HomeFragment>(homePageFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            HomeFragment homePageFragment = weakReference.get();
            if (homePageFragment != null && homePageFragment.getContext() != null) {
                switch (msg.what) {
                    case MSG_UPDATE_STEP:
                        //ava.lang.NullPointerException: Attempt to invoke virtual method 'android.content.Context android.content.Context.getApplicationContext()' on a null obje
                        LocalLog.d(TAG, "MSG_UPDATE_STEP msg " + msg.arg1);

                        if (msg.arg1 > 0) {
                            Presenter.getInstance(homePageFragment.getContext()).postUserStep(msg.arg1);
                        }

                        if (hasMessages(MSG_UPDATE_STEP)) {
                            removeMessages(MSG_UPDATE_STEP);
                        }
                        break;
                    case MSG_UPDATE_STEP_LOCAL:
                        LocalLog.d(TAG, "MSG_UPDATE_STEP_LOCAL enter");

                        break;
                    case MSG_UPDATE_STEP_TODAY:
                        if (homePageFragment.showStep < homePageFragment.stepAnimation) {
                            homePageFragment.showStep += homePageFragment.stepAnimation / DEFAULT_COUNT;
                        }
                        if (homePageFragment.showStep >= homePageFragment.stepAnimation) {
                            homePageFragment.toayStep.setText(String.valueOf(homePageFragment.stepAnimation));
                        } else {
                            homePageFragment.toayStep.setText(String.valueOf(homePageFragment.showStep));
                            sendEmptyMessageDelayed(MSG_UPDATE_STEP_TODAY, 40);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

    }

    @Override
    public void responseTarget(int personTarget) {

    }

    @Override
    public void responseWeather(WeatherResponse weatherResponse) {
    }

    @Override
    public void response(PostUserStepResponse postUserStepResponse) {
        if (postUserStepResponse.getData() != null) {
            if (postUserStepResponse.getData().getIs_receive() == 1) {
                LocalLog.d(TAG, "有步币可以领取");
                logAndStepReward(postUserStepResponse.getData().getType());
            }

        }
    }


    /*运动步币奖励*/
    private void logAndStepReward(final int type) {
        if (!isAdded()) {
            return;
        }
        if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
            LocalLog.d(TAG, "红包在显示");
            return;
        }
        popRedPkgView = View.inflate(getContext(), R.layout.register_reword, null);
        openRedPkgView = (ImageView) popRedPkgView.findViewById(R.id.open_first);
        popupRedPkgWindow = new PopupWindow(popRedPkgView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupRedPkgWindow = null;
            }
        });
        TextView title = (TextView) popRedPkgView.findViewById(R.id.des_title);
        TextView des = (TextView) popRedPkgView.findViewById(R.id.reg_pkg_des);
        final RelativeLayout unOpenRkg = (RelativeLayout) popRedPkgView.findViewById(R.id.start_red_kg);
        popupRedPkgWindow.setFocusable(true);
        popupRedPkgWindow.setOutsideTouchable(true);
        popupRedPkgWindow.setBackgroundDrawable(new BitmapDrawable());
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        popRedPkgView.findViewById(R.id.opened_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupRedPkgWindow.dismiss();
            }
        });
        popRedPkgView.findViewById(R.id.cancel_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupRedPkgWindow.dismiss();
                FlagPreference.setCurrentDate(getContext(), DateTimeUtil.getCurrentTime());
            }
        });
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        openRedPkgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                LocalLog.d(TAG, "领取步币奖励");
                final Rotate3dAnimation animation = new Rotate3dAnimation(0, 359, view.getWidth() / 2f, view.getHeight() / 2f, 30, true);
                animation.setDuration(500);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setFillAfter(true);
                view.setAnimation(animation);
                view.startAnimation(animation);

                openRedPkgView.setEnabled(false);
                openRedPkgView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (openRedPkgView != null && openRedPkgView.getVisibility() == View.VISIBLE
                                && popupRedPkgWindow != null
                                && popupRedPkgWindow.isShowing()) {
                            openRedPkgView.clearAnimation();
                        }
                    }
                }, 2 * 60 * 1000);
                Map<String, String> param = new HashMap<>();
                param.put("type", String.valueOf(type));
                Presenter.getInstance(getActivity()).postPaoBuSimple(NetApi.urlStepReWard, param, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        openRedPkgView.clearAnimation();
                        try {
                            StepReWardResponse stepReWardResponse = new Gson().fromJson(s, StepReWardResponse.class);
                            RelativeLayout resultShow = (RelativeLayout) popRedPkgView.findViewById(R.id.red_result);
                            TextView dayReward = (TextView) popRedPkgView.findViewById(R.id.day_reward);
                            dayReward.setText("恭喜您获得" + stepReWardResponse.getData().getSource_tip() + "奖励");
                            String dayStepDollar = String.format(getString(R.string.day_step_dollar), stepReWardResponse.getData().getCredit());
                            TextView dayStepTv = (TextView) popRedPkgView.findViewById(R.id.step_dollar);
                            dayStepTv.setText("+" + dayStepDollar);
                            RedPkgAnimation pkgAnimation = new RedPkgAnimation();
                            pkgAnimation.setHideAnimation(unOpenRkg, 200);
                            pkgAnimation.setShowAnimation(resultShow, 200);
                        } catch (JsonSyntaxException j) {
                            LocalLog.d(TAG, "数据错误!");
                        }
                    }

                    @Override
                    protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                        LocalLog.d(TAG, "领取失败!");
                        openRedPkgView.clearAnimation();
                    }
                });

            }
        });
        popupRedPkgWindow.showAtLocation(getActivity().findViewById(R.id.home_page_v1), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);
    }


    @Override
    public void responseAllIncome(AllIncomeResponse incomeResponse) {
        LocalLog.d(TAG, "responseAllIncome() enter " + incomeResponse.toString());
    }

    private void loadTextBanner() {
        String bannerUrl = NetApi.urlAd + "?position=homepage";
        LocalLog.d(TAG, "bannerUrl  = " + bannerUrl);
        Presenter.getInstance(getContext()).getPaoBuSimple(bannerUrl, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    Adresponse adresponse = new Gson().fromJson(s, Adresponse.class);
                    adList = new ArrayList<>();
                    if (adresponse.getData() != null && adresponse.getData().size() > 0) {
                        int size = adresponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            AdObject adObject = new AdObject();
                            adObject.setRid(Integer.parseInt(adresponse.getData().get(i).getRid()));
                            adObject.setTitle(adresponse.getData().get(i).getTitle());
                            adObject.setTarget_url(adresponse.getData().get(i).getTarget_url());
                            LocalLog.d(TAG, adObject.toString());
                            adList.add(adObject);
                        }
                    }
                    if (adList.size() > 0) {
                        if (homeAdLinear == null) {
                            homeAdLinear = (LinearLayout) getActivity().findViewById(R.id.home_ad);
                            homaAd = (LooperTextView) getActivity().findViewById(R.id.home_ad_text);
                        }
                        homeAdLinear.setVisibility(View.VISIBLE);
                        List<String> listAd = new ArrayList<>();
                        for (AdObject o : adList) {
                            listAd.add(o.getTitle());
                        }
                        homaAd.setTipList(listAd);
                        homaAd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (homaAd != null) {
                                    int index = homaAd.getCurrentIndex();
                                    LocalLog.d(TAG, "index = " + index);
                                    if (index < adList.size()) {
                                        String targetUrl = adList.get(index).getTarget_url();
                                        if (!TextUtils.isEmpty(targetUrl))
                                            startActivity(new Intent(getActivity(), SingleWebViewActivity.class).putExtra("url", targetUrl));
                                    }
                                }
                            }
                        });
                    }
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


    private void taskNumUI() {
        for (int i = 0; i < 5; i++) {
            if (arrayList.get(i).getType() == 1) {
                if (arrayList.get(i).getCount() > 0) {
                    fiendTaskNum.setText(String.valueOf((arrayList.get(i).getCount())));
                    fiendTaskNum.setVisibility(View.VISIBLE);
                } else {
                    fiendTaskNum.setVisibility(View.GONE);
                }
            } else if (arrayList.get(i).getType() == 2) {
                if (arrayList.get(i).getCount() > 0) {
                    olderTaskNum.setText(String.valueOf((arrayList.get(i).getCount())));
                    olderTaskNum.setVisibility(View.VISIBLE);
                } else {
                    olderTaskNum.setVisibility(View.GONE);
                }
            } else if (arrayList.get(i).getType() == 3) {
                if (arrayList.get(i).getCount() > 0) {
                    loveTaskNum.setText(String.valueOf((arrayList.get(i).getCount())));
                    loveTaskNum.setVisibility(View.VISIBLE);
                } else {
                    loveTaskNum.setVisibility(View.GONE);
                }
            } else if (arrayList.get(i).getType() == 4) {
                if (arrayList.get(i).getCount() > 0) {
                    babyTaskNum.setText(String.valueOf((arrayList.get(i).getCount())));
                    babyTaskNum.setVisibility(View.VISIBLE);
                } else {
                    babyTaskNum.setVisibility(View.GONE);
                }
            } else if (arrayList.get(i).getType() == 5) {
                if (arrayList.get(i).getCount() > 0) {
                    parentTaskNum.setText(String.valueOf((arrayList.get(i).getCount())));
                    parentTaskNum.setVisibility(View.VISIBLE);
                } else {
                    parentTaskNum.setVisibility(View.GONE);
                }
            }
        }
    }


    private void cleanTaskData() {
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setCount(0);
        }
    }

    private void getTaskNum() {
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlTaskNum, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    cleanTaskData();
                    TaskNumResponse taskNumResponse = new Gson().fromJson(s, TaskNumResponse.class);
                    if (taskNumResponse.getData() != null) {
                        int size = taskNumResponse.getData().size();
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < 5; j++) {
                                if (taskNumResponse.getData().get(i).getType() == arrayList.get(j).getType()) {
                                    arrayList.get(j).setCount(taskNumResponse.getData().get(i).getCount());
                                }
                            }
                        }
                    }
                    taskNumUI();
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
    public void recRedPkg(String redId) {
        LocalLog.d(TAG, "领红包 " + redId);
        if (errorBean != null && errorBean.getErr() >= 7) {
            popVipWindow(errorBean.getTitle(), errorBean.getErr());
            return;
        } else {
            popRoundRedPkg(redId);
        }
    }


    private void releaseGoods() {
        if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
            LocalLog.d(TAG, "弹窗在显示");
            return;
        }
        popRedPkgView = View.inflate(getContext(), R.layout.release_common_pop_window, null);
        ImageView closeButton = (ImageView) popRedPkgView.findViewById(R.id.close_release_common);
        Button copyButton = (Button) popRedPkgView.findViewById(R.id.copy_button);
        TextView textView = (TextView) popRedPkgView.findViewById(R.id.text_web);
        if (!TextUtils.isEmpty(urlShopApply)) {
            textView.setText(urlShopApply);
        }
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                if (!TextUtils.isEmpty(urlShopApply)) {
                    ClipboardManager cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData textClipData = ClipData.newPlainText("Label", urlShopApply);
                    cmb.setPrimaryClip(textClipData);
                    LocalLog.d(TAG, "  msg = " + cmb.getText());
                    PaoToastUtils.showLongToast(getActivity(), "网址复制成功");
                }*/


            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupRedPkgWindow != null)
                    popupRedPkgWindow.dismiss();
            }
        });
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
        popupRedPkgWindow.showAtLocation(getView().findViewById(R.id.home_page_v1), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);

    }

    private void popRoundRedPkg(final String redId) {
        if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
            LocalLog.d(TAG, "红包在显示");
            return;
        }

        popRedPkgView = View.inflate(getContext(), R.layout.round_pkg_pop_window, null);
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
                pullDownAroundRedBag(redId);
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
        popupRedPkgWindow.showAtLocation(getView().findViewById(R.id.home_page_v1), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);
    }


    private void pullDownAroundRedBag(final String redId) {
        Map<String, String> params = new HashMap<>();
        params.put("redid", redId);
        if (currentLocation[0] > 0d && currentLocation[1] > 0d) {
            params.put("latitude", String.valueOf(currentLocation[0]));
            params.put("longitude", String.valueOf(currentLocation[1]));
        }
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.receiveAroundRed, params, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                PaoToastUtils.showShortToast(getActivity(), "领取成功");
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
                Intent intent = new Intent();
                intent.setClass(getContext(), RoundRedDetailActivity.class);
                intent.putExtra(getContext().getPackageName() + "red_id", redId);
                intent.putExtra(getContext().getPackageName() + "red_result", result);
                intent.putExtra(getContext().getPackageName() + "type", type);
                startActivity(intent);
                switch (cityName.getText().toString().trim()) {
                    case COUNTRY:
                        getAroundRedBag();
                        break;
                    default:
                        getNearShopAndRed();
                        break;
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (popupRedPkgWindow != null && popupRedPkgWindow.isShowing()) {
                    popupRedPkgWindow.dismiss();
                }
                String result = "";
                if (errorBean == null) {
                    result = getString(R.string.error_red);
                } else {
                    if (errorBean.getError() == 2) {
                        LocalLog.d(TAG, "领红包已经达到上限!需要开通会员");
                        switch (cityName.getText().toString().trim()) {
                            case COUNTRY:
                                getAroundRedBag();
                                break;
                            default:
                                getNearShopAndRed();
                                break;
                        }
                        return;
                    } else {
                        result = getString(R.string.slow_text);
                    }

                }
                Intent intent = new Intent();
                intent.setClass(getContext(), RoundRedDetailActivity.class);
                intent.putExtra(getContext().getPackageName() + "red_id", redId);
                intent.putExtra(getContext().getPackageName() + "red_result", result);
                startActivity(intent);
                switch (cityName.getText().toString().trim()) {
                    case COUNTRY:
                        getAroundRedBag();
                        break;
                    default:
                        getNearShopAndRed();
                        break;
                }
            }
        });
    }


    private void getNearShopAndRed() {
        if (Presenter.getInstance(getContext()).getLocation()[0] > 0.0d && Presenter.getInstance(getContext()).getLocation()[1] > 0.0d) {
            Map<String, String> param = new HashMap<>();
            param.put("latitude", String.valueOf(Presenter.getInstance(getContext()).getLocation()[0]));
            param.put("longitude", String.valueOf(Presenter.getInstance(getContext()).getLocation()[1]));
            Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlShopNearRead, param, new PaoCallBack() {
                @Override
                protected void onSuc(String s) {
                    clear();
                    redArray.clear();
                    try {
                        final ShopRedResponse shopRedResponse = new Gson().fromJson(s, ShopRedResponse.class);
                        int redSize = shopRedResponse.getData().getRedpacket().size();
                        LocalLog.d(TAG, "红包个数 " + redSize);
                        int shopSize = shopRedResponse.getData().getBusiness().size();
                        LocalLog.d(TAG, "商铺数量 " + shopSize);
                        switch (shopRedResponse.getData().getIs_receive()) {
                            case 0:
                                popVipWindow(shopRedResponse.getData().getMessage(), shopRedResponse.getData().getIs_receive());
                                break;
                            case 1:
                                LocalLog.d(TAG, "无限制领取");
                                break;
                            case -1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                popVipWindow(shopRedResponse.getData().getMessage(), shopRedResponse.getData().getIs_receive());
                                break;
                            default:
                                if (checkShowedToday(shopRedResponse.getData().getMessage(), shopRedResponse.getData().getIs_receive())) {

                                } else {
                                    popVipWindow(shopRedResponse.getData().getMessage(), shopRedResponse.getData().getIs_receive());
                                }
                                break;
                        }

                        errorBean.setTitle(shopRedResponse.getData().getMessage());
                        errorBean.setErr(shopRedResponse.getData().getIs_receive());
                        if (shopRedResponse.getData().getAlert_type() == 1) {
                            LocalLog.d(TAG, "语音提醒");
                            startAuto(shopRedResponse.getData().getTips());
                        } else if (shopRedResponse.getData().getAlert_type() == 2) {
                            LocalLog.d(TAG, "震动提醒");
                            if(mVibrator != null){
                                mVibrator.vibrate(new long[]{100,10,100,1000},-1);
                            }
                        } else if (shopRedResponse.getData().getAlert_type() == 3) {
                            LocalLog.d(TAG, "语音+震动");
                            startAuto(shopRedResponse.getData().getTips());
                            if(mVibrator != null){
                                mVibrator.vibrate(new long[]{100,10,100,1000},-1);
                            }
                        }
                        for (int i = 0; i < redSize; i++) {
                            if (i == 0) {
                                LocalLog.d(TAG, "dibiao red");
                                dibiao = redLayout.findViewById(R.id.dibiao);
                                dibiao.setVisibility(View.VISIBLE);
                                RedDataBean redDataBean = new RedDataBean(dibiao,
                                        shopRedResponse.getData().getRedpacket().get(i).getRed_id(), HomeFragment.this);
                                redArray.add(redDataBean);
                            } else {
                                LocalLog.d(TAG, "new Red");
                                ImageView view = new ImageView(getContext());
                                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dibiao.getLayoutParams());
                                layoutParams.topMargin = layoutParams.topMargin + (int) (Math.random() * (redLayout.getHeight() - dibiao.getHeight()));
                                layoutParams.leftMargin = layoutParams.leftMargin + (int) (Math.random() * (redLayout.getWidth() - dibiao.getWidth()));
                                view.setImageResource(R.drawable.home_red);
                                view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                view.setLayoutParams(layoutParams);
                                redLayout.addView(view);
                                RedDataBean redDataBean = new RedDataBean(view,
                                        shopRedResponse.getData().getRedpacket().get(i).getRed_id(), HomeFragment.this);
                                redArray.add(redDataBean);
                            }
                        }

                        aroundShopMarkerList.clear();
                        for (int i = 0; i < aroundShopMarkerList.size(); i++) {
                            aroundShopMarkerList.get(i).remove();
                        }
                        for (int j = 0; j < shopSize; j++) {
                            final View view = View.inflate(getContext(), R.layout.make_view, null);
                            final ImageView logo = (CircleImageView) view.findViewById(R.id.logo_shop);
                            final int position = j;
                            LocalLog.d(TAG, logo == null ? "null" : "logo =" + shopRedResponse.getData().getBusiness().get(j).getLogo());
                            OkHttpUtils.get()
                                    .url(shopRedResponse.getData().getBusiness().get(j).getLogo())
                                    .build()
                                    .execute(new BitmapCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int i, Object o) {

                                        }

                                        @Override
                                        public void onResponse(Bitmap bitmap, int i) {
                                            if (bitmap != null && isAdded()) {
                                                logo.setImageBitmap(bitmap);
                                                Marker markerShop = tencentMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(Double.parseDouble(shopRedResponse.getData().getBusiness().get(position).getLatitude()),
                                                                Double.parseDouble(shopRedResponse.getData().getBusiness().get(position).getLongitude())))
                                                        .anchor(0.5f, 0.5f)
                                                        .snippet("around:" + position)
                                                        .icon(makeShopIco(view)).draggable(false));
                                                aroundShopMarkerList.add(markerShop);
                                            }
                                        }
                                    });

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                    clear();
                }
            });
        }
    }

    private BitmapDescriptor makeShopIco(View view) {
        return BitmapDescriptorFactory.fromView(view);
    }

    //获取全国红包
    private void getAroundRedBag() {
        LocalLog.d(TAG, "获取红包");
        Map<String, String> param = new HashMap<>();
        param.put("latitude", String.valueOf(Presenter.getInstance(getContext()).getLocation()[0]));
        param.put("longitude", String.valueOf(Presenter.getInstance(getContext()).getLocation()[1]));
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlGetRedpacketMap, param, new PaoTipsCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                clear();
                redArray.clear();
                try {
                    AroundRedBagResponse aroundRedBagResponse = new Gson().fromJson(s, AroundRedBagResponse.class);
                    if (aroundRedBagResponse.getData() != null
                            && aroundRedBagResponse.getData().getRedpacket_list() != null
                            && aroundRedBagResponse.getData().getRedpacket_list().size() > 0) {
                        switch (aroundRedBagResponse.getData().getIs_receive()) {
                            case 0:
                                popVipWindow(aroundRedBagResponse.getData().getMessage(), aroundRedBagResponse.getData().getIs_receive());
                                break;
                            case 1:
                                LocalLog.d(TAG, "无限制领取");
                                break;
                            case -1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                popVipWindow(aroundRedBagResponse.getData().getMessage(), aroundRedBagResponse.getData().getIs_receive());
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
                        //随机算法生成红包
                        for (int i = 0; i < aroundRedBagResponse.getData().getRedpacket_list().size(); i++) {
                            if (i == 0) {
                                LocalLog.d(TAG, "dibiao red");
                                dibiao = redLayout.findViewById(R.id.dibiao);
                                dibiao.setVisibility(View.VISIBLE);
                                RedDataBean redDataBean = new RedDataBean(dibiao,
                                        aroundRedBagResponse.getData().getRedpacket_list().get(i).getRed_id(), HomeFragment.this);
                                redArray.add(redDataBean);
                            } else {
                                LocalLog.d(TAG, "new Red");
                                ImageView view = new ImageView(getContext());
                                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dibiao.getLayoutParams());
                                layoutParams.topMargin = layoutParams.topMargin + (int) (Math.random() * (redLayout.getHeight() - dibiao.getHeight()));
                                layoutParams.leftMargin = layoutParams.leftMargin + (int) (Math.random() * (redLayout.getWidth() - dibiao.getWidth()));
                                view.setImageResource(R.drawable.home_red);
                                view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                view.setLayoutParams(layoutParams);
                                redLayout.addView(view);
                                RedDataBean redDataBean = new RedDataBean(view,
                                        aroundRedBagResponse.getData().getRedpacket_list().get(i).getRed_id(), HomeFragment.this);
                                redArray.add(redDataBean);
                            }
                        }
                    }
                } catch (Exception e) {
                    PaoToastUtils.showLongToast(getContext(), "开小差了，请稍后再试");
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                super.onFal(e, errorStr, errorBean);
                if (!isAdded()) {
                    return;
                }
                clear();
                if (errorBean != null) {
                    PaoToastUtils.showLongToast(getContext(), errorBean.getMessage());
                } else {
                    PaoToastUtils.showLongToast(getContext(), "开小差了，请稍后再试");
                }
            }
        });
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
        View vipView = View.inflate(getContext(), R.layout.target_dest_popwindow, null);
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
        View centerLine = (View) vipView.findViewById(R.id.center_line);
        LocalLog.d(TAG, "error_code = " + errorCode);
        switch (errorCode) {
            case -1:
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
                        intentAround.setClass(getContext(), AddAroundRedBagActivity.class);
                        startActivityForResult(intentAround, REQUEST_AROUND);
                    }
                });
                centerLine.setVisibility(View.VISIBLE);
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 0:
                SpannableString spannableString = new SpannableString(title);
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color_e4393c)),
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
                        intent.setClass(getContext(), VipActivity.class);
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
                        intentAround.setClass(getContext(), AddAroundRedBagActivity.class);
                        startActivityForResult(intentAround, REQUEST_AROUND);
                    }
                });
                textLeft.setVisibility(View.VISIBLE);
                centerLine.setVisibility(View.VISIBLE);
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
                        intent.setClass(getContext(), TaskReleaseActivity.class);
                        startActivity(intent);
                    }
                });
                textLeft.setVisibility(View.VISIBLE);
                centerLine.setVisibility(View.VISIBLE);
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
                        intent.setClass(getContext(), TaskReleaseActivity.class);
                        startActivity(intent);
                    }
                });
                textLeft.setVisibility(View.VISIBLE);
                centerLine.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 5:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("去开通");
                if (isVip) {
                    textDes.setText("发红包");
                }
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        LocalLog.d(TAG, "办理金牌会员");
                        if (isVip) {
                            Intent intent = new Intent();
                            intent.setClass(getContext(), AddConsumptiveRedBagActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(getContext(), GoldenSponsoractivity.class);
                            startActivity(intent);
                        }
                    }
                });
                centerLine.setVisibility(View.VISIBLE);
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                break;
            case 6:
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("发红包");
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        LocalLog.d(TAG, "发消费红包");
                        Intent intent = new Intent();
                        intent.setClass(getContext(), AddConsumptiveRedBagActivity.class);
                        startActivity(intent);
                    }
                });
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                centerLine.setVisibility(View.VISIBLE);
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
            case PRE_LEG:
                centerLine.setVisibility(View.VISIBLE);
                textTile.setGravity(Gravity.CENTER);
                textTile.setText(title);
                textDes.setText("去开通");
                textLeft.setVisibility(View.VISIBLE);
                textLeft.setText("取消");
                textDes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vipPopWnd.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(GOLDEN_VIP_ACTION);
                        intent.setClass(getContext(), VipActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            default:
                PaoToastUtils.showLongToast(getContext(), title);
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
        vipPopWnd.showAtLocation(getView().findViewById(R.id.home_page_v1), Gravity.CENTER, 0, 0);

        vipView.startAnimation(animationCircleType);
        SharedPreferencesUtil.put("around_error" + String.valueOf(errorCode), DateTimeUtil.getCurrentTime());
    }

    private void clear() {
        LocalLog.d(TAG, "清理首页红包");
        int size = redArray.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                redArray.get(i).view.setImageBitmap(null);
                Drawable drawable = redArray.get(i).view.getDrawable();
                if (drawable != null && drawable instanceof BitmapDrawable) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                }
                redLayout.removeView(redArray.get(i).view);
            } else {
                dibiao.setVisibility(View.GONE);
            }
        }
        System.gc();
    }

    private boolean isWhite() {
        String timeStr = DateTimeUtil.getCurrentDateHH();
        try {
            if (6 < Integer.parseInt(timeStr) && Integer.parseInt(timeStr) < 18) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    /*如果通过点击则弹框，否则不弹框*/
    private void canReleasePkg(final boolean isClick) {
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlPreLeg, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    PreliveLegResponse preliveLegResponse = new Gson().fromJson(s, PreliveLegResponse.class);
                    if (preliveLegResponse.getData().getRecord_btn_show() == 1) {
                        historyRecord.setVisibility(View.VISIBLE);
                    } else {
                        historyRecord.setVisibility(View.GONE);
                    }
                    if (preliveLegResponse.getData().getHas_privilege() == 1 && isClick) {
                        Intent intentAround = new Intent();
                        intentAround.setAction(SEND_ACTION);
                        intentAround.setClass(getContext(), AddAroundRedBagActivity.class);
                        startActivityForResult(intentAround, REQUEST_AROUND);
                    } else {
                        if (isClick) {
                            popVipWindow(preliveLegResponse.getData().getTip_msg(), PRE_LEG);
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

    @OnClick({R.id.step_ex, R.id.shop_entry, R.id.parent_red, R.id.baby_red, R.id.love_red, R.id.older_red, R.id.friend_red, R.id.new_reward,
            R.id.join_us, R.id.spring_red, R.id.scan_mark_home, R.id.push_red_pkg, R.id.history_record, R.id.select_city})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.history_record:
                Intent hisIntent = new Intent(getContext(), RedHsRecordActivity.class);
                hisIntent.setAction(ROUND_ACTION);
                startActivity(hisIntent);
                break;
            case R.id.push_red_pkg:
                canReleasePkg(true);
                break;
            case R.id.step_ex:
                LocalLog.d(TAG, "步币兑换!");
                break;
            case R.id.shop_entry:
                LocalLog.d(TAG, "店铺入驻!");
                Intent intentEntry = new Intent();
                intentEntry.setClass(getContext(), ShopEntryActivity.class);
                startActivityForResult(intentEntry, SHOP_ENTRY);
                break;
            case R.id.parent_red:
                bundle.putInt("style", 5);
                startActivity(TaskActivity.class, bundle);
                break;
            case R.id.baby_red:
                bundle.putInt("style", 4);
                startActivity(TaskActivity.class, bundle);
                break;
            case R.id.love_red:
                bundle.putInt("style", 3);
                startActivity(TaskActivity.class, bundle);
                break;
            case R.id.older_red:
                bundle.putInt("style", 2);
                startActivity(TaskActivity.class, bundle);
                break;
            case R.id.friend_red:
                bundle.putInt("style", 1);
                startActivity(TaskActivity.class, bundle);
                break;
            case R.id.new_reward:
                LocalLog.d(TAG, "新手奖励!");
                break;
            case R.id.join_us:
                LocalLog.d(TAG, "加盟我们!");
                break;
            case R.id.spring_red:
                LocalLog.d(TAG, "春节红包!");
                break;
            case R.id.scan_mark_home:
                requestPermissionScan(Permission.Group.CAMERA);
                break;
            case R.id.select_city:
                if (!TextUtils.isEmpty(cityName.getText().toString().trim())) {
                    switch (cityName.getText().toString().trim()) {
                        case COUNTRY:
                            cityName.setText(currentCity);
                            getNearShopAndRed();
                            break;
                        default:
                            cityName.setText(COUNTRY);
                            getAroundRedBag();
                            break;
                    }
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SHOP_ENTRY && resultCode == Activity.RESULT_OK) {
            LocalLog.d(TAG, "商家入驻!");
            //getExGood(1);
            getNearShopAndRed();
        }
    }
}
