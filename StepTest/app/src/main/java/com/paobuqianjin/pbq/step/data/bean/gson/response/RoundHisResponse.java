package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/8/20.
 */

public class RoundHisResponse {
    /**
     * error : 0
     * message : success
     * data : {"red_id":690,"voucherid":238,"userid":35822,"businessid":1666,"content":"FBI招募","number":100,"money":"48.50","receive_num":0,"target_url":"https://uland.taobao.com/semm/tbsearch?refpid=mm_33231677_4306871_14580636&keyword=%E5%A5%B3%E8%A3%85&rewriteQuery=1&clk1=64c58816b4ecc03c988f695a729d0569&upsid=64c58816b4ecc03c988f695a729d0569&mi=&sms=sogou#_mmwut_=1539590749103&c=sub&market=%E7%BB%85%E5","circleid":101555,"circle_pwd":"123456","distance":5000,"create_time":1539590850,"logo":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","name":"疼训氪金有限公司","tel":"13424156029","addra":"北环南海立交桥","address":"北环南海立交桥","do_day":"","s_do_time":"","e_do_time":"","scope":"","longitude":"113.933487","latitude":"22.552158","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","is_me":1,"red_img":[{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604877128.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604962710.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604274811.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604783706.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604726988.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604270832.jpg"}],"business_img":[{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506308751.jpg"}],"is_receive":0,"receiver_list":[],"income_money":0,"is_zan":0,"zan_count":0,"comment_count":0,"comment_list":[],"voucher":{"vid":238,"vname":"咸鱼优惠","money":"100.00","condition":"1500.00","e_time":1542124799,"status":0}}
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
        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        /**
         * red_id : 690
         * voucherid : 238
         * userid : 35822
         * businessid : 1666
         * content : FBI招募
         * number : 100
         * money : 48.50
         * receive_num : 0
         * target_url : https://uland.taobao.com/semm/tbsearch?refpid=mm_33231677_4306871_14580636&keyword=%E5%A5%B3%E8%A3%85&rewriteQuery=1&clk1=64c58816b4ecc03c988f695a729d0569&upsid=64c58816b4ecc03c988f695a729d0569&mi=&sms=sogou#_mmwut_=1539590749103&c=sub&market=%E7%BB%85%E5
         * circleid : 101555
         * circle_pwd : 123456
         * distance : 5000
         * create_time : 1539590850
         * logo : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg
         * name : 疼训氪金有限公司
         * tel : 13424156029
         * addra : 北环南海立交桥
         * address : 北环南海立交桥
         * do_day :
         * s_do_time :
         * e_do_time :
         * scope :
         * type:2
         * credit:1
         * longitude : 113.933487
         * latitude : 22.552158
         * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg
         * is_me : 1
         * red_img : [{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604877128.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604962710.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604274811.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604783706.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604726988.jpg"},{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/15/35822201810151604270832.jpg"}]
         * business_img : [{"url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506308751.jpg"}]
         * is_receive : 0
         * receiver_list : []
         * income_money : 0
         * is_zan : 0
         * zan_count : 0
         * comment_count : 0
         * comment_list : []
         * voucher : {"vid":238,"vname":"咸鱼优惠","money":"100.00","condition":"1500.00","e_time":1542124799,"status":0}
         */

        private int type;
        private int credit;
        private int red_id;
        private int voucherid;
        private int userid;
        private int businessid;
        private String content;
        private int number;
        private String money;
        private int receive_num;
        private String target_url;
        private String circleid;
        private String circle_pwd;
        private int distance;
        private long create_time;
        private String logo;
        private String name;
        private String tel;
        private String addra;
        private String address;
        private String do_day;
        private String s_do_time;
        private String e_do_time;
        private String scope;
        private String longitude;
        private String latitude;
        private String avatar;
        private int is_me;
        private int is_receive;
        private String income_money;
        private int is_zan;
        private int zan_count;
        private int comment_count;
        private VoucherBean voucher;
        private List<RedImgBean> red_img;
        private List<BusinessImgBean> business_img;
        private List<ReceiverListBean> receiver_list;
        private List<CommentListBean> comment_list;

        public int getRed_id() {
            return red_id;
        }

        public void setRed_id(int red_id) {
            this.red_id = red_id;
        }

        public int getVoucherid() {
            return voucherid;
        }

