package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/5/25.
 */

public class UpdateBusinessResponse {
    /**
     * error : 0
     * message : success
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
    public class DataBean {
        /**
         * businessid : 355
         * name : 哈哈
         */

        private String businessid;
        private String name;

        public String getBusinessid() {
            return businessid;
        }

        public void setBusinessid(String businessid) {
            this.businessid = businessid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
