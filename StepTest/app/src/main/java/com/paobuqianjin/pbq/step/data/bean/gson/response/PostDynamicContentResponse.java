package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/5.
 */
/*
@className :PostDynamicContentResponse
*@date 2018/3/5
*@author
*@description 发表评论时的返回
*/
public class PostDynamicContentResponse {

    /**
     * error : 0
     * message : success
     * data : {"dynamicid":"132","parent_id":"0","userid":"30","reply_userid":"1","content":"[0x1f620]","create_time":1524204018,"id":"369","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平"}
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
        return "PostDynamicContentResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * dynamicid : 132
         * parent_id : 0
         * userid : 30
         * reply_userid : 1
         * content : [0x1f620]
         * create_time : 1524204018
         * id : 369
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
         * nickname : 黄钦平
         */

        private String dynamicid;
        private String parent_id;
        private String userid;
        private String reply_userid;
        private String content;
        private int create_time;
        private String id;
        private String avatar;
        private String nickname;

        public String getReply_nickname() {
            return reply_nickname;
        }

        public void setReply_nickname(String reply_nickname) {
            this.reply_nickname = reply_nickname;
        }

        private String reply_nickname;

        public String getDynamicid() {
            return dynamicid;
        }

        public void setDynamicid(String dynamicid) {
            this.dynamicid = dynamicid;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getReply_userid() {
            return reply_userid;
        }

        public void setReply_userid(String reply_userid) {
            this.reply_userid = reply_userid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "dynamicid='" + dynamicid + '\'' +
                    ", parent_id='" + parent_id + '\'' +
                    ", userid='" + userid + '\'' +
                    ", reply_userid='" + reply_userid + '\'' +
                    ", content='" + content + '\'' +
                    ", create_time=" + create_time +
                    ", id='" + id + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", reply_nickname='" + reply_nickname + '\'' +
                    '}';
        }
    }
}
