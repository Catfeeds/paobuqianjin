package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */
/*
@className :DynamicAllIndexResponse
*@date 2018/1/12
*@author
*@description   GET index 获取动态列表
*/
public class DynamicAllIndexResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":6,"pageSize":1,"totalPage":54,"totalCount":54},"data":[{"id":119,"userid":57,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","nickname":"哈哈哈","dynamic":"哈哈","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6884903F-8342-4D71-B165-4779D97B702C.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/9EC07B0D-C72B-458D-9F3C-62219A5A7E52.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6032AE01-912E-4FAF-82DC-FDAB78EE2701.jpg"],"city":"深圳市","vote":1,"comment":1,"create_time":1521516930,"one_comment":{"id":327,"parent_id":0,"reply_userid":57,"userid":61,"dynamicid":119,"content":"哈哈","create_time":1521615940,"nickname":""},"is_vote":0}]}
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
        return "DynamicAllIndexResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":6,"pageSize":1,"totalPage":54,"totalCount":54}
         * data : [{"id":119,"userid":57,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","nickname":"哈哈哈","dynamic":"哈哈","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6884903F-8342-4D71-B165-4779D97B702C.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/9EC07B0D-C72B-458D-9F3C-62219A5A7E52.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6032AE01-912E-4FAF-82DC-FDAB78EE2701.jpg"],"city":"深圳市","vote":1,"comment":1,"create_time":1521516930,"one_comment":{"id":327,"parent_id":0,"reply_userid":57,"userid":61,"dynamicid":119,"content":"哈哈","create_time":1521615940,"nickname":""},"is_vote":0}]
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
             * page : 6
             * pageSize : 1
             * totalPage : 54
             * totalCount : 54
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
             * id : 119
             * userid : 57
             * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg
             * nickname : 哈哈哈
             * dynamic : 哈哈
             * images : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6884903F-8342-4D71-B165-4779D97B702C.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/9EC07B0D-C72B-458D-9F3C-62219A5A7E52.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6032AE01-912E-4FAF-82DC-FDAB78EE2701.jpg"]
             * city : 深圳市
             * vote : 1
             * comment : 1
             * create_time : 1521516930
             * one_comment : {"id":327,"parent_id":0,"reply_userid":57,"userid":61,"dynamicid":119,"content":"哈哈","create_time":1521615940,"nickname":""}
             * is_vote : 0
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

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            private int vip;

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
                        ", vip=" + vip +
                        '}';
            }

            public static class OneCommentBean implements Serializable {
                /**
                 * id : 327
                 * parent_id : 0
                 * reply_userid : 57
                 * userid : 61
                 * dynamicid : 119
                 * content : 哈哈
                 * create_time : 1521615940
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

                @Override
                public String toString() {
                    return "OneCommentBean{" +
                            "id=" + id +
                            ", parent_id=" + parent_id +
                            ", reply_userid=" + reply_userid +
                            ", userid=" + userid +
                            ", dynamicid=" + dynamicid +
                            ", content='" + content + '\'' +
                            ", create_time=" + create_time +
                            ", nickname='" + nickname + '\'' +
                            '}';
                }
            }
        }
    }
}
