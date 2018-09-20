package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/1/3.
 */
//我创建的圈子API返回类  http://119.29.10.64/v1/Circle/?action=create&userid=1
public class MyCreateCircleResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":14},"data":[{"id":100000,"name":"烦死了","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞","is_pwd":1,"member_number":20,"order_no":"wx0000000000000000","is_recharge":1},{"id":100001,"name":"测试圈子2","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"深圳市","is_pwd":0,"member_number":5,"is_recharge":0},{"id":100012,"name":"Test圈","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100014,"name":"Test圈圈","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100016,"name":"Test圈圈圈","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100019,"name":"测试不同","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100020,"name":"测试不同之处","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100027,"name":"测试不同之处啊","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100028,"name":"Hello World","logo":"ddddddddd","city":"深圳","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100030,"name":"Hello","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"深圳","is_pwd":1,"member_number":1,"is_recharge":0}]}
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
        return "MyCreateCircleResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":14}
         * data : [{"id":100000,"name":"烦死了","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"东莞","is_pwd":1,"member_number":20,"order_no":"wx0000000000000000","is_recharge":1},{"id":100001,"name":"测试圈子2","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"深圳市","is_pwd":0,"member_number":5,"is_recharge":0},{"id":100012,"name":"Test圈","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100014,"name":"Test圈圈","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100016,"name":"Test圈圈圈","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100019,"name":"测试不同","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100020,"name":"测试不同之处","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100027,"name":"测试不同之处啊","logo":"http://pic.qqtn.com/up/2017-12/2017120912081824953.jpg","city":"广州","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100028,"name":"Hello World","logo":"ddddddddd","city":"深圳","is_pwd":1,"member_number":1,"is_recharge":0},{"id":100030,"name":"Hello","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","city":"深圳","is_pwd":1,"member_number":1,"is_recharge":0}]
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
             * totalCount : 14
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
            /**
             * id : 100000
             * name : 烦死了
             * logo : http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg
             * city : 东莞
             * is_pwd : 1
             * member_number : 20
             * order_no : wx0000000000000000
             * is_recharge : 1
             * can_select 0
             */

            private int id;
            private String name;
            private String logo;
            private String city;
            private int is_pwd;
            private int member_number;
            private String order_no;
            private int is_recharge;

            public boolean isCan_select() {
                return can_select;
            }

            public void setCan_select(boolean can_select) {
                this.can_select = can_select;
            }

            private boolean can_select;

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

            public int getIs_pwd() {
                return is_pwd;
            }

            public void setIs_pwd(int is_pwd) {
                this.is_pwd = is_pwd;
            }

            public int getMember_number() {
                return member_number;
            }

            public void setMember_number(int member_number) {
                this.member_number = member_number;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public int getIs_recharge() {
                return is_recharge;
            }

            public void setIs_recharge(int is_recharge) {
                this.is_recharge = is_recharge;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", logo='" + logo + '\'' +
                        ", city='" + city + '\'' +
                        ", is_pwd=" + is_pwd +
                        ", member_number=" + member_number +
                        ", order_no='" + order_no + '\'' +
                        ", is_recharge=" + is_recharge +
                        '}';
            }
        }
    }
}
