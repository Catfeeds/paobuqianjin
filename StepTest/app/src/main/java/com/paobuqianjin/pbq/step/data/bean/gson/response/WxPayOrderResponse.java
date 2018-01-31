package com.paobuqianjin.pbq.step.data.bean.gson.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pbq on 2018/1/27.
 */
/*
@className :WxPayOrderResponse
*@date 2018/1/27
*@author
*@description 微信支付订单返回
*/
public class WxPayOrderResponse {

    /**
     * error : 0
     * message : success
     * data : {"appid":"wx1ed4ccc9a2226a73","partnerid":"1495263342","prepayid":"wx20180131110055637048e43d0611728350","package":"Sign=WXPay","noncestr":"7QdaNZIvwQ505PZk","timestamp":1517367655,"sign":"0A3047A4CBB7C41163BB738131528FEC"}
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
        return "WxPayOrderResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * appid : wx1ed4ccc9a2226a73
         * partnerid : 1495263342
         * prepayid : wx20180131110055637048e43d0611728350
         * package : Sign=WXPay
         * noncestr : 7QdaNZIvwQ505PZk
         * timestamp : 1517367655
         * sign : 0A3047A4CBB7C41163BB738131528FEC
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "appid='" + appid + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", packageX='" + packageX + '\'' +
                    ", noncestr='" + noncestr + '\'' +
                    ", timestamp=" + timestamp +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }
}
