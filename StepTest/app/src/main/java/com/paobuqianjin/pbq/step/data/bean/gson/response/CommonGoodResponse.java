package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/12/12.
 */
//全步币兑换商品
public class CommonGoodResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":25,"totalPage":1,"totalCount":5},"data":[{"goods_id":7,"goods_name":"华为HRD强烈推荐的800份表格","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":999,"market_price":"98.00","shipping_fee":"0.00","sales":4,"collects":0,"star":0,"evaluates":0,"picture":34,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/05e7fc8f403fb5f69258e18406041dce3.jpg"},{"goods_id":8,"goods_name":"阿里铁军内训资料绝版公开","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":999,"market_price":"98.00","shipping_fee":"0.00","sales":16,"collects":0,"star":0,"evaluates":0,"picture":35,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/58b0dd972f0fcc3a72928593e6fedfa83.jpg"},{"goods_id":116,"goods_name":"价值上万的思维导图软件+教程+模板","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":999,"market_price":"98.00","shipping_fee":"0.00","sales":0,"collects":0,"star":0,"evaluates":0,"picture":612,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/0fdb9d9c37d70acfcd70573c9f5ec10c3.jpg"},{"goods_id":117,"goods_name":"法国原瓶进口 御鹿（HINE）洋酒 希世干邑白兰地700ml","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":98000,"market_price":"468.00","shipping_fee":"1.00","sales":2,"collects":0,"star":0,"evaluates":0,"picture":613,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/5bde788484d0c7e1cd610309aa3225c83.jpg"},{"goods_id":122,"goods_name":"汰渍洗衣液200g*2香型随机去渍无残留","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":2999,"market_price":"29.00","shipping_fee":"1.00","sales":1,"collects":0,"star":0,"evaluates":0,"picture":636,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/aa19c6fd0002f8264f1ff98bbf6bf4603.jpg"}]}
     */

    private int error;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":25,"totalPage":1,"totalCount":5}
         * data : [{"goods_id":7,"goods_name":"华为HRD强烈推荐的800份表格","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":999,"market_price":"98.00","shipping_fee":"0.00","sales":4,"collects":0,"star":0,"evaluates":0,"picture":34,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/05e7fc8f403fb5f69258e18406041dce3.jpg"},{"goods_id":8,"goods_name":"阿里铁军内训资料绝版公开","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":999,"market_price":"98.00","shipping_fee":"0.00","sales":16,"collects":0,"star":0,"evaluates":0,"picture":35,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/58b0dd972f0fcc3a72928593e6fedfa83.jpg"},{"goods_id":116,"goods_name":"价值上万的思维导图软件+教程+模板","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":999,"market_price":"98.00","shipping_fee":"0.00","sales":0,"collects":0,"star":0,"evaluates":0,"picture":612,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/0fdb9d9c37d70acfcd70573c9f5ec10c3.jpg"},{"goods_id":117,"goods_name":"法国原瓶进口 御鹿（HINE）洋酒 希世干邑白兰地700ml","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":98000,"market_price":"468.00","shipping_fee":"1.00","sales":2,"collects":0,"star":0,"evaluates":0,"picture":613,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/5bde788484d0c7e1cd610309aa3225c83.jpg"},{"goods_id":122,"goods_name":"汰渍洗衣液200g*2香型随机去渍无残留","promotion_type":0,"goods_type":1,"price":"0.00","promotion_price":"0.00","point_exchange":2999,"market_price":"29.00","shipping_fee":"1.00","sales":1,"collects":0,"star":0,"evaluates":0,"picture":636,"pic_url":"http://api-test1.runmoneyin.com/upload/goods/aa19c6fd0002f8264f1ff98bbf6bf4603.jpg"}]
         */

        private PagenationBean pagenation;
        private List<DataBean> data;

        public PagenationBean getPagenation() {
            return pagenation;
        }

        public void setPagenation(PagenationBean pagenation) {
            this.pagenation = pagenation;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 25
             * totalPage : 1
             * totalCount : 5
             */

            private int page;
            private int pageSize;
            private int totalPage;
            private int totalCount;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }
        }

        public static class DataBean {
            public String getTarget_url() {
                return target_url;
            }

            /**
             * goods_id : 7
             * goods_name : 华为HRD强烈推荐的800份表格
             * promotion_type : 0
             * goods_type : 1
             * price : 0.00
             * promotion_price : 0.00
             * point_exchange : 999
             * market_price : 98.00
             * shipping_fee : 0.00
             * sales : 4
             * collects : 0
             * star : 0
             * evaluates : 0
             * picture : 34
             * pic_url : http://api-test1.runmoneyin.com/upload/goods/05e7fc8f403fb5f69258e18406041dce3.jpg
             * target_url: http:\/\/shop.runmoneyin.com\/\/wap\/goods\/goodsdetail?id=7
             */

            private String target_url;
            private int goods_id;
            private String goods_name;
            private int promotion_type;
            private int goods_type;
            private String price;
            private String promotion_price;
            private String point_exchange;
            private String market_price;
            private String shipping_fee;
            private int sales;
            private int collects;
            private int star;
            private int evaluates;
            private int picture;
            private String pic_url;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public int getPromotion_type() {
                return promotion_type;
            }

            public void setPromotion_type(int promotion_type) {
                this.promotion_type = promotion_type;
            }

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
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
        }
    }
}
