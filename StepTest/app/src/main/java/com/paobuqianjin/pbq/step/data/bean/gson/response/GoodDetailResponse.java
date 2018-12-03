package com.paobuqianjin.pbq.step.data.bean.gson.response;

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
     * data : {"n_tbk_item":{"cat_leaf_name":"冰箱磁性贴","cat_name":"家居饰品","item_url":"https://h5.m.taobao.com/awp/core/detail.htm?id=578834910065","material_lib_type":"1","nick":"带上潮包去旅行","num_iid":"578834910065","pict_url":"https://img.alicdn.com/bao/uploaded/i1/835933167/O1CN011ZGWnu70uluvMaK_!!835933167.jpg","provcity":"江苏 无锡","reserve_price":"33.8","seller_id":"835933167","small_images":{"string":"https://img.alicdn.com/i4/835933167/O1CN011ZGWntX8kXAgjNX_!!835933167.jpg"},"title":"包邮mm巧克力豆小磁铁强磁冰箱贴 圆形强力创意磁贴办公教具","user_type":"0","volume":"0","zk_final_price":"18.15"}}
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
         * n_tbk_item : {"cat_leaf_name":"冰箱磁性贴","cat_name":"家居饰品","item_url":"https://h5.m.taobao.com/awp/core/detail.htm?id=578834910065","material_lib_type":"1","nick":"带上潮包去旅行","num_iid":"578834910065","pict_url":"https://img.alicdn.com/bao/uploaded/i1/835933167/O1CN011ZGWnu70uluvMaK_!!835933167.jpg","provcity":"江苏 无锡","reserve_price":"33.8","seller_id":"835933167","small_images":{"string":"https://img.alicdn.com/i4/835933167/O1CN011ZGWntX8kXAgjNX_!!835933167.jpg"},"title":"包邮mm巧克力豆小磁铁强磁冰箱贴 圆形强力创意磁贴办公教具","user_type":"0","volume":"0","zk_final_price":"18.15"}
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
             * cat_leaf_name : 冰箱磁性贴
             * cat_name : 家居饰品
             * item_url : https://h5.m.taobao.com/awp/core/detail.htm?id=578834910065
             * material_lib_type : 1
             * nick : 带上潮包去旅行
             * num_iid : 578834910065
             * pict_url : https://img.alicdn.com/bao/uploaded/i1/835933167/O1CN011ZGWnu70uluvMaK_!!835933167.jpg
             * provcity : 江苏 无锡
             * reserve_price : 33.8
             * seller_id : 835933167
             * small_images : {"string":"https://img.alicdn.com/i4/835933167/O1CN011ZGWntX8kXAgjNX_!!835933167.jpg"}
             * title : 包邮mm巧克力豆小磁铁强磁冰箱贴 圆形强力创意磁贴办公教具
             * user_type : 0
             * volume : 0
             * zk_final_price : 18.15
             */

            private String cat_leaf_name;
            private String cat_name;
            private String item_url;
            private String material_lib_type;
            private String nick;
            private String num_iid;
            private String pict_url;
            private String provcity;
            private String reserve_price;
            private String seller_id;
            private SmallImagesBean small_images;
            private String title;
            private String user_type;
            private String volume;
            private String zk_final_price;

            public String getCat_leaf_name() {
                return cat_leaf_name;
            }

            public void setCat_leaf_name(String cat_leaf_name) {
                this.cat_leaf_name = cat_leaf_name;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public String getItem_url() {
                return item_url;
            }

            public void setItem_url(String item_url) {
                this.item_url = item_url;
            }

            public String getMaterial_lib_type() {
                return material_lib_type;
            }

            public void setMaterial_lib_type(String material_lib_type) {
                this.material_lib_type = material_lib_type;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
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

            public String getProvcity() {
                return provcity;
            }

            public void setProvcity(String provcity) {
                this.provcity = provcity;
            }

            public String getReserve_price() {
                return reserve_price;
            }

            public void setReserve_price(String reserve_price) {
                this.reserve_price = reserve_price;
            }

            public String getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(String seller_id) {
                this.seller_id = seller_id;
            }

            public SmallImagesBean getSmall_images() {
                return small_images;
            }

            public void setSmall_images(SmallImagesBean small_images) {
                this.small_images = small_images;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
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
                /**
                 * string : https://img.alicdn.com/i4/835933167/O1CN011ZGWntX8kXAgjNX_!!835933167.jpg
                 */

                private String string;

                public String getString() {
                    return string;
                }

                public void setString(String string) {
                    this.string = string;
                }
            }
        }
    }
}
