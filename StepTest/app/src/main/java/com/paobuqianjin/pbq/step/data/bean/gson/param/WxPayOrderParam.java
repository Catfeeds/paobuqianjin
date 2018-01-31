package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/1/27.
 */
/*
@className :WxPayOrderParam
*@date 2018/1/27
*@author
*@description  微信支付订单参数
*/
public class WxPayOrderParam {
    public String getAction() {
        return action;
    }

    public WxPayOrderParam setAction(String action) {
        this.action = action;
        params.put("action", action);
        return this;
    }

    /*
        * action	circle圈子订单 | user用户订单 | task任务订单	true	string
     userid	用户ID	true	int
     total_fee	充值金额	true	float
     circleid	圈子ID	false	int
     taskid	任务ID	false	int
     */
    private String action;
    private int taskid;
    private int userid;
    private int circleid;
    private int total_fee;
    private Map<String, String> params;

    public WxPayOrderParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public int getTaskid() {
        return taskid;
    }

    public WxPayOrderParam setTaskid(int taskid) {
        this.taskid = taskid;
        params.put("taskid", String.valueOf(taskid));
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public WxPayOrderParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getCircleid() {
        return circleid;
    }

    public WxPayOrderParam setCircleid(int circleid) {
        this.circleid = circleid;
        params.put("circleid", String.valueOf(circleid));
        return this;
    }

    public float getTotal_fee() {
        return total_fee;
    }

    @Override
    public String toString() {
        return "WxPayOrderParam{" +
                "action='" + action + '\'' +
                ", taskid=" + taskid +
                ", userid=" + userid +
                ", circleid=" + circleid +
                ", total_fee=" + total_fee +
                '}';
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public WxPayOrderParam setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
        params.put("total_fee", String.valueOf(total_fee));
        return this;
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }
}
