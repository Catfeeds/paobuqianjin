package com.paobuqianjin.pbq.step.data.bean.gson.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public class GetUserBusinessResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":3},"data":[{"businessid":115,"userid":232,"logo":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png","name":"ee","tel":"","addra":"","address":"","longitude":"0.000000","latitude":"0.000000","total_money":"0.00","create_time":1525057280,"do_day":"","s_do_time":"00:00","e_do_time":"00:00","default":0,"logo_imgs":[],"goods_imgs":[],"environment_imgs":[]},{"businessid":124,"userid":232,"logo":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png","name":"看看","tel":"1111","addra":"广东省深圳市南山区","address":"空","longitude":"0.000000","latitude":"0.000000","total_money":"0.00","create_time":1525231299,"do_day":"周一","s_do_time":"00:00","e_do_time":"00:30","default":0,"logo_imgs":[],"goods_imgs":[],"environment_imgs":[]},{"businessid":125,"userid":232,"logo":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png","name":"看看","tel":"1111","addra":"广东省深圳市南山区","address":"空","longitude":"0.000000","latitude":"0.000000","total_money":"0.00","create_time":1525231328,"do_day":"周一","s_do_time":"00:00","e_do_time":"00:30","default":0,"logo_imgs":[],"goods_imgs":[],"environment_imgs":[]}]}
     */

    private int error;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":3}
         * data : [{"businessid":115,"userid":232,"logo":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png","name":"ee","tel":"","addra":"","address":"","longitude":"0.000000","latitude":"0.000000","total_money":"0.00","create_time":1525057280,"do_day":"","s_do_time":"00:00","e_do_time":"00:00","default":0,"logo_imgs":[],"goods_imgs":[],"environment_imgs":[]},{"businessid":124,"userid":232,"logo":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png","name":"看看","tel":"1111","addra":"广东省深圳市南山区","address":"空","longitude":"0.000000","latitude":"0.000000","total_money":"0.00","create_time":1525231299,"do_day":"周一","s_do_time":"00:00","e_do_time":"00:30","default":0,"logo_imgs":[],"goods_imgs":[],"environment_imgs":[]},{"businessid":125,"userid":232,"logo":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png","name":"看看","tel":"1111","addra":"广东省深圳市南山区","address":"空","longitude":"0.000000","latitude":"0.000000","total_money":"0.00","create_time":1525231328,"do_day":"周一","s_do_time":"00:00","e_do_time":"00:30","default":0,"logo_imgs":[],"goods_imgs":[],"environment_imgs":[]}]
         */

        private PagenationBean pagenation;
        private List<DataBean> data;

        public PagenationBean getPagenation() {
            return pagenation;
        }

        public void setPagenation(PagenationBean pagenation) {
            this.pagenation = pagenation;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 10
             * totalPage : 1
             * totalCount : 3
             */

            private int page;
            private int pageSize;
            private int totalPage;
            private int totalCount;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }
        }

        public static class DataBean {
            /**
             * businessid : 115
             * userid : 232
             * logo : https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_c.png
             * name : ee
             * tel :
             * addra :
             * address :
             * longitude : 0.000000
             * latitude : 0.000000
             * total_money : 0.00
             * create_time : 1525057280
             * do_day :
             * s_do_time : 00:00
             * e_do_time : 00:00
             * default : 0
             * logo_imgs : []
             * goods_imgs : []
             * environment_imgs : []
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
            @SerializedName("default")
            private int defaultX;
            private List<?> logo_imgs;
            private List<?> goods_imgs;
            private List<?> environment_imgs;

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

            public int getDefaultX() {
                return defaultX;
            }

            public void setDefaultX(int defaultX) {
                this.defaultX = defaultX;
            }

            public List<?> getLogo_imgs() {
                return logo_imgs;
            }

            public void setLogo_imgs(List<?> logo_imgs) {
                this.logo_imgs = logo_imgs;
            }

            public List<?> getGoods_imgs() {
                return goods_imgs;
            }

            public void setGoods_imgs(List<?> goods_imgs) {
                this.goods_imgs = goods_imgs;
            }

            public List<?> getEnvironment_imgs() {
                return environment_imgs;
            }

            public void setEnvironment_imgs(List<?> environment_imgs) {
                this.environment_imgs = environment_imgs;
            }
        }
    }
}
