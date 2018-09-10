package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/8/20.
 */

public class RoundRedInfoResponse {
    /**
     * error : 0
     * message : success
     * data : {"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg","is_me":0,"money":"30","receive_num":3,"income_money":2.49,"receive_total_money":9.43,"receive_list":{"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":3},"data":[{"red_id":109,"from_uid":35835,"money":"3.63000","receive_uid":35882,"receive_time":1534764369,"nickname":"rm_13148896029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35882201808201416921087.jpg"},{"red_id":109,"from_uid":35835,"money":"2.49000","receive_uid":35822,"receive_time":1534749927,"nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg"},{"red_id":109,"from_uid":35835,"money":"3.31000","receive_uid":35821,"receive_time":1534754500,"nickname":"哥哥你是怎么回事！你说你","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/15/35821201808151933186689.jpg"}]}}
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
         * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg
         * is_me : 0
         * money : 30
         * receive_num : 3
         * income_money : 2.49
         * receive_total_money : 9.43
         * redpacket_num:3000
         * receive_list : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":3},"data":[{"red_id":109,"from_uid":35835,"money":"3.63000","receive_uid":35882,"receive_time":1534764369,"nickname":"rm_13148896029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35882201808201416921087.jpg"},{"red_id":109,"from_uid":35835,"money":"2.49000","receive_uid":35822,"receive_time":1534749927,"nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg"},{"red_id":109,"from_uid":35835,"money":"3.31000","receive_uid":35821,"receive_time":1534754500,"nickname":"哥哥你是怎么回事！你说你","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/15/35821201808151933186689.jpg"}]}
         */

        private String avatar;
        private int is_me;
        private String money;
        private int receive_num;
        private String income_money;
        private String receive_total_money;

        public String getRedpacket_num() {
            return redpacket_num;
        }

        public void setRedpacket_num(String redpacket_num) {
            this.redpacket_num = redpacket_num;
        }

        private String redpacket_num;
        private ReceiveListBean receive_list;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getIs_me() {
            return is_me;
        }

        public void setIs_me(int is_me) {
            this.is_me = is_me;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getReceive_num() {
            return receive_num;
        }

        public void setReceive_num(int receive_num) {
            this.receive_num = receive_num;
        }

        public String getIncome_money() {
            return income_money;
        }

        public void setIncome_money(String income_money) {
            this.income_money = income_money;
        }

        public String getReceive_total_money() {
            return receive_total_money;
        }

        public void setReceive_total_money(String receive_total_money) {
            this.receive_total_money = receive_total_money;
        }

        public ReceiveListBean getReceive_list() {
            return receive_list;
        }

        public void setReceive_list(ReceiveListBean receive_list) {
            this.receive_list = receive_list;
        }

        public static class ReceiveListBean {
            /**
             * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":3}
             * data : [{"red_id":109,"from_uid":35835,"money":"3.63000","receive_uid":35882,"receive_time":1534764369,"nickname":"rm_13148896029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35882201808201416921087.jpg"},{"red_id":109,"from_uid":35835,"money":"2.49000","receive_uid":35822,"receive_time":1534749927,"nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg"},{"red_id":109,"from_uid":35835,"money":"3.31000","receive_uid":35821,"receive_time":1534754500,"nickname":"哥哥你是怎么回事！你说你","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/15/35821201808151933186689.jpg"}]
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
            }

            public static class DataBean {
                /**
                 * red_id : 109
                 * from_uid : 35835
                 * money : 3.63000
                 * receive_uid : 35882
                 * receive_time : 1534764369
                 * nickname : rm_13148896029
                 * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35882201808201416921087.jpg
                 */

                private int red_id;
                private int from_uid;
                private String money;
                private int receive_uid;
                private long receive_time;
                private String nickname;
                private String avatar;

                public int getRed_id() {
                    return red_id;
                }

                public void setRed_id(int red_id) {
                    this.red_id = red_id;
                }

                public int getFrom_uid() {
                    return from_uid;
                }

                public void setFrom_uid(int from_uid) {
                    this.from_uid = from_uid;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public int getReceive_uid() {
                    return receive_uid;
                }

                public void setReceive_uid(int receive_uid) {
                    this.receive_uid = receive_uid;
                }

                public long getReceive_time() {
                    return receive_time;
                }

                public void setReceive_time(long receive_time) {
                    this.receive_time = receive_time;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }
            }
        }
    }
}
