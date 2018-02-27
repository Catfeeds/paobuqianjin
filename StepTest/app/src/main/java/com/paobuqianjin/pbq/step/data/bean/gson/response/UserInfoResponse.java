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
     * data : {"id":1,"wx_openid":"","qq_openid":"","mobile":"18588278880","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D0922F24-15D6-451E-B432-9B1F123D2C9A.jpg","nickname":"嗯额咳咳","levelid":3,"sex":0,"birthyear":2018,"birthmonth":2,"birthday":26,"height":166,"type":1,"province":"广东省","city":"广州市","balance":"67.94","target_step":10000,"credit":60,"status":0,"is_perfect":1,"create_time":1512699325,"level":"第三段","followCount":5,"circleCount":95,"authentication_status":1,"messagesCount":5}
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
         * wx_openid :
         * qq_openid :
         * mobile : 18588278880
         * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D0922F24-15D6-451E-B432-9B1F123D2C9A.jpg
         * nickname : 嗯额咳咳
         * levelid : 3
         * sex : 0
         * birthyear : 2018
         * birthmonth : 2
         * birthday : 26
         * height : 166
         * type : 1
         * province : 广东省
         * city : 广州市
         * balance : 67.94
         * target_step : 10000
         * credit : 60
         * status : 0
         * is_perfect : 1
         * create_time : 1512699325
         * level : 第三段
         * followCount : 5
         * circleCount : 95
         * authentication_status : 1
         * messagesCount : 5
         */

        private int id;
        private String wx_openid;
        private String qq_openid;
        private String mobile;
        private String avatar;
        private String nickname;
        private int levelid;
        private int sex;
        private int birthyear;
        private int birthmonth;
        private int birthday;
        private int height;
        private int type;
        private String province;
        private String city;
        private String balance;
        private int target_step;
        private int credit;
        private int status;
        private int is_perfect;
        private int create_time;
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

        public String getWx_openid() {
            return wx_openid;
        }

        public void setWx_openid(String wx_openid) {
            this.wx_openid = wx_openid;
        }

        public String getQq_openid() {
            return qq_openid;
        }

        public void setQq_openid(String qq_openid) {
            this.qq_openid = qq_openid;
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

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_perfect() {
            return is_perfect;
        }

        public void setIs_perfect(int is_perfect) {
            this.is_perfect = is_perfect;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
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
                    ", wx_openid='" + wx_openid + '\'' +
                    ", qq_openid='" + qq_openid + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", levelid=" + levelid +
                    ", sex=" + sex +
                    ", birthyear=" + birthyear +
                    ", birthmonth=" + birthmonth +
                    ", birthday=" + birthday +
                    ", height=" + height +
                    ", type=" + type +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", balance='" + balance + '\'' +
                    ", target_step=" + target_step +
                    ", credit=" + credit +
                    ", status=" + status +
                    ", is_perfect=" + is_perfect +
                    ", create_time=" + create_time +
                    ", level='" + level + '\'' +
                    ", followCount=" + followCount +
                    ", circleCount=" + circleCount +
                    ", authentication_status=" + authentication_status +
                    ", messagesCount=" + messagesCount +
                    '}';
        }
    }
}
