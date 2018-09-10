package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/9/8.
 */
/*
@className :NearByRedResponse
*@date 2018/9/8
*@author
*@description 附近商家红包详情
*/
public class NearByRedResponse {
    /**
     * error : 0
     * message : success
     * data : {"red_id":1493,"userid":35876,"red_name":"图图乐乐","red_content":"","number":10,"money":"50.00","sex":0,"step":1,"day":50,"age_min":0,"age_max":0,"education":0,"distance":0,"trading_area":"","s_time":1533484800,"e_time":1537804800,"create_time":1533543710,"target_url":"","rlongitude":"113.930750","rlatitude":"22.548560","businessid":1616,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","name":"跑步回来","tel":"13682385800","addra":"广东省/深圳市/福田区","address":"车公庙太空旅客哭唧唧卡里没钱了拒绝屠龙记录考虑兔兔扣扣据了解兔兔看看","longitude":"114.054540","latitude":"22.522910","scope":"","red_img":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg"],"is_receive":1,"business_img":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/B8910D85-4DA1-4692-BA71-ED13DDEBC3C2.jpg"],"income_money":"0.06","receiver_list":[{"actually":"0.17","userid":35876,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png"},{"actually":"0.04","userid":35876,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png"},{"actually":"0.06","userid":35822,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg"},{"actually":"0.05","userid":35875,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/25/35875201808251431128180.jpg"},{"actually":"0.06","userid":35821,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/23/35821201808231407665212.jpg"},{"actually":"0.08","userid":35881,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png"}]}
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
         * red_id : 1493
         * userid : 35876
         * red_name : 图图乐乐
         * red_content :
         * number : 10
         * money : 50.00
         * sex : 0
         * step : 1
         * day : 50
         * age_min : 0
         * age_max : 0
         * education : 0
         * distance : 0
         * trading_area :
         * s_time : 1533484800
         * e_time : 1537804800
         * create_time : 1533543710
         * target_url :
         * rlongitude : 113.930750
         * rlatitude : 22.548560
         * businessid : 1616
         * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg
         * name : 跑步回来
         * tel : 13682385800
         * addra : 广东省/深圳市/福田区
         * address : 车公庙太空旅客哭唧唧卡里没钱了拒绝屠龙记录考虑兔兔扣扣据了解兔兔看看
         * longitude : 114.054540
         * latitude : 22.522910
         * scope :
         * red_img : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg"]
         * is_receive : 1
         * business_img : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/B8910D85-4DA1-4692-BA71-ED13DDEBC3C2.jpg"]
         * income_money : 0.06
         * receiver_list : [{"actually":"0.17","userid":35876,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png"},{"actually":"0.04","userid":35876,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png"},{"actually":"0.06","userid":35822,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg"},{"actually":"0.05","userid":35875,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/25/35875201808251431128180.jpg"},{"actually":"0.06","userid":35821,"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/23/35821201808231407665212.jpg"},{"actually":"0.08","userid":35881,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png"}]
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
        private String target_url;
        private String rlongitude;
        private String rlatitude;
        private int businessid;
        private String logo;
        private String name;
        private String tel;
        private String addra;
        private String address;
        private String longitude;
        private String latitude;
        private String scope;
        private int is_receive;
        private String income_money;
        private List<String> red_img;
        private List<String> business_img;
        private List<ReceiverListBean> receiver_list;

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

        public String getTarget_url() {
            return target_url;
        }

        public void setTarget_url(String target_url) {
            this.target_url = target_url;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
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

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public int getIs_receive() {
            return is_receive;
        }

        public void setIs_receive(int is_receive) {
            this.is_receive = is_receive;
        }

        public String getIncome_money() {
            return income_money;
        }

        public void setIncome_money(String income_money) {
            this.income_money = income_money;
        }

        public List<String> getRed_img() {
            return red_img;
        }

        public void setRed_img(List<String> red_img) {
            this.red_img = red_img;
        }

        public List<String> getBusiness_img() {
            return business_img;
        }

        public void setBusiness_img(List<String> business_img) {
            this.business_img = business_img;
        }

        public List<ReceiverListBean> getReceiver_list() {
            return receiver_list;
        }

        public void setReceiver_list(List<ReceiverListBean> receiver_list) {
            this.receiver_list = receiver_list;
        }

        public static class ReceiverListBean {
            /**
             * actually : 0.17
             * userid : 35876
             * avatar : https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png
             */

            private String actually;
            private int userid;
            private String avatar;

            public String getActually() {
                return actually;
            }

            public void setActually(String actually) {
                this.actually = actually;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
