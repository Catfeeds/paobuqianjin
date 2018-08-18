package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/*
@className :Adresponse
*@date 2018/7/20
*@author
*@description
*/
public class Adresponse {
    /**
     * error : 0
     * message : success
     * data : [{"rid":"0","title":"默认广告图","target_url":"","imgs":[{"img_id":0,"img_url":"https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/circle_create.jpg","is_main":1}]}]
     */

    private int error;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rid : 0
         * title : 默认广告图
         * target_url :
         * imgs : [{"img_id":0,"img_url":"https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/circle_create.jpg","is_main":1}]
         */

        private String rid;
        private String title;
        private String target_url;
        private List<ImgsBean> imgs;

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTarget_url() {
            return target_url;
        }

        public void setTarget_url(String target_url) {
            this.target_url = target_url;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * img_id : 0
             * img_url : https://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/img/circle_create.jpg
             * is_main : 1
             */

            private int img_id;
            private String img_url;
            private int is_main;

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public int getIs_main() {
                return is_main;
            }

            public void setIs_main(int is_main) {
                this.is_main = is_main;
            }
        }
    }
}
