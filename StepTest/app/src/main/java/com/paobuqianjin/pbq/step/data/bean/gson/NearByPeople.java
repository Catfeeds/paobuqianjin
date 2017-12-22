package com.paobuqianjin.pbq.step.data.bean.gson;

import java.util.List;

/**
 * Created by pbq on 2017/12/22.
 */

public class NearByPeople {
    /**
     * error : 0
     * message : success
     * data : [{"id":4,"mobile":"13505000020","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","nickname":"团魂","user_step":2723,"logintime":1513567363,"distance":0},{"id":25,"mobile":"18276811234","avatar":"http://pic.qqtn.com/up/2017-11/15115072227199415.jpg","nickname":"rm_18276811234","user_step":0,"logintime":1513567421,"distance":0},{"id":1,"mobile":"18588278880","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","nickname":"陈杰","user_step":555,"logintime":1513245604,"distance":0.91054358017021},{"id":2,"mobile":"18276810059","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184279124.jpg","nickname":"张三","user_step":8701,"logintime":1513341174,"distance":0.91054358017021},{"id":24,"mobile":"18276810057","avatar":"","nickname":"rm_18276810057","user_step":0,"logintime":1513337772,"distance":596.9732560037}]
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
        return "NearByPeople{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 4
         * mobile : 13505000020
         * avatar : http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg
         * nickname : 团魂
         * user_step : 2723
         * logintime : 1513567363
         * distance : 0
         */

        private int id;
        private String mobile;
        private String avatar;
        private String nickname;
        private int user_step;
        private long logintime;
        private double distance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public int getUser_step() {
            return user_step;
        }

        public void setUser_step(int user_step) {
            this.user_step = user_step;
        }

        public long getLogintime() {
            return logintime;
        }

        public void setLogintime(long logintime) {
            this.logintime = logintime;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", mobile='" + mobile + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", user_step=" + user_step +
                    ", logintime=" + logintime +
                    ", distance=" + distance +
                    '}';
        }
    }
}
