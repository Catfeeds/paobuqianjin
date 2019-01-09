package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/12/31.
 */

public class ExOrderNumResponse {
    /**
     * error : 0
     * message : success
     * data : {"comm_order_id":"39","shipping_money":"10.00"}
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

    public static class DataBean {
        public String getComm_money() {
            return comm_money;
        }

        /**
         * comm_order_id : 39
         * shipping_money : 10.00
         * "comm_money":"5.00"
         */
        private String comm_money;
        private String comm_order_id;
        private String shipping_money;

        public String getComm_order_id() {
            return comm_order_id;
        }

        public void setComm_order_id(String comm_order_id) {
            this.comm_order_id = comm_order_id;
        }

        public String getShipping_money() {
            return shipping_money;
        }

        public void setShipping_money(String shipping_money) {
            this.shipping_money = shipping_money;
        }
    }
}
