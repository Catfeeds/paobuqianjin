package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2019/1/7.
 */

public class ExWantResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":2,"pageSize":3,"totalPage":5,"totalCount":14},"data":[{"id":18,"userid":35822,"name":"非凡醒出售","old_price":"123.00","credit":10,"number":1,"express_status":1,"status":2,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101634704735.jpg"},{"id":25,"userid":35875,"name":"挥泪大甩卖","old_price":"15.00","credit":30,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/4/35875201901041954777522.jpg"}]}
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
         * pagenation : {"page":2,"pageSize":3,"totalPage":5,"totalCount":14}
         * data : [{"id":18,"userid":35822,"name":"非凡醒出售","old_price":"123.00","credit":10,"number":1,"express_status":1,"status":2,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101634704735.jpg"},{"id":25,"userid":35875,"name":"挥泪大甩卖","old_price":"15.00","credit":30,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/4/35875201901041954777522.jpg"}]
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
             * page : 2
             * pageSize : 3
             * totalPage : 5
             * totalCount : 14
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
            public int getIs_select() {
                return is_select;
            }

            public void setIs_select(int is_select) {
                this.is_select = is_select;
            }

            public int getIs_edit() {
                return is_edit;
            }

            public void setIs_edit(int is_edit) {
                this.is_edit = is_edit;
            }

            /**
             * id : 18
             * userid : 35822
             * name : 非凡醒出售
             * old_price : 123.00
             * credit : 10
             * number : 1
             * express_status : 1
             * status : 2
             * img_url : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101634704735.jpg
             * is_select:
             * is_edit:
             */
            private int is_select;
            private int is_edit;
            private int id;
            private String userid;
            private String name;
            private String old_price;
            private String credit;
            private String number;
            private int express_status;
            private int status;
            private String img_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOld_price() {
                return old_price;
            }

            public void setOld_price(String old_price) {
                this.old_price = old_price;
            }

            public String getCredit() {
                return credit;
            }

            public void setCredit(String credit) {
                this.credit = credit;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public int getExpress_status() {
                return express_status;
            }

            public void setExpress_status(int express_status) {
                this.express_status = express_status;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}
