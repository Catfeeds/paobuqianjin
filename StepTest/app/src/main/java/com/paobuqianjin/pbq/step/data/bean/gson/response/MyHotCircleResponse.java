package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class MyHotCircleResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"id":100330,"name":"发红包","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","city":"深圳福田","is_recharge":1,"total_amount":"1.00","red_packet_amount":"0.20","target":5000,"member_number":2,"is_red_packet":1},{"id":100306,"name":"哈哈","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","city":"深圳福田","is_recharge":0,"total_amount":"0.01","red_packet_amount":"0.00","target":5000,"member_number":3},{"id":100305,"name":"到下面玩跑步","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171002_161235.jpg","city":"深圳福田","is_recharge":1,"total_amount":"0.01","red_packet_amount":"0.10","target":5000,"member_number":1,"is_red_packet":0},{"id":100304,"name":"藕勒","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171002_161235.jpg","city":"深圳福田","is_recharge":0,"total_amount":"0.02","red_packet_amount":"0.00","target":5000,"member_number":1}]}
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
        return "MyHotCircleResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"id":100330,"name":"发红包","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","city":"深圳福田","is_recharge":1,"total_amount":"1.00","red_packet_amount":"0.20","target":5000,"member_number":2,"is_red_packet":1},{"id":100306,"name":"哈哈","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","city":"深圳福田","is_recharge":0,"total_amount":"0.01","red_packet_amount":"0.00","target":5000,"member_number":3},{"id":100305,"name":"到下面玩跑步","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171002_161235.jpg","city":"深圳福田","is_recharge":1,"total_amount":"0.01","red_packet_amount":"0.10","target":5000,"member_number":1,"is_red_packet":0},{"id":100304,"name":"藕勒","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171002_161235.jpg","city":"深圳福田","is_recharge":0,"total_amount":"0.02","red_packet_amount":"0.00","target":5000,"member_number":1}]
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
             * id : 100330
             * name : 发红包
             * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg
             * city : 深圳福田
             * is_recharge : 1
             * total_amount : 1.00
             * red_packet_amount : 0.20
             * target : 5000
             * member_number : 2
             * is_red_packet : 1
             */

            private int id;
            private String name;
            private String logo;
            private String city;
            private int is_recharge;
            private String total_amount;
            private String red_packet_amount;
            private int target;
            private int member_number;
            private int is_red_packet;

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

            public int getIs_recharge() {
                return is_recharge;
            }

            public void setIs_recharge(int is_recharge) {
                this.is_recharge = is_recharge;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getRed_packet_amount() {
                return red_packet_amount;
            }

            public void setRed_packet_amount(String red_packet_amount) {
                this.red_packet_amount = red_packet_amount;
            }

            public int getTarget() {
                return target;
            }

            public void setTarget(int target) {
                this.target = target;
            }

            public int getMember_number() {
                return member_number;
            }

            public void setMember_number(int member_number) {
                this.member_number = member_number;
            }

            public int getIs_red_packet() {
                return is_red_packet;
            }

            public void setIs_red_packet(int is_red_packet) {
                this.is_red_packet = is_red_packet;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", logo='" + logo + '\'' +
                        ", city='" + city + '\'' +
                        ", is_recharge=" + is_recharge +
                        ", total_amount='" + total_amount + '\'' +
                        ", red_packet_amount='" + red_packet_amount + '\'' +
                        ", target=" + target +
                        ", member_number=" + member_number +
                        ", is_red_packet=" + is_red_packet +
                        '}';
            }
        }
    }
}
