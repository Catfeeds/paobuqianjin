package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/2/23.
 */

public class UserFriendResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":11},"data":[{"id":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","is_distribute":1},{"id":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","is_distribute":1},{"id":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","is_distribute":0},{"id":4,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","nickname":"团魂","is_distribute":0},{"id":5,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央","is_distribute":0},{"id":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","is_distribute":0},{"id":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","is_distribute":0},{"id":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","is_distribute":1},{"id":9,"avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","nickname":"孤傲王者","is_distribute":0},{"id":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"孤君独战","is_distribute":0},{"id":61,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","nickname":"俊","is_distribute":1}]}
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
        return "UserFriendResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":11}
         * data : [{"id":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","is_distribute":1},{"id":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","is_distribute":1},{"id":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","is_distribute":0},{"id":4,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","nickname":"团魂","is_distribute":0},{"id":5,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央","is_distribute":0},{"id":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","is_distribute":0},{"id":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","is_distribute":0},{"id":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","is_distribute":1},{"id":9,"avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","nickname":"孤傲王者","is_distribute":0},{"id":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"孤君独战","is_distribute":0},{"id":61,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","nickname":"俊","is_distribute":1}]
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
             * totalPage : 2
             * totalCount : 11
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
             * id : 1
             * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg
             * nickname : 嗯额
             * is_distribute : 1
             */

            private int id;
            private String avatar;
            private String nickname;
            private int is_distribute;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getIs_distribute() {
                return is_distribute;
            }

            public void setIs_distribute(int is_distribute) {
                this.is_distribute = is_distribute;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", is_distribute=" + is_distribute +
                        '}';
            }
        }
    }
}
