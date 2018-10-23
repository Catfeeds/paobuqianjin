package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/10/22.
 */

public class ConsumRedInfoResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"id":355,"vid":258,"userid":35901,"is_time":1539939356,"nickname":"咔","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/16/35901201810161006501862.jpg","vcontent":"满180元减30减"},{"id":354,"vid":258,"userid":35822,"is_time":1539938833,"nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","vcontent":"满180元减30减"}]}
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
        return "ConsumRedInfoResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : [{"id":355,"vid":258,"userid":35901,"is_time":1539939356,"nickname":"咔","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/16/35901201810161006501862.jpg","vcontent":"满180元减30减"},{"id":354,"vid":258,"userid":35822,"is_time":1539938833,"nickname":"江月何时初照人","avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/8/24/35822201808241502229844.jpg","vcontent":"满180元减30减"}]
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
             * pageSize : 10
             * totalPage : 1
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
             * id : 355
             * vid : 258
             * userid : 35901
             * is_time : 1539939356
             * nickname : 咔
             * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/10/16/35901201810161006501862.jpg
             * vcontent : 满180元减30减
             */

            private int id;
            private int vid;
            private int userid;
            private long is_time;
            private String nickname;
            private String avatar;
            private String vcontent;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVid() {
                return vid;
            }

            public void setVid(int vid) {
                this.vid = vid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public long getIs_time() {
                return is_time;
            }

            public void setIs_time(long is_time) {
                this.is_time = is_time;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getVcontent() {
                return vcontent;
            }

            public void setVcontent(String vcontent) {
                this.vcontent = vcontent;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", vid=" + vid +
                        ", userid=" + userid +
                        ", is_time=" + is_time +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", vcontent='" + vcontent + '\'' +
                        '}';
            }
        }
    }
}
