package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/20.
 */

public class PostRevRedPkgResponse {

    /**
     * error : 0
     * message : success
     * data : {"userid":"30","amount":"1.00"}
     */

    private int error;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PostRevRedPkgResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * userid : 30
         * amount : 1.00
         */

        private String userid;
        private String amount;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "userid='" + userid + '\'' +
                    ", amount='" + amount + '\'' +
                    '}';
        }
    }
}
