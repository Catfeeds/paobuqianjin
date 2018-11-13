package com.paobuqianjin.pbq.step.view.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.LooperTextView;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.customview.RedPkgAnimation;
import com.paobuqianjin.pbq.step.data.bean.AdObject;
import com.paobuqianjin.pbq.step.data.bean.gson.response.Adresponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.AllIncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.StepReWardResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TwentyOneResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WeatherResponse;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.FlagPreference;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.DateTimeUtil;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.AddAroundRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.ConsumTotalActivity;
import com.paobuqianjin.pbq.step.view.activity.ConsumptiveRedBag2Activity;
import com.paobuqianjin.pbq.step.view.activity.ConsumptiveRedBagActivity;
import com.paobuqianjin.pbq.step.view.activity.GoldenSponsoractivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeScanActivity;
import com.paobuqianjin.pbq.step.view.activity.ShopWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SingleWebViewActivity;
import com.paobuqianjin.pbq.step.view.activity.SponsorRedDetailActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
import com.paobuqianjin.pbq.step.view.activity.UserFitActivity;
import com.paobuqianjin.pbq.step.view.base.adapter.SponsorRedPakAdapter;
import com.paobuqianjin.pbq.step.view.base.fragment.BaseFragment;
import com.paobuqianjin.pbq.step.view.base.view.DefaultRationale;
import com.paobuqianjin.pbq.step.view.base.view.PermissionSetting;
import com.paobuqianjin.pbq.step.view.base.view.Rotate3dAnimation;
import com.paobuqianjin.pbq.step.view.base.view.StepProcessDrawable;
import com.paobuqianjin.pbq.step.view.base.view.WaveView;
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

/**
 * Created by pbq on 2017/12/1.
 */

public final class HomePageFragment extends BaseFragment implements HomePageInterface, SharedPreferences.OnSharedPreferenceChangeListener {
    private final static String TAG = HomePageFragment.class.getSimpleName();
    @Bind(R.id.bg_home)
    ImageView bgHome;
    @Bind(R.id.process_step_now)
    ImageView processStepNow;
    @Bind(R.id.toay_step)
    TextView toayStep;
    @Bind(R.id.mu_biao_text)
    TextView muBiaoText;
    @Bind(R.id.process_step)
    FrameLayout processStep;
    @Bind(R.id.wave_view)
    WaveView waveView;
    //    @Bind(R.id.wave_view_span)
//    RelativeLayout waveViewSpan;
//    @Bind(R.id.major_bg)
//    ImageView majorBg;
//    @Bind(R.id.major_span)
    //  RelativeLayout majorSpan;
    @Bind(R.id.home_title)
    TextView homeTitle;
    @Bind(R.id.home_page)
    RelativeLayout homePage;
    @Bind(R.id.income_today)
    TextView incomeToday;
    @Bind(R.id.today_income_span)
    RelativeLayout todayIncomeSpan;
    @Bind(R.id.line)
    ImageView line;
    @Bind(R.id.income_month)
    TextView incomeMonth;
    @Bind(R.id.month_income_home)
    TextView monthIncomeHome;
    @Bind(R.id.month_income_span)
    RelativeLayout monthIncomeSpan;
    @Bind(R.id.income_out_look)
    RelativeLayout incomeOutLook;
    @Bind(R.id.out_red_pkg_image)
    ImageView outRedPkgImage;
    @Bind(R.id.out_red_package)
    TextView outRedPackage;
    @Bind(R.id.create_circle_image)
    ImageView createCircleImage;
    @Bind(R.id.recv_sponsor_rpg)
    ImageView incomeRedPkgImage;
    @Bind(R.id.income_red_package)
    TextView incomeRedPackage;
    @Bind(R.id.add_friend_image)
    ImageView addFriendImage;

