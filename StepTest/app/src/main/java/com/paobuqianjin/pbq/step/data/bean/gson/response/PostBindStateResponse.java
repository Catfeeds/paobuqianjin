package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/5/5.
 */

public class PostBindStateResponse {
    /**
     * error : 0
     * message : sccess
     * data : {"mobile":1,"xcx":0,"wx":0,"qq":0}
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
        return "PostBindStateResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * mobile : 1
         * xcx : 0
         * wx : 0
         * qq : 0
         */

        private int mobile;
        private int xcx;
        private int wx;
        private int qq;

        public int getMobile() {
            return mobile;
        }

        public void setMobile(int mobile) {
            this.mobile = mobile;
        }

        public int getXcx() {
            return xcx;
        }

        public void setXcx(int xcx) {
            this.xcx = xcx;
        }

        public int getWx() {
            return wx;
        }

        public void setWx(int wx) {
            this.wx = wx;
        }

        public int getQq() {
            return qq;
        }

        public void setQq(int qq) {
            this.qq = qq;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "mobile=" + mobile +
                    ", xcx=" + xcx +
                    ", wx=" + wx +
                    ", qq=" + qq +
                    '}';
        }
    }
}
