package com.paobuqianjin.pbq.step.data.bean.gson.param;

import android.util.ArrayMap;

import com.paobuqianjin.pbq.step.utils.LocalLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/12.
 */
/*
@className :JoinCircleParam
*@date 2018/3/12
*@author
*@description 加入圈子参数
*/
public class JoinCircleParam {
    private int userid;
    private int circleid;
    private String password;
    private Map<String, String> params;

    public JoinCircleParam() {
        if (params == null) {
            params = new HashMap<>();
        }
    }

    @Override
    public String toString() {
        return "JoinCircleParam{" +
                "userid=" + userid +
                ", circleid=" + circleid +
                ", password='" + password + '\'' +
                ", param=" + params +
                '}';
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

    public JoinCircleParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getCircleid() {
        return circleid;
    }

    public JoinCircleParam setCircleid(int circleid) {
        this.circleid = circleid;
        params.put("circleid", String.valueOf(circleid));
        LocalLog.d("circleid",String.valueOf(circleid));
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JoinCircleParam setPassword(String password) {
        this.password = password;
        params.put("password", password);
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
