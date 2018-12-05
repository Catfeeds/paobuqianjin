package com.paobuqianjin.pbq.step.view.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.AroundRedBagResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.HomeGoodResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepReWardResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskNumResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WeatherResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.data.netcallback.PaoTipsCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.presenter.im.HomeRecInterface;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.AddAroundRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeScanActivity;
import com.paobuqianjin.pbq.step.view.activity.RedHsRecordActivity;
import com.paobuqianjin.pbq.step.view.activity.RoundRedDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.ShopWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskActivity;
import com.paobuqianjin.pbq.step.view.activity.shop.TaoTianActivity;
import com.paobuqianjin.pbq.step.view.activity.shop.TianMaoActivity;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.BounceScrollView;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.RedDataBean;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
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
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.model.RongGridView;

/**
 * Created by pbq on 2018/11/14.
 */

public class HomeFragment extends BaseFragment implements HomePageInterface, SharedPreferences.OnSharedPreferenceChangeListener, HomeRecInterface {
    private final static String TAG = HomeFragment.class.getSimpleName();
    @Bind(R.id.toay_step)
    TextView toayStep;
    @Bind(R.id.today_income_num)
    TextView todayIncomeNum;
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
    @Bind(R.id.step_dollar_shop)
    LinearLayout stepDollarShop;
    @Bind(R.id.good_grid)
    RongGridView goodGrid;
    @Bind(R.id.home_scroll)
    BounceScrollView homeScroll;
    @Bind(R.id.scan_mark_home)
    ImageView scanMarkHome;
    @Bind(R.id.linear_shop)
    LinearLayout linearShop;
    @Bind(R.id.home_page_v1)
    RelativeLayout homePageV1;
    @Bind(R.id.shop_ping)
    ImageView shopPing;
    @Bind(R.id.go_shoping_tv)
    TextView goShopingTv;
    @Bind(R.id.moon_white_bg)
    ImageView moonWhiteBg;
    @Bind(R.id.toay_step_dollor)
    TextView toayStepDollor;
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
    @Bind(R.id.tianmao)
    ImageView tianmao;
    @Bind(R.id.jingdong)
    ImageView jingdong;
    @Bind(R.id.weipinghui)
    ImageView weipinghui;
    @Bind(R.id.pingduoduo)
    ImageView pingduoduo;
    @Bind(R.id.mogu)
    ImageView mogu;
    @Bind(R.id.older_task_num)
    TextView olderTaskNum;
    @Bind(R.id.parent_task_num)
    TextView parentTaskNum;
    @Bind(R.id.love_task_num)
    TextView loveTaskNum;
    @Bind(R.id.baby_task_num)
    TextView babyTaskNum;
    @Bind(R.id.fiend_task_num)
    TextView fiendTaskNum;
    @Bind(R.id.banner_tv)
    ImageView bannerTv;

    private PopupWindow popupRedPkgWindow;
    private TranslateAnimation animationCircleType;

