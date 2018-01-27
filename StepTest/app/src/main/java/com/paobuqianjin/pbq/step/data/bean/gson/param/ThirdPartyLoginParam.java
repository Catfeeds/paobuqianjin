package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/1/27.
 */

public class ThirdPartyLoginParam {
    /*
    * action	登陆方式 wx 微信登陆 | qq QQ登陆	true	string
openid	openid	true	string
nickname	昵称	true	string
avatar	头像	true	string
province	省份	true	string
city	城市	true	string
sex	性别 0男 1女	true	int*/
    private String action;
    private String openid;
    private String nickname;
    private String avatar;
    private String province;
    private String city;
    private int sex;


    public ThirdPartyLoginParam() {
        if (param == null) {
            param = new LinkedHashMap<>();
        }
    }

    public Map<String, String> getParam() {
        return param;
    }

    public ThirdPartyLoginParam setParam(Map<String, String> param) {
        this.param = param;
        return this;
    }

    private Map<String, String> param;

    public String getAction() {
        return action;
    }

    public ThirdPartyLoginParam setAction(String action) {
        this.action = action;
        param.put("action", action);
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public ThirdPartyLoginParam setOpenid(String openid) {
        this.openid = openid;
        param.put("openid", openid);
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public ThirdPartyLoginParam setNickname(String nickname) {
        this.nickname = nickname;
        param.put("nickname", nickname);
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public ThirdPartyLoginParam setAvatar(String avatar) {
        this.avatar = avatar;
        param.put("avatar", avatar);
        return this;
    }

    public String getProvince() {
        return province;
    }

    public ThirdPartyLoginParam setProvince(String province) {
        this.province = province;
        param.put("province", province);
        return this;
    }

    public String getCity() {
        return city;
    }

    public ThirdPartyLoginParam setCity(String city) {
        this.city = city;
        param.put("city", city);
        return this;
    }

    public int getSex() {
        return sex;
    }

    public ThirdPartyLoginParam setSex(int sex) {
        this.sex = sex;
        param.put("sex", String.valueOf(sex));
        return this;
    }

    @Override
    public String toString() {
        return "ThirdPartyLoginParam{" +
                "action='" + action + '\'' +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sex=" + sex +
                ", param=" + param +
                '}';
    }
}
