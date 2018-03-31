package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/26.
 */

public class FeedBackParam {
    /*typeid	反馈类型	true	string
content	反馈内容	true	string
mobile	用户手机号	true	string
    * */
    private String typeid;
    private String content;
    private String mobile;
    private Map<String, String> param;

    public FeedBackParam() {
        param = new LinkedHashMap<>();
    }

    public String getTypeid() {
        return typeid;
    }

    public FeedBackParam setTypeid(String typeid) {
        this.typeid = typeid;
        param.put("typeid", typeid);
        return this;
    }

    public String getContent() {
        return content;
    }

    public FeedBackParam setContent(String content) {
        this.content = content;
        param.put("content", content);
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public FeedBackParam setMobile(String mobile) {
        this.mobile = mobile;
        param.put("mobile", mobile);
        return this;
    }

    public String paramString() {
        String temp = "";
        for (String key : param.keySet()) {
            temp = temp + key + ":" + param.get(key) + "\n";
        }
        return temp;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
