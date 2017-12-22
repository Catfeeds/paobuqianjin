package com.paobuqianjin.pbq.step.data.netcallback;

import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.data.bean.gson.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.SignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.SignUserResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.UserInfo;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by pbq on 2017/12/20.
 */

public class NetStringCallBack extends StringCallback {
    private final static String TAG = NetStringCallBack.class.getSimpleName();
    private CallBackInterface callBackInterface;
    private int command = -1;

    public NetStringCallBack(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    public NetStringCallBack(CallBackInterface callBackInterface, int command) {
        this.callBackInterface = callBackInterface;
        this.command = command;
    }

    @Override
    public void onError(Call call, Exception e, int i, Object response) {
        e.printStackTrace();
        if (response != null) {
            LocalLog.d(TAG, "onError() enter" + response.toString());

        }
    }

    @Override
    public void onResponse(String s, int i) {
        LocalLog.d(TAG, "onResponse() enter  " + s);
        disPatchResponse(s, i);
    }

    private void disPatchResponse(String s, int i) {
        if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface) {
            LocalLog.d(TAG, "disPatchResponse() enter body " + s);
            if (command == Engine.COMMAND_LOGIN_IN) {
                LoginResponse loginResponse = new Gson().fromJson(s, LoginResponse.class);
                ((LoginSignCallbackInterface) callBackInterface).requestPhoneLoginCallback(loginResponse);
            }

            if (command == Engine.COMMAND_REG_BY_PHONE) {
                SignUserResponse signUserResponse = new Gson().fromJson(s, SignUserResponse.class);
                ((LoginSignCallbackInterface) callBackInterface).registerByPhoneCallBack(signUserResponse);
            }
        } else if (callBackInterface != null && callBackInterface instanceof UserInfoInterface) {
            UserInfo userInfo = new Gson().fromJson(s, UserInfo.class);
            ((UserInfoInterface) callBackInterface).update(userInfo);
        } else if (callBackInterface != null && callBackInterface instanceof SignCodeCallBackInterface) {
            SignCodeResponse signCodeResponse = new Gson().fromJson(s, SignCodeResponse.class);
            ((SignCodeCallBackInterface) callBackInterface).signCodeCallBack(signCodeResponse);
        }
    }
}
