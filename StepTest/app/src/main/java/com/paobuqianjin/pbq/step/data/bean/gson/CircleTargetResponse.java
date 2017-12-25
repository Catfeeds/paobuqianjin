package com.paobuqianjin.pbq.step.data.bean.gson;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */

public class CircleTargetResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":1,"target":5000},{"id":2,"target":8000},{"id":3,"target":10000},{"id":4,"target":20000},{"id":5,"target":40000},{"id":6,"target":60000}]
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
         * target : 5000
         */

        private int id;
        private int target;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }
    }
}
