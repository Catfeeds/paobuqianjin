package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/6/20.
 */

public class SevenAliPayResponse {

    /**
     * error : 0
     * message : success
     * data : {"returnCode":"SUCCESS","returnMsg":"下单成功","signType":"RSA","signMsg":"7D24AA41058C9B78E251877C5920E78595108938E75037993E3314EE5030DD265E66AB8D31D58CDB265E97403686F331263ED06FFA8F54C5FD1FB871DEB692FFF0DE310267C7B698EFF07FF4CD8AC0826A9E929CEBCAF0A1AA53887A66983B388F6C6CE73FCC6F1D16B8B2D1D716F26EDE84D0C0BDCA1D898D53194E6E991702","payUrl":"https://s.qifenqian.com/s/36e1185a705747d7ae36523b77b6bf99","mchOrderId":"201806210225321053","mchId":"C2018061215114200210","inOrderId":"SP20180621142531519WENHqAksHl"}
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
         * returnCode : SUCCESS
         * returnMsg : 下单成功
         * signType : RSA
         * signMsg : 7D24AA41058C9B78E251877C5920E78595108938E75037993E3314EE5030DD265E66AB8D31D58CDB265E97403686F331263ED06FFA8F54C5FD1FB871DEB692FFF0DE310267C7B698EFF07FF4CD8AC0826A9E929CEBCAF0A1AA53887A66983B388F6C6CE73FCC6F1D16B8B2D1D716F26EDE84D0C0BDCA1D898D53194E6E991702
         * payUrl : https://s.qifenqian.com/s/36e1185a705747d7ae36523b77b6bf99
         * mchOrderId : 201806210225321053
         * mchId : C2018061215114200210
         * inOrderId : SP20180621142531519WENHqAksHl
         */

        private String returnCode;
        private String returnMsg;
        private String signType;
        private String signMsg;
        private String payUrl;
        private String mchOrderId;
        private String mchId;
        private String inOrderId;

        public String getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }

        public String getReturnMsg() {
            return returnMsg;
        }

        public void setReturnMsg(String returnMsg) {
            this.returnMsg = returnMsg;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getSignMsg() {
            return signMsg;
        }

        public void setSignMsg(String signMsg) {
            this.signMsg = signMsg;
        }

        public String getPayUrl() {
            return payUrl;
        }

        public void setPayUrl(String payUrl) {
            this.payUrl = payUrl;
        }

        public String getMchOrderId() {
            return mchOrderId;
        }

        public void setMchOrderId(String mchOrderId) {
            this.mchOrderId = mchOrderId;
        }

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public String getInOrderId() {
            return inOrderId;
        }

        public void setInOrderId(String inOrderId) {
            this.inOrderId = inOrderId;
        }
    }
}
