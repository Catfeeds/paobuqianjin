package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/7.
 */
/*
@className :BindCardListResponse
*@date 2018/2/7
*@author
*@description  绑定账户列表 http://119.29.10.64/v1/UserBankCard?userid=1
*/
public class BindCardListResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":1,"userid":1,"typeid":3,"realname":"陈杰","bank_card":"100000000000000000000","opening_bank":"中国工商银行","subbranch":"福田八卦岭支行","region":"深圳市福田区","create_time":1514287375},{"id":2,"userid":1,"typeid":3,"realname":"陈杰","bank_card":"100000000000000000001","opening_bank":"中国平安银行","subbranch":"福田八卦岭支行","region":"深圳市福田区","create_time":1514287375},{"id":3,"userid":1,"typeid":3,"realname":"陈杰","bank_card":"100000000000000000002","opening_bank":"中国建设银行","subbranch":"福田八卦岭支行","region":"深圳市福田区","create_time":1514287375},{"id":4,"userid":1,"typeid":3,"realname":"陈杰","bank_card":"100000000000000000003","opening_bank":"中国农业银行","subbranch":"福田八卦岭支行","region":"深圳市福田区","create_time":1514287375},{"id":5,"userid":1,"typeid":1,"realname":"陈杰","bank_card":"oPd5d0TGW_mckWv02CDqWdki5BAw","opening_bank":"","subbranch":"","region":"","create_time":1517813756}]
     */

    private int error;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BindCardListResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 1
         * userid : 1
         * typeid : 3
         * realname : 陈杰
         * bank_card : 100000000000000000000
         * opening_bank : 中国工商银行
         * subbranch : 福田八卦岭支行
         * region : 深圳市福田区
         * create_time : 1514287375
         */

        private int id;
        private int userid;
        private int typeid;
        private String realname;
        private String bank_card;
        private String opening_bank;
        private String subbranch;
        private String region;
        private int create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public String getOpening_bank() {
            return opening_bank;
        }

        public void setOpening_bank(String opening_bank) {
            this.opening_bank = opening_bank;
        }

        public String getSubbranch() {
            return subbranch;
        }

        public void setSubbranch(String subbranch) {
            this.subbranch = subbranch;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", typeid=" + typeid +
                    ", realname='" + realname + '\'' +
                    ", bank_card='" + bank_card + '\'' +
                    ", opening_bank='" + opening_bank + '\'' +
                    ", subbranch='" + subbranch + '\'' +
                    ", region='" + region + '\'' +
                    ", create_time=" + create_time +
                    '}';
        }
    }
}
