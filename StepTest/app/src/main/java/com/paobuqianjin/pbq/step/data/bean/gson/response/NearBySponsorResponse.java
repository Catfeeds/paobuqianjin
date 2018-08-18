package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/6/27.
 */

public class NearBySponsorResponse {

    /**
     * error : 0
     * message : success
     * data : {"userstatus":0,"ledredmoney":"0","ledredpacket":[],"nearedpacket":[{"red_id":1168,"red_name":"梅西百货任务","red_content":"","step":1,"number":5,"money":"50.00","sex":0,"day":1,"age_min":0,"age_max":0,"education":0,"distance":5000,"trading_area":"南山区金龙工业城(大新路北50米)","s_time":1530028800,"e_time":1530115200,"businessid":1408,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_1530068155125.jpg","name":"梅西百货","addra":"广东省/深圳市/南山区","address":"马家龙工业区","tel":"13214253678","rlatitude":"22.549260","rlongitude":"113.930786","status":0,"stastr":"可领取","realdistance":4189},{"red_id":1169,"red_name":"阿尼玛团购","red_content":"","step":10000,"number":5,"money":"5.00","sex":0,"day":1,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"南山区金龙工业城(大新路)","s_time":1530028800,"e_time":1530115200,"businessid":1409,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-06-22-12-33-53-705_com.paobuqianjin.pbq.step.png","name":"阿尼玛","addra":"广东省/深圳市/南山区","address":"腾讯大厦","tel":"13424156029","rlatitude":"22.548727","rlongitude":"113.930720","status":0,"stastr":"可领取","realdistance":4183}],"userstep":13664}
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

    public static class DataBean {
        /**
         * userstatus : 0
         * ledredmoney : 0
         * ledredpacket : [{
         * "issuerid": 23409,
         * "money": "50.00",
         * "actually": "0.23",
         * "is_receive": 1,
         * "is_time": 1530345025,
         * "red_id": 1182,
         * "red_name": "快乐是幸福生活忙碌奔波着生活中度",
         * "red_content": "",
         * "step": 6000,
         * "number": 10,
         * "sex": 0,
         * "day": 25,
         * "age_min": 0,
         * "age_max": 0,
         * "education": 0,
         * "distance": 0,
         * "trading_area": "",
         * "s_time": 1530115200,
         * "e_time": 1532275200,
         * "businessid": 1418,
         * "logo": "http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/1CE035B4-0CB7-4CE4-9290-C2A49B2E1258.jpg",
         * "name": "跑步的时候",
         * "addra": "广东省/深圳市/南山区",
         * "address": "大新路",
         * "tel": "18557558554",
         * "rlatitude": "22.548510",
         * "rlongitude": "113.930900"
         * }]
         * nearedpacket : [{"red_id":1168,"red_name":"梅西百货任务","red_content":"","step":1,"number":5,"money":"50.00","sex":0,"day":1,"age_min":0,"age_max":0,"education":0,"distance":5000,"trading_area":"南山区金龙工业城(大新路北50米)","s_time":1530028800,"e_time":1530115200,"businessid":1408,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_1530068155125.jpg","name":"梅西百货","addra":"广东省/深圳市/南山区","address":"马家龙工业区","tel":"13214253678","rlatitude":"22.549260","rlongitude":"113.930786","status":0,"stastr":"可领取","realdistance":4189},{"red_id":1169,"red_name":"阿尼玛团购","red_content":"","step":10000,"number":5,"money":"5.00","sex":0,"day":1,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"南山区金龙工业城(大新路)","s_time":1530028800,"e_time":1530115200,"businessid":1409,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-06-22-12-33-53-705_com.paobuqianjin.pbq.step.png","name":"阿尼玛","addra":"广东省/深圳市/南山区","address":"腾讯大厦","tel":"13424156029","rlatitude":"22.548727","rlongitude":"113.930720","status":0,"stastr":"可领取","realdistance":4183}]
         * userstep : 13664
         */

        private int userstatus;
        private String ledredmoney;
        private int userstep;
        private List<Ledredpacket> ledredpacket;
        private List<NearedpacketBean> nearedpacket;

        public int getUserstatus() {
            return userstatus;
        }

        public void setUserstatus(int userstatus) {
            this.userstatus = userstatus;
        }

        public String getLedredmoney() {
            return ledredmoney;
        }

        public void setLedredmoney(String ledredmoney) {
            this.ledredmoney = ledredmoney;
        }

        public int getUserstep() {
            return userstep;
        }

        public void setUserstep(int userstep) {
            this.userstep = userstep;
        }

        public List<Ledredpacket> getLedredpacket() {
            return ledredpacket;
        }

        public void setLedredpacket(List<Ledredpacket> ledredpacket) {
            this.ledredpacket = ledredpacket;
        }

        public List<NearedpacketBean> getNearedpacket() {
            return nearedpacket;
        }

