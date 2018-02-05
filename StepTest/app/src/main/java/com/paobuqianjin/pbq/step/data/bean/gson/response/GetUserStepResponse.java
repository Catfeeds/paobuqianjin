package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2017/12/25.
 */

public class GetUserStepResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":41,"userid":56,"step_number":700,"update_time":1517824876,"create_time":1517824403}
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
        return "GetUserStepResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 41
         * userid : 56
         * step_number : 700
         * update_time : 1517824876
         * create_time : 1517824403
         */

        private int id;
        private int userid;
        private int step_number;
        private int update_time;
        private int create_time;

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

        public int getStep_number() {
            return step_number;
        }

        public void setStep_number(int step_number) {
            this.step_number = step_number;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", step_number=" + step_number +
                    ", update_time=" + update_time +
                    ", create_time=" + create_time +
                    '}';
        }
    }
}
