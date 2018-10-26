package com.paobuqianjin.pbq.step.data.bean.gson.response;
/*
@className :StepReWardResponse
*@date 2018/10/23
*@author
*@description 领取步数步币奖励
*/
public class StepReWardResponse {
    /**
     * error : 0
     * message : success
     * data : {"is_existe":0,"type":8,"credit":100,"source_tip":"开机"}
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
         * is_existe : 0
         * type : 8
         * credit : 100
         * source_tip: **
         */

        private int is_existe;
        private int type;
        private int credit;

        public String getSource_tip() {
            return source_tip;
        }

        public void setSource_tip(String source_tip) {
            this.source_tip = source_tip;
        }

        private String source_tip;
        public int getIs_existe() {
            return is_existe;
        }

        public void setIs_existe(int is_existe) {
            this.is_existe = is_existe;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }
    }
}
