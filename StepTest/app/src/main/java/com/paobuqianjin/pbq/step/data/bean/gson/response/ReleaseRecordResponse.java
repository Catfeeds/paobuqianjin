package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/28.
 */

public class ReleaseRecordResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":5},"data":[{"id":9,"task_name":"8000步达标赛","to_userid":8,"nickname":"酒自斟","task_days":10,"create_time":1517565886,"unfinished":{"days":10,"amount":100},"finished":{"days":0,"amount":0}},{"id":17,"task_name":"1000步达标赛","to_userid":10,"nickname":"孤君独战","task_days":1,"create_time":1519614775,"unfinished":{"days":1,"amount":1},"finished":{"days":0,"amount":0}},{"id":23,"task_name":"1000步达标赛","to_userid":2,"nickname":"李五","task_days":10,"create_time":1519700356,"unfinished":{"days":10,"amount":1000},"finished":{"days":0,"amount":0}},{"id":24,"task_name":"2000步达标赛","to_userid":3,"nickname":"九卿臣","task_days":10,"create_time":1519700367,"unfinished":{"days":10,"amount":1000},"finished":{"days":0,"amount":0}}]}
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
        return "ReleaseRecordResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":5}
         * data : [{"id":9,"task_name":"8000步达标赛","to_userid":8,"nickname":"酒自斟","task_days":10,"create_time":1517565886,"unfinished":{"days":10,"amount":100},"finished":{"days":0,"amount":0}},{"id":17,"task_name":"1000步达标赛","to_userid":10,"nickname":"孤君独战","task_days":1,"create_time":1519614775,"unfinished":{"days":1,"amount":1},"finished":{"days":0,"amount":0}},{"id":23,"task_name":"1000步达标赛","to_userid":2,"nickname":"李五","task_days":10,"create_time":1519700356,"unfinished":{"days":10,"amount":1000},"finished":{"days":0,"amount":0}},{"id":24,"task_name":"2000步达标赛","to_userid":3,"nickname":"九卿臣","task_days":10,"create_time":1519700367,"unfinished":{"days":10,"amount":1000},"finished":{"days":0,"amount":0}}]
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
             * totalCount : 5
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
             * id : 9
             * task_name : 8000步达标赛
             * to_userid : 8
             * nickname : 酒自斟
             * task_days : 10
             * create_time : 1517565886
             * unfinished : {"days":10,"amount":100}
             * finished : {"days":0,"amount":0}
             */

            private int id;
            private String task_name;
            private int to_userid;
            private String nickname;
            private int task_days;
            private long create_time;
            private UnfinishedBean unfinished;
            private FinishedBean finished;

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

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public UnfinishedBean getUnfinished() {
                return unfinished;
            }

            public void setUnfinished(UnfinishedBean unfinished) {
                this.unfinished = unfinished;
            }

            public FinishedBean getFinished() {
                return finished;
            }

            public void setFinished(FinishedBean finished) {
                this.finished = finished;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", task_name='" + task_name + '\'' +
                        ", to_userid=" + to_userid +
                        ", nickname='" + nickname + '\'' +
                        ", task_days=" + task_days +
                        ", create_time=" + create_time +
                        ", unfinished=" + unfinished +
                        ", finished=" + finished +
                        '}';
            }

            public static class UnfinishedBean {
                /**
                 * days : 10
                 * amount : 100
                 */

                private int days;
                private float amount;

                public int getDays() {
                    return days;
                }

                public void setDays(int days) {
                    this.days = days;
                }

                public float getAmount() {
                    return amount;
                }

                public void setAmount(float amount) {
                    this.amount = amount;
                }

                @Override
                public String toString() {
                    return "UnfinishedBean{" +
                            "days=" + days +
                            ", amount=" + amount +
                            '}';
                }
            }

            public static class FinishedBean {
                /**
                 * days : 0
                 * amount : 0
                 */

                private int days;
                private float amount;

                public int getDays() {
                    return days;
                }

                public void setDays(int days) {
                    this.days = days;
                }

                public float getAmount() {
                    return amount;
                }

                public void setAmount(float amount) {
                    this.amount = amount;
                }

                @Override
                public String toString() {
                    return "FinishedBean{" +
                            "days=" + days +
                            ", amount=" + amount +
                            '}';
                }
            }
        }
    }
}
