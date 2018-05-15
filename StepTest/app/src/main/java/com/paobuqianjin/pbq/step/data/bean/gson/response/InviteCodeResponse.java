package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/5/7.
 */

public class InviteCodeResponse {
    /**
     * error : 0
     * message : success
     * data : {"mycode":30}
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
        return "InviteCodeResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * mycode : 30
         */

        private int mycode;

        public int getMycode() {
            return mycode;
        }

        public void setMycode(int mycode) {
            this.mycode = mycode;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "mycode=" + mycode +
                    '}';
        }
    }
}
