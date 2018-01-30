package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/1/27.
 */
/*
@className :CircleOrderParam
*@date 2018/1/27
*@author
*@description  圈子订单参数
*/
public class CircleOrderParam {
    /*typeid	订单类型 1钱包 2微信 3支付宝 4银行卡	true	int
userid	用户ID	true	int
circleid	圈子ID	true	int
total_fee	充值金额	true	float	*/
    private int typeid;
    private int userid;
    private int circleid;
    private int total_fee;
    private Map<String, String> params;

    public CircleOrderParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public int getTypeid() {
        return typeid;
    }

    public CircleOrderParam setTypeid(int typeid) {
        this.typeid = typeid;
        params.put("typeid", String.valueOf(typeid));
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public CircleOrderParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getCircleid() {
        return circleid;
    }

    public CircleOrderParam setCircleid(int circleid) {
        this.circleid = circleid;
        params.put("circleid", String.valueOf(circleid));
        return this;
    }

    public float getTotal_fee() {
        return total_fee;
    }

    @Override
    public String toString() {
        return "CircleOrderParam{" +
                "typeid=" + typeid +
                ", userid=" + userid +
                ", circleid=" + circleid +
                ", total_fee=" + total_fee +
                ", params=" + params +
                '}';
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public CircleOrderParam setTotal_fee(int total_fee) {
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
