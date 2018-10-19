package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/10/19.
 */

public class ConSumRedDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"voucherid":256,"type":1,"vname":"哈哈大笑","vcontent":"满200元减20减","step":1,"condition":"200","money":"20","amount":5,"receive":2,"day":2,"create_time":1539918552,"e_time":1540051199,"content":"填一下推广内容","target_url":"","busid":1684,"userid":35828,"nickname":"rm_13682385800","businessid":1684,"businessname":"哈哈大笑","addra":"金龙工业城","address":"计算机","phone":"13923832013","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FA600C53-D6FE-4AB5-BA9D-3EF94CBFDFDE.jpg","longitude":"113.930660","latitude":"22.548860","scope":"","receiver_list":[{"record_id":349,"busid":1684,"userid":35822,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg"},{"record_id":352,"busid":1684,"userid":35901,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/16/35901201810161006501862.jpg"}],"red_img":[{"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/83A82F4E-76CD-4093-849F-E95ED907D821.jpg"},{"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2A200CEE-CDA0-4F0E-8B6F-1288ACF5E41D.jpg"},{"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/5707817D-B707-4C89-8A84-D672ADBF76AF.jpg"}],"business_img":[{"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/A7E3ADF7-27B5-4FD1-A0BF-94D12D31A9FE.jpg"}]}
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
        return "ConSumRedDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * voucherid : 256
         * type : 1
         * vname : 哈哈大笑
         * vcontent : 满200元减20减
         * step : 1
         * condition : 200
         * money : 20
         * amount : 5
         * receive : 2
         * day : 2
         * create_time : 1539918552
         * e_time : 1540051199
         * content : 填一下推广内容
         * target_url :
         * busid : 1684
         * userid : 35828
         * nickname : rm_13682385800
         * businessid : 1684
         * businessname : 哈哈大笑
         * addra : 金龙工业城
         * address : 计算机
         * phone : 13923832013
         * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FA600C53-D6FE-4AB5-BA9D-3EF94CBFDFDE.jpg
         * longitude : 113.930660
         * latitude : 22.548860
         * scope :
         * receiver_list : [{"record_id":349,"busid":1684,"userid":35822,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg"},{"record_id":352,"busid":1684,"userid":35901,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/16/35901201810161006501862.jpg"}]
         * red_img : [{"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/83A82F4E-76CD-4093-849F-E95ED907D821.jpg"},{"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2A200CEE-CDA0-4F0E-8B6F-1288ACF5E41D.jpg"},{"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/5707817D-B707-4C89-8A84-D672ADBF76AF.jpg"}]
         * business_img : [{"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/A7E3ADF7-27B5-4FD1-A0BF-94D12D31A9FE.jpg"}]
         */

        private int voucherid;
        private int type;
        private String vname;
        private String vcontent;
        private int step;
        private String condition;
        private String money;
        private int amount;
        private int receive;
        private int day;
        private int create_time;
        private int e_time;
        private String content;
        private String target_url;
        private int busid;
        private int userid;
        private String nickname;
        private int businessid;
        private String businessname;
        private String addra;
        private String address;
        private String phone;
        private String logo;
        private String longitude;
        private String latitude;
        private String scope;
        private List<ReceiverListBean> receiver_list;
        private List<RedImgBean> red_img;
        private List<BusinessImgBean> business_img;

        public int getVoucherid() {
            return voucherid;
        }

        public void setVoucherid(int voucherid) {
            this.voucherid = voucherid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }

        public String getVcontent() {
            return vcontent;
        }

        public void setVcontent(String vcontent) {
            this.vcontent = vcontent;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getReceive() {
            return receive;
        }

        public void setReceive(int receive) {
            this.receive = receive;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getE_time() {
            return e_time;
        }

        public void setE_time(int e_time) {
            this.e_time = e_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTarget_url() {
            return target_url;
        }

        public void setTarget_url(String target_url) {
            this.target_url = target_url;
        }

        public int getBusid() {
            return busid;
        }

        public void setBusid(int busid) {
            this.busid = busid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getBusinessid() {
            return businessid;
        }

        public void setBusinessid(int businessid) {
            this.businessid = businessid;
        }

        public String getBusinessname() {
            return businessname;
        }

        public void setBusinessname(String businessname) {
            this.businessname = businessname;
        }

        public String getAddra() {
            return addra;
        }

        public void setAddra(String addra) {
            this.addra = addra;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public List<ReceiverListBean> getReceiver_list() {
            return receiver_list;
        }

        public void setReceiver_list(List<ReceiverListBean> receiver_list) {
            this.receiver_list = receiver_list;
        }

        public List<RedImgBean> getRed_img() {
            return red_img;
        }

        public void setRed_img(List<RedImgBean> red_img) {
            this.red_img = red_img;
        }

        public List<BusinessImgBean> getBusiness_img() {
            return business_img;
        }

        public void setBusiness_img(List<BusinessImgBean> business_img) {
            this.business_img = business_img;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "voucherid=" + voucherid +
                    ", type=" + type +
                    ", vname='" + vname + '\'' +
                    ", vcontent='" + vcontent + '\'' +
                    ", step=" + step +
                    ", condition='" + condition + '\'' +
                    ", money='" + money + '\'' +
                    ", amount=" + amount +
                    ", receive=" + receive +
                    ", day=" + day +
                    ", create_time=" + create_time +
                    ", e_time=" + e_time +
                    ", content='" + content + '\'' +
                    ", target_url='" + target_url + '\'' +
                    ", busid=" + busid +
                    ", userid=" + userid +
                    ", nickname='" + nickname + '\'' +
                    ", businessid=" + businessid +
                    ", businessname='" + businessname + '\'' +
                    ", addra='" + addra + '\'' +
                    ", address='" + address + '\'' +
                    ", phone='" + phone + '\'' +
                    ", logo='" + logo + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", scope='" + scope + '\'' +
                    ", receiver_list=" + receiver_list +
                    ", red_img=" + red_img +
                    ", business_img=" + business_img +
                    '}';
        }

        public static class ReceiverListBean {
            /**
             * record_id : 349
             * busid : 1684
             * userid : 35822
             * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg
             */

            private int record_id;
            private int busid;
            private int userid;
            private String avatar;

            public int getRecord_id() {
                return record_id;
            }

            public void setRecord_id(int record_id) {
                this.record_id = record_id;
            }

            public int getBusid() {
                return busid;
            }

            public void setBusid(int busid) {
                this.busid = busid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            @Override
            public String toString() {
                return "ReceiverListBean{" +
                        "record_id=" + record_id +
                        ", busid=" + busid +
                        ", userid=" + userid +
                        ", avatar='" + avatar + '\'' +
                        '}';
            }
        }

        public static class RedImgBean {
            /**
             * url : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/83A82F4E-76CD-4093-849F-E95ED907D821.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "RedImgBean{" +
                        "url='" + url + '\'' +
                        '}';
            }
        }

        public static class BusinessImgBean {
            /**
             * url : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/A7E3ADF7-27B5-4FD1-A0BF-94D12D31A9FE.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "BusinessImgBean{" +
                        "url='" + url + '\'' +
                        '}';
            }
        }
    }
}