    private static Map<String, Integer> weatherMap = new LinkedHashMap<>();
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private final static String STEP_ACTION = "com.paobuqianjian.intent.ACTION_STEP";
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private final static String SEND_ACTION = "com.paobuqianin.pbq.step.SEND";//发红包
    private final static String ROUND_ACTION = "com.paobuqianjin.pbq.ROUND_PKG.ACTION";
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
    private GridGoodAdpter gridGoodAdpter;
    private String shopUrl = null;
    RelativeLayout redLayout;
    ArrayList<RedDataBean> redArray = new ArrayList<>();
    //1.朋友专享 2.长辈专享 3.夫妻专享 4.孩子专享 5.父母专享
    private ArrayList<TaskNumResponse.DataBean> arrayList = new ArrayList<>();


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
        Presenter.getInstance(getContext()).attachUiInterface(this);
        Presenter.getInstance(getContext()).getHomePageIncome("today", 1, 1);
        return view;
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
        super.onResume();
        Presenter.getInstance(getContext()).refreshStep();
        updateIncome();
        getAroundRedBag();
        getTaskNum();
        updateUiTime();
    }

    @Override
    protected void initView(View viewRoot) {
        sharedPreferences = Presenter.getInstance(getContext()).getSharePreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        toayStep = (TextView) viewRoot.findViewById(R.id.toay_step);
        todayIncomeNum = (TextView) viewRoot.findViewById(R.id.today_income_num);
        pushRedPkg = (TextView) viewRoot.findViewById(R.id.push_red_pkg);
        historyRecord = (TextView) viewRoot.findViewById(R.id.history_record);
        isBind = Presenter.getInstance(getContext()).bindService(null, TodayStepService.class);
        homeScroll = (BounceScrollView) viewRoot.findViewById(R.id.home_scroll);
        barHome = (LinearLayout) viewRoot.findViewById(R.id.bar_home);
        toayStepDollor = (TextView) viewRoot.findViewById(R.id.toay_step_dollor);
        olderTaskNum = (TextView) viewRoot.findViewById(R.id.older_task_num);
        parentTaskNum = (TextView) viewRoot.findViewById(R.id.parent_task_num);
        fiendTaskNum = (TextView) viewRoot.findViewById(R.id.fiend_task_num);
        babyTaskNum = (TextView) viewRoot.findViewById(R.id.baby_task_num);
        loveTaskNum = (TextView) viewRoot.findViewById(R.id.love_task_num);
        gridGoodAdpter = new GridGoodAdpter(getContext(), 12);
        goodGrid = (RongGridView) viewRoot.findViewById(R.id.good_grid);
        for (int i = 1; i <= 5; i++) {
            TaskNumResponse.DataBean dataBean = new TaskNumResponse.DataBean();
            dataBean.setType(i);
            dataBean.setCount(0);
            arrayList.add(dataBean);
        }
        goodGrid.setAdapter(gridGoodAdpter);
        moonWhiteBg = (ImageView) viewRoot.findViewById(R.id.moon_white_bg);
        redLayout = (RelativeLayout) viewRoot.findViewById(R.id.red_pkg_sq);
        goodGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < gridGoodAdpter.getData().size()) {
                    if (!TextUtils.isEmpty(gridGoodAdpter.getData().get(position).getTarget_url())) {
                        String goodUrl = gridGoodAdpter.getData().get(position).getTarget_url() + "&" + Presenter.getInstance(getContext()).getShopEnd();
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
        shopPing = (ImageView) viewRoot.findViewById(R.id.shop_ping);
        goShopingTv = (TextView) viewRoot.findViewById(R.id.go_shoping_tv);
        SpannableString spannableString = new SpannableString("去商城逛逛 查看更多");
        spannableString.setSpan(new TypefaceSpan("default-bold"), 1, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(0xFFFFA202), 1, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        goShopingTv.setText(spannableString);
        updateUiTime();

    }

    private void updateUiTime() {
        if (isWhite()) {
            LocalLog.d(TAG, "白天");
            moonWhiteBg.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.white_day));
            todayIncomeNum.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_withe));
            todayIncomeNum.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            toayStep.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_withe));
            toayStep.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            toayStepDollor.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_withe));
            toayStepDollor.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            pushRedPkg.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_withe));
            pushRedPkg.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
            historyRecord.setTextColor(ContextCompat.getColor(getContext(), R.color.color_home_withe));
            historyRecord.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.home_element_white));
        } else {
            LocalLog.d(TAG, "黑夜");
            moonWhiteBg.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.moon_day));
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
        }
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
                    if (!TextUtils.isEmpty(homeGoodResponse.getData().getShop_url())) {
                        shopUrl = homeGoodResponse.getData().getShop_url() + "?" + Presenter.getInstance(getContext()).getShopEnd();
                    }
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
        popRoundRedPkg(redId);
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
                getAroundRedBag();
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
                        getAroundRedBag();
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
                getAroundRedBag();
            }
        });
    }

    private void getAroundRedBag() {
        LocalLog.d(TAG, "获取红包");
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlGetRedpacketMap, null, new PaoTipsCallBack() {
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
                                layoutParams.topMargin = layoutParams.topMargin + (int) (Math.random() * (redLayout.getWidth() - dibiao.getWidth()));
                                layoutParams.leftMargin = layoutParams.leftMargin + (int) (Math.random() * (redLayout.getHeight() - dibiao.getHeight()));
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

    @OnClick({R.id.go_shoping_tv, R.id.shop_ping, R.id.parent_red, R.id.baby_red, R.id.love_red, R.id.older_red, R.id.friend_red, R.id.tianmao,
            R.id.jingdong, R.id.weipinghui, R.id.pingduoduo, R.id.mogu, R.id.scan_mark_home, R.id.push_red_pkg, R.id.history_record})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.history_record:
                Intent hisIntent = new Intent(getContext(), RedHsRecordActivity.class);
                hisIntent.setAction(ROUND_ACTION);
                startActivity(hisIntent);
                break;
            case R.id.push_red_pkg:
                Intent intentAround = new Intent();
                intentAround.setAction(SEND_ACTION);
                intentAround.setClass(getContext(), AddAroundRedBagActivity.class);
                startActivityForResult(intentAround, REQUEST_AROUND);
                break;
            case R.id.go_shoping_tv:
                if (!TextUtils.isEmpty(shopUrl))
                    startActivity(new Intent(getActivity(), ShopWebViewActivity.class).putExtra("url", shopUrl));
                break;
            case R.id.shop_ping:
                if (!TextUtils.isEmpty(shopUrl))
                    startActivity(new Intent(getActivity(), ShopWebViewActivity.class).putExtra("url", shopUrl));
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
            case R.id.tianmao:
                startActivity(TianMaoActivity.class, null);
                break;
            case R.id.jingdong:
                startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", NetApi.urlJd));
                break;
            case R.id.weipinghui:
                PaoToastUtils.showLongToast(getContext(), "敬请期待");
                break;
            case R.id.pingduoduo:
                LocalLog.d(TAG, "拼多多");
                startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", NetApi.tickXiXiUrl));
                break;
            case R.id.mogu:
                startActivity(new Intent(getContext(), SingleWebViewActivity.class).putExtra("url", NetApi.urlMoguUrl));
                break;
            case R.id.scan_mark_home:
                requestPermissionScan(Permission.Group.CAMERA);
                break;
        }
    }

}
