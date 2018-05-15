package com.paobuqianjin.pbq.step.data.bean.gson.response;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by pbq on 2018/3/3.
 */

public class DynamicIdDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":388,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"546v5aKD\n","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/VID_20171004_165316.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/VID_20171004_165318.jpg"],"city":"深圳市","country":"","province":"","county":"","village":"","position":"金龙工业城","vote":0,"comment":0,"create_time":1526287415}
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
        return "DynamicIdDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 388
         * userid : 30
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
         * nickname : 黄钦平
         * dynamic : 546v5aKD
         * <p>
         * images : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/VID_20171004_165316.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/VID_20171004_165318.jpg"]
         * city : 深圳市
         * country :
         * province :
         * county :
         * village :
         * position : 金龙工业城
         * vote : 0
         * comment : 0
         * create_time : 1526287415
         */

        private int id;
        private int userid;
        private String avatar;
        private String nickname;
        private String dynamic;
        private String city;
        private String country;
        private String province;
        private String county;
        private String village;
        private String position;
        private int vote;
        private int comment;
        private int create_time;
        private List<String> images;

        public int getVip() {
            return vip;
        }

        public String getShowAddress() {
            return TextUtils.isEmpty(position) ? city : (city + "·" + position);
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        private int vip;

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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDynamic() {
            return dynamic;
        }

        public void setDynamic(String dynamic) {
            this.dynamic = dynamic;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getVote() {
            return vote;
        }

        public void setVote(int vote) {
            this.vote = vote;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", dynamic='" + dynamic + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", province='" + province + '\'' +
                    ", county='" + county + '\'' +
                    ", village='" + village + '\'' +
                    ", position='" + position + '\'' +
                    ", vote=" + vote +
                    ", comment=" + comment +
                    ", create_time=" + create_time +
                    ", images=" + images +
                    ", vip=" + vip +
                    '}';
        }
    }
}
