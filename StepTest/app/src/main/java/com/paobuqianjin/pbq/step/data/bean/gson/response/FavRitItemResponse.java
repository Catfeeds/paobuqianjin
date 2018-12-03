package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/29.
 */
/*
@className :FavRitItemResponse  v1/CoalitionLine/favoritesItem tianmao and taobao jiekou
*@date 2018/11/29
*@author
*@description
*/
public class FavRitItemResponse {

    /**
     * error : 0
     * message : success
     * data : {"uatm_tbk_item":[{"category":"21","click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3DSgoDoYFuvxxw4vFB6t2Z2ueEDrYVVa64yK8Cckff7TXjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vWplyh2W4e4BNfkwpW4FUzq9ZsOowOohY0yv9ycLD26agjNSMmmDDuisRSPt2qJlWOiZ%2BQMlGz6FQ%3D%3D&unid=3456","event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=35945581116","nick":"芳夕旗舰店","num_iid":"35945581116","pict_url":"https://img.alicdn.com/tfscom/i2/T1BSUTFotaXXXXXXXX_!!0-item_pic.jpg","provcity":"上海","reserve_price":"9.90","seller_id":"1012740000","shop_title":"芳夕旗舰店","small_images":{"string":["https://img.alicdn.com/tfscom/i1/1012740000/T2g8WyXxJXXXXXXXXX_!!1012740000.jpg","https://img.alicdn.com/tfscom/i2/1012740000/T2fZ5wXu8aXXXXXXXX_!!1012740000.jpg","https://img.alicdn.com/tfscom/i3/1012740000/T2UeKBXqBXXXXXXXXX_!!1012740000.jpg","https://img.alicdn.com/tfscom/i1/1012740000/T2urmzXv4XXXXXXXXX_!!1012740000.jpg"]},"status":"1","title":"包邮芳夕 雕花香熏炉 陶瓷香薰炉精油炉 蜡烛香薰灯 送精油+蜡烛","tk_rate":"3.00","type":"1","user_type":"1","volume":"15","zk_final_price":"9.90","zk_final_price_wap":"9.90"},{"category":"21","click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3D5asZQaQhUu5w4vFB6t2Z2ueEDrYVVa64yK8Cckff7TXjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vXmYjAG2NNsebPReQPLzmk%2BCKa3654jNMNZbxY%2Bh%2Bun7AjNSMmmDDuisRSPt2qJlWOiZ%2BQMlGz6FQ%3D%3D&unid=3456","event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=44628530969","nick":"和扇堂旗舰店","num_iid":"44628530969","pict_url":"https://img.alicdn.com/tfscom/i2/2476363521/TB1PpS.h2iSBuNkSnhJXXbDcpXa_!!0-item_pic.jpg","provcity":"浙江 杭州","reserve_price":"9.80","seller_id":"2476363521","shop_title":"和扇堂旗舰店","small_images":{"string":["https://img.alicdn.com/tfscom/i2/2476363521/TB2IQzdhZyYBuNkSnfoXXcWgVXa_!!2476363521.jpg","https://img.alicdn.com/tfscom/i2/2476363521/TB2bdalpuSSBuNjy0FlXXbBpVXa_!!2476363521.jpg","https://img.alicdn.com/tfscom/i2/2476363521/TB2mDLsj3aTBuNjSszfXXXgfpXa_!!2476363521.jpg","https://img.alicdn.com/tfscom/i3/2476363521/O1CN019F6pxJ1bsf1QnFFuK_!!2476363521.jpg"]},"status":"1","title":"日式折扇夏季中国风女式扇子绢扇樱花和风折叠古风舞蹈小扇","tk_rate":"0.45","type":"1","user_type":"1","volume":"472","zk_final_price":"9.80","zk_final_price_wap":"9.80"},{"category":"21","click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3D8EgMrescvmVw4vFB6t2Z2ueEDrYVVa64XoO8tOebS%2Bfjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vXmYjAG2NNsedoIUbBQXJwBVTTqcg%2F5JwRBbKMgfxKZtAnEqHUanck3zxP%2BzJm0cvI%3D&unid=3456","event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=535642362010","nick":"t8454509","num_iid":"535642362010","pict_url":"https://img.alicdn.com/tfscom/i2/22023577/TB1rrJepviSBuNkSnhJXXbDcpXa_!!0-item_pic.jpg","provcity":"广东 东莞","reserve_price":"13.90","seller_id":"22023577","shop_title":"忆美家居 全国包邮","small_images":{"string":["https://img.alicdn.com/tfscom/i4/22023577/TB2JQrThgvD8KJjy0FlXXagBFXa_!!22023577.jpg","https://img.alicdn.com/tfscom/i3/22023577/TB2vW0RsVXXXXcWXXXXXXXXXXXX_!!22023577.jpg","https://img.alicdn.com/tfscom/i2/22023577/TB2SuHVhcnI8KJjSsziXXb8QpXa_!!22023577.jpg","https://img.alicdn.com/tfscom/i1/22023577/TB2qySbsVXXXXadXXXXXXXXXXXX_!!22023577.jpg"]},"status":"1","title":"家用灭鼠神器捕鼠笼子自动诱捕连续抓老鼠的工具铁质超强捉老鼠夹","tk_rate":"6.00","type":"1","user_type":"0","volume":"215","zk_final_price":"9.90","zk_final_price_wap":"9.90"},{"category":"50020485","click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3DaUahCVt5Nq5w4vFB6t2Z2ueEDrYVVa64XoO8tOebS%2Bfjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vXmYjAG2NNsedqEccZ%2BV5WUJ5oRQpN5nHDgew3qcWA5QKOBwmJJpqDPTrnVnG8rlFDGJe8N%2FwNpGw%3D%3D&unid=3456","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=KM%2Fqrv2PJwUGQASttHIRqfsb6Q5Q309IQ0WtdlU2mqJ85jRliMF9K22zlzuBVx8gkWZ0d6EhzMqqDM%2BlRwc%2Bi%2BU0ISSq5CzAAy6OIxqvpBM3VpSh0s9h6TLZyP%2BOsOFo","coupon_end_time":"2018-12-01","coupon_info":"满5元减2元","coupon_remain_count":"9773","coupon_start_time":"2018-10-07","coupon_total_count":"10000","event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=544135726208","nick":"自动化工业用品","num_iid":"544135726208","pict_url":"https://img.alicdn.com/tfscom/i2/2967297273/TB20KDCd_wKL1JjSZFgXXb6aVXa_!!2967297273.jpg","provcity":"山东 青岛","reserve_price":"5.00","seller_id":"2967297273","shop_title":"精密直线轴承 导轨 螺母","small_images":{"string":["https://img.alicdn.com/tfscom/i3/2967297273/O1CN0123b52nbIsyO7DpC_!!2967297273.png","https://img.alicdn.com/tfscom/i2/2967297273/O1CN0123b52hDrnUeFKBJ_!!2967297273.png","https://img.alicdn.com/tfscom/i3/2967297273/TB2KhqIaMb.PuJjSZFpXXbuFpXa_!!2967297273.jpg","https://img.alicdn.com/tfscom/i2/2967297273/TB29xd0aL6H8KJjSspmXXb2WXXa_!!2967297273.jpg"]},"status":"1","title":"直线滑动轴承LM3/4/5/6/8/10/12/13/16/20/25/30/35/40/50UU内外","tk_rate":"9.00","type":"1","user_type":"0","volume":"22","zk_final_price":"5.00","zk_final_price_wap":"5.00"},{"category":"122852001","click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3Dm9odS%2Bt1PeVw4vFB6t2Z2ueEDrYVVa64XoO8tOebS%2Bfjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vVpyRszK3%2BypIfGK%2FzngxReEiM%2FlSG%2FbZRs3XcUzEay1l3mEffev43GxMRx022GE9XGDF1NzTQoPw%3D%3D&unid=3456","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=2SIkpg76cZYGQASttHIRqe6olyHrWdtJ3wxb8pvpzB585jRliMF9K22zlzuBVx8gkWZ0d6EhzMqqDM%2BlRwc%2Bi%2BU0ISSq5CzAAy6OIxqvpBM3VpSh0s9h6edth9k8bqqSonv6QcvcARY%3D","coupon_end_time":"2019-02-28","coupon_info":"满5元减3元","coupon_remain_count":"96200","coupon_start_time":"2018-10-30","coupon_total_count":"100000","event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=531068801097","nick":"pingping小店858","num_iid":"531068801097","pict_url":"https://img.alicdn.com/tfscom/i4/444329513/TB1G4qEaval9eJjSZFzXXaITVXa_!!0-item_pic.jpg","provcity":"上海","reserve_price":"8.00","seller_id":"444329513","shop_title":"爱尚家布艺工厂","small_images":{"string":"https://img.alicdn.com/tfscom/i4/444329513/TB2_y.Qf5CYBuNkSnaVXXcMsVXa_!!444329513.jpg"},"status":"1","title":"专用门帘伸缩杆 免打孔 环保强弹簧","tk_rate":"6.30","type":"4","user_type":"0","volume":"13","zk_final_price":"8.00","zk_final_price_wap":"8.00"},{"category":"16","click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3D7o6VWkNvU%2FNw4vFB6t2Z2ueEDrYVVa64XoO8tOebS%2Bfjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vXmYjAG2NNseYAPJ6zyk%2FbRxXvGuS1aodZZtGimpmzUhQjNSMmmDDuisRSPt2qJlWOiZ%2BQMlGz6FQ%3D%3D&unid=3456","event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=566662444269","nick":"有为数码","num_iid":"566662444269","pict_url":"https://img.alicdn.com/tfscom/i2/283318382/TB2aXwUhkSWBuNjSszdXXbeSpXa_!!283318382.jpg","provcity":"广东 揭阳","reserve_price":"9.90","seller_id":"283318382","shop_title":"苏小小韩版女装店","small_images":{"string":["https://img.alicdn.com/tfscom/i3/283318382/TB2DgOQhDtYBeNjy1XdXXXXyVXa_!!283318382.jpg","https://img.alicdn.com/tfscom/i3/283318382/TB2ZaE8hh9YBuNjy0FfXXXIsVXa_!!283318382.jpg","https://img.alicdn.com/tfscom/i4/283318382/TB2aSSobYorBKNjSZFjXXc_SpXa_!!283318382.png","https://img.alicdn.com/tfscom/i1/283318382/TB2GJWob5CYBuNkHFCcXXcHtVXa_!!283318382.jpg"]},"status":"1","title":"2018夏季新款女装成熟时尚韩版潮裙子假两件套装小清新复古连衣裙","tk_rate":"22.50","type":"1","user_type":"0","volume":"1","zk_final_price":"9.90","zk_final_price_wap":"9.90"},{"category":"16","click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3DCYBZlJ%2FRgKtw4vFB6t2Z2ueEDrYVVa64XoO8tOebS%2Bfjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vWplyh2W4e4BBUmZ6aNo%2BzkvznbjQhnx3pFyHKVm8Xj1qOBwmJJpqDPTrnVnG8rlFDGJe8N%2FwNpGw%3D%3D&unid=3456","event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=570668238101","nick":"taoleming88","num_iid":"570668238101","pict_url":"https://img.alicdn.com/tfscom/i4/1020917558/TB2YeC9t7SWBuNjSszdXXbeSpXa_!!1020917558.jpg","provcity":"广东 揭阳","reserve_price":"9.80","seller_id":"1020917558","shop_title":"77格调衣柜","small_images":{"string":["https://img.alicdn.com/tfscom/i4/1020917558/TB2NdgMlByWBuNkSmFPXXXguVXa_!!1020917558.jpg","https://img.alicdn.com/tfscom/i2/1020917558/TB2Solhg_qWBKNjSZFAXXanSpXa_!!1020917558.jpg","https://img.alicdn.com/tfscom/i4/1020917558/TB2T1KUtYSYBuNjSspfXXcZCpXa_!!1020917558.jpg","https://img.alicdn.com/tfscom/i1/1020917558/TB2eUB2tWSWBuNjSsrbXXa0mVXa_!!1020917558.jpg"]},"status":"1","title":"清仓特价2017夏装新款女装春秋款套装裙时尚两件套连衣裙阔腿裤裙","tk_rate":"7.50","type":"1","user_type":"0","volume":"3","zk_final_price":"9.80","zk_final_price_wap":"9.80"},{"category":"16","click_url":"https://s.click.taobao.com/t?e=m%3D2%26s%3DML%2Bs3jocimxw4vFB6t2Z2ueEDrYVVa64XoO8tOebS%2Bfjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vXmYjAG2NNsec8jdYSmKYTxsShUjNLx%2BdtGKHO5ewhh2c%2Blte4d%2BBzmWk2MM523vHhRLBgaW5udaw%3D%3D&unid=3456","event_end_time":"1970-01-01 00:00:00","event_start_time":"1970-01-01 00:00:00","item_url":"http://h5.m.taobao.com/awp/core/detail.htm?id=553505241025","nick":"漫漫青罗批发行","num_iid":"553505241025","pict_url":"https://img.alicdn.com/tfscom/i3/2849539966/TB2LILnxH4npuFjSZFmXXXl4FXa_!!2849539966.jpg","provcity":"福建 泉州","reserve_price":"9.90","seller_id":"2849539966","shop_title":"馨馨女装批发行6店","small_images":{"string":["https://img.alicdn.com/tfscom/i3/2849539966/TB2exMBtHplpuFjSspiXXcdfFXa_!!2849539966.jpg","https://img.alicdn.com/tfscom/i4/2849539966/TB25UiOxOlnpuFjSZFgXXbi7FXa_!!2849539966.jpg","https://img.alicdn.com/tfscom/i2/2849539966/TB2j__CtHBkpuFjy1zkXXbSpFXa_!!2849539966.jpg","https://img.alicdn.com/tfscom/i4/2849539966/TB2yLbcxS8mpuFjSZFMXXaxpVXa_!!2849539966.jpg"]},"status":"1","title":"2017夏季新款胖mm短袖t恤女宽松显瘦纯棉200斤特大码女装休闲上衣","tk_rate":"3.00","type":"4","user_type":"0","volume":"2","zk_final_price":"9.89","zk_final_price_wap":"9.89"}]}
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
        private List<UatmTbkItemBean> uatm_tbk_item;

        public List<UatmTbkItemBean> getUatm_tbk_item() {
            return uatm_tbk_item;
        }

        public void setUatm_tbk_item(List<UatmTbkItemBean> uatm_tbk_item) {
            this.uatm_tbk_item = uatm_tbk_item;
        }

        public static class UatmTbkItemBean {
            /**
             * category : 21
             * click_url : https://s.click.taobao.com/t?e=m%3D2%26s%3DSgoDoYFuvxxw4vFB6t2Z2ueEDrYVVa64yK8Cckff7TXjf2vlNIV67uXDF%2B9KJT0jbJxUEh8sgi9P5cSJ3wzquslkvyzq%2BsQhJFtWEZVI%2B2HfXmme9NYmCrhpTtlIfNSEKe3%2FbelnU7chre9gvq5P3zKYVV1BId1rfp5HoTJX0vWplyh2W4e4BNfkwpW4FUzq9ZsOowOohY0yv9ycLD26agjNSMmmDDuisRSPt2qJlWOiZ%2BQMlGz6FQ%3D%3D&unid=3456
             * event_end_time : 1970-01-01 00:00:00
             * event_start_time : 1970-01-01 00:00:00
             * item_url : http://h5.m.taobao.com/awp/core/detail.htm?id=35945581116
             * nick : 芳夕旗舰店
             * num_iid : 35945581116
             * pict_url : https://img.alicdn.com/tfscom/i2/T1BSUTFotaXXXXXXXX_!!0-item_pic.jpg
             * provcity : 上海
             * reserve_price : 9.90
             * seller_id : 1012740000
             * shop_title : 芳夕旗舰店
             * small_images : {"string":["https://img.alicdn.com/tfscom/i1/1012740000/T2g8WyXxJXXXXXXXXX_!!1012740000.jpg","https://img.alicdn.com/tfscom/i2/1012740000/T2fZ5wXu8aXXXXXXXX_!!1012740000.jpg","https://img.alicdn.com/tfscom/i3/1012740000/T2UeKBXqBXXXXXXXXX_!!1012740000.jpg","https://img.alicdn.com/tfscom/i1/1012740000/T2urmzXv4XXXXXXXXX_!!1012740000.jpg"]}
             * status : 1
             * title : 包邮芳夕 雕花香熏炉 陶瓷香薰炉精油炉 蜡烛香薰灯 送精油+蜡烛
             * tk_rate : 3.00
             * type : 1
             * user_type : 1
             * volume : 15
             * zk_final_price : 9.90
             * zk_final_price_wap : 9.90
             * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=KM%2Fqrv2PJwUGQASttHIRqfsb6Q5Q309IQ0WtdlU2mqJ85jRliMF9K22zlzuBVx8gkWZ0d6EhzMqqDM%2BlRwc%2Bi%2BU0ISSq5CzAAy6OIxqvpBM3VpSh0s9h6TLZyP%2BOsOFo
             * coupon_end_time : 2018-12-01
             * coupon_info : 满5元减2元
             * coupon_remain_count : 9773
             * coupon_start_time : 2018-10-07
             * coupon_total_count : 10000
             */

