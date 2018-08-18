package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/7/17.
 */
/*
@className :TwentyOneResponse
*@date 2018/7/17
*@author
*@description 21天红包
*/
public class TwentyOneResponse {
    /**
     * error : 0
     * message : 领取成功
     * data : {"day":2,"credit":279,"money":0,"remark":"邀请好友有机会得30元邀请奖励"}
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
         * day : 2
         * credit : 279
         * money : 0
         * remark : 邀请好友有机会得30元邀请奖励
         */

        private int day;
        private int credit;
        private String money;
        private String remark;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
