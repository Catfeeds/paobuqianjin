package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.Comparator;
import java.util.List;

public class AroundRedBagResponse {
    private int error;
    private String message;
    private DataBean data;

    public class AroundRedBagBean {
        /* "red_id":41,
                 "userid":35881,
                 "businessid":1637,
                 "content":"叶孤城",
                 "number":2,
                 "money":"31",
                 "receive_num":0,
                 "target_url":""
                 "status":1*/
        private String red_id;
        private String userid;
        private String businessid;
        private String number;
        private String money;
        private String receive_num;
        private String target_url;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        private int status;

        public String getBusinessid() {
            return businessid;
        }

        public String getRed_id() {
            return red_id;
        }

        public String getUserid() {
            return userid;
        }

        public String getNumber() {
            return number;
        }

        public String getMoney() {
            return money;
        }

        public String getReceive_num() {
            return receive_num;
        }

        public String getTarget_url() {
            return target_url;
        }
    }

    public class DataBean {
        /*"is_receive":0
        * */
        int remain_time;

        public int getIs_receive() {
            return is_receive;
        }

        public void setIs_receive(int is_receive) {
            this.is_receive = is_receive;
        }

        int is_receive;
        List<AroundRedBagBean> redpacket_list;

        /**
         * 秒
         *
         * @return
         */
        public int getRemain_time() {
            return remain_time;
        }

        public List<AroundRedBagBean> getRedpacket_list() {
            return redpacket_list;
        }
    }

    public DataBean getData() {
        return data;
    }

}
