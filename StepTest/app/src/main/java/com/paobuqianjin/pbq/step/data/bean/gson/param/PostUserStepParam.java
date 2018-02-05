package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/2/5.
 */
/*
@className :PostUserStepParam
*@date 2018/2/5
*@author
*@description 更新用户步数
*/
public class PostUserStepParam {
    /*
    * userid	用户ID	true	string
step_number	用户步数	true	string
*/
    private String userid;
    private String step_number;
    private Map<String, String> params;


    public PostUserStepParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }


    @Override
    public String toString() {
        return "PostUserStepParam{" +
                "userid='" + userid + '\'' +
                ", step_number='" + step_number + '\'' +
                '}';
    }


    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }

    public String getUserid() {
        return userid;
    }

    public PostUserStepParam setUserid(String userid) {
        this.userid = userid;
        params.put("userid", userid);
        return this;
    }

    public String getStep_number() {
        return step_number;
    }

    public PostUserStepParam setStep_number(String step_number) {
        this.step_number = step_number;
        params.put("step_number", step_number);
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
