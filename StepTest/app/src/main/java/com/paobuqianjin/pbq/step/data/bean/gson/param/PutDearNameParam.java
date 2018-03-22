package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/22.
 */

public class PutDearNameParam {
    /*
    * id	圈子成员id	true	string
action	remark修改备注名｜admin设为/取消管理员	true	string
nickname	备注名*/
    private int id;
    private String action;
    private String nickname;
    private Map<String, String> params;

    public PutDearNameParam() {
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

    public int getId() {
        return id;
    }

    public PutDearNameParam setId(int id) {
        this.id = id;
        params.put("id", String.valueOf(id));
        return this;
    }

    public String getAction() {
        return action;
    }

    public PutDearNameParam setAction(String action) {
        this.action = action;
        params.put("action", action);
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public PutDearNameParam setNickname(String nickname) {
        this.nickname = nickname;
        params.put("nickname", nickname);
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
        return "PutDearNameParam{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", nickname='" + nickname + '\'' +
                ", params=" + params +
                '}';
    }
}
