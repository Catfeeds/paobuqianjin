package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/26.
 */

public class DynamicParam {
    /*
    * userid	用户ID	true	int
dynamic	动态内容	false	string
images	动态图片	false	string
city	城市	true	string
vote	点赞数	true	int*/
    private int userid;
    private String dynamic;
    private String images;
    private String city;
    private int vote;
    private Map<String, String> param;


    public int getUserid() {
        return userid;
    }

    public DynamicParam setUserid(int userid) {
        this.userid = userid;
        param.put("userid", String.valueOf(userid));
        return this;
    }

    public String getDynamic() {
        return dynamic;
    }

    public DynamicParam setDynamic(String dynamic) {
        this.dynamic = dynamic;
        param.put("dynamic", dynamic);
        return this;
    }

    public String getImages() {
        return images;
    }

    public DynamicParam setImages(String images) {
        this.images = images;
        param.put("images", images);
        return this;
    }

    public String getCity() {
        return city;
    }

    public DynamicParam setCity(String city) {
        this.city = city;
        param.put("city", city);
        return this;
    }

    public int getVote() {
        return vote;
    }

    public DynamicParam() {
        if (param == null) {
            param = new LinkedHashMap<>();
        }
    }

    public DynamicParam setVote(int vote) {
        this.vote = vote;
        param.put("vote", String.valueOf(vote));
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
