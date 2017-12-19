package com.paobuqianjin.pbq.step.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by pbq on 2017/12/18.
 */

public class NetWorkUtil {
    private final static String TAG = NetWorkUtil.class.getSimpleName();

    /*@descn  返回当前网络类型，不同条件下进行不同的业务
    *@function GetNetWorkType
    *@param
    *@return 
    */
    public static String GetNetWorkType(Context context) {
        String strNetWorkType = "";
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetWorkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String strSubTypeName = networkInfo.getSubtypeName();
                LocalLog.d(TAG, "GetNetWorkType() strSubTypeName" + strSubTypeName);
                int mobileNetType = networkInfo.getSubtype();
                switch (mobileNetType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetWorkType = "2G";
                        break;

                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD: //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP: //api<13 : replace by 15
                        strNetWorkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:  //api<11 : replace by 13
                        strNetWorkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetWorkType = "3G";
                        } else {
                            strNetWorkType = strSubTypeName;
                        }

                        break;
                }
            }
        }
        LocalLog.d(TAG, "当前网络为:" + strNetWorkType);
        return strNetWorkType;
    }
}
