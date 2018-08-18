package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/6/23.
 */

public class SponsorPkgRecordResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"red_id":1463,"userid":30,"red_name":"好好聚聚","red_content":"","number":10,"money":"5.00","sex":0,"step":1,"day":5,"age_min":29,"age_max":31,"education":0,"distance":2000,"trading_area":"金龙工业城","s_time":1529683200,"e_time":1530115200,"create_time":1529735178,"rlongitude":"113.930740","rlatitude":"22.548870","businessid":1386,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1529136410477.jpg","name":"好吃木桶饭","red_status":1},{"red_id":1164,"userid":30,"red_name":"马家龙","red_content":"","number":5,"money":"10.00","sex":0,"step":1,"day":1,"age_min":28,"age_max":31,"education":0,"distance":3000,"trading_area":"金龙工业城","s_time":1529078400,"e_time":1529164800,"create_time":1529149562,"rlongitude":"113.930740","rlatitude":"22.548870","businessid":1386,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1529136410477.jpg","name":"好吃木桶饭","red_status":2}]}
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
        return "SponsorPkgRecordResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : [{"red_id":1463,"userid":30,"red_name":"好好聚聚","red_content":"","number":10,"money":"5.00","sex":0,"step":1,"day":5,"age_min":29,"age_max":31,"education":0,"distance":2000,"trading_area":"金龙工业城","s_time":1529683200,"e_time":1530115200,"create_time":1529735178,"rlongitude":"113.930740","rlatitude":"22.548870","businessid":1386,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1529136410477.jpg","name":"好吃木桶饭","red_status":1},{"red_id":1164,"userid":30,"red_name":"马家龙","red_content":"","number":5,"money":"10.00","sex":0,"step":1,"day":1,"age_min":28,"age_max":31,"education":0,"distance":3000,"trading_area":"金龙工业城","s_time":1529078400,"e_time":1529164800,"create_time":1529149562,"rlongitude":"113.930740","rlatitude":"22.548870","businessid":1386,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1529136410477.jpg","name":"好吃木桶饭","red_status":2}]
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
             * red_id : 1463
             * userid : 30
             * red_name : 好好聚聚
             * red_content :
             * number : 10
             * money : 5.00
             * sex : 0
             * step : 1
             * day : 5
             * age_min : 29
             * age_max : 31
             * education : 0
             * distance : 2000
             * trading_area : 金龙工业城
             * s_time : 1529683200
             * e_time : 1530115200
             * create_time : 1529735178
             * rlongitude : 113.930740
             * rlatitude : 22.548870
             * businessid : 1386
             * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1529136410477.jpg
             * name : 好吃木桶饭
             * red_status : 1
             */

            private int red_id;
            private int userid;
            private String red_name;
            private String red_content;
            private int number;
            private String money;
            private int sex;
            private int step;
            private int day;
            private int age_min;
            private int age_max;
            private int education;
            private int distance;
            private String trading_area;
            private int s_time;
            private int e_time;
            private int create_time;
            private String rlongitude;
            private String rlatitude;
            private int businessid;
            private String logo;
            private String name;
            private int red_status;

            public int getRed_id() {
                return red_id;
            }

            public void setRed_id(int red_id) {
                this.red_id = red_id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getRed_name() {
                return red_name;
            }

            public void setRed_name(String red_name) {
                this.red_name = red_name;
            }

            public String getRed_content() {
                return red_content;
            }

            public void setRed_content(String red_content) {
                this.red_content = red_content;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getStep() {
                return step;
            }

            public void setStep(int step) {
                this.step = step;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getAge_min() {
                return age_min;
            }

            public void setAge_min(int age_min) {
                this.age_min = age_min;
            }

            public int getAge_max() {
                return age_max;
            }

            public void setAge_max(int age_max) {
                this.age_max = age_max;
            }

            public int getEducation() {
                return education;
            }

            public void setEducation(int education) {
                this.education = education;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getTrading_area() {
                return trading_area;
            }

            public void setTrading_area(String trading_area) {
                this.trading_area = trading_area;
            }

            public int getS_time() {
                return s_time;
            }

            public void setS_time(int s_time) {
                this.s_time = s_time;
            }

            public int getE_time() {
                return e_time;
            }

            public void setE_time(int e_time) {
                this.e_time = e_time;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getRlongitude() {
                return rlongitude;
            }

            public void setRlongitude(String rlongitude) {
                this.rlongitude = rlongitude;
            }

            public String getRlatitude() {
                return rlatitude;
            }

            public void setRlatitude(String rlatitude) {
                this.rlatitude = rlatitude;
            }

            public int getBusinessid() {
                return businessid;
            }

            public void setBusinessid(int businessid) {
                this.businessid = businessid;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRed_status() {
                return red_status;
            }

            public void setRed_status(int red_status) {
                this.red_status = red_status;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "red_id=" + red_id +
                        ", userid=" + userid +
                        ", red_name='" + red_name + '\'' +
                        ", red_content='" + red_content + '\'' +
                        ", number=" + number +
                        ", money='" + money + '\'' +
                        ", sex=" + sex +
                        ", step=" + step +
                        ", day=" + day +
                        ", age_min=" + age_min +
                        ", age_max=" + age_max +
                        ", education=" + education +
                        ", distance=" + distance +
                        ", trading_area='" + trading_area + '\'' +
                        ", s_time=" + s_time +
                        ", e_time=" + e_time +
                        ", create_time=" + create_time +
                        ", rlongitude='" + rlongitude + '\'' +
                        ", rlatitude='" + rlatitude + '\'' +
                        ", businessid=" + businessid +
                        ", logo='" + logo + '\'' +
                        ", name='" + name + '\'' +
                        ", red_status=" + red_status +
                        '}';
            }
        }
    }
}
