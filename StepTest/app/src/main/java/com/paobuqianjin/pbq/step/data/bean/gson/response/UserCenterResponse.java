package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/5/21.
 */
/*
@className :UserCenterResponse
*@date 2018/5/21
*@author
*@description 个人主页复合接口
*/
public class UserCenterResponse {
    /**
     * error : 0
     * message : success
     * data : {"user_data":{"id":30,"no":30,"wx_unionid":"oOR3P1Oyq7Xt6xFRx7otKbA3YdcU","qq_unionid":"","wx_openid":"oPd5d0SDHRV1oZF8dB80Jq0kH_tY","xcx_openid":"","qq_openid":"838E8FC341776719299893171D5F552C","mobile":"13424156029","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","levelid":8,"sex":1,"birthyear":1911,"birthmonth":7,"birthday":1,"height":150,"weight":"15.00","type":1,"province":"广东","city":"深圳","balance":"189.12","credit":0,"status":0,"is_perfect":1,"create_time":1521794437,"delete_time":null,"logintimes":371,"vip":1,"target_step":5000},"is_follow":0,"dynamic_data":{"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"id":631,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"5bmy5rS75ZOI5ZOI\n","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/sinaimgpath.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-03-01-08-47-31-638_lockscreen.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/58307cf327400000.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/5830108793400000.jpg"],"city":"北京市","country":"","province":"","county":"","village":"","position":"北京宝辰饭店","vote":1,"comment":6,"create_time":1526888979,"one_comment":{"id":830,"dynamicid":631,"parent_id":0,"reply_userid":30,"userid":11228,"content":"白白嫩嫩扭扭捏捏叫姐姐","create_time":1526889124,"delete_id":0,"delete_time":0,"nickname":"九洲涧"},"is_vote":0},{"id":577,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"5ZO85ZO85ZSn5ZSn\n","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/VID_20171004_163925.jpg"],"city":"深圳市","country":"","province":"","county":"","village":"","position":"","vote":0,"comment":0,"create_time":1526630061,"one_comment":{},"is_vote":0}]}}
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
        return "UserCenterResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * user_data : {"id":30,"no":30,"wx_unionid":"oOR3P1Oyq7Xt6xFRx7otKbA3YdcU","qq_unionid":"","wx_openid":"oPd5d0SDHRV1oZF8dB80Jq0kH_tY","xcx_openid":"","qq_openid":"838E8FC341776719299893171D5F552C","mobile":"13424156029","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","levelid":8,"sex":1,"birthyear":1911,"birthmonth":7,"birthday":1,"height":150,"weight":"15.00","type":1,"province":"广东","city":"深圳","balance":"189.12","credit":0,"status":0,"is_perfect":1,"create_time":1521794437,"delete_time":null,"logintimes":371,"vip":1,"target_step":5000}
         * is_follow : 0
         * dynamic_data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"id":631,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"5bmy5rS75ZOI5ZOI\n","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/sinaimgpath.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-03-01-08-47-31-638_lockscreen.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/58307cf327400000.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/5830108793400000.jpg"],"city":"北京市","country":"","province":"","county":"","village":"","position":"北京宝辰饭店","vote":1,"comment":6,"create_time":1526888979,"one_comment":{"id":830,"dynamicid":631,"parent_id":0,"reply_userid":30,"userid":11228,"content":"白白嫩嫩扭扭捏捏叫姐姐","create_time":1526889124,"delete_id":0,"delete_time":0,"nickname":"九洲涧"},"is_vote":0},{"id":577,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"5ZO85ZO85ZSn5ZSn\n","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/VID_20171004_163925.jpg"],"city":"深圳市","country":"","province":"","county":"","village":"","position":"","vote":0,"comment":0,"create_time":1526630061,"one_comment":{},"is_vote":0}]}
         */

        private UserDataBean user_data;
        private int is_follow;
        private DynamicDataBean dynamic_data;

        public UserDataBean getUser_data() {
            return user_data;
        }

        public void setUser_data(UserDataBean user_data) {
            this.user_data = user_data;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }

        public DynamicDataBean getDynamic_data() {
            return dynamic_data;
        }

