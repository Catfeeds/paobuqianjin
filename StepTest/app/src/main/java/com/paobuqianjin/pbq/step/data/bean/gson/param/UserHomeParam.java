package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/5/21.
 */

public class UserHomeParam {
    /*
    adminid	获取者的用户ID（可以根据秘钥获取）	false	int
    userid	用户id（id和编号必填一个）	false	int
    userno	用户编号（id和编号必填一个）	false	int
    page	动态分页当前页码	false	int	1
    pagesize	动态分页每页显示数量
    * */

    private int adminid;
    private int userid;
    private int userno;
    private int page;
    private int pagesize;
    private Map<String, String> params;

    public UserHomeParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public int getAdminid() {
        return adminid;
    }

    public UserHomeParam setAdminid(int adminid) {
        this.adminid = adminid;
        params.put("adminid", String.valueOf(adminid));
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public UserHomeParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getUserno() {
        return userno;
    }

    public UserHomeParam setUserno(int userno) {
        this.userno = userno;
        params.put("userno", String.valueOf(userno));
        return this;
    }

    public int getPage() {
        return page;
    }

    public UserHomeParam setPage(int page) {
        this.page = page;
        params.put("page", String.valueOf(page));
        return this;
    }

    public int getPagesize() {
        return pagesize;
    }

    public UserHomeParam setPagesize(int pagesize) {
        this.pagesize = pagesize;
        params.put("pagesize", String.valueOf(pagesize));
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
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
        return "UserHomeParam{" +
                "adminid=" + adminid +
                ", userid=" + userid +
                ", userno=" + userno +
                ", page=" + page +
                ", pagesize=" + pagesize +
                ", params=" + params +
                '}';
    }
}
