package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/4/26.
 */
/*
@className :VipNoResponse
*@date 2018/4/26
*@author
*@description 生成VIPNO
*/
public class VipNoResponse {
    /**
     * error : 0
     * message : success
     * data : {"vip_no":"201804260500177182"}
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
        return "VipNoResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * vip_no : 201804260500177182
         */

        private String vip_no;

        public String getVip_no() {
            return vip_no;
        }

        public void setVip_no(String vip_no) {
            this.vip_no = vip_no;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "vip_no='" + vip_no + '\'' +
                    '}';
        }
    }
}
