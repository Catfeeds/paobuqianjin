package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/5.
 */
/*
@className :PutVoteParam
*@date 2018/3/5
*@author
*@description 点赞、取消点赞参数
*/
public class PutVoteParam {
    /*
    * dynamicid	动态ID	true	int
id	点赞用户id	true	int*/
    private Map<String, String> params;
    private int dynamicid;
    private int userid;

    public PutVoteParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public Map<String, String> getParams() {
        return params;
    }

    public PutVoteParam setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public int getDynamicid() {
        return dynamicid;
    }

    public PutVoteParam setDynamicid(int dynamicid) {
        this.dynamicid = dynamicid;
        params.put("dynamicid", String.valueOf(dynamicid));
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public PutVoteParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    @Override
    public String toString() {
        return "PutVoteParam{" +
                "params=" + params +
                ", dynamicid=" + dynamicid +
                ", userid=" + userid +
                '}';
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }

}
