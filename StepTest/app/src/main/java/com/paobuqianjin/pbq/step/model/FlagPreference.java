package com.paobuqianjin.pbq.step.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;
import com.paobuqianjin.pbq.step.utils.LocalLog;

/**
 * Created by pbq on 2017/12/6.
 */
/*
@className :FlagPreference  directed access by dao and engine
*@date 2017/12/6
*@author
*@description
*/
public final class FlagPreference {
    private final static String TAG = FlagPreference.class.getSimpleName();
    private final static String SHARE_PREF_NAME = "com.paobuqianjin.pbq.step.login";


    public static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return flagPreference;
    }

    public static void setPayResultCode(Context context, int errorCode) {
        LocalLog.d(TAG, "保存当前支付errorCode");
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putInt("errorCode", errorCode);
        editor.commit();
    }

    public static int getPayResultCode(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        int errorCode = flagPreference.getInt("errorCode", -3);
        return errorCode;
    }

    public static String[] getLocation(Context context) {
        String[] location = new String[2];
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        location[0] = flagPreference.getString("latitude", "");
        location[1] = flagPreference.getString("longitude", "");
        return location;
    }

    public static void setLocation(Context context, String latitude, String longitude) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("latitude", latitude);
        editor.putString("longitude", longitude);
        editor.commit();
    }

    public static void setOutTradeNo(Context context, String outTradeNo) {
        LocalLog.d(TAG, "setOutTradeNo() 保存最新订单号");
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("out_trade_no", outTradeNo);
        editor.commit();
    }

    //与订单号必须同步操作
    public static void setTradeStyle(Context context, String tradeStyle) {
        LocalLog.d(TAG, "setTradeStyle() 保存最新订类型");
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("out_trade_style", tradeStyle);
        editor.commit();
    }

    public static String getTradeStyle(Context context) {
        LocalLog.d(TAG, "TradeStyle() 获取最新订单号");
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String trade_style = flagPreference.getString("out_trade_style", "");
        return trade_style;
    }

    public static String getOutTradeNo(Context context) {
        LocalLog.d(TAG, "getOutTradeNo() 获取最新订单号");
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String outTradeNo = flagPreference.getString("out_trade_no", "");
        return outTradeNo;
    }

    public static void setUid(Context context, int id) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putInt("id", id);
        editor.commit();
    }

    public static void setNo(Context context, String no) {
        LocalLog.d(TAG, "setNo() " + no);
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("no", no);
        editor.commit();
    }

    public static String getNo(Context context) {
        LocalLog.d(TAG, "getNo() ");
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String no = flagPreference.getString("no", "");
        return no;
    }

    public static String getAvatar(Context context) {
        LocalLog.d(TAG, "getAvatar() ");
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String mobile = flagPreference.getString("avatar", "");
        return mobile;
    }

    public static void setAvatar(Context context, String avatar) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("avatar", avatar);
        editor.commit();
    }

    public static void setNickName(Context context, String nickname) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("nickname", nickname);
        editor.commit();
    }

    public static String getNickName(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String mobile = flagPreference.getString("nickname", "");
        return mobile;
    }

    public static void setTarget(Context context, int target) {
        LocalLog.d(TAG, "setTarget() 设置目标" + target);
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putInt("target", target);
        editor.commit();
    }

    public static int getTarget(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        int target = flagPreference.getInt("target", 10000);
        LocalLog.d(TAG, "getTarget() 获取目标" + target);
        return target;
    }

    public static String getMobile(Context context) {
        LocalLog.d(TAG, "getMobile() 获取最新订单号");
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String mobile = flagPreference.getString("mobile", "");
        return mobile;
    }

    public static void setMobile(Context context, String mobile) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("mobile", mobile);
        editor.commit();
    }

    public static int getUid(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        int id = flagPreference.getInt("id", -1);
        return id;
    }

    public static boolean getLoginFlag(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        boolean isLogin = flagPreference.getBoolean("isLogin", false);
        return isLogin;
    }

    public static void setLoginFlag(Context context, boolean isLogin) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putBoolean("isLogin", isLogin);
        editor.commit();
    }

    public static void setToken(Context context, String user_token) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("user_token", user_token);
        editor.commit();
    }

    public static void setReadCrashProtocol(Context context, boolean readFlag) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putBoolean("crash_protocl", readFlag);
        editor.commit();
    }

    public static boolean getReadCrashProtocol(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        boolean readFlag = flagPreference.getBoolean("crash_protocl", false);
        return readFlag;
    }

    public static String getToken(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String user_token = flagPreference.getString("user_token", "");
        return user_token;
    }

    public static String getEffectStartSportTime(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String effectStartSortTime = flagPreference.getString("effect_start_time", "5:30");
        return effectStartSortTime;
    }

    public static void EffectStartSportTime(Context context, String effectStartSortTime) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("effect_start_time", effectStartSortTime);
        editor.commit();
    }

    public static String getEffectEndSportTime(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String effectStartSortTime = flagPreference.getString("effect_end_time", "5:30");
        return effectStartSortTime;
    }

    public static void setEffectEndSportTime(Context context, String effectEndSportTime) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("effect_start_time", effectEndSportTime);
        editor.commit();
    }

    /*服务在本终端第一次启动的时间点*/
    public static String getStartServiceTime(Context context) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String start_service_time = flagPreference.getString("start_service_time", null);
        return start_service_time;
    }

    /*记录服务开启时间点*/
    public static void setStartServiceTime(Context context, String startServiceTime) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("start_service_time", startServiceTime);
        editor.commit();
    }

    public static int getUnEffectStep(Context context) {
        //获取登录时已经积累的步数
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        int unEffectStep = flagPreference.getInt("un_effect_step", -1);
        return unEffectStep;
    }

    public static void setUnEffectStep(Context context, int unEffectStep) {
        //获取登录时已经积累的步数
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putInt("un_effect_step", unEffectStep);
        editor.commit();
    }
}
