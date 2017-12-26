package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class CircleTagResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":3,"name":"90后"},{"id":4,"name":"爱运动"},{"id":5,"name":"美少女"},{"id":6,"name":"飞毛腿"},{"id":7,"name":"北包客"},{"id":8,"name":"铲屎官"},{"id":9,"name":"女神"},{"id":10,"name":"耳机族"},{"id":11,"name":"魅力大叔"},{"id":12,"name":"大长腿"},{"id":13,"name":"精力充沛"},{"id":14,"name":"生长达人"}]
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
         * id : 3
         * name : 90后
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
