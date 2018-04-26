package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/16.
 */

public class FriendStepRankDayResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":11},"data":{"member":[{"userid":57,"nickname":"周周周","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIxXJTVJvRmxPhfS0aNF0pOXn3EdZ96DabUIULlH1E9mUbEZCE98IPEzqTgwD7jtSqfqiaUFV0rIsQ/132","step_number":"6000"},{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"291"},{"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"0"},{"userid":2,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","step_number":"0"},{"userid":3,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","step_number":"0"},{"userid":5,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","step_number":"0"},{"userid":6,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"},{"userid":7,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","step_number":"0"},{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"},{"userid":9,"nickname":"孤傲王者","avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","step_number":"0"},{"userid":10,"nickname":"孤君独战","avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","step_number":"0"},{"userid":66,"nickname":"小沙","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","step_number":"0"}],"mydata":{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"291","rank":2}}}
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
        return "FriendStepRankDayResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":11}
         * data : {"member":[{"userid":57,"nickname":"周周周","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIxXJTVJvRmxPhfS0aNF0pOXn3EdZ96DabUIULlH1E9mUbEZCE98IPEzqTgwD7jtSqfqiaUFV0rIsQ/132","step_number":"6000"},{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"291"},{"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"0"},{"userid":2,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","step_number":"0"},{"userid":3,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","step_number":"0"},{"userid":5,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","step_number":"0"},{"userid":6,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"},{"userid":7,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","step_number":"0"},{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"},{"userid":9,"nickname":"孤傲王者","avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","step_number":"0"},{"userid":10,"nickname":"孤君独战","avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","step_number":"0"},{"userid":66,"nickname":"小沙","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","step_number":"0"}],"mydata":{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"291","rank":2}}
         */

        private PagenationBean pagenation;
        private DataBean data;

        public PagenationBean getPagenation() {
            return pagenation;
        }

        public void setPagenation(PagenationBean pagenation) {
            this.pagenation = pagenation;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
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
             * totalCount : 11
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
             * member : [{"userid":57,"nickname":"周周周","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIxXJTVJvRmxPhfS0aNF0pOXn3EdZ96DabUIULlH1E9mUbEZCE98IPEzqTgwD7jtSqfqiaUFV0rIsQ/132","step_number":"6000"},{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"291"},{"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"0"},{"userid":2,"nickname":"李五","avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","step_number":"0"},{"userid":3,"nickname":"九卿臣","avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","step_number":"0"},{"userid":5,"nickname":"孤央","avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","step_number":"0"},{"userid":6,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"},{"userid":7,"nickname":"沉秋","avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","step_number":"0"},{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"},{"userid":9,"nickname":"孤傲王者","avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","step_number":"0"},{"userid":10,"nickname":"孤君独战","avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","step_number":"0"},{"userid":66,"nickname":"小沙","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","step_number":"0"}]
             * mydata : {"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"291","rank":2}
             */

            private MydataBean mydata;
            private List<MemberBean> member;

            public MydataBean getMydata() {
                return mydata;
            }

            public void setMydata(MydataBean mydata) {
                this.mydata = mydata;
            }

            public List<MemberBean> getMember() {
                return member;
            }

            public void setMember(List<MemberBean> member) {
                this.member = member;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "mydata=" + mydata +
                        ", member=" + member +
                        '}';
            }

            public static class MydataBean {
                /**
                 * userid : 30
                 * nickname : 黄钦平
                 * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
                 * step_number : 291
                 * rank : 2
                 */

                private int userid;
                private String nickname;
                private String avatar;
                private String step_number;
                private int rank;

                public int getVip() {
                    return vip;
                }

                public void setVip(int vip) {
                    this.vip = vip;
                }

                private int vip;

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
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

                public String getStep_number() {
                    return step_number;
                }

                public void setStep_number(String step_number) {
                    this.step_number = step_number;
                }

                public int getRank() {
                    return rank;
                }

                public void setRank(int rank) {
                    this.rank = rank;
                }

                @Override
                public String toString() {
                    return "MydataBean{" +
                            "userid=" + userid +
                            ", nickname='" + nickname + '\'' +
                            ", avatar='" + avatar + '\'' +
                            ", step_number='" + step_number + '\'' +
                            ", rank=" + rank +
                            ", vip=" + vip +
                            '}';
                }
            }

            public static class MemberBean {
                /**
                 * userid : 57
                 * nickname : 周周周
                 * avatar : https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIxXJTVJvRmxPhfS0aNF0pOXn3EdZ96DabUIULlH1E9mUbEZCE98IPEzqTgwD7jtSqfqiaUFV0rIsQ/132
                 * step_number : 6000
                 */

                private int userid;
                private String nickname;
                private String avatar;
                private String step_number;

                public int getVip() {
                    return vip;
                }

                public void setVip(int vip) {
                    this.vip = vip;
                }

                private int vip;

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
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

                public String getStep_number() {
                    return step_number;
                }

                public void setStep_number(String step_number) {
                    this.step_number = step_number;
                }

                @Override
                public String toString() {
                    return "MemberBean{" +
                            "userid=" + userid +
                            ", nickname='" + nickname + '\'' +
                            ", avatar='" + avatar + '\'' +
                            ", step_number='" + step_number + '\'' +
                            ", vip=" + vip +
                            '}';
                }
            }
        }
    }
}
