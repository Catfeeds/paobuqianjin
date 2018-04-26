package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/1/4.
 */

public class MyJoinCircleResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"id":100003,"name":"测试圈子4","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞市","member_number":4},{"id":100000,"name":"烦死了","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞","member_number":20},{"id":100000,"name":"烦死了","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞","member_number":20},{"id":100000,"name":"烦死了","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞","member_number":20}]}
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
        return "MyJoinCircleResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"id":100003,"name":"测试圈子4","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞市","member_number":4},{"id":100000,"name":"烦死了","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞","member_number":20},{"id":100000,"name":"烦死了","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞","member_number":20},{"id":100000,"name":"烦死了","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞","member_number":20}]
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
        }

        public static class DataBean implements Serializable {
            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            /**
             * id : 100003
             * name : 测试圈子4
             * logo : http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg
             * city : 东莞市
             * member_number : 4
             */
            private int vip;
            private int id;
            private String name;
            private String logo;
            private String city;
            private int member_number;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getMember_number() {
                return member_number;
            }

            public void setMember_number(int member_number) {
                this.member_number = member_number;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "vip=" + vip +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        ", logo='" + logo + '\'' +
                        ", city='" + city + '\'' +
                        ", member_number=" + member_number +
                        '}';
            }
        }
    }
}
