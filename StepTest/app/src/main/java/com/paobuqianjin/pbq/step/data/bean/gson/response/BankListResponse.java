package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by adminisitor on 2018/5/18.
 */
public class BankListResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"id":1,"userid":1,"typeid":1,"amount":"2000.00","actual_amount":"1980","status":0,"create_time":1513755628},{"id":31,"userid":1,"typeid":2,"amount":"240.00","actual_amount":"237.6","status":0,"create_time":1514186667},{"id":32,"userid":1,"typeid":1,"amount":"24.00","actual_amount":"23.76","status":0,"create_time":1514186628},{"id":35,"userid":1,"typeid":1,"amount":"1.00","actual_amount":"1","status":0,"create_time":1517816173}]}
     */

    private int error;
    private String message;
    private List<CardBean> data;

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


    @Override
    public String toString() {
        return "CrashListDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


    public static class CardBean {
        /* "cardid":5,
        "userid":11125,
        "account_name":"黄钦平",
        "bank_name":"光大银行",
        "bank_code":"CEB",
        "bank_card":"6226660405864808",
        "img_url":"",
        "region":"",
        "couplet":"",
        "is_bind":1,
        "bind_time":1527585083,
        "unbind_time":0*/
        private String cardid;
        private String account_name;
        private String bank_name;
        private String bank_card;
        private String img_url;
        private String bank_code;
        private String region;
        private String couplet;
        private String is_bind;
        private int status;//选中状态

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCardid() {
            return cardid;
        }

        public String getBank_name() {
            return bank_name;
        }

        public String getAccount_name() {
            return account_name;
        }

        public String getBank_card() {
            return bank_card;
        }

        public String getImg_url() {
            return img_url;
        }

        public String getBank_code() {
            return bank_code;
        }

        public String getRegion() {
            return region;
        }

        public String getCouplet() {
            return couplet;
        }

        public String getIs_bind() {
            return is_bind;
        }
    }

    public List<CardBean> getData() {
        return data;
    }
}
