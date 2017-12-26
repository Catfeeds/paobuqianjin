package com.paobuqianjin.pbq.step.data.netcallback;

import com.google.gson.Gson;
import com.l.okhttppaobu.okhttp.callback.StringCallback;
import com.paobuqianjin.pbq.step.data.bean.gson.param.NearByPeopleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ChoiceCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyHotCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignCodeResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.SignUserResponse;
import com.paobuqianjin.pbq.step.model.Engine;
import com.paobuqianjin.pbq.step.presenter.im.CallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.LoginSignCallbackInterface;
import com.paobuqianjin.pbq.step.presenter.im.SignCodeCallBackInterface;
import com.paobuqianjin.pbq.step.presenter.im.UiHotCircleInterface;
import com.paobuqianjin.pbq.step.presenter.im.UserInfoInterface;
import com.paobuqianjin.pbq.step.utils.LocalLog;

import okhttp3.Call;

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

            if (command == Engine.COMMAND_NEARBY_PEOPLE) {
                NearByPeopleResponse nearByPeopleResponse = new Gson().fromJson(s, NearByPeopleResponse.class);
                LocalLog.d(TAG, nearByPeopleResponse.toString());
            }
        } else if (callBackInterface != null && callBackInterface instanceof UserInfoInterface) {
            UserInfoResponse userInfoResponse = new Gson().fromJson(s, UserInfoResponse.class);
            ((UserInfoInterface) callBackInterface).update(userInfoResponse);
        } else if (callBackInterface != null && callBackInterface instanceof SignCodeCallBackInterface) {
            SignCodeResponse signCodeResponse = new Gson().fromJson(s, SignCodeResponse.class);
            ((SignCodeCallBackInterface) callBackInterface).signCodeCallBack(signCodeResponse);
        } else if (callBackInterface != null && callBackInterface instanceof LoginSignCallbackInterface
                && command == Engine.COMMAND_REFRESH_PASSWORD) {
            LocalLog.d(TAG, s);
        } else if (callBackInterface != null && callBackInterface instanceof UiHotCircleInterface
                && command == Engine.COMMAND_GET_MY_CIRCLE) {
            LocalLog.d(TAG, "获取我的圈子");
            MyHotCircleResponse myHotCircleResponse = new Gson().fromJson(s, MyHotCircleResponse.class);
            ((UiHotCircleInterface) callBackInterface).response(myHotCircleResponse);
        } else if (callBackInterface != null && callBackInterface instanceof UiHotCircleInterface
                && command == Engine.COMMAND_GET_CHOICE_CIRCLE) {
            ChoiceCircleResponse choiceCircleResponse = new Gson().fromJson(s, ChoiceCircleResponse.class);
            ((UiHotCircleInterface) callBackInterface).response(choiceCircleResponse);
        }
    }
}
