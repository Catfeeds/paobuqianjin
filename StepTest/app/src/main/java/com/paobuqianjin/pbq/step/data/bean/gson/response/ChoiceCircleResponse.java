package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class ChoiceCircleResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"circleid":100000,"name":"清风高谊","city":"深圳市","is_pwd":1,"logo":"http://pic.qqtn.com/up/2017-12/2017120912081855932.jpg","total_step":"1049240","member_number":18,"is_join":1},{"circleid":100001,"name":"测试圈子2","city":"深圳市","is_pwd":0,"logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","total_step":"11421","member_number":3,"is_join":0},{"circleid":100003,"name":"测试圈子4","city":"东莞市","is_pwd":1,"logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","total_step":"9794","member_number":1,"is_join":0},{"circleid":100002,"name":"测试圈子3","city":"东莞市","is_pwd":0,"logo":"http://pic.qqtn.com/up/2017-12/2017120912081840574.jpg","total_step":"9591","member_number":1,"is_join":0}]}
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
        return "ChoiceCircleResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"circleid":100000,"name":"清风高谊","city":"深圳市","is_pwd":1,"logo":"http://pic.qqtn.com/up/2017-12/2017120912081855932.jpg","total_step":"1049240","member_number":18,"is_join":1},{"circleid":100001,"name":"测试圈子2","city":"深圳市","is_pwd":0,"logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","total_step":"11421","member_number":3,"is_join":0},{"circleid":100003,"name":"测试圈子4","city":"东莞市","is_pwd":1,"logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","total_step":"9794","member_number":1,"is_join":0},{"circleid":100002,"name":"测试圈子3","city":"东莞市","is_pwd":0,"logo":"http://pic.qqtn.com/up/2017-12/2017120912081840574.jpg","total_step":"9591","member_number":1,"is_join":0}]
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
             * circleid : 100000
             * name : 清风高谊
             * city : 深圳市
             * is_pwd : 1
             * logo : http://pic.qqtn.com/up/2017-12/2017120912081855932.jpg
             * total_step : 1049240
             * member_number : 18
             * is_join : 1
             */

            private int circleid;
            private String name;
            private String city;
            private int is_pwd;
            private String logo;
            private String total_step;
            private int member_number;
            private int is_join;

            public int getCircleid() {
                return circleid;
            }

            public void setCircleid(int circleid) {
                this.circleid = circleid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getIs_pwd() {
                return is_pwd;
            }

            public void setIs_pwd(int is_pwd) {
                this.is_pwd = is_pwd;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getTotal_step() {
                return total_step;
            }

            public void setTotal_step(String total_step) {
                this.total_step = total_step;
            }

            public int getMember_number() {
                return member_number;
            }

            public void setMember_number(int member_number) {
                this.member_number = member_number;
            }

            public int getIs_join() {
                return is_join;
            }

            public void setIs_join(int is_join) {
                this.is_join = is_join;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "circleid=" + circleid +
                        ", name='" + name + '\'' +
                        ", city='" + city + '\'' +
                        ", is_pwd=" + is_pwd +
                        ", logo='" + logo + '\'' +
                        ", total_step='" + total_step + '\'' +
                        ", member_number=" + member_number +
                        ", is_join=" + is_join +
                        '}';
            }
        }
    }
}
