package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/24.
 * 商家发红包参数
 */
public class TaskSponsorParam {
    private String userid;
    private float longitude;
    private float latitude;
    private int number;
    private String money;
    private String day;
    private String red_name;
    private String red_content;
    private String sex;
    private String businessid;
    private String distance;
    private String step;
    private String age_min;
    private String age_max;
    private String education;
    private String s_time;
    private String e_time;
    private String city;
    private String city_code;
    private String remark;
    private String trading_area;

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    private String target_url;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    private String images;

    public TaskSponsorParam() {
        if (params == null) {
            params = new HashMap<>();
        }
    }

    public TaskSponsorParam(String userid, int number, String money, String day, String red_name) {
        super();
        this.userid = userid;
        this.number = number;
        this.money = money;
        this.day = day;
        this.red_name = red_name;
    }

    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
        params.put("userid", userid);
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
        params.put("longitude", longitude + "");
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
        params.put("latitude", latitude + "");
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        params.put("number", number + "");
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;

        params.put("money", money + "");
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
        params.put("day", day + "");
    }

    public String getRed_name() {
        return red_name;
    }

    public void setRed_name(String red_name) {
        this.red_name = red_name;
        params.put("red_name", red_name + "");
    }

    public String getRed_content() {
        return red_content;
    }

    public void setRed_content(String red_content) {
        this.red_content = red_content;
        params.put("red_content", red_content + "");
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        params.put("sex", sex + "");
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
        params.put("businessid", businessid + "");
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
        params.put("distance", distance + "");
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
        params.put("step", step + "");
    }

    public String getAge_min() {
        return age_min;
    }

    public void setAge_min(String age_min) {
        this.age_min = age_min;
        params.put("age_min", age_min + "");
    }

    public String getAge_max() {
        return age_max;
    }

    public void setAge_max(String age_max) {
        this.age_max = age_max;
        params.put("age_max", age_max + "");
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
        params.put("education", education + "");
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
        params.put("s_time", s_time + "");
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
        params.put("e_time", e_time + "");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        params.put("city", city + "");
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
        params.put("city_code", city_code + "");
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        params.put("remark", remark + "");
    }

    public String getTrading_area() {
        return trading_area;
    }

    public void setTrading_area(String trading_area) {
        this.trading_area = trading_area;
        params.put("trading_area", trading_area + "");
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
