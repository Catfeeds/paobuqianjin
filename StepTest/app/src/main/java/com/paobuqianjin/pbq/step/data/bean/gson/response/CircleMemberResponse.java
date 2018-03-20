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
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":4},"data":[{"id":406,"user_id":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","circlenickname":"","is_admin":2},{"id":412,"user_id":61,"nickname":"俊","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","circlenickname":"","is_admin":0},{"id":413,"user_id":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","circlenickname":"","is_admin":0},{"id":465,"user_id":57,"nickname":"周周周","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIxXJTVJvRmxPhfS0aNF0pOXn3EdZ96DabUIULlH1E9mUbEZCE98IPEzqTgwD7jtSqfqiaUFV0rIsQ/132","circlenickname":"","is_admin":0}]}
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
        return "CircleMemberResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":4}
         * data : [{"id":406,"user_id":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","circlenickname":"","is_admin":2},{"id":412,"user_id":61,"nickname":"俊","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","circlenickname":"","is_admin":0},{"id":413,"user_id":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","circlenickname":"","is_admin":0},{"id":465,"user_id":57,"nickname":"周周周","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIxXJTVJvRmxPhfS0aNF0pOXn3EdZ96DabUIULlH1E9mUbEZCE98IPEzqTgwD7jtSqfqiaUFV0rIsQ/132","circlenickname":"","is_admin":0}]
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
             * id : 406
             * user_id : 30
             * nickname : 黄钦平
             * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
             * circlenickname :
             * is_admin : 2
             */

            private int id;
            private int user_id;
            private String nickname;
            private String avatar;
            private String circlenickname;
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

            public String getCirclenickname() {
                return circlenickname;
            }

            public void setCirclenickname(String circlenickname) {
                this.circlenickname = circlenickname;
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
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", circlenickname='" + circlenickname + '\'' +
                        ", is_admin=" + is_admin +
                        '}';
            }
        }
    }
}
