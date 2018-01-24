package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2017/12/21.
 */
/*
@className :UserInfoResponse
*@date 2018/1/24
*@author
*@description  用户个人信息
*/
public class UserInfoResponse {

    /**
     * error : 0
     * message : success
     * data : {"id":1,"openid":"","mobile":"18588278880","levelid":3,"sex":0,"birthyear":1992,"birthmonth":1,"birthday":30,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","type":1,"nickname":"陈杰","province":"广东省","city":"深圳市","balance":"43.66","target_step":10000,"credit":"0.00","status":0,"creat_time":1512699325,"level":"第三段","followCount":9,"circleCount":44,"authentication_status":4,"messagesCount":1}
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
        return "UserInfoResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 1
         * openid :
         * mobile : 18588278880
         * levelid : 3
         * sex : 0 0:男 1：女
         * birthyear : 1992
         * birthmonth : 1
         * birthday : 30
         * avatar : http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg
         * type : 1
         * nickname : 陈杰
         * province : 广东省
         * city : 深圳市
         * balance : 43.66  余额
         * target_step : 10000
         * credit : 0.00  步币
         * status : 0     是否被禁用
         * creat_time : 1512699325
         * level : 第三段
         * followCount : 9
         * circleCount : 44
         * authentication_status : 4  4，未认证
         * messagesCount : 1
         */

        private int id;
        private String openid;
        private String mobile;
        private int levelid;
        private int sex;
        private int birthyear;
        private int birthmonth;
        private int birthday;
        private String avatar;
        private int type;
        private String nickname;
        private String province;
        private String city;
        private String balance;
        private int target_step;
        private String credit;
        private int status;
        private int creat_time;
        private String level;
        private int followCount;
        private int circleCount;
        private int authentication_status;
        private int messagesCount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getLevelid() {
            return levelid;
        }

        public void setLevelid(int levelid) {
            this.levelid = levelid;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getBirthyear() {
            return birthyear;
        }

        public void setBirthyear(int birthyear) {
            this.birthyear = birthyear;
        }

        public int getBirthmonth() {
            return birthmonth;
        }

        public void setBirthmonth(int birthmonth) {
            this.birthmonth = birthmonth;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public int getTarget_step() {
            return target_step;
        }

        public void setTarget_step(int target_step) {
            this.target_step = target_step;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(int creat_time) {
            this.creat_time = creat_time;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getCircleCount() {
            return circleCount;
        }

        public void setCircleCount(int circleCount) {
            this.circleCount = circleCount;
        }

        public int getAuthentication_status() {
            return authentication_status;
        }

        public void setAuthentication_status(int authentication_status) {
            this.authentication_status = authentication_status;
        }

        public int getMessagesCount() {
            return messagesCount;
        }

        public void setMessagesCount(int messagesCount) {
            this.messagesCount = messagesCount;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", openid='" + openid + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", levelid=" + levelid +
                    ", sex=" + sex +
                    ", birthyear=" + birthyear +
                    ", birthmonth=" + birthmonth +
                    ", birthday=" + birthday +
                    ", avatar='" + avatar + '\'' +
                    ", type=" + type +
                    ", nickname='" + nickname + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", balance='" + balance + '\'' +
                    ", target_step=" + target_step +
                    ", credit='" + credit + '\'' +
                    ", status=" + status +
                    ", creat_time=" + creat_time +
                    ", level='" + level + '\'' +
                    ", followCount=" + followCount +
                    ", circleCount=" + circleCount +
                    ", authentication_status=" + authentication_status +
                    ", messagesCount=" + messagesCount +
                    '}';
        }
    }
}
