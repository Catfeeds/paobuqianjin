package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/8.
 */

public class TaskRecDetailResponse {

    /**
     * error : 0
     * message : success
     * data : {"id":770,"no":770,"task_name":"3820步达标赛","task_days":31,"target_step":3820,"reward_amount":"100.00","userid":193,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/YXr2VhxJpdg2dcIZMrju42mmGXmLabwpbTiaoKq9lP5WbdkhcTkkZ6pjuBwLIQdC0u68UhqUkpb5yZ89PoyMiarA/132","nickname":"李","activity_start_time":1525190400,"activity_end_time":1525276799,"task_rule":"达标即可获得红包奖励，未达标者无奖励","task_desc":"\r\n                <p>\r\n                    ①运动数据以跑步钱进的APP为准；\r\n                <\/p>\r\n                <p>\r\n                    ②任务每天晚上12点重置，未领取的红包退回到派发人账户；\r\n                <\/p>\r\n                <p>\r\n                    ③若任务领取人未领取任务及奖励，属用户自主行为，与跑步钱进无关；\r\n   ","to_userid":30,"is_receive":1,"user_step":3086,"is_finished":0}
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
         * id : 770
         * userno:  770
         * task_name : 3820步达标赛
         * task_days : 31
         * target_step : 3820
         * reward_amount : 100.00
         * userid : 193
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/YXr2VhxJpdg2dcIZMrju42mmGXmLabwpbTiaoKq9lP5WbdkhcTkkZ6pjuBwLIQdC0u68UhqUkpb5yZ89PoyMiarA/132
         * nickname : 李
         * activity_start_time : 1525190400
         * activity_end_time : 1525276799
         * task_rule : 达标即可获得红包奖励，未达标者无奖励
         * task_desc :
         * <p>
         * ①运动数据以跑步钱进的APP为准；
         * </p>
         * <p>
         * ②任务每天晚上12点重置，未领取的红包退回到派发人账户；
         * </p>
         * <p>
         * ③若任务领取人未领取任务及奖励，属用户自主行为，与跑步钱进无关；
         * <p>
         * to_userid : 30
         * is_receive : 1
         * user_step : 3086
         * is_finished : 0
         */

        private int id;


        public String getUserno() {
            return userno;
        }

        public void setUserno(String userno) {
            this.userno = userno;
        }

        private String userno;
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
        private int to_userid;
        private int is_receive;
        private int user_step;
        private int is_finished;

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        private int vip;

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

        public int getTo_userid() {
            return to_userid;
        }

        public void setTo_userid(int to_userid) {
            this.to_userid = to_userid;
        }

        public int getIs_receive() {
            return is_receive;
        }

        public void setIs_receive(int is_receive) {
            this.is_receive = is_receive;
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
                    ", to_userid=" + to_userid +
                    ", is_receive=" + is_receive +
                    ", user_step=" + user_step +
                    ", is_finished=" + is_finished +
                    ", vip=" + vip +
                    '}';
        }
    }
}
