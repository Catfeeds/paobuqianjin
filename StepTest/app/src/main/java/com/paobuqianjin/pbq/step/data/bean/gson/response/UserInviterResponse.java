package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */

public class UserInviterResponse {
    /**
     * error : 0
     * message : success
     * data : [{"inviternum":3,"inviterid":1,"creattime":0,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","nickname":"陈杰"},{"inviternum":2,"inviterid":2,"creattime":0,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184279124.jpg","nickname":"张三"},{"inviternum":1,"inviterid":3,"creattime":0,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣"}]
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
        /**
         * inviternum : 3
         * inviterid : 1
         * creattime : 0
         * avatar : http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg
         * nickname : 陈杰
         */

        private int inviternum;
        private int inviterid;
        private int creattime;
        private String avatar;
        private String nickname;

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        private int vip;

        public int getInviternum() {
            return inviternum;
        }

        public void setInviternum(int inviternum) {
            this.inviternum = inviternum;
        }

        public int getInviterid() {
            return inviterid;
        }

        public void setInviterid(int inviterid) {
            this.inviterid = inviterid;
        }

        public int getCreattime() {
            return creattime;
        }

        public void setCreattime(int creattime) {
            this.creattime = creattime;
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "inviternum=" + inviternum +
                    ", inviterid=" + inviterid +
                    ", creattime=" + creattime +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", vip=" + vip +
                    '}';
        }
    }
}
