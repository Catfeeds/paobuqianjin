package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/8.
 */

public class TaskRecDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":1,"task_name":"10000步达标赛","task_days":1,"target_step":10000,"reward_amount":"0.02","userid":57,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/619AB1F6-84F1-4BF0-8502-309BB584BF98.jpg","nickname":"周周周","activity_start_time":1517328000,"activity_end_time":1517414399,"task_rule":"达标即可获得红包奖励，未达标者无奖励","task_desc":"①任务每天晚上12点重置，未达标者的红包退回到派发人账户;\\n②运动数据以跑步钱进的APP为准;","user_step":6000,"is_finished":0}
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
        return "TaskRecDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 1
         * task_name : 10000步达标赛
         * task_days : 1
         * target_step : 10000
         * reward_amount : 0.02
         * userid : 57
         * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/619AB1F6-84F1-4BF0-8502-309BB584BF98.jpg
         * nickname : 周周周
         * activity_start_time : 1517328000
         * activity_end_time : 1517414399
         * task_rule : 达标即可获得红包奖励，未达标者无奖励
         * task_desc : ①任务每天晚上12点重置，未达标者的红包退回到派发人账户;\n②运动数据以跑步钱进的APP为准;
         * user_step : 6000
         * is_finished : 0
         */

        private int id;
        private String task_name;
        private int task_days;
        private int target_step;
        private String reward_amount;
        private int userid;
        private String avatar;
        private String nickname;
        private int activity_start_time;
        private int activity_end_time;
        private String task_rule;
        private String task_desc;
        private int user_step;
        private int is_finished;

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

        public int getTask_days() {
            return task_days;
        }

        public void setTask_days(int task_days) {
            this.task_days = task_days;
        }

        public int getTarget_step() {
            return target_step;
        }

        public void setTarget_step(int target_step) {
            this.target_step = target_step;
        }

        public String getReward_amount() {
            return reward_amount;
        }

        public void setReward_amount(String reward_amount) {
            this.reward_amount = reward_amount;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getActivity_start_time() {
            return activity_start_time;
        }

        public void setActivity_start_time(int activity_start_time) {
            this.activity_start_time = activity_start_time;
        }

        public int getActivity_end_time() {
            return activity_end_time;
        }

        public void setActivity_end_time(int activity_end_time) {
            this.activity_end_time = activity_end_time;
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

        public int getUser_step() {
            return user_step;
        }

        public void setUser_step(int user_step) {
            this.user_step = user_step;
        }

        public int getIs_finished() {
            return is_finished;
        }

        public void setIs_finished(int is_finished) {
            this.is_finished = is_finished;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", task_name='" + task_name + '\'' +
                    ", task_days=" + task_days +
                    ", target_step=" + target_step +
                    ", reward_amount='" + reward_amount + '\'' +
                    ", userid=" + userid +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", activity_start_time=" + activity_start_time +
                    ", activity_end_time=" + activity_end_time +
                    ", task_rule='" + task_rule + '\'' +
                    ", task_desc='" + task_desc + '\'' +
                    ", user_step=" + user_step +
                    ", is_finished=" + is_finished +
                    '}';
        }
    }
}
