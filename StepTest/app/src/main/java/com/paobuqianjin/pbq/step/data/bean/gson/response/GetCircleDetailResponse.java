package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */

public class GetCircleDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"typeid":1,"name":"清风高谊","cover":"http://www.paobu.com/img/bg01.png","target":5000,"mobile":"18588278880","logo":"http://pic.qqtn.com/up/2017-12/2017120912081855932.jpg","tags":[{"id":3,"name":"90后"},{"id":4,"name":"爱运动"},{"id":5,"name":"美少女"}],"description":"圈子文化是文化慨念。在中国，现今流行圈子文化。同学讲同学圈子，朋友讲朋友圈子，战友讲战友圈子,官场讲官场圈子。","city":"深圳市","longitude":"0.0000000000","latitude":"0.0000000000","total_amount":"0.00","creattime":0}
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
         * typeid : 1
         * name : 清风高谊
         * cover : http://www.paobu.com/img/bg01.png
         * target : 5000
         * mobile : 18588278880
         * logo : http://pic.qqtn.com/up/2017-12/2017120912081855932.jpg
         * tags : [{"id":3,"name":"90后"},{"id":4,"name":"爱运动"},{"id":5,"name":"美少女"}]
         * description : 圈子文化是文化慨念。在中国，现今流行圈子文化。同学讲同学圈子，朋友讲朋友圈子，战友讲战友圈子,官场讲官场圈子。
         * city : 深圳市
         * longitude : 0.0000000000
         * latitude : 0.0000000000
         * total_amount : 0.00
         * creattime : 0
         */

        private int typeid;
        private String name;
        private String cover;
        private int target;
        private String mobile;
        private String logo;
        private String description;
        private String city;
        private String longitude;
        private String latitude;
        private String total_amount;
        private int creattime;
        private List<TagsBean> tags;

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
        }
    }
}
