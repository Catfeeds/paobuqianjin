package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class MyHotCircleResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":4,"totalCount":38},"data":[{"id":100382,"name":"一回国","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-04-18-10-20-16-985_com.paobuqianjin.pbq.step.png","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":40000,"member_number":2,"is_red_time":0},{"id":100380,"name":"好几块","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180326_230829_HDR.jpg","city":"深圳市","is_recharge":1,"total_amount":"1.00","red_packet_amount":"0.10","target":5000,"member_number":1,"is_red_packet":0,"is_red_time":0},{"id":100377,"name":"红包圈子监控","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/03BB8424-F74A-45DC-AA0A-71F4F678C9D6.jpg","city":"深圳市","is_recharge":1,"total_amount":"0.01","red_packet_amount":"0.01","target":5000,"member_number":2,"is_red_packet":0,"is_red_time":0},{"id":100376,"name":"uhjj","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180326_230829_HDR.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":5000,"member_number":1,"is_red_time":0},{"id":100375,"name":"jjjjj","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180326_230829_HDR.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":5000,"member_number":1,"is_red_time":0},{"id":100374,"name":"jkk","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180326_230829_HDR.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":5000,"member_number":1,"is_red_time":0},{"id":100372,"name":"圈子a","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":5000,"member_number":1,"is_red_time":0},{"id":100371,"name":"2233","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","city":"深圳市","is_recharge":1,"total_amount":"1.00","red_packet_amount":"0.20","target":8000,"member_number":1,"is_red_packet":0,"is_red_time":0},{"id":100370,"name":"测试红苦痛噜啦啦啦啦啦","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/091C8B29-0AF0-4C4F-AC20-825103A2831E.jpg","city":"深圳市","is_recharge":1,"total_amount":"2.13","red_packet_amount":"0.10","target":5000,"member_number":3,"is_red_packet":0,"is_red_time":0},{"id":100366,"name":"立刻就会","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/973CAF20-D19E-4B9E-ABE4-173EA035E653.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":10000,"member_number":2,"is_red_time":0}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":4,"totalCount":38}
         * data : [{"id":100382,"name":"一回国","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-04-18-10-20-16-985_com.paobuqianjin.pbq.step.png","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":40000,"member_number":2,"is_red_time":0},{"id":100380,"name":"好几块","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180326_230829_HDR.jpg","city":"深圳市","is_recharge":1,"total_amount":"1.00","red_packet_amount":"0.10","target":5000,"member_number":1,"is_red_packet":0,"is_red_time":0},{"id":100377,"name":"红包圈子监控","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/03BB8424-F74A-45DC-AA0A-71F4F678C9D6.jpg","city":"深圳市","is_recharge":1,"total_amount":"0.01","red_packet_amount":"0.01","target":5000,"member_number":2,"is_red_packet":0,"is_red_time":0},{"id":100376,"name":"uhjj","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180326_230829_HDR.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":5000,"member_number":1,"is_red_time":0},{"id":100375,"name":"jjjjj","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180326_230829_HDR.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":5000,"member_number":1,"is_red_time":0},{"id":100374,"name":"jkk","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180326_230829_HDR.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":5000,"member_number":1,"is_red_time":0},{"id":100372,"name":"圈子a","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":5000,"member_number":1,"is_red_time":0},{"id":100371,"name":"2233","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","city":"深圳市","is_recharge":1,"total_amount":"1.00","red_packet_amount":"0.20","target":8000,"member_number":1,"is_red_packet":0,"is_red_time":0},{"id":100370,"name":"测试红苦痛噜啦啦啦啦啦","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/091C8B29-0AF0-4C4F-AC20-825103A2831E.jpg","city":"深圳市","is_recharge":1,"total_amount":"2.13","red_packet_amount":"0.10","target":5000,"member_number":3,"is_red_packet":0,"is_red_time":0},{"id":100366,"name":"立刻就会","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/973CAF20-D19E-4B9E-ABE4-173EA035E653.jpg","city":"深圳市","is_recharge":0,"total_amount":"0.00","red_packet_amount":"0.00","target":10000,"member_number":2,"is_red_time":0}]
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
             * totalPage : 4
             * totalCount : 38
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
             * id : 100382
             * name : 一回国
             * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-04-18-10-20-16-985_com.paobuqianjin.pbq.step.png
             * city : 深圳市
             * is_recharge : 0
             * total_amount : 0.00
             * red_packet_amount : 0.00
             * target : 40000
             * member_number : 2
             * is_red_time : 0
             * is_red_packet : 0
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
            private int is_red_time;
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

            public int getIs_red_time() {
                return is_red_time;
            }

            public void setIs_red_time(int is_red_time) {
                this.is_red_time = is_red_time;
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
                        ", is_red_time=" + is_red_time +
                        ", is_red_packet=" + is_red_packet +
                        '}';
            }
        }
    }
}
