package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/24.
 */
/*
@className :DynamicPersonResponse
*@date 2018/2/24
*@author
*@description 个人动态列表
*/
public class DynamicPersonResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":4,"pageSize":1,"totalPage":15,"totalCount":15},"data":[{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"one_comment":{"id":338,"parent_id":0,"reply_userid":30,"userid":61,"dynamicid":125,"content":"[0x1f603][0x1f60d][0x1f612][0x1f633][0x1f601][0x1f618]","create_time":1522668805,"nickname":""},"is_vote":1}]}
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
        return "DynamicPersonResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":4,"pageSize":1,"totalPage":15,"totalCount":15}
         * data : [{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"one_comment":{"id":338,"parent_id":0,"reply_userid":30,"userid":61,"dynamicid":125,"content":"[0x1f603][0x1f60d][0x1f612][0x1f633][0x1f601][0x1f618]","create_time":1522668805,"nickname":""},"is_vote":1}]
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
             * page : 4
             * pageSize : 1
             * totalPage : 15
             * totalCount : 15
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
             * id : 125
             * userid : 30
             * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
             * nickname : 黄钦平
             * dynamic : Vivo Android 5.0  720*1280
             * images : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"]
             * city : 上海市
             * vote : 2
             * comment : 25
             * create_time : 1522409030
             * one_comment : {"id":338,"parent_id":0,"reply_userid":30,"userid":61,"dynamicid":125,"content":"[0x1f603][0x1f60d][0x1f612][0x1f633][0x1f601][0x1f618]","create_time":1522668805,"nickname":""}
             * is_vote : 1
             */

            private int id;
            private int userid;
            private String avatar;
            private String nickname;
            private String dynamic;
            private String city;
            private int vote;
            private int comment;
            private int create_time;
            private OneCommentBean one_comment;
            private int is_vote;
            private List<String> images;

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

            public String getDynamic() {
                return dynamic;
            }

            public void setDynamic(String dynamic) {
                this.dynamic = dynamic;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getVote() {
                return vote;
            }

            public void setVote(int vote) {
                this.vote = vote;
            }

            public int getComment() {
                return comment;
            }

            public void setComment(int comment) {
                this.comment = comment;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public OneCommentBean getOne_comment() {
                return one_comment;
            }

            public void setOne_comment(OneCommentBean one_comment) {
                this.one_comment = one_comment;
            }

            public int getIs_vote() {
                return is_vote;
            }

            public void setIs_vote(int is_vote) {
                this.is_vote = is_vote;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", userid=" + userid +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", dynamic='" + dynamic + '\'' +
                        ", city='" + city + '\'' +
                        ", vote=" + vote +
                        ", comment=" + comment +
                        ", create_time=" + create_time +
                        ", one_comment=" + one_comment +
                        ", is_vote=" + is_vote +
                        ", images=" + images +
                        '}';
            }

            public static class OneCommentBean {
                /**
                 * id : 338
                 * parent_id : 0
                 * reply_userid : 30
                 * userid : 61
                 * dynamicid : 125
                 * content : [0x1f603][0x1f60d][0x1f612][0x1f633][0x1f601][0x1f618]
                 * create_time : 1522668805
                 * nickname :
                 */

                private int id;
                private int parent_id;
                private int reply_userid;
                private int userid;
                private int dynamicid;
                private String content;
                private int create_time;
                private String nickname;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }

                public int getReply_userid() {
                    return reply_userid;
                }

                public void setReply_userid(int reply_userid) {
                    this.reply_userid = reply_userid;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public int getDynamicid() {
                    return dynamicid;
                }

                public void setDynamicid(int dynamicid) {
                    this.dynamicid = dynamicid;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }
            }
        }
    }
}
