package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/2/7.
 */

public class CheckSignCodeParam {
    /*
    userid	用户ID	true	int	1
code	短信验证码	true	string	1
    * */

    private int userid;
    private String code;
    private Map<String, String> params;

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }

    public CheckSignCodeParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public int getUserid() {
        return userid;
    }

    public CheckSignCodeParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid",String.valueOf(userid));
        return this;
    }

    public String getCode() {
        return code;
    }

    public CheckSignCodeParam setCode(String code) {
        this.code = code;
        params.put("code",code);
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
        return "CheckSignCodeParam{" +
                "userid=" + userid +
                ", code='" + code + '\'' +
                ", params=" + params +
                '}';
    }
}
