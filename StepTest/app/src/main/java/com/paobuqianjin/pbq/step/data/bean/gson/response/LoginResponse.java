package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2017/12/21.
 */

public class LoginResponse {
    /**
     * error : 0
     * message : 登陆成功
     * data : {"id":30,"mobile":"13424156029","avatar":"","openid":"","type":0,"nickname":"rm_13424156029","sex":0,"city":"","amount":"0.00","target_step":0,"credit":"0.00","levelid":0,"register_time":1513757559}
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
         * id : 30
         * mobile : 13424156029
         * avatar :
         * openid :
         * type : 0
         * nickname : rm_13424156029
         * sex : 0
         * city :
         * amount : 0.00
         * target_step : 0
         * credit : 0.00
         * levelid : 0
         * register_time : 1513757559
         */

        private int id;
        private String mobile;
        private String avatar;
        private String openid;
        private int type;
        private String nickname;
        private int sex;
        private String city;
        private String amount;
        private int target_step;
        private String credit;
        private int levelid;
        private int register_time;

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

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
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

        public int getLevelid() {
            return levelid;
        }

        public void setLevelid(int levelid) {
            this.levelid = levelid;
        }

        public int getRegister_time() {
            return register_time;
        }

        public void setRegister_time(int register_time) {
            this.register_time = register_time;
        }
    }

}
