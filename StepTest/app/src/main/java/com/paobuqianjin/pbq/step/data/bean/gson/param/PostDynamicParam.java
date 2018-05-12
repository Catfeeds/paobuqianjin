package com.paobuqianjin.pbq.step.data.bean.gson.param;

import com.paobuqianjin.pbq.step.utils.Base64Util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/26.
 */

public class PostDynamicParam {
    /*
    * userid	用户ID	true	int
dynamic	动态内容	false	string
images	动态图片	false	string
city	城市	true	string*/
    private int userid;
    private String dynamic;
    private String images;
    private String city;
    private Map<String, String> params;


    public int getUserid() {
        return userid;
    }

    public PostDynamicParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public String getDynamic() {
        return dynamic;
    }

    public PostDynamicParam setDynamic(String dynamic) {
        this.dynamic = dynamic;
        params.put("dynamic", Base64Util.makeUidToBase64(dynamic));
        return this;
    }

    public String getImages() {
        return images;
    }

    public PostDynamicParam setImages(String images) {
        this.images = images;
        params.put("images", images);
        return this;
    }

    public String getCity() {
        return city;
    }

    public PostDynamicParam setCity(String city) {
        this.city = city;
        params.put("city", city);
        return this;
    }


    public PostDynamicParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }
}
