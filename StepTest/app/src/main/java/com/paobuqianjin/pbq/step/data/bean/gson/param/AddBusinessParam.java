package com.paobuqianjin.pbq.step.data.bean.gson.param;

import android.text.TextUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/4/27.
 */

public class AddBusinessParam {
    private int businessId;
    private String name;
    private String logo;
    private String tel;
    private String addra;
    private String address;
    private String longitude;
    private String latitude;
    private String environment_images;
    private String goods_images;
    private String do_day;
    private String s_do_time;
    private String e_do_time;
    private int defaultBusiness;

    public String getScope() {
        return scope;
    }

    public AddBusinessParam setScope(String scope) {
        this.scope = scope;
        params.put("scope", scope);
        return this;
    }

    private String scope;
    private Map<String, String> params;

    public AddBusinessParam() {
        params = new LinkedHashMap<>();
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }

    public int getBusinessId() {
        return businessId;
    }

    public AddBusinessParam setBusinessId(int businessId) {
        this.businessId = businessId;
        params.put("businessid", String.valueOf(businessId));
        return this;
    }

    public String getName() {
        return name;
    }

    public AddBusinessParam setName(String name) {
        this.name = name;
        params.put("name", name);
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public AddBusinessParam setLogo(String logo) {
        this.logo = logo;
        params.put("logo", logo);
        return this;
    }

    public String getTel() {
        return tel;
    }

    public AddBusinessParam setTel(String tel) {
        this.tel = tel;
        params.put("tel", tel);
        return this;
    }

    public String getAddra() {
        return addra;
    }

    public AddBusinessParam setAddra(String addra) {
        this.addra = addra;
        params.put("addra", addra);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AddBusinessParam setAddress(String address) {
        this.address = address;
        params.put("address", address);
        return this;
    }

    public String getEnvironment_images() {
        return environment_images;
    }

    public AddBusinessParam setEnvironment_images(String environment_images) {
        this.environment_images = environment_images;
        params.put("environment_images", environment_images);
        return this;
    }

    public String getGoods_images() {
        return goods_images;
    }

    public AddBusinessParam setGoods_images(String goods_images) {
        this.goods_images = goods_images;
        params.put("goods_images", goods_images);
        return this;
    }

    public String getDo_day() {
        return do_day;
    }

    public AddBusinessParam setDo_day(String do_day) {
        this.do_day = do_day;
        params.put("do_day", do_day);
        return this;
    }

    public String getS_do_time() {
        return s_do_time;
    }

    public AddBusinessParam setS_do_time(String s_do_time) {
        this.s_do_time = s_do_time;
        params.put("s_do_time", s_do_time);
        return this;
    }

    public String getE_do_time() {
        return e_do_time;
    }

    public AddBusinessParam setE_do_time(String e_do_time) {
        this.e_do_time = e_do_time;
        params.put("e_do_time", e_do_time);
        return this;
    }

    public int getDefaultBusiness() {
        return defaultBusiness;
    }

    public AddBusinessParam setDefaultBusiness(int defaultBusiness) {
        this.defaultBusiness = defaultBusiness;
        params.put("defaultBusiness", String.valueOf(defaultBusiness));
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public AddBusinessParam setLatitude(String latitude) {
        this.latitude = latitude;
        params.put("latitude", latitude);
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public AddBusinessParam setLongitude(String longitude) {
        this.longitude = longitude;
        params.put("longitude", longitude);
        return this;
    }


    public boolean equals(AddBusinessParam obj) {
        boolean result = false;
        if (!TextUtils.isEmpty(obj.paramString())) {
            result = obj.paramString().equals(paramString());
        }
        return result;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
