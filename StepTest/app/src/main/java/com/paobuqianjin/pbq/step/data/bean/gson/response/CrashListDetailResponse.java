package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/10.
 */
/*
@className :CrashListDetailResponse
*@date 2018/3/10
*@author
*@description 提现详情
*/
public class CrashListDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"id":1,"userid":1,"typeid":1,"amount":"2000.00","actual_amount":"1980","status":0,"create_time":1513755628},{"id":31,"userid":1,"typeid":2,"amount":"240.00","actual_amount":"237.6","status":0,"create_time":1514186667},{"id":32,"userid":1,"typeid":1,"amount":"24.00","actual_amount":"23.76","status":0,"create_time":1514186628},{"id":35,"userid":1,"typeid":1,"amount":"1.00","actual_amount":"1","status":0,"create_time":1517816173}]}
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
        return "CrashListDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"id":1,"userid":1,"typeid":1,"amount":"2000.00","actual_amount":"1980","status":0,"create_time":1513755628},{"id":31,"userid":1,"typeid":2,"amount":"240.00","actual_amount":"237.6","status":0,"create_time":1514186667},{"id":32,"userid":1,"typeid":1,"amount":"24.00","actual_amount":"23.76","status":0,"create_time":1514186628},{"id":35,"userid":1,"typeid":1,"amount":"1.00","actual_amount":"1","status":0,"create_time":1517816173}]
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

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "pagenation=" + pagenation +
                    ", data=" + data +
                    '}';
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 10
             * totalPage : 1
             * totalCount : 4
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
             * userid : 1
             * typeid : 1
             * amount : 2000.00
             * actual_amount : 1980
             * status : 0
             * create_time : 1513755628
             * type_name :提现到微信
             */

            private int id;
            private int userid;
            private int typeid;
            private String amount;
            private String actual_amount;
            private int status;
            private long create_time;

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            private String type_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getActual_amount() {
                return actual_amount;
            }

            public void setActual_amount(String actual_amount) {
                this.actual_amount = actual_amount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", userid=" + userid +
                        ", typeid=" + typeid +
                        ", amount='" + amount + '\'' +
                        ", actual_amount='" + actual_amount + '\'' +
                        ", status=" + status +
                        ", create_time=" + create_time +
                        ", type_name='" + type_name + '\'' +
                        '}';
            }
        }
    }
}
