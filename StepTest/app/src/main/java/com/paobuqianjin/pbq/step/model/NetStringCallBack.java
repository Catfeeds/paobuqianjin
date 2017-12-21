package com.paobuqianjin.pbq.step.model;

import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.callback.Callback;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.data.bean.gson.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.SignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.UserInfo;
import com.paobuqianjin.pbq.step.presenter.Presenter;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.InlineCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pbq on 2017/12/20.
 */

public class NetStringCallBack extends StringCallback {
    private final static String TAG = NetStringCallBack.class.getSimpleName();
    private CallBackInterface callBackInterface;


    public NetStringCallBack(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    @Override
    public void onError(Call call, Exception e, int i) {

    }

    @Override
    public void onResponse(String s, int i) {
        LocalLog.d(TAG, "onResponse() enter body " + s);
        disPatchResponse(s, i);
    }

    private void disPatchResponse(String s, int i) {
        if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface) {
            LocalLog.d(TAG, "disPatchResponse() enter body " + s);
            LoginResponse loginResponse = new Gson().fromJson(s, LoginResponse.class);
            ((LoginSignCallbackInterface) callBackInterface).requestPhoneLoginCallback(loginResponse);
        } else if (callBackInterface != null && callBackInterface instanceof UserInfoInterface) {
            UserInfo userInfo = new Gson().fromJson(s, UserInfo.class);
            ((UserInfoInterface) callBackInterface).update(userInfo);
        } else if (callBackInterface != null && callBackInterface instanceof SignCodeCallBackInterface) {
            SignCodeResponse signCodeResponse = new Gson().fromJson(s, SignCodeResponse.class);
            ((SignCodeCallBackInterface) callBackInterface).signCodeCallBack(signCodeResponse);
        }
    }
}
