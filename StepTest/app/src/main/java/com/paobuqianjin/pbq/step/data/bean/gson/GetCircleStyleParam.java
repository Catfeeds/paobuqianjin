package com.paobuqianjin.pbq.step.data.bean.gson;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/23.
 */

public class GetCircleStyleParam {
    /*
    action	my我的圈子｜choice精选圈子｜ recharge充值排行榜 | step步数排行榜 | all我的和精选圈子	false	string	my
    userid	用户ID	false	int	1
    page	当前页码	false	int	1
    pagesize	每页显示数量	false	int	10
    * */
    private String action;
    private int userid;
    private int page;
    private int pagesize;
    private Map<String, String> param;

    public GetCircleStyleParam() {
        if (param == null) {
            param = new LinkedHashMap<>();
        }
    }

    @Override
    public String toString() {
        return "GetCircleStyleParam{" +
                "action='" + action + '\'' +
                ", userid=" + userid +
                ", page=" + page +
                ", pagesize=" + pagesize +
                ", param=" + param +
                '}';
    }

    public String getAction() {
        return action;
    }

    public GetCircleStyleParam setAction(String action) {
        this.action = action;
        param.put("action", action);
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public GetCircleStyleParam setUserid(int userid) {
        this.userid = userid;
        param.put("userid", String.valueOf(userid));
        return this;
    }

    public int getPage() {
        return page;
    }

    public GetCircleStyleParam setPage(int page) {
        this.page = page;
        param.put("page", String.valueOf(page));
        return this;
    }

    public int getPagesize() {
        return pagesize;
    }

    public GetCircleStyleParam setPagesize(int pagesize) {
        this.pagesize = pagesize;
        param.put("pagesize", String.valueOf(pagesize));
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
