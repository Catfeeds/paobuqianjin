package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/8/1.
 */
/*
@className :MultAccountResponse
*@date 2018/8/1
*@author
*@description 多账户列表
*/
public class MultAccountResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":61,"uuid":"05a3e1cd-5ce5-4f31-a634-d51832e18c28","appid":"05a3e1cd-5ce5-4f31-a634-d51832e18c285b611f48e7915","mtoken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIzNTgyMiIsInN1YiI6InVzZXJfbXVsdGkiLCJhdWIiOiIwNWEzZTFjZC01Y2U1LTRmMzEtYTYzNC1kNTE4MzJlMThjMjgiLCJleHAiOjE1MzU2ODM2NTYsIm5iZiI6MTUzMzA5MTY1NiwiaWF0IjoxNTMzMDkxNjU2LCJqdGkiOiIxNTMzMDkxNjU2NWI2MTFmNDhlNzk2YiJ9.Peh6ypAtbwOkccTIU-lLPwU_OchKXU8wWBkBi2GJwAE","is_login":0,"no":35822,"nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/27/35822201807271509426297.jpg"},{"id":62,"uuid":"05a3e1cd-5ce5-4f31-a634-d51832e18c28","appid":"05a3e1cd-5ce5-4f31-a634-d51832e18c285b611f48e7915","mtoken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOjM1ODIyLCJzdWIiOiJ1c2VyX211bHRpIiwiYXViIjoiMDVhM2UxY2QtNWNlNS00ZjMxLWE2MzQtZDUxODMyZTE4YzI4IiwiZXhwIjoxNTM1NjgzNjU2LCJuYmYiOjE1MzMwOTE2NTYsImlhdCI6MTUzMzA5MTY1NiwianRpIjoiMTUzMzA5MTY1NjViNjExZjQ4ZjE4NzcifQ.bZOg9GQPVUuXmv9QqTg9bCYv8JiMLSg9pjl-VB-EW6U","is_login":1,"no":35822,"nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/27/35822201807271509426297.jpg"}]
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

    @Override
    public String toString() {
        return "MultAccountResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        /**
         * id : 61
         * uuid : 05a3e1cd-5ce5-4f31-a634-d51832e18c28
         * appid : 05a3e1cd-5ce5-4f31-a634-d51832e18c285b611f48e7915
         * mtoken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIzNTgyMiIsInN1YiI6InVzZXJfbXVsdGkiLCJhdWIiOiIwNWEzZTFjZC01Y2U1LTRmMzEtYTYzNC1kNTE4MzJlMThjMjgiLCJleHAiOjE1MzU2ODM2NTYsIm5iZiI6MTUzMzA5MTY1NiwiaWF0IjoxNTMzMDkxNjU2LCJqdGkiOiIxNTMzMDkxNjU2NWI2MTFmNDhlNzk2YiJ9.Peh6ypAtbwOkccTIU-lLPwU_OchKXU8wWBkBi2GJwAE
         * is_login : 0
         * user_id:35822
         * no : 35822
         * nickname : rm_13424156029
         * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/27/35822201807271509426297.jpg
         */

        private int id;
        private String uuid;
        private String appid;
        private String mtoken;
        private int is_login;
        private String no;
        private String nickname;
        private String avatar;


        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        private int user_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMtoken() {
            return mtoken;
        }

        public void setMtoken(String mtoken) {
            this.mtoken = mtoken;
        }

        public int getIs_login() {
            return is_login;
        }

        public void setIs_login(int is_login) {
            this.is_login = is_login;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", uuid='" + uuid + '\'' +
                    ", appid='" + appid + '\'' +
                    ", mtoken='" + mtoken + '\'' +
                    ", is_login=" + is_login +
                    ", no=" + no +
                    ", nickname='" + nickname + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }
    }
}