            private String category;
            private String click_url;
            private String event_end_time;
            private String event_start_time;
            private String item_url;
            private String nick;
            private String num_iid;
            private String pict_url;
            private String provcity;
            private String reserve_price;
            private String seller_id;
            private String shop_title;
            private SmallImagesBean small_images;
            private String status;
            private String title;
            private String tk_rate;
            private String type;
            private String user_type;
            private String volume;
            private String zk_final_price;
            private String zk_final_price_wap;
            private String coupon_click_url;
            private String coupon_end_time;
            private String coupon_info;
            private String coupon_remain_count;
            private String coupon_start_time;
            private String coupon_total_count;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getClick_url() {
                return click_url;
            }

            public void setClick_url(String click_url) {
                this.click_url = click_url;
            }

            public String getEvent_end_time() {
                return event_end_time;
            }

            public void setEvent_end_time(String event_end_time) {
                this.event_end_time = event_end_time;
            }

            public String getEvent_start_time() {
                return event_start_time;
            }

            public void setEvent_start_time(String event_start_time) {
                this.event_start_time = event_start_time;
            }

            public String getItem_url() {
                return item_url;
            }

            public void setItem_url(String item_url) {
                this.item_url = item_url;
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

            public String getShop_title() {
                return shop_title;
            }

            public void setShop_title(String shop_title) {
                this.shop_title = shop_title;
            }

            public SmallImagesBean getSmall_images() {
                return small_images;
            }

            public void setSmall_images(SmallImagesBean small_images) {
                this.small_images = small_images;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTk_rate() {
                return tk_rate;
            }

            public void setTk_rate(String tk_rate) {
                this.tk_rate = tk_rate;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getZk_final_price_wap() {
                return zk_final_price_wap;
            }

            public void setZk_final_price_wap(String zk_final_price_wap) {
                this.zk_final_price_wap = zk_final_price_wap;
            }

            public String getCoupon_click_url() {
                return coupon_click_url;
            }

            public void setCoupon_click_url(String coupon_click_url) {
                this.coupon_click_url = coupon_click_url;
            }

            public String getCoupon_end_time() {
                return coupon_end_time;
            }

            public void setCoupon_end_time(String coupon_end_time) {
                this.coupon_end_time = coupon_end_time;
            }

            public String getCoupon_info() {
                return coupon_info;
            }

            public void setCoupon_info(String coupon_info) {
                this.coupon_info = coupon_info;
            }

            public String getCoupon_remain_count() {
                return coupon_remain_count;
            }

            public void setCoupon_remain_count(String coupon_remain_count) {
                this.coupon_remain_count = coupon_remain_count;
            }

            public String getCoupon_start_time() {
                return coupon_start_time;
            }

            public void setCoupon_start_time(String coupon_start_time) {
                this.coupon_start_time = coupon_start_time;
            }

            public String getCoupon_total_count() {
                return coupon_total_count;
            }

            public void setCoupon_total_count(String coupon_total_count) {
                this.coupon_total_count = coupon_total_count;
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
