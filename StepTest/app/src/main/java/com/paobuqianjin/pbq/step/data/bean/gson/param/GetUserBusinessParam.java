package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/3.
 */

public class GetUserBusinessParam {
    private Map<String, String> params;
    private int userid;
    private int page;
    private int pagesize;

    public GetUserBusinessParam() {
        params = new LinkedHashMap<>();
    }

    public int getUserid() {
        return userid;
    }

    public GetUserBusinessParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getPage() {
        return page;
    }

    public GetUserBusinessParam setPage(int page) {
        this.page = page;
        params.put("page", String.valueOf(page));
        return this;
    }

    public int getPagesize() {
        return pagesize;
    }

    public GetUserBusinessParam setPagesize(int pagesize) {
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
}
