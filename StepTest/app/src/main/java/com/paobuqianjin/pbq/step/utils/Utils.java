package com.paobuqianjin.pbq.step.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by pbq on 2017/12/4.
 */

public class Utils {
    private final static String TAG = Utils.class.getSimpleName();
    public final static int PAGE_SIZE_DEFAULT = 20;
    public final static String KEY_SIGN = "paobuqianjinzhf..20181029";
    public final static String KEY_SIGN_SHOP = "paobuqianjinzhf..20181102";

    public static Context getApplicationContext(Context context) {
        return context.getApplicationContext();
    }

    public static String appSignShop(String tokenShop) {
        return MD5.md5Slat(tokenShop + Utils.KEY_SIGN_SHOP);
    }

    /*EditText 动态限制字符*/
    public static void setEditTextLengthLimit(EditText editText, int length) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }

    //dp转px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //dp转px
    public static int dp2px(Context context, int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int result = (int) (pxValue / scale + 0.5f);
        LocalLog.d(TAG, "scale = " + scale + "result = " + result);
        return result;
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

    public static void hideInput(Context context) {
        InputMethodManager manager = null;
        if (manager == null) {
            manager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        manager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、178(新)、182、184、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、170、173、177、180、181、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][23456789]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /**
     * 获取屏幕宽高
     *
     * @param context
     * @return {width,height}
     */
    public static int[] getScreenWidthHight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    /*@desc
    *@function getIMEI
    *@param
    *@return 
    */
    public static final String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            String imei = telephonyManager.getDeviceId();
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (SecurityException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 判断服务是否开启
     *
     * @return
     */
    public static boolean isServiceRunning(Context context, String ServiceName) {
        if (("").equals(ServiceName) || ServiceName == null)
            return false;
        ActivityManager myManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString()
                    .equals(ServiceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get Mobile Type
     *
     * @return
     */
    private static String getMobileType() {
        return Build.MANUFACTURER;
    }

    /**
     * GoTo Open Self Setting Layout
     * Compatible Mainstream Models 兼容市面主流机型
     *
     * @param context
     */
    public static void jumpStartInterface(Context context) {
        Intent intent = new Intent();
        try {
            ComponentName componentName = null;

            String brand = android.os.Build.BRAND;

            switch (brand.toLowerCase()) {
                case "samsung":
                    componentName = new ComponentName("com.samsung.android.sm_sn",
                            "com.samsung.android.sm.ui.ram.AutoRunActivity");
                    break;
                case "huawei":
                case "honor":
                    if (Build.VERSION.SDK_INT >= 26) {
                        componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity");
                    } else if (Build.VERSION.SDK_INT < 26 && Build.VERSION.SDK_INT >= 23) {
                        componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
                    } else {
                        componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.com.huawei.permissionmanager.ui.MainActivity");
                    }
                    break;
                case "xiaomi":
                    componentName = new ComponentName("com.miui.securitycenter",
                            "com.miui.permcenter.autostart.AutoStartManagementActivity");
                    break;
                case "vivo":
                    if (Build.VERSION.SDK_INT >= 26) {
                        componentName = new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity");
                    } else {
                        componentName = new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.SoftwareManagerActivity");
                    }
                    break;
                case "oppo":
                    if (Build.VERSION.SDK_INT >= 26) {
                        componentName = new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity");
                    } else {
                        componentName = new ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity");
                    }
                    break;
                case "360":
                    componentName = new ComponentName("com.yulong.android.coolsafe",
                            "com.yulong.android.coolsafe.ui.activity.autorun.AutoRunListActivity");
                    break;
                case "meizu":
                    componentName = ComponentName.unflattenFromString("com.meizu.safe/.permission.PermissionMainActivity");
                    break;
                case "oneplus":
                    componentName = new ComponentName("com.oneplus.security",
                            "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity");
                    break;
                default:
                    break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (componentName != null) {
                intent.setComponent(componentName);
            } else {
                intent.setAction(Settings.ACTION_SETTINGS);
            }
            context.startActivity(intent);
        } catch (Exception e) {//抛出异常就直接打开设置页面
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }

    public static void sendSMS(Context context, String phoneNumber, String message) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
            intent.putExtra("sms_body", message);
            context.startActivity(intent);
        }
    }

    public static void callPhone(Context context, String phoneNumber) throws SecurityException {
        if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + phoneNumber);
            intent.setData(data);
            context.startActivity(intent);
        }
    }

    //num >10000
    public static String zeroTow(int num) {
        String result = "";
        int high = num / 10000;
        int low = num / 1000;
        if (low > 0) {
            result = String.valueOf(high) + "." + String.valueOf(low) + "w+";
        } else {
            result = high + "w+";
        }
        return result;
    }

    public static File getDiskCacheDir(Context context) {
        File cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir();
            LocalLog.d("Utils", "getExternalCachdir() = " + cachePath);
        } else {
            cachePath = context.getCacheDir();
            LocalLog.d("Utils", "getCacheDir() = " + cachePath);
        }
        if (cachePath == null) {
            cachePath = context.getFilesDir();
            LocalLog.e("Utils", "保存到文件目录" + cachePath);
        }

        if (cachePath == null) {
            cachePath = context.getExternalFilesDir(null);
            LocalLog.d("Utils", "getExternalFilesDir()" + cachePath);
        }
        if (cachePath == null) {
            cachePath = Environment.getExternalStorageDirectory();
        }
        Log.e("Utils", "cachePath =" + cachePath);
        return cachePath;
    }

    //check maps on phone
    public static boolean isHaveBaiduMap() {
        try {
            if (!new File("/data/data/" + "com.baidu.BaiduMap").exists()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean isHaveGaodeMap() {
        try {
            if (!new File("/data/data/" + "com.autonavi.minimap").exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isHaveTencentMap() {
        try {
            if (!new File("/data/data/" + "com.tencent.map").exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //地图坐标转换，高德和腾讯坐标一致，百度和高德需要转换
    //百度转换高德
    public double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }

    //高德转百度
    private static double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
        double[] bd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gd_lon, y = gd_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
        bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
        return bd_lat_lon;
    }

    /**
     * 打开腾讯地图
     * params 参考http://lbs.qq.com/uri_v1/guide-route.html
     *
     * @param context
     * @param dqAddress
     * @param gotoAddress
     * @param gotoLatitude
     * @param gotoLongitude 驾车：type=drive，policy有以下取值
     *                      0：较快捷
     *                      1：无高速
     *                      2：距离
     *                      policy的取值缺省为0
     *                      &from=" + dqAddress + "&fromcoord=" + dqLatitude + "," + dqLongitude + "
     */
    public static void openTencentMap(Context context, String dqAddress, String gotoAddress, String gotoLatitude, String gotoLongitude) {
        try {
            String url1 = "qqmap://map/routeplan?type=walk&fromcoord=CurrentLocation&to=" + gotoAddress + "&tocoord=" + gotoLatitude + "," + gotoLongitude + "&referer=H7PBZ-HE73V-YJYPZ-UNZA3-P6N53-KKF4I";
            Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(url1));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * openGaoDeMap void 调用高德地图apk
     * http://lbs.amap.com/api/uri-api/guide/travel/route
     */
    public static void openGaoDeMap(Context context, String dqAddress, String gotoAddress, String gotoLatitude, String gotoLongitude) {
        try {

            //double[] gotoLang=AMAPUtils.getInstance().bdToGaoDe(Double.parseDouble(gotoLatitude),Double.parseDouble(gotoLongitude));
            //gotoLatitude=String.valueOf(gotoLang[0]);gotoLongitude=String.valueOf(gotoLang[1]);
            String url = "androidamap://navi?sourceApplication=com.paobuqianjin.pbq.step&poiname=" + gotoAddress + "&lat=" + gotoLatitude + "&lon=" + gotoLongitude + "&dev=1&style=2";
            LocalLog.d(TAG, "url = " + url);
            Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(url));
            intent.setPackage("com.autonavi.minimap");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 百度地图[传参过来的值可以判断为百度]
     * http://lbsyun.baidu.com/index.php?title=uri/api/android
     */
    public static void openBaiduMap(Context context, String dqAddress, String gotoAddress, String currentLatitude, String currentLongitude, String gotoLatitude, String gotoLongitude) {
        try {
            double[] location = {Double.parseDouble(gotoLatitude), Double.parseDouble(gotoLongitude)};
            location = gaoDeToBaidu(location[0], location[1]);
            Intent intent = new Intent();
            String urls = "baidumap://map/walknavi?origin=" + currentLatitude + "," + currentLongitude + "&destination="
                    + String.valueOf(location[0]) + "," + String.valueOf(location[1]) + "&src=andr.baidu.openAPIdemo";
            LocalLog.d(TAG, "urls = " + urls);
            intent.setData(Uri.parse(urls));
            context.startActivity(intent); // 启动调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查APP是否安装
    public static boolean checkPackage(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager
                    .GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

    }
}
