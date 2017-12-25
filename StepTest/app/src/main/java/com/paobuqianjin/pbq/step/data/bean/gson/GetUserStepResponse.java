package com.paobuqianjin.pbq.step.data.bean.gson;

/**
 * Created by pbq on 2017/12/25.
 */

public class GetUserStepResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":23,"userid":1,"step_number":999999999,"updatetime":1513586717,"creattime":1513580177}
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
         * id : 23
         * userid : 1
         * step_number : 999999999
         * updatetime : 1513586717
         * creattime : 1513580177
         */

        private int id;
        private int userid;
        private int step_number;
        private long updatetime;
        private long creattime;

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

        public long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public long getCreattime() {
            return creattime;
        }

        public void setCreattime(int creattime) {
            this.creattime = creattime;
        }
    }
}
