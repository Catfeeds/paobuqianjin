package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */

public class UserIdFollowResponse {
    /**
     * error : 0
     * message : success
     * data : {"total_follow":2,"data":[{"id":1,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央"},{"id":2,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央"}]}
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

    public static class DataBeanX {
        /**
         * total_follow : 2
         * data : [{"id":1,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央"},{"id":2,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央"}]
         */

        private int total_follow;
        private List<DataBean> data;

        public int getTotal_follow() {
            return total_follow;
        }

        public void setTotal_follow(int total_follow) {
            this.total_follow = total_follow;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * avatar : http://pic.qqtn.com/up/2017-12/15127898935630842.jpg
             * nickname : 孤央
             */

            private int id;
            private String avatar;
            private String nickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
        }
    }
}
