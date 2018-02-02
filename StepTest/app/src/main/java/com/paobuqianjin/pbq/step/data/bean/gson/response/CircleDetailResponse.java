package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**圈子详情应答
 * Created by pbq on 2017/12/23.
 * 2018/2/2
 */

public class CircleDetailResponse {

    /**
     * error : 0
     * message : success
     * data : {"id":100110,"userid":1,"name":"一样可以想像","cover":"http://www.paobu.com/img/bg04.png","target":5000,"mobile":"13922895735","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/62529131-E1E3-4B70-AB6A-7C606AC3D8CE.jpg","is_pwd":0,"description":"在线教育家办学的确很","city":"深圳市","longitude":"0.9999999999","latitude":"0.9999999999","total_amount":"1.00","is_recharge":1,"red_packet_amount":"1","red_packet":1,"create_time":1517018768}
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
        return "CircleDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 100110
         * userid : 1
         * name : 一样可以想像
         * cover : http://www.paobu.com/img/bg04.png
         * target : 5000
         * mobile : 13922895735
         * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/62529131-E1E3-4B70-AB6A-7C606AC3D8CE.jpg
         * is_pwd : 0
         * description : 在线教育家办学的确很
         * city : 深圳市
         * longitude : 0.9999999999
         * latitude : 0.9999999999
         * total_amount : 1.00
         * is_recharge : 1
         * red_packet_amount : 1
         * red_packet : 1
         * create_time : 1517018768
         */

        private int id;
        private int userid;
        private String name;
        private String cover;
        private int target;
        private String mobile;
        private String logo;
        private int is_pwd;
        private String description;
        private String city;
        private String longitude;
        private String latitude;
        private String total_amount;
        private int is_recharge;
        private String red_packet_amount;
        private int red_packet;
        private int create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getIs_pwd() {
            return is_pwd;
        }

        public void setIs_pwd(int is_pwd) {
            this.is_pwd = is_pwd;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public int getIs_recharge() {
            return is_recharge;
        }

        public void setIs_recharge(int is_recharge) {
            this.is_recharge = is_recharge;
        }

        public String getRed_packet_amount() {
            return red_packet_amount;
        }

        public void setRed_packet_amount(String red_packet_amount) {
            this.red_packet_amount = red_packet_amount;
        }

        public int getRed_packet() {
            return red_packet;
        }

        public void setRed_packet(int red_packet) {
            this.red_packet = red_packet;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", name='" + name + '\'' +
                    ", cover='" + cover + '\'' +
                    ", target=" + target +
                    ", mobile='" + mobile + '\'' +
                    ", logo='" + logo + '\'' +
                    ", is_pwd=" + is_pwd +
                    ", description='" + description + '\'' +
                    ", city='" + city + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", total_amount='" + total_amount + '\'' +
                    ", is_recharge=" + is_recharge +
                    ", red_packet_amount='" + red_packet_amount + '\'' +
                    ", red_packet=" + red_packet +
                    ", create_time=" + create_time +
                    '}';
        }
    }
}
