package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/2/28.
 */

public class UserDanResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":3,"level":"第三段","target":120000,"total_step_number":55151,"number":52,"beat":88}
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
        return "UserDanResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 3
         * level : 第三段
         * target : 120000
         * total_step_number : 55151
         * number : 52
         * beat : 88
         */

        private int id;
        private String level;
        private int target;
        private int total_step_number;
        private int number;
        private int beat;

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

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }

        public int getTotal_step_number() {
            return total_step_number;
        }

        public void setTotal_step_number(int total_step_number) {
            this.total_step_number = total_step_number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getBeat() {
            return beat;
        }

        public void setBeat(int beat) {
            this.beat = beat;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", level='" + level + '\'' +
                    ", target=" + target +
                    ", total_step_number=" + total_step_number +
                    ", number=" + number +
                    ", beat=" + beat +
                    '}';
        }
    }
}
