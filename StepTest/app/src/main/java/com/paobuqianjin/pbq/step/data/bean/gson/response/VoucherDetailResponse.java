package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by Administrator on 2018/8/2.
 */

public class VoucherDetailResponse {
    private int error;
    private String message;
    private VoucherDetailBean data;

    public VoucherDetailBean getData() {
        return data;
    }


    public class VoucherDetailBean {
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
        private String logo;
        private String phone;
        private String addra;
        private String address;
        private long e_time;
        private long create_time;
        private String longitude;
        private String latitude;
        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLogo() {
            return logo;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddra() {
            return addra;
        }

        public String getAddress() {
            return address;
        }

        public String getNickname() {
            return nickname;
        }

        public long getE_time() {
            return e_time*1000;
        }


        public String getVoucherid() {
            return voucherid;
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
}
