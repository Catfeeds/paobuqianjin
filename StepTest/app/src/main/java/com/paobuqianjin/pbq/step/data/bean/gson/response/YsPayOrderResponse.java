package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * 云闪付订单
 * Created by pbq on 2018/5/26.
 */
public class YsPayOrderResponse {
    /**
     * error : 0
     * message : success
     * data : {"merchantNo":"C1800275339","prePayId":"959953308775817906916","userId":"30","appId":"c7317c6c84f3c13999fe3eb15abf564f"}
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
         * merchantNo : C1800275339
         * prePayId : 959953308775817906916
         * userId : 30
         * appId : c7317c6c84f3c13999fe3eb15abf564f
         */

        private String merchantNo;
        private String prePayId;
        private String userId;
        private String appId;

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getPrePayId() {
            return prePayId;
        }

        public void setPrePayId(String prePayId) {
            this.prePayId = prePayId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }
}
