package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/23.
 */

public class PostPassWordParam {
    /*
    * mobile	用户手机号	true	string
password	密码	true	string
code	验证码	true	string*/
    private String mobile;
    private String password;
    private String code;
    private Map<String, String> params;

    public PostPassWordParam() {
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

    @Override
    public String toString() {
        return "PostPassWordParam{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", params=" + params +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public PostPassWordParam setMobile(String mobile) {
        this.mobile = mobile;
        params.put("mobile", mobile);
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PostPassWordParam setPassword(String password) {
        this.password = password;
        params.put("password", password);
        return this;
    }

    public String getCode() {
        return code;
    }

    public PostPassWordParam setCode(String code) {
        this.code = code;
        params.put("code", code);
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
