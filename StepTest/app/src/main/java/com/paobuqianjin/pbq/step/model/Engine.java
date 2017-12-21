package com.paobuqianjin.pbq.step.model;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.paobuqianjin.pbq.step.data.bean.gson.CircleType;
import com.paobuqianjin.pbq.step.data.netcallback.ListCircleCallBack;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;

import java.util.List;

import okhttp3.Call;

import static com.paobuqianjin.pbq.step.utils.NetApi.urlFindPassWord;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlNearByPeople;
import static com.paobuqianjin.pbq.step.utils.NetApi.urlRegisterPhone;

/**
 * Created by pbq on 2017/11/29.
 */

public final class Engine {
    private final static String TAG = Engine.class.getSimpleName();
    private static Engine engine;
    private static Context mContext;
    private StepServerConnection connection = new StepServerConnection();
    private Messenger messenger;
    private Messenger serviceMessenger = new Messenger(new ServiceHandler());
    private final static int MSG_SEND_DATA_TO_SETP_SERVICE = 0;
    private final static int MSG_SEND_DATA_TO_ENGINE = 1;

    private Engine() {

    }

    public static Engine getEngine(Context context) {
        mContext = context;
        if (engine == null) {
            engine = new Engine();
        }
        return engine;
    }

    public void startService(String action, Class<? extends Service> clazz) {
        LocalLog.d(TAG, "startService()  "
                + clazz.getSimpleName() + ",ACTION = " + action);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClass(mContext, clazz);
        mContext.startService(intent);
    }

    public void stopService(Class<? extends Service> clazz) {
        Intent intent = new Intent();
        intent.setClass(mContext, clazz);
        mContext.stopService(intent);
    }

    public void bindService(String action, Class<? extends Service> clazz) {
        LocalLog.d(TAG, "bindService() enter");
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setClass(mContext, clazz);
        mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void unbindStepService() {
        LocalLog.d(TAG, "unbindStepService() enter");
        mContext.unbindService(connection);
    }

    private class StepServerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalLog.d(TAG, "onServiceConnected() = " + componentName);
            if (iBinder != null) {
                messenger = new Messenger(iBinder);
                sendToService(new Bundle(), MSG_SEND_DATA_TO_SETP_SERVICE);
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LocalLog.d(TAG, "onServiceDisconnected() 断开连接！");
        }
    }

    public void sendToService(Bundle bundle, int what) {
        LocalLog.d(TAG, "sendBundleToService() enter");
        if (messenger != null) {
            Message msg = Message.obtain();
            msg.what = what;
            bundle.putString("Engine", "Engine -> StepService");
            msg.setData(bundle);
            msg.replyTo = serviceMessenger;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                LocalLog.d(TAG, "sendToService() failed");
                e.printStackTrace();
            } finally {

            }
        }

    }


    public boolean getLogFlag(Context context) {
        return FlagPreference.getLoginFlag(context);
    }

    public String getStartSportTime(Context context) {
        return FlagPreference.getEffectStartSportTime(context);
    }

    public void setStartServiceTime(Context context, String startServiceTime) {
        FlagPreference.setStartServiceTime(context, startServiceTime);
    }

    public void getUserInfo() {
        LocalLog.d(TAG, "getUserInfo() enter");
        int id = 0;
        String url = NetApi.urlUser + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack());
    }

    //重置密码
    public void refreshPassWorld() {
        LocalLog.d(TAG, "findPassWorld() enter");
        String url = urlFindPassWord + "13424156029";
        OkHttpUtils
                .put()
                .url(urlFindPassWord)
                .param("code", "123456")
                .param("password", "23dasdd")
                .build()
                .execute(new NetStringCallBack());
    }

    //手机号码注册
    public void registerByPhoneNumber() {
        LocalLog.d(TAG, "registerByPhoneNumber() enter");
        OkHttpUtils
                .post()
                .url(urlRegisterPhone)
                .addParams("mobile", "13424156029")
                .addParams("password", "123456")
                .addParams("code", "123456")
                .build()
                .execute(new NetStringCallBack());
    }

    //获取附近的人
    public void getNearByPeople() {
        LocalLog.d(TAG, "urlNearByPeople() enter");
        OkHttpUtils
                .get()
                .url(urlNearByPeople)
                .build()
                .execute(new NetStringCallBack());
    }

    //获取验证码
    public void getMsg() {
        LocalLog.d(TAG, "getMsg() enter");
        String url = NetApi.urlSendMsg + "18276810059";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack());
    }

    public void userLoginByPhoneNumber() {
        LocalLog.d(TAG, "userLoginByPhoneNumber() enter");
        OkHttpUtils
                .post()
                .url(NetApi.urlUserLogin)
                .addParams("mobile", "13424156029")
                .addParams("password", "123456")
                .build()
                .execute(new NetStringCallBack());
    }

    /*@desc
    *@function getCircleType
    *@param
    *@return
    */
    public void getCircleType() {
        LocalLog.d(TAG, "getCircleType() enter");
        OkHttpUtils
                .get()
                .url(NetApi.urlCircleType)
                .build()
                .execute(new ListCircleCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        LocalLog.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onResponse(List<CircleType.DataBean> dataBeans, int i) {
                        for (int j = 0; j < dataBeans.size(); j++) {
                            LocalLog.d(TAG, "getCircleType() 圈子类型数据:" + dataBeans.toString());
                        }
                    }
                });
    }

    private static class ServiceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SEND_DATA_TO_ENGINE:
                    LocalLog.d(TAG, "handleMessage() enter" + msg.what + " bundle.data ->" + msg.getData().getString("StepService"));
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
