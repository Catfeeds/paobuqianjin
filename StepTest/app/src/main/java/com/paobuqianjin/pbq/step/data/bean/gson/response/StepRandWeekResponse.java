package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/30.
 */

public class StepRandWeekResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"userid":61,"step_number":"18000","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","nickname":"俊"},{"userid":63,"step_number":"18000","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/8042CCF8-59DD-475D-A79D-09E40F6F5CA5.jpg","nickname":"旺旺xwy"},{"userid":30,"step_number":"16562","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平"},{"userid":1,"step_number":"14354","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额"}]}
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
        return "StepRandWeekResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"userid":61,"step_number":"18000","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","nickname":"俊"},{"userid":63,"step_number":"18000","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/8042CCF8-59DD-475D-A79D-09E40F6F5CA5.jpg","nickname":"旺旺xwy"},{"userid":30,"step_number":"16562","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平"},{"userid":1,"step_number":"14354","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额"}]
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
             * totalCount : 4
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
             * userid : 61
             * step_number : 18000
             * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg
             * nickname : 俊
             */

            private int userid;
            private String step_number;
            private String avatar;
            private String nickname;

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getStep_number() {
                return step_number;
            }

            public void setStep_number(String step_number) {
                this.step_number = step_number;
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

            @Override
            public String toString() {
                return "DataBean{" +
                        "userid=" + userid +
                        ", step_number='" + step_number + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        '}';
            }
        }
    }
}
