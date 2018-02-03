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
     * data : {"appid":"wx1ed4ccc9a2226a73","partnerid":"1495263342","prepayid":"wx2018020314154474759cf71c0986846128","package":"Sign=WXPay","noncestr":"t86603068o5jaq9escojzb8h82bdbj0z","timestamp":1517638544,"sign":"EE66CEA452A2BEE4AF90AD5D9A066E59","order_no":"1517638544829"}
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
         * appid : wx1ed4ccc9a2226a73
         * partnerid : 1495263342
         * prepayid : wx2018020314154474759cf71c0986846128
         * package : Sign=WXPay
         * noncestr : t86603068o5jaq9escojzb8h82bdbj0z
         * timestamp : 1517638544
         * sign : EE66CEA452A2BEE4AF90AD5D9A066E59
         * order_no : 1517638544829
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private int timestamp;
        private String sign;
        private String order_no;

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

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
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
                    ", order_no='" + order_no + '\'' +
                    '}';
        }
    }
}
