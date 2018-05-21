package com.paobuqianjin.pbq.step.view.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;
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
import com.paobuqianjin.pbq.step.data.bean.gson.response.CurrentStepResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.model.services.baidu.LocationService;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.model.services.local.StepService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.InnerCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;
import com.paobuqianjin.pbq.step.view.emoji.IImageLoader;
import com.paobuqianjin.pbq.step.view.emoji.LQREmotionKit;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.today.step.lib.TodayStepManager;
import com.today.step.lib.TodayStepService;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

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
    public LocationService locationService;
    private static boolean isAsyncRun = false;
    private static final long cacheSize = 1024 * 1024 * 200;
    private static PaoBuApplication sApplication;
    private int appCount = 0;
    private final static String START_STEP_ACTION = "com.paobuqianjin.step.START_STEP_ACTION";

    @Override
    public void onCreate() {
        super.onCreate();
        initTencentBugly();
        initHttpOk();
        initSDKService();
        sApplication = this;

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
        Bugly.init(getApplicationContext(), "a7472c9378", true);
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
                app.loadCitySelect(app);
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
            LocalLog.d(TAG, "intercept() enter" + response.toString());
            /*在此处定义缓存策略，图片缓存，信息缓存，验证码缓存.....,按链接性质过滤,选择缓存首页信息一段时间*/
            LocalLog.d(TAG, "String url =" + request.url());
            long max_age = 30 * 24 * 3600;
            if (request.url().toString().startsWith(NetApi.url)) {
                LocalLog.d(TAG, "request.url().toString() = " + request.url().toString());
                max_age = 0;
            }
            LocalLog.d(TAG, "max_age = " + max_age);
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for 30 days
                    .header("Cache-Control", "max-age=" + String.valueOf(max_age)) //缓存60 秒，在60秒内不会重新访问网络只会访问缓存
                    .build();
            return response1;
        }
    }

    private void loadCitySelect(Context context) {
        LocalLog.d(TAG, "加载城市选择控件");
        CityPickerView.getInstance().init(context);
    }

    private File getDiskCacheDir(Context context) {
        File cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir();
            LocalLog.d(TAG, "getExternalCachdir() = " + cachePath.getPath());
        } else {
            cachePath = context.getCacheDir();
            LocalLog.d(TAG, "getCacheDir() = " + cachePath.getPath());
        }
        return cachePath;
    }

    private void initHttpOk() {
        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        Cache cache = new Cache(getDiskCacheDir(this), cacheSize);
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        LocalLog.d(TAG, "Cache: " + getDiskCacheDir(this).getPath());
//        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
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
        OkHttpUtils.initClient(okHttpClient);
        LocalLog.d(TAG, "initHttpOk()  leave");
    }
}
