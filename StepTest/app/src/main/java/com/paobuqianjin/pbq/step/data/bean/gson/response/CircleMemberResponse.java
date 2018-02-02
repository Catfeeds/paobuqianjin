package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/2.
 */
/*
@className :CircleMemberResponse
*@date 2018/2/2
*@author
*@description  获取成员
*/
public class CircleMemberResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":19},"data":[{"id":6,"user_id":11,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937433836.jpg","nickname":"、干部","is_admin":1},{"id":9,"user_id":13,"avatar":"http://pic.qqtn.com/up/2017-12/15127898947765211.jpg","nickname":"中共中央总书记","is_admin":1},{"id":10,"user_id":14,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910395447428.jpg","nickname":"国务院副总理张高丽","is_admin":2},{"id":11,"user_id":15,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910395412309.jpg","nickname":"中共中央政治局常委栗战书","is_admin":2},{"id":20,"user_id":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"栗战书","is_admin":1},{"id":21,"user_id":9,"avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","nickname":"张德江、俞正声、张高丽、栗战书","is_admin":0},{"id":27,"user_id":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"","is_admin":0},{"id":29,"user_id":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"狗狗狗","is_admin":0},{"id":32,"user_id":17,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910431781876.jpg","nickname":"","is_admin":0},{"id":33,"user_id":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"","is_admin":0}]}
     */

    private int error;
    private String message;

    @Override
    public String toString() {
        return "CircleMemberResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

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
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":19}
         * data : [{"id":6,"user_id":11,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937433836.jpg","nickname":"、干部","is_admin":1},{"id":9,"user_id":13,"avatar":"http://pic.qqtn.com/up/2017-12/15127898947765211.jpg","nickname":"中共中央总书记","is_admin":1},{"id":10,"user_id":14,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910395447428.jpg","nickname":"国务院副总理张高丽","is_admin":2},{"id":11,"user_id":15,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910395412309.jpg","nickname":"中共中央政治局常委栗战书","is_admin":2},{"id":20,"user_id":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"栗战书","is_admin":1},{"id":21,"user_id":9,"avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","nickname":"张德江、俞正声、张高丽、栗战书","is_admin":0},{"id":27,"user_id":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"","is_admin":0},{"id":29,"user_id":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"狗狗狗","is_admin":0},{"id":32,"user_id":17,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910431781876.jpg","nickname":"","is_admin":0},{"id":33,"user_id":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"","is_admin":0}]
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
             * totalCount : 19
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
             * id : 6
             * user_id : 11
             * avatar : http://pic.qqtn.com/up/2017-12/15127898937433836.jpg
             * nickname : 、干部
             * is_admin : 1
             */

            private int id;
            private int user_id;
            private String avatar;
            private String nickname;
            private int is_admin;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public int getIs_admin() {
                return is_admin;
            }

            public void setIs_admin(int is_admin) {
                this.is_admin = is_admin;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", user_id=" + user_id +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", is_admin=" + is_admin +
                        '}';
            }
        }
    }
}
