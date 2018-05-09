package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/5/9.
 */

public class CurrentStepResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":676,"userid":30,"step_number":2147,"update_time":1525846874,"create_day":"2018-05-09","create_time":1525830119,"updatetime":"2018-05-09 14:21:14"}
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
        return "CurrentStepResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 676
         * userid : 30
         * step_number : 2147
         * update_time : 1525846874
         * create_day : 2018-05-09
         * create_time : 1525830119
         * updatetime : 2018-05-09 14:21:14
         */

        private int id;
        private int userid;
        private int step_number;
        private int update_time;
        private String create_day;
        private int create_time;
        private String updatetime;

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

        public String getCreate_day() {
            return create_day;
        }

        public void setCreate_day(String create_day) {
            this.create_day = create_day;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", userid=" + userid +
                    ", step_number=" + step_number +
                    ", update_time=" + update_time +
                    ", create_day='" + create_day + '\'' +
                    ", create_time=" + create_time +
                    ", updatetime='" + updatetime + '\'' +
                    '}';
        }
    }
}
