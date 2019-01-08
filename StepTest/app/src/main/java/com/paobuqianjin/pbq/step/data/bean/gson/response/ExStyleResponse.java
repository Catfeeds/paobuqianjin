package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2019/1/8.
 */

public class ExStyleResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":1,"categroy_name":"其他","pid":0,"create_time":0},{"id":2,"categroy_name":"玩具","pid":0,"create_time":0},{"id":3,"categroy_name":"家居","pid":0,"create_time":0},{"id":4,"categroy_name":"服装鞋帽","pid":0,"create_time":0},{"id":5,"categroy_name":"美妆个护","pid":0,"create_time":0},{"id":6,"categroy_name":"运动户外","pid":0,"create_time":0},{"id":7,"categroy_name":"数码","pid":0,"create_time":0},{"id":8,"categroy_name":"图书","pid":0,"create_time":0}]
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
         * categroy_name : 其他
         * pid : 0
         * create_time : 0
         */

        private int id;
        private String categroy_name;
        private int pid;
        private int create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategroy_name() {
            return categroy_name;
        }

        public void setCategroy_name(String categroy_name) {
            this.categroy_name = categroy_name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
