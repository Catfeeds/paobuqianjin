package com.paobuqianjin.pbq.step.data.bean.gson.param;

import com.paobuqianjin.pbq.step.utils.MD5;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/4/2.
 */

public class PostPassByOldParam {
    /*
    * userid	用户ID	true	string
old_password	原密码	true	string
new_password	新密码	true	string	*/
    private int userid;
    private String old_password;
    private String new_password;

    private Map<String, String> params;

    public PostPassByOldParam() {
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

    public int getUserid() {
        return userid;
    }

    public PostPassByOldParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public String getOld_password() {
        return old_password;
    }

    public PostPassByOldParam setOld_password(String old_password) {
        this.old_password = old_password;
        String md5PassWord = MD5.md5Slat(old_password);
        params.put("old_password", md5PassWord);
        return this;
    }

    public String getNew_password() {
        return new_password;
    }

    public PostPassByOldParam setNew_password(String new_password) {
        this.new_password = new_password;
        String md5PassWord = MD5.md5Slat(new_password);
        params.put("new_password", md5PassWord);
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "PostPassByOldParam{" +
                "userid=" + userid +
                ", old_password='" + old_password + '\'' +
                ", new_password='" + new_password + '\'' +
                ", params=" + params +
                '}';
    }
}
