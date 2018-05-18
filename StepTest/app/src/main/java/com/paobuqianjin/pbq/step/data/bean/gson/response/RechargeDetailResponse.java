package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/22.
 */
/*
@className :RechargeDetailResponse
*@date 2018/3/22
*@author
*@description 充值明细
*/
public class RechargeDetailResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":13,"totalCount":129},"data":[{"id":1527,"otype":2,"ptype":1,"order_no":"201805161258047305","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"123.00","create_time":1526446684},{"id":1526,"otype":2,"ptype":1,"order_no":"201805161254478463","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"4.00","create_time":1526446487},{"id":1405,"otype":4,"ptype":1,"order_no":"201805150941557592","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"50.00","create_time":1526348515},{"id":1354,"otype":2,"ptype":1,"order_no":"201805140355423187","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"100.00","create_time":1526284542},{"id":1325,"otype":2,"ptype":1,"order_no":"201805120804134734","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"1.00","create_time":1526126653},{"id":1324,"otype":2,"ptype":1,"order_no":"201805120748041040","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"100.00","create_time":1526125684},{"id":1290,"otype":1,"ptype":2,"order_no":"201805110858108430","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"0.01","create_time":1526043490},{"id":1289,"otype":0,"ptype":1,"order_no":"201805110837279821","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"200.00","create_time":1526042247},{"id":1288,"otype":0,"ptype":2,"order_no":"201805110837205241","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"200.00","create_time":1526042240},{"id":1287,"otype":0,"ptype":1,"order_no":"201805110837085599","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"200.00","create_time":1526042228}]}
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

    @Override
    public String toString() {
        return "RechargeDetailResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":13,"totalCount":129}
         * data : [{"id":1527,"otype":2,"ptype":1,"order_no":"201805161258047305","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"123.00","create_time":1526446684},{"id":1526,"otype":2,"ptype":1,"order_no":"201805161254478463","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"4.00","create_time":1526446487},{"id":1405,"otype":4,"ptype":1,"order_no":"201805150941557592","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"50.00","create_time":1526348515},{"id":1354,"otype":2,"ptype":1,"order_no":"201805140355423187","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"100.00","create_time":1526284542},{"id":1325,"otype":2,"ptype":1,"order_no":"201805120804134734","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"1.00","create_time":1526126653},{"id":1324,"otype":2,"ptype":1,"order_no":"201805120748041040","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"100.00","create_time":1526125684},{"id":1290,"otype":1,"ptype":2,"order_no":"201805110858108430","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"0.01","create_time":1526043490},{"id":1289,"otype":0,"ptype":1,"order_no":"201805110837279821","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"200.00","create_time":1526042247},{"id":1288,"otype":0,"ptype":2,"order_no":"201805110837205241","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"200.00","create_time":1526042240},{"id":1287,"otype":0,"ptype":1,"order_no":"201805110837085599","userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","total_fee":"200.00","create_time":1526042228}]
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

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "pagenation=" + pagenation +
                    ", data=" + data +
                    '}';
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 10
             * totalPage : 13
             * totalCount : 129
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

            @Override
            public String toString() {
                return "PagenationBean{" +
                        "page=" + page +
                        ", pageSize=" + pageSize +
                        ", totalPage=" + totalPage +
                        ", totalCount=" + totalCount +
                        '}';
            }
        }

        public static class DataBean {
            /**
             * id : 1527
             * otype : 2
             * ptype : 1
             * order_no : 201805161258047305
             * userid : 30
             * nickname : 黄钦平
             * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
             * total_fee : 123.00
             * create_time : 1526446684
             * pay_name:微信充值-用户钱包
             */

            private int id;
            private int otype;
            private int ptype;
            private String order_no;
            private int userid;
            private String nickname;
            private String avatar;
            private String total_fee;
            private int create_time;

            public String getPay_name() {
                return pay_name;
            }

            public void setPay_name(String pay_name) {
                this.pay_name = pay_name;
            }

            private String pay_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOtype() {
                return otype;
            }

            public void setOtype(int otype) {
                this.otype = otype;
            }

            public int getPtype() {
                return ptype;
            }

            public void setPtype(int ptype) {
                this.ptype = ptype;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(String total_fee) {
                this.total_fee = total_fee;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", otype=" + otype +
                        ", ptype=" + ptype +
                        ", order_no='" + order_no + '\'' +
                        ", userid=" + userid +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", total_fee='" + total_fee + '\'' +
                        ", create_time=" + create_time +
                        ", pay_name='" + pay_name + '\'' +
                        '}';
            }
        }
    }
}
