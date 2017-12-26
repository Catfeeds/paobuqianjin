package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2017/12/26.
 */

public class AboutTypeDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":2,"title":"联系","content":"400-870-1207","creattime":1252458745}
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
         * id : 2
         * title : 联系
         * content : 400-870-1207
         * creattime : 1252458745
         */

        private int id;
        private String title;
        private String content;
        private int creattime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCreattime() {
            return creattime;
        }

        public void setCreattime(int creattime) {
            this.creattime = creattime;
        }
    }
}
