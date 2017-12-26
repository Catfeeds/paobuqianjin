package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */

public class IncomeTypeResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":1,"name":"运动红包","userid":1,"typeid":1,"circleid":100000,"amount":"0.50","creattime":1512628228},{"id":2,"name":"运动红包","userid":1,"typeid":1,"circleid":100000,"amount":"0.80","creattime":1512714628},{"id":3,"name":"运动红包","userid":1,"typeid":1,"circleid":100000,"amount":"0.15","creattime":1512714628},{"id":4,"name":"运动红包","userid":1,"typeid":1,"circleid":100000,"amount":"0.28","creattime":1512628228},{"id":5,"name":"运动红包","userid":1,"typeid":1,"circleid":100000,"amount":"1.28","creattime":1512714628},{"id":6,"name":"运动红包","userid":1,"typeid":1,"circleid":100000,"amount":"6.66","creattime":1512835200},{"id":7,"name":"邀请红包","userid":1,"typeid":2,"circleid":100000,"amount":"8.88","creattime":1513581442},{"id":8,"name":"运动红包","userid":1,"typeid":1,"circleid":100000,"amount":"8.88","creattime":1513597298},{"id":9,"name":"邀请红包","userid":1,"typeid":2,"circleid":1,"amount":"99.99","creattime":1512005565}]
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
         * id : 1
         * name : 运动红包
         * userid : 1
         * typeid : 1
         * circleid : 100000
         * amount : 0.50
         * creattime : 1512628228
         */

        private int id;
        private String name;
        private int userid;
        private int typeid;
        private int circleid;
        private String amount;
        private int creattime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getCircleid() {
            return circleid;
        }

        public void setCircleid(int circleid) {
            this.circleid = circleid;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getCreattime() {
            return creattime;
        }

        public void setCreattime(int creattime) {
            this.creattime = creattime;
        }
    }
}
