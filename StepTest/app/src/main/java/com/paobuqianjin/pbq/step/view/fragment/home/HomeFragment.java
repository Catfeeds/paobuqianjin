package com.paobuqianjin.pbq.step.view.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.GridGoodAdpter;
import com.paobuqianjin.pbq.step.customview.LooperTextView;
import com.paobuqianjin.pbq.step.customview.RedPkgAnimation;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.HomeGoodResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepReWardResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WeatherResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.ConsumTotalActivity;
import com.paobuqianjin.pbq.step.view.activity.ConsumptiveRedBag2Activity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeScanActivity;
import com.paobuqianjin.pbq.step.view.activity.ShopWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorRedDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
import com.paobuqianjin.pbq.step.view.base.view.StepProcessDrawable;
import com.today.step.lib.TodayStepService;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2018/11/14.
 */

public class HomeFragment extends BaseFragment implements HomePageInterface, SharedPreferences.OnSharedPreferenceChangeListener {
    private final static String TAG = HomeFragment.class.getSimpleName();
    @Bind(R.id.target_step_circle)
    ImageView targetStepCircle;
    @Bind(R.id.process_step_now)
    ImageView processStepNow;
    @Bind(R.id.toay_step)
    TextView toayStep;
    @Bind(R.id.toay_step_span)
    RelativeLayout toayStepSpan;
    @Bind(R.id.mu_biao_text)
    TextView muBiaoText;
    @Bind(R.id.target_steps)
    TextView targetSteps;
    @Bind(R.id.target_steps_span)
    RelativeLayout targetStepsSpan;
    @Bind(R.id.step_desc)
    ImageView stepDesc;
    @Bind(R.id.step_desc_span)
    RelativeLayout stepDescSpan;
    @Bind(R.id.process_step)
    FrameLayout processStep;
    @Bind(R.id.today_income_num)
    TextView todayIncomeNum;
    @Bind(R.id.income_today)
    TextView incomeToday;
    @Bind(R.id.today_income_span)
    RelativeLayout todayIncomeSpan;
    @Bind(R.id.month_income_home)
    TextView monthIncomeHome;
    @Bind(R.id.income_month)
    TextView incomeMonth;
    @Bind(R.id.month_income_span)
    RelativeLayout monthIncomeSpan;
    @Bind(R.id.income_out_look)
    RelativeLayout incomeOutLook;
    @Bind(R.id.new_s)
    TextView newS;
    @Bind(R.id.home_ad_text)
    LooperTextView homeAdText;
    @Bind(R.id.home_ad)
    LinearLayout homeAd;
    @Bind(R.id.parent_red)
    LinearLayout parentRed;
    @Bind(R.id.baby_red)
    LinearLayout babyRed;
    @Bind(R.id.love_red)
    LinearLayout loveRed;
    @Bind(R.id.older_red)
    LinearLayout olderRed;
    @Bind(R.id.friend_red)
    LinearLayout friendRed;
    @Bind(R.id.round_red_image)
    ImageView roundRedImage;
    @Bind(R.id.out_red_package)
    TextView outRedPackage;
    @Bind(R.id.position_red_image)
    ImageView positionRedImage;
    @Bind(R.id.position_red_text)
    TextView positionRedText;
    @Bind(R.id.consum_rpg)
    ImageView consumRpg;
    @Bind(R.id.income_red_package)
    TextView incomeRedPackage;
    @Bind(R.id.step_dollar_shop)
    LinearLayout stepDollarShop;
    @Bind(R.id.good_grid)
    RongGridView goodGrid;
    @Bind(R.id.home_scroll)
    BounceScrollView homeScroll;
    @Bind(R.id.city_name)
    TextView cityName;
    @Bind(R.id.home_title)
    TextView homeTitle;
    @Bind(R.id.scan_mark_home)
    ImageView scanMarkHome;
    @Bind(R.id.linear_shop)
    LinearLayout linearShop;
    @Bind(R.id.home_page_v1)
    RelativeLayout homePageV1;

    private View popTargetView;
    private PopupWindow popupRedPkgWindow;
    private TranslateAnimation animationCircleType;

    private static Map<String, Integer> weatherMap = new LinkedHashMap<>();
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private boolean canDrawProcess = true;
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private final static String STEP_ACTION = "com.paobuqianjian.intent.ACTION_STEP";
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private int targetStep = 10000;
    public static int lastStep = 0;
    private int showStep = 0;
    ImageView targetCircle;
    StepProcessDrawable stepProcessDrawable;
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
    private GridGoodAdpter gridGoodAdpter;

