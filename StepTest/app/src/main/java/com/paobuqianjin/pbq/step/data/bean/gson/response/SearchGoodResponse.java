package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2019/1/15.
 */

public class SearchGoodResponse {
    /**
     * error : 0
     * message : success
     * data : [{"id":38,"userid":35828,"name":"洗衣机","old_price":"1000.00","credit":100,"number":1,"express_status":0,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6E17C91D-EDA9-4412-9CCC-ED16BBCD88B1.jpg"},{"id":39,"userid":35822,"name":"嘤嘤嘤","old_price":"0.00","credit":100,"number":1,"express_status":0,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/9/35822201901092007676061.jpg"},{"id":40,"userid":35842,"name":"米老鼠","old_price":"50.00","credit":100,"number":1,"express_status":1,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/907D7E63-4D6E-4402-8834-A18DD66FA523.jpg"},{"id":41,"userid":35842,"name":"笔盒","old_price":"20.00","credit":100,"number":1,"express_status":1,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/DE45B412-71B4-4DD6-9A9B-6202B9F1828C.jpg"},{"id":42,"userid":35842,"name":"别墅装修设计效果图制作过程","old_price":"1000.00","credit":1000,"number":1,"express_status":0,"status":1,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/38573C30-9BDC-4B49-A05E-B877EB0620F1.jpg"},{"id":43,"userid":35905,"name":"高级盆栽","old_price":"300.00","credit":300,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35905201901101048772806.jpg"},{"id":44,"userid":35905,"name":"只要邮费","old_price":"30.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35905201901101111182710.jpg"},{"id":45,"userid":35822,"name":"35822","old_price":"100.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101415971523.jpg"},{"id":46,"userid":35828,"name":"书包书包书包","old_price":"100.00","credit":100,"number":1,"express_status":2,"status":2,"img_url":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/B22F3281-8CC5-40F4-B585-3F81581B0D74.jpg"},{"id":47,"userid":35875,"name":"35871汽车","old_price":"10.00","credit":10,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35875201901101450776645.jpg"},{"id":48,"userid":35875,"name":"001","old_price":"1.00","credit":10,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35875201901101515690374.jpg"},{"id":49,"userid":35875,"name":"002","old_price":"10.00","credit":10,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35875201901101520621742.jpg"},{"id":50,"userid":35822,"name":"效果很好好","old_price":"10.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101536602927.jpg"},{"id":51,"userid":35822,"name":"003","old_price":"30.00","credit":30,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101537237849.jpg"},{"id":52,"userid":35822,"name":"005","old_price":"30.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101616419824.jpg"},{"id":53,"userid":35822,"name":"006","old_price":"10.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101618152762.jpg"},{"id":54,"userid":35822,"name":"优惠券","old_price":"20.00","credit":10,"number":1,"express_status":2,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101618278067.jpg"},{"id":55,"userid":35822,"name":"啊舒服个","old_price":"15.00","credit":100,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101619336319.jpg"},{"id":56,"userid":35822,"name":"008","old_price":"30.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101621668266.jpg"},{"id":57,"userid":35822,"name":"009","old_price":"12.00","credit":18,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101622535987.jpg"},{"id":58,"userid":35822,"name":"010","old_price":"50.00","credit":200,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101625655696.jpg"},{"id":59,"userid":35822,"name":"011","old_price":"38.00","credit":20,"number":1,"express_status":1,"status":2,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/10/35822201901101626581716.jpg"},{"id":60,"userid":35822,"name":"珠宝","old_price":"30.00","credit":10,"number":1,"express_status":1,"status":1,"img_url":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/15/35822201901151006336874.jpg"}]
     */

    private int error;
    private String message;
    private List<ExListResponse.DataBeanX.DataBean> data;

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

    public List<ExListResponse.DataBeanX.DataBean> getData() {
        return data;
    }

    public void setData(List<ExListResponse.DataBeanX.DataBean> data) {
        this.data = data;
    }



}
