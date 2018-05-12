package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/26.
 */

public class DynamicContentParam {
    /*
userid	用户ID	true	int
dynamicid	动态ID	true	int
content	评论内容	true	string
    * */
    private int userid;
    private int dynamicid;
    private String content;
    private Map<String, String> param;


    public int getDynamicid() {
        return dynamicid;
    }

    public DynamicContentParam setDynamicid(int dynamicid) {
        this.dynamicid = dynamicid;
        param.put("dynamicid", String.valueOf(dynamicid));
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public int getUserid() {
        return userid;
    }

    public DynamicContentParam() {
        if (param == null) {
            param = new LinkedHashMap<>();
        }
    }

    public DynamicContentParam setUserid(int userid) {
        this.userid = userid;
        param.put("userid", String.valueOf(userid));
        return this;
    }

    public String getContent() {
        return content;
    }

    public DynamicContentParam setContent(String content) {
        this.content = content;
        param.put("content", content);
        return this;
    }
}
