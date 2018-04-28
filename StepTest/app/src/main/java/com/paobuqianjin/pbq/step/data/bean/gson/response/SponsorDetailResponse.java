package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/4/28.
 */
/*
@className :SponsorDetailResponse
*@date 2018/4/28
*@author 获取商家详情
*@description
*/
public class SponsorDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"businessid":15,"userid":66,"logo":"http://logo.png","name":"超级酒店","tel":"0756-888168","addra":"广东深圳南山","address":"马家龙","longitude":"113.111111","latitude":"22.555556","total_money":"0.00","create_time":1524470406,"do_day":"周一,周二,周六","s_do_time":"18:44","e_do_time":"18:44","logo_imgs":[],"goods_imgs":[{"img_id":34,"type":2,"url":"http://logo.png","status":0}],"environment_imgs":[{"img_id":36,"type":3,"url":"http://logo.png","status":0},{"img_id":37,"type":3,"url":"http://logo.png","status":0},{"img_id":38,"type":3,"url":"http://logo.png","status":0},{"img_id":77,"type":3,"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/F06B1B0D-F6F0-489F-8D27-7C862E90A0E6.jpg","status":0}]}
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
         * businessid : 15
         * userid : 66
         * logo : http://logo.png
         * name : 超级酒店
         * tel : 0756-888168
         * addra : 广东深圳南山
         * address : 马家龙
         * longitude : 113.111111
         * latitude : 22.555556
         * total_money : 0.00
         * create_time : 1524470406
         * do_day : 周一,周二,周六
         * s_do_time : 18:44
         * e_do_time : 18:44
         * logo_imgs : []
         * goods_imgs : [{"img_id":34,"type":2,"url":"http://logo.png","status":0}]
         * environment_imgs : [{"img_id":36,"type":3,"url":"http://logo.png","status":0},{"img_id":37,"type":3,"url":"http://logo.png","status":0},{"img_id":38,"type":3,"url":"http://logo.png","status":0},{"img_id":77,"type":3,"url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/F06B1B0D-F6F0-489F-8D27-7C862E90A0E6.jpg","status":0}]
         */

        private int businessid;
        private int userid;
        private String logo;
        private String name;
        private String tel;
        private String addra;
        private String address;
        private String longitude;
        private String latitude;
        private String total_money;
        private int create_time;
        private String do_day;
        private String s_do_time;
        private String e_do_time;
        private List<Logo_ImgsBean> logo_imgs;
        private List<GoodsImgsBean> goods_imgs;
        private List<EnvironmentImgsBean> environment_imgs;

        public int getBusinessid() {
            return businessid;
        }

        public void setBusinessid(int businessid) {
            this.businessid = businessid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddra() {
            return addra;
        }

        public void setAddra(String addra) {
            this.addra = addra;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getDo_day() {
            return do_day;
        }

        public void setDo_day(String do_day) {
            this.do_day = do_day;
        }

        public String getS_do_time() {
            return s_do_time;
        }

        public void setS_do_time(String s_do_time) {
            this.s_do_time = s_do_time;
        }

        public String getE_do_time() {
            return e_do_time;
        }

        public void setE_do_time(String e_do_time) {
            this.e_do_time = e_do_time;
        }

        public List<Logo_ImgsBean> getLogo_imgs() {
            return logo_imgs;
        }

        public void setLogo_imgs(List<Logo_ImgsBean> logo_imgs) {
            this.logo_imgs = logo_imgs;
        }

        public List<GoodsImgsBean> getGoods_imgs() {
            return goods_imgs;
        }

        public void setGoods_imgs(List<GoodsImgsBean> goods_imgs) {
            this.goods_imgs = goods_imgs;
        }

        public List<EnvironmentImgsBean> getEnvironment_imgs() {
            return environment_imgs;
        }

        public void setEnvironment_imgs(List<EnvironmentImgsBean> environment_imgs) {
            this.environment_imgs = environment_imgs;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "businessid=" + businessid +
                    ", userid=" + userid +
                    ", logo='" + logo + '\'' +
                    ", name='" + name + '\'' +
                    ", tel='" + tel + '\'' +
                    ", addra='" + addra + '\'' +
                    ", address='" + address + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", total_money='" + total_money + '\'' +
                    ", create_time=" + create_time +
                    ", do_day='" + do_day + '\'' +
                    ", s_do_time='" + s_do_time + '\'' +
                    ", e_do_time='" + e_do_time + '\'' +
                    ", logo_imgs=" + logo_imgs +
                    ", goods_imgs=" + goods_imgs +
                    ", environment_imgs=" + environment_imgs +
                    '}';
        }

        public static class Logo_ImgsBean {
            /**
             * img_id : 40
             * type : 1
             * url : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/DA054141-61B7-4683-8F5E-31959EE87C2B.jpg
             * status : 0
             */

            private int img_id;
            private int type;
            private String url;
            private int status;

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            @Override
            public String toString() {
                return "Logo_ImgsBean{" +
                        "img_id=" + img_id +
                        ", type=" + type +
                        ", url='" + url + '\'' +
                        ", status=" + status +
                        '}';
            }
        }

        public static class GoodsImgsBean implements Serializable {
            /**
             * img_id : 34
             * type : 2
             * url : http://logo.png
             * status : 0
             */

            private int img_id;
            private int type;
            private String url;
            private int status;

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            @Override
            public String toString() {
                return "GoodsImgsBean{" +
                        "img_id=" + img_id +
                        ", type=" + type +
                        ", url='" + url + '\'' +
                        ", status=" + status +
                        '}';
            }
        }

        public static class EnvironmentImgsBean {
            /**
             * img_id : 36
             * type : 3
             * url : http://logo.png
             * status : 0
             */

            private int img_id;
            private int type;
            private String url;
            private int status;

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            @Override
            public String toString() {
                return "EnvironmentImgsBean{" +
                        "img_id=" + img_id +
                        ", type=" + type +
                        ", url='" + url + '\'' +
                        ", status=" + status +
                        '}';
            }
        }
    }
}
