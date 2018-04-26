package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */
/*
@className :DynamicCommentListResponse
*@date 2018/3/3
*@author
*@description 获取单条动态的评论列表
*/
public class DynamicCommentListResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":10},"data":[{"id":2,"parent_id":0,"reply_userid":0,"userid":3,"dynamicid":1,"content":"原图老哥谢谢","create_time":1513394223,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","child":[{"id":3,"parent_id":2,"reply_userid":3,"userid":2,"dynamicid":1,"content":"过不了多久，某宝就会有同款婴儿服了吧","create_time":1513394224,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","reply_nickname":"九卿臣"},{"id":4,"parent_id":2,"reply_userid":3,"userid":5,"dynamicid":1,"content":"那就买买买 然后攒着","create_time":1513394224,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","reply_nickname":"九卿臣"}]},{"id":5,"parent_id":0,"reply_userid":0,"userid":6,"dynamicid":1,"content":"励志 我罗牛逼","create_time":1513394224,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","child":[{"id":6,"parent_id":5,"reply_userid":3,"userid":7,"dynamicid":1,"content":"大家误会了，这位巴萨球迷是在自我介绍","create_time":1513394224,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","reply_nickname":"九卿臣"},{"id":7,"parent_id":5,"reply_userid":2,"userid":8,"dynamicid":1,"content":"前几天梅西得的是什么奖来着","create_time":1513394224,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","reply_nickname":"李五"}]},{"id":8,"parent_id":0,"reply_userid":5,"userid":9,"dynamicid":1,"content":"三票罗的逆袭","create_time":1513394224,"nickname":"孤傲王者","avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","reply_nickname":"孤央"},{"id":9,"parent_id":0,"reply_userid":6,"userid":10,"dynamicid":1,"content":"所有的成功，都是日夜苦练的褒奖！","create_time":1513394224,"nickname":"孤君独战","avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","reply_nickname":"酒自斟"},{"id":16,"parent_id":0,"reply_userid":5,"userid":1,"dynamicid":1,"content":"你是不是傻？？？","create_time":0,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","reply_nickname":"孤央"},{"id":20,"parent_id":0,"reply_userid":6,"userid":2,"dynamicid":1,"content":"小作坊","create_time":1519614288,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","reply_nickname":"酒自斟"}]}
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
        return "DynamicCommentListResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":10}
         * data : [{"id":2,"parent_id":0,"reply_userid":0,"userid":3,"dynamicid":1,"content":"原图老哥谢谢","create_time":1513394223,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","child":[{"id":3,"parent_id":2,"reply_userid":3,"userid":2,"dynamicid":1,"content":"过不了多久，某宝就会有同款婴儿服了吧","create_time":1513394224,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","reply_nickname":"九卿臣"},{"id":4,"parent_id":2,"reply_userid":3,"userid":5,"dynamicid":1,"content":"那就买买买 然后攒着","create_time":1513394224,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","reply_nickname":"九卿臣"}]},{"id":5,"parent_id":0,"reply_userid":0,"userid":6,"dynamicid":1,"content":"励志 我罗牛逼","create_time":1513394224,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","child":[{"id":6,"parent_id":5,"reply_userid":3,"userid":7,"dynamicid":1,"content":"大家误会了，这位巴萨球迷是在自我介绍","create_time":1513394224,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","reply_nickname":"九卿臣"},{"id":7,"parent_id":5,"reply_userid":2,"userid":8,"dynamicid":1,"content":"前几天梅西得的是什么奖来着","create_time":1513394224,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","reply_nickname":"李五"}]},{"id":8,"parent_id":0,"reply_userid":5,"userid":9,"dynamicid":1,"content":"三票罗的逆袭","create_time":1513394224,"nickname":"孤傲王者","avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","reply_nickname":"孤央"},{"id":9,"parent_id":0,"reply_userid":6,"userid":10,"dynamicid":1,"content":"所有的成功，都是日夜苦练的褒奖！","create_time":1513394224,"nickname":"孤君独战","avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","reply_nickname":"酒自斟"},{"id":16,"parent_id":0,"reply_userid":5,"userid":1,"dynamicid":1,"content":"你是不是傻？？？","create_time":0,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","reply_nickname":"孤央"},{"id":20,"parent_id":0,"reply_userid":6,"userid":2,"dynamicid":1,"content":"小作坊","create_time":1519614288,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","reply_nickname":"酒自斟"}]
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
            @Override
            public String toString() {
                return "DataBean{" +
                        "vip=" + vip +
                        ", id=" + id +
                        ", parent_id=" + parent_id +
                        ", reply_userid=" + reply_userid +
                        ", userid=" + userid +
                        ", dynamicid=" + dynamicid +
                        ", content='" + content + '\'' +
                        ", create_time=" + create_time +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", reply_nickname='" + reply_nickname + '\'' +
                        ", child=" + child +
                        '}';
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            /**
             * id : 2
             * parent_id : 0
             * reply_userid : 0
             * userid : 3
             * dynamicid : 1
             * content : 原图老哥谢谢
             * create_time : 1513394223
             * nickname : 九卿臣
             * avatar : http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg
             * child : [{"id":3,"parent_id":2,"reply_userid":3,"userid":2,"dynamicid":1,"content":"过不了多久，某宝就会有同款婴儿服了吧","create_time":1513394224,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","reply_nickname":"九卿臣"},{"id":4,"parent_id":2,"reply_userid":3,"userid":5,"dynamicid":1,"content":"那就买买买 然后攒着","create_time":1513394224,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","reply_nickname":"九卿臣"}]
             * reply_nickname : 孤央
             */

            private int vip;
            private int id;
            private int parent_id;
            private int reply_userid;
            private int userid;
            private int dynamicid;
            private String content;
            private long create_time;
            private String nickname;
            private String avatar;
            private String reply_nickname;
            private List<ChildBean> child;

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getReply_nickname() {
                return reply_nickname;
            }

            public void setReply_nickname(String reply_nickname) {
                this.reply_nickname = reply_nickname;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
                /**
                 * id : 3
                 * parent_id : 2
                 * reply_userid : 3
                 * userid : 2
                 * dynamicid : 1
                 * content : 过不了多久，某宝就会有同款婴儿服了吧
                 * create_time : 1513394224
                 * nickname : 李五
                 * avatar : http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg
                 * reply_nickname : 九卿臣
                 */

                private int id;
                private int parent_id;
                private int reply_userid;
                private int userid;
                private int dynamicid;
                private String content;
                private int create_time;
                private String nickname;
                private String avatar;
                private String reply_nickname;

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

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getReply_nickname() {
                    return reply_nickname;
                }

                public void setReply_nickname(String reply_nickname) {
                    this.reply_nickname = reply_nickname;
                }

                @Override
                public String toString() {
                    return "ChildBean{" +
                            "id=" + id +
                            ", parent_id=" + parent_id +
                            ", reply_userid=" + reply_userid +
                            ", userid=" + userid +
                            ", dynamicid=" + dynamicid +
                            ", content='" + content + '\'' +
                            ", create_time=" + create_time +
                            ", nickname='" + nickname + '\'' +
                            ", avatar='" + avatar + '\'' +
                            ", reply_nickname='" + reply_nickname + '\'' +
                            '}';
                }
            }
        }
    }
}
