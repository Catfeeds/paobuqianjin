package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/2/2.
 */

public class TaskReleaseResponse {
    /**
     * error : 0
     * message : success
     * data : {"taskid":"10"}
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
        return "TaskReleaseResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "taskid='" + taskid + '\'' +
                    '}';
        }

        /**
         * taskid : 10
         */

        private String taskid;

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }
    }
}
