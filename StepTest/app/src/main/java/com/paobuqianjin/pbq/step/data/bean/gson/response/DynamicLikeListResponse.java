package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/3/3.
 */
/*
@className :DynamicLikeListResponse
*@date 2018/3/3
*@author
*@description
*/
public class DynamicLikeListResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":10},"data":[{"id":2,"userid":12,"avatar":"http://pic.qqtn.com/up/2017-12/15127898946203092.jpg","nickname":"惆怅旅客","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":3,"userid":13,"avatar":"http://pic.qqtn.com/up/2017-12/15127898947765211.jpg","nickname":"孤冢清风","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":4,"userid":14,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910395447428.jpg","nickname":"不忘初心","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":5,"userid":15,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910395412309.jpg","nickname":"盏茶作酒","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":6,"userid":16,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910431780408.jpg","nickname":"不忘初心","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":7,"userid":17,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910431781876.jpg","nickname":"盏茶作酒","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":8,"userid":18,"avatar":"http://pic.qqtn.com/up/2017-12/15130622723340055.jpg","nickname":"流苏如画","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":9,"userid":19,"avatar":"http://pic.qqtn.com/up/2017-11/15115072217014845.jpg","nickname":"空扰寡人","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":10,"userid":20,"avatar":"http://pic.qqtn.com/up/2017-11/15115072217041212.jpg","nickname":"久伴别酒伴","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":19,"userid":11,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937433836.jpg","nickname":"青丝几渐","dynamicid":1,"create_time":1515155547,"is_follow":0}]}
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
        return "DynamicLikeListResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":10}
         * data : [{"id":2,"userid":12,"avatar":"http://pic.qqtn.com/up/2017-12/15127898946203092.jpg","nickname":"惆怅旅客","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":3,"userid":13,"avatar":"http://pic.qqtn.com/up/2017-12/15127898947765211.jpg","nickname":"孤冢清风","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":4,"userid":14,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910395447428.jpg","nickname":"不忘初心","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":5,"userid":15,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910395412309.jpg","nickname":"盏茶作酒","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":6,"userid":16,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910431780408.jpg","nickname":"不忘初心","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":7,"userid":17,"avatar":"http://pic.qqtn.com/up/2017-12/2017120910431781876.jpg","nickname":"盏茶作酒","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":8,"userid":18,"avatar":"http://pic.qqtn.com/up/2017-12/15130622723340055.jpg","nickname":"流苏如画","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":9,"userid":19,"avatar":"http://pic.qqtn.com/up/2017-11/15115072217014845.jpg","nickname":"空扰寡人","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":10,"userid":20,"avatar":"http://pic.qqtn.com/up/2017-11/15115072217041212.jpg","nickname":"久伴别酒伴","dynamicid":1,"create_time":1513394510,"is_follow":0},{"id":19,"userid":11,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937433836.jpg","nickname":"青丝几渐","dynamicid":1,"create_time":1515155547,"is_follow":0}]
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
             * totalCount : 10
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
             * id : 2
             * userid : 12
             * avatar : http://pic.qqtn.com/up/2017-12/15127898946203092.jpg
             * nickname : 惆怅旅客
             * dynamicid : 1
             * create_time : 1513394510
             * is_follow : 0
             */

            private int id;
            private int userid;
            private String avatar;
            private String nickname;
            private int dynamicid;
            private int create_time;
            private int is_follow;

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

            public int getDynamicid() {
                return dynamicid;
            }

            public void setDynamicid(int dynamicid) {
                this.dynamicid = dynamicid;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(int is_follow) {
                this.is_follow = is_follow;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", userid=" + userid +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", dynamicid=" + dynamicid +
                        ", create_time=" + create_time +
                        ", is_follow=" + is_follow +
                        '}';
            }
        }
    }
}
