package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/10/19.
 */

public class ConSumSendHisResponse {

    /**
     * error : 0
     * message : success
     * data : {"info":{"send_sum":10,"receive_sum":1},"send_list":{"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":1},"data":[{"voucherid":254,"type":1,"vname":"天天酷跑","vcontent":"满300元减30减","step":1,"condition":"300","money":"30","amount":10,"receive":1,"day":30,"create_time":1539844178,"e_time":1542383999,"lower_id":0,"lower_time":0,"userid":35822,"nickname":"江月何时初照人","content":"","target_url":"","businessid":1664,"businessname":"太讨厌","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","status":0,"zan_count":0,"comment_count":0,"vimg_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/18/35822201810181427129953.jpg"],"vimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/18/35822201810181427129953.jpg"}]}}
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
        return "ConSumSendHisResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * info : {"send_sum":10,"receive_sum":1}
         * send_list : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":1},"data":[{"voucherid":254,"type":1,"vname":"天天酷跑","vcontent":"满300元减30减","step":1,"condition":"300","money":"30","amount":10,"receive":1,"day":30,"create_time":1539844178,"e_time":1542383999,"lower_id":0,"lower_time":0,"userid":35822,"nickname":"江月何时初照人","content":"","target_url":"","businessid":1664,"businessname":"太讨厌","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","status":0,"zan_count":0,"comment_count":0,"vimg_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/18/35822201810181427129953.jpg"],"vimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/18/35822201810181427129953.jpg"}]}
         */

        private InfoBean info;
        private SendListBean send_list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public SendListBean getSend_list() {
            return send_list;
        }

        public void setSend_list(SendListBean send_list) {
            this.send_list = send_list;
        }

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "info=" + info +
                    ", send_list=" + send_list +
                    '}';
        }

        public static class InfoBean {
            /**
             * send_sum : 10
             * receive_sum : 1
             */

            private String send_sum;
            private String receive_sum;

            public String getSend_sum() {
                return send_sum;
            }

            public void setSend_sum(String send_sum) {
                this.send_sum = send_sum;
            }

            public String getReceive_sum() {
                return receive_sum;
            }

            public void setReceive_sum(String receive_sum) {
                this.receive_sum = receive_sum;
            }

            @Override
            public String toString() {
                return "InfoBean{" +
                        "send_sum=" + send_sum +
                        ", receive_sum=" + receive_sum +
                        '}';
            }
        }

        public static class SendListBean {
            /**
             * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":1}
             * data : [{"voucherid":254,"type":1,"vname":"天天酷跑","vcontent":"满300元减30减","step":1,"condition":"300","money":"30","amount":10,"receive":1,"day":30,"create_time":1539844178,"e_time":1542383999,"lower_id":0,"lower_time":0,"userid":35822,"nickname":"江月何时初照人","content":"","target_url":"","businessid":1664,"businessname":"太讨厌","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","status":0,"zan_count":0,"comment_count":0,"vimg_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/18/35822201810181427129953.jpg"],"vimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/18/35822201810181427129953.jpg"}]
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
                return "SendListBean{" +
                        "pagenation=" + pagenation +
                        ", data=" + data +
                        '}';
            }

            public static class PagenationBean {
                /**
                 * page : 1
                 * pageSize : 10
                 * totalPage : 1
                 * totalCount : 1
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

            public static class DataBean implements Serializable {
                /**
                 * voucherid : 254
                 * type : 1
                 * vname : 天天酷跑
                 * vcontent : 满300元减30减
                 * step : 1
                 * condition : 300
                 * money : 30
                 * amount : 10
                 * receive : 1
                 * day : 30
                 * create_time : 1539844178
                 * e_time : 1542383999
                 * lower_id : 0
                 * lower_time : 0
                 * userid : 35822
                 * nickname : 江月何时初照人
                 * content :
                 * target_url :
                 * businessid : 1664
                 * businessname : 太讨厌
                 * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg
                 * status : 0
                 * zan_count : 0
                 * comment_count : 0
                 * vimg_arr : ["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/18/35822201810181427129953.jpg"]
                 * vimg : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/18/35822201810181427129953.jpg
                 */

                private int voucherid;
                private int type;
                private String vname;
                private String vcontent;
                private int step;
                private String condition;
                private String money;
                private int amount;
                private int receive;
                private int day;
                private long create_time;
                private long e_time;
                private int lower_id;
                private int lower_time;
                private int userid;
                private String nickname;
                private String content;
                private String target_url;
                private int businessid;
                private String businessname;
                private String avatar;
                private int status;
                private String zan_count;
                private String comment_count;
                private String vimg;
                private List<String> vimg_arr;

                public int getVoucherid() {
                    return voucherid;
                }

                public void setVoucherid(int voucherid) {
                    this.voucherid = voucherid;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getVname() {
                    return vname;
                }

                public void setVname(String vname) {
                    this.vname = vname;
                }

                public String getVcontent() {
                    return vcontent;
                }

                public void setVcontent(String vcontent) {
                    this.vcontent = vcontent;
                }

                public int getStep() {
                    return step;
                }

                public void setStep(int step) {
                    this.step = step;
                }

                public String getCondition() {
                    return condition;
                }

                public void setCondition(String condition) {
                    this.condition = condition;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public int getReceive() {
                    return receive;
                }

                public void setReceive(int receive) {
                    this.receive = receive;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public long getE_time() {
                    return e_time;
                }

                public void setE_time(long e_time) {
                    this.e_time = e_time;
                }

                public int getLower_id() {
                    return lower_id;
                }

                public void setLower_id(int lower_id) {
                    this.lower_id = lower_id;
                }

                public int getLower_time() {
                    return lower_time;
                }

                public void setLower_time(int lower_time) {
                    this.lower_time = lower_time;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getTarget_url() {
                    return target_url;
                }

                public void setTarget_url(String target_url) {
                    this.target_url = target_url;
                }

                public int getBusinessid() {
                    return businessid;
                }

                public void setBusinessid(int businessid) {
                    this.businessid = businessid;
                }

                public String getBusinessname() {
                    return businessname;
                }

                public void setBusinessname(String businessname) {
                    this.businessname = businessname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
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

                public String getVimg() {
                    return vimg;
                }

                public void setVimg(String vimg) {
                    this.vimg = vimg;
                }

                public List<String> getVimg_arr() {
                    return vimg_arr;
                }

                public void setVimg_arr(List<String> vimg_arr) {
                    this.vimg_arr = vimg_arr;
                }

                @Override
                public String toString() {
                    return "DataBean{" +
                            "voucherid=" + voucherid +
                            ", type=" + type +
                            ", vname='" + vname + '\'' +
                            ", vcontent='" + vcontent + '\'' +
                            ", step=" + step +
                            ", condition='" + condition + '\'' +
                            ", money='" + money + '\'' +
                            ", amount=" + amount +
                            ", receive=" + receive +
                            ", day=" + day +
                            ", create_time=" + create_time +
                            ", e_time=" + e_time +
                            ", lower_id=" + lower_id +
                            ", lower_time=" + lower_time +
                            ", userid=" + userid +
                            ", nickname='" + nickname + '\'' +
                            ", content='" + content + '\'' +
                            ", target_url='" + target_url + '\'' +
                            ", businessid=" + businessid +
                            ", businessname='" + businessname + '\'' +
                            ", avatar='" + avatar + '\'' +
                            ", status=" + status +
                            ", zan_count=" + zan_count +
                            ", comment_count=" + comment_count +
                            ", vimg='" + vimg + '\'' +
                            ", vimg_arr=" + vimg_arr +
                            '}';
                }
            }
        }
    }
}
