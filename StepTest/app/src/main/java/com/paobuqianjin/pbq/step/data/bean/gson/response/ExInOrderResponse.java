package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hqp on 19-1-3.
 */

public class ExInOrderResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":16},"data":[{"id":39,"comm_no":"COM20181231033922927","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546241962,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"3000.00","name":"风驰电掣大运摩托","comm_id":16,"content":"公户酒","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058365723.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058947636.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058335906.jpg"]},{"id":41,"comm_no":"COM20181231042159788","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546244519,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"3000.00","name":"风驰电掣大运摩托","comm_id":16,"content":"公户酒","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058365723.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058947636.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058335906.jpg"]},{"id":45,"comm_no":"COM20181231045224375","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546246344,"remark":"","order_finish_status":0,"express_price":"0.00","credit_total":10,"express_status":2,"number":1,"old_price":"10.00","name":"包邮包邮","comm_id":20,"content":"包邮","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101646408675.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101646701108.jpg"]},{"id":40,"comm_no":"COM20181231041115965","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546243875,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"3000.00","name":"风驰电掣大运摩托","comm_id":16,"content":"公户酒","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058365723.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058947636.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058335906.jpg"]},{"id":42,"comm_no":"COM20181231042739562","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546244859,"remark":"","order_finish_status":0,"express_price":"1.00","credit_total":10,"express_status":1,"number":1,"old_price":"30.00","name":"出租车好用","comm_id":17,"content":"出租车","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101624830108.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101624346973.jpg"]},{"id":44,"comm_no":"COM20181231044103921","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546245663,"remark":"","order_finish_status":0,"express_price":"9.99","credit_total":10,"express_status":1,"number":1,"old_price":"123.00","name":"非凡醒出售","comm_id":18,"content":"非凡醒","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101634704735.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101634103573.jpg"]},{"id":48,"comm_no":"COM20181231054432182","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546249472,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"10.00","name":"测试付款","comm_id":21,"content":"付款","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734173869.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734600197.jpg"]},{"id":49,"comm_no":"COM20181231055146269","order_type":1,"shipping_type":1,"order_status":2,"review_status":0,"close_order_status":0,"create_time":1546249906,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"10.00","name":"测试付款","comm_id":21,"content":"付款","order_status_text":"待收货","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734173869.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734600197.jpg"]},{"id":54,"comm_no":"COM20190103125152962","order_type":1,"shipping_type":1,"order_status":2,"review_status":0,"close_order_status":0,"create_time":1546447912,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":100,"express_status":1,"number":1,"old_price":"10.00","name":"呵呵","comm_id":22,"content":"1111","order_status_text":"待收货","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/3/35822201901030025374571.jpg"]},{"id":52,"comm_no":"COM20190102100440923","order_type":1,"shipping_type":1,"order_status":1,"review_status":0,"close_order_status":0,"create_time":1546437880,"remark":"","order_finish_status":0,"express_price":"0.00","credit_total":300,"express_status":2,"number":1,"old_price":"30.00","name":"路虎","comm_id":6,"content":"路上捡的叫路虎","order_status_text":"待发货","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"]}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":16}
         * data : [{"id":39,"comm_no":"COM20181231033922927","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546241962,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"3000.00","name":"风驰电掣大运摩托","comm_id":16,"content":"公户酒","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058365723.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058947636.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058335906.jpg"]},{"id":41,"comm_no":"COM20181231042159788","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546244519,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"3000.00","name":"风驰电掣大运摩托","comm_id":16,"content":"公户酒","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058365723.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058947636.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058335906.jpg"]},{"id":45,"comm_no":"COM20181231045224375","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546246344,"remark":"","order_finish_status":0,"express_price":"0.00","credit_total":10,"express_status":2,"number":1,"old_price":"10.00","name":"包邮包邮","comm_id":20,"content":"包邮","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101646408675.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101646701108.jpg"]},{"id":40,"comm_no":"COM20181231041115965","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546243875,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"3000.00","name":"风驰电掣大运摩托","comm_id":16,"content":"公户酒","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058365723.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058947636.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058335906.jpg"]},{"id":42,"comm_no":"COM20181231042739562","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546244859,"remark":"","order_finish_status":0,"express_price":"1.00","credit_total":10,"express_status":1,"number":1,"old_price":"30.00","name":"出租车好用","comm_id":17,"content":"出租车","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101624830108.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101624346973.jpg"]},{"id":44,"comm_no":"COM20181231044103921","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546245663,"remark":"","order_finish_status":0,"express_price":"9.99","credit_total":10,"express_status":1,"number":1,"old_price":"123.00","name":"非凡醒出售","comm_id":18,"content":"非凡醒","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101634704735.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101634103573.jpg"]},{"id":48,"comm_no":"COM20181231054432182","order_type":1,"shipping_type":1,"order_status":5,"review_status":0,"close_order_status":1,"create_time":1546249472,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"10.00","name":"测试付款","comm_id":21,"content":"付款","order_status_text":"已关闭","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734173869.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734600197.jpg"]},{"id":49,"comm_no":"COM20181231055146269","order_type":1,"shipping_type":1,"order_status":2,"review_status":0,"close_order_status":0,"create_time":1546249906,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":10,"express_status":1,"number":1,"old_price":"10.00","name":"测试付款","comm_id":21,"content":"付款","order_status_text":"待收货","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734173869.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101734600197.jpg"]},{"id":54,"comm_no":"COM20190103125152962","order_type":1,"shipping_type":1,"order_status":2,"review_status":0,"close_order_status":0,"create_time":1546447912,"remark":"","order_finish_status":0,"express_price":"10.00","credit_total":100,"express_status":1,"number":1,"old_price":"10.00","name":"呵呵","comm_id":22,"content":"1111","order_status_text":"待收货","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/3/35822201901030025374571.jpg"]},{"id":52,"comm_no":"COM20190102100440923","order_type":1,"shipping_type":1,"order_status":1,"review_status":0,"close_order_status":0,"create_time":1546437880,"remark":"","order_finish_status":0,"express_price":"0.00","credit_total":300,"express_status":2,"number":1,"old_price":"30.00","name":"路虎","comm_id":6,"content":"路上捡的叫路虎","order_status_text":"待发货","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"]}]
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
             * totalPage : 2
             * totalCount : 16
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
             * id : 39
             * comm_no : COM20181231033922927
             * order_type : 1
             * shipping_type : 1
             * order_status : 5
             * review_status : 0
             * close_order_status : 1
             * create_time : 1546241962
             * remark :
             * order_finish_status : 0
             * express_price : 10.00
             * credit_total : 10
             * express_status : 1
             * number : 1
             * old_price : 3000.00
             * name : 风驰电掣大运摩托
             * comm_id : 16
             * content : 公户酒
             * order_status_text : 已关闭
             * img_arr : ["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058365723.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058947636.jpg","http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101058335906.jpg"]
             */

            private String id;
            private String comm_no;
            private int order_type;
            private int shipping_type;
            private int order_status;
            private int review_status;
            private int close_order_status;
            private int create_time;
            private String remark;
            private int order_finish_status;
            private String express_price;
            private String credit_total;
            private int express_status;
            private String number;
            private String old_price;
            private String name;
            private String comm_id;
            private String content;
            private String order_status_text;
            private List<String> img_arr;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getComm_no() {
                return comm_no;
            }

            public void setComm_no(String comm_no) {
                this.comm_no = comm_no;
            }

            public int getOrder_type() {
                return order_type;
            }

            public void setOrder_type(int order_type) {
                this.order_type = order_type;
            }

            public int getShipping_type() {
                return shipping_type;
            }

            public void setShipping_type(int shipping_type) {
                this.shipping_type = shipping_type;
            }

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
            }

            public int getReview_status() {
                return review_status;
            }

            public void setReview_status(int review_status) {
                this.review_status = review_status;
            }

            public int getClose_order_status() {
                return close_order_status;
            }

            public void setClose_order_status(int close_order_status) {
                this.close_order_status = close_order_status;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getOrder_finish_status() {
                return order_finish_status;
            }

            public void setOrder_finish_status(int order_finish_status) {
                this.order_finish_status = order_finish_status;
            }

            public String getExpress_price() {
                return express_price;
            }

            public void setExpress_price(String express_price) {
                this.express_price = express_price;
            }

            public String getCredit_total() {
                return credit_total;
            }

            public void setCredit_total(String credit_total) {
                this.credit_total = credit_total;
            }

            public int getExpress_status() {
                return express_status;
            }

            public void setExpress_status(int express_status) {
                this.express_status = express_status;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getOld_price() {
                return old_price;
            }

            public void setOld_price(String old_price) {
                this.old_price = old_price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getComm_id() {
                return comm_id;
            }

            public void setComm_id(String comm_id) {
                this.comm_id = comm_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getOrder_status_text() {
                return order_status_text;
            }

            public void setOrder_status_text(String order_status_text) {
                this.order_status_text = order_status_text;
            }

            public List<String> getImg_arr() {
                return img_arr;
            }

            public void setImg_arr(List<String> img_arr) {
                this.img_arr = img_arr;
            }
        }
    }
}
