package com.paobuqianjin.pbq.step.view.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Looper;

import com.baidu.mapapi.SDKInitializer;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.l.okhttppaobu.okhttp.https.HttpsUtils;
import com.l.okhttppaobu.okhttp.log.LoggerInterceptor;
import com.paobuqianjin.pbq.step.model.services.baidu.LocationService;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.model.services.local.StepService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;
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

public class PaoBuApplication extends Application {
    private final static String TAG = PaoBuApplication.class.getSimpleName();
    public LocationService locationService;
    private static boolean isAsyncRun = false;
    private static final long cacheSize = 1024 * 1024 * 60;

    @Override
    public void onCreate() {
        super.onCreate();
        initHttpOk();
        initSDKService();
    }

    private boolean initBaiDuSDK(Context context) {
        LocalLog.d(TAG, "initBaiDuSDK() enter");
        locationService = new LocationService(context);
        SDKInitializer.initialize(getApplicationContext());
        return true;
    }

    private boolean initWXapi(Context context) {
        Config.DEBUG = true;
        PlatformConfig.setWeixin("wx1ed4ccc9a2226a73", "b1398e4064b5ea28549201f43965c1dc");
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
        isAsyncRun = true;
    }

    private static class DetectThread extends Thread {
        WeakReference<PaoBuApplication> application;

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
                Presenter.getInstance(app).startService(StepService.START_STEP_ACTION, StepService.class);
                app.initBaiDuSDK(app);
                Presenter.getInstance(app).startService(null, LocalBaiduService.class);
                app.initWXapi(app);
            }
        }
    }

    public class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            LocalLog.d(TAG, "intercept() enter" + response.toString());
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for 30 days
                    .header("Cache-Control", "max-age=" + 1800 * 1)
                    .build();
            return response1;
        }
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
