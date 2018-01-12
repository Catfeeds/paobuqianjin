package com.paobuqianjin.pbq.step.view.base;

import android.app.Application;
import android.content.Context;
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
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by pbq on 2017/12/14.
 */

public class PaoBuApplication extends Application {
    private final static String TAG = PaoBuApplication.class.getSimpleName();
    public LocationService locationService;
    private static boolean isAsyncRun = false;

    @Override
    public void onCreate() {
        super.onCreate();
        initSDKService();
    }

    private boolean initBaiDuSDK(Context context) {
        LocalLog.d(TAG, "initBaiDuSDK() enter");
        locationService = new LocationService(context);
        SDKInitializer.initialize(getApplicationContext());
        return true;
    }

    private boolean initWXapi(Context context) {
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
                app.initHttpOk();
                Presenter.getInstance(app).startService(StepService.START_STEP_ACTION, StepService.class);
                app.initBaiDuSDK(app);
                Presenter.getInstance(app).startService(null, LocalBaiduService.class);
                app.initWXapi(app);
            }
        }
    }


    private void initHttpOk() {
        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

//        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar1)
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
