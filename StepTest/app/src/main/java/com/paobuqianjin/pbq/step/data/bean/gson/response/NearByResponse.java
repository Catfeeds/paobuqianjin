package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/5.
 */
/*
@className :NearByResponse
*@date 2018/2/5
*@author
*@description 获取附近的人接口返回
*/
public class NearByResponse {
    /**
     * error : 0
     * message : success
     * data : [{"userid":25,"avatar":"http://www.admin.com/uploads/20180112/7a43b4fad29a1d1cae79b538590572aa.jpg","nickname":"rm_18276811234","sex":1,"longitude":"86.26000","latitude":"35.17000","login_time":1513411592,"distance":0,"user_step":0,"is_follow":0},{"userid":4,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","nickname":"团魂","sex":1,"longitude":"86.26000","latitude":"35.17000","login_time":1513150041,"distance":0,"user_step":0,"is_follow":0},{"userid":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","sex":0,"longitude":"86.26001","latitude":"35.17000","login_time":1513150041,"distance":0.91054358017021,"user_step":0,"is_follow":0},{"userid":24,"avatar":"","nickname":"rm_18276810057","sex":0,"longitude":"86.26464","latitude":"35.17380","login_time":1513331727,"distance":596.97325665894,"user_step":0,"is_follow":0}]
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
         * userid : 25
         * avatar : http://www.admin.com/uploads/20180112/7a43b4fad29a1d1cae79b538590572aa.jpg
         * nickname : rm_18276811234
         * sex : 1
         * longitude : 86.26000
         * latitude : 35.17000
         * login_time : 1513411592
         * distance : 0
         * user_step : 0
         * is_follow : 0
         */

        private int userid;
        private String avatar;
        private String nickname;
        private int sex;
        private String longitude;
        private String latitude;
        private int login_time;
        private double distance;
        private int user_step;
        private int is_follow;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public int getLogin_time() {
            return login_time;
        }

        public void setLogin_time(int login_time) {
            this.login_time = login_time;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getUser_step() {
            return user_step;
        }

        public void setUser_step(int user_step) {
            this.user_step = user_step;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }
    }
}
