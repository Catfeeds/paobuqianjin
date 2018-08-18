package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/2/28.
 */

public class UserDanResponse {

    /**
     * error : 0
     * message : success
     * data : {"id":1,"level":"第一段","target":20000,"end":69999,"total_step_number":31528,"number":27,"beat":"84.38"}
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
         * id : 1
         * level : 第一段
         * target : 20000
         * end : 69999
         * total_step_number : 31528
         * number : 27
         * beat : 84.38
         */

        private int id;
        private String level;
        private long target;
        private long end;
        private long total_step_number;
        private int number;
        private float beat;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public long getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public long getTotal_step_number() {
            return total_step_number;
        }

        public void setTotal_step_number(long total_step_number) {
            this.total_step_number = total_step_number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public float getBeat() {
            return beat;
        }

        public void setBeat(float beat) {
            this.beat = beat;
        }
    }
}
