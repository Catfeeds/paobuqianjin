package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/26.
 */
/*
@className :StepDollarDetailResponse
*@date 2018/2/26
*@author
*@description 步币明细数据
*/
public class StepDollarDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":11},"data":[{"id":2,"userid":1,"source":"注册奖励","credit":"10.00","creat_time":1515809369},{"id":3,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":4,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":5,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":6,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":7,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":8,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":9,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":10,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":11,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":12,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369}]}
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
        return "StepDollarDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":11}
         * data : [{"id":2,"userid":1,"source":"注册奖励","credit":"10.00","creat_time":1515809369},{"id":3,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":4,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":5,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":6,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":7,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":8,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":9,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":10,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":11,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369},{"id":12,"userid":1,"source":"邀请好友","credit":"5.00","creat_time":1515809369}]
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
             * totalPage : 2
             * totalCount : 11
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
             * id : 2
             * userid : 1
             * source : 注册奖励
             * credit : 10.00
             * creat_time : 1515809369
             */

            private int id;
            private int userid;
            private String source;
            private String credit;
            private long creat_time;

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

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getCredit() {
                return credit;
            }

            public void setCredit(String credit) {
                this.credit = credit;
            }

            public long getCreat_time() {
                return creat_time;
            }

            public void setCreat_time(int creat_time) {
                this.creat_time = creat_time;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", userid=" + userid +
                        ", source='" + source + '\'' +
                        ", credit='" + credit + '\'' +
                        ", creat_time=" + creat_time +
                        '}';
            }
        }
    }
}
