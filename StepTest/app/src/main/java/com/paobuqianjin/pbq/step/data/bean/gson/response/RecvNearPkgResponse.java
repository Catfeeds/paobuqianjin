package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/9/8.
 */

public class RecvNearPkgResponse {
    /**
     * error : 0
     * message : 获取红包历史记录成功
     * data : {"count_info":{"receive_total":18.73,"max_money":2.98,"receive_count":10},"redpacket_list":{"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":10},"data":[{"record_id":37078,"red_id":1561,"businessid":1666,"from_uid":35822,"record_money":"0.40","receive_uid":35822,"receive_time":1536392082,"red_content":"好几节课快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0},{"record_id":36790,"red_id":1551,"businessid":1667,"from_uid":35822,"record_money":"0.44","receive_uid":35822,"receive_time":1536390841,"red_content":"888888","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0},{"record_id":36726,"red_id":1550,"businessid":1667,"from_uid":35822,"record_money":"0.60","receive_uid":35822,"receive_time":1536390830,"red_content":"8888888","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0},{"record_id":36649,"red_id":1548,"businessid":1670,"from_uid":35822,"record_money":"0.39","receive_uid":35822,"receive_time":1536390758,"red_content":"如果好几节课快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0},{"record_id":36585,"red_id":1547,"businessid":1670,"from_uid":35822,"record_money":"0.87","receive_uid":35822,"receive_time":1536390543,"red_content":"会快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0},{"record_id":34787,"red_id":1494,"businessid":1616,"from_uid":35876,"record_money":"0.17","receive_uid":35822,"receive_time":1536390180,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":34447,"red_id":1493,"businessid":1616,"from_uid":35876,"record_money":"0.06","receive_uid":35822,"receive_time":1536372410,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":33935,"red_id":1492,"businessid":1616,"from_uid":35876,"record_money":"0.17","receive_uid":35822,"receive_time":1536372388,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":33921,"red_id":1492,"businessid":1616,"from_uid":35876,"record_money":"0.06","receive_uid":35822,"receive_time":1536226301,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":36575,"red_id":1547,"businessid":1670,"from_uid":35822,"record_money":"0.33","receive_uid":35822,"receive_time":1536197543,"red_content":"会快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0}]}}
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
         * count_info : {"receive_total":18.73,"max_money":2.98,"receive_count":10}
         * redpacket_list : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":10},"data":[{"record_id":37078,"red_id":1561,"businessid":1666,"from_uid":35822,"record_money":"0.40","receive_uid":35822,"receive_time":1536392082,"red_content":"好几节课快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0},{"record_id":36790,"red_id":1551,"businessid":1667,"from_uid":35822,"record_money":"0.44","receive_uid":35822,"receive_time":1536390841,"red_content":"888888","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0},{"record_id":36726,"red_id":1550,"businessid":1667,"from_uid":35822,"record_money":"0.60","receive_uid":35822,"receive_time":1536390830,"red_content":"8888888","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0},{"record_id":36649,"red_id":1548,"businessid":1670,"from_uid":35822,"record_money":"0.39","receive_uid":35822,"receive_time":1536390758,"red_content":"如果好几节课快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0},{"record_id":36585,"red_id":1547,"businessid":1670,"from_uid":35822,"record_money":"0.87","receive_uid":35822,"receive_time":1536390543,"red_content":"会快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0},{"record_id":34787,"red_id":1494,"businessid":1616,"from_uid":35876,"record_money":"0.17","receive_uid":35822,"receive_time":1536390180,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":34447,"red_id":1493,"businessid":1616,"from_uid":35876,"record_money":"0.06","receive_uid":35822,"receive_time":1536372410,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":33935,"red_id":1492,"businessid":1616,"from_uid":35876,"record_money":"0.17","receive_uid":35822,"receive_time":1536372388,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":33921,"red_id":1492,"businessid":1616,"from_uid":35876,"record_money":"0.06","receive_uid":35822,"receive_time":1536226301,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":36575,"red_id":1547,"businessid":1670,"from_uid":35822,"record_money":"0.33","receive_uid":35822,"receive_time":1536197543,"red_content":"会快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0}]}
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
             * receive_total : 18.73
             * max_money : 2.98
             * receive_count : 10
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
             * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":10}
             * data : [{"record_id":37078,"red_id":1561,"businessid":1666,"from_uid":35822,"record_money":"0.40","receive_uid":35822,"receive_time":1536392082,"red_content":"好几节课快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0},{"record_id":36790,"red_id":1551,"businessid":1667,"from_uid":35822,"record_money":"0.44","receive_uid":35822,"receive_time":1536390841,"red_content":"888888","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0},{"record_id":36726,"red_id":1550,"businessid":1667,"from_uid":35822,"record_money":"0.60","receive_uid":35822,"receive_time":1536390830,"red_content":"8888888","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0},{"record_id":36649,"red_id":1548,"businessid":1670,"from_uid":35822,"record_money":"0.39","receive_uid":35822,"receive_time":1536390758,"red_content":"如果好几节课快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0},{"record_id":36585,"red_id":1547,"businessid":1670,"from_uid":35822,"record_money":"0.87","receive_uid":35822,"receive_time":1536390543,"red_content":"会快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0},{"record_id":34787,"red_id":1494,"businessid":1616,"from_uid":35876,"record_money":"0.17","receive_uid":35822,"receive_time":1536390180,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":34447,"red_id":1493,"businessid":1616,"from_uid":35876,"record_money":"0.06","receive_uid":35822,"receive_time":1536372410,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":33935,"red_id":1492,"businessid":1616,"from_uid":35876,"record_money":"0.17","receive_uid":35822,"receive_time":1536372388,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":33921,"red_id":1492,"businessid":1616,"from_uid":35876,"record_money":"0.06","receive_uid":35822,"receive_time":1536226301,"red_content":"","user_nickname":"rm_18620134550","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","red_img":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6037D1C6-46BD-465F-B2A9-9040F597DA01.jpg","zan_count":0,"comment_count":0},{"record_id":36575,"red_id":1547,"businessid":1670,"from_uid":35822,"record_money":"0.33","receive_uid":35822,"receive_time":1536197543,"red_content":"会快快快","user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","red_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0}]
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
                 * totalCount : 10
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
                 * record_id : 37078
                 * red_id : 1561
                 * businessid : 1666
                 * from_uid : 35822
                 * record_money : 0.40
                 * receive_uid : 35822
                 * receive_time : 1536392082
                 * red_content : 好几节课快快快
                 * user_nickname : 江月何时初照人
                 * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg
                 * red_img : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg
                 * zan_count : 0
                 * comment_count : 0
                 */

