package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/9/4.
 */

public class CrashInfoResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":49,"userid":35821,"typeid":3,"order_no":"20180816170130820569","amount":"2","actual_amount":"0.98","feemoney":"1.02","bank_id":144,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"4469","bank_phone":"13652354126","status":0,"check_status":0,"delete_id":0,"delete_time":0,"create_time":1534410090,"update_time":"2018-08-16 17:01:30","remark":"","type":3,"tno":"","statime":0,"check_time":0,"withdraw_type":"银行卡","bankname":"中国工商银行","withdraw_status":0,"servicer_id":94739,"servicer_phone":"0755-86540067"}
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
         * id : 49
         * userid : 35821
         * typeid : 3
         * order_no : 20180816170130820569
         * amount : 2
         * actual_amount : 0.98
         * feemoney : 1.02
         * bank_id : 144
         * account_name : 谢淑雨
         * bank_code : ICBC
         * bank_card : 4469
         * bank_phone : 13652354126
         * status : 0
         * check_status : 0
         * delete_id : 0
         * delete_time : 0
         * create_time : 1534410090
         * update_time : 2018-08-16 17:01:30
         * remark :
         * type : 3
         * tno :
         * statime : 0
         * check_time : 0
         * withdraw_type : 银行卡
         * bankname : 中国工商银行
         * withdraw_status : 0
         * servicer_id : 94739
         * servicer_phone : 0755-86540067
         * "nickname":"黄钦平"
         */

        private int id;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        private String nickname;
        private int userid;
        private int typeid;
        private String order_no;
        private String amount;
        private String actual_amount;
        private String feemoney;
        private int bank_id;
        private String account_name;
        private String bank_code;
        private String bank_card;
        private String bank_phone;
        private int status;
        private int check_status;
        private int delete_id;
        private long delete_time;
        private long create_time;
        private String update_time;
        private String remark;
        private int type;
        private String tno;
        private long statime;
        private long check_time;
        private String withdraw_type;
        private String bankname;
        private int withdraw_status;
        private String servicer_id;
        private String servicer_phone;

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

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getActual_amount() {
            return actual_amount;
        }

        public void setActual_amount(String actual_amount) {
            this.actual_amount = actual_amount;
        }

        public String getFeemoney() {
            return feemoney;
        }

        public void setFeemoney(String feemoney) {
            this.feemoney = feemoney;
        }

        public int getBank_id() {
            return bank_id;
        }

        public void setBank_id(int bank_id) {
            this.bank_id = bank_id;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getBank_code() {
            return bank_code;
        }

        public void setBank_code(String bank_code) {
            this.bank_code = bank_code;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public String getBank_phone() {
            return bank_phone;
        }

        public void setBank_phone(String bank_phone) {
            this.bank_phone = bank_phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCheck_status() {
            return check_status;
        }

        public void setCheck_status(int check_status) {
            this.check_status = check_status;
        }

        public int getDelete_id() {
            return delete_id;
        }

        public void setDelete_id(int delete_id) {
            this.delete_id = delete_id;
        }

        public long getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(int delete_time) {
            this.delete_time = delete_time;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTno() {
            return tno;
        }

        public void setTno(String tno) {
            this.tno = tno;
        }

        public long getStatime() {
            return statime;
        }

        public void setStatime(int statime) {
            this.statime = statime;
        }

        public long getCheck_time() {
            return check_time;
        }

        public void setCheck_time(int check_time) {
            this.check_time = check_time;
        }

        public String getWithdraw_type() {
            return withdraw_type;
        }

        public void setWithdraw_type(String withdraw_type) {
            this.withdraw_type = withdraw_type;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public int getWithdraw_status() {
            return withdraw_status;
        }

        public void setWithdraw_status(int withdraw_status) {
            this.withdraw_status = withdraw_status;
        }

        public String getServicer_id() {
            return servicer_id;
        }

        public void setServicer_id(String servicer_id) {
            this.servicer_id = servicer_id;
        }

        public String getServicer_phone() {
            return servicer_phone;
        }

        public void setServicer_phone(String servicer_phone) {
            this.servicer_phone = servicer_phone;
        }
    }
}
