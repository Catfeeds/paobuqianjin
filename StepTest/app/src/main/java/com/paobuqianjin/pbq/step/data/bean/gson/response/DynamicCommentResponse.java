package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class DynamicCommentResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":9},"data":[{"id":4,"parent_id":0,"reply_userid":0,"userid":5,"dynamicid":1,"content":"那就买买买 然后攒着","creat_time":1513394224,"nickname":"孤央","child":[{"id":5,"parent_id":4,"reply_userid":5,"userid":6,"dynamicid":1,"content":"励志 我罗牛逼","creat_time":1513394224,"nickname":"青冘","reply_nickname":"孤央"},{"id":6,"parent_id":4,"reply_userid":6,"userid":7,"dynamicid":1,"content":"大家误会了，这位巴萨球迷是在自我介绍","creat_time":1513394224,"nickname":"沉秋","reply_nickname":"青冘"},{"id":7,"parent_id":4,"reply_userid":6,"userid":8,"dynamicid":1,"content":"前几天梅西得的是什么奖来着","creat_time":1513394224,"nickname":"酒自斟","reply_nickname":"青冘"}]},{"id":8,"parent_id":0,"reply_userid":0,"userid":9,"dynamicid":1,"content":"三票罗的逆袭","creat_time":1513394224,"nickname":"孤傲王者"},{"id":9,"parent_id":0,"reply_userid":0,"userid":10,"dynamicid":1,"content":"所有的成功，都是日夜苦练的褒奖！","creat_time":1513394224,"nickname":"孤君独战"},{"id":16,"parent_id":0,"reply_userid":0,"userid":1,"dynamicid":1,"content":"你是不是傻？？？","creat_time":0,"nickname":"陈杰"}]}
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
        return "DynamicCommentResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":9}
         * data : [{"id":4,"parent_id":0,"reply_userid":0,"userid":5,"dynamicid":1,"content":"那就买买买 然后攒着","creat_time":1513394224,"nickname":"孤央","child":[{"id":5,"parent_id":4,"reply_userid":5,"userid":6,"dynamicid":1,"content":"励志 我罗牛逼","creat_time":1513394224,"nickname":"青冘","reply_nickname":"孤央"},{"id":6,"parent_id":4,"reply_userid":6,"userid":7,"dynamicid":1,"content":"大家误会了，这位巴萨球迷是在自我介绍","creat_time":1513394224,"nickname":"沉秋","reply_nickname":"青冘"},{"id":7,"parent_id":4,"reply_userid":6,"userid":8,"dynamicid":1,"content":"前几天梅西得的是什么奖来着","creat_time":1513394224,"nickname":"酒自斟","reply_nickname":"青冘"}]},{"id":8,"parent_id":0,"reply_userid":0,"userid":9,"dynamicid":1,"content":"三票罗的逆袭","creat_time":1513394224,"nickname":"孤傲王者"},{"id":9,"parent_id":0,"reply_userid":0,"userid":10,"dynamicid":1,"content":"所有的成功，都是日夜苦练的褒奖！","creat_time":1513394224,"nickname":"孤君独战"},{"id":16,"parent_id":0,"reply_userid":0,"userid":1,"dynamicid":1,"content":"你是不是傻？？？","creat_time":0,"nickname":"陈杰"}]
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
             * totalCount : 9
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
             * id : 4
             * parent_id : 0
             * reply_userid : 0
             * userid : 5
             * dynamicid : 1
             * content : 那就买买买 然后攒着
             * creat_time : 1513394224
             * nickname : 孤央
             * child : [{"id":5,"parent_id":4,"reply_userid":5,"userid":6,"dynamicid":1,"content":"励志 我罗牛逼","creat_time":1513394224,"nickname":"青冘","reply_nickname":"孤央"},{"id":6,"parent_id":4,"reply_userid":6,"userid":7,"dynamicid":1,"content":"大家误会了，这位巴萨球迷是在自我介绍","creat_time":1513394224,"nickname":"沉秋","reply_nickname":"青冘"},{"id":7,"parent_id":4,"reply_userid":6,"userid":8,"dynamicid":1,"content":"前几天梅西得的是什么奖来着","creat_time":1513394224,"nickname":"酒自斟","reply_nickname":"青冘"}]
             */

            private int id;
            private int parent_id;
            private int reply_userid;
            private int userid;
            private int dynamicid;
            private String content;
            private int creat_time;
            private String nickname;
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

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", parent_id=" + parent_id +
                        ", reply_userid=" + reply_userid +
                        ", userid=" + userid +
                        ", dynamicid=" + dynamicid +
                        ", content='" + content + '\'' +
                        ", creat_time=" + creat_time +
                        ", nickname='" + nickname + '\'' +
                        ", child=" + child +
                        '}';
            }

            public static class ChildBean {
                /**
                 * id : 5
                 * parent_id : 4
                 * reply_userid : 5
                 * userid : 6
                 * dynamicid : 1
                 * content : 励志 我罗牛逼
                 * creat_time : 1513394224
                 * nickname : 青冘
                 * reply_nickname : 孤央
                 */

                private int id;
                private int parent_id;
                private int reply_userid;
                private int userid;
                private int dynamicid;
                private String content;
                private int creat_time;
                private String nickname;
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
                            ", creat_time=" + creat_time +
                            ", nickname='" + nickname + '\'' +
                            ", reply_nickname='" + reply_nickname + '\'' +
                            '}';
                }
            }
        }
    }
}