                private int record_id;
                private String red_id;
                private int businessid;
                private int from_uid;
                private String record_money;
                private int receive_uid;
                private long receive_time;
                private String red_content;
                private String user_nickname;
                private String avatar;
                private String red_img;
                private String zan_count;
                private String comment_count;

                public int getRecord_id() {
                    return record_id;
                }

                public void setRecord_id(int record_id) {
                    this.record_id = record_id;
                }

                public String getRed_id() {
                    return red_id;
                }

                public void setRed_id(String red_id) {
                    this.red_id = red_id;
                }

                public int getBusinessid() {
                    return businessid;
                }

                public void setBusinessid(int businessid) {
                    this.businessid = businessid;
                }

                public int getFrom_uid() {
                    return from_uid;
                }

                public void setFrom_uid(int from_uid) {
                    this.from_uid = from_uid;
                }

                public String getRecord_money() {
                    return record_money;
                }

                public void setRecord_money(String record_money) {
                    this.record_money = record_money;
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

                public void setReceive_time(int receive_time) {
                    this.receive_time = receive_time;
                }

                public String getRed_content() {
                    return red_content;
                }

                public void setRed_content(String red_content) {
                    this.red_content = red_content;
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

                public String getRed_img() {
                    return red_img;
                }

                public void setRed_img(String red_img) {
                    this.red_img = red_img;
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
