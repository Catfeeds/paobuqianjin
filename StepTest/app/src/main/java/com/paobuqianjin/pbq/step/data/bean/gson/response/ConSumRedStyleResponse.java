package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/10/17.
 */

public class ConSumRedStyleResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":1,"pid":0,"name":"其它","icon":"https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/voucher_cate/Other.png"},{"id":2,"pid":0,"name":"美食","icon":"https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/voucher_cate/food.png"},{"id":3,"pid":0,"name":"休闲娱乐","icon":"https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/voucher_cate/Leisure.png"},{"id":4,"pid":0,"name":"美容美发","icon":"https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/voucher_cate/beauty.png"},{"id":5,"pid":0,"name":"学习培训","icon":"https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/voucher_cate/Study.png"}]
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

    @Override
    public String toString() {
        return "ConSumRedStyleResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 1
         * pid : 0
         * name : 其它
         * icon : https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/voucher_cate/Other.png
         */

        private int id;
        private int pid;
        private String name;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", pid=" + pid +
                    ", name='" + name + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }
}
