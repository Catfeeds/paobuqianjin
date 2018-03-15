package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/15.
 */

public class QueryFollowStateParam {
    /*
    * userid	用户ID	true	int	0
followid	被关注用户ID	true	int	0*/
    private int userid;
    private int followid;
    private Map<String, String> params;

    public QueryFollowStateParam() {
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

    public int getUserid() {
        return userid;
    }

    public QueryFollowStateParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getFollowid() {
        return followid;
    }

    public QueryFollowStateParam setFollowid(int followid) {
        this.followid = followid;
        params.put("followid", String.valueOf(followid));
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
        return "QueryFollowStateParam{" +
                "userid=" + userid +
                ", followid=" + followid +
                ", params=" + params +
                '}';
    }
}
