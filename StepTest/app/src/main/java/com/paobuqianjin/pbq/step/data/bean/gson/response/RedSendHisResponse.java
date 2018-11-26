package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/8/20.
 */

public class RedSendHisResponse {

    /**
     * error : 0
     * message : 获取历史记录成功
     * data : {"count_info":{"send_total":"230.00","send_count":140,"recice_total":19},"redpacket_list":{"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":3},"data":[{"money":"100.00","voucherid":242,"number":10,"receive_num":5,"red_id":615,"map_content":"呱太","create_time":1537441224,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"circleid":101557,"circle_pwd":"123456","target_url":"","circle_name":"呱哒环境","business_name":"疼训氪金有限公司","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0},{"money":"30.00","number":30,"receive_num":7,"red_id":614,"map_content":"回来","create_time":1537357329,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"circleid":101603,"circle_pwd":"","target_url":"","circle_name":"aasdddddd","business_name":"疼训氪金有限公司","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0}]}}
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
         * count_info : {"send_total":"230.00","send_count":140,"recice_total":19}
         * redpacket_list : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":3},"data":[{"money":"100.00","number":10,"receive_num":5,"red_id":615,"map_content":"呱太","create_time":1537441224,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"circleid":101557,"circle_pwd":"123456","target_url":"","circle_name":"呱哒环境","business_name":"疼训氪金有限公司","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0},{"money":"30.00","number":30,"receive_num":7,"red_id":614,"map_content":"回来","create_time":1537357329,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"circleid":101603,"circle_pwd":"","target_url":"","circle_name":"aasdddddd","business_name":"疼训氪金有限公司","lower_id":35822,"vname":"开业大酬宾","vnumber":10,"vday":1,"vcondition":"300.00","vmoney":"30.00","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0}]}
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
             * send_total : 230.00
             * send_count : 140
             * recice_total : 19
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
             * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":3}
             * data : [{"money":"100.00","voucherid":242,"number":10,"receive_num":5,"red_id":615,"map_content":"呱太","create_time":1537441224,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"circleid":101557,"circle_pwd":"123456","target_url":"","circle_name":"呱哒环境","business_name":"疼训氪金有限公司","lower_id":35822,"vname":"开业大酬宾","vnumber":10,"vday":1,"vcondition":"300.00","vmoney":"30.00","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0},{"money":"30.00","number":30,"receive_num":7,"red_id":614,"map_content":"回来","create_time":1537357329,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"circleid":101603,"circle_pwd":"","target_url":"","circle_name":"aasdddddd","business_name":"疼训氪金有限公司","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","zan_count":0,"comment_count":0}]
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

            public static class DataBean implements Serializable {
                public int getPay_credit() {
                    return pay_credit;
                }

                public void setPay_credit(int pay_credit) {
                    this.pay_credit = pay_credit;
                }

                /**
                 * money : 100.00
                 * voucherid:242
                 * number : 10
                 * receive_num : 5
                 * red_id : 615
                 * map_content : 呱太
                 * create_time : 1537441224
                 * user_nickname : 江月何时初照人
                 * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg
                 * businessid : 1666
                 * circleid : 101557
                 * circle_pwd : 123456
                 * target_url :
                 * circle_name : 呱哒环境
                 * business_name : 疼训氪金有限公司
                 * vname:开业大酬宾
                 * vnumber:10
                 * vday:1
                 * vmoney:30.00
                 * "type":2,
                 * "pay_credit":21
                 * vcondition:300.00
                 * lower_id:0 (0正常，大于0表示已经上架)
                 * map_img_arr : ["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"]
                 * map_img : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg
                 * zan_count : 0
                 * comment_count : 0
                 */

                private int pay_credit;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                private int type;
                private int lower_id;
                private String vname;
                private int vday;
                private String vcondition;
                private String vmoney;
                private String money;
                private String number;
                private String receive_num;
                private String red_id;
                private String map_content;
                private long create_time;
                private String user_nickname;
                private String avatar;
                private String businessid;
                private String circleid;
                private String circle_pwd;
                private String target_url;
                private String circle_name;
                private String business_name;
                private String map_img;
                private String zan_count;
                private String comment_count;
                private List<String> map_img_arr;

                public int getVoucherid() {
                    return voucherid;
                }

                public void setVoucherid(int voucherid) {
                    this.voucherid = voucherid;
                }

                private int voucherid;

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
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

                public String getBusinessid() {
                    return businessid;
                }

                public void setBusinessid(String businessid) {
                    this.businessid = businessid;
                }

                public String getCircleid() {
                    return circleid;
                }

                public void setCircleid(String circleid) {
                    this.circleid = circleid;
                }

                public String getCircle_pwd() {
                    return circle_pwd;
                }

                public void setCircle_pwd(String circle_pwd) {
                    this.circle_pwd = circle_pwd;
                }

                public String getTarget_url() {
                    return target_url;
                }

                public void setTarget_url(String target_url) {
                    this.target_url = target_url;
                }

                public String getCircle_name() {
                    return circle_name;
                }

                public void setCircle_name(String circle_name) {
                    this.circle_name = circle_name;
                }

                public String getBusiness_name() {
                    return business_name;
                }

                public void setBusiness_name(String business_name) {
                    this.business_name = business_name;
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

                public List<String> getMap_img_arr() {
                    return map_img_arr;
                }

                public void setMap_img_arr(List<String> map_img_arr) {
                    this.map_img_arr = map_img_arr;
                }

                public int getLower_id() {
                    return lower_id;
                }

                public void setLower_id(int lower_id) {
                    this.lower_id = lower_id;
                }

                public String getVname() {
                    return vname;
                }

                public void setVname(String vname) {
                    this.vname = vname;
                }

                public int getVday() {
                    return vday;
                }

                public void setVday(int vday) {
                    this.vday = vday;
                }

                public String getVcondition() {
                    return vcondition;
                }

                public void setVcondition(String vcondition) {
                    this.vcondition = vcondition;
                }

                public String getVmoney() {
                    return vmoney;
                }

                public void setVmoney(String vmoney) {
                    this.vmoney = vmoney;
                }

                @Override
                public String toString() {
                    return "DataBean{" +
                            "pay_credit=" + pay_credit +
                            ", type=" + type +
                            ", lower_id=" + lower_id +
                            ", vname='" + vname + '\'' +
                            ", vday=" + vday +
                            ", vcondition='" + vcondition + '\'' +
                            ", vmoney='" + vmoney + '\'' +
                            ", money='" + money + '\'' +
                            ", number='" + number + '\'' +
                            ", receive_num='" + receive_num + '\'' +
                            ", red_id='" + red_id + '\'' +
                            ", map_content='" + map_content + '\'' +
                            ", create_time=" + create_time +
                            ", user_nickname='" + user_nickname + '\'' +
                            ", avatar='" + avatar + '\'' +
                            ", businessid='" + businessid + '\'' +
                            ", circleid='" + circleid + '\'' +
                            ", circle_pwd='" + circle_pwd + '\'' +
                            ", target_url='" + target_url + '\'' +
                            ", circle_name='" + circle_name + '\'' +
                            ", business_name='" + business_name + '\'' +
                            ", map_img='" + map_img + '\'' +
                            ", zan_count='" + zan_count + '\'' +
                            ", comment_count='" + comment_count + '\'' +
                            ", map_img_arr=" + map_img_arr +
                            ", voucherid=" + voucherid +
                            '}';
                }
            }
        }
    }
}
