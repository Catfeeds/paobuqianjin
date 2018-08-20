package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/8/20.
 */

public class RedSendHisResponse {
    /**
     * error : 0
     * message : 获取历史记录成功
     * data : {"count_info":{"send_total":60,"send_count":2,"recice_total":0},"redpacket_list":{"pagenation":{"page":1,"pageSize":3,"totalPage":1,"totalCount":2},"data":[{"money":"30","receive_num":0,"red_id":95,"map_content":"淘宝店","create_time":1534748948,"user_nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35822201808201508407133.jpg","zan_count":0,"comment_count":0},{"money":"30","receive_num":0,"red_id":97,"map_content":"收大麦玉米桔梗","create_time":1534749030,"user_nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35822201808201510501790.png","zan_count":0,"comment_count":0}]}}
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
         * count_info : {"send_total":60,"send_count":2,"recice_total":0}
         * redpacket_list : {"pagenation":{"page":1,"pageSize":3,"totalPage":1,"totalCount":2},"data":[{"money":"30","receive_num":0,"red_id":95,"map_content":"淘宝店","create_time":1534748948,"user_nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35822201808201508407133.jpg","zan_count":0,"comment_count":0},{"money":"30","receive_num":0,"red_id":97,"map_content":"收大麦玉米桔梗","create_time":1534749030,"user_nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35822201808201510501790.png","zan_count":0,"comment_count":0}]}
         */

        private CountInfoBean count_info;
        private RedpacketListBean redpacket_list;

        public CountInfoBean getCount_info() {
            return count_info;
        }

        public void setCount_info(CountInfoBean count_info) {
            this.count_info = count_info;
        }

        public RedpacketListBean getRedpacket_list() {
            return redpacket_list;
        }

        public void setRedpacket_list(RedpacketListBean redpacket_list) {
            this.redpacket_list = redpacket_list;
        }

        public static class CountInfoBean {
            /**
             * send_total : 60
             * send_count : 2
             * recice_total : 0
             */

            private String send_total;
            private String send_count;
            private String recice_total;

            public String getSend_total() {
                return send_total;
            }

            public void setSend_total(String send_total) {
                this.send_total = send_total;
            }

            public String getSend_count() {
                return send_count;
            }

            public void setSend_count(String send_count) {
                this.send_count = send_count;
            }

            public String getRecice_total() {
                return recice_total;
            }

            public void setRecice_total(String recice_total) {
                this.recice_total = recice_total;
            }
        }

        public static class RedpacketListBean {
            /**
             * pagenation : {"page":1,"pageSize":3,"totalPage":1,"totalCount":2}
             * data : [{"money":"30","receive_num":0,"red_id":95,"map_content":"淘宝店","create_time":1534748948,"user_nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35822201808201508407133.jpg","zan_count":0,"comment_count":0},{"money":"30","receive_num":0,"red_id":97,"map_content":"收大麦玉米桔梗","create_time":1534749030,"user_nickname":"rm_13424156029","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35822201808201510501790.png","zan_count":0,"comment_count":0}]
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
                 * pageSize : 3
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
            }

            public static class DataBean {
                /**
                 * money : 30
                 * receive_num : 0
                 * red_id : 95
                 * map_content : 淘宝店
                 * create_time : 1534748948
                 * user_nickname : rm_13424156029
                 * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/7/35822201808071121231227.jpg
                 * map_img : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/20/35822201808201508407133.jpg
                 * zan_count : 0
                 * comment_count : 0
                 */

                private String money;
                private String receive_num;
                private String red_id;
                private String map_content;
                private long create_time;
                private String user_nickname;
                private String avatar;
                private String map_img;
                private String zan_count;
                private String comment_count;

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getReceive_num() {
                    return receive_num;
                }

                public void setReceive_num(String receive_num) {
                    this.receive_num = receive_num;
                }

                public String getRed_id() {
                    return red_id;
                }

                public void setRed_id(String red_id) {
                    this.red_id = red_id;
                }

                public String getMap_content() {
                    return map_content;
                }

                public void setMap_content(String map_content) {
                    this.map_content = map_content;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public String getUser_nickname() {
                    return user_nickname;
                }

                public void setUser_nickname(String user_nickname) {
                    this.user_nickname = user_nickname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getMap_img() {
                    return map_img;
                }

                public void setMap_img(String map_img) {
                    this.map_img = map_img;
                }

                public String getZan_count() {
                    return zan_count;
                }

                public void setZan_count(String zan_count) {
                    this.zan_count = zan_count;
                }

                public String getComment_count() {
                    return comment_count;
                }

                public void setComment_count(String comment_count) {
                    this.comment_count = comment_count;
                }
            }
        }
    }
}
