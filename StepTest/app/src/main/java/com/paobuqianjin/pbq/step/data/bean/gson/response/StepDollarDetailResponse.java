package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/26.
 */
/*
@className :StepDollarDetailResponse
*@date 2018/2/26
*@author
*@description 积分明细数据
*/
public class StepDollarDetailResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"id":59,"userid":11148,"type":1,"source":11133,"credit":100,"create_time":1526355879,"sname":"OPPO2","bname":"rm_13652354126","create_day":"2018-05-15","typename":"邀请用户奖励"},{"id":61,"userid":11148,"type":1,"source":11171,"credit":100,"create_time":1526364937,"sname":"俊","bname":"rm_13652354126","create_day":"2018-05-15","typename":"邀请用户奖励"}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : [{"id":59,"userid":11148,"type":1,"source":11133,"credit":100,"create_time":1526355879,"sname":"OPPO2","bname":"rm_13652354126","create_day":"2018-05-15","typename":"邀请用户奖励"},{"id":61,"userid":11148,"type":1,"source":11171,"credit":100,"create_time":1526364937,"sname":"俊","bname":"rm_13652354126","create_day":"2018-05-15","typename":"邀请用户奖励"}]
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
             * totalCount : 2
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
             * id : 59
             * userid : 11148
             * type : 1
             * source : 11133
             * credit : 100
             * create_time : 1526355879
             * sname : OPPO2
             * bname : rm_13652354126
             * create_day : 2018-05-15
             * typename : 邀请用户奖励
             */

            private int id;
            private int userid;
            private int type;
            private int source;
            private int credit;
            private int create_time;
            private String sname;
            private String bname;
            private String create_day;
            private String typename;

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getSource() {
                return source;
            }

            public void setSource(int source) {
                this.source = source;
            }

            public int getCredit() {
                return credit;
            }

            public void setCredit(int credit) {
                this.credit = credit;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public String getBname() {
                return bname;
            }

            public void setBname(String bname) {
                this.bname = bname;
            }

            public String getCreate_day() {
                return create_day;
            }

            public void setCreate_day(String create_day) {
                this.create_day = create_day;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", userid=" + userid +
                        ", type=" + type +
                        ", source=" + source +
                        ", credit=" + credit +
                        ", create_time=" + create_time +
                        ", sname='" + sname + '\'' +
                        ", bname='" + bname + '\'' +
                        ", create_day='" + create_day + '\'' +
                        ", typename='" + typename + '\'' +
                        '}';
            }
        }
    }
}
