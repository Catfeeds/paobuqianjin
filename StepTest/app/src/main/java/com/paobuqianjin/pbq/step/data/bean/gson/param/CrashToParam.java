package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/2/8.
 */
/*
@className :CrashToParam
*@date 2018/2/8
*@author
*@description 提现参数
*/
public class CrashToParam {
    public String getWx_openid() {
        return wx_openid;
    }

    public CrashToParam setWx_openid(String wx_openid) {
        this.wx_openid = wx_openid;
        params.put("wx_openid", wx_openid);
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public CrashToParam setAmount(String amount) {
        this.amount = amount;
        params.put("amount", amount);
        return this;
    }

    public String getWx_unionid() {
        return wx_unionid;
    }

    public CrashToParam setWx_unionid(String wx_unionid) {
        this.wx_unionid = wx_unionid;
        params.put("wx_unionid", wx_unionid);
        return this;
    }

    public String getWx_nickname() {
        return wx_nickname;
    }

    public CrashToParam setWx_nickname(String wx_nickname) {
        this.wx_nickname = wx_nickname;
        params.put("wx_nickname", wx_nickname);
        return this;
    }

    public String getWx_sex() {
        return wx_sex;
    }

    public CrashToParam setWx_sex(String wx_sex) {
        this.wx_sex = wx_sex;
        params.put("wx_sex", wx_sex);
        return this;
    }

    public String getWx_avatar() {
        return wx_avatar;
    }

    public CrashToParam setWx_avatar(String wx_avatar) {
        this.wx_avatar = wx_avatar;
        params.put("wx_avatar", wx_avatar);
        return this;
    }

    public String getWx_province() {
        return wx_province;
    }

    public CrashToParam setWx_province(String wx_province) {
        this.wx_province = wx_province;
        return this;
    }

    public String getWx_city() {
        return wx_city;
    }

    public CrashToParam setWx_city(String wx_city) {
        this.wx_city = wx_city;
        params.put("wx_city", wx_city);
        return this;
    }

    public String getWx_area() {
        return wx_area;
    }

    public CrashToParam setWx_area(String wx_area) {
        this.wx_area = wx_area;
        params.put("wx_area", wx_area);
        return this;
    }

    /*
        wx_openid	微信openid	true	string
        amount	提现金额	true	float
        wx_unionid	微信unionid	false	string
        wx_nickname	微信昵称	false	string
        wx_sex	微信性别	false	string
        wx_avatar	微信头像	false	string
        wx_province	省	false	string
        wx_city	市	false	string
        wx_area	区	false	string
        */
    private String wx_openid;
    private String amount;
    private String wx_unionid;
    private String wx_nickname;
    private String wx_sex;
    private String wx_avatar;
    private String wx_province;
    private String wx_city;
    private String wx_area;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    private Map<String, String> params;


    public CrashToParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }

}
