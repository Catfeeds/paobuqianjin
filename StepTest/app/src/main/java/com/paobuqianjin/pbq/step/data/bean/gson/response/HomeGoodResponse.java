package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/14.
 */
/*
@className :HomeGoodResponse  首页特选商品
*@date 2018/11/14
*@author
*@description
*/
public class HomeGoodResponse {
    /**
     * error : 0
     * message : success
     * data : {"goods_list":[{"goods_id":1,"goods_name":"简单测试的商品名称","promotion_type":0,"goods_type":1,"market_price":"8.00","promotion_price":"8.00","point_exchange":60,"shipping_fee":"0.00","sales":121,"collects":0,"star":0,"evaluates":0,"picture":1,"pic_url":"http://www.runmoney.shop/upload/goods/kele.jpg","target_url":"http://www.runmoney.shop/wap/goods/goodsdetail?id=1"},{"goods_id":2,"goods_name":"一元测试商品","promotion_type":0,"goods_type":1,"price":"1.00","promotion_price":"1.00","point_exchange":10,"shipping_fee":"0.00","sales":31,"collects":0,"star":0,"evaluates":0,"picture":2,"pic_url":"http://www.runmoney.shop/upload/goods/46d8d27c4cd4c6865f62443ce7a9d1f83.jpeg","target_url":"http://www.runmoney.shop/wap/goods/goodsdetail?id=2"}],"shop_url":"http://www.runmoney.shop/"}
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
         * goods_list : [{"goods_id":1,"goods_name":"简单测试的商品名称","promotion_type":0,"goods_type":1,"price":"8.00","promotion_price":"8.00","point_exchange":60,"shipping_fee":"0.00","sales":121,"collects":0,"star":0,"evaluates":0,"picture":1,"pic_url":"http://www.runmoney.shop/upload/goods/kele.jpg","target_url":"http://www.runmoney.shop/wap/goods/goodsdetail?id=1"},{"goods_id":2,"goods_name":"一元测试商品","promotion_type":0,"goods_type":1,"price":"1.00","promotion_price":"1.00","point_exchange":10,"shipping_fee":"0.00","sales":31,"collects":0,"star":0,"evaluates":0,"picture":2,"pic_url":"http://www.runmoney.shop/upload/goods/46d8d27c4cd4c6865f62443ce7a9d1f83.jpeg","target_url":"http://www.runmoney.shop/wap/goods/goodsdetail?id=2"}]
         * shop_url : http://www.runmoney.shop/
         */

        private String shop_url;
        private List<GoodsListBean> goods_list;

        public String getShop_url() {
            return shop_url;
        }

        public void setShop_url(String shop_url) {
            this.shop_url = shop_url;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 1
             * goods_name : 简单测试的商品名称
             * promotion_type : 0
             * goods_type : 1
             * market_price : 8.00
             * promotion_price : 8.00
             * point_exchange : 60
             * shipping_fee : 0.00
             * sales : 121
             * collects : 0
             * star : 0
             * evaluates : 0
             * picture : 1
             * pic_url : http://www.runmoney.shop/upload/goods/kele.jpg
             * target_url : http://www.runmoney.shop/wap/goods/goodsdetail?id=1
             */

            private String goods_id;
            private String goods_name;
            private String promotion_type;
            private int goods_type;
            private String market_price;
            private String promotion_price;
            private String point_exchange;
            private String shipping_fee;
            private int sales;
            private int collects;
            private int star;
            private int evaluates;
            private int picture;
            private String pic_url;
            private String target_url;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getPromotion_type() {
                return promotion_type;
            }

            public void setPromotion_type(String promotion_type) {
                this.promotion_type = promotion_type;
            }

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String price) {
                this.market_price = price;
            }

            public String getPromotion_price() {
                return promotion_price;
            }

            public void setPromotion_price(String promotion_price) {
                this.promotion_price = promotion_price;
            }

            public String getPoint_exchange() {
                return point_exchange;
            }

            public void setPoint_exchange(String point_exchange) {
                this.point_exchange = point_exchange;
            }

            public String getShipping_fee() {
                return shipping_fee;
            }

            public void setShipping_fee(String shipping_fee) {
                this.shipping_fee = shipping_fee;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public int getCollects() {
                return collects;
            }

            public void setCollects(int collects) {
                this.collects = collects;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public int getEvaluates() {
                return evaluates;
            }

            public void setEvaluates(int evaluates) {
                this.evaluates = evaluates;
            }

            public int getPicture() {
                return picture;
            }

            public void setPicture(int picture) {
                this.picture = picture;
            }

            public String getPic_url() {
                return pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }
        }
    }
}