    static {
        weatherMap.put("0", R.drawable.weather_0);
        weatherMap.put("1", R.drawable.weather_1);
        weatherMap.put("2", R.drawable.weather_2);
        weatherMap.put("3", R.drawable.weather_3);
        weatherMap.put("4", R.drawable.weather_4);
        weatherMap.put("5", R.drawable.weather_5);
        weatherMap.put("6", R.drawable.weather_6);
        weatherMap.put("7", R.drawable.weather_7);
        weatherMap.put("8", R.drawable.weather_8);
        weatherMap.put("9", R.drawable.weather_9);
        weatherMap.put("10", R.drawable.weather_10);
        weatherMap.put("11", R.drawable.weather_11);
        weatherMap.put("12", R.drawable.weather_12);
        weatherMap.put("13", R.drawable.weather_13);
        weatherMap.put("14", R.drawable.weather_14);
        weatherMap.put("15", R.drawable.weather_15);
        weatherMap.put("16", R.drawable.weather_17);
        weatherMap.put("18", R.drawable.weather_18);
        weatherMap.put("19", R.drawable.weather_19);
        weatherMap.put("20", R.drawable.weather_20);
        weatherMap.put("21", R.drawable.weather_21);
        weatherMap.put("22", R.drawable.weather_22);
        weatherMap.put("23", R.drawable.weather_23);
        weatherMap.put("24", R.drawable.weather_24);
        weatherMap.put("25", R.drawable.weather_25);
        weatherMap.put("26", R.drawable.weather_26);
        weatherMap.put("27", R.drawable.weather_27);
        weatherMap.put("28", R.drawable.weather_28);
        weatherMap.put("29", R.drawable.weather_29);
        weatherMap.put("30", R.drawable.weather_30);
        weatherMap.put("31", R.drawable.weather_31);
        weatherMap.put("32", R.drawable.weather_32);
        weatherMap.put("49", R.drawable.weather_49);
        weatherMap.put("53", R.drawable.weather_53);
        weatherMap.put("54", R.drawable.weather_54);
        weatherMap.put("55", R.drawable.weather_55);
        weatherMap.put("56", R.drawable.weather_56);
        weatherMap.put("57", R.drawable.weather_57);
        weatherMap.put("58", R.drawable.weather_58);
        weatherMap.put("99", R.drawable.weather_99);
        weatherMap.put("301", R.drawable.weather_301);
        weatherMap.put("302", R.drawable.weather_302);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(context);
        canDrawProcess = true;
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
        Presenter.getInstance(getContext()).attachUiInterface(this);
        requestPermission(Permission.Group.LOCATION);
        Presenter.getInstance(getContext()).getHomePageIncome("today", 1, 1);
        Presenter.getInstance(getContext()).getHomePageIncome("all", 1, 1);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        getContext().unregisterReceiver(stepLocationReciver);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        if (isBind) {
            Presenter.getInstance(getContext()).unbindStepService();
            isBind = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Presenter.getInstance(getContext()).refreshStep();
        updateIncome();
    }

    @Override
    protected void initView(View viewRoot) {
        sharedPreferences = Presenter.getInstance(getContext()).getSharePreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        processStepNow = (ImageView) viewRoot.findViewById(R.id.process_step_now);
        toayStep = (TextView) viewRoot.findViewById(R.id.toay_step);
        cityName = (TextView) viewRoot.findViewById(R.id.city_name);
        todayIncomeNum = (TextView) viewRoot.findViewById(R.id.today_income_num);
        homeTitle = (TextView) viewRoot.findViewById(R.id.home_title);
        monthIncomeHome = (TextView) viewRoot.findViewById(R.id.month_income_home);
        isBind = Presenter.getInstance(getContext()).bindService(null, TodayStepService.class);
        targetCircle = (ImageView) viewRoot.findViewById(R.id.target_step_circle);
        targetSteps = (TextView) viewRoot.findViewById(R.id.target_steps);
        targetStep = Presenter.getInstance(getContext()).getTarget(getContext());
        targetSteps.setText(String.valueOf(targetStep));
        homeScroll = (BounceScrollView) viewRoot.findViewById(R.id.home_scroll);
        barHome = (LinearLayout) viewRoot.findViewById(R.id.bar_home);
        gridGoodAdpter = new GridGoodAdpter(getContext(), 12);
        goodGrid = (RongGridView) viewRoot.findViewById(R.id.good_grid);
        goodGrid.setAdapter(gridGoodAdpter);
        goodGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < gridGoodAdpter.getData().size()) {
                    if (!TextUtils.isEmpty(gridGoodAdpter.getData().get(position).getTarget_url())) {
                        String goodUrl = gridGoodAdpter.getData().get(position).getTarget_url() + Presenter.getInstance(getContext()).getShopEnd();
                        startActivity(new Intent(getActivity(), ShopWebViewActivity.class).putExtra("url",
                                goodUrl));
                    }
                }
            }
        });
        homeScroll.setScrollListener(new BounceScrollView.ScrollListener() {
            @Override
            public void scrollOritention(int l, int t, int oldl, int oldt) {
                LocalLog.d(TAG, "l =  " + l + ",t = " + t + ",oldl= " + oldl + "," + oldt);
                if (Utils.px2dip(getContext(), (float) t) > 64 / 2) {
                    barHome.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_232433));
                } else {
                    barHome.setBackground(null);
                }
            }
        });
        loadBanner();
        initGridGood();
    }

    private void initGridGood() {
        LocalLog.d(TAG, "init() enter");
        Presenter.getInstance(getContext()).postPaoBuSimple(NetApi.urlShopHome, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded())
                    return;
                try {
                    HomeGoodResponse homeGoodResponse = new Gson().fromJson(s, HomeGoodResponse.class);
                    gridGoodAdpter.setDatas(homeGoodResponse.getData().getGoods_list());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (!isAdded()) {
                    return;
                }
                if (errorBean != null) {

                } else {

                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        updateHandler.removeCallbacksAndMessages(null);
        LocalLog.d(TAG, "onDestroy() enter");
        showStep = 0;
        if (popupRedPkgWindow != null) {
            popupRedPkgWindow.dismiss();
            popupRedPkgWindow = null;
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
                        Presenter.getInstance(getContext()).startService(null, LocalBaiduService.class);
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
            drawProcess(targetStep, lastStep);
            showStep = stepToday;

            LocalLog.d(TAG, "stepToday = " + stepToday);
            Message messageNet = Message.obtain();
            messageNet.what = MSG_UPDATE_STEP;
            messageNet.arg1 = stepToday;
            updateHandler.sendMessageDelayed(messageNet, 10000);
        } else {
            toayStep.setText(String.valueOf(stepToday));
            if (lastStep < stepToday) {
                float addAngle = (stepToday - lastStep) * 360.0f / targetStep;
                toayStep.setText(String.valueOf(stepToday));
                if (stepProcessDrawable != null) {
                    stepProcessDrawable.add(addAngle);
                }
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
        }

    }

    public void updateIncome() {
        Presenter.getInstance(getContext()).getHomePageIncome("today", 1, 1);
        Presenter.getInstance(getContext()).getHomePageIncome("all", 1, 1);
    }


    @Override
    public void responseLocation(String city, double latitude, double longitude) {
        LocalLog.d(TAG, "responseLocation() enter city =" + city + " ,latitude = " + latitude
                + ",longitude= " + longitude);
        cityName.setText(city);
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
        if ("target".equals(key) && isAdded()) {
            canDrawProcess = true;
            LocalLog.d(TAG, "用户目标值改变");
            targetStep = Presenter.getInstance(getContext()).getTarget(getContext());
            targetSteps.setText(String.valueOf(targetStep));
            drawProcess(targetStep, lastStep);
        }
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

    public void drawProcess(int target, final int currentStep) {
        float angle = (float) currentStep * 360.0f / target;
        /*if (angle > 360.0f) {
            angle = 360.0f;
        }*/
        final float angelProcess = angle;
        if (canDrawProcess) {
            targetCircle.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int[] location = new int[2];
                    if (processStepNow != null) {
                        processStepNow.getLocationOnScreen(location);
                        if (stepProcessDrawable == null) {
                            stepProcessDrawable = new StepProcessDrawable(getContext(), location[0], location[1], processStepNow.getWidth(), processStepNow.getHeight(),
                                    targetCircle.getWidth(), targetCircle.getHeight());
                        }
                        processStepNow.setImageDrawable(stepProcessDrawable.setmAngle(angelProcess));
                    }
                }
            }, 500);
            canDrawProcess = false;
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
        if (incomeResponse.getError() == 0) {
            if (isAdded() && monthIncomeHome != null) {
                String moneyFormat = getContext().getResources().getString(R.string.month_income);
                String moneyStr = String.format(moneyFormat, incomeResponse.getData().getTotal_amount());
                SpannableString spannableString = new SpannableString(moneyStr);
                spannableString.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                LocalLog.d(TAG, "responseAllIncome() " + moneyStr);
                monthIncomeHome.setText(spannableString);
            }
        } else if (incomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else if (incomeResponse.getError() == 1) {
            String moneyFormat = getContext().getResources().getString(R.string.month_income);
            String moneyStr = String.format(moneyFormat, 0.00d);
            SpannableString spannableString = new SpannableString(moneyStr);
            spannableString.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            LocalLog.d(TAG, "responseAllIncome() " + moneyStr);
            monthIncomeHome.setText(spannableString);
        }
    }


    private void loadBanner() {
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
                } catch (
                        JsonSyntaxException e)

                {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                LocalLog.d(TAG, "获取失败!");
            }
        });
    }

    @OnClick({R.id.linear_shop, R.id.parent_red, R.id.baby_red, R.id.love_red, R.id.older_red, R.id.friend_red, R.id.round_red_image, R.id.position_red_image, R.id.consum_rpg, R.id.scan_mark_home, R.id.step_desc_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_shop:
                String shopUrl = Presenter.getInstance(getContext()).shop();
                if (!TextUtils.isEmpty(shopUrl))
                    startActivity(new Intent(getActivity(), ShopWebViewActivity.class).putExtra("url", shopUrl));
                break;
            case R.id.parent_red:
                startActivity(TaskActivity.class, null);
                break;
            case R.id.baby_red:
                startActivity(TaskActivity.class, null);
                break;
            case R.id.love_red:
                startActivity(TaskActivity.class, null);
                break;
            case R.id.older_red:
                startActivity(TaskActivity.class, null);
                break;
            case R.id.friend_red:
                startActivity(TaskActivity.class, null);
                break;
            case R.id.round_red_image:
                startActivity(new Intent(getActivity(), ConsumptiveRedBag2Activity.class).putExtra("isNoConsumptive", true));
                break;
            case R.id.position_red_image:
                startActivity(SponsorRedDetailActivity.class, null);
                break;
            case R.id.consum_rpg:
                startActivity(ConsumTotalActivity.class, null);
                break;
            case R.id.scan_mark_home:
                requestPermissionScan(Permission.Group.CAMERA);
                break;
            case R.id.step_desc_span:
                popTargetView(getString(R.string.target_desc));
                break;
        }
    }

    private void popTargetView(String titleContent) {
        popTargetView = View.inflate(getContext(), R.layout.target_dest_popwindow, null);
        TextView confirm = (TextView) popTargetView.findViewById(R.id.read_des);
        popupRedPkgWindow = new PopupWindow(popTargetView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        TextView textView = (TextView) popTargetView.findViewById(R.id.quit_title);
        SpannableString text = new SpannableString(titleContent + "\n" + "\n" + "为了准确地统计到用户的步数，建议用户将该应用设置为自启动或添加到白名单。");
        ClickableSpan clickSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                LocalLog.d(TAG, "设置白名单");
                Utils.jumpStartInterface(getActivity());
            }
        };
//        text.setSpan(new TextAppearanceSpan(getContext(),R.style.MyTextPurple6c_Title12),titleContent.length()+2,text.length(),SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        text.setSpan(clickSpan, titleContent.length() + 2 + 22, text.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_6c71c4)), titleContent.length() + 2 + 22, text.length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupRedPkgWindow = null;
            }
        });

        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        popupRedPkgWindow.setBackgroundDrawable(new BitmapDrawable());
        popupRedPkgWindow.setOutsideTouchable(true);
        popupRedPkgWindow.setFocusable(true);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupRedPkgWindow != null) {
                    popupRedPkgWindow.dismiss();
                }
            }
        });
        popupRedPkgWindow.showAtLocation(getActivity().findViewById(R.id.home_page_v1), Gravity.CENTER, 0, 0);

        popTargetView.startAnimation(animationCircleType);
    }
}
