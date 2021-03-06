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
    private String voucher_name;

    public String getVoucher_name() {
        return voucher_name;
    }

    public TaskSponsorParam setVoucher_name(String voucher_name) {
        this.voucher_name = voucher_name;
        params.put("voucher_name", voucher_name);
        return this;
    }

    public String getSpend_money() {
        return spend_money;
    }

    public TaskSponsorParam setSpend_money(String spend_money) {
        this.spend_money = spend_money;
        params.put("spend_money", spend_money);
        return this;
    }

    public String getDeduction_money() {
        return deduction_money;
    }

    public TaskSponsorParam setDeduction_money(String deduction_money) {
        this.deduction_money = deduction_money;
        params.put("deduction_money", deduction_money);
        return this;
    }

    public String getValid_day() {
        return valid_day;
    }

    public TaskSponsorParam setValid_day(String valid_day) {
        this.valid_day = valid_day;
        params.put("valid_day", valid_day);
        return this;
    }

    public String getVoucher_number() {
        return voucher_number;
    }

    public TaskSponsorParam setVoucher_number(String voucher_number) {
        this.voucher_number = voucher_number;
        params.put("voucher_number", voucher_number);
        return this;
    }

    private String spend_money;
    private String deduction_money;
    private String valid_day;
    private String voucher_number;

    public String getCircleid() {
        return circleid;
    }

    public TaskSponsorParam setCircleid(String circleid) {
        this.circleid = circleid;
        params.put("circleid", circleid);
        return this;
    }

    public String getCircle_pwd() {
        return circle_pwd;
    }

    public TaskSponsorParam setCircle_pwd(String circle_pwd) {
        this.circle_pwd = circle_pwd;
        params.put("circle_pwd", circle_pwd);
        return this;
    }

    private String circleid;
    private String circle_pwd;

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

    public TaskSponsorParam setImages(String images) {
        this.images = images;
        params.put("images", images);
        return this;
    }

    private String images;

    public TaskSponsorParam() {
        if (params == null) {
            params = new HashMap<>();
        }
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
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
