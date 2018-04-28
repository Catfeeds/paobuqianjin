package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/4/27.
 */

public class RedPkgRecParam {
    /*
    userid	用户id	true	int
redids	红包id字符串，例：2,3,110	true	int
longitude	经度	true	float
latitude	纬度	true	float
    * */
    private int userid;
    private String redids;
    private String latitude;
    private String longitude;
    private Map<String, String> params;

    public RedPkgRecParam() {
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

    public RedPkgRecParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public String getRedids() {
        return redids;
    }

    public RedPkgRecParam setRedids(String redids) {
        this.redids = redids;
        params.put("redids", redids);
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public RedPkgRecParam setLatitude(String latitude) {
        this.latitude = latitude;
        params.put("latitude", latitude);
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public RedPkgRecParam setLongitude(String longitude) {
        this.longitude = longitude;
        params.put("longitude", longitude);
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
