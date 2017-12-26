package com.paobuqianjin.pbq.step.data.bean.gson.param;

import java.util.List;

/**
 * Created by pbq on 2017/12/25.
 */

    /*用户登陆记录response
    @className :UserRecordResponse
    *@date 2017/12/25
    *@author
    *@description
    */
public class UserRecordResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":9,"userid":2,"longitude":"86.26464","latitude":"35.17380","logintime":1513341174},{"id":7,"userid":2,"longitude":"86.26464","latitude":"35.17380","logintime":1513337314},{"id":6,"userid":2,"longitude":"86.26464","latitude":"35.17380","logintime":1513330240},{"id":5,"userid":2,"longitude":"86.26464","latitude":"35.17380","logintime":1513330172},{"id":4,"userid":2,"longitude":"86.26464","latitude":"35.17380","logintime":1513308234},{"id":3,"userid":2,"longitude":"86.26464","latitude":"35.17380","logintime":1513307855},{"id":2,"userid":2,"longitude":"86.26001","latitude":"35.17000","logintime":1513245803}]
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
        return "UserRecordResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 9
         * userid : 2
         * longitude : 86.26464
         * latitude : 35.17380
         * logintime : 1513341174
         */

        private int id;
        private int userid;
        private String longitude;
        private String latitude;
        private int logintime;

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

        public int getLogintime() {
            return logintime;
        }

        public void setLogintime(int logintime) {
            this.logintime = logintime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", logintime=" + logintime +
                    '}';
        }
    }
}
