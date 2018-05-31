package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/5/30.
 */

public class YsPayResultResponse {
    /**
     * error : 0
     * message : success
     * data : {"userid":11228,"order_type":1,"payment_type":6,"order_no":"201805300536424226","transaction_no":"QUICKSDK180530173642ARCZ","body":"用户充值_201805300536424226","total_fee":"0.11","status":1,"create_time":1527673002,"circleid":0,"taskid":0}
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
        /**
         * userid : 11228
         * order_type : 1
         * payment_type : 6
         * order_no : 201805300536424226
         * transaction_no : QUICKSDK180530173642ARCZ
         * body : 用户充值_201805300536424226
         * total_fee : 0.11
         * status : 1
         * create_time : 1527673002
         * circleid : 0
         * taskid : 0
         */

        private int userid;
        private int order_type;
        private int payment_type;
        private String order_no;
        private String transaction_no;
        private String body;
        private String total_fee;
        private int status;
        private int create_time;
        private int circleid;
        private int taskid;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public int getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(int payment_type) {
            this.payment_type = payment_type;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getTransaction_no() {
            return transaction_no;
        }

        public void setTransaction_no(String transaction_no) {
            this.transaction_no = transaction_no;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getCircleid() {
            return circleid;
        }

        public void setCircleid(int circleid) {
            this.circleid = circleid;
        }

        public int getTaskid() {
            return taskid;
        }

        public void setTaskid(int taskid) {
            this.taskid = taskid;
        }
    }
}
