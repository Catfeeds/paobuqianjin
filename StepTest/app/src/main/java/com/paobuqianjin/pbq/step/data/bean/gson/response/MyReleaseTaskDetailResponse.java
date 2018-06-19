package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/28.
 */

public class MyReleaseTaskDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"task":{"id":9,"task_name":"8000步达标赛","target_step":8000,"reward_amount":"10.00","avgmoney":"10.00","task_days":10,"task_rule":"达标即可获得红包奖励，未达标者无奖励","task_desc":"①任务每天晚上12点重置，未达标者的红包退回到派发人账户;\\n②运动数据以跑步钱进的APP为准;"},"task_record":[{"activity_start_time":1517500800,"is_receive":0},{"activity_start_time":1517587200,"is_receive":0},{"activity_start_time":1517673600,"is_receive":0},{"activity_start_time":1517760000,"is_receive":0},{"activity_start_time":1517846400,"is_receive":0},{"activity_start_time":1517932800,"is_receive":0},{"activity_start_time":1518019200,"is_receive":0},{"activity_start_time":1518105600,"is_receive":0},{"activity_start_time":1518192000,"is_receive":0},{"activity_start_time":1518278400,"is_receive":0}]}
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
        return "MyReleaseTaskDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * task : {"id":9,"task_name":"8000步达标赛","target_step":8000,"reward_amount":"10.00","avgmoney":"10.00","task_days":10,"task_rule":"达标即可获得红包奖励，未达标者无奖励","task_desc":"①任务每天晚上12点重置，未达标者的红包退回到派发人账户;\\n②运动数据以跑步钱进的APP为准;"}
         * task_record : [{"activity_start_time":1517500800,"is_receive":0},{"activity_start_time":1517587200,"is_receive":0},{"activity_start_time":1517673600,"is_receive":0},{"activity_start_time":1517760000,"is_receive":0},{"activity_start_time":1517846400,"is_receive":0},{"activity_start_time":1517932800,"is_receive":0},{"activity_start_time":1518019200,"is_receive":0},{"activity_start_time":1518105600,"is_receive":0},{"activity_start_time":1518192000,"is_receive":0},{"activity_start_time":1518278400,"is_receive":0}]
         */

        private TaskBean task;
        private List<TaskRecordBean> task_record;

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public List<TaskRecordBean> getTask_record() {
            return task_record;
        }

        public void setTask_record(List<TaskRecordBean> task_record) {
            this.task_record = task_record;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "task=" + task +
                    ", task_record=" + task_record +
                    '}';
        }

        public static class TaskBean {
            /**
             * id : 9
             * task_name : 8000步达标赛
             * target_step : 8000
             * reward_amount : 10.00
             * avgmoney :10.00
             * task_days : 10
             * task_rule : 达标即可获得红包奖励，未达标者无奖励
             * task_desc : ①任务每天晚上12点重置，未达标者的红包退回到派发人账户;\n②运动数据以跑步钱进的APP为准;
             */

            private int id;
            private String task_name;
            private int target_step;
            private float reward_amount;

            public float getAvgmoney() {
                return avgmoney;
            }

            public void setAvgmoney(float avgmoney) {
                this.avgmoney = avgmoney;
            }

            private float avgmoney;
            private float allmoney;
            private int task_days;
            private String task_rule;
            private String task_desc;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTask_name() {
                return task_name;
            }

            public void setTask_name(String task_name) {
                this.task_name = task_name;
            }

            public int getTarget_step() {
                return target_step;
            }

            public void setTarget_step(int target_step) {
                this.target_step = target_step;
            }

            public float getReward_amount() {
                return reward_amount;
            }

            public void setReward_amount(float reward_amount) {
                this.reward_amount = reward_amount;
            }

            public int getTask_days() {
                return task_days;
            }

            public void setTask_days(int task_days) {
                this.task_days = task_days;
            }

            public String getTask_rule() {
                return task_rule;
            }

            public void setTask_rule(String task_rule) {
                this.task_rule = task_rule;
            }

            public String getTask_desc() {
                return task_desc;
            }

            public void setTask_desc(String task_desc) {
                this.task_desc = task_desc;
            }

            public float getAllmoney() {
                return allmoney;
            }

            public void setAllmoney(float allmoney) {
                this.allmoney = allmoney;
            }

            @Override
            public String toString() {
                return "TaskBean{" +
                        "id=" + id +
                        ", task_name='" + task_name + '\'' +
                        ", target_step=" + target_step +
                        ", reward_amount=" + reward_amount +
                        ", avgmoney=" + avgmoney +
                        ", allmoney=" + allmoney +
                        ", task_days=" + task_days +
                        ", task_rule='" + task_rule + '\'' +
                        ", task_desc='" + task_desc + '\'' +
                        '}';
            }
        }

        public static class TaskRecordBean {
            /**
             * activity_start_time : 1517500800
             * is_receive : 0
             */

            private long activity_start_time;
            private int is_receive;

            public long getActivity_start_time() {
                return activity_start_time;
            }

            public void setActivity_start_time(long activity_start_time) {
                this.activity_start_time = activity_start_time;
            }

            public int getIs_receive() {
                return is_receive;
            }

            public void setIs_receive(int is_receive) {
                this.is_receive = is_receive;
            }

            @Override
            public String toString() {
                return "TaskRecordBean{" +
                        "activity_start_time=" + activity_start_time +
                        ", is_receive=" + is_receive +
                        '}';
            }
        }
    }
}
