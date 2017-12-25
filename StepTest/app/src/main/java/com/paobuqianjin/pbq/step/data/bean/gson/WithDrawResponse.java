package com.paobuqianjin.pbq.step.data.bean.gson;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */

public class WithDrawResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":2,"userid":2,"type":1,"amount":"2000.00","actual_amount":"1980","creattime":1513755666}]
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
         * id : 2
         * userid : 2
         * type : 1
         * amount : 2000.00
         * actual_amount : 1980
         * creattime : 1513755666
         */

        private int id;
        private int userid;
        private int type;
        private String amount;
        private String actual_amount;
        private int creattime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getActual_amount() {
            return actual_amount;
        }

        public void setActual_amount(String actual_amount) {
            this.actual_amount = actual_amount;
        }

        public int getCreattime() {
            return creattime;
        }

        public void setCreattime(int creattime) {
            this.creattime = creattime;
        }
    }
}
