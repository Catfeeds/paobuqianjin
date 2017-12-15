package com.paobuqianjin.pbq.step.view.base;

import android.app.Application;
import android.content.Context;
import android.os.Looper;

import com.baidu.mapapi.SDKInitializer;
import com.paobuqianjin.pbq.step.model.services.baidu.LocationService;
import com.paobuqianjin.pbq.step.model.services.local.LocalBaiduService;
import com.paobuqianjin.pbq.step.model.services.local.StepService;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.lang.ref.WeakReference;

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
                LocalLog.d(TAG, "DetectThread() service");
                Presenter.getInstance(app).startService(StepService.START_STEP_ACTION, StepService.class);
                app.initBaiDuSDK(app);
                Presenter.getInstance(app).startService(null, LocalBaiduService.class);
            }
        }
    }
}
