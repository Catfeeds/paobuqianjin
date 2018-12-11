package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/29.
 */

/*
@className :CouponListResponse v1/CoalitionLine/getCouponList  tianmao and taobao jiekou
*@date 2018/11/29
*@author
*@description
*/
public class CouponListResponse {

    /**
     * error : 0
     * message : success
     * data : {"tbk_coupon":[{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=ezaUzuvs%2Fr8GQASttHIRqXJHNH%2BZgDvuTEseZPDHO3SJBiHzvBsckbIk2ChoCl7mR9C5C6BXY5zo8wpXgQg1%2Fho3XtwjZ7hbbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlkZOzvETTH2TsMlK0uFyiNQ%3D%3D&traceId=0bba83d215438912504954080e","coupon_end_time":"2018-12-04","coupon_info":"满10元减3元","coupon_remain_count":"3","coupon_start_time":"2018-12-04","coupon_total_count":"60","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=568990338509","nick":"华玉批发公司","num_iid":"568990338509","pict_url":"http://img.alicdn.com/tfscom/i2/13130757/TB2tOWYX1UXBuNjt_a0XXcysXXa_!!13130757.jpg","seller_id":"13130757","shop_title":"第六神话华玉美衣女装成衣性价比之王外贸批发零售","small_images":{"string":["http://img.alicdn.com/tfscom/i1/13130757/TB2_4JShfuSBuNkHFqDXXXfhVXa_!!13130757.jpg","http://img.alicdn.com/tfscom/i3/13130757/TB2qof1eSYTBKNjSZKbXXXJ8pXa_!!13130757.jpg","http://img.alicdn.com/tfscom/i4/13130757/O1CN011HSkGrGmlwx4imI_!!13130757.jpg","http://img.alicdn.com/tfscom/i1/13130757/TB2IniOp29TBuNjy1zbXXXpepXa_!!13130757.jpg"]},"title":"8077 卫衣2018夏季新款韩版动物印花连帽抽绳女装宽松百搭卫衣裙","user_type":"0","volume":"29","zk_final_price":"15.00"},{"category":"16","commission_rate":"8.10","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=Inj11YUUoEAGQASttHIRqevLsehP19oxqBeyiyszKITl43M3mIB1t6qDxKvbUaGenuBiK41CBSxJSb%2FI4voBQre5us%2FKBnmAbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlkZOzvETTH2TsMlK0uFyiNQ%3D%3D&traceId=0bba83d215438912504954080e","coupon_end_time":"2018-12-04","coupon_info":"满6元减5元","coupon_remain_count":"18000","coupon_start_time":"2018-12-04","coupon_total_count":"20000","item_description":["asd","sda"],"item_url":"https://detail.tmall.com/item.htm?id=576130219725","nick":"曦月果儿","num_iid":"576130219725","pict_url":"http://img.alicdn.com/tfscom/i1/3140708456/TB2P3xXrRjTBKNjSZFDXXbVgVXa_!!3140708456.jpg","seller_id":"3140708456","shop_title":"思杰时尚美衣","small_images":{"string":["http://img.alicdn.com/tfscom/i4/3140708456/TB2EUx9rqAoBKNjSZSyXXaHAVXa_!!3140708456.jpg","http://img.alicdn.com/tfscom/i3/3140708456/TB2PeVDrH3nBKNjSZFMXXaUSFXa_!!3140708456.jpg","http://img.alicdn.com/tfscom/i2/3140708456/TB2LohIrAZmBKNjSZPiXXXFNVXa_!!3140708456.jpg"]},"title":"初秋新款女装chicV领显瘦条纹衬衫+侧开叉拉链A字半身裙学生套装","user_type":"0","volume":"0","zk_final_price":"17.84"}]}
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
        private List<TbkCouponBean> tbk_coupon;

        public List<TbkCouponBean> getTbk_coupon() {
            return tbk_coupon;
        }

        public void setTbk_coupon(List<TbkCouponBean> tbk_coupon) {
            this.tbk_coupon = tbk_coupon;
        }

        public static class TbkCouponBean {
            /**
             * category : 16
             * commission_rate : 4.50
             * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=ezaUzuvs%2Fr8GQASttHIRqXJHNH%2BZgDvuTEseZPDHO3SJBiHzvBsckbIk2ChoCl7mR9C5C6BXY5zo8wpXgQg1%2Fho3XtwjZ7hbbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlkZOzvETTH2TsMlK0uFyiNQ%3D%3D&traceId=0bba83d215438912504954080e
             * coupon_end_time : 2018-12-04
             * coupon_info : 满10元减3元
             * coupon_remain_count : 3
             * coupon_start_time : 2018-12-04
             * coupon_total_count : 60
             * item_description : []
             * item_url : https://detail.tmall.com/item.htm?id=568990338509
             * nick : 华玉批发公司
             * num_iid : 568990338509
             * pict_url : http://img.alicdn.com/tfscom/i2/13130757/TB2tOWYX1UXBuNjt_a0XXcysXXa_!!13130757.jpg
             * seller_id : 13130757
             * shop_title : 第六神话华玉美衣女装成衣性价比之王外贸批发零售
             * small_images : {"string":["http://img.alicdn.com/tfscom/i1/13130757/TB2_4JShfuSBuNkHFqDXXXfhVXa_!!13130757.jpg","http://img.alicdn.com/tfscom/i3/13130757/TB2qof1eSYTBKNjSZKbXXXJ8pXa_!!13130757.jpg","http://img.alicdn.com/tfscom/i4/13130757/O1CN011HSkGrGmlwx4imI_!!13130757.jpg","http://img.alicdn.com/tfscom/i1/13130757/TB2IniOp29TBuNjy1zbXXXpepXa_!!13130757.jpg"]}
             * title : 8077 卫衣2018夏季新款韩版动物印花连帽抽绳女装宽松百搭卫衣裙
             * user_type : 0
             * volume : 29
             * zk_final_price : 15.00
             */
            private String coupon_click_url;
            private String coupon_info;
            private String num_iid;
            private String pict_url;
            private String title;
            private int user_type;
            private String volume;
            private String zk_final_price;


            public String getCoupon_click_url() {
                return coupon_click_url;
            }

            public void setCoupon_click_url(String coupon_click_url) {
                this.coupon_click_url = coupon_click_url;
            }

            public String getCoupon_info() {
                return coupon_info;
            }

            public void setCoupon_info(String coupon_info) {
                this.coupon_info = coupon_info;
            }


            public String getNum_iid() {
                return num_iid;
            }

            public void setNum_iid(String num_iid) {
                this.num_iid = num_iid;
            }

            public String getPict_url() {
                return pict_url;
            }

            public void setPict_url(String pict_url) {
                this.pict_url = pict_url;
            }
            
            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getZk_final_price() {
                return zk_final_price;
            }

            public void setZk_final_price(String zk_final_price) {
                this.zk_final_price = zk_final_price;
            }

        }
    }
}
