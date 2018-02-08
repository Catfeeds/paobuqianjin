package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2018/2/7.
 */
/*
@className :BindCardPostParam
*@date 2018/2/7
*@author
*@description 绑定提现账号接口
*/
public class BindCardPostParam {
    /*userid	用户ID	true	int	1
typeid	提现类型 1微信 2支付宝 3银行卡	true	string	1
realname	真实姓名	true	int	1
bank_card	账户 typeid为1填写微信openid｜typeid为2填写支付宝账号｜typeid为3填写银行账号	true	string	1
opening_bank	开户行	false	string	1
subbranch	开户支行	false	string	1
region	开户地区	false	string	1
    * */

    private int userid;
    private int typeid;
    private String realname;
    private String bank_card;
    private String opening_bank;
    private String subbranch;
    private String region;
    private Map<String, String> params;

    public BindCardPostParam() {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
    }

    @Override
    public String toString() {
        return "BindCardPostParam{" +
                "userid=" + userid +
                ", typeid=" + typeid +
                ", realname='" + realname + '\'' +
                ", bank_card='" + bank_card + '\'' +
                ", opening_bank='" + opening_bank + '\'' +
                ", subbranch='" + subbranch + '\'' +
                ", region='" + region + '\'' +
                ", params=" + params +
                '}';
    }

    public int getUserid() {
        return userid;
    }

    public BindCardPostParam setUserid(int userid) {
        this.userid = userid;
        params.put("userid", String.valueOf(userid));
        return this;
    }

    public int getTypeid() {
        return typeid;
    }

    public BindCardPostParam setTypeid(int typeid) {
        this.typeid = typeid;
        params.put("typeid", String.valueOf(typeid));
        return this;
    }

    public String getRealname() {
        return realname;
    }

    public BindCardPostParam setRealname(String realname) {
        this.realname = realname;
        params.put("realname", realname);
        return this;
    }

    public String getBank_card() {
        return bank_card;
    }

    public BindCardPostParam setBank_card(String bank_card) {
        this.bank_card = bank_card;
        params.put("bank_card", bank_card);
        return this;
    }

    public String getOpening_bank() {
        return opening_bank;
    }

    public BindCardPostParam setOpening_bank(String opening_bank) {
        this.opening_bank = opening_bank;
        params.put("bank_card", opening_bank);
        return this;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public BindCardPostParam setSubbranch(String subbranch) {
        this.subbranch = subbranch;
        params.put("subbranch", subbranch);
        return this;
    }

    public String getRegion() {
        return region;
    }

    public BindCardPostParam setRegion(String region) {
        this.region = region;
        params.put("region", region);
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String paramString() {
        String temp = "";
        for (String key : params.keySet()) {
            temp = temp + key + ":" + params.get(key) + "\n";
        }
        return temp;
    }
}
