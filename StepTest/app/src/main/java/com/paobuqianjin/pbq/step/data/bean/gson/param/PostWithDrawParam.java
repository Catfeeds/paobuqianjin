package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pbq on 2017/12/25.
 */

public class PostWithDrawParam {
    private String type;
    private String amount;
    private String id;
    private Map<String, String> param;

    public PostWithDrawParam() {
        if (param == null) {
            param = new LinkedHashMap<>();
        }
    }


    public String getType() {
        return type;
    }

    public PostWithDrawParam setType(String type) {
        this.type = type;
        param.put("type", type);
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public PostWithDrawParam setAmount(String amount) {
        this.amount = amount;
        param.put("amount", amount);
        return this;
    }

    public String getId() {
        return id;
    }

    public PostWithDrawParam setId(String id) {
        this.id = id;
        param.put("id", id);
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
