package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/2.
 */

public class TaskReleaseResponse {

    /**
     * error : 0
     * message : success
     * data : {"taskid":["88"],"task_no":"201803202047165786"}
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
        /**
         * taskid : ["88"]
         * task_no : 201803202047165786
         */

        private String task_no;
        private List<String> taskid;

        public String getTask_no() {
            return task_no;
        }

        public void setTask_no(String task_no) {
            this.task_no = task_no;
        }

        public List<String> getTaskid() {
            return taskid;
        }

        public void setTaskid(List<String> taskid) {
            this.taskid = taskid;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "task_no='" + task_no + '\'' +
                    ", taskid=" + taskid +
                    '}';
        }
    }
}
