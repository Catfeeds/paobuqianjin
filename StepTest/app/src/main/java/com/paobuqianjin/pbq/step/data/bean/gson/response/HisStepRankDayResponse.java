package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/12/11.
 */

public class HisStepRankDayResponse {
    /**
     * error : 0
     * message : success
     * data : {"member":[{"userid":35842,"nickname":"大王","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/516FA83C-8D45-42C1-8011-E8DEFC2883ED.jpg","vip":1,"cusvip":0,"step_number":"1432","user_step_id":3146,"rnickname":"大王","zan_count":0,"is_zan":0},{"userid":35822,"nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/11/29/35822201811291414825696.jpg","vip":1,"cusvip":0,"step_number":"269","user_step_id":3147,"rnickname":"江月何时初照人","zan_count":0,"is_zan":0},{"userid":35828,"nickname":"rm_13682385800","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/9BFA16BA-349F-4FA2-956F-80B1C7B8E593.jpg","vip":1,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_13682385800","zan_count":0,"is_zan":0},{"userid":35875,"nickname":"rm_13652354126ha","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/25/35875201808251431128180.jpg","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_13652354126ha","zan_count":0,"is_zan":0},{"userid":35876,"nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_18620134550","zan_count":0,"is_zan":0},{"userid":35877,"nickname":"rm_17191987961","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_17191987961","zan_count":0,"is_zan":0},{"userid":35882,"nickname":"rm_13148896029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35882201808201416921087.jpg","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_13148896029","zan_count":0,"is_zan":0},{"userid":35886,"nickname":"山羊","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/31/35886201808311455174969.jpg","vip":1,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"山羊","zan_count":0,"is_zan":0},{"userid":35901,"nickname":"咔","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/16/35901201810161006501862.jpg","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"咔","zan_count":0,"is_zan":0}],"mydata":{"userid":35822,"nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/11/29/35822201811291414825696.jpg","vip":1,"cusvip":0,"step_number":"269","rank":2}}
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
         * member : [{"userid":35842,"nickname":"大王","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/516FA83C-8D45-42C1-8011-E8DEFC2883ED.jpg","vip":1,"cusvip":0,"step_number":"1432","user_step_id":3146,"rnickname":"大王","zan_count":0,"is_zan":0},{"userid":35822,"nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/11/29/35822201811291414825696.jpg","vip":1,"cusvip":0,"step_number":"269","user_step_id":3147,"rnickname":"江月何时初照人","zan_count":0,"is_zan":0},{"userid":35828,"nickname":"rm_13682385800","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/9BFA16BA-349F-4FA2-956F-80B1C7B8E593.jpg","vip":1,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_13682385800","zan_count":0,"is_zan":0},{"userid":35875,"nickname":"rm_13652354126ha","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/25/35875201808251431128180.jpg","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_13652354126ha","zan_count":0,"is_zan":0},{"userid":35876,"nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_18620134550","zan_count":0,"is_zan":0},{"userid":35877,"nickname":"rm_17191987961","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_17191987961","zan_count":0,"is_zan":0},{"userid":35882,"nickname":"rm_13148896029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35882201808201416921087.jpg","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"rm_13148896029","zan_count":0,"is_zan":0},{"userid":35886,"nickname":"山羊","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/31/35886201808311455174969.jpg","vip":1,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"山羊","zan_count":0,"is_zan":0},{"userid":35901,"nickname":"咔","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/16/35901201810161006501862.jpg","vip":0,"cusvip":0,"step_number":"0","user_step_id":0,"rnickname":"咔","zan_count":0,"is_zan":0}]
         * mydata : {"userid":35822,"nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/11/29/35822201811291414825696.jpg","vip":1,"cusvip":0,"step_number":"269","rank":2}
         */

        private MydataBean mydata;
        private List<MemberBean> member;

        public MydataBean getMydata() {
            return mydata;
        }

        public void setMydata(MydataBean mydata) {
            this.mydata = mydata;
        }

        public List<MemberBean> getMember() {
            return member;
        }

        public void setMember(List<MemberBean> member) {
            this.member = member;
        }

        public static class MydataBean {
            /**
             * userid : 35822
             * nickname : 江月何时初照人
             * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/11/29/35822201811291414825696.jpg
             * vip : 1
             * cusvip : 0
             * step_number : 269
             * rank : 2
             */

            private int userid;
            private String nickname;
            private String avatar;
            private int vip;
            private int cusvip;
            private String step_number;
            private int rank;

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public int getCusvip() {
                return cusvip;
            }

            public void setCusvip(int cusvip) {
                this.cusvip = cusvip;
            }

            public String getStep_number() {
                return step_number;
            }

            public void setStep_number(String step_number) {
                this.step_number = step_number;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }
        }

        public static class MemberBean {
            /**
             * userid : 35842
             * nickname : 大王
             * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/516FA83C-8D45-42C1-8011-E8DEFC2883ED.jpg
             * vip : 1
             * cusvip : 0
             * step_number : 1432
             * user_step_id : 3146
             * rnickname : 大王
             * zan_count : 0
             * is_zan : 0
             */

            private int userid;
            private String nickname;
            private String avatar;
            private int vip;
            private int cusvip;
            private String step_number;
            private String user_step_id;
            private String rnickname;
            private int zan_count;
            private int is_zan;

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public int getCusvip() {
                return cusvip;
            }

            public void setCusvip(int cusvip) {
                this.cusvip = cusvip;
            }

            public String getStep_number() {
                return step_number;
            }

            public void setStep_number(String step_number) {
                this.step_number = step_number;
            }

            public String getUser_step_id() {
                return user_step_id;
            }

            public void setUser_step_id(String user_step_id) {
                this.user_step_id = user_step_id;
            }

            public String getRnickname() {
                return rnickname;
            }

            public void setRnickname(String rnickname) {
                this.rnickname = rnickname;
            }

            public int getZan_count() {
                return zan_count;
            }

            public void setZan_count(int zan_count) {
                this.zan_count = zan_count;
            }

            public int getIs_zan() {
                return is_zan;
            }

            public void setIs_zan(int is_zan) {
                this.is_zan = is_zan;
            }
        }
    }
}
