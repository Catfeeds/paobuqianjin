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
     * data : {"pagenation":{"page":1,"pageSize":100,"totalPage":1,"totalCount":24},"data":[{"id":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","followid":2,"vip":1,"is_distribute":0},{"id":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","followid":3,"vip":1,"is_distribute":1},{"id":4,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","nickname":"团魂","followid":4,"vip":0,"is_distribute":1},{"id":5,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央","followid":5,"vip":0,"is_distribute":1},{"id":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","followid":6,"vip":0,"is_distribute":1},{"id":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","followid":7,"vip":0,"is_distribute":1},{"id":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","followid":8,"vip":0,"is_distribute":1},{"id":9,"avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","nickname":"孤傲王者","followid":9,"vip":0,"is_distribute":1},{"id":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"孤君独战","followid":10,"vip":0,"is_distribute":1},{"id":66,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","nickname":"wensen","followid":66,"vip":0,"is_distribute":1},{"id":67,"avatar":"","nickname":"","followid":67,"vip":0,"is_distribute":1},{"id":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯好几块没空看开没开门妈妈额","followid":1,"vip":1,"is_distribute":0},{"id":57,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","nickname":"周手机","followid":57,"vip":1,"is_distribute":0},{"id":61,"avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","nickname":"","followid":61,"vip":0,"is_distribute":0},{"id":174,"avatar":"","nickname":"红米手机","followid":174,"vip":0,"is_distribute":1},{"id":195,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/838E8FC341776719299893171D5F552C/100","nickname":"九州涧（空）","followid":195,"vip":0,"is_distribute":1},{"id":189,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJPu1sSYJ1XWcyiayNRY7KP4QBbcibS1bYLWEgrEaAmiaOcB8vV2pYnD7fbLOKcLeRhkI5WdrQyVicBCA/132","nickname":"傅超","followid":189,"vip":0,"is_distribute":0},{"id":193,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/YXr2VhxJpdg2dcIZMrju42mmGXmLabwpbTiaoKq9lP5WbdkhcTkkZ6pjuBwLIQdC0u68UhqUkpb5yZ89PoyMiarA/132","nickname":"李","followid":193,"vip":0,"is_distribute":0},{"id":206,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/qoicvAvQjJPE3r0EZSVbZ0dziaYklJialD5r4aN61yDGVtotUy4F1DWYib2ic6WLTbOTMJ7OI1OWdX0QFMS2Dq6GBAQ/0","nickname":"（^O^）","followid":206,"vip":0,"is_distribute":1},{"id":219,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/CE44816EB4948E97C994F702EF47AFF8/100","nickname":" huawei","followid":219,"vip":0,"is_distribute":0},{"id":178,"avatar":"","nickname":"嘉年华","followid":178,"vip":0,"is_distribute":0},{"id":221,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/574918FFDEE3D08C5288F617116253A7/100","nickname":"(^O^)","followid":221,"vip":0,"is_distribute":0},{"id":222,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/91D59BCF7E054D391A5BE0C1BC4396A8/100","nickname":"Vivo","followid":222,"vip":0,"is_distribute":0},{"id":201,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/838E8FC341776719299893171D5F552C/100","nickname":"九洲涧","followid":201,"vip":0,"is_distribute":1}]}
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
         * pagenation : {"page":1,"pageSize":100,"totalPage":1,"totalCount":24}
         * data : [{"id":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","followid":2,"vip":1,"is_distribute":0},{"id":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","followid":3,"vip":1,"is_distribute":1},{"id":4,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","nickname":"团魂","followid":4,"vip":0,"is_distribute":1},{"id":5,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央","followid":5,"vip":0,"is_distribute":1},{"id":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","followid":6,"vip":0,"is_distribute":1},{"id":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","followid":7,"vip":0,"is_distribute":1},{"id":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","followid":8,"vip":0,"is_distribute":1},{"id":9,"avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","nickname":"孤傲王者","followid":9,"vip":0,"is_distribute":1},{"id":10,"avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","nickname":"孤君独战","followid":10,"vip":0,"is_distribute":1},{"id":66,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","nickname":"wensen","followid":66,"vip":0,"is_distribute":1},{"id":67,"avatar":"","nickname":"","followid":67,"vip":0,"is_distribute":1},{"id":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯好几块没空看开没开门妈妈额","followid":1,"vip":1,"is_distribute":0},{"id":57,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","nickname":"周手机","followid":57,"vip":1,"is_distribute":0},{"id":61,"avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","nickname":"","followid":61,"vip":0,"is_distribute":0},{"id":174,"avatar":"","nickname":"红米手机","followid":174,"vip":0,"is_distribute":1},{"id":195,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/838E8FC341776719299893171D5F552C/100","nickname":"九州涧（空）","followid":195,"vip":0,"is_distribute":1},{"id":189,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJPu1sSYJ1XWcyiayNRY7KP4QBbcibS1bYLWEgrEaAmiaOcB8vV2pYnD7fbLOKcLeRhkI5WdrQyVicBCA/132","nickname":"傅超","followid":189,"vip":0,"is_distribute":0},{"id":193,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/YXr2VhxJpdg2dcIZMrju42mmGXmLabwpbTiaoKq9lP5WbdkhcTkkZ6pjuBwLIQdC0u68UhqUkpb5yZ89PoyMiarA/132","nickname":"李","followid":193,"vip":0,"is_distribute":0},{"id":206,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/qoicvAvQjJPE3r0EZSVbZ0dziaYklJialD5r4aN61yDGVtotUy4F1DWYib2ic6WLTbOTMJ7OI1OWdX0QFMS2Dq6GBAQ/0","nickname":"（^O^）","followid":206,"vip":0,"is_distribute":1},{"id":219,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/CE44816EB4948E97C994F702EF47AFF8/100","nickname":" huawei","followid":219,"vip":0,"is_distribute":0},{"id":178,"avatar":"","nickname":"嘉年华","followid":178,"vip":0,"is_distribute":0},{"id":221,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/574918FFDEE3D08C5288F617116253A7/100","nickname":"(^O^)","followid":221,"vip":0,"is_distribute":0},{"id":222,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/91D59BCF7E054D391A5BE0C1BC4396A8/100","nickname":"Vivo","followid":222,"vip":0,"is_distribute":0},{"id":201,"avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/838E8FC341776719299893171D5F552C/100","nickname":"九洲涧","followid":201,"vip":0,"is_distribute":1}]
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
             * pageSize : 100
             * totalPage : 1
             * totalCount : 24
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
             * vip : 1
             * is_distribute : 0
             */

            private int id;
            private String avatar;
            private String nickname;
            private int followid;
            private int vip;
            private int is_distribute;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

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

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
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
                        ", vip=" + vip +
                        ", is_distribute=" + is_distribute +
                        ", isSelected=" + isSelected +
                        '}';
            }
        }
    }
}
