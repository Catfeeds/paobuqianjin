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
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":13},"data":[{"id":1,"userid":1,"dynamic":"你马上要8岁了哟 看我一眼好不好","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":9,"creat_time":1513393712,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","one_comment":{"id":1,"parent_id":0,"reply_userid":0,"userid":2,"dynamicid":1,"content":"十年峰景易路同行 我与易峰一起前行","creat_time":1513394223,"nickname":"李五"}},{"id":2,"userid":2,"dynamic":"我有个小小的愿望，下次如路人相遇的时候，人们都能大大方方的来打个招呼，而不是偷偷的举起相机。","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"惠州","vote":100,"comment":5,"creat_time":1513393712,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","one_comment":{"id":10,"parent_id":0,"reply_userid":0,"userid":32,"dynamicid":2,"content":"哎呦","creat_time":0,"nickname":"rm_13424156025"}},{"id":3,"userid":3,"dynamic":"王祖蓝晒与宋仲基的合照，笑称长胖了 网友表示：果然幸福的人都一样啊！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"江门","vote":100,"comment":0,"creat_time":1513393712,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","one_comment":{}},{"id":4,"userid":4,"dynamic":"被问到从王思聪薛之谦吴亦凡中选一个拍吻戏时，韩雪竟这样回答！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":0,"creat_time":1513393712,"nickname":"团魂","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","one_comment":{}},{"id":5,"userid":5,"dynamic":"看到刘亦菲这些照片，我要哭了","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"东莞","vote":100,"comment":1,"creat_time":1513393712,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","one_comment":{}},{"id":6,"userid":6,"dynamic":"5位明星想要掩盖的身体缺陷，他摔倒失去蛋蛋，她天生没有子宫！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":0,"creat_time":1513393712,"nickname":"青冘","avatar":"http://pic.qqtn.com/up/2017-12/15127898933093824.jpg","one_comment":{}},{"id":7,"userid":7,"dynamic":"赵丽颖被各卫视拒上跨年，现又被春晚拒绝，曝光的真相你能接受吗？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":0,"creat_time":1513393712,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","one_comment":{}},{"id":8,"userid":8,"dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":0,"creat_time":1513393712,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","one_comment":{}},{"id":16,"userid":1,"dynamic":"你是不是傻？？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"creat_time":1514873370,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","one_comment":{}},{"id":18,"userid":1,"dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"creat_time":1514961235,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","one_comment":{}}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":13}
         * data : [{"id":1,"userid":1,"dynamic":"你马上要8岁了哟 看我一眼好不好","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":9,"creat_time":1513393712,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","one_comment":{"id":1,"parent_id":0,"reply_userid":0,"userid":2,"dynamicid":1,"content":"十年峰景易路同行 我与易峰一起前行","creat_time":1513394223,"nickname":"李五"}},{"id":2,"userid":2,"dynamic":"我有个小小的愿望，下次如路人相遇的时候，人们都能大大方方的来打个招呼，而不是偷偷的举起相机。","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"惠州","vote":100,"comment":5,"creat_time":1513393712,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","one_comment":{"id":10,"parent_id":0,"reply_userid":0,"userid":32,"dynamicid":2,"content":"哎呦","creat_time":0,"nickname":"rm_13424156025"}},{"id":3,"userid":3,"dynamic":"王祖蓝晒与宋仲基的合照，笑称长胖了 网友表示：果然幸福的人都一样啊！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"江门","vote":100,"comment":0,"creat_time":1513393712,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","one_comment":{}},{"id":4,"userid":4,"dynamic":"被问到从王思聪薛之谦吴亦凡中选一个拍吻戏时，韩雪竟这样回答！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":0,"creat_time":1513393712,"nickname":"团魂","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","one_comment":{}},{"id":5,"userid":5,"dynamic":"看到刘亦菲这些照片，我要哭了","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"东莞","vote":100,"comment":1,"creat_time":1513393712,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","one_comment":{}},{"id":6,"userid":6,"dynamic":"5位明星想要掩盖的身体缺陷，他摔倒失去蛋蛋，她天生没有子宫！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":0,"creat_time":1513393712,"nickname":"青冘","avatar":"http://pic.qqtn.com/up/2017-12/15127898933093824.jpg","one_comment":{}},{"id":7,"userid":7,"dynamic":"赵丽颖被各卫视拒上跨年，现又被春晚拒绝，曝光的真相你能接受吗？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":0,"creat_time":1513393712,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","one_comment":{}},{"id":8,"userid":8,"dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":0,"creat_time":1513393712,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","one_comment":{}},{"id":16,"userid":1,"dynamic":"你是不是傻？？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"creat_time":1514873370,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","one_comment":{}},{"id":18,"userid":1,"dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"creat_time":1514961235,"nickname":"陈杰","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg","one_comment":{}}]
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
             * dynamic : 你马上要8岁了哟 看我一眼好不好
             * images : ["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"]
             * city : 深圳
             * vote : 100
             * comment : 9
             * creat_time : 1513393712
             * nickname : 陈杰
             * avatar : http://pic.qqtn.com/up/2017-12/2017120911184235460.jpg
             * one_comment : {"id":1,"parent_id":0,"reply_userid":0,"userid":2,"dynamicid":1,"content":"十年峰景易路同行 我与易峰一起前行","creat_time":1513394223,"nickname":"李五"}
             */

            private int id;
            private int userid;
            private String dynamic;
            private String city;
            private int vote;
            private int comment;
            private int creat_time;
            private String nickname;
            private String avatar;
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

            public int getCreat_time() {
                return creat_time;
            }

            public void setCreat_time(int creat_time) {
                this.creat_time = creat_time;
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
                        ", dynamic='" + dynamic + '\'' +
                        ", city='" + city + '\'' +
                        ", vote=" + vote +
                        ", comment=" + comment +
                        ", creat_time=" + creat_time +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", one_comment=" + one_comment +
                        ", images=" + images +
                        '}';
            }

            public static class OneCommentBean {
                /**
                 * id : 1
                 * parent_id : 0
                 * reply_userid : 0
                 * userid : 2
                 * dynamicid : 1
                 * content : 十年峰景易路同行 我与易峰一起前行
                 * creat_time : 1513394223
                 * nickname : 李五
                 */

                private int id;
                private int parent_id;
                private int reply_userid;
                private int userid;
                private int dynamicid;
                private String content;
                private int creat_time;
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

                public int getCreat_time() {
                    return creat_time;
                }

                public void setCreat_time(int creat_time) {
                    this.creat_time = creat_time;
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
                            ", creat_time=" + creat_time +
                            ", nickname='" + nickname + '\'' +
                            '}';
                }
            }
        }
    }
}
