package com.paobuqianjin.pbq.step.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.input.InputManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by pbq on 2017/12/4.
 */

public class Utils {
    public final static int PAGE_SIZE_DEFAULT = 10;

    public static Context getApplicationContext(Context context) {
        return context.getApplicationContext();
    }

    //dp转px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
    public  static void hideInput(Context context) {
        InputMethodManager manager = null;
        if (manager == null) {
            manager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        manager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
