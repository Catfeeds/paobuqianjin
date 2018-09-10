package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/9/8.
 */

public class SendNearPkgResponse {
    /**
     * error : 0
     * message : 获取红包历史记录成功
     * data : {"count_info":{"send_total":851.12,"send_count":1350,"recice_total":73},"redpacket_list":{"pagenation":{"page":1,"pageSize":10,"totalPage":4,"totalCount":31},"data":[{"money":"29.10","number":10,"red_id":1561,"day":10,"red_content":"好几节课快快快","create_time":1536392050,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1536336000,"e_time":1537200000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1551,"day":10,"red_content":"888888","create_time":1536199792,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1550,"day":10,"red_content":"8888888","create_time":1536199563,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"64.02","number":10,"red_id":1549,"day":1,"red_content":"会快快快","create_time":1536197610,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1536249600,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1548,"day":10,"red_content":"如果好几节课快快快","create_time":1536197457,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1547,"day":10,"red_content":"会快快快","create_time":1536196301,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":3,"red_id":1534,"day":1,"red_content":"","create_time":1535766603,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1535731200,"e_time":1535817600,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":1,"red_id":1532,"day":1,"red_content":"","create_time":1535612894,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535558400,"e_time":1535644800,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1531,"day":10,"red_content":"","create_time":1535526768,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535472000,"e_time":1536336000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","zan_count":0,"comment_count":0,"red_status":0},{"money":"10.00","number":10,"red_id":1530,"day":10,"red_content":"","create_time":1534852392,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1636,"s_time":1534780800,"e_time":1535644800,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/13/35822201808131007600980.jpg","zan_count":0,"comment_count":0,"red_status":0}]}}
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
         * count_info : {"send_total":851.12,"send_count":1350,"recice_total":73}
         * redpacket_list : {"pagenation":{"page":1,"pageSize":10,"totalPage":4,"totalCount":31},"data":[{"money":"29.10","number":10,"red_id":1561,"day":10,"red_content":"好几节课快快快","create_time":1536392050,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1536336000,"e_time":1537200000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1551,"day":10,"red_content":"888888","create_time":1536199792,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1550,"day":10,"red_content":"8888888","create_time":1536199563,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"64.02","number":10,"red_id":1549,"day":1,"red_content":"会快快快","create_time":1536197610,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1536249600,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1548,"day":10,"red_content":"如果好几节课快快快","create_time":1536197457,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1547,"day":10,"red_content":"会快快快","create_time":1536196301,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":3,"red_id":1534,"day":1,"red_content":"","create_time":1535766603,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1535731200,"e_time":1535817600,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":1,"red_id":1532,"day":1,"red_content":"","create_time":1535612894,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535558400,"e_time":1535644800,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1531,"day":10,"red_content":"","create_time":1535526768,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535472000,"e_time":1536336000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","zan_count":0,"comment_count":0,"red_status":0},{"money":"10.00","number":10,"red_id":1530,"day":10,"red_content":"","create_time":1534852392,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1636,"s_time":1534780800,"e_time":1535644800,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/13/35822201808131007600980.jpg","zan_count":0,"comment_count":0,"red_status":0}]}
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
             * send_total : 851.12
             * send_count : 1350
             * recice_total : 73
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
             * pagenation : {"page":1,"pageSize":10,"totalPage":4,"totalCount":31}
             * data : [{"money":"29.10","number":10,"red_id":1561,"day":10,"red_content":"好几节课快快快","create_time":1536392050,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1536336000,"e_time":1537200000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1551,"day":10,"red_content":"888888","create_time":1536199792,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1550,"day":10,"red_content":"8888888","create_time":1536199563,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"64.02","number":10,"red_id":1549,"day":1,"red_content":"会快快快","create_time":1536197610,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1536249600,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1548,"day":10,"red_content":"如果好几节课快快快","create_time":1536197457,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"29.10","number":5,"red_id":1547,"day":10,"red_content":"会快快快","create_time":1536196301,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":3,"red_id":1534,"day":1,"red_content":"","create_time":1535766603,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1535731200,"e_time":1535817600,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":1,"red_id":1532,"day":1,"red_content":"","create_time":1535612894,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535558400,"e_time":1535644800,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1531,"day":10,"red_content":"","create_time":1535526768,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535472000,"e_time":1536336000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","zan_count":0,"comment_count":0,"red_status":0},{"money":"10.00","number":10,"red_id":1530,"day":10,"red_content":"","create_time":1534852392,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1636,"s_time":1534780800,"e_time":1535644800,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/13/35822201808131007600980.jpg","zan_count":0,"comment_count":0,"red_status":0}]
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
                 * totalPage : 4
                 * totalCount : 31
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
                 * money : 29.10
                 * number : 10
                 * red_id : 1561
                 * day : 10
                 * red_content : 好几节课快快快
                 * create_time : 1536392050
                 * user_nickname : 江月何时初照人
                 * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg
                 * businessid : 1666
                 * s_time : 1536336000
                 * e_time : 1537200000
                 * map_img : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg
                 * zan_count : 0
                 * comment_count : 0
                 * red_status : 0
                 */

                private String money;
                private int number;
                private String red_id;
                private int day;
                private String red_content;
                private long create_time;
                private String user_nickname;
                private String avatar;
                private int businessid;
                private long s_time;
                private long e_time;
                private String map_img;
                private String zan_count;
                private String comment_count;
                private String red_status;

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public String getRed_id() {
                    return red_id;
                }

                public void setRed_id(String red_id) {
                    this.red_id = red_id;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public String getRed_content() {
                    return red_content;
                }

                public void setRed_content(String red_content) {
                    this.red_content = red_content;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
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

                public int getBusinessid() {
                    return businessid;
                }

                public void setBusinessid(int businessid) {
                    this.businessid = businessid;
                }

                public long getS_time() {
                    return s_time;
                }

                public void setS_time(int s_time) {
                    this.s_time = s_time;
                }

                public long getE_time() {
                    return e_time;
                }

                public void setE_time(int e_time) {
                    this.e_time = e_time;
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

                public String getRed_status() {
                    return red_status;
                }

                public void setRed_status(String red_status) {
                    this.red_status = red_status;
                }
            }
        }
    }
}
