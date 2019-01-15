package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2019/1/15.
 */

public class ShopEntryRePayResponse {
    /**
     * error : 0
     * message : success
     * data : {"guide_id":"9","amount":94}
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
         * guide_id : 9
         * amount : 94
         */

        private String guide_id;
        private String amount;

        public String getGuide_id() {
            return guide_id;
        }

        public void setGuide_id(String guide_id) {
            this.guide_id = guide_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
