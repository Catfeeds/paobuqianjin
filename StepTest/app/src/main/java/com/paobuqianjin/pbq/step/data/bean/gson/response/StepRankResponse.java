package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * 圈子步数排行
 * Created by pbq on 2018/1/4.
 */

public class StepRankResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":5,"totalPage":4,"totalCount":20},"data":[{"id":12,"nickname":"惆怅旅客","avatar":"http://pic.qqtn.com/up/2017-12/15127898946203092.jpg","step_number":9795},{"id":11,"nickname":"青丝几渐","avatar":"http://pic.qqtn.com/up/2017-12/15127898937433836.jpg","step_number":9794},{"id":10,"nickname":"孤君独战","avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","step_number":9726},{"id":13,"nickname":"孤冢清风","avatar":"http://pic.qqtn.com/up/2017-12/15127898947765211.jpg","step_number":9591},{"id":2,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","step_number":8701}]}
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
        return "StepRankResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":5,"totalPage":4,"totalCount":20}
         * data : [{"id":12,"nickname":"惆怅旅客","avatar":"http://pic.qqtn.com/up/2017-12/15127898946203092.jpg","step_number":9795},{"id":11,"nickname":"青丝几渐","avatar":"http://pic.qqtn.com/up/2017-12/15127898937433836.jpg","step_number":9794},{"id":10,"nickname":"孤君独战","avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","step_number":9726},{"id":13,"nickname":"孤冢清风","avatar":"http://pic.qqtn.com/up/2017-12/15127898947765211.jpg","step_number":9591},{"id":2,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","step_number":8701}]
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
             * pageSize : 5
             * totalPage : 4
             * totalCount : 20
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
             * id : 12
             * nickname : 惆怅旅客
             * avatar : http://pic.qqtn.com/up/2017-12/15127898946203092.jpg
             * step_number : 9795
             */

            private int id;
            private String nickname;
            private String avatar;
            private int step_number;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getStep_number() {
                return step_number;
            }

            public void setStep_number(int step_number) {
                this.step_number = step_number;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", step_number=" + step_number +
                        '}';
            }
        }
    }
}