        public void setVoucherid(int voucherid) {
            this.voucherid = voucherid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getBusinessid() {
            return businessid;
        }

        public void setBusinessid(int businessid) {
            this.businessid = businessid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getReceive_num() {
            return receive_num;
        }

        public void setReceive_num(int receive_num) {
            this.receive_num = receive_num;
        }

        public String getTarget_url() {
            return target_url;
        }

        public void setTarget_url(String target_url) {
            this.target_url = target_url;
        }

        public String getCircleid() {
            return circleid;
        }

        public void setCircleid(String circleid) {
            this.circleid = circleid;
        }

        public String getCircle_pwd() {
            return circle_pwd;
        }

        public void setCircle_pwd(String circle_pwd) {
            this.circle_pwd = circle_pwd;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
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

        public String getDo_day() {
            return do_day;
        }

        public void setDo_day(String do_day) {
            this.do_day = do_day;
        }

        public String getS_do_time() {
            return s_do_time;
        }

        public void setS_do_time(String s_do_time) {
            this.s_do_time = s_do_time;
        }

        public String getE_do_time() {
            return e_do_time;
        }

        public void setE_do_time(String e_do_time) {
            this.e_do_time = e_do_time;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getIs_me() {
            return is_me;
        }

        public void setIs_me(int is_me) {
            this.is_me = is_me;
        }

        public int getIs_receive() {
            return is_receive;
        }

        public void setIs_receive(int is_receive) {
            this.is_receive = is_receive;
        }

        public String getIncome_money() {
            return income_money;
        }

        public void setIncome_money(String income_money) {
            this.income_money = income_money;
        }

        public int getIs_zan() {
            return is_zan;
        }

        public void setIs_zan(int is_zan) {
            this.is_zan = is_zan;
        }

        public int getZan_count() {
            return zan_count;
        }

        public void setZan_count(int zan_count) {
            this.zan_count = zan_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public VoucherBean getVoucher() {
            return voucher;
        }

        public void setVoucher(VoucherBean voucher) {
            this.voucher = voucher;
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

        public List<ReceiverListBean> getReceiver_list() {
            return receiver_list;
        }

        public void setReceiver_list(List<ReceiverListBean> receiver_list) {
            this.receiver_list = receiver_list;
        }

        public List<CommentListBean> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public static class VoucherBean {
            /**
             * vid : 238
             * vname : 咸鱼优惠
             * money : 100.00
             * condition : 1500.00
             * e_time : 1542124799
             * status : 0
             */

            private int vid;
            private String vname;
            private String money;
            private String condition;
            private long e_time;
            private int status;

            public int getVid() {
                return vid;
            }

            public void setVid(int vid) {
                this.vid = vid;
            }

            public String getVname() {
                return vname;
            }

            public void setVname(String vname) {
                this.vname = vname;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public long getE_time() {
                return e_time;
            }

            public void setE_time(long e_time) {
                this.e_time = e_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class RedImgBean {
            /**
             * url : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35822201808181409250016.png
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class BusinessImgBean {
            /**
             * url : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/13/35822201808131007600980.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }


        public static class CommentListBean {
            /**
             * eid : 260
             * userid : 35882
             * grade : 0
             * fatherid : 0
             * score : 0.0
             * content : 收藏了
             * businessid : 1636
             * create_time : 1534486883
             * avatar : https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png
             * nickname : rm_13148896029
             * father_avatar : null
             * father_nickname : null
             * user_is_vip : 0
             * user_is_cusvip : 0
             * user_is_gvip : 0
             * father_is_vip : null
             * father_is_cusvip : null
             * father_is_gvip : null
             */

            private int eid;
            private int userid;
            private int grade;
            private int fatherid;
            private String score;
            private String content;
            private int businessid;
            private long create_time;
            private String avatar;
            private String nickname;
            private String father_avatar;
            private String father_nickname;
            private int user_is_vip;
            private int user_is_cusvip;
            private int user_is_gvip;
            private int father_is_vip;
            private int father_is_cusvip;
            private int father_is_gvip;

            public int getEid() {
                return eid;
            }

            public void setEid(int eid) {
                this.eid = eid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getFatherid() {
                return fatherid;
            }

            public void setFatherid(int fatherid) {
                this.fatherid = fatherid;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getBusinessid() {
                return businessid;
            }

            public void setBusinessid(int businessid) {
                this.businessid = businessid;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Object getFather_avatar() {
                return father_avatar;
            }

            public void setFather_avatar(String father_avatar) {
                this.father_avatar = father_avatar;
            }

            public String getFather_nickname() {
                return father_nickname;
            }

            public void setFather_nickname(String father_nickname) {
                this.father_nickname = father_nickname;
            }

            public int getUser_is_vip() {
                return user_is_vip;
            }

            public void setUser_is_vip(int user_is_vip) {
                this.user_is_vip = user_is_vip;
            }

            public int getUser_is_cusvip() {
                return user_is_cusvip;
            }

            public void setUser_is_cusvip(int user_is_cusvip) {
                this.user_is_cusvip = user_is_cusvip;
            }

            public int getUser_is_gvip() {
                return user_is_gvip;
            }

            public void setUser_is_gvip(int user_is_gvip) {
                this.user_is_gvip = user_is_gvip;
            }

            public Object getFather_is_vip() {
                return father_is_vip;
            }

            public void setFather_is_vip(int father_is_vip) {
                this.father_is_vip = father_is_vip;
            }

            public int getFather_is_cusvip() {
                return father_is_cusvip;
            }

            public void setFather_is_cusvip(int father_is_cusvip) {
                this.father_is_cusvip = father_is_cusvip;
            }

            public int getFather_is_gvip() {
                return father_is_gvip;
            }

            public void setFather_is_gvip(int father_is_gvip) {
                this.father_is_gvip = father_is_gvip;
            }
        }

        public static class ReceiverListBean {
            /**
             * money : 9.69000
             * receive_uid : 35882
             * avatar : https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png
             */

            private String money;
            private int receive_uid;
            private String avatar;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getReceive_uid() {
                return receive_uid;
            }

            public void setReceive_uid(int receive_uid) {
                this.receive_uid = receive_uid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
