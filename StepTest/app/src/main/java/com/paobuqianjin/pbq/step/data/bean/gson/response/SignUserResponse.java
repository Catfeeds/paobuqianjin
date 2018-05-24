package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;

/**
 * Created by pbq on 2017/12/22.
 */

public class SignUserResponse {
    /**
     * error : 0
     * message : 注册成功
     * data : {"id":"68","no":22247,user_token : "1:kjF1GwBURNi9OSMFTbHArEAuTtjuWjlM"}
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
        return "SignUserResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        /**
         * id : 68
         * no: 22247
         * user_token : 1:kjF1GwBURNi9OSMFTbHArEAuTtjuWjlM
         */
        private int id;
        private String no;
        private String user_token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String  getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
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
                    ", no=" + no +
                    ", user_token='" + user_token + '\'' +
                    '}';
        }
    }
}
