package com.paobuqianjin.pbq.step.model;

import android.content.Context;
import android.content.SharedPreferences;

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
    private final static String SHARE_PREF_NAME = "com.paobuqianjin.pbq.step.login";

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
    public static void setStartServiceTime(Context context,String startServiceTime) {
        SharedPreferences flagPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = flagPreference.edit();
        editor.putString("start_service_time", startServiceTime);
        editor.commit();
    }
}
