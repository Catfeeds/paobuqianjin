package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/7/9.
 */

public class SearchResponse {
    /**
     * error : 0
     * message : success
     * data : [{"userid":25761,"userno":25761,"nickname":"rm_13424156029","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/257612018070314288037034.jpg","vip":1,"cusvip":0}]
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

    public static class DataBean {
        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        /**
         * userid : 25761
         * userno : 25761
         * nickname : rm_13424156029
         * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/257612018070314288037034.jpg
         * vip : 1
         * cusvip : 0
         * follow: 0
         */
        private int follow;
        private int userid;
        private String userno;
        private String nickname;
        private String avatar;
        private int vip;
        private int cusvip;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUserno() {
            return userno;
        }

        public void setUserno(String userno) {
            this.userno = userno;
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
    }
}
