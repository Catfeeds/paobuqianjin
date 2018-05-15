package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/5/3.
 */

public class LiveResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"id":1,"target":10000,"title":"跑步钱进健康跑","conver":"https://activity-1255484416.cos.ap-guangzhou.myqcloud.com/temp2018/201853/1525310224657_12731_pao2_20180424145734.png","starttime":"2018-05-02 07:24:07","endtime":"2018-05-11 10:25:07","deail":"<p style=\"white-space: normal;\">任务规则: 达标即可获得红包奖励，未达标者无奖励<\/p><p style=\"white-space: normal;\">任务说明:<\/p><p style=\"white-space: normal;\">① 运动数据以跑步钱进的APP为准；<\/p><p style=\"white-space: normal;\">② 任务截止时间为当天23:59:59，达标即可获得奖励，先到先得；<\/p><p style=\"white-space: normal;\">④ 奖励金额将会发放至\u201c我的钱包\u201d<\/p><p style=\"white-space: normal;\">③ 该活动由跑步钱进举办，和苹果公司无关；<\/p><p><br/><\/p>","create_time":"2018-05-02 19:29:31","remote_url":"https://mer.runmoneyin.com/Admin/Activity/activityDetails?activid=1&uid=30","read_total":2,"is_process":2},{"id":2,"target":12000,"title":"测试活动","conver":"https://activity-1255484416.cos.ap-guangzhou.myqcloud.com/temp2018/201853/1525310235555_24730_pao3_20180424145741.png","starttime":"2018-04-29 07:30:40","endtime":"2018-05-03 07:31:40","deail":"<p>我现在测试的内容20180502<\/p>","create_time":"2018-05-02 19:32:43","remote_url":"https://mer.runmoneyin.com/Admin/Activity/activityDetails?activid=2&uid=30","read_total":0,"is_process":1}]}
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
        return "LiveResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : [{"id":1,"target":10000,"title":"跑步钱进健康跑","conver":"https://activity-1255484416.cos.ap-guangzhou.myqcloud.com/temp2018/201853/1525310224657_12731_pao2_20180424145734.png","starttime":"2018-05-02 07:24:07","endtime":"2018-05-11 10:25:07","deail":"<p style=\"white-space: normal;\">任务规则: 达标即可获得红包奖励，未达标者无奖励<\/p><p style=\"white-space: normal;\">任务说明:<\/p><p style=\"white-space: normal;\">① 运动数据以跑步钱进的APP为准；<\/p><p style=\"white-space: normal;\">② 任务截止时间为当天23:59:59，达标即可获得奖励，先到先得；<\/p><p style=\"white-space: normal;\">④ 奖励金额将会发放至\u201c我的钱包\u201d<\/p><p style=\"white-space: normal;\">③ 该活动由跑步钱进举办，和苹果公司无关；<\/p><p><br/><\/p>","create_time":"2018-05-02 19:29:31","remote_url":"https://mer.runmoneyin.com/Admin/Activity/activityDetails?activid=1&uid=30","read_total":2,"is_process":2},{"id":2,"target":12000,"title":"测试活动","conver":"https://activity-1255484416.cos.ap-guangzhou.myqcloud.com/temp2018/201853/1525310235555_24730_pao3_20180424145741.png","starttime":"2018-04-29 07:30:40","endtime":"2018-05-03 07:31:40","deail":"<p>我现在测试的内容20180502<\/p>","create_time":"2018-05-02 19:32:43","remote_url":"https://mer.runmoneyin.com/Admin/Activity/activityDetails?activid=2&uid=30","read_total":0,"is_process":1}]
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
             * totalCount : 2
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
             * id : 1
             * target : 10000
             * title : 跑步钱进健康跑
             * conver : https://activity-1255484416.cos.ap-guangzhou.myqcloud.com/temp2018/201853/1525310224657_12731_pao2_20180424145734.png
             * starttime : 2018-05-02 07:24:07
             * endtime : 2018-05-11 10:25:07
             * deail : <p style="white-space: normal;">任务规则: 达标即可获得红包奖励，未达标者无奖励</p><p style="white-space: normal;">任务说明:</p><p style="white-space: normal;">① 运动数据以跑步钱进的APP为准；</p><p style="white-space: normal;">② 任务截止时间为当天23:59:59，达标即可获得奖励，先到先得；</p><p style="white-space: normal;">④ 奖励金额将会发放至“我的钱包”</p><p style="white-space: normal;">③ 该活动由跑步钱进举办，和苹果公司无关；</p><p><br/></p>
             * create_time : 2018-05-02 19:29:31
             * remote_url : https://mer.runmoneyin.com/Admin/Activity/activityDetails?activid=1&uid=30
             * read_total : 2
             * is_process : 2
             */

            private int id;
            private int target;
            private String title;
            private String conver;
            private String starttime;
            private String endtime;
            private String deail;
            private String create_time;
            private String remote_url;
            private int read_total;
            private int is_process;//活动状态：0未开始 1进行中 2已结束 3已经被领完
            private int is_receive;//领取状态：0未领 1已领

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTarget() {
                return target;
            }

            public void setTarget(int target) {
                this.target = target;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getConver() {
                return conver;
            }

            public void setConver(String conver) {
                this.conver = conver;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getDeail() {
                return deail;
            }

            public void setDeail(String deail) {
                this.deail = deail;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getRemote_url() {
                return remote_url;
            }

            public void setRemote_url(String remote_url) {
                this.remote_url = remote_url;
            }

            public int getRead_total() {
                return read_total;
            }

            public void setRead_total(int read_total) {
                this.read_total = read_total;
            }

            public int getIs_process() {
                return is_process;
            }

            public void setIs_process(int is_process) {
                this.is_process = is_process;
            }

            public int getIs_receive() {
                return is_receive;
            }

            public void setIs_receive(int is_receive) {
                this.is_receive = is_receive;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", target=" + target +
                        ", title='" + title + '\'' +
                        ", conver='" + conver + '\'' +
                        ", starttime='" + starttime + '\'' +
                        ", endtime='" + endtime + '\'' +
                        ", deail='" + deail + '\'' +
                        ", create_time='" + create_time + '\'' +
                        ", remote_url='" + remote_url + '\'' +
                        ", read_total=" + read_total +
                        ", is_process=" + is_process +
                        ", is_receive=" + is_receive +
                        '}';
            }
        }
    }
}
