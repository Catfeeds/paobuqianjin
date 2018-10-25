package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/10/25.
 */

public class InviteTopResponse {
    /**
     * error : 0
     * message : success
     * data : {"Inumber":1,"Icredit":626,"Imoney":0,"Ilist":{"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":1},"data":[{"id":45384,"inviterid":11241,"userid":71250,"create_time":1529655169,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","nickname":"rm_18296653180","vip":0,"gvip":0,"sub_inviter_count":0}]}}
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

    public static class DataBeanX {
        /**
         * Inumber : 1
         * Icredit : 626
         * Imoney : 0
         * Ilist : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":1},"data":[{"id":45384,"inviterid":11241,"userid":71250,"create_time":1529655169,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","nickname":"rm_18296653180","vip":0,"gvip":0,"sub_inviter_count":0}]}
         */

        private String Inumber;
        private String Icredit;
        private String Imoney;
        private IlistBean Ilist;

        public String getInumber() {
            return Inumber;
        }

        public void setInumber(String Inumber) {
            this.Inumber = Inumber;
        }

        public String getIcredit() {
            return Icredit;
        }

        public void setIcredit(String Icredit) {
            this.Icredit = Icredit;
        }

        public String getImoney() {
            return Imoney;
        }

        public void setImoney(String Imoney) {
            this.Imoney = Imoney;
        }

        public IlistBean getIlist() {
            return Ilist;
        }

        public void setIlist(IlistBean Ilist) {
            this.Ilist = Ilist;
        }

        public static class IlistBean {
            /**
             * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":1}
             * data : [{"id":45384,"inviterid":11241,"userid":71250,"create_time":1529655169,"avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","nickname":"rm_18296653180","vip":0,"gvip":0,"sub_inviter_count":0}]
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
                 * totalCount : 1
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
            }

            public static class DataBean {
                /**
                 * id : 45384
                 * inviterid : 11241
                 * userid : 71250
                 * create_time : 1529655169
                 * avatar : https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png
                 * nickname : rm_18296653180
                 * vip : 0
                 * gvip : 0
                 * sub_inviter_count : 0
                 */

                private String id;
                private String inviterid;
                private int userid;
                private long create_time;
                private String avatar;
                private String nickname;
                private int vip;
                private int gvip;
                private int sub_inviter_count;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getInviterid() {
                    return inviterid;
                }

                public void setInviterid(String inviterid) {
                    this.inviterid = inviterid;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
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

                public int getVip() {
                    return vip;
                }

                public void setVip(int vip) {
                    this.vip = vip;
                }

                public int getGvip() {
                    return gvip;
                }

                public void setGvip(int gvip) {
                    this.gvip = gvip;
                }

                public int getSub_inviter_count() {
                    return sub_inviter_count;
                }

                public void setSub_inviter_count(int sub_inviter_count) {
                    this.sub_inviter_count = sub_inviter_count;
                }
            }
        }
    }
}
