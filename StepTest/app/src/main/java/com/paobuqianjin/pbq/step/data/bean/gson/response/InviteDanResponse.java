package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/27.
 */
/*
@className :InviteDanResponse
*@date 2018/2/27
*@author
*@description 邀请排行达人榜
*/
public class InviteDanResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":3},"data":[{"inviterid":1,"inviternum":4,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D0922F24-15D6-451E-B432-9B1F123D2C9A.jpg","nickname":"嗯额咳咳","create_time":1519637725,"sum_credit":50},{"inviterid":2,"inviternum":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","create_time":1519637725,"sum_credit":50},{"inviterid":3,"inviternum":1,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","create_time":1519637725,"sum_credit":0}]}
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
        return "InviteDanResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":3}
         * data : [{"inviterid":1,"inviternum":4,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D0922F24-15D6-451E-B432-9B1F123D2C9A.jpg","nickname":"嗯额咳咳","create_time":1519637725,"sum_credit":50},{"inviterid":2,"inviternum":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","create_time":1519637725,"sum_credit":50},{"inviterid":3,"inviternum":1,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","create_time":1519637725,"sum_credit":0}]
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
             * totalCount : 3
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
             * inviterid : 1
             * inviternum : 4
             * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D0922F24-15D6-451E-B432-9B1F123D2C9A.jpg
             * nickname : 嗯额咳咳
             * create_time : 1519637725
             * sum_credit : 50
             */

            private int inviterid;
            private int inviternum;
            private String avatar;
            private String nickname;
            private int create_time;
            private float sum_credit;

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            private int vip;

            public int getInviterid() {
                return inviterid;
            }

            public void setInviterid(int inviterid) {
                this.inviterid = inviterid;
            }

            public int getInviternum() {
                return inviternum;
            }

            public void setInviternum(int inviternum) {
                this.inviternum = inviternum;
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

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public float getSum_credit() {
                return sum_credit;
            }

            public void setSum_credit(int sum_credit) {
                this.sum_credit = sum_credit;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "inviterid=" + inviterid +
                        ", inviternum=" + inviternum +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", create_time=" + create_time +
                        ", sum_credit=" + sum_credit +
                        ", vip=" + vip +
                        '}';
            }
        }
    }
}
