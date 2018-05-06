package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by Administrator on 2018/4/23.
 */

public class TaskSponsorRespone {

    /**
     * error : 0
     * message : success
     * data : {"red_id":"152"}
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
         * red_id : 152
         */

        private String red_id;

        public String getRed_id() {
            return red_id;
        }

        public void setRed_id(String red_id) {
            this.red_id = red_id;
        }
    }
}