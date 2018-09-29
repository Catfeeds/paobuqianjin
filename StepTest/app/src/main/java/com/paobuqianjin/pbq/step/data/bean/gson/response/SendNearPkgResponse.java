package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/9/8.
 * 附近红包历史记录
 */

public class SendNearPkgResponse {

    /**
     * error : 0
     * message : 获取红包历史记录成功
     * data : {"count_info":{"send_total":"226.00","send_count":1450,"recice_total":97},"redpacket_list":{"pagenation":{"page":1,"pageSize":10,"totalPage":4,"totalCount":32},"data":[{"money":"10.00","number":100,"red_id":1570,"day":1,"red_content":"刚回家","create_time":1537428584,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1537372800,"e_time":1537459200,"circleid":101569,"circle_name":"不健康","circle_pwd":"","business_name":"疼训氪金有限公司","target_url":"","red_name":"回家","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":10,"red_id":1561,"day":10,"red_content":"好几节课快快快","create_time":1536392050,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1536336000,"e_time":1537200000,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"疼训氪金有限公司","target_url":"","red_name":"法国哈哈哈","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1551,"day":10,"red_content":"888888","create_time":1536199792,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"天颐湖","target_url":"","red_name":"88888","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1550,"day":10,"red_content":"8888888","create_time":1536199563,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"天颐湖","target_url":"","red_name":"thjjkkb","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"66.00","number":10,"red_id":1549,"day":1,"red_content":"会快快快","create_time":1536197610,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1536249600,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"好几块","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1548,"day":10,"red_content":"如果好几节课快快快","create_time":1536197457,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"刚回家","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1547,"day":10,"red_content":"会快快快","create_time":1536196301,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"一会回家","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":3,"red_id":1534,"day":1,"red_content":"","create_time":1535766603,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1535731200,"e_time":1535817600,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"测试百度","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":1,"red_id":1532,"day":1,"red_content":"","create_time":1535612894,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535558400,"e_time":1535644800,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"新店铺","target_url":"","red_name":"疼训大厦","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":5,"red_id":1531,"day":10,"red_content":"","create_time":1535526768,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535472000,"e_time":1536336000,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"新店铺","target_url":"","red_name":"新店开张","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png"],"zan_count":0,"comment_count":0,"red_status":0}]}}
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
         * count_info : {"send_total":"226.00","send_count":1450,"recice_total":97}
         * redpacket_list : {"pagenation":{"page":1,"pageSize":10,"totalPage":4,"totalCount":32},"data":[{"money":"10.00","number":100,"red_id":1570,"day":1,"red_content":"刚回家","create_time":1537428584,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1537372800,"e_time":1537459200,"circleid":101569,"circle_name":"不健康","circle_pwd":"","business_name":"疼训氪金有限公司","target_url":"","red_name":"回家","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":10,"red_id":1561,"day":10,"red_content":"好几节课快快快","create_time":1536392050,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1536336000,"e_time":1537200000,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"疼训氪金有限公司","target_url":"","red_name":"法国哈哈哈","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1551,"day":10,"red_content":"888888","create_time":1536199792,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"天颐湖","target_url":"","red_name":"88888","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1550,"day":10,"red_content":"8888888","create_time":1536199563,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"天颐湖","target_url":"","red_name":"thjjkkb","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"66.00","number":10,"red_id":1549,"day":1,"red_content":"会快快快","create_time":1536197610,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1536249600,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"好几块","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1548,"day":10,"red_content":"如果好几节课快快快","create_time":1536197457,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"刚回家","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1547,"day":10,"red_content":"会快快快","create_time":1536196301,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"一会回家","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":3,"red_id":1534,"day":1,"red_content":"","create_time":1535766603,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1535731200,"e_time":1535817600,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"测试百度","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":1,"red_id":1532,"day":1,"red_content":"","create_time":1535612894,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535558400,"e_time":1535644800,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"新店铺","target_url":"","red_name":"疼训大厦","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":5,"red_id":1531,"day":10,"red_content":"","create_time":1535526768,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535472000,"e_time":1536336000,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"新店铺","target_url":"","red_name":"新店开张","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png"],"zan_count":0,"comment_count":0,"red_status":0}]}
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
             * send_total : 226.00
             * send_count : 1450
             * recice_total : 97
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
             * pagenation : {"page":1,"pageSize":10,"totalPage":4,"totalCount":32}
             * data : [{"money":"10.00","number":100,"red_id":1570,"day":1,"red_content":"刚回家","create_time":1537428584,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1537372800,"e_time":1537459200,"circleid":101569,"circle_name":"不健康","circle_pwd":"","business_name":"疼训氪金有限公司","target_url":"","red_name":"回家","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":10,"red_id":1561,"day":10,"red_content":"好几节课快快快","create_time":1536392050,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1666,"s_time":1536336000,"e_time":1537200000,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"疼训氪金有限公司","target_url":"","red_name":"法国哈哈哈","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1551,"day":10,"red_content":"888888","create_time":1536199792,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"天颐湖","target_url":"","red_name":"88888","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1550,"day":10,"red_content":"8888888","create_time":1536199563,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1667,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"天颐湖","target_url":"","red_name":"thjjkkb","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301623979575.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"66.00","number":10,"red_id":1549,"day":1,"red_content":"会快快快","create_time":1536197610,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1536249600,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"好几块","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1548,"day":10,"red_content":"如果好几节课快快快","create_time":1536197457,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"刚回家","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"30.00","number":5,"red_id":1547,"day":10,"red_content":"会快快快","create_time":1536196301,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1536163200,"e_time":1537027200,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"一会回家","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":3,"red_id":1534,"day":1,"red_content":"","create_time":1535766603,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1670,"s_time":1535731200,"e_time":1535817600,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"腾讯大厦","target_url":"","red_name":"测试百度","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/9/1/35822201809010947390547.jpg"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":1,"red_id":1532,"day":1,"red_content":"","create_time":1535612894,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535558400,"e_time":1535644800,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"新店铺","target_url":"","red_name":"疼训大厦","red_step":1,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png"],"zan_count":0,"comment_count":0,"red_status":0},{"money":"0.00","number":5,"red_id":1531,"day":10,"red_content":"","create_time":1535526768,"user_nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","businessid":1663,"s_time":1535472000,"e_time":1536336000,"circleid":0,"circle_name":null,"circle_pwd":"","business_name":"新店铺","target_url":"","red_name":"新店开张","red_step":10000,"map_img":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png","map_img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/29/35822201808291511399763.png"],"zan_count":0,"comment_count":0,"red_status":0}]
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
                 * totalCount : 32
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
                /**
                 * money : 10.00
                 * number : 100
                 * red_id : 1570
                 * day : 1
                 * red_content : 刚回家
                 * create_time : 1537428584
                 * user_nickname : 江月何时初照人
                 * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg
                 * businessid : 1666
                 * s_time : 1537372800
                 * e_time : 1537459200
                 * circleid : 101569
                 * circle_name : 不健康
                 * circle_pwd :
                 * business_name : 疼训氪金有限公司
                 * target_url :
                 * red_name : 回家
                 * red_step : 10000
                 * map_img : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg
                 * map_img_arr : ["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/30/35822201808301506825244.jpg"]
                 * zan_count : 0
                 * comment_count : 0
                 * red_status : 0
                 * age_min: 0,
                 * age_max: 0,
                 * education: 0,
                 * distance: 10000,
                 * country: "",
                 * province: "",
                 * county: "",
                 * village: "",
                 * city: "深圳市",
                 * city_code: "440305",
                 * rlongitude: "113.930740",
                 * rlatitude: "22.548870",
                 * sex: 0,
                 */

