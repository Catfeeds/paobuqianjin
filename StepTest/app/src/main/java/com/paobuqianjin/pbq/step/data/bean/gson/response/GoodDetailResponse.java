package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/29.
 */
/*
@className :GoodDetailResponse v1/CoalitionLine/goodsDetails tianmao and taobao jiekou
*@date 2018/11/29
*@author
*@description
*/
public class GoodDetailResponse {

    /**
     * error : 0
     * message : success
     * data : {"n_tbk_item":{"cat_leaf_name":"连衣裙","cat_name":"女装/女士精品","item_url":"https://h5.m.taobao.com/awp/core/detail.htm?id=583196938569","material_lib_type":[],"nick":"兴成时尚女装店","num_iid":"583196938569","pict_url":"https://img.alicdn.com/bao/uploaded/i4/2054833620/O1CN01kktADN1cc0EtA680V_!!0-item_pic.jpg","provcity":"广东 广州","reserve_price":"49","seller_id":"2054833620","small_images":{"string":["https://img.alicdn.com/i3/2054833620/O1CN01Y1h4e71cc0EuFgXjU_!!2054833620.jpg","https://img.alicdn.com/i4/2054833620/O1CN01m0G1PZ1cc0ErIuoqz_!!2054833620.jpg","https://img.alicdn.com/i4/2054833620/O1CN01dWZFpt1cc0ErItc1r_!!2054833620.jpg"]},"title":"2018春秋冬季新款韩版女装显瘦两件套套装裙子七分袖上衣连衣裙","user_type":"0","volume":"0","zk_final_price":"49"}}
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
         * n_tbk_item : {"cat_leaf_name":"连衣裙","cat_name":"女装/女士精品","item_url":"https://h5.m.taobao.com/awp/core/detail.htm?id=583196938569","material_lib_type":[],"nick":"兴成时尚女装店","num_iid":"583196938569","pict_url":"https://img.alicdn.com/bao/uploaded/i4/2054833620/O1CN01kktADN1cc0EtA680V_!!0-item_pic.jpg","provcity":"广东 广州","reserve_price":"49","seller_id":"2054833620","small_images":{"string":["https://img.alicdn.com/i3/2054833620/O1CN01Y1h4e71cc0EuFgXjU_!!2054833620.jpg","https://img.alicdn.com/i4/2054833620/O1CN01m0G1PZ1cc0ErIuoqz_!!2054833620.jpg","https://img.alicdn.com/i4/2054833620/O1CN01dWZFpt1cc0ErItc1r_!!2054833620.jpg"]},"title":"2018春秋冬季新款韩版女装显瘦两件套套装裙子七分袖上衣连衣裙","user_type":"0","volume":"0","zk_final_price":"49"}
         */

        private NTbkItemBean n_tbk_item;

        public NTbkItemBean getN_tbk_item() {
            return n_tbk_item;
        }

        public void setN_tbk_item(NTbkItemBean n_tbk_item) {
            this.n_tbk_item = n_tbk_item;
        }

        public static class NTbkItemBean {
            /**
             * cat_leaf_name : 连衣裙
             * cat_name : 女装/女士精品
             * item_url : https://h5.m.taobao.com/awp/core/detail.htm?id=583196938569
             * material_lib_type : []
             * nick : 兴成时尚女装店
             * num_iid : 583196938569
             * pict_url : https://img.alicdn.com/bao/uploaded/i4/2054833620/O1CN01kktADN1cc0EtA680V_!!0-item_pic.jpg
             * provcity : 广东 广州
             * reserve_price : 49
             * seller_id : 2054833620
             * small_images : {"string":["https://img.alicdn.com/i3/2054833620/O1CN01Y1h4e71cc0EuFgXjU_!!2054833620.jpg","https://img.alicdn.com/i4/2054833620/O1CN01m0G1PZ1cc0ErIuoqz_!!2054833620.jpg","https://img.alicdn.com/i4/2054833620/O1CN01dWZFpt1cc0ErItc1r_!!2054833620.jpg"]}
             * title : 2018春秋冬季新款韩版女装显瘦两件套套装裙子七分袖上衣连衣裙
             * user_type : 0
             * volume : 0
             * zk_final_price : 49
             */
            private String item_url;
            private String num_iid;
            private String pict_url;
            /*       private SmallImagesBean small_images;*/
            private String title;
            private int user_type;
            private String volume;
            private String zk_final_price;


            public String getItem_url() {
                return item_url;
            }

            public void setItem_url(String item_url) {
                this.item_url = item_url;
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

            public static class SmallImagesBean {
                private List<String> string;

                public List<String> getString() {
                    return string;
                }

                public void setString(List<String> string) {
                    this.string = string;
                }
            }
        }
    }
}
