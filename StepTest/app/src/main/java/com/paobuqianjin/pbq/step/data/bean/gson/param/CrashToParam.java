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
    userid	用户ID	false	string
typeid	提现方式（不传默认为微信提现）：1微信|2支付宝|3银行卡	false	string
wx_openid	app对应的openid（备注：小程序的未开通）	true	string
amount	提现金额
   */
    private String userid;
    private String amount;
    private String wx_openid;
    private String typeid;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    private Map<String, String> params;


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

    public String getUserid() {
        return userid;
    }

    public CrashToParam setUserid(String userid) {
        this.userid = userid;
        params.put("userid", userid);
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

    public String getWx_openid() {
        return wx_openid;
    }

    public CrashToParam setWx_openid(String wx_openid) {
        this.wx_openid = wx_openid;
        params.put("wx_openid", wx_openid);
        return this;
    }

    public String getTypeid() {
        return typeid;
    }

    public CrashToParam setTypeid(String typeid) {
        this.typeid = typeid;
        params.put("typeid", typeid);
        return this;
    }

    @Override
    public String toString() {
        return "CrashToParam{" +
                "userid='" + userid + '\'' +
                ", amount='" + amount + '\'' +
                ", wx_openid='" + wx_openid + '\'' +
                ", typeid='" + typeid + '\'' +
                ", params=" + params +
                '}';
    }
}
