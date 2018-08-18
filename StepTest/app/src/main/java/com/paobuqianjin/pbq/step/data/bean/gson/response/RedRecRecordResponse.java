package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/6/28.
 */
/*
@className :RedRecRecordResponse
*@date 2018/6/28
*@author
*@description 红包领取记录
*/
public class RedRecRecordResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":5},"data":[{"id":22174,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23405,"actually":"1.96","ratio":"0.80","is_receive":1,"is_time":1530175838,"create_day":"2018-06-28","longitude":"113.937325","latitude":"22.554335","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 16:50:38","create_time":1530152650,"devices_num":0,"usname":"Tina","busname":"阿尼玛","redname":"珠江国际贸易城"},{"id":22173,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23407,"actually":"0.86","ratio":"1.00","is_receive":1,"is_time":1530169333,"create_day":"2018-06-28","longitude":"113.937347","latitude":"22.554338","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 15:02:13","create_time":1530152650,"devices_num":0,"usname":"Titanium","busname":"阿尼玛","redname":"珠江国际贸易城"},{"id":22172,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23404,"actually":"0.57","ratio":"1.00","is_receive":1,"is_time":1530168863,"create_day":"2018-06-28","longitude":"113.937229","latitude":"22.554383","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 14:54:23","create_time":1530152650,"devices_num":0,"usname":"rm_13392444684","busname":"阿尼玛","redname":"珠江国际贸易城"},{"id":22171,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23412,"actually":"0.34","ratio":"1.00","is_receive":1,"is_time":1530157317,"create_day":"2018-06-28","longitude":"113.937286","latitude":"22.554381","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 11:41:57","create_time":1530152650,"devices_num":0,"usname":"rm_17688739002","busname":"阿尼玛","redname":"珠江国际贸易城"},{"id":22170,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23413,"actually":"0.78","ratio":"1.00","is_receive":1,"is_time":1530156032,"create_day":"2018-06-28","longitude":"113.937254","latitude":"22.554465","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 11:20:32","create_time":1530152650,"devices_num":0,"usname":"黄钦平","busname":"阿尼玛","redname":"珠江国际贸易城"}]}
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
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":5}
         * data : [{"id":22174,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23405,"actually":"1.96","ratio":"0.80","is_receive":1,"is_time":1530175838,"create_day":"2018-06-28","longitude":"113.937325","latitude":"22.554335","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 16:50:38","create_time":1530152650,"devices_num":0,"usname":"Tina","busname":"阿尼玛","redname":"珠江国际贸易城"},{"id":22173,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23407,"actually":"0.86","ratio":"1.00","is_receive":1,"is_time":1530169333,"create_day":"2018-06-28","longitude":"113.937347","latitude":"22.554338","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 15:02:13","create_time":1530152650,"devices_num":0,"usname":"Titanium","busname":"阿尼玛","redname":"珠江国际贸易城"},{"id":22172,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23404,"actually":"0.57","ratio":"1.00","is_receive":1,"is_time":1530168863,"create_day":"2018-06-28","longitude":"113.937229","latitude":"22.554383","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 14:54:23","create_time":1530152650,"devices_num":0,"usname":"rm_13392444684","busname":"阿尼玛","redname":"珠江国际贸易城"},{"id":22171,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23412,"actually":"0.34","ratio":"1.00","is_receive":1,"is_time":1530157317,"create_day":"2018-06-28","longitude":"113.937286","latitude":"22.554381","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 11:41:57","create_time":1530152650,"devices_num":0,"usname":"rm_17688739002","busname":"阿尼玛","redname":"珠江国际贸易城"},{"id":22170,"business_id":1409,"issuerid":23413,"red_id":1180,"userid":23413,"actually":"0.78","ratio":"1.00","is_receive":1,"is_time":1530156032,"create_day":"2018-06-28","longitude":"113.937254","latitude":"22.554465","ok":1,"ok_time":1530152656,"update_time":"2018-06-28 11:20:32","create_time":1530152650,"devices_num":0,"usname":"黄钦平","busname":"阿尼玛","redname":"珠江国际贸易城"}]
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
             * totalCount : 5
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
             * id : 22174
             * business_id : 1409
             * issuerid : 23413
             * red_id : 1180
             * userid : 23405
             * actually : 1.96
             * ratio : 0.80
             * is_receive : 1
             * is_time : 1530175838
             * create_day : 2018-06-28
             * longitude : 113.937325
             * latitude : 22.554335
             * ok : 1
             * ok_time : 1530152656
             * update_time : 2018-06-28 16:50:38
             * create_time : 1530152650
             * devices_num : 0
             * usname : Tina
             * busname : 阿尼玛
             * redname : 珠江国际贸易城
             * money : 4.41
             */

            private int id;
            private int business_id;
            private int issuerid;
            private int red_id;
            private int userid;
            private String actually;
            private String ratio;
            private int is_receive;
            private int is_time;
            private String create_day;
            private String longitude;
            private String latitude;
            private int ok;
            private int ok_time;
            private String update_time;
            private int create_time;
            private int devices_num;
            private String usname;
            private String busname;
            private String redname;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            private String money;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBusiness_id() {
                return business_id;
            }

            public void setBusiness_id(int business_id) {
                this.business_id = business_id;
            }

            public int getIssuerid() {
                return issuerid;
            }

            public void setIssuerid(int issuerid) {
                this.issuerid = issuerid;
            }

            public int getRed_id() {
                return red_id;
            }

            public void setRed_id(int red_id) {
                this.red_id = red_id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getActually() {
                return actually;
            }

            public void setActually(String actually) {
                this.actually = actually;
            }

            public String getRatio() {
                return ratio;
            }

            public void setRatio(String ratio) {
                this.ratio = ratio;
            }

            public int getIs_receive() {
                return is_receive;
            }

            public void setIs_receive(int is_receive) {
                this.is_receive = is_receive;
            }

            public int getIs_time() {
                return is_time;
            }

            public void setIs_time(int is_time) {
                this.is_time = is_time;
            }

            public String getCreate_day() {
                return create_day;
            }

            public void setCreate_day(String create_day) {
                this.create_day = create_day;
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

            public int getOk() {
                return ok;
            }

            public void setOk(int ok) {
                this.ok = ok;
            }

            public int getOk_time() {
                return ok_time;
            }

            public void setOk_time(int ok_time) {
                this.ok_time = ok_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getDevices_num() {
                return devices_num;
            }

            public void setDevices_num(int devices_num) {
                this.devices_num = devices_num;
            }

            public String getUsname() {
                return usname;
            }

            public void setUsname(String usname) {
                this.usname = usname;
            }

            public String getBusname() {
                return busname;
            }

            public void setBusname(String busname) {
                this.busname = busname;
            }

            public String getRedname() {
                return redname;
            }

            public void setRedname(String redname) {
                this.redname = redname;
            }
        }
    }
}
