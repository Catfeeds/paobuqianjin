package com.paobuqianjin.pbq.step.data.bean.gson.param;

import android.util.ArrayMap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/2/27.
 */

public class PostInviteCodeParam {
    /*
    * icode	邀请码	true	string
userid	用户ID	false	int*/
    private String mobile;
    private int userid;

    public String getIcode() {
        return icode;
    }

    public PostInviteCodeParam setIcode(String icode) {
        this.icode = icode;
        params.put("icode", icode);
        return this;
    }

    private String icode;
    private Map<String, String> params;

    public PostInviteCodeParam() {
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

    public String getMobile() {
        return mobile;
    }

    public PostInviteCodeParam setMobile(String mobile) {
        this.mobile = mobile;
        params.put("mobile", mobile);
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public PostInviteCodeParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "PostInviteCodeParam{" +
                "mobile='" + mobile + '\'' +
                ", userid=" + userid +
                ", icode='" + icode + '\'' +
                ", params=" + params +
                '}';
    }
}
