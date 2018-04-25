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
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":9},"data":[{"id":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","followid":2,"is_distribute":0},{"id":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","followid":3,"is_distribute":0},{"id":5,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央","followid":5,"is_distribute":0},{"id":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","followid":6,"is_distribute":0},{"id":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","followid":7,"is_distribute":0},{"id":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","followid":8,"is_distribute":0},{"id":9,"avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","nickname":"孤傲王者","followid":9,"is_distribute":0},{"id":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"孤君独战","followid":10,"is_distribute":0},{"id":66,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","nickname":"小沙","followid":66,"is_distribute":0}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":9}
         * data : [{"id":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","followid":2,"is_distribute":0},{"id":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","followid":3,"is_distribute":0},{"id":5,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央","followid":5,"is_distribute":0},{"id":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","followid":6,"is_distribute":0},{"id":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","followid":7,"is_distribute":0},{"id":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","followid":8,"is_distribute":0},{"id":9,"avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","nickname":"孤傲王者","followid":9,"is_distribute":0},{"id":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"孤君独战","followid":10,"is_distribute":0},{"id":66,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","nickname":"小沙","followid":66,"is_distribute":0}]
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
             * totalCount : 9
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
             * avatar : http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg
             * nickname : 李五
             * followid : 2
             * is_distribute : 0
             */

            private int id;
            private String avatar;
            private String nickname;
            private int followid;
            private int is_distribute;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            //增加一个选中状态
            private boolean isSelected = false;

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

            public int getFollowid() {
                return followid;
            }

            public void setFollowid(int followid) {
                this.followid = followid;
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
                        ", followid=" + followid +
                        ", is_distribute=" + is_distribute +
                        ", isSelected=" + isSelected +
                        '}';
            }
        }
    }
}
