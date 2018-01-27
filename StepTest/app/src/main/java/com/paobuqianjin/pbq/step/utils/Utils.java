package com.paobuqianjin.pbq.step.utils;

import android.content.Context;

/**
 * Created by pbq on 2017/12/4.
 */

public class Utils {

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
}
