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
    private float total_fee;
    private Map<String, String> param;

    public CircleOrderParam() {
        if (param == null) {
            param = new LinkedHashMap<>();
        }
    }

    public int getTypeid() {
        return typeid;
    }

    public CircleOrderParam setTypeid(int typeid) {
        this.typeid = typeid;
        param.put("typeid", String.valueOf(typeid));
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public CircleOrderParam setUserid(int userid) {
        this.userid = userid;
        param.put("userid", String.valueOf(userid));
        return this;
    }

    public int getCircleid() {
        return circleid;
    }

    public CircleOrderParam setCircleid(int circleid) {
        this.circleid = circleid;
        param.put("circleid", String.valueOf(circleid));
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
                ", param=" + param +
                '}';
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public CircleOrderParam setTotal_fee(float total_fee) {
        this.total_fee = total_fee;
        param.put("total_fee", String.valueOf(total_fee));
        return this;
    }
}
