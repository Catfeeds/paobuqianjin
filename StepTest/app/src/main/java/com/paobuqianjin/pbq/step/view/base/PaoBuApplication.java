package com.paobuqianjin.pbq.step.view.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.l.okhttppaobu.okhttp.https.HttpsUtils;
import com.l.okhttppaobu.okhttp.log.LoggerInterceptor;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.customview.NormalDialog;
import com.paobuqianjin.pbq.step.data.bean.gson.response.CurrentStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.netcallback.PaoCallBack;
import com.paobuqianjin.pbq.step.model.services.RedCheckService;
import com.paobuqianjin.pbq.step.model.services.baidu.LocationService;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.model.services.local.StepService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.utils.PaoToastUtils;
import com.paobuqianjin.pbq.step.utils.RongYunChatUtils;
import com.paobuqianjin.pbq.step.utils.Utils;
import com.paobuqianjin.pbq.step.view.activity.LoginActivity;
import com.paobuqianjin.pbq.step.view.activity.MainActivity;
import com.paobuqianjin.pbq.step.view.emoji.IImageLoader;
import com.paobuqianjin.pbq.step.view.emoji.LQREmotionKit;
import com.tencent.bugly.Bugly;
import com.today.step.lib.TodayStepManager;
import com.today.step.lib.TodayStepService;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.xiaomi.MiPushRegistar;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.ipc.RongExceptionHandler;
import io.rong.push.RongPushClient;
import io.rong.push.common.RongException;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.cache.InternalCache;

/**
 * Created by pbq on 2017/12/14.
 */

public class PaoBuApplication extends MultiDexApplication {
    private final static String TAG = PaoBuApplication.class.getSimpleName();
    private static final String XIAOMI_ID = "2882303761517807651";
    private static final String XIAOMI_KEY = "5331780797651";
    public static final String MEIZU_ID = "1000750";
    public static final String MEIZU_KEY = "b7afc36952cc4167af1bd6d27b5a7354";
    public LocationService locationService;
    private static boolean isAsyncRun = false;
    private static final long cacheSize = 1024 * 1024 * 200;
    private static PaoBuApplication sApplication;
    private int appCount = 0;
    private final static String START_STEP_ACTION = "com.paobuqianjin.step.START_STEP_ACTION";
    private Activity currentActivity = null;
    private NormalDialog exitDialog;
    private int timeCheck = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
        initTencentBugly();
        initHttpOk();
        initSDKService();
        initHlb();
        initUmeng();
        initRongYunChat();
        sApplication = this;
        initBroadcastReceiver();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                currentActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        LQREmotionKit.init(this, new IImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
    }

    private void initData() {
        try {
            if (NetApi.url.equals(NetApi.url_online)) {//正式环境
                String onlineAppId = "8luwapkv8jy1l";
                ApplicationInfo appInfo = null;
                appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                String msg = appInfo.metaData.getString("RONG_CLOUD_APP_KEY");

                if (msg.equals(onlineAppId)) return;
                Log.e(TAG, "before: " + msg);
                appInfo.metaData.putString("RONG_CLOUD_APP_KEY", onlineAppId);
                msg = appInfo.metaData.getString("RONG_CLOUD_APP_KEY");
                Log.e(TAG, "after: " + msg);


            } else {
                String debugAppId = "uwd1c0sxup861";
                ApplicationInfo appInfo = null;
                appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                String msg = appInfo.metaData.getString("RONG_CLOUD_APP_KEY");

                if (msg.equals(debugAppId)) return;
                Log.e(TAG, "before: " + msg);
                appInfo.metaData.putString("RONG_CLOUD_APP_KEY", debugAppId);
                msg = appInfo.metaData.getString("RONG_CLOUD_APP_KEY");
                Log.e(TAG, "after: " + msg);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initRongYunChat() {

        RongPushClient.registerHWPush(this);
        RongPushClient.registerMiPush(this, XIAOMI_ID, XIAOMI_KEY);
        RongPushClient.registerMZPush(this, MEIZU_ID, MEIZU_KEY);
//        try {
//            RongPushClient.registerFCM(this);
//        } catch (RongException e) {
//            e.printStackTrace();
//        }
        RongIM.init(this);
        Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(RongIMClient.ConnectionStatusListener.ConnectionStatus status) {
                LocalLog.d(TAG, "ConnectionStatusListener onChanged: " + status);
                switch (status) {
                    case CONNECTED://连接成功。

                        break;
                    case DISCONNECTED://断开连接。

                        break;
                    case CONNECTING://连接中。

                        break;
                    case NETWORK_UNAVAILABLE://网络不可用。

                        break;
                    case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线

                        break;
                }
            }
        });

        RongYunChatUtils.init(this);
    }

    private void initBroadcastReceiver() {
        LocalLog.d(TAG, "初始化红包检查");
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                    LocalLog.d(TAG, "获取红包消息。。。。。");
                    try {
                        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
                        if (!tasks.isEmpty() || appCount <= 0) {
                            timeCheck++;
                            if (timeCheck % 9 < 9) {
                                return;
                            } else {
                                ComponentName topActivity = tasks.get(0).topActivity;
                                if (!topActivity.getPackageName().equals(context.getPackageName())) {
                                    LocalLog.d(TAG, "处于后台模式");
                                    //上一次上报的时间戳
                                    Intent intentCheck = new Intent(context, RedCheckService.class);
                                    context.startService(intentCheck);
                                }
                                timeCheck = 0;
                            }
                        }
                    } catch (Exception c) {
                        c.printStackTrace();
                    }
                }
            }
        };
        registerReceiver(mBatInfoReceiver, filter);

    }

    private void initUmeng() {
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "b60a2f688d8f367430384085199ba353");
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LocalLog.d(TAG, "IUmengRegisterCallback.onSuccess() deviceToken : " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LocalLog.d(TAG, "IUmengRegisterCallback.onFailure() s : " + s + "   ;s1:" + s1);
            }
        });


        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                LocalLog.d(TAG, new Gson().toJson(msg));
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
//                         //对于自定义消息，PushSDK默认只统计送达。若开发者需要统计点击和忽略，则需手动调用统计方法。
//                        boolean isClickOrDismissed = true;
//                        if(isClickOrDismissed) {
                        //自定义消息的点击统计
                        UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
