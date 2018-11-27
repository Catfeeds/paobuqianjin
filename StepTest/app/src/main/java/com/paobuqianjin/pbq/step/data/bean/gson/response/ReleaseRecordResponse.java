package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/28.
 */
/*
@className :ReleaseRecordResponse
*@date 2018/6/28
*@author
*@description 个人任务发布记录
*/
public class ReleaseRecordResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"id":1083,"task_no":"201806281437349481","task_name":"10000步达标赛","reward_amount":"1.00","avgmoney":"1.00","to_userid":23420,"nickname":"九洲涧","task_days":1,"create_time":1530167854,"end_time":1530201600,"status":1},{"id":1082,"task_no":"201806281407172571","task_name":"10000步达标赛","reward_amount":"1.00","avgmoney":"1.00","to_userid":23417,"nickname":"rm_13824123478","task_days":1,"create_time":1530166037,"end_time":1530201600,"status":1},{"id":1081,"task_no":"201806280919476673","task_name":"1步达标赛","reward_amount":"1.00","avgmoney":"1.00","to_userid":23404,"nickname":"rm_13392444684","task_days":1,"create_time":1530148787,"end_time":1530201600,"status":1},{"id":1080,"task_no":"201806271920246433","task_name":"4000步达标赛","reward_amount":"1.00","avgmoney":"1.00","to_userid":23404,"nickname":"rm_13392444684","task_days":1,"create_time":1530098424,"end_time":1530115200,"status":2}]}
     */

    private int error;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"id":1083,"task_no":"201806281437349481","task_name":"10000步达标赛","reward_amount":"1.00","avgmoney":"1.00","to_userid":23420,"nickname":"九洲涧","task_days":1,"create_time":1530167854,"end_time":1530201600,"status":1},{"id":1082,"task_no":"201806281407172571","task_name":"10000步达标赛","reward_amount":"1.00","avgmoney":"1.00","to_userid":23417,"nickname":"rm_13824123478","task_days":1,"create_time":1530166037,"end_time":1530201600,"status":1},{"id":1081,"task_no":"201806280919476673","task_name":"1步达标赛","reward_amount":"1.00","avgmoney":"1.00","to_userid":23404,"nickname":"rm_13392444684","task_days":1,"create_time":1530148787,"end_time":1530201600,"status":1},{"id":1080,"task_no":"201806271920246433","task_name":"4000步达标赛","reward_amount":"1.00","avgmoney":"1.00","to_userid":23404,"nickname":"rm_13392444684","task_days":1,"create_time":1530098424,"end_time":1530115200,"status":2}]
         */

        private PagenationBean pagenation;
        private List<DataBean> data;

        public PagenationBean getPagenation() {
            return pagenation;
        }

        public void setPagenation(PagenationBean pagenation) {
            this.pagenation = pagenation;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 10
             * totalPage : 1
             * totalCount : 4
             */

            private int page;
            private int pageSize;
            private int totalPage;
            private int totalCount;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }
        }

        public static class DataBean {
            public int getTrade_way() {
                return trade_way;
            }

            public int getCredit() {
                return credit;
            }

            /**
             * id : 1083
             * task_no : 201806281437349481
             * task_name : 10000步达标赛
             * reward_amount : 1.00
             * avgmoney : 1.00
             * to_userid : 23420
             * nickname : 九洲涧
             * task_days : 1
             * create_time : 1530167854
             * end_time : 1530201600
             * status : 1
             * "trade_way":2,
             * "credit":10
             */
            private int trade_way;
            private int credit;
            private int id;
            private String task_no;
            private String task_name;
            private String reward_amount;
            private String avgmoney;
            private int to_userid;
            private String nickname;
            private int task_days;
            private int create_time;
            private int end_time;
            private int status;

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

            public String getReward_amount() {
                return reward_amount;
            }

            public void setReward_amount(String reward_amount) {
                this.reward_amount = reward_amount;
            }

            public String getAvgmoney() {
                return avgmoney;
            }

            public void setAvgmoney(String avgmoney) {
                this.avgmoney = avgmoney;
            }

            public int getTo_userid() {
                return to_userid;
            }

            public void setTo_userid(int to_userid) {
                this.to_userid = to_userid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getTask_days() {
                return task_days;
            }

            public void setTask_days(int task_days) {
                this.task_days = task_days;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