    @Bind(R.id.weather_icon)
    ImageView weatherIcon;
    @Bind(R.id.wendu)
    TextView wendu;
    @Bind(R.id.weather_span)
    RelativeLayout weatherSpan;
    @Bind(R.id.city_name)
    TextView cityName;
    @Bind(R.id.create_circle)
    TextView createCircle;
    @Bind(R.id.invite_friend)
    TextView inviteFriend;
    @Bind(R.id.today_income_num)
    TextView todayIncomeNum;
    @Bind(R.id.target_steps)
    TextView targetSteps;
    @Bind(R.id.step_desc)
    ImageView stepDesc;
    @Bind(R.id.scan_mark_home)
    ImageView scanMarkHome;
    @Bind(R.id.scan_img)
    RelativeLayout scanImg;
    @Bind(R.id.toay_step_span)
    RelativeLayout toayStepSpan;
    @Bind(R.id.target_steps_span)
    RelativeLayout targetStepsSpan;
    @Bind(R.id.step_desc_span)
    RelativeLayout stepDescSpan;
    ImageView openRedPkgView;
    @Bind(R.id.person_task_pkg)
    RelativeLayout personTaskPkg;
    @Bind(R.id.task_num)
    TextView taskNum;
    private View popRedPkgView;
    private View popTargetView;
    private PopupWindow popupRedPkgWindow;
    private TranslateAnimation animationCircleType;
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private final static String STEP_ACTION = "com.paobuqianjian.intent.ACTION_STEP";
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private final static String USER_FIT_ACTION_SETTING = "com.paobuqianjin.pbq.USER_FIT_ACTION_USER_SETTING";
    private final static int MSG_UPDATE_STEP = 0;
    private final static int MSG_UPDATE_STEP_LOCAL = 1;
    private final static int MSG_UPDATE_STEP_TODAY = 2;
    private UpdateHandler updateHandler = new UpdateHandler(this);

    private static Map<String, Integer> weatherMap = new LinkedHashMap<>();
    private Rationale mRationale;
    private PermissionSetting mSetting;
    public static int lastStep = 0;
    private int PERMISSION_REQUEST = 100;
    private boolean isBind = false;
    SponsorRedPakAdapter sponsorRedPakAdapter;
    TextView totalRedPkg;
    TextView redRevTv;
    RecyclerView redPkgRecycler;
    TextView errorTextView;
    TextView desPkgTextView;
    ImageView targetCircle;
    private boolean canDrawProcess = true;
    private int pageIndex = 1;
    private final static int PAGESIZE = 10;
    private NormalDialog normalDialog;
    private int stepAnimation = 0;
    private int SPEED_DEFAULT = 40;
    private int showStep = 0;
    private int targetStep = 10000;

