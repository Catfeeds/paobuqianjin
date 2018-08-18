package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/7/25.
 */

public class SponsorLabelResponse {
    /**
     * error : 0
     * message : success
     * data : [{"red_id":1462,"money":"5.00","create_time":1532500342,"businessid":1592,"businessname":"加贺见健介","logo":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251431136463.jpg"},{"red_id":1461,"money":"2.00","create_time":1532412748,"businessid":1590,"businessname":"阿鲁巴黎","logo":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png"},{"red_id":1460,"money":"5.00","create_time":1532347409,"businessid":1574,"businessname":"不鸟你","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/358312018072116592895265.jpg"},{"red_id":1459,"money":"2.00","create_time":1532340146,"businessid":1556,"businessname":"哈哈","logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/358212018071916463866307.jpg"},{"red_id":1458,"money":"2.00","create_time":1532339516,"businessid":1578,"businessname":"你对你的你","logo":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png"}]
     */

    private int error;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * red_id : 1462
         * money : 5.00
         * create_time : 1532500342
         * businessid : 1592
         * businessname : 加贺见健介
         * logo : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251431136463.jpg
         */

        private int red_id;
        private String money;
        private int create_time;
        private int businessid;
        private String businessname;
        private String logo;

        public int getRed_id() {
            return red_id;
        }

        public void setRed_id(int red_id) {
            this.red_id = red_id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getBusinessid() {
            return businessid;
        }

        public void setBusinessid(int businessid) {
            this.businessid = businessid;
        }

        public String getBusinessname() {
            return businessname;
        }

        public void setBusinessname(String businessname) {
            this.businessname = businessname;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
