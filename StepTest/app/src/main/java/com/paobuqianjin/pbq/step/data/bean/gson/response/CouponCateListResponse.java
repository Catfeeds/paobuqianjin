package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/29.
 */
/*
@className :CouponCateListResponse v1/CoalitionLine/couponCateList  tianmao and taobao jiekou
*@date 2018/11/29
*@author
*@description
*/
public class CouponCateListResponse {
    /**
     * error : 0
     * message : success
     * data : [{"cate_id":1,"cate_name":"女装"},{"cate_id":2,"cate_name":"男装"},{"cate_id":3,"cate_name":"鞋包"},{"cate_id":4,"cate_name":"珠宝配饰"},{"cate_id":5,"cate_name":"运动户外"},{"cate_id":6,"cate_name":"美妆"},{"cate_id":7,"cate_name":"母婴"},{"cate_id":8,"cate_name":"食品"},{"cate_id":9,"cate_name":"内衣"},{"cate_id":10,"cate_name":"数码"},{"cate_id":11,"cate_name":"家装"},{"cate_id":12,"cate_name":"家居用品"},{"cate_id":13,"cate_name":"家电"},{"cate_id":14,"cate_name":"图书音像"},{"cate_id":15,"cate_name":"生活服务"},{"cate_id":16,"cate_name":"汽车"}]
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
         * cate_id : 1
         * cate_name : 女装
         */

        private String cate_id;
        private String cate_name;

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
