package com.paobuqianjin.pbq.step.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pbq on 2018/11/12.
 */
//淘宝京东拼多多等等
public class ShopToolUtil {
    public final static String TaoBaoSchema = "taobao://";
    private final static String TAG = ShopToolUtil.class.getSimpleName();
    //淘宝店铺的链接风格
    private final static String TaoBaoAppShopUrlStyle = "taobao://shop.m.taobao.com/shop/shop_index.htm?shop_id=";
    private final static String TaoBaoWebShopUrlStyle = "https://shop.m.taobao.com/shop/shop_index.htm?shop_id=";
    private final static String TaoBaoShopShopStyle = "https://shop";
    private final static String TaoBaoShopShopStyleEnd = "taobao.com/";
    //淘宝商品详店铺的风格
    private final static String TaoBaoGoodH5UrlStyle = "https://h5.m.taobao.com/awp/core/detail.htm?id=";
    private final static String TaoBaoGoodWebUrlStyle = "https://item.taobao.com/item.htm?id=";
    private final static String TaoBaoGoodAppUrlStyle = "taobao://item.taobao.com/item.htm?id=";

    //拼多多


    //包名
    public final static String TaoBao = "com.taobao.taobao";

    public static String taoBaoString(String resStr) {
        String result = resStr;
        String regEx = "[^0-9]";
        if (!TextUtils.isEmpty(resStr)) {
            LocalLog.d(TAG, "resStr= " + resStr);
            if (resStr.startsWith(TaoBaoAppShopUrlStyle)) {
                LocalLog.d(TAG, "跳转店铺详情1");
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(resStr);
                String shopId = m.replaceAll("").trim();
                LocalLog.d(TAG, "shopId = " + shopId);
                result = TaoBaoAppShopUrlStyle + shopId;
            } else if (resStr.startsWith(TaoBaoWebShopUrlStyle)) {
                LocalLog.d(TAG, "跳转店铺详情2");
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(resStr);
                String shopId = m.replaceAll("").trim();
                LocalLog.d(TAG, "shopId = " + shopId);
                result = TaoBaoAppShopUrlStyle + shopId;
            } else if (resStr.startsWith(TaoBaoShopShopStyle) && (resStr.endsWith(TaoBaoShopShopStyleEnd) || resStr.endsWith(""))) {
                LocalLog.d(TAG, "跳转店铺详情3");
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(resStr);
                String shopId = m.replaceAll("").trim();
                LocalLog.d(TAG, "shopId = " + shopId);
                result = TaoBaoAppShopUrlStyle + shopId;
            } else if (resStr.startsWith(TaoBaoGoodH5UrlStyle)) {
                LocalLog.d(TAG, "跳转商品详情1");
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(resStr);
                String gooId = m.replaceAll("").trim();
                LocalLog.d(TAG, "goodId = " + gooId);
                result = TaoBaoGoodAppUrlStyle + gooId;
            } else if (resStr.startsWith(TaoBaoGoodWebUrlStyle)) {
                LocalLog.d(TAG, "跳转商品详情2");
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(resStr);
                String gooId = m.replaceAll("").trim();
                LocalLog.d(TAG, "goodId = " + gooId);
                result = TaoBaoGoodAppUrlStyle + gooId;
            } else if (resStr.startsWith(TaoBaoGoodAppUrlStyle)) {
                LocalLog.d(TAG, "跳转商品详情3");
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(resStr);
                String gooId = m.replaceAll("").trim();
                LocalLog.d(TAG, "goodId = " + gooId);
                result = TaoBaoGoodAppUrlStyle + gooId;
            }
        }
        LocalLog.d(TAG, "result = " + result);
        return result;
    }

}
