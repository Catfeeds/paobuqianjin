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
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":10},"data":[{"id":1,"userid":1,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","nickname":"狗狗","dynamic":"你马上要8岁了哟 看我一眼好不好","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":9,"create_time":1513393712,"one_comment":{"id":2,"reply_userid":2,"userid":3,"dynamicid":1,"content":"原图老哥谢谢","create_time":1513394223,"nickname":"九卿臣"}}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":10}
         * data : [{"id":1,"userid":1,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","nickname":"狗狗","dynamic":"你马上要8岁了哟 看我一眼好不好","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":9,"create_time":1513393712,"one_comment":{"id":2,"reply_userid":2,"userid":3,"dynamicid":1,"content":"原图老哥谢谢","create_time":1513394223,"nickname":"九卿臣"}}]
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
             * totalCount : 10
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
             * id : 1
             * userid : 1
             * avatar : http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg
             * nickname : 狗狗
             * dynamic : 你马上要8岁了哟 看我一眼好不好
             * images : ["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"]
             * city : 深圳
             * vote : 100
             * comment : 9
             * create_time : 1513393712
             * one_comment : {"id":2,"reply_userid":2,"userid":3,"dynamicid":1,"content":"原图老哥谢谢","create_time":1513394223,"nickname":"九卿臣"}
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
                        ", images=" + images +
                        '}';
            }

            public static class OneCommentBean {
                /**
                 * id : 2
                 * reply_userid : 2
                 * userid : 3
                 * dynamicid : 1
                 * content : 原图老哥谢谢
                 * create_time : 1513394223
                 * nickname : 九卿臣
                 */

                private int id;
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
