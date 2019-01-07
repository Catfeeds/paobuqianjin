package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2019/1/7.
 */

public class TrSugResponse {
    /**
     * error : 0
     * message : success
     * data : [{"logo":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/other/express_company/kuaidi%402x.png","name":"普通快递","first_weight":12,"area":1,"renew_weight":8},{"logo":"https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/other/express_company/shunfeng%402x.png","name":"顺丰快递","first_weight":22,"area":1,"renew_weight":13}]
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
         * logo : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/other/express_company/kuaidi%402x.png
         * name : 普通快递
         * first_weight : 12
         * area : 1
         * renew_weight : 8
         */

        private String logo;
        private String name;
        private float first_weight;
        private int area;
        private float renew_weight;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getFirst_weight() {
            return first_weight;
        }

        public void setFirst_weight(float first_weight) {
            this.first_weight = first_weight;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public float getRenew_weight() {
            return renew_weight;
        }

        public void setRenew_weight(float renew_weight) {
            this.renew_weight = renew_weight;
        }
    }
}
