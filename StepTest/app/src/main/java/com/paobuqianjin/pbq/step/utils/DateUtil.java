package com.paobuqianjin.pbq.step.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by pbq on 2017/12/7.
 */

public final class DateUtil {
    public static String getCurDate() {
        SimpleDateFormat localSimpleDataFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        localSimpleDataFormat.setTimeZone(TimeZone.getDefault());
        return localSimpleDataFormat.format(Calendar.getInstance().getTime());
    }

    public static String getCurDateMin() {
        SimpleDateFormat localSimpleDataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        localSimpleDataFormat.setTimeZone(TimeZone.getDefault());
        return localSimpleDataFormat.format(Calendar.getInstance().getTime());
    }

    public static String getCurDateSec() {
        SimpleDateFormat localSimpleDataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        localSimpleDataFormat.setTimeZone(TimeZone.getDefault());
        return localSimpleDataFormat.format(Calendar.getInstance().getTime());
    }
}
