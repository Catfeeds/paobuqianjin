package com.paobuqianjin.pbq.step.data.bean.gson;

import java.util.Map;

/**
 * Created by pbq on 2017/12/25.
 */

public class UserRecordParam {
    private int id;
    private double longitude;
    private double latitude;

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    private Map<String, String> param;

    public int getId() {
        return id;
    }

    public UserRecordParam setId(int id) {
        this.id = id;
        param.put("id", String.valueOf(id));
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public UserRecordParam setLongitude(double longitude) {
        this.longitude = longitude;
        param.put("longitude", String.valueOf(longitude));
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public UserRecordParam setLatitude(double latitude) {
        this.latitude = latitude;
        param.put("latitude", String.valueOf(latitude));
        return this;
    }
}
