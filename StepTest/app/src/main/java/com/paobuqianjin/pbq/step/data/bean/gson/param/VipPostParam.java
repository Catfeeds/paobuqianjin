package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/4/26.
 */

public class VipPostParam {
    private Map<String, String> params;
    private String userids;
    private String spend;

    public VipPostParam() {
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

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getUserids() {
        return userids;
    }

    public VipPostParam setUserids(String userids) {
        this.userids = userids;
        params.put("userids", userids);
        return this;
    }

    public String getSpend() {
        return spend;
    }

    public VipPostParam setSpend(String spend) {
        this.spend = spend;
        params.put("spend", spend);
        return this;
    }

    @Override
    public String toString() {
        return "VipPostParam{" +
                "params=" + params +
                ", userids='" + userids + '\'' +
                ", spend='" + spend + '\'' +
                '}';
    }
}
