package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/12/25.
 */

public class RefreshTokenResponse {
    /**
     * error : 0
     * message : sccess
     * data : {"userid":35822,"userno":"88888888888","user_token":"35822:Elg5JCJ0QB8tVrIbrwB8W5ESs92uBiPz"}
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
         * userid : 35822
         * userno : 88888888888
         * user_token : 35822:Elg5JCJ0QB8tVrIbrwB8W5ESs92uBiPz
         */

        private int userid;
        private String userno;
        private String user_token;

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

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }
    }
}
