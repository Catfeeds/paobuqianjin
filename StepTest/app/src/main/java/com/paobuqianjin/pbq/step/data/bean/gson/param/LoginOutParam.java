package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/15.
 */

public class LoginOutParam {
    /*
 userid	用户ID	true	int
circleid	圈子ID	true	int
    * */
    private int userid;
    private int circleid;

    private Map<String, String> params;

    public LoginOutParam() {
        if (params == null) {
            params = new HashMap<>();
        }
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }

    public int getUserid() {
        return userid;
    }

    public LoginOutParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getCircleid() {
        return circleid;
    }

    public LoginOutParam setCircleid(int circleid) {
        this.circleid = circleid;
        params.put("circleid", String.valueOf(circleid));
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
        return "LoginOutParam{" +
                "userid=" + userid +
                ", circleid=" + circleid +
                ", params=" + params +
                '}';
    }
}
