package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/7/10.
 */

public class PhoneCheckResponse {

    /**
     * error : 0
     * message : success
     * data : {"status":1,"userid":35774,"userno":35774,"nickname":"rm_30717","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","follow_type":3}
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
         * status : 1
         * userid : 35774
         * userno : 35774
         * nickname : rm_30717
         * avatar : https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png
         * follow_type : 3
         */

        private int status;
        private int userid;
        private int userno;
        private String nickname;
        private String avatar;
        private int follow_type;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getUserno() {
            return userno;
        }

        public void setUserno(int userno) {
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

        public int getFollow_type() {
            return follow_type;
        }

        public void setFollow_type(int follow_type) {
            this.follow_type = follow_type;
        }
    }
}
