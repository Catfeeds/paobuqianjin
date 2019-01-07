package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/12/29.
 */

public class ExPublistResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"id":8,"userid":35905,"name":"哦哦哦","content":"股海护航成本价","old_price":"556.00","credit":100000,"number":1,"express_status":1,"express_price":"12.00","status":1,"create_time":1546055562,"img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081152796476.jpg"]},{"id":7,"userid":35905,"name":"好几块","content":"么啦啦啦啦上课","old_price":"30.00","credit":100,"number":1,"express_status":2,"express_price":"0.00","status":1,"create_time":1546055247,"img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081145950904.jpg"]},{"id":6,"userid":35905,"name":"路虎","content":"路上捡的叫路虎","old_price":"30.00","credit":300,"number":1,"express_status":2,"express_price":"0.00","status":1,"create_time":1546055090,"img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"]},{"id":5,"userid":35905,"name":"路虎","content":"路上捡的车","old_price":"1223.00","credit":1000,"number":1,"express_status":1,"express_price":"10.00","status":1,"create_time":1546053166,"img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081111520394.jpg"]}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"id":8,"userid":35905,"name":"哦哦哦","content":"股海护航成本价","old_price":"556.00","credit":100000,"number":1,"express_status":1,"express_price":"12.00","status":1,"create_time":1546055562,"img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081152796476.jpg"]},{"id":7,"userid":35905,"name":"好几块","content":"么啦啦啦啦上课","old_price":"30.00","credit":100,"number":1,"express_status":2,"express_price":"0.00","status":1,"create_time":1546055247,"img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081145950904.jpg"]},{"id":6,"userid":35905,"name":"路虎","content":"路上捡的叫路虎","old_price":"30.00","credit":300,"number":1,"express_status":2,"express_price":"0.00","status":1,"create_time":1546055090,"img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"]},{"id":5,"userid":35905,"name":"路虎","content":"路上捡的车","old_price":"1223.00","credit":1000,"number":1,"express_status":1,"express_price":"10.00","status":1,"create_time":1546053166,"img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081111520394.jpg"]}]
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

        public static class DataBean implements Serializable {
            /**
             * id : 8
             * userid : 35905
             * name : 哦哦哦
             * content : 股海护航成本价
             * old_price : 556.00
             * credit : 100000
             * number : 1
             * express_status : 1
             * express_price : 12.00
             * status : 1
             * create_time : 1546055562
             * img_arr : ["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081152796476.jpg"]
             */

            private int id;
            private int userid;
            private String name;
            private String content;
            private String old_price;
            private int credit;
            private int number;
            private int express_status;
            private String express_price;
            private int status;
            private int create_time;
            private List<String> img_arr;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getOld_price() {
                return old_price;
            }

            public void setOld_price(String old_price) {
                this.old_price = old_price;
            }

            public int getCredit() {
                return credit;
            }

            public void setCredit(int credit) {
                this.credit = credit;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getExpress_status() {
                return express_status;
            }

            public void setExpress_status(int express_status) {
                this.express_status = express_status;
            }

            public String getExpress_price() {
                return express_price;
            }

            public void setExpress_price(String express_price) {
                this.express_price = express_price;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
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
