package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */
/*
@className :AllIncomeResponse
*@date 2018/2/6
*@author
*@description 总收益
*/
public class AllIncomeResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":17},"data":[{"id":237,"amount":"0.16","name":"圈子红包","create_time":1522811299,"typeid":1,"source_id":100360,"circleid":100360,"circlename":"一样一样咿呀咿呀哟以后呀呀呀"},{"id":236,"amount":"1.00","name":"圈子红包","create_time":1522810702,"typeid":1,"source_id":100361,"circleid":100361,"circlename":"一样一样咿呀咿呀哟以后呀呀呀前"},{"id":234,"amount":"1.00","name":"圈子红包","create_time":1522297949,"typeid":1,"source_id":100355,"circleid":100355,"circlename":"钱包支付"},{"id":233,"amount":"1.00","name":"圈子红包","create_time":1522294271,"typeid":1,"source_id":100361,"circleid":100361,"circlename":"一样一样咿呀咿呀哟以后呀呀呀前"},{"id":232,"amount":"0.12","name":"圈子红包","create_time":1522226267,"typeid":1,"source_id":100360,"circleid":100360,"circlename":"一样一样咿呀咿呀哟以后呀呀呀"},{"id":231,"amount":"0.10","name":"圈子红包","create_time":1522225515,"typeid":1,"source_id":100356,"circleid":100356,"circlename":"1毛"},{"id":230,"amount":"1.00","name":"圈子红包","create_time":1522225485,"typeid":1,"source_id":100357,"circleid":100357,"circlename":" 钱包"},{"id":229,"amount":"1.00","name":"圈子红包","create_time":1522225474,"typeid":1,"source_id":100358,"circleid":100358,"circlename":"更好"},{"id":222,"amount":"0.01","name":"任务退款","create_time":1521799207,"typeid":6,"source_id":545},{"id":211,"amount":"1.00","name":"任务退款","create_time":1521799207,"typeid":6,"source_id":361}],"total_amount":7.63}
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

    @Override
    public String toString() {
        return "AllIncomeResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":17}
         * data : [{"id":237,"amount":"0.16","name":"圈子红包","create_time":1522811299,"typeid":1,"source_id":100360,"circleid":100360,"circlename":"一样一样咿呀咿呀哟以后呀呀呀"},{"id":236,"amount":"1.00","name":"圈子红包","create_time":1522810702,"typeid":1,"source_id":100361,"circleid":100361,"circlename":"一样一样咿呀咿呀哟以后呀呀呀前"},{"id":234,"amount":"1.00","name":"圈子红包","create_time":1522297949,"typeid":1,"source_id":100355,"circleid":100355,"circlename":"钱包支付"},{"id":233,"amount":"1.00","name":"圈子红包","create_time":1522294271,"typeid":1,"source_id":100361,"circleid":100361,"circlename":"一样一样咿呀咿呀哟以后呀呀呀前"},{"id":232,"amount":"0.12","name":"圈子红包","create_time":1522226267,"typeid":1,"source_id":100360,"circleid":100360,"circlename":"一样一样咿呀咿呀哟以后呀呀呀"},{"id":231,"amount":"0.10","name":"圈子红包","create_time":1522225515,"typeid":1,"source_id":100356,"circleid":100356,"circlename":"1毛"},{"id":230,"amount":"1.00","name":"圈子红包","create_time":1522225485,"typeid":1,"source_id":100357,"circleid":100357,"circlename":" 钱包"},{"id":229,"amount":"1.00","name":"圈子红包","create_time":1522225474,"typeid":1,"source_id":100358,"circleid":100358,"circlename":"更好"},{"id":222,"amount":"0.01","name":"任务退款","create_time":1521799207,"typeid":6,"source_id":545},{"id":211,"amount":"1.00","name":"任务退款","create_time":1521799207,"typeid":6,"source_id":361}]
         * total_amount : 7.63
         */

        private PagenationBean pagenation;
        private double total_amount;
        private List<DataBean> data;

        public PagenationBean getPagenation() {
            return pagenation;
        }

        public void setPagenation(PagenationBean pagenation) {
            this.pagenation = pagenation;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(double total_amount) {
            this.total_amount = total_amount;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "pagenation=" + pagenation +
                    ", total_amount=" + total_amount +
                    ", data=" + data +
                    '}';
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 10
             * totalPage : 2
             * totalCount : 17
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

            @Override
            public String toString() {
                return "PagenationBean{" +
                        "page=" + page +
                        ", pageSize=" + pageSize +
                        ", totalPage=" + totalPage +
                        ", totalCount=" + totalCount +
                        '}';
            }
        }

        public static class DataBean {
            /**
             * id : 237
             * amount : 0.16
             * name : 圈子红包
             * create_time : 1522811299
             * typeid : 1
             * source_id : 100360
             * circleid : 100360
             * circlename : 一样一样咿呀咿呀哟以后呀呀呀
             * businessid:1454
             */

            private int id;
            private String amount;
            private String name;
            private int create_time;
            private int typeid;
            private int source_id;
            private int circleid;
            private String circlename;

            public String getBusinessid() {
                return businessid;
            }

            public void setBusinessid(String businessid) {
                this.businessid = businessid;
            }

            private String businessid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public int getSource_id() {
                return source_id;
            }

            public void setSource_id(int source_id) {
                this.source_id = source_id;
            }

            public int getCircleid() {
                return circleid;
            }

            public void setCircleid(int circleid) {
                this.circleid = circleid;
            }

            public String getCirclename() {
                return circlename;
            }

            public void setCirclename(String circlename) {
                this.circlename = circlename;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", amount='" + amount + '\'' +
                        ", name='" + name + '\'' +
                        ", create_time=" + create_time +
                        ", typeid=" + typeid +
                        ", source_id=" + source_id +
                        ", circleid=" + circleid +
                        ", circlename='" + circlename + '\'' +
                        '}';
            }
        }
    }
}
