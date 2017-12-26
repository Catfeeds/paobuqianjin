package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class DynamicResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":2,"pageSize":4,"totalPage":2,"totalCount":8},"data":[{"id":5,"userid":1,"dynamic":"看到刘亦菲这些照片，我要哭了","images":"a:1:{i:0;s:65:\"https://wx4.sinaimg.cn/mw690/005WPY4oly1fmh3dxx7m9j30hs0qomyz.jpg\";}","city":"东莞","vote":100,"creattime":1513393712},{"id":6,"userid":1,"dynamic":"5位明星想要掩盖的身体缺陷，他摔倒失去蛋蛋，她天生没有子宫！","images":"a:1:{i:0;s:65:\"https://wx2.sinaimg.cn/mw690/005WPY4oly1fmh35ur98jj30dg09cdfy.jpg\";}","city":"深圳","vote":100,"creattime":1513393712},{"id":7,"userid":1,"dynamic":"赵丽颖被各卫视拒上跨年，现又被春晚拒绝，曝光的真相你能接受吗？","images":"a:1:{i:0;s:65:\"https://wx4.sinaimg.cn/mw690/005WPY4oly1fmhu2134a0j30ey0acq3a.jpg\";}","city":"广州","vote":100,"creattime":1513393712},{"id":8,"userid":1,"dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":"a:4:{i:0;s:65:\"https://wx1.sinaimg.cn/mw690/48e837eely1fmhxeqwby0j22ds1sg157.jpg\";i:1;s:65:\"https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vxqcslj30i90euwf1.jpg\";i:2;s:65:\"https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg\";i:3;s:65:\"https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vyclzej30ii0i974g.jpg\";}","city":"深圳","vote":100,"creattime":1513393712}]}
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
         * pagenation : {"page":2,"pageSize":4,"totalPage":2,"totalCount":8}
         * data : [{"id":5,"userid":1,"dynamic":"看到刘亦菲这些照片，我要哭了","images":"a:1:{i:0;s:65:\"https://wx4.sinaimg.cn/mw690/005WPY4oly1fmh3dxx7m9j30hs0qomyz.jpg\";}","city":"东莞","vote":100,"creattime":1513393712},{"id":6,"userid":1,"dynamic":"5位明星想要掩盖的身体缺陷，他摔倒失去蛋蛋，她天生没有子宫！","images":"a:1:{i:0;s:65:\"https://wx2.sinaimg.cn/mw690/005WPY4oly1fmh35ur98jj30dg09cdfy.jpg\";}","city":"深圳","vote":100,"creattime":1513393712},{"id":7,"userid":1,"dynamic":"赵丽颖被各卫视拒上跨年，现又被春晚拒绝，曝光的真相你能接受吗？","images":"a:1:{i:0;s:65:\"https://wx4.sinaimg.cn/mw690/005WPY4oly1fmhu2134a0j30ey0acq3a.jpg\";}","city":"广州","vote":100,"creattime":1513393712},{"id":8,"userid":1,"dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":"a:4:{i:0;s:65:\"https://wx1.sinaimg.cn/mw690/48e837eely1fmhxeqwby0j22ds1sg157.jpg\";i:1;s:65:\"https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vxqcslj30i90euwf1.jpg\";i:2;s:65:\"https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg\";i:3;s:65:\"https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vyclzej30ii0i974g.jpg\";}","city":"深圳","vote":100,"creattime":1513393712}]
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
             * pageSize : 4
             * totalPage : 2
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

        public static class DataBean {
            /**
             * id : 5
             * userid : 1
             * dynamic : 看到刘亦菲这些照片，我要哭了
             * images : a:1:{i:0;s:65:"https://wx4.sinaimg.cn/mw690/005WPY4oly1fmh3dxx7m9j30hs0qomyz.jpg";}
             * city : 东莞
             * vote : 100
             * creattime : 1513393712
             */

            private int id;
            private int userid;
            private String dynamic;
            private String images;
            private String city;
            private int vote;
            private int creattime;

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

            public String getDynamic() {
                return dynamic;
            }

            public void setDynamic(String dynamic) {
                this.dynamic = dynamic;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getVote() {
                return vote;
            }

            public void setVote(int vote) {
                this.vote = vote;
            }

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }
        }
    }
}
