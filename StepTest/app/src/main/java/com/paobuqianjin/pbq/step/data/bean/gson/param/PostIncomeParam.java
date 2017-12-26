package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/25.
 */

public class PostIncomeParam {
    private int userid;
    private int typeid;
    private int circleid;
    private float amount;

    public PostIncomeParam() {
        if (param == null) {
            param = new LinkedHashMap<>();
        }
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    private Map<String, String> param;

    public int getUserid() {
        return userid;
    }

    public PostIncomeParam setUserid(int userid) {
        this.userid = userid;
        param.put("userid", String.valueOf(userid));
        return this;
    }

    public int getTypeid() {
        return typeid;
    }

    public PostIncomeParam setTypeid(int typeid) {
        this.typeid = typeid;
        param.put("typeid", String.valueOf(typeid));
        return this;
    }

    public int getCircleid() {
        return circleid;
    }

    public PostIncomeParam setCircleid(int circleid) {
        this.circleid = circleid;
        param.put("circleid", String.valueOf(circleid));
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public PostIncomeParam setAmount(float amount) {
        this.amount = amount;
        param.put("amount", String.valueOf(amount));
        return this;
    }

    @Override
    public String toString() {
        return "PostIncomeParam{" +
                "userid=" + userid +
                ", typeid=" + typeid +
                ", circleid=" + circleid +
                ", amount=" + amount +
                '}';
    }
}
