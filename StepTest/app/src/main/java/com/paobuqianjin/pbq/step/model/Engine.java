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
import android.widget.Toast;

import com.l.okhttppaobu.okhttp.OkHttpUtils;
import com.paobuqianjin.pbq.step.data.bean.gson.CreateCircleBodyParam;
import com.paobuqianjin.pbq.step.data.bean.gson.SignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.UserInfo;
import com.paobuqianjin.pbq.step.data.netcallback.NetStringCallBack;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiCreateCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;
import com.paobuqianjin.pbq.step.utils.NetApi;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

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
    private LoginCallBackInterface loginCallBackInterface;
    private UiCreateCircleInterface uiCreateCircleInterface;
    public final static int COMMAND_REQUEST_SIGN = 0;
    public final static int COMMAND_REG_BY_PHONE = 1;
    public final static int COMMAND_LOGIN_IN = 2;
    public final static int COMMAND_REFRESH_PASSWORD = 3;
    public final static int COMMAND_NEARBY_PEOPLE = 4;
    //
    private final static int COMMAND_CREATE_CIRCLE = 5;

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

    public void setLogFlag(Context context, boolean isLogin) {
        FlagPreference.setLoginFlag(context, isLogin);
    }

    public String getStartSportTime(Context context) {
        return FlagPreference.getEffectStartSportTime(context);
    }

    public void setStartServiceTime(Context context, String startServiceTime) {
        FlagPreference.setStartServiceTime(context, startServiceTime);
    }

    public void getUserInfo(final int userId) {
        LocalLog.d(TAG, "getUserInfo() enter");
        String url = NetApi.urlUser + String.valueOf(userId);
        LocalLog.d(TAG, "getUserInfo() url = " + url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(new UserInfoInterface() {
                    @Override
                    public void update(UserInfo userInfo) {
                        LocalLog.d(TAG, " 获取用户信息回调成功!" + userInfo.toString());
                    }
                }));
    }

    //重置密码
    public void refreshPassWorld() {
        LocalLog.d(TAG, "findPassWorld() enter");
        String url = urlFindPassWord + "13424156029";
        OkHttpUtils
                .put()
                .url(url)
                .requestBody(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/x-www-form-urlencoded");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                })
                .param("password", "1223434")
                .param("code", "123456")
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_REFRESH_PASSWORD));
    }

    //手机号码注册
    public void registerByPhoneNumber(String[] userInfo) {
        LocalLog.d(TAG, "registerByPhoneNumber() enter");
        if (userInfo[0] == null && !isPhone(userInfo[0])) {
            Toast.makeText(mContext, "请输入一个手机号码:", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userInfo[0] == null) {
            Toast.makeText(mContext, "需要验证码:", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userInfo[2] == null) {
            Toast.makeText(mContext, "注册需要设置密码:", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(urlRegisterPhone)
                .addParams("mobile", userInfo[0])
                .addParams("password", userInfo[2])
                .addParams("code", userInfo[1])
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_REG_BY_PHONE));
    }

    //获取附近的人
    public void getNearByPeople() {
        LocalLog.d(TAG, "urlNearByPeople() enter");
        OkHttpUtils
                .get()
                .url(urlNearByPeople)
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_NEARBY_PEOPLE));
    }

    //获取验证码
    public void getMsg(String phone) {
        LocalLog.d(TAG, "getMsg() enter phone =" + phone);
        if (!isPhone(phone)) {
            Toast.makeText(mContext, "请输入一个手机号码:", Toast.LENGTH_SHORT).show();
            return;

        }
        String url = NetApi.urlSendMsg + phone;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new NetStringCallBack(new SignCodeCallBackInterface() {
                    @Override
                    public void signCodeCallBack(SignCodeResponse response) {
                        Toast.makeText(mContext, "验证码已发送，请查看短信！" + response.getMessage() + " data : " + response.getData(), Toast.LENGTH_SHORT).show();
                        if (loginCallBackInterface != null && loginCallBackInterface instanceof LoginSignCallbackInterface) {
                            // 设置验码
                            ((LoginSignCallbackInterface) loginCallBackInterface).requestPhoneSignCodeCallBack(response.getData());
                        }
                        return;
                    }
                }));
    }

    /**
     * 判断手机格式是否正确
     *
     * @param str 需要判断的字符串
     * @return 返回true说明字符串匹配成功
     */
    // Pattern类的作用在于编译正则表达式后创建一个匹配模式. Matcher类使用Pattern实例提供的模式信息对正则表达式进行匹配
    private boolean isPhone(String str) {
        // 将给定的正则表达式编译并赋予给Pattern类
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        // 对指定输入的字符串创建一个Matcher对象
        Matcher matcher = pattern.matcher(str);
        // 尝试对整个目标字符展开匹配检测,也就是只有整个目标字符串完全匹配时才返回真值.
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public void userLoginByPhoneNumber(String[] userInfo) {
        LocalLog.d(TAG, "userLoginByPhoneNumber() enter");
        if (!isPhone(userInfo[0])) {
            Toast.makeText(mContext, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userInfo[1] == null) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(NetApi.urlUserLogin)
                .addParams("mobile", userInfo[0])
                .addParams("password", userInfo[1])
                .build()
                .execute(new NetStringCallBack(loginCallBackInterface, COMMAND_LOGIN_IN));
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
                .execute(new NetStringCallBack(loginCallBackInterface) {
                });
    }

    //创建圈子
    public void createCircle(CreateCircleBodyParam createCircleBodyParam) {
        LocalLog.d(TAG, "createCircle() enter");
        OkHttpUtils
                .post()
                .url(NetApi.urlCircle)
                .params(createCircleBodyParam.getParams())
                .build()
                .execute(new NetStringCallBack(uiCreateCircleInterface,COMMAND_CREATE_CIRCLE));
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


    //call onResume
    public void attachUiInterface(LoginCallBackInterface uiCallBackInterface) {
        this.loginCallBackInterface = uiCallBackInterface;

    }

    //call onDestroy
    public void dispatchUiInterface() {
        this.loginCallBackInterface = null;
    }
}
