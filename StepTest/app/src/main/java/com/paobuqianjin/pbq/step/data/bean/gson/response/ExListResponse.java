package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2019/1/2.
 */

public class ExListResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":20,"totalPage":1,"totalCount":8},"data":[{"id":19,"userid":35822,"name":"好几家","old_price":"10.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101645196587.jpg"},{"id":15,"userid":35822,"name":"城堡出售","old_price":"33330.00","credit":10000,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101055141386.jpg"},{"id":13,"userid":35828,"name":"测试宝宝","old_price":"100.00","credit":100,"number":1,"express_status":0,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/7E2D53BF-FB26-4AEC-9367-3687723AC2AB.jpg"},{"id":12,"userid":35828,"name":"你说什么都可以","old_price":"20.00","credit":100,"number":1,"express_status":0,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/F6884119-BE59-423F-BF38-566BE04416B4.jpg"},{"id":10,"userid":35828,"name":"哈哈大笑就很好","old_price":"200.00","credit":10000,"number":1,"express_status":0,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/4D4A4C6A-72D2-4A1D-BD4A-FA232025DEEA.jpg"},{"id":6,"userid":35905,"name":"路虎","old_price":"30.00","credit":300,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"},{"id":5,"userid":35905,"name":"路虎","old_price":"1223.00","credit":1000,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081111520394.jpg"},{"id":3,"userid":11241,"name":"青年","old_price":"0.00","credit":20,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/5/35822201901051046218973.jpg"}]}
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
         * pagenation : {"page":1,"pageSize":20,"totalPage":1,"totalCount":8}
         * data : [{"id":19,"userid":35822,"name":"好几家","old_price":"10.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101645196587.jpg"},{"id":15,"userid":35822,"name":"城堡出售","old_price":"33330.00","credit":10000,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101055141386.jpg"},{"id":13,"userid":35828,"name":"测试宝宝","old_price":"100.00","credit":100,"number":1,"express_status":0,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/7E2D53BF-FB26-4AEC-9367-3687723AC2AB.jpg"},{"id":12,"userid":35828,"name":"你说什么都可以","old_price":"20.00","credit":100,"number":1,"express_status":0,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/F6884119-BE59-423F-BF38-566BE04416B4.jpg"},{"id":10,"userid":35828,"name":"哈哈大笑就很好","old_price":"200.00","credit":10000,"number":1,"express_status":0,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/4D4A4C6A-72D2-4A1D-BD4A-FA232025DEEA.jpg"},{"id":6,"userid":35905,"name":"路虎","old_price":"30.00","credit":300,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"},{"id":5,"userid":35905,"name":"路虎","old_price":"1223.00","credit":1000,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081111520394.jpg"},{"id":3,"userid":11241,"name":"青年","old_price":"0.00","credit":20,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/5/35822201901051046218973.jpg"}]
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
             * pageSize : 20
             * totalPage : 1
             * totalCount : 8
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
             * id : 19
             * userid : 35822
             * name : 好几家
             * old_price : 10.00
             * credit : 10
             * number : 1
             * express_status : 1
             * status : 1
             * img_url : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101645196587.jpg
             */

            private int id;
            private int userid;
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
