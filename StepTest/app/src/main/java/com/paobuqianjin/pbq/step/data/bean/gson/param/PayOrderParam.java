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
    private int red_id;

    public PayOrderParam setApply_guide_id(String apply_guide_id) {
        this.apply_guide_id = apply_guide_id;
        params.put("apply_guide_id", apply_guide_id);
        return this;
    }

    private String apply_guide_id;

    public String getRed_map_id() {
        return red_map_id;
    }

    public PayOrderParam setRed_map_id(String red_map_id) {
        this.red_map_id = red_map_id;
        params.put("red_map_id", red_map_id);
        return this;
    }

    private String red_map_id;//遍地红包充值ID

    public String getDeviceType() {
        return deviceType;
    }

    public PayOrderParam setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        params.put("deviceType", deviceType);
        return this;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public PayOrderParam setTerminalId(String terminalId) {
        this.terminalId = terminalId;
        params.put("terminalId", terminalId);
        return this;
    }

    /*增加云闪付，设备类型，1，安卓，2，苹果
        * 设备IMEI号*/
    private String deviceType;
    private String terminalId;

    public String getCvip_no() {
        return cvip_no;
    }

    public PayOrderParam setCvip_no(String cvip_no) {
        this.cvip_no = cvip_no;
        params.put("cvip_no", cvip_no);
        return this;
    }

    private String cvip_no;

    public String getGvip_no() {
        return gvip_no;
    }

    public PayOrderParam setGvip_no(String gvip_no) {
        this.gvip_no = gvip_no;
        params.put("gvip_no", gvip_no);
        return this;
    }

    private String gvip_no;

    public String getVip_no() {
        return vip_no;
    }

    public PayOrderParam setVip_no(String vip_no) {
        this.vip_no = vip_no;
        params.put("vip_no", vip_no);
        return this;
    }


    private String vip_no;

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
        params.put("taskno", taskno);
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

    public int getRed_id() {
        return red_id;
    }

    public PayOrderParam setRed_id(int red_id) {
        this.red_id = red_id;
        params.put("red_id", String.valueOf(red_id));
        return this;
    }

    public float getTotal_fee() {
        return total_fee;
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

    @Override
    public String toString() {
        return "PayOrderParam{" +
                "payment_type='" + payment_type + '\'' +
                ", taskno='" + taskno + '\'' +
                ", userid=" + userid +
                ", circleid=" + circleid +
                ", total_fee=" + total_fee +
                ", red_id=" + red_id +
                ", cvip_no='" + cvip_no + '\'' +
                ", vip_no='" + vip_no + '\'' +
                ", order_type='" + order_type + '\'' +
                ", params=" + params +
                '}';
    }
}
