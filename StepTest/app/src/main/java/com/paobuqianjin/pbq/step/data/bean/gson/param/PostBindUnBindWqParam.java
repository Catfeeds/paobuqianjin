package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/4/2.
 */

public class PostBindUnBindWqParam {
    /*
    * action	用户类型 wx qq	true	string
openid	绑定的OPENID	true	string
userid	用户ID	true	string
*/
    private String action;
    private String openid;
    private int userid;

    public String getUnionid() {
        return unionid;
    }

    public PostBindUnBindWqParam setUnionid(String unionid) {
        this.unionid = unionid;
        params.put("unionid", unionid);
        return this;
    }

    private String unionid;
    private Map<String, String> params;


    public PostBindUnBindWqParam() {
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

    public String getAction() {
        return action;
    }

    public PostBindUnBindWqParam setAction(String action) {
        this.action = action;
        params.put("action", action);
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public PostBindUnBindWqParam setOpenid(String openid) {
        this.openid = openid;
        params.put("openid", openid);
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public PostBindUnBindWqParam setUserid(int userid) {
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
        return "PostBindUnBindWqParam{" +
                "action='" + action + '\'' +
                ", openid='" + openid + '\'' +
                ", userid=" + userid +
                ", unionid='" + unionid + '\'' +
                ", params=" + params +
                '}';
    }
}
