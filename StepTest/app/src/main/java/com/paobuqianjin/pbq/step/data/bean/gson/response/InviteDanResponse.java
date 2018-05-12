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
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"inviterid":2,"inviternum":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","create_time":1526106975,"vip":0,"sum_credit":200,"allmoney":60},{"inviterid":66,"inviternum":1,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqrcBtCRb0zSZbkaV20yibScfdoqsHwcianPcBJjX1KhMBR113nm5R7IwIw5LsHHhp6EUpscauqzicOA/132","nickname":"小沙","create_time":1526106630,"vip":1,"sum_credit":0,"allmoney":50}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : [{"inviterid":2,"inviternum":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","create_time":1526106975,"vip":0,"sum_credit":200,"allmoney":60},{"inviterid":66,"inviternum":1,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqrcBtCRb0zSZbkaV20yibScfdoqsHwcianPcBJjX1KhMBR113nm5R7IwIw5LsHHhp6EUpscauqzicOA/132","nickname":"小沙","create_time":1526106630,"vip":1,"sum_credit":0,"allmoney":50}]
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
        }

        public static class DataBean {
            /**
             * inviterid : 2
             * inviternum : 2
             * avatar : http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg
             * nickname : 李五
             * create_time : 1526106975
             * vip : 0
             * sum_credit : 200
             * allmoney : 60
             */

            private int inviterid;
            private int inviternum;
            private String avatar;
            private String nickname;
            private int create_time;
            private int vip;
            private int sum_credit;
            private float allmoney;

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

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public int getSum_credit() {
                return sum_credit;
            }

            public void setSum_credit(int sum_credit) {
                this.sum_credit = sum_credit;
            }

            public float getAllmoney() {
                return allmoney;
            }

            public void setAllmoney(float allmoney) {
                this.allmoney = allmoney;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "inviterid=" + inviterid +
                        ", inviternum=" + inviternum +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", create_time=" + create_time +
                        ", vip=" + vip +
                        ", sum_credit=" + sum_credit +
                        ", allmoney=" + allmoney +
                        '}';
            }
        }
    }
}
