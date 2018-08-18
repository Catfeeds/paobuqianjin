package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;

/**
 * Created by pbq on 2017/12/21.
 */

public class LoginResponse {

    /**
     * error : 0
     * message : 登录成功
     * data : {"id":1,"no":22258,"wx_openid":"","qq_openid":"","mobile":"18588278880","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","levelid":3,"sex":0,"birthyear":2018,"birthmonth":2,"birthday":26,"height":166,"weight":"120.00","type":1,"province":"安徽省","city":"安庆市","balance":"68.08","target_step":10000,"credit":60,"status":0,"is_perfect":1,"create_time":1512699325,"delete_time":null,"logintimes":10,"h_long":null,"h_lat":null,"h_updatetime":0,"c_long":"0.00000","c_lat":"0.00000","c_updatetime":0,"n_long":"0.00000","n_lat":"0.00000","n_updatetime":0,"user_token":"1:kjF1GwBURNi9OSMFTbHArEAuTtjuWjlM"}
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
        return "LoginResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * no:22258
         * wx_openid :
         * qq_openid :
         * mobile : 18588278880
         * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg
         * nickname : 嗯额
         * levelid : 3
         * sex : 0
         * birthyear : 2018
         * birthmonth : 2
         * birthday : 26
         * height : 166
         * weight : 120.00
         * type : 1
         * province : 安徽省
         * city : 安庆市
         * balance : 68.08
         * target_step : 10000
         * credit : 60
         * status : 0
         * is_perfect : 1
         * create_time : 1512699325
         * delete_time : null
         * logintimes : 10
         * h_long : null
         * h_lat : null
         * h_updatetime : 0
         * c_long : 0.00000
         * c_lat : 0.00000
         * c_updatetime : 0
         * n_long : 0.00000
         * n_lat : 0.00000
         * n_updatetime : 0
         * user_token : 1:kjF1GwBURNi9OSMFTbHArEAuTtjuWjlM
         * chat_token : 1:kjF1GwBURNi9OSMFTbHArEAuTtjuWjlM
         * cusvip: 0,
         * appid:1:kjF1GwBURNi9OSMFTbHArEAuTtjuWjlM
         */

        private int id;

        public int getCusvip() {
            return cusvip;
        }

        public void setCusvip(int cusvip) {
            this.cusvip = cusvip;
        }

        private int cusvip;

        public String getNo() {
            return no;
        }
        public void setNo(String no) {
            this.no = no;
        }

        private String no;

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
        private String weight;
        private int type;
        private String province;
        private String city;
        private String balance;
        private int target_step;
        private int credit;
        private int status;
        private int is_perfect;
        private int create_time;
        private Object delete_time;
        private int logintimes;
        private Object h_long;
        private Object h_lat;
        private int h_updatetime;
        private String c_long;
        private String c_lat;
        private int c_updatetime;
        private String n_long;
        private String n_lat;
        private int n_updatetime;
        private String user_token;
        private String chat_token;
        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        private int state;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        private String appid;

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        private int vip;

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

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
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

        public Object getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(Object delete_time) {
            this.delete_time = delete_time;
        }

        public int getLogintimes() {
            return logintimes;
        }

        public void setLogintimes(int logintimes) {
            this.logintimes = logintimes;
        }

        public Object getH_long() {
            return h_long;
        }

        public void setH_long(Object h_long) {
            this.h_long = h_long;
        }

        public Object getH_lat() {
            return h_lat;
        }

        public void setH_lat(Object h_lat) {
            this.h_lat = h_lat;
        }

        public int getH_updatetime() {
            return h_updatetime;
        }

        public void setH_updatetime(int h_updatetime) {
            this.h_updatetime = h_updatetime;
        }

        public String getC_long() {
            return c_long;
        }

        public void setC_long(String c_long) {
            this.c_long = c_long;
        }

        public String getC_lat() {
            return c_lat;
        }

        public void setC_lat(String c_lat) {
            this.c_lat = c_lat;
        }

        public int getC_updatetime() {
            return c_updatetime;
        }

        public void setC_updatetime(int c_updatetime) {
            this.c_updatetime = c_updatetime;
        }

        public String getN_long() {
            return n_long;
        }

        public void setN_long(String n_long) {
            this.n_long = n_long;
        }

        public String getN_lat() {
            return n_lat;
        }

        public void setN_lat(String n_lat) {
            this.n_lat = n_lat;
        }

        public int getN_updatetime() {
            return n_updatetime;
        }

        public void setN_updatetime(int n_updatetime) {
            this.n_updatetime = n_updatetime;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", cusvip=" + cusvip +
                    ", no='" + no + '\'' +
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
                    ", weight='" + weight + '\'' +
                    ", type=" + type +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", balance='" + balance + '\'' +
                    ", target_step=" + target_step +
                    ", credit=" + credit +
                    ", status=" + status +
                    ", is_perfect=" + is_perfect +
                    ", create_time=" + create_time +
                    ", delete_time=" + delete_time +
                    ", logintimes=" + logintimes +
                    ", h_long=" + h_long +
                    ", h_lat=" + h_lat +
                    ", h_updatetime=" + h_updatetime +
                    ", c_long='" + c_long + '\'' +
                    ", c_lat='" + c_lat + '\'' +
                    ", c_updatetime=" + c_updatetime +
                    ", n_long='" + n_long + '\'' +
                    ", n_lat='" + n_lat + '\'' +
                    ", n_updatetime=" + n_updatetime +
                    ", user_token='" + user_token + '\'' +
                    ", chat_token='" + chat_token + '\'' +
                    ", vip=" + vip +
                    '}';
        }

        public String getChat_token() {
            return chat_token;
        }

        public void setChat_token(String chat_token) {
            this.chat_token = chat_token;
        }
    }
}
