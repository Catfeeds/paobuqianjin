package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/24.
 */

public class DanListResponse {

    /**
     * error : 0
     * message : success
     * data : [{"id":1,"level":"第一段","target":20000,"end":69999,"number":9},{"id":2,"level":"第二段","target":70000,"end":119999,"number":3},{"id":3,"level":"第三段","target":120000,"end":219999,"number":2},{"id":4,"level":"第四段","target":220000,"end":419999,"number":0},{"id":5,"level":"第五段","target":420000,"end":719999,"number":0},{"id":6,"level":"第六段","target":720000,"end":1119999,"number":0},{"id":7,"level":"第七段","target":1120000,"end":1619999,"number":0},{"id":8,"level":"第八段","target":1620000,"end":2219999,"number":0},{"id":9,"level":"第九段","target":2220000,"end":"4294967295","number":0}]
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

    public static class DataBean {
        /**
         * id : 1
         * level : 第一段
         * target : 20000
         * end : 69999
         * number : 9
         */

        private int id;
        private String level;
        private long target;
        private long end;
        private int number;

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        private boolean finished;
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

        public void setTarget(long target) {
            this.target = target;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
