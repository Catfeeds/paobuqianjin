package com.paobuqianjin.pbq.step.data.bean;

/**
 * Created by Administrator on 2018/5/8.
 */

public class SelectPoisitonListBean {
    private String name;
    private String address;
    private float lat;
    private float lon;

    public String getName() {
        return name;
    }

    public SelectPoisitonListBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SelectPoisitonListBean setAddress(String address) {
        this.address = address;
        return this;
    }

    public float getLat() {
        return lat;
    }

    public SelectPoisitonListBean setLat(float lat) {
        this.lat = lat;
        return this;
    }

    public float getLon() {
        return lon;
    }

    public SelectPoisitonListBean setLon(float lon) {
        this.lon = lon;
        return this;
    }
}
