package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.Comparator;
import java.util.List;

public class ShopSendedRedBagResponse {
    private int error;
    private String message;
    private DataBean data;

    public static class ShopSendedRedBagBean {
        private String id;
        private String voucherid;
        private String nickname;
        private String type;
        private String vname;
        private String vcontent;
        private String step;
        private String condition;
        private String money;
        private String amount;
        private String receive;
        private String day;
        private String businessname;
        private String businessid;
        private String businesslogo;
        private int status;
        private long e_time;
        private long create_time;
        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getId() {
            return id;
        }

        public String getNickname() {
            return nickname;
        }

        public long getE_time() {
            return e_time*1000;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getVoucherid() {
            return voucherid;
        }

        public String getBusinesslogo() {
            return businesslogo;
        }

        /**
         * 0正常|1已下架|2已过期|3已领完
         * @return
         */
        public int getStatus() {
            return status;
        }

        /**
         * 1满减，2无门槛，3叠加
         * @return
         */
        public String getType() {
            return type;
        }

        public String getVname() {
            return vname;
        }

        public String getVcontent() {
            return vcontent;
        }

        public String getStep() {
            return step;
        }

        public String getCondition() {
            return condition;
        }

        public String getMoney() {
            return money;
        }

        public String getAmount() {
            return amount;
        }

        public String getReceive() {
            return receive;
        }

        public String getDay() {
            return day;
        }

        public long getCreate_time() {
            return create_time*1000;
        }

        public String getBusinessname() {
            return businessname;
        }

        public String getBusinessid() {
            return businessid;
        }
    }
    /*{
        "error":0,
            "message":"success",
            "data":{
        "pagenation":{
            "page":1,
                    "pageSize":20,
                    "totalPage":1,
                    "totalCount":1
        },
        "data":[
        {
            "voucherid":2,
                "type":1,
                "vname":"欧特咳咳咳",
                "vcontent":"满2000元减86减",
                "step":10000,
                "condition":"2000.00",
                "money":"86.00",
                "amount":3,
                "receive":0,
                "day":20,
                "create_time":1531901871,
                "userid":25752,
                "nickname":"搞笑吧",
                "businessid":1551,
                "businessname":"嘟嘟嘟饿了"
        }
        ]
    }
    }*/
    public class DataBean {
        PagenationBean pagenation;
        List<ShopSendedRedBagBean> data;

        public List<ShopSendedRedBagBean> getData() {
            return data;
        }

        public PagenationBean getPagenation() {
            return pagenation;
        }
    }

    public DataBean getData() {
        return data;
    }

    public static class LatComparator implements Comparator<ShopSendedRedBagBean> {

        @Override
        public int compare(ShopSendedRedBagBean shopSendedRedBagResponse, ShopSendedRedBagBean t1) {
            return shopSendedRedBagResponse.getLatitude() - t1.getLatitude() < 0 ? -1 : 1;
        }
    }
    public static class LngComparator implements Comparator<ShopSendedRedBagBean> {

        @Override
        public int compare(ShopSendedRedBagBean shopSendedRedBagResponse, ShopSendedRedBagBean t1) {
            return shopSendedRedBagResponse.getLongitude() - t1.getLongitude() < 0 ? -1 : 1;
        }
    }
}
