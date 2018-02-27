package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/2/27.
 */
/*
@className :MyInviteResponse
*@date 2018/2/27
*@author
*@description 个人邀请信息
*/
public class MyInviteResponse {
    /**
     * error : 0
     * message : success
     * data : {"userid":1,"mobile":"18588278880","number":4,"sum_credit":50}
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
        return "MyInviteResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * userid : 1
         * mobile : 18588278880
         * number : 4
         * sum_credit : 50
         */

        private int userid;
        private String mobile;
        private int number;
        private int sum_credit;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSum_credit() {
            return sum_credit;
        }

        public void setSum_credit(int sum_credit) {
            this.sum_credit = sum_credit;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "userid=" + userid +
                    ", mobile='" + mobile + '\'' +
                    ", number=" + number +
                    ", sum_credit=" + sum_credit +
                    '}';
        }
    }
}