        public void setDynamic_data(DynamicDataBean dynamic_data) {
            this.dynamic_data = dynamic_data;
        }

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "user_data=" + user_data +
                    ", is_follow=" + is_follow +
                    ", dynamic_data=" + dynamic_data +
                    '}';
        }

        public static class UserDataBean {
            /**
             * id : 30
             * no : 30
             * wx_unionid : oOR3P1Oyq7Xt6xFRx7otKbA3YdcU
             * qq_unionid :
             * wx_openid : oPd5d0SDHRV1oZF8dB80Jq0kH_tY
             * xcx_openid :
             * qq_openid : 838E8FC341776719299893171D5F552C
             * mobile : 13424156029
             * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
             * nickname : 黄钦平
             * levelid : 8
             * sex : 1
             * birthyear : 1911
             * birthmonth : 7
             * birthday : 1
             * height : 150
             * weight : 15.00
             * type : 1
             * province : 广东
             * city : 深圳
             * balance : 189.12
             * credit : 0
             * status : 0
             * is_perfect : 1
             * create_time : 1521794437
             * delete_time : null
             * logintimes : 371
             * vip : 1
             * target_step : 5000
             * cusvip: 0
             */

            private int id;
            private int no;
            private String wx_unionid;
            private String qq_unionid;
            private String wx_openid;
            private String xcx_openid;
            private String qq_openid;
            private String mobile;
            private String avatar;
            private String nickname;
            private int levelid;
            private int sex;
            private int birthyear;
            private int birthmonth;
            private int birthday;
            private int height;
            private String weight;
            private int type;
            private String province;
            private String city;
            private String balance;
            private int credit;
            private int status;
            private int is_perfect;
            private int create_time;
            private Object delete_time;
            private int logintimes;
            private int vip;
            private int target_step;

            public int getCusvip() {
                return cusvip;
            }

            public void setCusvip(int cusvip) {
                this.cusvip = cusvip;
            }

            private int cusvip;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public String getWx_unionid() {
                return wx_unionid;
            }

            public void setWx_unionid(String wx_unionid) {
                this.wx_unionid = wx_unionid;
            }

            public String getQq_unionid() {
                return qq_unionid;
            }

            public void setQq_unionid(String qq_unionid) {
                this.qq_unionid = qq_unionid;
            }

            public String getWx_openid() {
                return wx_openid;
            }

            public void setWx_openid(String wx_openid) {
                this.wx_openid = wx_openid;
            }

            public String getXcx_openid() {
                return xcx_openid;
            }

            public void setXcx_openid(String xcx_openid) {
                this.xcx_openid = xcx_openid;
            }

            public String getQq_openid() {
                return qq_openid;
            }

            public void setQq_openid(String qq_openid) {
                this.qq_openid = qq_openid;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public int getLevelid() {
                return levelid;
            }

            public void setLevelid(int levelid) {
                this.levelid = levelid;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getBirthyear() {
                return birthyear;
            }

            public void setBirthyear(int birthyear) {
                this.birthyear = birthyear;
            }

            public int getBirthmonth() {
                return birthmonth;
            }

            public void setBirthmonth(int birthmonth) {
                this.birthmonth = birthmonth;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public int getCredit() {
                return credit;
            }

            public void setCredit(int credit) {
                this.credit = credit;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_perfect() {
                return is_perfect;
            }

            public void setIs_perfect(int is_perfect) {
                this.is_perfect = is_perfect;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public Object getDelete_time() {
                return delete_time;
            }

            public void setDelete_time(Object delete_time) {
                this.delete_time = delete_time;
            }

            public int getLogintimes() {
                return logintimes;
            }

            public void setLogintimes(int logintimes) {
                this.logintimes = logintimes;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public int getTarget_step() {
                return target_step;
            }

            public void setTarget_step(int target_step) {
                this.target_step = target_step;
            }

            @Override
            public String toString() {
                return "UserDataBean{" +
                        "id=" + id +
                        ", no=" + no +
                        ", wx_unionid='" + wx_unionid + '\'' +
                        ", qq_unionid='" + qq_unionid + '\'' +
                        ", wx_openid='" + wx_openid + '\'' +
                        ", xcx_openid='" + xcx_openid + '\'' +
                        ", qq_openid='" + qq_openid + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", levelid=" + levelid +
                        ", sex=" + sex +
                        ", birthyear=" + birthyear +
                        ", birthmonth=" + birthmonth +
                        ", birthday=" + birthday +
                        ", height=" + height +
                        ", weight='" + weight + '\'' +
                        ", type=" + type +
                        ", province='" + province + '\'' +
                        ", city='" + city + '\'' +
                        ", balance='" + balance + '\'' +
                        ", credit=" + credit +
                        ", status=" + status +
                        ", is_perfect=" + is_perfect +
                        ", create_time=" + create_time +
                        ", delete_time=" + delete_time +
                        ", logintimes=" + logintimes +
                        ", vip=" + vip +
                        ", target_step=" + target_step +
                        ", cusvip=" + cusvip +
                        '}';
            }
        }

        public static class DynamicDataBean {
            /**
             * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
             * data : [{"id":631,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"5bmy5rS75ZOI5ZOI\n","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/sinaimgpath.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-03-01-08-47-31-638_lockscreen.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/58307cf327400000.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/5830108793400000.jpg"],"city":"北京市","country":"","province":"","county":"","village":"","position":"北京宝辰饭店","vote":1,"comment":6,"create_time":1526888979,"one_comment":{"id":830,"dynamicid":631,"parent_id":0,"reply_userid":30,"userid":11228,"content":"白白嫩嫩扭扭捏捏叫姐姐","create_time":1526889124,"delete_id":0,"delete_time":0,"nickname":"九洲涧"},"is_vote":0},{"id":577,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"5ZO85ZO85ZSn5ZSn\n","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/VID_20171004_163925.jpg"],"city":"深圳市","country":"","province":"","county":"","village":"","position":"","vote":0,"comment":0,"create_time":1526630061,"one_comment":{},"is_vote":0}]
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
                return "DynamicDataBean{" +
                        "pagenation=" + pagenation +
                        ", data=" + data +
                        '}';
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
                 * id : 631
                 * userid : 30
                 * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
                 * nickname : 黄钦平
                 * dynamic : 5bmy5rS75ZOI5ZOI

                 * images : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/sinaimgpath.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/Screenshot_2018-03-01-08-47-31-638_lockscreen.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/58307cf327400000.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/5830108793400000.jpg"]
                 * city : 北京市
                 * country :
                 * province :
                 * county :
                 * village :
                 * position : 北京宝辰饭店
                 * vote : 1
                 * comment : 6
                 * create_time : 1526888979
                 * one_comment : {"id":830,"dynamicid":631,"parent_id":0,"reply_userid":30,"userid":11228,"content":"白白嫩嫩扭扭捏捏叫姐姐","create_time":1526889124,"delete_id":0,"delete_time":0,"nickname":"九洲涧"}
                 * is_vote : 0
                 */

                private int id;
                private int userid;
                private String avatar;
                private String nickname;
                private String dynamic;
                private String city;
                private String country;
                private String province;
                private String county;
                private String village;
                private String position;
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

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public String getVillage() {
                    return village;
                }

                public void setVillage(String village) {
                    this.village = village;
                }

                public String getPosition() {
                    return position;
                }

                public void setPosition(String position) {
                    this.position = position;
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
                            ", country='" + country + '\'' +
                            ", province='" + province + '\'' +
                            ", county='" + county + '\'' +
                            ", village='" + village + '\'' +
                            ", position='" + position + '\'' +
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
                     * id : 830
                     * dynamicid : 631
                     * parent_id : 0
                     * reply_userid : 30
                     * userid : 11228
                     * content : 白白嫩嫩扭扭捏捏叫姐姐
                     * create_time : 1526889124
                     * delete_id : 0
                     * delete_time : 0
                     * nickname : 九洲涧
                     */

                    private int id;
                    private int dynamicid;
                    private int parent_id;
                    private int reply_userid;
                    private int userid;
                    private String content;
                    private int create_time;
                    private int delete_id;
                    private int delete_time;
                    private String nickname;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getDynamicid() {
                        return dynamicid;
                    }

                    public void setDynamicid(int dynamicid) {
                        this.dynamicid = dynamicid;
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

                    public int getDelete_id() {
                        return delete_id;
                    }

                    public void setDelete_id(int delete_id) {
                        this.delete_id = delete_id;
                    }

                    public int getDelete_time() {
                        return delete_time;
                    }

                    public void setDelete_time(int delete_time) {
                        this.delete_time = delete_time;
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
                                ", dynamicid=" + dynamicid +
                                ", parent_id=" + parent_id +
                                ", reply_userid=" + reply_userid +
                                ", userid=" + userid +
                                ", content='" + content + '\'' +
                                ", create_time=" + create_time +
                                ", delete_id=" + delete_id +
                                ", delete_time=" + delete_time +
                                ", nickname='" + nickname + '\'' +
                                '}';
                    }
                }
            }
        }
    }
}