    private static int DEFAULT_COUNT = 25;//默认动画25
    StepProcessDrawable stepProcessDrawable;
    NormalDialog dialog;
    private SharedPreferences sharedPreferences;
    private final static String PKG_ACTION = "com.paobuqianjin.person.PKG_ACTION";
    private final static String SPOSNOR_ACTION = "com.paobuqianjin.person.SPONSOR_ACTION";
    private RelativeLayout homeAdLinear;
    private LooperTextView homaAd;
    private ArrayList<AdObject> adList;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        //rectRoundBitmap();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(STEP_ACTION);
        intentFilter.addAction(LOCATION_ACTION);
        getContext().registerReceiver(stepLocationReciver, intentFilter);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        requestPermission(Permission.Group.LOCATION);
        Presenter.getInstance(getContext()).getHomePageIncome("today", pageIndex, 1);
        Presenter.getInstance(getContext()).getHomePageIncome("all", pageIndex, 1);
        return view;
    }


    private void checkTwentyOne() {
        LocalLog.d(TAG, "checkTwentyOne() enter");
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlUser + FlagPreference.getUid(getContext()), null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                if (!isAdded()) {
                    return;
                }
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    int firstred = jsonObj.getInt("firstred");
                    if (firstred == 1) {
                        if (!((DateTimeUtil.getCurrentTime() + String.valueOf(Presenter.getInstance(getContext()).getId())).equals(FlagPreference.getCurrentDate(getContext())))) {
                            showFirstRedPkg();
                        }
                    } else {

                    }
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                if (errorBean == null) {
                    PaoToastUtils.showLongToast(getContext(), getString(R.string.error_red));
                }
            }
        });
    }

    private void getTaskNum() {
        Presenter.getInstance(getContext()).getPaoBuSimple(NetApi.urlTaskNum, null, new PaoCallBack() {
            @Override
            protected void onSuc(String s) {
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    jsonObj = jsonObj.getJSONObject("data");
                    String totalCount = jsonObj.getString("totalCount");
                    taskNum.setText(totalCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onFal(Exception e, String errorStr, ErrorCode errorBean) {
                taskNum.setVisibility(View.GONE);
            }
        });
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
        popupRedPkgWindow.showAtLocation(getActivity().findViewById(R.id.home_page), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);
    }

    /*新手红包*/
    private void showFirstRedPkg() {
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
                LocalLog.d(TAG, "领取新手红包");
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
                Presenter.getInstance(getActivity()).postPaoBuSimple(NetApi.urlCheckTtyOne, null, new PaoCallBack() {
                    @Override
                    protected void onSuc(String s) {
                        openRedPkgView.clearAnimation();
                        try {
                            TwentyOneResponse twentyOneResponse = new Gson().fromJson(s, TwentyOneResponse.class);
                            String dayStr = String.format(getString(R.string.day_congratulate),
                                    twentyOneResponse.getData().getDay());
                            RelativeLayout resultShow = (RelativeLayout) popRedPkgView.findViewById(R.id.red_result);
                            TextView dayTv = (TextView) popRedPkgView.findViewById(R.id.day_reward);
                            dayTv.setText(dayStr);
                            String dayStepDollar = String.format(getString(R.string.day_step_dollar), twentyOneResponse.getData().getCredit());
                            TextView dayStepTv = (TextView) popRedPkgView.findViewById(R.id.step_dollar);
                            dayStepTv.setText(dayStepDollar);
                            TextView moneyTv = (TextView) popRedPkgView.findViewById(R.id.day_money);
                            if (Float.parseFloat(twentyOneResponse.getData().getMoney()) > 0.0f) {
                                moneyTv.setText("+" + twentyOneResponse.getData().getMoney() + "元");
                            }
                            String deStr = twentyOneResponse.getData().getRemark();
                            if (!TextUtils.isEmpty(deStr)) {
                                deStr = deStr.replace(",", "\n");
                                TextView oneTv = (TextView) popRedPkgView.findViewById(R.id.text_one);
                                oneTv.setText(deStr);
                            }
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
        popupRedPkgWindow.showAtLocation(getActivity().findViewById(R.id.home_page), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);
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
    protected int getLayoutResId() {
        return R.layout.home_page;
    }

    @Override
    protected void initView(View viewRoot) {
        sharedPreferences = Presenter.getInstance(getContext()).getSharePreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        processStepNow = (ImageView) viewRoot.findViewById(R.id.process_step_now);
        toayStep = (TextView) viewRoot.findViewById(R.id.toay_step);
        cityName = (TextView) viewRoot.findViewById(R.id.city_name);
        todayIncomeNum = (TextView) viewRoot.findViewById(R.id.today_income_num);
        wendu = (TextView) viewRoot.findViewById(R.id.wendu);
        weatherIcon = (ImageView) viewRoot.findViewById(R.id.weather_icon);
        outRedPkgImage = (ImageView) viewRoot.findViewById(R.id.out_red_pkg_image);
        outRedPkgImage.setOnClickListener(onClickListener);
        incomeRedPkgImage = (ImageView) viewRoot.findViewById(R.id.recv_sponsor_rpg);
        incomeRedPkgImage.setOnClickListener(onClickListener);
        createCircleImage = (ImageView) viewRoot.findViewById(R.id.create_circle_image);
        createCircleImage.setOnClickListener(onClickListener);
        addFriendImage = (ImageView) viewRoot.findViewById(R.id.add_friend_image);
        addFriendImage.setOnClickListener(onClickListener);
        homeTitle = (TextView) viewRoot.findViewById(R.id.home_title);
        monthIncomeHome = (TextView) viewRoot.findViewById(R.id.month_income_home);
        scanImg = (RelativeLayout) viewRoot.findViewById(R.id.scan_img);
        stepDescSpan = (RelativeLayout) viewRoot.findViewById(R.id.step_desc_span);
        stepDescSpan.setOnClickListener(onClickListener);
        scanImg.setOnClickListener(onClickListener);
        isBind = Presenter.getInstance(getContext()).bindService(null, TodayStepService.class);
        targetCircle = (ImageView) viewRoot.findViewById(R.id.target_step_circle);
        targetSteps = (TextView) viewRoot.findViewById(R.id.target_steps);
        targetStep = Presenter.getInstance(getContext()).getTarget(getContext());
        targetSteps.setText(String.valueOf(targetStep));
        personTaskPkg = (RelativeLayout) viewRoot.findViewById(R.id.person_task_pkg);
        personTaskPkg.setOnClickListener(onClickListener);
        taskNum = (TextView) viewRoot.findViewById(R.id.task_num);
        Presenter.getInstance(getContext()).getWeather(-1d, -1d);
        loadBanner();
    }

    private void loadBanner() {
        String bannerUrl = NetApi.urlAd + "?position=homepage" + Presenter.getInstance(getContext()).getLocationStrFormat();
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
                            homeAdLinear = (RelativeLayout) getActivity().findViewById(R.id.home_ad);
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

    @Override
    public void onResume() {
        super.onResume();
        Presenter.getInstance(getContext()).refreshStep();
        updateIncome();
        getTaskNum();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        getContext().unregisterReceiver(stepLocationReciver);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        if (waveView != null) {
            waveView.stopDraw();
        }
        waveView = null;
        if (isBind) {
            Presenter.getInstance(getContext()).unbindStepService();
            isBind = false;
        }
    }

    private void showUseInfSettingDialog(final UserInfoResponse.DataBean userInfo) {
        if (normalDialog == null) {
            normalDialog = new NormalDialog(getActivity());
            normalDialog.setMessage(getString(R.string.useinfo_setting));
            normalDialog.setNoOnclickListener(getString(R.string.cancel), new NormalDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    normalDialog.dismiss();
                }
            });

            normalDialog.setYesOnclickListener(getString(R.string.fit_info), new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    normalDialog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), UserFitActivity.class);
                    intent.setAction(USER_FIT_ACTION_SETTING);
                    intent.putExtra("userinfo", userInfo);
                    startActivity(intent);

                }
            });
            if (!normalDialog.isShowing()) {
                normalDialog.show();
            }
        } else {
            if (!normalDialog.isShowing()) {
                normalDialog.show();
            }
        }
    }

    public void updateIncome() {
        Presenter.getInstance(getContext()).getHomePageIncome("today", pageIndex, 1);
        Presenter.getInstance(getContext()).getHomePageIncome("all", pageIndex, 1);
    }

    private final InnerCallBack innerRecRedCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            if (!isAdded()) {
                return;
            }
            openRedPkgView.clearAnimation();
            if (object instanceof ErrorCode) {
                LocalLog.d(TAG, "领取红包出错" + ((ErrorCode) object).getMessage());
                openRedPkgView.setVisibility(View.INVISIBLE);
                errorTextView.setVisibility(View.VISIBLE);
                errorTextView.setText(getString(R.string.error_red));
            } else if (object instanceof RecRedPkgResponse) {
                if (((RecRedPkgResponse) object).getError() == 0) {
                    if (((RecRedPkgResponse) object).getData().getAllmoney() > 0.0f) {
                        openRedPkgView.setVisibility(View.INVISIBLE);
                        totalRedPkg.setVisibility(View.VISIBLE);
                        totalRedPkg.setText(String.valueOf(((RecRedPkgResponse) object).getData().getAllmoney()));
                        redRevTv.setText(getString(R.string.get_red));
                        redRevTv.setVisibility(View.VISIBLE);
                        updateIncome();
                        ArrayList<?> sponsorData = new ArrayList<>();
                        sponsorData.addAll((ArrayList) ((RecRedPkgResponse) object).getData().getResult());
                        if (sponsorRedPakAdapter != null) {
                            sponsorRedPakAdapter.notifyDataSetChanged(sponsorData);
                        }
                    } else {
                        openRedPkgView.setVisibility(View.INVISIBLE);
                        errorTextView.setText(getString(R.string.error_red));
                        errorTextView.setVisibility(View.VISIBLE);
                    }
                    desPkgTextView.setVisibility(View.VISIBLE);

                } else if (((RecRedPkgResponse) object).getError() == -1) {
                    errorTextView.setText(getString(R.string.error_red));
                    errorTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    };

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
        popupRedPkgWindow.showAtLocation(getActivity().findViewById(R.id.home_page), Gravity.CENTER, 0, 0);

        popTargetView.startAnimation(animationCircleType);
    }

    @Override
    public void responseLocation(String city, double latitude, double longitude) {
        LocalLog.d(TAG, "responseLocation() enter city =" + city + " ,latitude = " + latitude
                + ",longitude= " + longitude);
        cityName.setText(city);
        Presenter.getInstance(getContext()).getWeather(latitude, longitude);
    }

    @Override
    public void responseAllIncome(AllIncomeResponse incomeResponse) {
        LocalLog.d(TAG, "responseAllIncome() enter " + incomeResponse.toString());
        if (incomeResponse.getError() == 0) {
            if (isAdded() && monthIncomeHome != null) {
                String moneyFormat = getContext().getResources().getString(R.string.month_income);
                String moneyStr = String.format(moneyFormat, incomeResponse.getData().getTotal_amount());
                LocalLog.d(TAG, "responseAllIncome() " + moneyStr);
                monthIncomeHome.setText(moneyStr);
            }
        } else if (incomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
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
    public void responseStepToday(final int stepToday) {
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
    public void responseTarget(int personTarget) {
        LocalLog.d(TAG, "responseTarget() enter");
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
            todayIncomeNum.setText(moneyStr);
        } else if (incomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        }

    }

    @Override
    public void responseWeather(WeatherResponse weatherResponse) {
        if (!isAdded()) {
            return;
        }
        LocalLog.d(TAG, "responseWeather() enter" + weatherResponse.toString());
        if (wendu == null) {
            return;
        }
        if (weatherResponse.getError() == 0) {
            if (weatherResponse.getData() != null) {
                if (!TextUtils.isEmpty(weatherResponse.getData().getTemp())
                        && !TextUtils.isEmpty(weatherResponse.getData().getImg())
                        && weatherResponse.getData().getIs_weather() == 1) {
                    wendu.setText(weatherResponse.getData().getTemp() + "°");
                    weatherIcon.setImageResource(weatherMap.get(weatherResponse.getData().getImg()));
                }

            }
        } else if (weatherResponse.getError() == -100) {
            exitTokenUnfect();
        }


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

    @Override
    public void response(SponsorRedPkgResponse sponsorRedPkgResponse) {
        LocalLog.d(TAG, "SponsorRedPkgResponse() enter " + sponsorRedPkgResponse.toString());
        if (!this.isAdded()) {
            return;
        }
        if (sponsorRedPkgResponse.getError() == 0) {
            if (sponsorRedPkgResponse.getData().getUserstatus() == 0
                    && (sponsorRedPkgResponse.getData().getCanredpacket() == null
                    || sponsorRedPkgResponse.getData().getCanredpacket().size() == 0)) {
                //TODO 领红包错误提示
                //popTargetView();
                LocalLog.d(TAG, "附近没有商家红包");
                UserInfoResponse.DataBean userInfo = Presenter.getInstance(getContext()).getCurrentUser();
                if (userInfo != null) {
                    if (userInfo.getIs_perfect() == 0) {
                        showUseInfSettingDialog(userInfo);
                    } else {
                        showNoRedPkg();
//                        popTargetView(getString(R.string.no_buess_pkg));
                    }
                } else {
                    LocalLog.d(TAG, "userInfo had not get");
                    return;
                }

            } else {
                LocalLog.d(TAG, "有可领或者已经领过的红包！");
                //popRedPkg(sponsorRedPkgResponse);
            }

        } else if (sponsorRedPkgResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            UserInfoResponse.DataBean userInfo = Presenter.getInstance(getContext()).getCurrentUser();
            if (userInfo != null) {
                if (userInfo.getIs_perfect() == 0) {
                    showUseInfSettingDialog(userInfo);
                } else {
                    showNoRedPkg();
                }
            }
        }
        outRedPkgImage.setEnabled(true);
    }

    private void showNoRedPkg() {
        if (dialog == null) {
            dialog = new NormalDialog(getActivity());
            dialog.setMessage(getString(R.string.no_buess_pkg));
            dialog.setSingleBtn(true);
            dialog.setYesOnclickListener("好的", new NormalDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
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

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearLayout:
                    startActivity(TaskActivity.class, null);
                    break;
                case R.id.out_red_pkg_image:
                    LocalLog.d(TAG, "领红包");
                    /*Presenter.getInstance(getContext()).getSponsorRedPkg();
                    outRedPkgImage.setEnabled(false);
                    outRedPkgImage.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (outRedPkgImage != null)
                                outRedPkgImage.setEnabled(true);
                        }
                    }, 15 * 1000);*/
                    startActivity(new Intent(getActivity(), ConsumptiveRedBag2Activity.class).putExtra("isNoConsumptive", true));
                    break;
                case R.id.person_task_pkg:
                    startActivity(TaskActivity.class, null);
                    break;
                case R.id.create_circle_image:
                    LocalLog.d(TAG, "发红包");
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SponsorRedDetailActivity.class);
                    startActivity(intent);
                    break;
                case R.id.recv_sponsor_rpg:
                    LocalLog.d(TAG, "商家消费红包");
                    startActivity(ConsumTotalActivity.class, null);
                    break;
                case R.id.add_friend_image:
                    LocalLog.d(TAG, "商家金牌会员");
                    /*startActivity(GoldenSponsoractivity.class, null);*/
             /*       PaoToastUtils.showLongToast(getContext(), "敬请期待");
                    */
                    String shopUrl = Presenter.getInstance(getContext()).shop();
                    ;
                    if (!TextUtils.isEmpty(shopUrl))
                        startActivity(new Intent(getActivity(), ShopWebViewActivity.class).putExtra("url", shopUrl));
                    break;
                case R.id.scan_img:
                    requestPermissionScan(Permission.Group.CAMERA);
                    break;
                case R.id.step_desc_span:
                    LocalLog.d(TAG, "目标说明");
                    popTargetView(getString(R.string.target_desc));
                    break;
            }
        }
    };

/*    @OnClick({R.id.toay_step_span, R.id.target_steps_span})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toay_step_span:
            case R.id.target_steps_span:
                LocalLog.d(TAG, "目标说明");
                popTargetView(getString(R.string.target_desc));
                break;
        }
    }*/


    private static class UpdateHandler extends Handler {
        WeakReference<HomePageFragment> weakReference;

        public UpdateHandler(HomePageFragment homePageFragment) {
            weakReference = new WeakReference<HomePageFragment>(homePageFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            HomePageFragment homePageFragment = weakReference.get();
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
    public void response(ErrorCode errorCode) {
        if (!isAdded()) {
            return;
        }
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            exitTokenUnfect();
        } else {
            if (!TextUtils.isEmpty(errorCode.getMessage()) && errorCode.getMessage().contains("红包")) {
                showNoRedPkg();
            } else {
                if (errorCode.getMessage().contains("城市")) {
                    return;
                }
                PaoToastUtils.showLongToast(getContext(), errorCode.getMessage());
            }
        }
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
}
