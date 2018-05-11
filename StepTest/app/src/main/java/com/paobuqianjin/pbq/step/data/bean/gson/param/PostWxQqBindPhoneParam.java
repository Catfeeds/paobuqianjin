package com.paobuqianjin.pbq.step.data.bean.gson.param;

import com.paobuqianjin.pbq.step.utils.MD5;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/23.
 */
/*
@className :PostWxQqBindPhoneParam
*@date 2018/3/23
*@author
*@description  三方绑定手机号码
*/
public class PostWxQqBindPhoneParam {
    public int getUserid() {
        return userid;
    }

    public PostWxQqBindPhoneParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    /*
    userid	用户id必填	true	string
    openid	微信或者QQ的openid	true	string
    mobile	绑定的手机号	true	string
    code	验证码	true	string
    password 验证码 true string
        * */
    private int userid;
    private String openid;
    private String mobile;

    public String getPassword() {
        return password;
    }

    public PostWxQqBindPhoneParam setPassword(String password) {
        this.password = password;
        String md5 = MD5.md5Slat(password);
        params.put("password", md5);
        return this;
    }

    private String password;

    public String getCode() {
        return code;
    }

    public PostWxQqBindPhoneParam setCode(String code) {
        this.code = code;
        params.put("code", code);
        return this;
    }

    private String code;
    private Map<String, String> params;

    public PostWxQqBindPhoneParam() {
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


    public String getOpenid() {
        return openid;
    }

    public PostWxQqBindPhoneParam setOpenid(String openid) {
        this.openid = openid;
        params.put("openid", openid);
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public PostWxQqBindPhoneParam setMobile(String mobile) {
        this.mobile = mobile;
        params.put("mobile", mobile);
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
