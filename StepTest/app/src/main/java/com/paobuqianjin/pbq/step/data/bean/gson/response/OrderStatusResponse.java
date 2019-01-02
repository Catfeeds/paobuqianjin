package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2019/1/2.
 */

public class OrderStatusResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":49,"comm_no":"COM20181231055146269","shipping_type":1,"order_status":2,"review_status":0,"close_order_status":0,"payment_type":1,"userid":35822,"user_avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/11/29/35822201811291414825696.jpg","user_nickname":"ABCD","buyer_id":35822,"buyer_consigner":"非凡醒","buyer_mobile":"2147483647","buyer_addr":"广东省深圳市南山区大新路90-5号麒麟路","buyer_address":"f回事","buyer_zip_code":"0","create_time":1546249906,"remark":"","express_total":"10.00","credit_total":10,"name":"测试付款","old_price":"10.00","credit":10,"number":1,"express_price":"10.00","img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734173869.jpg","company_name":"圆通速递","express_no":"8033023666676880171","consign_time":"0","order_status_text":"待收货"}
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
         * comm_no : COM20181231055146269
         * shipping_type : 1
         * order_status : 2
         * review_status : 0
         * close_order_status : 0
         * payment_type : 1
         * userid : 35822
         * user_avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/11/29/35822201811291414825696.jpg
         * user_nickname : ABCD
         * buyer_id : 35822
         * buyer_consigner : 非凡醒
         * buyer_mobile : 2147483647
         * buyer_addr : 广东省深圳市南山区大新路90-5号麒麟路
         * buyer_address : f回事
         * buyer_zip_code : 0
         * create_time : 1546249906
         * remark :
         * express_total : 10.00
         * credit_total : 10
         * name : 测试付款
         * old_price : 10.00
         * credit : 10
         * number : 1
         * express_price : 10.00
         * img_url : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734173869.jpg
         * company_name : 圆通速递
         * express_no : 8033023666676880171
         * consign_time : 0
         * order_status_text : 待收货
         */

        private int id;
        private String comm_no;
        private int shipping_type;
        private int order_status;
        private int review_status;
        private int close_order_status;
        private int payment_type;
        private String userid;
        private String user_avatar;
        private String user_nickname;
        private String buyer_id;
        private String buyer_consigner;
        private String buyer_mobile;
        private String buyer_addr;
        private String buyer_address;
        private String buyer_zip_code;
        private long create_time;
        private String remark;
        private String express_total;
        private String credit_total;
        private String name;
        private String old_price;
        private String credit;
        private String number;
        private String express_price;
        private String img_url;
        private String company_name;
        private String express_no;
        private String consign_time;
        private String order_status_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getComm_no() {
            return comm_no;
        }

        public void setComm_no(String comm_no) {
            this.comm_no = comm_no;
        }

        public int getShipping_type() {
            return shipping_type;
        }

        public void setShipping_type(int shipping_type) {
            this.shipping_type = shipping_type;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public int getReview_status() {
            return review_status;
        }

        public void setReview_status(int review_status) {
            this.review_status = review_status;
        }

        public int getClose_order_status() {
            return close_order_status;
        }

        public void setClose_order_status(int close_order_status) {
            this.close_order_status = close_order_status;
        }

        public int getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(int payment_type) {
            this.payment_type = payment_type;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getBuyer_consigner() {
            return buyer_consigner;
        }

        public void setBuyer_consigner(String buyer_consigner) {
            this.buyer_consigner = buyer_consigner;
        }

        public String getBuyer_mobile() {
            return buyer_mobile;
        }

        public void setBuyer_mobile(String buyer_mobile) {
            this.buyer_mobile = buyer_mobile;
        }

        public String getBuyer_addr() {
            return buyer_addr;
        }

        public void setBuyer_addr(String buyer_addr) {
            this.buyer_addr = buyer_addr;
        }

        public String getBuyer_address() {
            return buyer_address;
        }

        public void setBuyer_address(String buyer_address) {
            this.buyer_address = buyer_address;
        }

        public String getBuyer_zip_code() {
            return buyer_zip_code;
        }

        public void setBuyer_zip_code(String buyer_zip_code) {
            this.buyer_zip_code = buyer_zip_code;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getExpress_total() {
            return express_total;
        }

        public void setExpress_total(String express_total) {
            this.express_total = express_total;
        }

        public String getCredit_total() {
            return credit_total;
        }

        public void setCredit_total(String credit_total) {
            this.credit_total = credit_total;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOld_price() {
            return old_price;
        }

        public void setOld_price(String old_price) {
            this.old_price = old_price;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getExpress_price() {
            return express_price;
        }

        public void setExpress_price(String express_price) {
            this.express_price = express_price;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        public String getConsign_time() {
            return consign_time;
        }

        public void setConsign_time(String consign_time) {
            this.consign_time = consign_time;
        }

        public String getOrder_status_text() {
            return order_status_text;
        }

        public void setOrder_status_text(String order_status_text) {
            this.order_status_text = order_status_text;
        }
    }
}