        public void setNearedpacket(List<NearedpacketBean> nearedpacket) {
            this.nearedpacket = nearedpacket;
        }

        public static class Ledredpacket {
            /**
             * issuerid : 23409
             * money : 50.00
             * actually : 0.23
             * is_receive : 1
             * is_time : 1530345025
             * red_id : 1182
             * red_name : 快乐是幸福生活忙碌奔波着生活中度
             * red_content :
             * step : 6000
             * number : 10
             * sex : 0
             * day : 25
             * age_min : 0
             * age_max : 0
             * education : 0
             * distance : 0
             * trading_area :
             * s_time : 1530115200
             * e_time : 1532275200
             * businessid : 1418
             * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/1CE035B4-0CB7-4CE4-9290-C2A49B2E1258.jpg
             * name : 跑步的时候
             * addra : 广东省/深圳市/南山区
             * address : 大新路
             * tel : 18557558554
             * rlatitude : 22.548510
             * rlongitude : 113.930900
             */

            private int issuerid;
            private String money;
            private String actually;
            private int is_receive;
            private int is_time;
            private int red_id;
            private String red_name;
            private String red_content;
            private int step;
            private int number;
            private int sex;
            private int day;
            private int age_min;
            private int age_max;
            private int education;
            private int distance;
            private String trading_area;
            private int s_time;
            private int e_time;
            private int businessid;
            private String logo;
            private String name;
            private String addra;
            private String address;
            private String tel;
            private String rlatitude;
            private String rlongitude;

            public int getIssuerid() {
                return issuerid;
            }

            public void setIssuerid(int issuerid) {
                this.issuerid = issuerid;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getActually() {
                return actually;
            }

            public void setActually(String actually) {
                this.actually = actually;
            }

            public int getIs_receive() {
                return is_receive;
            }

            public void setIs_receive(int is_receive) {
                this.is_receive = is_receive;
            }

            public int getIs_time() {
                return is_time;
            }

            public void setIs_time(int is_time) {
                this.is_time = is_time;
            }

            public int getRed_id() {
                return red_id;
            }

            public void setRed_id(int red_id) {
                this.red_id = red_id;
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

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
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

            public String getAddra() {
                return addra;
            }

            public void setAddra(String addra) {
                this.addra = addra;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getRlatitude() {
                return rlatitude;
            }

            public void setRlatitude(String rlatitude) {
                this.rlatitude = rlatitude;
            }

            public String getRlongitude() {
                return rlongitude;
            }

            public void setRlongitude(String rlongitude) {
                this.rlongitude = rlongitude;
            }
        }

        public static class NearedpacketBean {
            /**
             * red_id : 1168
             * red_name : 梅西百货任务
             * red_content :
             * step : 1
             * number : 5
             * money : 50.00
             * sex : 0
             * day : 1
             * age_min : 0
             * age_max : 0
             * education : 0
             * distance : 5000
             * trading_area : 南山区金龙工业城(大新路北50米)
             * s_time : 1530028800
             * e_time : 1530115200
             * businessid : 1408
             * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_1530068155125.jpg
             * name : 梅西百货
             * addra : 广东省/深圳市/南山区
             * address : 马家龙工业区
             * tel : 13214253678
             * rlatitude : 22.549260
             * rlongitude : 113.930786
             * status : 0
             * stastr : 可领取
             * realdistance : 4189
             */

            private int red_id;
            private String red_name;
            private String red_content;
            private int step;
            private int number;
            private String money;
            private int sex;
            private int day;
            private int age_min;
            private int age_max;
            private int education;
            private int distance;
            private String trading_area;
            private int s_time;
            private int e_time;
            private int businessid;
            private String logo;
            private String name;
            private String addra;
            private String address;
            private String tel;
            private String rlatitude;
            private String rlongitude;
            private int status;
            private String stastr;
            private int realdistance;

            public int getUserstep() {
                return userstep;
            }

            public void setUserstep(int userstep) {
                this.userstep = userstep;
            }

            private int userstep;

            public int getRed_id() {
                return red_id;
            }

            public void setRed_id(int red_id) {
                this.red_id = red_id;
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

            public String getAddra() {
                return addra;
            }

            public void setAddra(String addra) {
                this.addra = addra;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getRlatitude() {
                return rlatitude;
            }

            public void setRlatitude(String rlatitude) {
                this.rlatitude = rlatitude;
            }

            public String getRlongitude() {
                return rlongitude;
            }

            public void setRlongitude(String rlongitude) {
                this.rlongitude = rlongitude;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStastr() {
                return stastr;
            }

            public void setStastr(String stastr) {
                this.stastr = stastr;
            }

            public int getRealdistance() {
                return realdistance;
            }

            public void setRealdistance(int realdistance) {
                this.realdistance = realdistance;
            }
        }
    }
}
