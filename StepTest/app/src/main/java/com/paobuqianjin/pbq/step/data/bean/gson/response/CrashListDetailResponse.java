package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/10.
 */
/*
@className :CrashListDetailResponse
*@date 2018/3/10
*@author
*@description 提现详情
*/
public class CrashListDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"id":51,"userid":35821,"typeid":3,"order_no":"20180827150245873627","amount":"2","actual_amount":"0.98","feemoney":"1.02","bank_id":150,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"6212264000053654469","bank_phone":"13652354126","delete_id":0,"delete_time":0,"create_time":1535353365,"update_time":"2018-08-27 15:02:45","remark":"","type":3,"tno":"","statime":0,"check_time":0,"type_name":"提现到银行卡","withdraw_status":0},{"id":49,"userid":35821,"typeid":3,"order_no":"20180816170130820569","amount":"2","actual_amount":"0.98","feemoney":"1.02","bank_id":144,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"6212264000053654469","bank_phone":"13652354126","delete_id":0,"delete_time":0,"create_time":1534410090,"update_time":"2018-08-16 17:01:30","remark":"","type":3,"tno":"","statime":0,"check_time":0,"type_name":"提现到银行卡","withdraw_status":0},{"id":48,"userid":35821,"typeid":3,"order_no":"20180815193631520105","amount":"2","actual_amount":"0.98","feemoney":"1.02","bank_id":136,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"6212264000053654469","bank_phone":"13652354126","delete_id":0,"delete_time":0,"create_time":1534332991,"update_time":"2018-08-15 19:36:31","remark":"","type":3,"tno":"","statime":0,"check_time":0,"type_name":"提现到银行卡","withdraw_status":0},{"id":47,"userid":35821,"typeid":3,"order_no":"20180814115644171433","amount":"3","actual_amount":"1.97","feemoney":"1.03","bank_id":136,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"6212264000053654469","bank_phone":"13652354126","delete_id":0,"delete_time":0,"create_time":1534219004,"update_time":"2018-08-14 11:57:06","remark":"","type":3,"tno":"","statime":0,"check_time":1534219026,"type_name":"提现到银行卡","withdraw_status":3}]}
     */

    private int error;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"id":51,"userid":35821,"typeid":3,"order_no":"20180827150245873627","amount":"2","actual_amount":"0.98","feemoney":"1.02","bank_id":150,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"6212264000053654469","bank_phone":"13652354126","delete_id":0,"delete_time":0,"create_time":1535353365,"update_time":"2018-08-27 15:02:45","remark":"","type":3,"tno":"","statime":0,"check_time":0,"type_name":"提现到银行卡","withdraw_status":0},{"id":49,"userid":35821,"typeid":3,"order_no":"20180816170130820569","amount":"2","actual_amount":"0.98","feemoney":"1.02","bank_id":144,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"6212264000053654469","bank_phone":"13652354126","delete_id":0,"delete_time":0,"create_time":1534410090,"update_time":"2018-08-16 17:01:30","remark":"","type":3,"tno":"","statime":0,"check_time":0,"type_name":"提现到银行卡","withdraw_status":0},{"id":48,"userid":35821,"typeid":3,"order_no":"20180815193631520105","amount":"2","actual_amount":"0.98","feemoney":"1.02","bank_id":136,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"6212264000053654469","bank_phone":"13652354126","delete_id":0,"delete_time":0,"create_time":1534332991,"update_time":"2018-08-15 19:36:31","remark":"","type":3,"tno":"","statime":0,"check_time":0,"type_name":"提现到银行卡","withdraw_status":0},{"id":47,"userid":35821,"typeid":3,"order_no":"20180814115644171433","amount":"3","actual_amount":"1.97","feemoney":"1.03","bank_id":136,"account_name":"谢淑雨","bank_code":"ICBC","bank_card":"6212264000053654469","bank_phone":"13652354126","delete_id":0,"delete_time":0,"create_time":1534219004,"update_time":"2018-08-14 11:57:06","remark":"","type":3,"tno":"","statime":0,"check_time":1534219026,"type_name":"提现到银行卡","withdraw_status":3}]
         */

        private PagenationBean pagenation;
        private List<DataBean> data;

        public PagenationBean getPagenation() {
            return pagenation;
        }

        public void setPagenation(PagenationBean pagenation) {
            this.pagenation = pagenation;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 10
             * totalPage : 1
             * totalCount : 4
             */

            private int page;
            private int pageSize;
            private int totalPage;
            private int totalCount;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }
        }

        public static class DataBean {
            /**
             * id : 51
             * userid : 35821
             * typeid : 3
             * order_no : 20180827150245873627
             * amount : 2
             * actual_amount : 0.98
             * feemoney : 1.02
             * bank_id : 150
             * account_name : 谢淑雨
             * bank_code : ICBC
             * bank_card : 6212264000053654469
             * bank_phone : 13652354126
             * delete_id : 0
             * delete_time : 0
             * create_time : 1535353365
             * update_time : 2018-08-27 15:02:45
             * remark :
             * type : 3
             * tno :
             * statime : 0
             * check_time : 0
             * type_name : 提现到银行卡
             * withdraw_status : 0
             */

            private int id;
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
            private int delete_id;
            private int delete_time;
            private int create_time;
            private String update_time;
            private String remark;
            private int type;
            private String tno;
            private int statime;
            private int check_time;
            private String type_name;
            private int withdraw_status;

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

            public int getDelete_id() {
                return delete_id;
            }

            public void setDelete_id(int delete_id) {
                this.delete_id = delete_id;
            }

            public int getDelete_time() {
                return delete_time;
            }

            public void setDelete_time(int delete_time) {
                this.delete_time = delete_time;
            }

            public int getCreate_time() {
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

            public int getStatime() {
                return statime;
            }

            public void setStatime(int statime) {
                this.statime = statime;
            }

            public int getCheck_time() {
                return check_time;
            }

            public void setCheck_time(int check_time) {
                this.check_time = check_time;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public int getWithdraw_status() {
                return withdraw_status;
            }

            public void setWithdraw_status(int withdraw_status) {
                this.withdraw_status = withdraw_status;
            }
        }
    }
}