                private int age_min;
                private int age_max;
                private int education;

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                private int sex;

                public int getAge_min() {
                    return age_min;
                }

                public void setAge_min(int age_min) {
                    this.age_min = age_min;
                }

                public int getAge_max() {
                    return age_max;
                }

                public void setAge_max(int age_max) {
                    this.age_max = age_max;
                }

                public int getEducation() {
                    return education;
                }

                public void setEducation(int education) {
                    this.education = education;
                }

                public String getDistance() {
                    return distance;
                }

                public void setDistance(String distance) {
                    this.distance = distance;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getVillage() {
                    return village;
                }

                public void setVillage(String village) {
                    this.village = village;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public double getRlongitude() {
                    return rlongitude;
                }

                public void setRlongitude(double rlongitude) {
                    this.rlongitude = rlongitude;
                }

                public double getRlatitude() {
                    return rlatitude;
                }

                public void setRlatitude(double rlatitude) {
                    this.rlatitude = rlatitude;
                }

                private String distance;
                private String country;
                private String province;
                private String village;
                private String city;
                private String city_code;
                private double rlongitude;
                private double rlatitude;
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
                private String circleid;
                private String circle_name;
                private String circle_pwd;
                private String business_name;
                private String target_url;
                private String red_name;
                private String red_step;
                private String map_img;
                private String zan_count;
                private String comment_count;
                private String red_status;
                private List<String> map_img_arr;

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

                public int getBusinessid() {
                    return businessid;
                }

                public void setBusinessid(int businessid) {
                    this.businessid = businessid;
                }

                public long getS_time() {
                    return s_time;
                }

                public void setS_time(long s_time) {
                    this.s_time = s_time;
                }

                public long getE_time() {
                    return e_time;
                }

                public void setE_time(long e_time) {
                    this.e_time = e_time;
                }

                public String getCircleid() {
                    return circleid;
                }

                public void setCircleid(String circleid) {
                    this.circleid = circleid;
                }

                public String getCircle_name() {
                    return circle_name;
                }

                public void setCircle_name(String circle_name) {
                    this.circle_name = circle_name;
                }

                public String getCircle_pwd() {
                    return circle_pwd;
                }

                public void setCircle_pwd(String circle_pwd) {
                    this.circle_pwd = circle_pwd;
                }

                public String getBusiness_name() {
                    return business_name;
                }

                public void setBusiness_name(String business_name) {
                    this.business_name = business_name;
                }

                public String getTarget_url() {
                    return target_url;
                }

                public void setTarget_url(String target_url) {
                    this.target_url = target_url;
                }

                public String getRed_name() {
                    return red_name;
                }

                public void setRed_name(String red_name) {
                    this.red_name = red_name;
                }

                public String getRed_step() {
                    return red_step;
                }

                public void setRed_step(String red_step) {
                    this.red_step = red_step;
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

                public List<String> getMap_img_arr() {
                    return map_img_arr;
                }

                public void setMap_img_arr(List<String> map_img_arr) {
                    this.map_img_arr = map_img_arr;
                }

                @Override
                public String toString() {
                    return "DataBean{" +
                            "money='" + money + '\'' +
                            ", number=" + number +
                            ", red_id='" + red_id + '\'' +
                            ", day=" + day +
                            ", red_content='" + red_content + '\'' +
                            ", create_time=" + create_time +
                            ", user_nickname='" + user_nickname + '\'' +
                            ", avatar='" + avatar + '\'' +
                            ", businessid=" + businessid +
                            ", s_time=" + s_time +
                            ", e_time=" + e_time +
                            ", circleid='" + circleid + '\'' +
                            ", circle_name='" + circle_name + '\'' +
                            ", circle_pwd='" + circle_pwd + '\'' +
                            ", business_name='" + business_name + '\'' +
                            ", target_url='" + target_url + '\'' +
                            ", red_name='" + red_name + '\'' +
                            ", red_step='" + red_step + '\'' +
                            ", map_img='" + map_img + '\'' +
                            ", zan_count='" + zan_count + '\'' +
                            ", comment_count='" + comment_count + '\'' +
                            ", red_status='" + red_status + '\'' +
                            ", map_img_arr=" + map_img_arr +
                            '}';
                }
            }
        }
    }
}
