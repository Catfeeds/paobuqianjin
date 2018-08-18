package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/8/6.
 */

public class CollectSponsorResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":1,"totalPage":2,"totalCount":2},"data":[{"collectionid":8,"type":1,"userid":35822,"content":"","create_time":1533542975,"remark":"","businessid":1592,"circleid":0,"dynamicid":0,"businessname":"加贺见健介","businesslogo":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251431136463.jpg","tel":"13424156029","addra":"广东省/深圳市/南山区","address":"腾讯大厦"}]}
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
         * pagenation : {"page":1,"pageSize":1,"totalPage":2,"totalCount":2}
         * data : [{"collectionid":8,"type":1,"userid":35822,"content":"","create_time":1533542975,"remark":"","businessid":1592,"circleid":0,"dynamicid":0,"businessname":"加贺见健介","businesslogo":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251431136463.jpg","tel":"13424156029","addra":"广东省/深圳市/南山区","address":"腾讯大厦"}]
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
            return "DataBeanX{" +
                    "pagenation=" + pagenation +
                    ", data=" + data +
                    '}';
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 1
             * totalPage : 2
             * totalCount : 2
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

        public static class DataBean {
            /**
             * collectionid : 8
             * type : 1
             * userid : 35822
             * content :
             * create_time : 1533542975
             * remark :
             * businessid : 1592
             * circleid : 0
             * dynamicid : 0
             * businessname : 加贺见健介
             * businesslogo : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251431136463.jpg
             * tel : 13424156029
             * addra : 广东省/深圳市/南山区
             * address : 腾讯大厦
             */

            private int collectionid;
            private int type;
            private int userid;
            private String content;
            private int create_time;
            private String remark;
            private int businessid;
            private int circleid;
            private int dynamicid;
            private String businessname;
            private String businesslogo;
            private String tel;
            private String addra;
            private String address;

            public int getCollectionid() {
                return collectionid;
            }

            public void setCollectionid(int collectionid) {
                this.collectionid = collectionid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public int getBusinessid() {
                return businessid;
            }

            public void setBusinessid(int businessid) {
                this.businessid = businessid;
            }

            public int getCircleid() {
                return circleid;
            }

            public void setCircleid(int circleid) {
                this.circleid = circleid;
            }

            public int getDynamicid() {
                return dynamicid;
            }

            public void setDynamicid(int dynamicid) {
                this.dynamicid = dynamicid;
            }

            public String getBusinessname() {
                return businessname;
            }

            public void setBusinessname(String businessname) {
                this.businessname = businessname;
            }

            public String getBusinesslogo() {
                return businesslogo;
            }

            public void setBusinesslogo(String businesslogo) {
                this.businesslogo = businesslogo;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getAddra() {
                return addra;
            }

            public void setAddra(String addra) {
                this.addra = addra;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "collectionid=" + collectionid +
                        ", type=" + type +
                        ", userid=" + userid +
                        ", content='" + content + '\'' +
                        ", create_time=" + create_time +
                        ", remark='" + remark + '\'' +
                        ", businessid=" + businessid +
                        ", circleid=" + circleid +
                        ", dynamicid=" + dynamicid +
                        ", businessname='" + businessname + '\'' +
                        ", businesslogo='" + businesslogo + '\'' +
                        ", tel='" + tel + '\'' +
                        ", addra='" + addra + '\'' +
                        ", address='" + address + '\'' +
                        '}';
            }
        }
    }
}
