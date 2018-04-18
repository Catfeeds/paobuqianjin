package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/3/1.
 */
/*
@className :UserFollowOtOResponse
*@date 2018/3/1
*@author
*@description 互相关注
*/
public class UserFollowOtOResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"userid":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","sex":0,"followid":8},{"userid":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"孤君独战","sex":1,"followid":10}]}
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
        return "UserFollowOtOResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : [{"userid":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","sex":0,"followid":8},{"userid":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"孤君独战","sex":1,"followid":10}]
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

        public static class DataBean implements Serializable {
            /**
             * userid : 8
             * avatar : http://pic.qqtn.com/up/2017-12/15127898937460203.jpg
             * nickname : 酒自斟
             * sex : 0
             * followid : 8
             */

            private int userid;
            private String avatar;
            private String nickname;
            private int sex;
            private int followid;

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

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getFollowid() {
                return followid;
            }

            public void setFollowid(int followid) {
                this.followid = followid;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "userid=" + userid +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", sex=" + sex +
                        ", followid=" + followid +
                        '}';
            }
        }
    }
}
