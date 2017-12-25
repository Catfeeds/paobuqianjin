package com.paobuqianjin.pbq.step.data.bean.gson;

import java.util.Map;

/**
 * Created by pbq on 2017/12/25.
 */

public class AddFollowParam {
    private int userid;
    private int followid;
    private Map<String, String> param;

    public int getUserid() {
        return userid;
    }

    public AddFollowParam setUserid(int userid) {
        this.userid = userid;
        param.put("userid", String.valueOf(userid));
        return this;
    }

    public int getFollowid() {
        return followid;
    }

    public AddFollowParam setFollowid(int followid) {
        this.followid = followid;
        param.put("followid", String.valueOf(followid));
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
