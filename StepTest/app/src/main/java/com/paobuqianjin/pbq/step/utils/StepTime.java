package com.paobuqianjin.pbq.step.utils;

import java.util.Calendar;

/**
 * Created by pbq on 2017/12/4.
 */
/*
@className :StepTime
*@date 2017/12/4
*@author
*@description  日历工具类,最佳运动时间在早上5:30 ~ 21:00之间，统计之间的步数
*/
public final class StepTime {
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }
    
}
