package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/5/23.
 */

public class CvipNoResponse {
    /**
     * error : 0
     * message : success
     * data : {"cvip_no":"201804260500177182"}
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
        return "CvipNoResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * cvip_no : 201804260500177182
         */

        private String cvip_no;

        public String getCvip_no() {
            return cvip_no;
        }

        public void setCvip_no(String cvip_no) {
            this.cvip_no = cvip_no;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "cvip_no='" + cvip_no + '\'' +
                    '}';
        }
    }
}
