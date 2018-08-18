package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/6/22.
 */

public class TargetDistanceResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":1,"distance":1000},{"id":2,"distance":2000},{"id":3,"distance":3000},{"id":4,"distance":5000},{"id":5,"distance":10000},{"id":6,"distance":15000},{"id":7,"distance":20000},{"id":8,"distance":30000},{"id":9,"distance":45000},{"id":10,"distance":50000}]
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
         * distance : 1000
         */

        private int id;
        private int distance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
