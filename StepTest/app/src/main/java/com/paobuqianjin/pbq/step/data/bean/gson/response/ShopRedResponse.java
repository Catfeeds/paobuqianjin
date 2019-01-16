package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2019/1/16.
 */

public class ShopRedResponse {
    /**
     * error : 0
     * message : success
     * data : {"alert_type":1,"business":[{"id":1703,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0E485A9B-2151-4BF9-8E93-A7751D5E30D4.jpg","name":"哈哈大笑","latitude":"22.548920","longitude":"113.930547","distance":15},{"id":1705,"logo":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/15/35822201901151929287520.jpg","name":"入驻测试","latitude":"22.540430","longitude":"113.934540","distance":1038}],"redpacket":[{"red_id":802,"content":"号外,号外,我刚入驻跑步钱进红包地图坐标了,欢迎各位亲光临我的小店!","distance":3000,"latitude":"22.548920","longitude":"113.930547","distance_diff":15,"can_receive":1,"tips":""},{"red_id":803,"content":"号外,号外,我刚入驻跑步钱进红包地图坐标了,欢迎各位亲光临我的小店!","distance":3000,"latitude":"22.540430","longitude":"113.934540","distance_diff":1038,"can_receive":1,"tips":""}],"tips":"小主,您当前位置有2家商铺还有2个红包","is_receive":1,"message":""}
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
         * alert_type : 1
         * business : [{"id":1703,"logo":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0E485A9B-2151-4BF9-8E93-A7751D5E30D4.jpg","name":"哈哈大笑","latitude":"22.548920","longitude":"113.930547","distance":15},{"id":1705,"logo":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/15/35822201901151929287520.jpg","name":"入驻测试","latitude":"22.540430","longitude":"113.934540","distance":1038}]
         * redpacket : [{"red_id":802,"content":"号外,号外,我刚入驻跑步钱进红包地图坐标了,欢迎各位亲光临我的小店!","distance":3000,"latitude":"22.548920","longitude":"113.930547","distance_diff":15,"can_receive":1,"tips":""},{"red_id":803,"content":"号外,号外,我刚入驻跑步钱进红包地图坐标了,欢迎各位亲光临我的小店!","distance":3000,"latitude":"22.540430","longitude":"113.934540","distance_diff":1038,"can_receive":1,"tips":""}]
         * tips : 小主,您当前位置有2家商铺还有2个红包
         * is_receive : 1
         * message :
         */

        private int alert_type;
        private String tips;
        private int is_receive;
        private String message;
        private List<BusinessBean> business;
        private List<RedpacketBean> redpacket;

        public int getAlert_type() {
            return alert_type;
        }

        public void setAlert_type(int alert_type) {
            this.alert_type = alert_type;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public int getIs_receive() {
            return is_receive;
        }

        public void setIs_receive(int is_receive) {
            this.is_receive = is_receive;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<BusinessBean> getBusiness() {
            return business;
        }

        public void setBusiness(List<BusinessBean> business) {
            this.business = business;
        }

        public List<RedpacketBean> getRedpacket() {
            return redpacket;
        }

        public void setRedpacket(List<RedpacketBean> redpacket) {
            this.redpacket = redpacket;
        }

        public static class BusinessBean {
            /**
             * id : 1703
             * logo : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0E485A9B-2151-4BF9-8E93-A7751D5E30D4.jpg
             * name : 哈哈大笑
             * latitude : 22.548920
             * longitude : 113.930547
             * distance : 15
             */

            private String id;
            private String logo;
            private String name;
            private String latitude;
            private String longitude;
            private String distance;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }

        public static class RedpacketBean {
            /**
             * red_id : 802
             * content : 号外,号外,我刚入驻跑步钱进红包地图坐标了,欢迎各位亲光临我的小店!
             * distance : 3000
             * latitude : 22.548920
             * longitude : 113.930547
             * distance_diff : 15
             * can_receive : 1
             * tips :
             */

            private String red_id;
            private String content;
            private String distance;
            private String latitude;
            private String longitude;
            private String distance_diff;
            private String can_receive;
            private String tips;

            public String getRed_id() {
                return red_id;
            }

            public void setRed_id(String red_id) {
                this.red_id = red_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getDistance_diff() {
                return distance_diff;
            }

            public void setDistance_diff(String distance_diff) {
                this.distance_diff = distance_diff;
            }

            public String getCan_receive() {
                return can_receive;
            }

            public void setCan_receive(String can_receive) {
                this.can_receive = can_receive;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }
        }
    }
}
