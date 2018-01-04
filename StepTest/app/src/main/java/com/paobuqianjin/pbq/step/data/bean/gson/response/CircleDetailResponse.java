package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**圈子详情应答
 * Created by pbq on 2017/12/23.
 */

public class CircleDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"userid":1,"id":100000,"typeid":2,"name":"烦死了","cover":"http://www.paobu.com/img/bg01.png","target":40000,"mobile":"18276810054","logo":"http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg","is_pwd":1,"password":"123456","tags":[{"id":3,"name":"90后"},{"id":4,"name":"爱运动"},{"id":5,"name":"美少女"}],"description":"表表表表表表","city":"东莞","longitude":"0.9999999999","latitude":"0.9999999999","total_amount":"562.82","red_packet_amount":"100","red_packet":10,"creattime":1514601774}
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
         * userid : 1
         * id : 100000
         * typeid : 2
         * name : 烦死了
         * cover : http://www.paobu.com/img/bg01.png
         * target : 40000
         * mobile : 18276810054
         * logo : http://pic.qqtn.com/up/2017-12/2017120912081833498.jpg
         * is_pwd : 1
         * password : 123456
         * tags : [{"id":3,"name":"90后"},{"id":4,"name":"爱运动"},{"id":5,"name":"美少女"}]
         * description : 表表表表表表
         * city : 东莞
         * longitude : 0.9999999999
         * latitude : 0.9999999999
         * total_amount : 562.82
         * red_packet_amount : 100
         * red_packet : 10
         * creattime : 1514601774
         */

        private int userid;
        private int id;
        private int typeid;
        private String name;
        private String cover;
        private int target;
        private String mobile;
        private String logo;
        private int is_pwd;
        private String password;
        private String description;
        private String city;
        private String longitude;
        private String latitude;
        private String total_amount;
        private String red_packet_amount;
        private int red_packet;
        private int creattime;
        private List<TagsBean> tags;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public int getCreattime() {
            return creattime;
        }

        public void setCreattime(int creattime) {
            this.creattime = creattime;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "userid=" + userid +
                    ", id=" + id +
                    ", typeid=" + typeid +
                    ", name='" + name + '\'' +
                    ", cover='" + cover + '\'' +
                    ", target=" + target +
                    ", mobile='" + mobile + '\'' +
                    ", logo='" + logo + '\'' +
                    ", is_pwd=" + is_pwd +
                    ", password='" + password + '\'' +
                    ", description='" + description + '\'' +
                    ", city='" + city + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", total_amount='" + total_amount + '\'' +
                    ", red_packet_amount='" + red_packet_amount + '\'' +
                    ", red_packet=" + red_packet +
                    ", creattime=" + creattime +
                    ", tags=" + tags +
                    '}';
        }

        public static class TagsBean {
            /**
             * id : 3
             * name : 90后
             */

            private int id;
            private String name;

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

            @Override
            public String toString() {
                return "TagsBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }
        }
    }
}
