package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/28.
 */

public class MyReleaseTaskDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"task":{"id":1094,"task_no":"201806290929335819","task_name":"1步达标赛","target_step":1,"reward_amount":"1.00","avgmoney":"1.00","task_days":1,"task_rule":"达标即可获得红包奖励，未达标者无奖励","task_ios_desc":"\r\n            <p>\r\n                ①运动数据以跑步钱进的APP为准；\r\n            <\/p>\r\n            <p>\r\n                ②任务每天晚上12点重置，未领取的红包退回到派发人账户；\r\n            <\/p>\r\n            <p>\r\n                ③若任务领取人未领取任务及奖励，属用户自主行为，与跑步钱进无关；\r\n            <\/p>\r\n            <p>\r\n                ④此活动由跑步钱进举办，与苹果公司无关；\r\n            <\/p>\r\n        ","task_desc":"\r\n            <p>\r\n                ①运动数据以跑步钱进的APP为准；\r\n            <\/p>\r\n            <p>\r\n                ②任务每天晚上12点重置，未领取的红包退回到派发人账户；\r\n            <\/p>\r\n            <p>\r\n                ③若任务领取人未领取任务及奖励，属用户自主行为，与跑步钱进无关；\r\n            <\/p>\r\n            <p>\r\n                ④此活动由跑步钱进举办；\r\n            <\/p>\r\n        ","create_time":1530235773,"end_time":1530288000},"task_record":[{"userid":23420,"userno":23420,"nickname":"九洲涧","activity_start_time":1530201600,"is_receive":0,"amount":"1.00","date":"2018-06-29 00:00"}]}
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
         * task : {"id":1094,"task_no":"201806290929335819","task_name":"1步达标赛","target_step":1,"reward_amount":"1.00","avgmoney":"1.00","task_days":1,"task_rule":"达标即可获得红包奖励，未达标者无奖励","task_ios_desc":"\r\n            <p>\r\n                ①运动数据以跑步钱进的APP为准；\r\n            <\/p>\r\n            <p>\r\n                ②任务每天晚上12点重置，未领取的红包退回到派发人账户；\r\n            <\/p>\r\n            <p>\r\n                ③若任务领取人未领取任务及奖励，属用户自主行为，与跑步钱进无关；\r\n            <\/p>\r\n            <p>\r\n                ④此活动由跑步钱进举办，与苹果公司无关；\r\n            <\/p>\r\n        ","task_desc":"\r\n            <p>\r\n                ①运动数据以跑步钱进的APP为准；\r\n            <\/p>\r\n            <p>\r\n                ②任务每天晚上12点重置，未领取的红包退回到派发人账户；\r\n            <\/p>\r\n            <p>\r\n                ③若任务领取人未领取任务及奖励，属用户自主行为，与跑步钱进无关；\r\n            <\/p>\r\n            <p>\r\n                ④此活动由跑步钱进举办；\r\n            <\/p>\r\n        ","create_time":1530235773,"end_time":1530288000}
         * task_record : [{"userid":23420,"userno":23420,"nickname":"九洲涧","activity_start_time":1530201600,"is_receive":0,"amount":"1.00","date":"2018-06-29 00:00"}]
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

        public static class TaskBean {
            public int getCredit() {
                return credit;
            }

            public int getTrade_way() {
                return trade_way;
            }

            /**
             * id : 1094
             * task_no : 201806290929335819
             * task_name : 1步达标赛
             * target_step : 1
             * reward_amount : 1.00
             * avgmoney : 1.00
             * task_days : 1
             * task_rule : 达标即可获得红包奖励，未达标者无奖励
             * task_ios_desc :
             <p>
             ①运动数据以跑步钱进的APP为准；
             </p>
             <p>
             ②任务每天晚上12点重置，未领取的红包退回到派发人账户；
             </p>
             <p>
             ③若任务领取人未领取任务及奖励，属用户自主行为，与跑步钱进无关；
             </p>
             <p>
             ④此活动由跑步钱进举办，与苹果公司无关；
             </p>

             * task_desc :
             <p>
             ①运动数据以跑步钱进的APP为准；
             </p>
             <p>
             ②任务每天晚上12点重置，未领取的红包退回到派发人账户；
             </p>
             <p>
             ③若任务领取人未领取任务及奖励，属用户自主行为，与跑步钱进无关；
             </p>
             <p>
             ④此活动由跑步钱进举办；
             </p>

             * create_time : 1530235773
             * end_time : 1530288000
             * "trade_way":2,
             * "credit":10
             */
            private int credit;
            private int trade_way;
            private int id;
            private String task_no;
            private String task_name;
            private int target_step;
            private float reward_amount;
            private String avgmoney;
            private int task_days;
            private String task_rule;
            private String task_ios_desc;
            private String task_desc;
            private int create_time;
            private int end_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTask_no() {
                return task_no;
            }

            public void setTask_no(String task_no) {
                this.task_no = task_no;
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

            public String getAvgmoney() {
                return avgmoney;
            }

            public void setAvgmoney(String avgmoney) {
                this.avgmoney = avgmoney;
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

            public String getTask_ios_desc() {
                return task_ios_desc;
            }

            public void setTask_ios_desc(String task_ios_desc) {
                this.task_ios_desc = task_ios_desc;
            }

            public String getTask_desc() {
                return task_desc;
            }

            public void setTask_desc(String task_desc) {
                this.task_desc = task_desc;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }
        }

        public static class TaskRecordBean {
            /**
             * userid : 23420
             * userno : 23420
             * nickname : 九洲涧
             * activity_start_time : 1530201600
             * is_receive : 0
             * amount : 1.00
             * date : 2018-06-29 00:00
             */

            private int userid;
            private String userno;
            private String nickname;
            private int activity_start_time;
            private int is_receive;
            private String amount;
            private String date;

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getUserno() {
                return userno;
            }

            public void setUserno(String userno) {
                this.userno = userno;
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

            public int getIs_receive() {
                return is_receive;
            }

            public void setIs_receive(int is_receive) {
                this.is_receive = is_receive;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
