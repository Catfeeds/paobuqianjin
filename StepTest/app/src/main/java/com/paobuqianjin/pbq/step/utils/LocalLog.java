package com.paobuqianjin.pbq.step.utils;

import android.util.Log;

/**
 * Created by pbq on 2017/11/30.
 */

public final class LocalLog {
    private final static String TAG = "pbq";
    public final static int DEBUG = Log.DEBUG;
    public final static int ERROR = Log.ERROR;

    /*
    *@function debug log
    *@param
    *@return 
    */
    public static void d(String className, String msg) {
        if (/*Log.isLoggable(TAG, DEBUG) && */msg != null) {
            Log.d(TAG, " ->" + className + " :" + msg);
        }
    }

    public static void e(String className ,String msg) {
        if (/*Log.isLoggable(TAG, ERROR) && */msg != null) {
            Log.e(TAG, " ->" + className + " :" + msg);
        }

    }
}
