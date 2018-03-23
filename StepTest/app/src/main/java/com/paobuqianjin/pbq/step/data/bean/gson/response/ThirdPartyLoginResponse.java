package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;

/**
 * Created by pbq on 2018/1/27.
 */

public class ThirdPartyLoginResponse {

    /**
     * error : 0
     * message : 登陆成功
     * data : {"id":30,"wx_openid":"oPd5d0SDHRV1oZF8dB80Jq0kH_tY","qq_openid":"","mobile":"","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","levelid":1,"sex":0,"birthyear":0,"birthmonth":0,"birthday":0,"height":169,"weight":120,"type":0,"province":"广东","city":"深圳","balance":"4.71","target_step":0,"credit":0,"status":0,"is_perfect":0,"create_time":1513757559,"delete_time":null}
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
        return "ThirdPartyLoginResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        /**
         * id : 30
         * wx_openid : oPd5d0SDHRV1oZF8dB80Jq0kH_tY
         * qq_openid :
         * mobile :
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
         * nickname : 黄钦平
         * levelid : 1
         * sex : 0
         * birthyear : 0
         * birthmonth : 0
         * birthday : 0
         * height : 169
         * weight : 120
         * type : 0
         * province : 广东
         * city : 深圳
         * balance : 4.71
         * target_step : 0
         * credit : 0
         * status : 0
         * is_perfect : 0
         * create_time : 1513757559
         * delete_time : null
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
        private int weight;
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

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
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
                    ", weight=" + weight +
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
                    '}';
        }
    }
}
