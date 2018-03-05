package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/3.
 */

public class DynamicIdDetailResponse  {
    /**
     * error : 0
     * message : success
     * data : {"id":8,"userid":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":3,"create_time":1513393712}
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
         * id : 8
         * userid : 8
         * avatar : http://pic.qqtn.com/up/2017-12/15127898937460203.jpg
         * nickname : 酒自斟
         * dynamic : 易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！
         * images : ["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"]
         * city : 深圳
         * vote : 100
         * comment : 3
         * create_time : 1513393712
         */

        private int id;
        private int userid;
        private String avatar;
        private String nickname;
        private String dynamic;
        private String city;
        private int vote;
        private int comment;
        private long create_time;
        private List<String> images;

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

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
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
                    ", vote=" + vote +
                    ", comment=" + comment +
                    ", create_time=" + create_time +
                    ", images=" + images +
                    '}';
        }
    }
}
