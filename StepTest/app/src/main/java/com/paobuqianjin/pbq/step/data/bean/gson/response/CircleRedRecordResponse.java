package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/6/30.
 */

public class CircleRedRecordResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":1},"data":[{"logid":33299,"userid":35822,"circleid":101518,"amount":"200.00","create_time":1533631292,"status":1,"receivetime":1533631883,"nickname":"rm_13424156029","userno":"88888888888"}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":1}
         * data : [{"logid":33299,"userid":35822,"circleid":101518,"amount":"200.00","create_time":1533631292,"status":1,"receivetime":1533631883,"nickname":"rm_13424156029","userno":"88888888888"}]
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
             * pageSize : 10
             * totalPage : 1
             * totalCount : 1
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
            /**
             * logid : 33299
             * userid : 35822
             * circleid : 101518
             * amount : 200.00
             * create_time : 1533631292
             * status : 1
             * receivetime : 1533631883
             * nickname : rm_13424156029
             * userno : 88888888888
             */

            private int logid;
            private int userid;
            private int circleid;
            private String amount;
            private int create_time;
            private int status;
            private int receivetime;
            private String nickname;
            private String userno;

            public int getLogid() {
                return logid;
            }

            public void setLogid(int logid) {
                this.logid = logid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getCircleid() {
                return circleid;
            }

            public void setCircleid(int circleid) {
                this.circleid = circleid;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getReceivetime() {
                return receivetime;
            }

            public void setReceivetime(int receivetime) {
                this.receivetime = receivetime;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUserno() {
                return userno;
            }

            public void setUserno(String userno) {
                this.userno = userno;
            }
        }
    }
}
