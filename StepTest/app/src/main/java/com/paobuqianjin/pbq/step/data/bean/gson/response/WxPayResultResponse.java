package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/1/31.
 */
/*
@className :WxPayResultResponse 微信查询订单信息
*@date 2018/1/31
*@author
*@description
*/
public class WxPayResultResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":86,"userid":56,"order_type":0,"payment_type":2,"order_no":"1517396241706","transaction_no":"4200000058201801314315711649","body":"圈子充值_1517396241706","total_fee":"0.01","status":1,"create_time":1517396241}
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
        return "WxPayResultResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 86
         * userid : 56
         * order_type : 0
         * payment_type : 2
         * order_no : 1517396241706
         * transaction_no : 4200000058201801314315711649
         * body : 圈子充值_1517396241706
         * total_fee : 0.01
         * status : 1
         * create_time : 1517396241
         */

        private int id;
        private int userid;
        private int order_type;
        private int payment_type;
        private String order_no;
        private String transaction_no;
        private String body;
        private String total_fee;
        private int status;
        private int create_time;

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

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", order_type=" + order_type +
                    ", payment_type=" + payment_type +
                    ", order_no='" + order_no + '\'' +
                    ", transaction_no='" + transaction_no + '\'' +
                    ", body='" + body + '\'' +
                    ", total_fee='" + total_fee + '\'' +
                    ", status=" + status +
                    ", create_time=" + create_time +
                    '}';
        }
    }
}
