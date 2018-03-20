package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/20.
 */

public class PostCircleRedPkgParam {
    /*
    * userid	用户id	true	int
circleid	圈子id	true	int	1*/
    private int userid;
    private int circleid;
    private Map<String, String> params;


    public PostCircleRedPkgParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public int getUserid() {
        return userid;
    }

    public PostCircleRedPkgParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid",String.valueOf(userid));
        return this;
    }

    public int getCircleid() {
        return circleid;
    }

    public PostCircleRedPkgParam setCircleid(int circleid) {
        this.circleid = circleid;
        params.put("circleid",String.valueOf(circleid));
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
        return "PostCircleRedPkgParam{" +
                "userid=" + userid +
                ", circleid=" + circleid +
                ", params=" + params +
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
