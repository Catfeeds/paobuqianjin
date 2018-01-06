package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * 圈子充值排行榜返回
 * Created by pbq on 2018/1/4.
 */

public class ReChargeRankResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":13},"data":[{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","amount":"600.00"},{"userid":7,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","amount":"500.00"},{"userid":4,"nickname":"团魂","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","amount":"380.00"},{"userid":3,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","amount":"300.00"},{"userid":6,"nickname":"青冘","avatar":"http://pic.qqtn.com/up/2017-12/15127898933093824.jpg","amount":"280.00"},{"userid":2,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","amount":"200.00"},{"userid":1,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","amount":"199.00"},{"userid":5,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","amount":"180.00"},{"userid":1,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","amount":"100.00"},{"userid":11,"nickname":"青丝几渐","avatar":"http://pic.qqtn.com/up/2017-12/15127898937433836.jpg","amount":"90.00"}]}
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
        return "ReChargeRankResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":13}
         * data : [{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","amount":"600.00"},{"userid":7,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","amount":"500.00"},{"userid":4,"nickname":"团魂","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","amount":"380.00"},{"userid":3,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","amount":"300.00"},{"userid":6,"nickname":"青冘","avatar":"http://pic.qqtn.com/up/2017-12/15127898933093824.jpg","amount":"280.00"},{"userid":2,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","amount":"200.00"},{"userid":1,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","amount":"199.00"},{"userid":5,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","amount":"180.00"},{"userid":1,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","amount":"100.00"},{"userid":11,"nickname":"青丝几渐","avatar":"http://pic.qqtn.com/up/2017-12/15127898937433836.jpg","amount":"90.00"}]
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
             * totalCount : 13
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

        public static class DataBean implements Serializable {
            /**
             * userid : 8
             * nickname : 酒自斟
             * avatar : http://pic.qqtn.com/up/2017-12/15127898937460203.jpg
             * amount : 600.00
             */

            private int userid;
            private String nickname;
            private String avatar;
            private String amount;

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "userid=" + userid +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", amount='" + amount + '\'' +
                        '}';
            }
        }
    }
}
