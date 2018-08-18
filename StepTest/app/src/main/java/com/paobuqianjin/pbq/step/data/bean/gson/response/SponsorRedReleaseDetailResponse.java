package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/6/28.
 */
/*
@className :SponsorRedReleaseDetailResponse
*@date 2018/6/28
*@author
*@description 商家发布的红包详情
*/
public class SponsorRedReleaseDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"red_id":1180,"userid":23413,"red_name":"珠江国际贸易城","red_content":"","number":5,"money":"5.00","sex":0,"step":1,"day":1,"age_min":0,"age_max":0,"education":0,"distance":20000,"trading_area":"金龙工业城","s_time":1530115200,"e_time":1530201600,"create_time":1530152650,"rlongitude":"113.930740","rlatitude":"22.548870","businessid":1409,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-06-22-12-33-53-705_com.paobuqianjin.pbq.step.png","name":"阿尼玛","surplus":"2.45","explain":"\r\n            <p>\r\n                ①运动数据以跑步钱进的APP为准；\r\n            <\/p>\r\n            <p>\r\n                ②未领取的红包退回到派发人账户；\r\n            <\/p>\r\n        "}
     */

    private int error;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SponsorRedReleaseDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * red_id : 1180
         * userid : 23413
         * red_name : 珠江国际贸易城
         * red_content :
         * number : 5
         * money : 5.00
         * sex : 0
         * step : 1
         * day : 1
         * age_min : 0
         * age_max : 0
         * education : 0
         * distance : 20000
         * trading_area : 金龙工业城
         * s_time : 1530115200
         * e_time : 1530201600
         * create_time : 1530152650
         * rlongitude : 113.930740
         * rlatitude : 22.548870
         * businessid : 1409
         * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-06-22-12-33-53-705_com.paobuqianjin.pbq.step.png
         * name : 阿尼玛
         * surplus : 2.45
         * explain :
         * <p>
         * ①运动数据以跑步钱进的APP为准；
         * </p>
         * <p>
         * ②未领取的红包退回到派发人账户；
         * </p>
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
        private String surplus;
        private String explain;

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

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
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
                    ", surplus='" + surplus + '\'' +
                    ", explain='" + explain + '\'' +
                    '}';
        }
    }
}
