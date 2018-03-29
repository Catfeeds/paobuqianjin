package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/29.
 */
/*
@className :FriendWeekResponse
*@date 2018/3/29
*@author
*@description 好友步数周排行
*/
public class FriendWeekResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":5},"data":{"member":[{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"37400"},{"userid":61,"nickname":"俊","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","step_number":"36000"},{"userid":66,"nickname":"小沙","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","step_number":"21820"},{"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"14359"},{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"}],"mydata":{"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"14359","rank":4}}}
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
        return "FriendWeekResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":5}
         * data : {"member":[{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"37400"},{"userid":61,"nickname":"俊","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","step_number":"36000"},{"userid":66,"nickname":"小沙","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","step_number":"21820"},{"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"14359"},{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"}],"mydata":{"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"14359","rank":4}}
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
             * totalPage : 1
             * totalCount : 5
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
             * member : [{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"37400"},{"userid":61,"nickname":"俊","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","step_number":"36000"},{"userid":66,"nickname":"小沙","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","step_number":"21820"},{"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"14359"},{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","step_number":"0"}]
             * mydata : {"userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","step_number":"14359","rank":4}
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
                 * userid : 1
                 * nickname : 嗯额
                 * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg
                 * step_number : 14359
                 * rank : 4
                 */

                private int userid;
                private String nickname;
                private String avatar;
                private String step_number;
                private int rank;

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
                            '}';
                }
            }

            public static class MemberBean {
                /**
                 * userid : 30
                 * nickname : 黄钦平
                 * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
                 * step_number : 37400
                 */

                private int userid;
                private String nickname;
                private String avatar;
                private String step_number;

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
                            '}';
                }
            }
        }
    }
}
