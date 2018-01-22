package com.paobuqianjin.pbq.step.data;

import android.util.Log;

import com.paobuqianjin.pbq.step.utils.LocalLog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by pbq on 2018/1/22.
 */

public class Weather {
    private final static String TAG = Weather.class.getSimpleName();

    public static void getWeather(double la, double lb) {
        String url = "http://e.Weather.com.cn/d/town/index?lat=" + String.valueOf(la) + "&lon=" + String.valueOf(lb);
        LocalLog.d(TAG, "Weather url = " + url);
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
