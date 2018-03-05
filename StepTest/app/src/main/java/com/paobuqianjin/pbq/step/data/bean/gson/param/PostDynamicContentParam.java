package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/3/5.
 */
/*
@className :PostDynamicContentParam
*@date 2018/3/5
*@author
*@description 发表评论参数
*/
public class PostDynamicContentParam {
    /*
    parent_id	评论ID	true	int
reply_userid	被评论者用户ID	true	int
userid	评论者用户ID	true	int
dynamicid	动态ID	true	int
content	评论内容	true	string
    * */
    private Map<String, String> params;
    private int parent_id;
    private int reply_userid;
    private int userid;
    private int dynamicid;
    private String content;

    public PostDynamicContentParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public int getParent_id() {
        return parent_id;
    }

    public PostDynamicContentParam setParent_id(int parent_id) {
        this.parent_id = parent_id;
        params.put("parent_id", String.valueOf(parent_id));
        return this;
    }

    public int getReply_userid() {
        return reply_userid;
    }

    public PostDynamicContentParam setReply_userid(int reply_userid) {
        this.reply_userid = reply_userid;
        params.put("reply_userid", String.valueOf(reply_userid));
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public PostDynamicContentParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getDynamicid() {
        return dynamicid;
    }

    public PostDynamicContentParam setDynamicid(int dynamicid) {
        this.dynamicid = dynamicid;
        params.put("dynamicid", String.valueOf(dynamicid));
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostDynamicContentParam setContent(String content) {
        this.content = content;
        params.put("content", content);
        return this;
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }

    @Override
    public String toString() {
        return "PostDynamicContentParam{" +
                "params=" + params +
                ", parent_id=" + parent_id +
                ", reply_userid=" + reply_userid +
                ", userid=" + userid +
                ", dynamicid=" + dynamicid +
                ", content='" + content + '\'' +
                '}';
    }
}
