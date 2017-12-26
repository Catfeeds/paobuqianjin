package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/26.
 */

public class PostMessageParam {
    /*
    userid	接收方id	true	string
    from	发送方id	true	string
    typeid	消息类型id	true	string
    title	消息题目	true	string
    content	消息内容	true	string
    * */
    private String userid;
    private String from;
    private String typeid;
    private String title;
    private String content;
    private Map<String, String> param;

    public String getUserid() {
        return userid;
    }

    public PostMessageParam setUserid(String userid) {
        this.userid = userid;
        param.put("userid", userid);
        return this;
    }

    public String getFrom() {
        return from;
    }

    public PostMessageParam setFrom(String from) {
        this.from = from;
        param.put("from", from);
        return this;
    }

    public String getTypeid() {
        return typeid;
    }

    public PostMessageParam setTypeid(String typeid) {
        this.typeid = typeid;
        param.put("typeid", typeid);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PostMessageParam setTitle(String title) {
        this.title = title;
        param.put("title", title);
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostMessageParam setContent(String content) {
        this.content = content;
        param.put("content", content);
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public PostMessageParam() {
        if (param == null) {
            param = new LinkedHashMap<>();
        }
    }


}
