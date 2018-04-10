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
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":20},"data":[{"id":1,"amount":"0.50","name":"圈子红包","create_time":1517818067},{"id":2,"amount":"0.80","name":"圈子红包","create_time":1517818067},{"id":3,"amount":"0.15","name":"圈子红包","create_time":1517818067},{"id":4,"amount":"0.28","name":"圈子红包","create_time":1517818067},{"id":5,"amount":"1.28","name":"圈子红包","create_time":1517818067},{"id":6,"amount":"6.66","name":"圈子红包","create_time":1517673605},{"id":7,"amount":"8.88","name":"邀请红包","create_time":1517673605},{"id":8,"amount":"8.88","name":"圈子红包","create_time":1517673605},{"id":9,"amount":"99.99","name":"邀请红包","create_time":1517673605},{"id":58,"amount":"1.59","name":"商户广告","create_time":1515394608},{"id":73,"amount":"4.55","name":"商户广告","create_time":1515480276},{"id":74,"amount":"4.55","name":"商户广告","create_time":1515480744},{"id":75,"amount":"4.55","name":"商户广告","create_time":1515481027},{"id":77,"amount":"5.76","name":"商户广告","create_time":1515482311},{"id":80,"amount":"4.55","name":"商户广告","create_time":1515482693},{"id":81,"amount":"4.55","name":"商户广告","create_time":1515482740},{"id":82,"amount":"7.45","name":"商户广告","create_time":1515737786},{"id":83,"amount":"22.29","name":"商户广告","create_time":0},{"id":85,"amount":"14.94","name":"商户广告","create_time":0},{"id":86,"amount":"2.07","name":"商户广告","create_time":0}],"total_amount":204.27}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":20}
         * data : [{"id":1,"amount":"0.50","name":"圈子红包","create_time":1517818067},{"id":2,"amount":"0.80","name":"圈子红包","create_time":1517818067},{"id":3,"amount":"0.15","name":"圈子红包","create_time":1517818067},{"id":4,"amount":"0.28","name":"圈子红包","create_time":1517818067},{"id":5,"amount":"1.28","name":"圈子红包","create_time":1517818067},{"id":6,"amount":"6.66","name":"圈子红包","create_time":1517673605},{"id":7,"amount":"8.88","name":"邀请红包","create_time":1517673605},{"id":8,"amount":"8.88","name":"圈子红包","create_time":1517673605},{"id":9,"amount":"99.99","name":"邀请红包","create_time":1517673605},{"id":58,"amount":"1.59","name":"商户广告","create_time":1515394608},{"id":73,"amount":"4.55","name":"商户广告","create_time":1515480276},{"id":74,"amount":"4.55","name":"商户广告","create_time":1515480744},{"id":75,"amount":"4.55","name":"商户广告","create_time":1515481027},{"id":77,"amount":"5.76","name":"商户广告","create_time":1515482311},{"id":80,"amount":"4.55","name":"商户广告","create_time":1515482693},{"id":81,"amount":"4.55","name":"商户广告","create_time":1515482740},{"id":82,"amount":"7.45","name":"商户广告","create_time":1515737786},{"id":83,"amount":"22.29","name":"商户广告","create_time":0},{"id":85,"amount":"14.94","name":"商户广告","create_time":0},{"id":86,"amount":"2.07","name":"商户广告","create_time":0}]
         * total_amount : 204.27
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
             * totalCount : 20
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
             * id : 1
             * amount : 0.50
             * name : 圈子红包
             * create_time : 1517818067
             */

            private int id;
            private String amount;
            private String name;
            private int create_time;

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

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", amount='" + amount + '\'' +
                        ", name='" + name + '\'' +
                        ", create_time=" + create_time +
                        '}';
            }
        }
    }
}
