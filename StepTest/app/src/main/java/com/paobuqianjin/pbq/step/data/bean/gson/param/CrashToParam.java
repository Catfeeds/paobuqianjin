package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/2/8.
 */
/*
@className :CrashToParam
*@date 2018/2/8
*@author
*@description 提现参数
*/
public class CrashToParam {
    /*
    * id	提现方式ID	true	string
amount	提现金额	true	string
*/
    private int id;
    private String amount;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    private Map<String, String> params;

    public int getId() {
        return id;
    }

    public CrashToParam setId(int id) {
        this.id = id;
        params.put("id", String.valueOf(id));
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public CrashToParam setAmount(String amount) {
        this.amount = amount;
        params.put("amount", amount);
        return this;
    }

    @Override
    public String toString() {
        return "CrashToParam{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                '}';
    }

    public CrashToParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }
}
