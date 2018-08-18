package com.paobuqianjin.pbq.step.utils;

import android.text.TextUtils;
import android.util.Base64;


/**
 * Created by pbq on 2018/5/11.
 */

public class Base64Util {
    private final static String TAG = Base64Util.class.getSimpleName();

    public static String makeUidToBase64(String source) {
        LocalLog.d(TAG, "makeUidToBase64 uid = " + source);
        String enUid = new String(Base64.encode(source.getBytes(), Base64.DEFAULT));
        LocalLog.d(TAG, "makeUidToBase64 enUid = " + enUid);
        return enUid;
    }

    public static String getUidFromBase64(String base64Id) {
        String result = "";
        if (!TextUtils.isEmpty(base64Id)) {
            LocalLog.d(TAG, "getUidFromBase64 enUID = " + base64Id);
            if (!TextUtils.isEmpty(base64Id)) {
                try {
                    result = new String(Base64.decode(base64Id.getBytes(), Base64.DEFAULT));
                } catch (IllegalArgumentException e) {
                    result = base64Id;
                    LocalLog.d(TAG, "bad base-64");
                }
            }
        }
        LocalLog.d(TAG, "getUidFromBase64 uid = " + result);
        return result;
    }
}
