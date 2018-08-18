package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/23.
 */

public class CreateCircleBodyParam {
    /*
    userid  用户ID      true    int
    typeid	圈子类型	true	int
    name	圈子名称	true	string
    targetid	目标ID	true	int
    mobile	管理员手机号	true	string
    is_recharge	是否充值 0不充值 1充值	true	boolean	0
    red_packet_amount	红包起始金额	true	float	0
    total_amount	红包起止金额	true	float	0
    red_packet	红包个数	true	int	0
    logo	圈子LOGO	true	string	0
    coverid	封面ID	true	int	0
    is_pwd	是否设置密码 0无需密码 1密码验证	true	boolean	0
    password	密码	true	string	0	32
    tags	标签	true	string	0
    description	圈子描述	true	string	0
    city	城市	true	string	0
    longitude	经度	true	float	0
    latitude	纬度	true	float	0
    total_amount	圈子总金额	true	float	0
    * */
    private int userid;
    private String name;
    private int targetid;
    private String mobile;
    private int is_recharge;
    private float red_packet_amount;
    private int red_packet;
    private String logo;
    private int coverid;
    private int is_pwd;
    private String password;
    private String description;
    private String city;
    private float longitude;
    private float latitude;
    private String tagid;

    public float getTotal_amount() {
        return total_amount;
    }

    public CreateCircleBodyParam setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
        params.put("total_amount", String.valueOf(total_amount));
        return this;
    }

    private float total_amount;

    public Map<String, String> getParams() {
        return params;
    }

    protected Map<String, String> params;

    public CreateCircleBodyParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public String getName() {
        return name;
    }

    public CreateCircleBodyParam setName(String name) {
        this.name = name;
        params.put("name", name);
        return this;
    }

    public int getTargetid() {
        return targetid;
    }

    public CreateCircleBodyParam setTargetid(int targetid) {
        this.targetid = targetid;
        params.put("targetid", String.valueOf(targetid));
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CreateCircleBodyParam setMobile(String mobile) {
        this.mobile = mobile;
        params.put("mobile", mobile);
        return this;
    }

    public int isIs_recharge() {
        return is_recharge;
    }

    public CreateCircleBodyParam setIs_recharge(int is_recharge) {
        this.is_recharge = is_recharge;
        params.put("is_recharge", String.valueOf(is_recharge));
        return this;
    }

    public float getRed_packet_amount() {
        return red_packet_amount;
    }

    public CreateCircleBodyParam setRed_packet_amount(float red_packet_amount) {
        this.red_packet_amount = red_packet_amount;
        params.put("red_packet_amount", String.valueOf(red_packet_amount));
        return this;
    }

    public int getRed_packet() {
        return red_packet;
    }

    public CreateCircleBodyParam setRed_packet(int red_packet) {
        this.red_packet = red_packet;
        params.put("red_packet", String.valueOf(red_packet));
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public CreateCircleBodyParam setLogo(String logo) {
        this.logo = logo;
        params.put("logo", logo);
        return this;
    }

    public int getCoverid() {
        return coverid;
    }

    public CreateCircleBodyParam setCoverid(int coverid) {
        this.coverid = coverid;
        params.put("coverid", String.valueOf(coverid));
        return this;
    }

    public int isIs_pwd() {
        return is_pwd;
    }

    public CreateCircleBodyParam setIs_pwd(int is_pwd) {
        this.is_pwd = is_pwd;
        params.put("is_pwd", String.valueOf(is_pwd));
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateCircleBodyParam setPassword(String password) {
        this.password = password;
        params.put("password", password);
        if (password == null) {
            params.remove("password");
        }
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateCircleBodyParam setDescription(String description) {
        this.description = description;
        params.put("description", description);
        return this;
    }

    public String getCity() {
        return city;
    }

    public CreateCircleBodyParam setCity(String city) {
        this.city = city;
        params.put("city", city);
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public CreateCircleBodyParam setLongitude(float longitude) {
        this.longitude = longitude;
        params.put("longitude", String.valueOf(longitude));
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public CreateCircleBodyParam setLatitude(float latitude) {
        this.latitude = latitude;
        params.put("latitude", String.valueOf(latitude));
        return this;
    }

    @Override
    public String toString() {
        return "CreateCircleBodyParam{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", targetid=" + targetid +
                ", mobile='" + mobile + '\'' +
                ", is_recharge=" + is_recharge +
                ", red_packet_amount=" + red_packet_amount +
                ", red_packet=" + red_packet +
                ", logo='" + logo + '\'' +
                ", coverid=" + coverid +
                ", is_pwd=" + is_pwd +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", tagid='" + tagid + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude + '}';
    }

    public int getUserid() {
        return userid;
    }

    public CreateCircleBodyParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public int getIs_recharge() {
        return is_recharge;
    }

    public int getIs_pwd() {
        return is_pwd;
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }

    public CreateCircleBodyParam setTagid(String typeId) {
        this.tagid = typeId;
        params.put("tagid", String.valueOf(userid));
        return this;
    }

    public String getTagid() {
        return tagid;
    }
}
