package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/12/24.
 */

public class PreliveLegResponse {
    /**
     * error : 0
     * message : success
     * data : {"has_privilege":1,"tip_msg":"","record_btn_show" : 1,"can_credit":1,"is_near":1}
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
         * has_privilege : 1
         * tip_msg :
         * record_btn_show: 1
         * can_credit: 1
         * is_near:1
         */
        private int can_credit;

        public int getCan_credit() {
            return can_credit;
        }

        public int getIs_near() {
            return is_near;
        }

        private int is_near;
        private int has_privilege;
        private String tip_msg;

        public int getRecord_btn_show() {
            return record_btn_show;
        }

        private int record_btn_show;

        public int getHas_privilege() {
            return has_privilege;
        }

        public void setHas_privilege(int has_privilege) {
            this.has_privilege = has_privilege;
        }

        public String getTip_msg() {
            return tip_msg;
        }

        public void setTip_msg(String tip_msg) {
            this.tip_msg = tip_msg;
        }
    }
}
