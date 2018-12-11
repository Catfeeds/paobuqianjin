package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/2.
 */

public class DrawMoneyListResponse {

    /**
     * error : 0
     * message : success
     * data : [{"money":10,"is_disable":0,"withdraw_tips":["提现条件与手续费减免标准:凡是在本平台对接的拼多多、京东、天猫上成功购物两次，即符合提现条件，并免除手续费，还获得平台赠送同等数额步币奖励。"]},{"money":20,"is_disable":0,"withdraw_tips":["提现条件与手续费减免标准:凡是在本平台对接的拼多多、京东、天猫上成功购物两次，即符合提现条件，并免除手续费，还获得平台赠送同等数额步币奖励。"]},{"money":30,"is_disable":0,"withdraw_tips":["提现条件与手续费减免标准:凡是在本平台对接的拼多多、京东、天猫上成功购物两次，即符合提现条件，并免除手续费，还获得平台赠送同等数额步币奖励。"]}]
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
         * money : 10
         * is_disable : 0
         * is_select:0
         * withdraw_tips : ["提现条件与手续费减免标准:凡是在本平台对接的拼多多、京东、天猫上成功购物两次，即符合提现条件，并免除手续费，还获得平台赠送同等数额步币奖励。"]
         */

        private String money;
        private int is_disable;

        public boolean isIs_select() {
            return is_select;
        }

        public void setIs_select(boolean is_select) {
            this.is_select = is_select;
        }

        private boolean is_select;
        private List<String> withdraw_tips;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getIs_disable() {
            return is_disable;
        }

        public void setIs_disable(int is_disable) {
            this.is_disable = is_disable;
        }

        public List<String> getWithdraw_tips() {
            return withdraw_tips;
        }

        public void setWithdraw_tips(List<String> withdraw_tips) {
            this.withdraw_tips = withdraw_tips;
        }
    }
}
