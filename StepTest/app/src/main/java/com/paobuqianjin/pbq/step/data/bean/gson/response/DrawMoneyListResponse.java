package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/2.
 */

public class DrawMoneyListResponse {
    /**
     * error : 0
     * message : success
     * data : [{"money":10,"is_disable":1},{"money":50,"is_disable":0},{"money":100,"is_disable":0}]
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
         * is_disable : 1 是否可选择
         * is_select :0 是否被选中
         */

        private String money;
        public boolean isIs_select() {
            return is_select;
        }

        private int is_disable;

        public void setIs_select(boolean is_select) {
            this.is_select = is_select;
        }

        private boolean is_select;

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
    }
}
