package com.paobuqianjin.pbq.step.data.bean.gson.response;

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
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"id":8,"userid":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":1,"create_time":1513393712,"one_comment":{"id":22,"parent_id":0,"reply_userid":8,"userid":1,"dynamicid":8,"content":"wwwwwee","create_time":1520060313,"nickname":"嗯额"}},{"id":7,"userid":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","dynamic":"赵丽颖被各卫视拒上跨年，现又被春晚拒绝，曝光的真相你能接受吗？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":1,"create_time":1513393712,"one_comment":{"id":21,"parent_id":0,"reply_userid":7,"userid":1,"dynamicid":7,"content":"223333","create_time":1520060206,"nickname":"嗯额"}}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : [{"id":8,"userid":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":1,"create_time":1513393712,"one_comment":{"id":22,"parent_id":0,"reply_userid":8,"userid":1,"dynamicid":8,"content":"wwwwwee","create_time":1520060313,"nickname":"嗯额"}},{"id":7,"userid":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","dynamic":"赵丽颖被各卫视拒上跨年，现又被春晚拒绝，曝光的真相你能接受吗？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":1,"create_time":1513393712,"one_comment":{"id":21,"parent_id":0,"reply_userid":7,"userid":1,"dynamicid":7,"content":"223333","create_time":1520060206,"nickname":"嗯额"}}]
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
             * id : 8
             * userid : 8
             * avatar : http://pic.qqtn.com/up/2017-12/15127898937460203.jpg
             * nickname : 酒自斟
             * dynamic : 易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！
             * images : ["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"]
             * city : 深圳
             * vote : 100
             * comment : 1
             * create_time : 1513393712
             * one_comment : {"id":22,"parent_id":0,"reply_userid":8,"userid":1,"dynamicid":8,"content":"wwwwwee","create_time":1520060313,"nickname":"嗯额"}
             */

            private int id;
            private int userid;
            private String avatar;
            private String nickname;
            private String dynamic;
            private String city;
            private int vote;
            private int comment;
            private long create_time;
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

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
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
                 * id : 22
                 * parent_id : 0
                 * reply_userid : 8
                 * userid : 1
                 * dynamicid : 8
                 * content : wwwwwee
                 * create_time : 1520060313
                 * nickname : 嗯额
                 */

                private int id;
                private int parent_id;
                private int reply_userid;
                private int userid;
                private int dynamicid;
                private String content;
                private long create_time;
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

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
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
