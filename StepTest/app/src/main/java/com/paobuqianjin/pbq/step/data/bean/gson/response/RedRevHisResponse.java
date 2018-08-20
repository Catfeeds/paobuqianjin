package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/8/18.
 */

public class RedRevHisResponse {
    /**
     * error : 0
     * message : 获取历史记录成功
     * data : {"count_info":{"receive_total":3.58,"max_money":"2.19000","receive_count":4},"redpacket_list":{"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"record_id":499,"red_id":71,"businessid":1613,"from_uid":35875,"record_money":"2.19000","receive_uid":35822,"receive_time":1534562993,"map_content":"第二个遍地红包","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181018413178.jpeg","zan_count":0,"comment_count":0},{"record_id":485,"red_id":70,"businessid":1613,"from_uid":35875,"record_money":"0.92000","receive_uid":35822,"receive_time":1534563150,"map_content":"第一个遍地红包10个吧","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181017578618.jpeg","zan_count":0,"comment_count":0},{"record_id":1561,"red_id":79,"businessid":1650,"from_uid":35882,"record_money":"0.35000","receive_uid":35822,"receive_time":1534573785,"map_content":"今天苹果免费","user_nickname":"rm_13148896029","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35882201808181420418275.png","zan_count":0,"comment_count":0},{"record_id":547,"red_id":72,"businessid":1613,"from_uid":35875,"record_money":"0.12000","receive_uid":35822,"receive_time":1534562861,"map_content":"单个遍地红包金额","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181023671146.jpg","zan_count":0,"comment_count":0}]}}
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
         * count_info : {"receive_total":3.58,"max_money":"2.19000","receive_count":4}
         * redpacket_list : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"record_id":499,"red_id":71,"businessid":1613,"from_uid":35875,"record_money":"2.19000","receive_uid":35822,"receive_time":1534562993,"map_content":"第二个遍地红包","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181018413178.jpeg","zan_count":0,"comment_count":0},{"record_id":485,"red_id":70,"businessid":1613,"from_uid":35875,"record_money":"0.92000","receive_uid":35822,"receive_time":1534563150,"map_content":"第一个遍地红包10个吧","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181017578618.jpeg","zan_count":0,"comment_count":0},{"record_id":1561,"red_id":79,"businessid":1650,"from_uid":35882,"record_money":"0.35000","receive_uid":35822,"receive_time":1534573785,"map_content":"今天苹果免费","user_nickname":"rm_13148896029","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35882201808181420418275.png","zan_count":0,"comment_count":0},{"record_id":547,"red_id":72,"businessid":1613,"from_uid":35875,"record_money":"0.12000","receive_uid":35822,"receive_time":1534562861,"map_content":"单个遍地红包金额","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181023671146.jpg","zan_count":0,"comment_count":0}]}
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
             * receive_total : 3.58
             * max_money : 2.19000
             * receive_count : 4
             */

            private String receive_total;
            private String max_money;
            private String receive_count;

            public String getReceive_total() {
                return receive_total;
            }

            public void setReceive_total(String receive_total) {
                this.receive_total = receive_total;
            }

            public String getMax_money() {
                return max_money;
            }

            public void setMax_money(String max_money) {
                this.max_money = max_money;
            }

            public String getReceive_count() {
                return receive_count;
            }

            public void setReceive_count(String receive_count) {
                this.receive_count = receive_count;
            }
        }

        public static class RedpacketListBean {
            /**
             * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
             * data : [{"record_id":499,"red_id":71,"businessid":1613,"from_uid":35875,"record_money":"2.19000","receive_uid":35822,"receive_time":1534562993,"map_content":"第二个遍地红包","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181018413178.jpeg","zan_count":0,"comment_count":0},{"record_id":485,"red_id":70,"businessid":1613,"from_uid":35875,"record_money":"0.92000","receive_uid":35822,"receive_time":1534563150,"map_content":"第一个遍地红包10个吧","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181017578618.jpeg","zan_count":0,"comment_count":0},{"record_id":1561,"red_id":79,"businessid":1650,"from_uid":35882,"record_money":"0.35000","receive_uid":35822,"receive_time":1534573785,"map_content":"今天苹果免费","user_nickname":"rm_13148896029","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35882201808181420418275.png","zan_count":0,"comment_count":0},{"record_id":547,"red_id":72,"businessid":1613,"from_uid":35875,"record_money":"0.12000","receive_uid":35822,"receive_time":1534562861,"map_content":"单个遍地红包金额","user_nickname":"rm_13652354126ha","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181023671146.jpg","zan_count":0,"comment_count":0}]
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
                /**
                 * record_id : 499
                 * red_id : 71
                 * businessid : 1613
                 * from_uid : 35875
                 * record_money : 2.19000
                 * receive_uid : 35822
                 * receive_time : 1534562993
                 * map_content : 第二个遍地红包
                 * user_nickname : rm_13652354126ha
                 * avatar : https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png
                 * map_img : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/18/35875201808181018413178.jpeg
                 * zan_count : 0
                 * comment_count : 0
                 */

                private String record_id;
                private String red_id;
                private String businessid;
                private String from_uid;
                private String record_money;
                private String receive_uid;
                private long receive_time;
                private String map_content;
                private String user_nickname;
                private String avatar;
                private String map_img;
                private String zan_count;
                private String comment_count;

                public String getRecord_id() {
                    return record_id;
                }

                public void setRecord_id(String record_id) {
                    this.record_id = record_id;
                }

                public String getRed_id() {
                    return red_id;
                }

                public void setRed_id(String red_id) {
                    this.red_id = red_id;
                }

                public String getBusinessid() {
                    return businessid;
                }

                public void setBusinessid(String businessid) {
                    this.businessid = businessid;
                }

                public String getFrom_uid() {
                    return from_uid;
                }

                public void setFrom_uid(String from_uid) {
                    this.from_uid = from_uid;
                }

                public String getRecord_money() {
                    return record_money;
                }

                public void setRecord_money(String record_money) {
                    this.record_money = record_money;
                }

                public String getReceive_uid() {
                    return receive_uid;
                }

                public void setReceive_uid(String receive_uid) {
                    this.receive_uid = receive_uid;
                }

                public long getReceive_time() {
                    return receive_time;
                }

                public void setReceive_time(long receive_time) {
                    this.receive_time = receive_time;
                }

                public String getMap_content() {
                    return map_content;
                }

                public void setMap_content(String map_content) {
                    this.map_content = map_content;
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
