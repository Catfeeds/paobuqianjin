package com.paobuqianjin.pbq.step.view.fragment.home;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.data.bean.gson.param.PostCircleRedPkgParam;
import com.paobuqianjin.pbq.step.data.bean.gson.param.RedPkgRecParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.IncomeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.PostUserStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.RecRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SponsorRedPkgResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.WeatherResponse;
import com.paobuqianjin.pbq.step.model.broadcast.StepLocationReciver;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.model.services.local.StepService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.HomePageInterface;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.view.activity.CreateCircleActivity;
import com.paobuqianjin.pbq.step.view.activity.InviteActivity;
import com.paobuqianjin.pbq.step.view.activity.QrCodeScanActivity;
import com.paobuqianjin.pbq.step.view.activity.TaskReleaseActivity;
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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.income_red_pkg_image)
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
    private View popRedPkgView;
    private View popTargetView;
    private PopupWindow popupRedPkgWindow;
    private TranslateAnimation animationCircleType;
    private StepLocationReciver stepLocationReciver = new StepLocationReciver();
    private final static String STEP_ACTION = "com.paobuqianjian.intent.ACTION_STEP";
    private final static String LOCATION_ACTION = "com.paobuqianjin.intent.ACTION_LOCATION";
    private final static int MSG_UPDATE_STEP = 0;
    private final static int MSG_UPDATE_STEP_LOCAL = 1;
    private UpdateHandler updateHandler = new UpdateHandler(this);

    private static Map<String, Integer> weatherMap = new LinkedHashMap<>();
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private int lastStep = 0;
    private int PERMISSION_REQUEST = 100;
    private boolean isBind = false;
    private boolean redPkgEnable = true;
    SponsorRedPakAdapter sponsorRedPakAdapter;
    TextView totalRedPkg;
    RecyclerView redPkgRecycler;
    TextView errorTextView;
    TextView desPkgTextView;
    ImageView targetCircle;
    private boolean canDrawProcess = true;

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
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        //rectRoundBitmap();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(STEP_ACTION);
        intentFilter.addAction(LOCATION_ACTION);
        getContext().registerReceiver(stepLocationReciver, intentFilter);
        Presenter.getInstance(getContext()).attachUiInterface(this);
        requestPermission(Permission.Group.LOCATION);
        Presenter.getInstance(getContext()).getHomePageIncome("today", 1, 10);
        Presenter.getInstance(getContext()).getHomePageIncome("month", 1, 10);
        return super.onCreateView(inflater, container, savedInstanceState);
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

    protected void toast(@StringRes int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_page;
    }

    @Override
    protected void initView(View viewRoot) {
        super.initView(viewRoot);
        processStepNow = (ImageView) viewRoot.findViewById(R.id.process_step_now);
        toayStep = (TextView) viewRoot.findViewById(R.id.toay_step);
        cityName = (TextView) viewRoot.findViewById(R.id.city_name);
        todayIncomeNum = (TextView) viewRoot.findViewById(R.id.today_income_num);
        wendu = (TextView) viewRoot.findViewById(R.id.wendu);
        weatherIcon = (ImageView) viewRoot.findViewById(R.id.weather_icon);
        outRedPkgImage = (ImageView) viewRoot.findViewById(R.id.out_red_pkg_image);
        outRedPkgImage.setOnClickListener(onClickListener);
        incomeRedPkgImage = (ImageView) viewRoot.findViewById(R.id.income_red_pkg_image);
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
    }

    @Override
    public void onResume() {
        super.onResume();

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
        if (isBind) {
            Presenter.getInstance(getContext()).unbindStepService();
            isBind = false;
        }
    }

    public void popRedPkg(SponsorRedPkgResponse sponsorRedPkgResponse) {
        LocalLog.d(TAG, "popRedPkg() enter");
        String canRevPkg = "";
        popRedPkgView = View.inflate(getContext(), R.layout.red_pkg_pop_window, null);
        totalRedPkg = (TextView) popRedPkgView.findViewById(R.id.total_red_pkg);
        redPkgRecycler = (RecyclerView) popRedPkgView.findViewById(R.id.red_pkg_recycler);
        openRedPkgView = (ImageView) popRedPkgView.findViewById(R.id.open_red_pkg);
        RelativeLayout relativeLayout = (RelativeLayout) popRedPkgView.findViewById(R.id.cancel_red_span);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupRedPkgWindow.dismiss();
            }
        });
        popupRedPkgWindow = new PopupWindow(popRedPkgView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        errorTextView = (TextView) popRedPkgView.findViewById(R.id.error_text);
        desPkgTextView = (TextView) popRedPkgView.findViewById(R.id.des_pkg);
        popupRedPkgWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                redPkgEnable = true;
                popupRedPkgWindow = null;
            }
        });
        popupRedPkgWindow.setFocusable(true);
        popupRedPkgWindow.setOutsideTouchable(true);
        popupRedPkgWindow.setBackgroundDrawable(new BitmapDrawable());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        redPkgRecycler.setLayoutManager(layoutManager);
        ArrayList<?> sponsorData = new ArrayList<>();
        if (sponsorRedPkgResponse.getData().getLedredpacket() != null) {
            if (sponsorRedPkgResponse.getData().getLedredpacket().size() > 0) {
                sponsorData.addAll((ArrayList) sponsorRedPkgResponse.getData().getLedredpacket());
            }
        }
        if (sponsorRedPkgResponse.getData().getCanredpacket() != null) {
            /*if (sponsorRedPkgResponse.getData().getCanredpacket().size() > 0) {
                sponsorData.addAll((ArrayList) sponsorRedPkgResponse.getData().getCanredpacket());
            }*/
            int size = sponsorRedPkgResponse.getData().getCanredpacket().size();
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    canRevPkg += sponsorRedPkgResponse.getData().getCanredpacket().get(i).getRed_id();
                } else {
                    canRevPkg += sponsorRedPkgResponse.getData().getCanredpacket().get(i).getRed_id() + ",";
                }
            }
            sponsorData.addAll((ArrayList) sponsorRedPkgResponse.getData().getCanredpacket());
        }

        if (sponsorRedPakAdapter == null) {
            sponsorRedPakAdapter = new SponsorRedPakAdapter(getContext(), sponsorData);
        }
        redPkgRecycler.setAdapter(sponsorRedPakAdapter);
        animationCircleType = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
                1, Animation.RELATIVE_TO_PARENT, 0);
        animationCircleType.setInterpolator(new AccelerateInterpolator());
        animationCircleType.setDuration(200);
        final String redids = canRevPkg;
        openRedPkgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalLog.d(TAG, "领取商户红包");
                final Rotate3dAnimation animation = new Rotate3dAnimation(0, 359, view.getWidth() / 2f, view.getHeight() / 2f, 30, true);
                animation.setDuration(500);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setFillAfter(true);
                view.setAnimation(animation);
                view.startAnimation(animation);
                LocalLog.d(TAG, "可领红包的 " + redids);
                if (TextUtils.isEmpty(redids)) {
                    Toast.makeText(getContext(), "没有可领取的红包", Toast.LENGTH_SHORT).show();
                }
                RedPkgRecParam redPkgRecParam = new RedPkgRecParam().setRedids(redids);
                Presenter.getInstance(getContext()).postRedPkgRec(redPkgRecParam, innerRecRedCallBack);
            }
        });
        popupRedPkgWindow.showAtLocation(getActivity().findViewById(R.id.home_page), Gravity.CENTER, 0, 0);

        popRedPkgView.startAnimation(animationCircleType);
    }

    private final InnerCallBack innerRecRedCallBack = new InnerCallBack() {
        @Override
        public void innerCallBack(Object object) {
            openRedPkgView.clearAnimation();
            openRedPkgView.setVisibility(View.INVISIBLE);
            if (object instanceof ErrorCode) {
                LocalLog.d(TAG, "领取红包出错" + ((ErrorCode) object).getMessage());
            } else if (object instanceof RecRedPkgResponse) {
                if (((RecRedPkgResponse) object).getError() == 0) {
                    if (((RecRedPkgResponse) object).getData().getAllmoney() > 0.0f) {
                        totalRedPkg.setVisibility(View.VISIBLE);
                        totalRedPkg.setText(String.valueOf(((RecRedPkgResponse) object).getData().getAllmoney()));
                    } else {
                        errorTextView.setVisibility(View.VISIBLE);
                    }
                    desPkgTextView.setVisibility(View.VISIBLE);
                    ArrayList<?> sponsorData = new ArrayList<>();
                    sponsorData.addAll((ArrayList) ((RecRedPkgResponse) object).getData().getResult());
                    if (sponsorRedPakAdapter != null) {
                        sponsorRedPakAdapter.notifyDataSetChanged(sponsorData);
                    }
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
        textView.setText(titleContent);
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
                popupRedPkgWindow.dismiss();
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
    public void responseMonthIncome(IncomeResponse incomeResponse) {
        LocalLog.d(TAG, "responseMonthIncome() enter " + incomeResponse.toString());
        if (incomeResponse.getError() == 0) {
            String moneyFormat = getContext().getResources().getString(R.string.month_income);
            String moneyStr = String.format(moneyFormat, incomeResponse.getData().getTotal_amount());
            LocalLog.d(TAG, "responseMonthIncome() " + moneyStr);
            monthIncomeHome.setText(moneyStr);
        } else if (incomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    public void drawProcess(int target, final int currentStep) {
        float angle = (float) currentStep * 360.0f / target;
        if (angle > 360.0f) {
            angle = 360.0f;
        }
        final float angelProcess = angle;
        if (canDrawProcess) {
            targetCircle.post(new Runnable() {
                @Override
                public void run() {
                    int[] location = new int[2];
                    processStepNow.getLocationOnScreen(location);
                    processStepNow.setImageDrawable(new StepProcessDrawable(getContext(), location[0], location[1], processStepNow.getWidth(), processStepNow.getHeight(),
                            targetCircle.getWidth(), targetCircle.getHeight()).setmAngle(angelProcess));
                }
            });
            canDrawProcess = false;
        }
    }

    @Override
    public void responseStepToday(int stepToday) {
        if (lastStep >= stepToday) {
            return;
        }
        lastStep = stepToday;
        LocalLog.d(TAG, "responseStepToday() enter" + stepToday + ",lastStep" + lastStep);
        if (toayStep != null) {
            toayStep.setText(String.valueOf(stepToday));
            int targetStep = Presenter.getInstance(getContext()).getTarget(getContext());
            targetSteps.setText(String.valueOf(targetStep));
            if (targetStep > 0) {
                drawProcess(targetStep, lastStep);
            }
        }
        LocalLog.d(TAG, "stepToday = " + stepToday);
        Message messageNet = Message.obtain();
        messageNet.what = MSG_UPDATE_STEP;
        messageNet.arg1 = stepToday;
        updateHandler.sendMessageDelayed(messageNet, 10000);
        updateHandler.sendEmptyMessageDelayed(MSG_UPDATE_STEP_LOCAL, 500);

    }

    @Override
    public void responseTarget(int personTarget) {
        LocalLog.d(TAG, "responseTarget() enter");
    }

    @Override
    public void responseTodayIncome(IncomeResponse incomeResponse) {
        if (incomeResponse.getError() == 0) {
            LocalLog.d(TAG, "responseTodayIncome() enter " + incomeResponse.toString());
            String moneyFormat = getContext().getResources().getString(R.string.today_income);
            String moneyStr = String.format(moneyFormat, incomeResponse.getData().getTotal_amount());
            todayIncomeNum.setText(moneyStr);
        } else if (incomeResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }

    }

    @Override
    public void responseWeather(WeatherResponse weatherResponse) {
        LocalLog.d(TAG, "responseWeather() enter" + weatherResponse.toString());
        if (wendu == null) {
            return;
        }
        if (weatherResponse.getError() == 0) {
            if (weatherResponse.getData() != null) {
                wendu.setText(weatherResponse.getData().getTemp() + "°");
                weatherIcon.setImageResource(weatherMap.get(weatherResponse.getData().getImg()));
            }
        } else if (weatherResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }


    }

    @Override
    public void response(PostUserStepResponse postUserStepResponse) {
        LocalLog.d(TAG, "PostUserStepResponse() enter");

    }

    @Override
    public void response(SponsorRedPkgResponse sponsorRedPkgResponse) {
        LocalLog.d(TAG, "SponsorRedPkgResponse() enter " + sponsorRedPkgResponse.toString());
        if (sponsorRedPkgResponse.getError() == 0) {
            popRedPkg(sponsorRedPkgResponse);
        } else if (sponsorRedPkgResponse.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        } else {
            Toast.makeText(getContext(), sponsorRedPkgResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
        redPkgEnable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Presenter.getInstance(getContext()).dispatchUiInterface(this);
        if (popupRedPkgWindow != null) {
            popupRedPkgWindow.dismiss();
            popupRedPkgWindow = null;
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.out_red_pkg_image:
                    LocalLog.d(TAG, "领红包");
                    if (redPkgEnable) {
                        redPkgEnable = false;
                        Presenter.getInstance(getContext()).getSponsorRedPkg();
                    }
                    break;
                case R.id.create_circle_image:
                    LocalLog.d(TAG, "发红包");
                    startActivity(TaskReleaseActivity.class, null);
                    break;
                case R.id.income_red_pkg_image:
                    LocalLog.d(TAG, "创建圈子");
                    startActivity(CreateCircleActivity.class, null);
                    break;

                case R.id.add_friend_image:
                    LocalLog.d(TAG, "邀请好友");
                    startActivity(InviteActivity.class, null);
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


    private void popTargetDesc() {

    }

    @OnClick(R.id.today_income_num)
    public void onClick() {
    }

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
                        Presenter.getInstance(homePageFragment.getContext()).refreshStep();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void response(ErrorCode errorCode) {
        if (errorCode.getError() == -100) {
            LocalLog.d(TAG, "Token 过期!");
            Presenter.getInstance(getContext()).setId(-1);
            Presenter.getInstance(getContext()).steLogFlg(false);
            Presenter.getInstance(getContext()).setToken(getContext(), "");
            getActivity().finish();
            System.exit(0);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if ("target".equals(key)) {
            canDrawProcess = true;
            LocalLog.d(TAG, "用户目标值改变");
            int target = Presenter.getInstance(getContext()).getTarget(getContext());
            targetSteps.setText(String.valueOf(target));
            drawProcess(target, lastStep);
        }
    }
}