//                        } else {
//                            //自定义消息的忽略统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
//                        }
//                            PaoToastUtils.showShortToast(getApplicationContext(), msg.custom);

                        if (!TextUtils.isEmpty(msg.custom)) {
                            try {
                                // TODO: 推送处理动作
                                JSONObject jsonObject = new JSONObject(msg.custom);
                                String is_pull_step_service = jsonObject.getString("pull_service");
                                if (is_pull_step_service != null) {
                                    switch (is_pull_step_service) {
                                        case "step":
                                            if (!Utils.isServiceRunning(PaoBuApplication.getApplication(), TodayStepService.class.getName())) {
                                                Intent intent = new Intent();
                                                intent.setAction(START_STEP_ACTION);
                                                intent.setClass(getApplicationContext(), TodayStepService.class);
                                                TodayStepManager.init(PaoBuApplication.getApplication(), intent);
                                            }
                                            break;

                                        case "login":
                                            if (exitDialog != null && exitDialog.isShowing())
                                                exitDialog.dismiss();

                                            String tipsMsg = jsonObject.getString("msg");
                                            if (TextUtils.isEmpty(tipsMsg)) {
                                                tipsMsg = "登录过期，点击确定重新登录";
                                            }
                                            if (currentActivity == null) return;
                                            exitDialog = new NormalDialog(currentActivity);
                                            exitDialog.setMessage(tipsMsg);
                                            exitDialog.setSingleBtn(true);
                                            exitDialog.setYesOnclickListener(currentActivity.getString(R.string.confirm_yes), new NormalDialog.onYesOnclickListener() {
                                                @Override
                                                public void onYesClick() {
                                                    exitDialog.dismiss();
                                                    Presenter.getInstance(currentActivity).setId(-1);
                                                    Presenter.getInstance(currentActivity).steLogFlg(false);
                                                    Presenter.getInstance(currentActivity).setToken(currentActivity, "");

                                                    if (currentActivity != null) {
                                                        Intent intent = new Intent(currentActivity, MainActivity.class);
                                                        intent.setAction("login_other_phone");
                                                        startActivity(intent);
                                                    }
                                                }
                                            });

                                            //exitDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                                            exitDialog.show();
                                            break;
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                return super.getNotification(context, uMessage);
            }

            //            @Override
//            public void dealWithNotificationMessage(Context context, UMessage uMessage) {
//                //调用super则会走通知展示流程，不调用super则不展示通知
//                super.dealWithNotificationMessage(context, uMessage);
//            }
        };
        mPushAgent.setMessageHandler(messageHandler);
        MiPushRegistar.register(this, XIAOMI_ID, XIAOMI_KEY);
        HuaWeiRegister.register(this);
        MeizuRegister.register(this, MEIZU_ID, MEIZU_KEY);

    }

    private void initHlb() {
//        if(Looper.getMainLooper() == Looper.myLooper())
//        {
//            HLSDK.init(this);
//        }
    }


    /**
     * app是否在前台
     *
     * @return true前台，false后台
     */
    public boolean isForeground() {
        return appCount > 0;
    }

    public static PaoBuApplication getApplication() {
        return sApplication;
    }

    private void initTencentBugly() {
        LocalLog.d(TAG, "initTencentBugly() enter");
        Bugly.init(getApplicationContext(), "a7472c9378", false);
    }

    private boolean initBaiDuSDK(Context context) {
        LocalLog.d(TAG, "initBaiDuSDK() enter");
        locationService = new LocationService(context);
        SDKInitializer.initialize(getApplicationContext());
        return true;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    private boolean initWXapi(Context context) {
        Config.DEBUG = true;
        PlatformConfig.setWeixin("wx1ed4ccc9a2226a73", "b1398e4064b5ea28549201f43965c1dc");
        PlatformConfig.setQQZone("1106825696", "4An0iHCeY1xfvRS7");
        return true;
    }

    /*@desc 检测或启动计算步数的后台服务
  *@function initSDKService
  *@param
  *@return
  */
    protected void initSDKService() {
        if (!isAsyncRun) {
            LocalLog.d(TAG, "isAsyncRun is " + isAsyncRun + " and run initSDKService()");
            new PaoBuApplication.DetectThread(this).start();
        }
        initBaiDuSDK(this);
        isAsyncRun = true;
    }

    private static class DetectThread extends Thread {
        WeakReference<PaoBuApplication> application;
        InnerCallBack innerCallBack = new InnerCallBack() {
            @Override
            public void innerCallBack(Object object) {
                final PaoBuApplication app = application.get();
                if (object instanceof ErrorCode) {

                } else if (object instanceof CurrentStepResponse) {
                    LocalLog.d(TAG, "CurrentStepResponse  " + object.toString());
                    Intent intent = new Intent();
                    if (((CurrentStepResponse) object).getError() == 0 && ((CurrentStepResponse) object).getData() != null) {
                        intent.putExtra("today_step", ((CurrentStepResponse) object).getData().getStep_number());
                    }
                    intent.setAction(START_STEP_ACTION);
                    intent.setClass(app, TodayStepService.class);
                    TodayStepManager.init(app, intent);
                }
            }
        };

        DetectThread(PaoBuApplication application) {
            this.application = new WeakReference<PaoBuApplication>(application);
        }

        @Override
        public void run() {
            Looper.prepare();
            final PaoBuApplication app = application.get();
            if (app != null) {
                UMShareAPI.get(app);
                LocalLog.d(TAG, "DetectThread run() 初始化网络、计步服务、定位SDK、三方登陆注册、三方支付SDK等");
                app.initWXapi(app);
                /*boolean netAccess = Presenter.getInstance(app).getCurrentStep(innerCallBack);
                * 暂时不做数据融合显示，只显示本手机的步数*/

                LocalLog.d(TAG, "未登录无网络");
                Intent intent = new Intent();
                intent.setAction(START_STEP_ACTION);
                intent.setClass(app, TodayStepService.class);
                TodayStepManager.init(app, intent);

            }
        }
    }

    public class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
/*            LocalLog.d(TAG, "intercept() enter" + response.toString());
            *//*在此处定义缓存策略，图片缓存，信息缓存，验证码缓存.....,按链接性质过滤,选择缓存首页信息一段时间*/
            LocalLog.d(TAG, "String url =" + request.url());
            LocalLog.d(TAG, "token =" + request.header("headtoken"));
            long max_age = 24 * 3600;
            if (request.url().toString().startsWith(NetApi.url)) {
/*                LocalLog.d(TAG, "request.url().toString() = " + request.url().toString());*/
                max_age = 0;
                Response response1 = response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        //cache for 30 days
                        .header("Cache-Control", "max-age=" + String.valueOf(max_age)) //缓存60 秒，在60秒内不会重新访问网络只会访问缓存
                        .build();
                return response1;
            } else {
                LocalLog.d(TAG, "IMG....");
                return response;
            }
/*            LocalLog.d(TAG, "max_age = " + max_age);*/

        }
    }

    private File getDiskCacheDir(Context context) {
        File cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir();
            LocalLog.d(TAG, "getExternalCachdir() = " + cachePath);
        } else {
            cachePath = context.getCacheDir();
            LocalLog.d(TAG, "getCacheDir() = " + cachePath);
        }
        return cachePath;
    }

    private void initHttpOk() {
        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        File fileCache = getDiskCacheDir(this);
        Cache cache = null;
        if (fileCache != null) {
            cache = new Cache(getDiskCacheDir(this), cacheSize);
        }
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = null;
        if (cache != null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60000L, TimeUnit.MILLISECONDS)
                    .readTimeout(60000L, TimeUnit.MILLISECONDS)
                    .addInterceptor(new LoggerInterceptor("TAG"))
                    .cookieJar(cookieJar1)
                    .addNetworkInterceptor(new CacheInterceptor())
                    .cache(cache)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
        } else {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60000L, TimeUnit.MILLISECONDS)
                    .readTimeout(60000L, TimeUnit.MILLISECONDS)
                    .addInterceptor(new LoggerInterceptor("TAG"))
                    .cookieJar(cookieJar1)
                    .addNetworkInterceptor(new CacheInterceptor())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
        }
        OkHttpUtils.initClient(okHttpClient);
        LocalLog.d(TAG, "initHttpOk()  leave");
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }
}
