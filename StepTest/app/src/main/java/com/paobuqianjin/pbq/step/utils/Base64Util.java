package com.paobuqianjin.pbq.step.utils;

import android.text.TextUtils;
import android.util.Base64;


/**
 * Created by pbq on 2018/5/11.
 */

public class Base64Util {
    private final static String TAG = Base64Util.class.getSimpleName();

    public static String makeUidToBase64(long uid) {
        LocalLog.d(TAG, "makeUidToBase64 uid = " + uid);
        String strUid = String.valueOf(uid);
        String enUid = new String(Base64.encode(strUid.getBytes(), Base64.DEFAULT));
        LocalLog.d(TAG, "makeUidToBase64 enUid = " + enUid);
        return enUid;
    }

    public static String getUidFromBase64(String base64Id) {
        String result = "";
        if (!TextUtils.isEmpty(base64Id)) {
            LocalLog.d(TAG, "getUidFromBase64 enUID = " + base64Id);
            if (!TextUtils.isEmpty(base64Id)) {
                result = new String(Base64.decode(base64Id.getBytes(), Base64.DEFAULT));

            }
        }
        LocalLog.d(TAG, "getUidFromBase64 uid = " + result);
        return result;
    }
}
