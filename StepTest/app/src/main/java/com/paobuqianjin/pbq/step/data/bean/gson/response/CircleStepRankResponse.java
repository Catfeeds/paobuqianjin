package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/17.
 */

public class CircleStepRankResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":{"id":30,"wx_unionid":"","wx_openid":"oPd5d0SDHRV1oZF8dB80Jq0kH_tY","qq_unionid":"","qq_openid":"","mobile":"13424156029","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","levelid":4,"sex":0,"education":0,"birthyear":1924,"birthmonth":7,"birthday":1,"height":200,"weight":"45.00","type":1,"province":"广东","city":"深圳","balance":"9865.28","target_step":5000,"credit":0,"status":0,"is_perfect":1,"create_time":1521794437,"delete_id":0,"delete_time":null,"logintimes":129,"h_long":null,"h_lat":null,"h_updatetime":0,"c_long":"0.000000","c_lat":"0.000000","c_updatetime":0,"n_long":"113.874750","n_lat":"22.575980","n_updatetime":1523984709,"circleid":100382,"circle":[{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"2462"},{"userid":206,"nickname":"（^O^）","avatar":"https://wx.qlogo.cn/mmopen/vi_32/qoicvAvQjJPE3r0EZSVbZ0dziaYklJialD5r4aN61yDGVtotUy4F1DWYib2ic6WLTbOTMJ7OI1OWdX0QFMS2Dq6GBAQ/0","step_number":"2019"}],"step_number":"2462","rank":1}}
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
        return "CircleStepRankResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : {"id":30,"wx_unionid":"","wx_openid":"oPd5d0SDHRV1oZF8dB80Jq0kH_tY","qq_unionid":"","qq_openid":"","mobile":"13424156029","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","levelid":4,"sex":0,"education":0,"birthyear":1924,"birthmonth":7,"birthday":1,"height":200,"weight":"45.00","type":1,"province":"广东","city":"深圳","balance":"9865.28","target_step":5000,"credit":0,"status":0,"is_perfect":1,"create_time":1521794437,"delete_id":0,"delete_time":null,"logintimes":129,"h_long":null,"h_lat":null,"h_updatetime":0,"c_long":"0.000000","c_lat":"0.000000","c_updatetime":0,"n_long":"113.874750","n_lat":"22.575980","n_updatetime":1523984709,"circleid":100382,"circle":[{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"2462"},{"userid":206,"nickname":"（^O^）","avatar":"https://wx.qlogo.cn/mmopen/vi_32/qoicvAvQjJPE3r0EZSVbZ0dziaYklJialD5r4aN61yDGVtotUy4F1DWYib2ic6WLTbOTMJ7OI1OWdX0QFMS2Dq6GBAQ/0","step_number":"2019"}],"step_number":"2462","rank":1}
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
            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            /**
             * id : 30
             * wx_unionid :
             * wx_openid : oPd5d0SDHRV1oZF8dB80Jq0kH_tY
             * qq_unionid :
             * qq_openid :
             * mobile : 13424156029
             * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
             * nickname : 黄钦平
             * levelid : 4
             * sex : 0
             * education : 0
             * birthyear : 1924
             * birthmonth : 7
             * birthday : 1
             * height : 200
             * weight : 45.00
             * type : 1
             * province : 广东
             * city : 深圳
             * balance : 9865.28
             * target_step : 5000
             * credit : 0
             * status : 0
             * is_perfect : 1
             * create_time : 1521794437
             * delete_id : 0
             * delete_time : null
             * logintimes : 129
             * h_long : null
             * h_lat : null
             * h_updatetime : 0
             * c_long : 0.000000
             * c_lat : 0.000000
             * c_updatetime : 0
             * n_long : 113.874750
             * n_lat : 22.575980
             * n_updatetime : 1523984709
             * circleid : 100382
             * circle : [{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":"2462"},{"userid":206,"nickname":"（^O^）","avatar":"https://wx.qlogo.cn/mmopen/vi_32/qoicvAvQjJPE3r0EZSVbZ0dziaYklJialD5r4aN61yDGVtotUy4F1DWYib2ic6WLTbOTMJ7OI1OWdX0QFMS2Dq6GBAQ/0","step_number":"2019"}]
             * step_number : 2462
             * rank : 1
             */
            private int vip;
            private int id;
            private String wx_unionid;
            private String wx_openid;
            private String qq_unionid;
            private String qq_openid;
            private String mobile;
            private String avatar;
            private String nickname;
            private int levelid;
            private int sex;
            private int education;
            private int birthyear;
            private int birthmonth;
            private int birthday;
            private int height;
            private String weight;
            private int type;
            private String province;
            private String city;
            private String balance;
            private int target_step;
            private int credit;
            private int status;
            private int is_perfect;
            private int create_time;
            private int delete_id;
            private Object delete_time;
            private int logintimes;
            private Object h_long;
            private Object h_lat;
            private int h_updatetime;
            private String c_long;
            private String c_lat;
            private int c_updatetime;
            private String n_long;
            private String n_lat;
            private int n_updatetime;
            private int circleid;
            private String step_number;
            private int rank;
            private List<CircleBean> circle;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getWx_unionid() {
                return wx_unionid;
            }

            public void setWx_unionid(String wx_unionid) {
                this.wx_unionid = wx_unionid;
            }

            public String getWx_openid() {
                return wx_openid;
            }

            public void setWx_openid(String wx_openid) {
                this.wx_openid = wx_openid;
            }

            public String getQq_unionid() {
                return qq_unionid;
            }

            public void setQq_unionid(String qq_unionid) {
                this.qq_unionid = qq_unionid;
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

            public int getEducation() {
                return education;
            }

            public void setEducation(int education) {
                this.education = education;
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

            public int getTarget_step() {
                return target_step;
            }

            public void setTarget_step(int target_step) {
                this.target_step = target_step;
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

            public int getDelete_id() {
                return delete_id;
            }

            public void setDelete_id(int delete_id) {
                this.delete_id = delete_id;
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

            public Object getH_long() {
                return h_long;
            }

            public void setH_long(Object h_long) {
                this.h_long = h_long;
            }

            public Object getH_lat() {
                return h_lat;
            }

            public void setH_lat(Object h_lat) {
                this.h_lat = h_lat;
            }

            public int getH_updatetime() {
                return h_updatetime;
            }

            public void setH_updatetime(int h_updatetime) {
                this.h_updatetime = h_updatetime;
            }

            public String getC_long() {
                return c_long;
            }

            public void setC_long(String c_long) {
                this.c_long = c_long;
            }

            public String getC_lat() {
                return c_lat;
            }

            public void setC_lat(String c_lat) {
                this.c_lat = c_lat;
            }

            public int getC_updatetime() {
                return c_updatetime;
            }

            public void setC_updatetime(int c_updatetime) {
                this.c_updatetime = c_updatetime;
            }

            public String getN_long() {
                return n_long;
            }

            public void setN_long(String n_long) {
                this.n_long = n_long;
            }

            public String getN_lat() {
                return n_lat;
            }

            public void setN_lat(String n_lat) {
                this.n_lat = n_lat;
            }

            public int getN_updatetime() {
                return n_updatetime;
            }

            public void setN_updatetime(int n_updatetime) {
                this.n_updatetime = n_updatetime;
            }

            public int getCircleid() {
                return circleid;
            }

            public void setCircleid(int circleid) {
                this.circleid = circleid;
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

            public List<CircleBean> getCircle() {
                return circle;
            }

            public void setCircle(List<CircleBean> circle) {
                this.circle = circle;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", wx_unionid='" + wx_unionid + '\'' +
                        ", wx_openid='" + wx_openid + '\'' +
                        ", qq_unionid='" + qq_unionid + '\'' +
                        ", qq_openid='" + qq_openid + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", levelid=" + levelid +
                        ", sex=" + sex +
                        ", education=" + education +
                        ", birthyear=" + birthyear +
                        ", birthmonth=" + birthmonth +
                        ", birthday=" + birthday +
                        ", height=" + height +
                        ", weight='" + weight + '\'' +
                        ", type=" + type +
                        ", province='" + province + '\'' +
                        ", city='" + city + '\'' +
                        ", balance='" + balance + '\'' +
                        ", target_step=" + target_step +
                        ", credit=" + credit +
                        ", status=" + status +
                        ", is_perfect=" + is_perfect +
                        ", create_time=" + create_time +
                        ", delete_id=" + delete_id +
                        ", delete_time=" + delete_time +
                        ", logintimes=" + logintimes +
                        ", h_long=" + h_long +
                        ", h_lat=" + h_lat +
                        ", h_updatetime=" + h_updatetime +
                        ", c_long='" + c_long + '\'' +
                        ", c_lat='" + c_lat + '\'' +
                        ", c_updatetime=" + c_updatetime +
                        ", n_long='" + n_long + '\'' +
                        ", n_lat='" + n_lat + '\'' +
                        ", n_updatetime=" + n_updatetime +
                        ", circleid=" + circleid +
                        ", step_number='" + step_number + '\'' +
                        ", rank=" + rank +
                        ", circle=" + circle +
                        '}';
            }

            public static class CircleBean {
                /**
                 * userid : 30
                 * nickname : 黄钦平
                 * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
                 * step_number : 2462
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
                    return "CircleBean{" +
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
