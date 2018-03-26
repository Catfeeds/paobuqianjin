package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/26.
 */

public class PutUserInfoParam {
    /*
    * nickname	昵称	false	string
avatar	头像	false	string
sex	性别 0男 1女	false	string
birthyear	生年	false	string
birthmonth	生月	false	string
birthday	生日	false	string
height	身高	false	string
weight	体重	false	string
province	所在省	false	string
city	所在市	false	string
*/
    private Map<String, String> params;
    private String nickname;
    private String avatar;
    private int sex;
    private String birthyear;
    private String birthmonth;
    private String birthday;
    private String height;
    private String weight;
    private String province;
    private String city;

    public PutUserInfoParam() {
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

    public String getNickname() {
        return nickname;
    }

    public PutUserInfoParam setNickname(String nickname) {
        this.nickname = nickname;
        params.put("nickname", nickname);
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public PutUserInfoParam setAvatar(String avatar) {
        this.avatar = avatar;
        params.put("avatar", avatar);
        return this;
    }

    public int getSex() {
        return sex;
    }

    public PutUserInfoParam setSex(int sex) {
        this.sex = sex;
        params.put("sex", String.valueOf(sex));
        return this;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public PutUserInfoParam setBirthyear(String birthyear) {
        this.birthyear = birthyear;
        params.put("birthyear", birthyear);
        return this;
    }

    public String getBirthmonth() {
        return birthmonth;
    }

    public PutUserInfoParam setBirthmonth(String birthmonth) {
        this.birthmonth = birthmonth;
        params.put("birthmonth", birthmonth);
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public PutUserInfoParam setBirthday(String birthday) {
        this.birthday = birthday;
        params.put("birthday", birthday);
        return this;
    }

    public String getHeight() {
        return height;
    }

    public PutUserInfoParam setHeight(String height) {
        this.height = height;
        params.put("height", height);
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public PutUserInfoParam setWeight(String weight) {
        this.weight = weight;
        params.put("weight", weight);
        return this;
    }

    public String getProvince() {
        return province;
    }

    public PutUserInfoParam setProvince(String province) {
        this.province = province;
        params.put("province", province);
        return this;
    }

    public String getCity() {
        return city;
    }

    public PutUserInfoParam setCity(String city) {
        this.city = city;
        params.put("city", city);
        return this;
    }
}
