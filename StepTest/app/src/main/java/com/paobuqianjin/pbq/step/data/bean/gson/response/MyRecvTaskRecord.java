package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/8.
 */
/*
@className :MyRecvTaskRecord
*@date 2018/2/8
*@author
*@description 我领取的任务列表  http://119.29.10.64/v1/TaskRecord?action=all&userid=8
*/
public class MyRecvTaskRecord {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":3},"data":[{"id":21,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/icx2uvqOiaR6R1f8UA0V5gp98BcUviaeVpN3VWaMEYyPGQzJTdsJYibbrw3ialv1TMibrJNfLdFL7hrJM08RGxIEvqpA/132","nickname":"Annabelle","task_name":"5000步达标赛","target_step":5000,"reward_amount":"0.10","task_days":10,"is_receive":0,"is_finished":0},{"id":34,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","nickname":"陈杰","task_name":"8000步达标赛","target_step":8000,"reward_amount":"10.00","task_days":10,"is_receive":0,"is_finished":0},{"id":51,"avatar":"http://wx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","task_name":"8000步达标赛","target_step":8000,"reward_amount":"10.00","task_days":10,"is_receive":0,"is_finished":0}]}
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

    @Override
    public String toString() {
        return "MyRecvTaskRecord{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":3}
         * data : [{"id":21,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/icx2uvqOiaR6R1f8UA0V5gp98BcUviaeVpN3VWaMEYyPGQzJTdsJYibbrw3ialv1TMibrJNfLdFL7hrJM08RGxIEvqpA/132","nickname":"Annabelle","task_name":"5000步达标赛","target_step":5000,"reward_amount":"0.10","task_days":10,"is_receive":0,"is_finished":0},{"id":34,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","nickname":"陈杰","task_name":"8000步达标赛","target_step":8000,"reward_amount":"10.00","task_days":10,"is_receive":0,"is_finished":0},{"id":51,"avatar":"http://wx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","task_name":"8000步达标赛","target_step":8000,"reward_amount":"10.00","task_days":10,"is_receive":0,"is_finished":0}]
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

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "pagenation=" + pagenation +
                    ", data=" + data +
                    '}';
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 10
             * totalPage : 1
             * totalCount : 3
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

            @Override
            public String toString() {
                return "PagenationBean{" +
                        "page=" + page +
                        ", pageSize=" + pageSize +
                        ", totalPage=" + totalPage +
                        ", totalCount=" + totalCount +
                        '}';
            }
        }

        public static class DataBean {
            /**
             * id : 21
             * avatar : https://wx.qlogo.cn/mmopen/vi_32/icx2uvqOiaR6R1f8UA0V5gp98BcUviaeVpN3VWaMEYyPGQzJTdsJYibbrw3ialv1TMibrJNfLdFL7hrJM08RGxIEvqpA/132
             * nickname : Annabelle
             * task_name : 5000步达标赛
             * target_step : 5000
             * reward_amount : 0.10
             * task_days : 10
             * is_receive : 0
             * is_finished : 0
             */

            private int id;
            private String avatar;
            private String nickname;
            private String task_name;
            private int target_step;
            private String reward_amount;
            private int task_days;
            private int is_receive;
            private int is_finished;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getReward_amount() {
                return reward_amount;
            }

            public void setReward_amount(String reward_amount) {
                this.reward_amount = reward_amount;
            }

            public int getTask_days() {
                return task_days;
            }

            public void setTask_days(int task_days) {
                this.task_days = task_days;
            }

            public int getIs_receive() {
                return is_receive;
            }

            public void setIs_receive(int is_receive) {
                this.is_receive = is_receive;
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
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", task_name='" + task_name + '\'' +
                        ", target_step=" + target_step +
                        ", reward_amount='" + reward_amount + '\'' +
                        ", task_days=" + task_days +
                        ", is_receive=" + is_receive +
                        ", is_finished=" + is_finished +
                        '}';
            }
        }
    }
}
