package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/3.
 */
/*
@className :AllMyPayResponse
*@date 2018/2/3
*@author
*@description 查询用订单返回类
*/
public class AllMyPayResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":1,"userid":1,"order_type":0,"payment_type":0,"order_no":"1516936358701","transaction_no":"0","body":"跑步钱进_1516936358701","total_fee":"100.00","status":0,"create_time":1516936358},{"id":2,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517300766748","transaction_no":"","body":"圈子充值_1517300766748","total_fee":"10.00","status":0,"create_time":1517300766},{"id":3,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517300793422","transaction_no":"","body":"圈子充值_1517300793422","total_fee":"10.00","status":0,"create_time":1517300793},{"id":4,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517300860438","transaction_no":"","body":"圈子充值_1517300860438","total_fee":"10.00","status":0,"create_time":1517300860},{"id":5,"userid":1,"order_type":0,"payment_type":2,"order_no":"151730092687","transaction_no":"","body":"圈子充值_151730092687","total_fee":"10.00","status":0,"create_time":1517300926},{"id":6,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517300936396","transaction_no":"","body":"圈子充值_1517300936396","total_fee":"10.00","status":0,"create_time":1517300936},{"id":7,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301124151","transaction_no":"","body":"圈子充值_1517301124151","total_fee":"10.00","status":0,"create_time":1517301124},{"id":8,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301295436","transaction_no":"","body":"圈子充值_1517301295436","total_fee":"10.00","status":0,"create_time":1517301295},{"id":9,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301335658","transaction_no":"","body":"圈子充值_1517301335658","total_fee":"10.00","status":0,"create_time":1517301335},{"id":10,"userid":1,"order_type":0,"payment_type":2,"order_no":"15173013652","transaction_no":"","body":"圈子充值_15173013652","total_fee":"10.00","status":0,"create_time":1517301365},{"id":11,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301365164","transaction_no":"","body":"圈子充值_1517301365164","total_fee":"10.00","status":0,"create_time":1517301365},{"id":12,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301394353","transaction_no":"","body":"圈子充值_1517301394353","total_fee":"10.00","status":0,"create_time":1517301394},{"id":13,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301395181","transaction_no":"","body":"圈子充值_1517301395181","total_fee":"10.00","status":0,"create_time":1517301395},{"id":14,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301414762","transaction_no":"","body":"圈子充值_1517301414762","total_fee":"10.00","status":0,"create_time":1517301414},{"id":15,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301451560","transaction_no":"","body":"圈子充值_1517301451560","total_fee":"10.00","status":0,"create_time":1517301451},{"id":16,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301473819","transaction_no":"","body":"圈子充值_1517301473819","total_fee":"10.00","status":0,"create_time":1517301473},{"id":17,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301746623","transaction_no":"","body":"圈子充值_1517301746623","total_fee":"10.00","status":0,"create_time":1517301746},{"id":18,"userid":1,"order_type":0,"payment_type":2,"order_no":"151730176887","transaction_no":"","body":"圈子充值_151730176887","total_fee":"10.00","status":0,"create_time":1517301768},{"id":19,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301795542","transaction_no":"","body":"圈子充值_1517301795542","total_fee":"10.00","status":0,"create_time":1517301795},{"id":20,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301811787","transaction_no":"","body":"圈子充值_1517301811787","total_fee":"10.00","status":0,"create_time":1517301811},{"id":21,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301818274","transaction_no":"","body":"圈子充值_1517301818274","total_fee":"10.00","status":0,"create_time":1517301818},{"id":22,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301838492","transaction_no":"","body":"圈子充值_1517301838492","total_fee":"10.00","status":0,"create_time":1517301838},{"id":23,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301845257","transaction_no":"","body":"圈子充值_1517301845257","total_fee":"10.00","status":0,"create_time":1517301845},{"id":24,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301854846","transaction_no":"","body":"圈子充值_1517301854846","total_fee":"10.00","status":0,"create_time":1517301854},{"id":25,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517301863499","transaction_no":"","body":"圈子充值_1517301863499","total_fee":"10.00","status":0,"create_time":1517301863},{"id":26,"userid":1,"order_type":1,"payment_type":2,"order_no":"1517301917944","transaction_no":"","body":"用户充值_1517301917944","total_fee":"10.00","status":0,"create_time":1517301917},{"id":27,"userid":1,"order_type":2,"payment_type":2,"order_no":"1517301940575","transaction_no":"","body":"任务充值_1517301940575","total_fee":"10.00","status":0,"create_time":1517301940},{"id":28,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517303749268","transaction_no":"","body":"圈子充值_1517303749268","total_fee":"10.00","status":0,"create_time":1517303749},{"id":29,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517303860249","transaction_no":"","body":"圈子充值_1517303860249","total_fee":"10.00","status":0,"create_time":1517303860},{"id":30,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517303911500","transaction_no":"","body":"圈子充值_1517303911500","total_fee":"10.00","status":0,"create_time":1517303911},{"id":31,"userid":1,"order_type":2,"payment_type":2,"order_no":"1517303963562","transaction_no":"","body":"任务充值_1517303963562","total_fee":"10.00","status":0,"create_time":1517303963},{"id":32,"userid":1,"order_type":2,"payment_type":2,"order_no":"1517303982581","transaction_no":"","body":"任务充值_1517303982581","total_fee":"10.00","status":0,"create_time":1517303982},{"id":33,"userid":1,"order_type":2,"payment_type":2,"order_no":"1517362583996","transaction_no":"","body":"任务充值_1517362583996","total_fee":"10.00","status":0,"create_time":1517362583},{"id":34,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517363301644","transaction_no":"","body":"圈子充值_1517363301644","total_fee":"10.00","status":0,"create_time":1517363301},{"id":35,"userid":1,"order_type":2,"payment_type":2,"order_no":"1517363322571","transaction_no":"","body":"任务充值_1517363322571","total_fee":"10.00","status":0,"create_time":1517363322},{"id":36,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517365587513","transaction_no":"","body":"圈子充值_1517365587513","total_fee":"10.00","status":0,"create_time":1517365587},{"id":100,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517483675341","transaction_no":"","body":"圈子充值_1517483675341","total_fee":"0.01","status":0,"create_time":1517483675},{"id":103,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517483860339","transaction_no":"","body":"圈子充值_1517483860339","total_fee":"0.01","status":0,"create_time":1517483860},{"id":104,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517484023548","transaction_no":"","body":"圈子充值_1517484023548","total_fee":"0.01","status":0,"create_time":1517484023},{"id":108,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517488737282","transaction_no":"","body":"圈子充值_1517488737282","total_fee":"1.00","status":0,"create_time":1517488737},{"id":109,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517488821425","transaction_no":"","body":"圈子充值_1517488821425","total_fee":"1.00","status":0,"create_time":1517488821},{"id":110,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517488830999","transaction_no":"","body":"圈子充值_1517488830999","total_fee":"1.00","status":0,"create_time":1517488830},{"id":125,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517542031888","transaction_no":"","body":"圈子充值_1517542031888","total_fee":"1.00","status":0,"create_time":1517542031},{"id":126,"userid":1,"order_type":0,"payment_type":2,"order_no":"1517542189992","transaction_no":"","body":"圈子充值_1517542189992","total_fee":"1.00","status":0,"create_time":1517542189},{"id":127,"userid":1,"order_type":0,"payment_type":3,"order_no":"1517542198697","transaction_no":"","body":"圈子充值_1517542198697","total_fee":"1.00","status":0,"create_time":1517542198},{"id":128,"userid":1,"order_type":0,"payment_type":3,"order_no":"1517542233677","transaction_no":"","body":"圈子充值_1517542233677","total_fee":"1.00","status":0,"create_time":1517542233},{"id":129,"userid":1,"order_type":0,"payment_type":3,"order_no":"1517542241130","transaction_no":"","body":"圈子充值_1517542241130","total_fee":"1.00","status":0,"create_time":1517542241},{"id":130,"userid":1,"order_type":0,"payment_type":3,"order_no":"1517552139981","transaction_no":"","body":"圈子充值_1517552139981","total_fee":"1.00","status":0,"create_time":1517552139},{"id":131,"userid":1,"order_type":0,"payment_type":3,"order_no":"1517554780495","transaction_no":"","body":"圈子充值_1517554780495","total_fee":"1.00","status":0,"create_time":1517554780}]
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
         * userid : 1
         * order_type : 0
         * payment_type : 0
         * order_no : 1516936358701
         * transaction_no : 0
         * body : 跑步钱进_1516936358701
         * total_fee : 100.00
         * status : 0
         * create_time : 1516936358
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
    }
}
