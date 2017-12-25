package com.paobuqianjin.pbq.step.data.bean.gson;

import java.util.Map;

/**
 * Created by pbq on 2017/12/25.
 */

public class AuthenticationParam {
    private String id;
    private String idcard;
    private String realname;
    private Map<String, String> param;


    @Override
    public String toString() {
        return "AuthenticationParam{" +
                "id='" + id + '\'' +
                ", idcard='" + idcard + '\'' +
                ", realname='" + realname + '\'' +
                ", param=" + param +
                '}';
    }

    public String getId() {
        return id;
    }

    public AuthenticationParam setId(String id) {
        this.id = id;
        param.put("id", String.valueOf(id));
        return this;
    }

    public String getIdcard() {
        return idcard;
    }

    public AuthenticationParam setIdcard(String idcard) {
        this.idcard = idcard;
        param.put("idcard", String.valueOf(idcard));
        return this;
    }

    public String getRealname() {
        return realname;
    }

    public AuthenticationParam setRealname(String realname) {
        this.realname = realname;
        param.put("realname", String.valueOf(realname));
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
