package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/1/27.
 */
/*
@className :PayOrderParam
*@date 2018/1/27
*@author
*@description  微信支付订单参数
*/
public class PayOrderParam {
    public String getPayment_type() {
        return payment_type;
    }

    public PayOrderParam setPayment_type(String payment_type) {
        this.payment_type = payment_type;
        params.put("payment_type", payment_type);
        return this;
    }

    /*
        * action	circle圈子订单 | user用户订单 | task任务订单	true	string
     userid	用户ID	true	int
     total_fee	充值金额	true	float
     circleid	圈子ID	false	int
     taskid	任务ID	false	int

     payment_type	支付类型 wx微信支付 | ali支付宝支付	true	int
order_type	circle圈子订单 | user用户订单 | task任务订单	true	string
userid	用户ID	true	int
total_fee	充值金额	true	float
circleid	圈子ID	false	int
taskid	任务ID	false	int
     */
    private String payment_type;
    private String taskno;
    private int userid;
    private int circleid;
    private float total_fee;

    public String getOrder_type() {
        return order_type;
    }

    public PayOrderParam setOrder_type(String order_type) {
        this.order_type = order_type;
        params.put("order_type", order_type);
        return this;
    }

    private String order_type;
    private Map<String, String> params;


    public PayOrderParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public String getTaskno() {
        return taskno;
    }

    public PayOrderParam setTaskno(String taskno) {
        this.taskno = taskno;
        params.put("taskno",taskno);
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public PayOrderParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getCircleid() {
        return circleid;
    }

    public PayOrderParam setCircleid(int circleid) {
        this.circleid = circleid;
        params.put("circleid", String.valueOf(circleid));
        return this;
    }

    public float getTotal_fee() {
        return total_fee;
    }

    @Override
    public String toString() {
        return "PayOrderParam{" +
                "payment_type='" + payment_type + '\'' +
                ", taskid=" + taskno +
                ", userid=" + userid +
                ", circleid=" + circleid +
                ", total_fee=" + total_fee +
                ", order_type='" + order_type + '\'' +
                ", params=" + params +
                '}';
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public PayOrderParam setTotal_fee(float total_fee) {
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
