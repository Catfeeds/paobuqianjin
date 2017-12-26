package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */

public class AllIncomeResponse {

    /**
     * error : 0
     * message : success
     * data : [{"creattime":1512628228,"amount":"0.50","name":"运动红包"},{"creattime":1512714628,"amount":"0.80","name":"运动红包"},{"creattime":1512714628,"amount":"0.15","name":"运动红包"},{"creattime":1512628228,"amount":"0.28","name":"运动红包"},{"creattime":1512714628,"amount":"1.28","name":"运动红包"},{"creattime":1512835200,"amount":"6.66","name":"运动红包"},{"creattime":1513581442,"amount":"8.88","name":"邀请红包"},{"creattime":1513597298,"amount":"8.88","name":"运动红包"},{"creattime":1512005565,"amount":"99.99","name":"邀请红包"}]
     */

    private int error;
    private String message;
    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * creattime : 1512628228
         * amount : 0.50
         * name : 运动红包
         */

        private int creattime;
        private String amount;
        private String name;

        public int getCreattime() {
            return creattime;
        }

        public void setCreattime(int creattime) {
            this.creattime = creattime;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
