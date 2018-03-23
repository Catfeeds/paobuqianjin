package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/23.
 */
/*
@className :LoginRecordResponse
*@date 2018/3/23
*@author
*@description 用户登录记录
*/
public class LoginRecordResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":12,"userid":2,"longitude":"22.00000","latitude":"114.00000","create_time":1514170727,"delete_time":null},{"id":9,"userid":2,"longitude":"86.26464","latitude":"35.17380","create_time":1513341174,"delete_time":null},{"id":7,"userid":2,"longitude":"86.26464","latitude":"35.17380","create_time":1513337314,"delete_time":null},{"id":6,"userid":2,"longitude":"86.26464","latitude":"35.17380","create_time":1513330240,"delete_time":null},{"id":5,"userid":2,"longitude":"86.26464","latitude":"35.17380","create_time":1513330172,"delete_time":null},{"id":4,"userid":2,"longitude":"86.26464","latitude":"35.17380","create_time":1513308234,"delete_time":null},{"id":3,"userid":2,"longitude":"86.26464","latitude":"35.17380","create_time":1513307855,"delete_time":null},{"id":2,"userid":2,"longitude":"86.26001","latitude":"35.17000","create_time":1513245803,"delete_time":null}]
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
        return "LoginRecordResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 12
         * userid : 2
         * longitude : 22.00000
         * latitude : 114.00000
         * create_time : 1514170727
         * delete_time : null
         */

        private int id;
        private int userid;
        private String longitude;
        private String latitude;
        private int create_time;
        private Object delete_time;

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

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public Object getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(Object delete_time) {
            this.delete_time = delete_time;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", create_time=" + create_time +
                    ", delete_time=" + delete_time +
                    '}';
        }
    }
}
