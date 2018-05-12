package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/27.
 */
/*
@className :MyInviteResponse
*@date 2018/2/27
*@author
*@description 个人邀请信息
*/
public class MyInviteResponse {

    /**
     * error : 0
     * message : success
     * data : {"Inumber":1,"Icredit":0,"Imoney":50,"Ilist":[{"id":33,"inviterid":66,"userid":3,"code":"66T3","create_time":1526106630}]}
     */

    private int error;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Inumber : 1
         * Icredit : 0
         * Imoney : 50
         * Ilist : [{"id":33,"inviterid":66,"userid":3,"code":"66T3","create_time":1526106630}]
         */

        private int Inumber;
        private int Icredit;
        private int Imoney;
        private List<IlistBean> Ilist;

        public int getInumber() {
            return Inumber;
        }

        public void setInumber(int Inumber) {
            this.Inumber = Inumber;
        }

        public int getIcredit() {
            return Icredit;
        }

        public void setIcredit(int Icredit) {
            this.Icredit = Icredit;
        }

        public int getImoney() {
            return Imoney;
        }

        public void setImoney(int Imoney) {
            this.Imoney = Imoney;
        }

        public List<IlistBean> getIlist() {
            return Ilist;
        }

        public void setIlist(List<IlistBean> Ilist) {
            this.Ilist = Ilist;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "Inumber=" + Inumber +
                    ", Icredit=" + Icredit +
                    ", Imoney=" + Imoney +
                    ", Ilist=" + Ilist +
                    '}';
        }

        public static class IlistBean {
            /**
             * id : 33
             * inviterid : 66
             * userid : 3
             * code : 66T3
             * create_time : 1526106630
             */

            private int id;
            private int inviterid;
            private int userid;
            private String code;
            private int create_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getInviterid() {
                return inviterid;
            }

            public void setInviterid(int inviterid) {
                this.inviterid = inviterid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            @Override
            public String toString() {
                return "IlistBean{" +
                        "id=" + id +
                        ", inviterid=" + inviterid +
                        ", userid=" + userid +
                        ", code='" + code + '\'' +
                        ", create_time=" + create_time +
                        '}';
            }
        }
    }
}
