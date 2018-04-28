package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/4/28.
 */
/*
@className :RecRedPkgResponse
*@date 2018/4/28
*@author
*@description  领取红包结果
*/
public class RecRedPkgResponse {
    /**
     * error : 0
     * message : success
     * data : {"result":[{"red_id":29,"businessid":26,"red_name":"跑步钱进","red_content":"","step":0,"number":5,"money":"0.20","sex":0,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":null,"e_time":null,"longitude":"0.000000","latitude":"0.000000","realdistance":12453880,"is_redpacket":1,"ms_redpacket":"红包数量不足","is_statue":1},{"red_id":30,"businessid":27,"red_name":"跑步钱进","red_content":"","step":0,"number":5,"money":"1.00","sex":0,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":null,"e_time":null,"longitude":"0.000000","latitude":"0.000000","realdistance":12453880,"is_redpacket":1,"ms_redpacket":"红包数量不足","is_statue":1},{"red_id":33,"businessid":30,"red_name":"你定","red_content":"","step":0,"number":2,"money":"64.00","sex":0,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":null,"e_time":null,"longitude":"0.000000","latitude":"0.000000","realdistance":12453880,"is_redpacket":1,"ms_redpacket":"红包数量不足","is_statue":1},{"red_id":34,"businessid":31,"red_name":"你定","red_content":"","step":0,"number":7,"money":"3.00","sex":0,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":null,"e_time":null,"longitude":"0.000000","latitude":"0.000000","realdistance":12453880,"is_redpacket":1,"ms_redpacket":"红包数量不足","is_statue":1}],"allmoney":0}
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
        return "RecRedPkgResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * result : [{"red_id":29,"businessid":26,"red_name":"跑步钱进","red_content":"","step":0,"number":5,"money":"0.20","sex":0,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":null,"e_time":null,"longitude":"0.000000","latitude":"0.000000","realdistance":12453880,"is_redpacket":1,"ms_redpacket":"红包数量不足","is_statue":1},{"red_id":30,"businessid":27,"red_name":"跑步钱进","red_content":"","step":0,"number":5,"money":"1.00","sex":0,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":null,"e_time":null,"longitude":"0.000000","latitude":"0.000000","realdistance":12453880,"is_redpacket":1,"ms_redpacket":"红包数量不足","is_statue":1},{"red_id":33,"businessid":30,"red_name":"你定","red_content":"","step":0,"number":2,"money":"64.00","sex":0,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":null,"e_time":null,"longitude":"0.000000","latitude":"0.000000","realdistance":12453880,"is_redpacket":1,"ms_redpacket":"红包数量不足","is_statue":1},{"red_id":34,"businessid":31,"red_name":"你定","red_content":"","step":0,"number":7,"money":"3.00","sex":0,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":null,"e_time":null,"longitude":"0.000000","latitude":"0.000000","realdistance":12453880,"is_redpacket":1,"ms_redpacket":"红包数量不足","is_statue":1}]
         * allmoney : 0
         */

        private float allmoney;
        private List<ResultBean> result;

        public float getAllmoney() {
            return allmoney;
        }

        public void setAllmoney(float allmoney) {
            this.allmoney = allmoney;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "allmoney=" + allmoney +
                    ", result=" + result +
                    '}';
        }

        public static class ResultBean {
            /**
             * red_id : 29
             * businessid : 26
             * red_name : 跑步钱进
             * red_content :
             * step : 0
             * number : 5
             * money : 0.20
             * sex : 0
             * age_min : 0
             * age_max : 0
             * education : 0
             * distance : 0
             * trading_area :
             * s_time : null
             * e_time : null
             * longitude : 0.000000
             * latitude : 0.000000
             * realdistance : 12453880
             * is_redpacket : 1
             * ms_redpacket : 红包数量不足
             * is_statue : 1
             */

            private int red_id;
            private int businessid;
            private String red_name;
            private String red_content;
            private int step;
            private int number;
            private String money;
            private int sex;
            private int age_min;
            private int age_max;
            private int education;
            private int distance;
            private String trading_area;
            private Object s_time;
            private Object e_time;
            private String longitude;
            private String latitude;
            private int realdistance;
            private int is_redpacket;
            private String ms_redpacket;
            private int is_statue;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            private String name;
            public int getRed_id() {
                return red_id;
            }

            public void setRed_id(int red_id) {
                this.red_id = red_id;
            }

            public int getBusinessid() {
                return businessid;
            }

            public void setBusinessid(int businessid) {
                this.businessid = businessid;
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

            public int getStep() {
                return step;
            }

            public void setStep(int step) {
                this.step = step;
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

            public Object getS_time() {
                return s_time;
            }

            public void setS_time(Object s_time) {
                this.s_time = s_time;
            }

            public Object getE_time() {
                return e_time;
            }

            public void setE_time(Object e_time) {
                this.e_time = e_time;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public int getRealdistance() {
                return realdistance;
            }

            public void setRealdistance(int realdistance) {
                this.realdistance = realdistance;
            }

            public int getIs_redpacket() {
                return is_redpacket;
            }

            public void setIs_redpacket(int is_redpacket) {
                this.is_redpacket = is_redpacket;
            }

            public String getMs_redpacket() {
                return ms_redpacket;
            }

            public void setMs_redpacket(String ms_redpacket) {
                this.ms_redpacket = ms_redpacket;
            }

            public int getIs_statue() {
                return is_statue;
            }

            public void setIs_statue(int is_statue) {
                this.is_statue = is_statue;
            }

            @Override
            public String toString() {
                return "ResultBean{" +
                        "red_id=" + red_id +
                        ", businessid=" + businessid +
                        ", red_name='" + red_name + '\'' +
                        ", red_content='" + red_content + '\'' +
                        ", step=" + step +
                        ", number=" + number +
                        ", money='" + money + '\'' +
                        ", sex=" + sex +
                        ", age_min=" + age_min +
                        ", age_max=" + age_max +
                        ", education=" + education +
                        ", distance=" + distance +
                        ", trading_area='" + trading_area + '\'' +
                        ", s_time=" + s_time +
                        ", e_time=" + e_time +
                        ", longitude='" + longitude + '\'' +
                        ", latitude='" + latitude + '\'' +
                        ", realdistance=" + realdistance +
                        ", is_redpacket=" + is_redpacket +
                        ", ms_redpacket='" + ms_redpacket + '\'' +
                        ", is_statue=" + is_statue +
                        ", name='" + name + '\'' +
                        '}';
            }
        }
    }
}
