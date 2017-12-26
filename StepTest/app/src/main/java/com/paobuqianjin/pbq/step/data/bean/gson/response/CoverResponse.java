package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class CoverResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":1,"conver":"http://www.paobu.com/img/bg01.png"},{"id":2,"conver":"http://www.paobu.com/img/bg02.png"},{"id":3,"conver":"http://www.paobu.com/img/bg03.png"},{"id":4,"conver":"http://www.paobu.com/img/bg04.png"},{"id":5,"conver":"http://www.paobu.com/img/bg05.png"}]
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
         * id : 1
         * conver : http://www.paobu.com/img/bg01.png
         */

        private int id;
        private String conver;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getConver() {
            return conver;
        }

        public void setConver(String conver) {
            this.conver = conver;
        }
    }
}
