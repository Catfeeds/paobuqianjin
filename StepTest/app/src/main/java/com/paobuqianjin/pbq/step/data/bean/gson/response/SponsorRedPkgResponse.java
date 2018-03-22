package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/3/22.
 */
/*
@className :SponsorRedPkgResponse
*@date 2018/3/22
*@author
*@description 获取附近商户红包
*/
public class SponsorRedPkgResponse {
    /**
     * error : 0
     * message : success
     * data : {"total_red_packet":4.04,"data":[{"id":1,"logo":"https://img.meituan.net/msmerchant/c641ffddf16a11c73f40aa24d5fad53737125.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"金利龙酒楼","hours":"周一至周日 11:30-01:00","tel":"0755-8249069","address":"八卦一路47号(老酒川菜馆对面)","longitude":"114.108071","latitude":"22.564662","total_money":"9928.87","environment_images":["http://qcloud.dpfile.com/pc/pjOb53a8HLKVmDNIJyucuWO2tM5KJAwhkmOxbIVGc71F-0yissx54CYggZKkmVktTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/SIGusdKI3fjjuvbtU_614CdNPUbDdL3UoXpTh4wEAAaOS9KTf3d-j_wdvfcJFGnLTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/N15Ki3mzSqsCZIpyEHRcNVqWi8cuFhxHL8kh4zJeSoRfvXDPj6ufn7ygGkheaIIZTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/Lr7BY1nvBzElETofaq0reN0vHOPb7bvdtVQuFt10gPaX9oUsvtzPecE0z1l25yCuTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/Nk5PfvYvAxp-2_VHjDX4TNznJnphnLlC81Rb9IQaPRxWzN_4hUPhHPENFzwE2h71TYGVDmosZWTLal1WbWRW3A.jpg"],"goods_images":["http://qcloud.dpfile.com/pc/cuG9IqK-jUcBYzwhUSJ-q_0Hj7yt9QXqAeU9nR6Ec2ZLEw4bJRr9m6L_LKNNFAG_TYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/VxvWAVU63D22XHjEZSDf1T6J0lKgpSSj3zX1klvy2QeBIyGHOMGHtHbXfqxk0-SJTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/qqRw5PCeHf76nYJPmE49v2fjFZbLZymYquzsT7wiBqYJax8J12Hib8O1myTwRDFoTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/FrxlZt16WSA6eOQi4jst08NXQwDn2_7ptBqIhPwBIsxCuOLBp4VcPfUu7tXbiRZdTYGVDmosZWTLal1WbWRW3A.jpg"],"create_time":0,"distance":0.25116310480322,"red_packet":0.08},{"id":4,"logo":"http://p1.meituan.net/msmerchant/2dbf035ecfa10bd944ce1a62441cfe04105778.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"水木潮汕牛肉馆","hours":"周一至周日 10:00-05:00","tel":"0755-8269699","address":"八卦一路美食街8栋14-1号","longitude":"114.108138","latitude":"22.563672","total_money":"9723.04","environment_images":[],"goods_images":[],"create_time":0,"distance":110.07696125555,"red_packet":0.72},{"id":3,"logo":"https://img.meituan.net/msmerchant/4734b3204fcab10d094102951085daa258771.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"新起点脆肉鲩火锅","hours":"周一至周日 11:00-14:30 17:00-23:00","tel":"0755-2293477","address":"八卦二路106号(上林苑酒店一楼)","longitude":"114.106591","latitude":"22.565430","total_money":"7182.83","environment_images":[],"goods_images":[],"create_time":0,"distance":174.33162173177,"red_packet":0.37},{"id":6,"logo":"http://p1.meituan.net/mogu/df47c05d505da89002d8a2654113780f289952.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"重庆梁山鸡","hours":"周一至周日 11:00-03:00","tel":"15818782389","address":"八卦岭八卦二路二栋49号(串门串串香火锅旁)","longitude":"114.105844","latitude":"22.565009","total_money":"6270.01","environment_images":[],"goods_images":[],"create_time":0,"distance":231.83102885486,"red_packet":0.8},{"id":2,"logo":"https://img.meituan.net/msmerchant/3c8e00650f703026903e9c5b0f39e7c8176127.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"慕炭东北烤肉","hours":"周一至周日 11:30-01:00","tel":"0755-2220882","address":"八卦一路4号银泉酒店一楼","longitude":"114.110061","latitude":"22.563642","total_money":"5392.40","environment_images":["http://qcloud.dpfile.com/pc/NfdHLe9qKfJ-7o8lLHgLlEuHl2AxsM49Ai-wvzi3SaDrnnpZez_AhVhqWF73PBr4TYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/8g8pHTPpAIYhYG-S3K6jUsg4Bdp6eeHpq_NUO-5hdjUaWV7jnGXDu6q3ZNCC13ooTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/IQCumZt5Q1A6NW-q6XTSMGDwmDwiOhmx9INpSBfbRZaMeRpyKCg0A0wXsXAOAdxoTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/nZVDREwErAcY3VdNBsRQW-bQ2Kny8yz9useCVeX4yWBF-0yissx54CYggZKkmVktTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/lOKBXa2Cjor3wZvCaFr-FgtErqqUCdkx16dZjvsKHhix0FB5XAwjlC-OyK5Y2SgaTYGVDmosZWTLal1WbWRW3A.jpg"],"goods_images":["http://qcloud.dpfile.com/pc/009h__kBilu8wiRZLUfmpP45z96oNgCcjp-1P09htW3Sg2m0nWO5k_AN68tpSUOMTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/c5L1DnICIOothC-klQV8BBjzrpYmvlUNegJwL2MrHfhF3HNB3vggzmw8raY1JNntTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/1yat4CTvVlDtmY5Gl2A7vcb3NsnWgRN5vd_GZ2vqSjpdl2PSGM1_kJwSN2GQRCDrTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/q2LhWjWIcyT_zFDUHdPax2cOdhXrUFS0CpfiuvezcAA4OnA797ve0qcViZcSKwMVTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/z7bxfurT7sS6b1Wlff85AAYWG1cx7Gdh6dOaMnKn8RPRKFpYEo6H3OpKaU98Sy8-TYGVDmosZWTLal1WbWRW3A.jpg"],"create_time":0,"distance":233.67638497825,"red_packet":0.45},{"id":5,"logo":"http://p1.meituan.net/mogu/2ec2261d38fed7780525649574bb1dba215891.png%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"五味缘钢管厂小郡肝串串香火锅","hours":"","tel":"0755-2510857","address":"八卦一路盛世鹏城花园5号(上步路与笋岗路交汇处东北侧)","longitude":"114.105476","latitude":"22.563135","total_money":"7091.19","environment_images":[],"goods_images":[],"create_time":0,"distance":315.74186473716,"red_packet":0.11},{"id":10,"logo":"http://qcloud.dpfile.com/pc/acQtsD6mCGf1za9038BBQio538JFu94Q-8DJImNfTnk9umVb1JMGHSHLe193sXzls9bU8u7TlyVW3ovyQ7Jtmw.jpg","name":"唐潮酒楼","hours":"周一至周日 09:30-13:30 下午茶 14:00-15:30 晚市 17:00-22:00","tel":"0755-2221066","address":"八卦三路荣生综合楼98号(近荣生大厦)","longitude":"114.106862","latitude":"22.568361","total_money":"4086.15","environment_images":[],"goods_images":[],"create_time":0,"distance":429.79858670726,"red_packet":0.2},{"id":9,"logo":"https://p0.meituan.net/shopmainpic/fd6bd0d9be2a7fb4b4a82da7dfc9ccec31906.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"麓谷小镇","hours":"周一至周日 10:00-22:00","tel":"13530750702","address":"八卦一路50-4鹏基时空商务大厦1楼","longitude":"114.102061","latitude":"22.566685","total_money":"8067.10","environment_images":[],"goods_images":[],"create_time":0,"distance":656.78602493602,"red_packet":0.76},{"id":8,"logo":"https://img.meituan.net/msmerchant/894c95614a3d43d159aae0d4f4e3dbbc81757.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"小条食堂","hours":"周一至周日 10:00-22:00","tel":"0755-2290993","address":"园岭中路万佳广场一楼1306铺","longitude":"114.106146","latitude":"22.558189","total_money":"8752.72","environment_images":[],"goods_images":[],"create_time":0,"distance":746.13670234823,"red_packet":0.15},{"id":7,"logo":"http://p1.meituan.net/mogu/8de6ef0169a448258296ebf794574826440455.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"爱上素食","hours":"周一至周日 午市 11:30-13:30 晚市 17:30-20:30","tel":"13689573601","address":"松园路鸿翔花园商铺1栋2楼","longitude":"114.112713","latitude":"22.559069","total_money":"5230.79","environment_images":[],"goods_images":[],"create_time":0,"distance":783.41904939659,"red_packet":0.4}]}
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
        return "SponsorRedPkgResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * total_red_packet : 4.04
         * data : [{"id":1,"logo":"https://img.meituan.net/msmerchant/c641ffddf16a11c73f40aa24d5fad53737125.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"金利龙酒楼","hours":"周一至周日 11:30-01:00","tel":"0755-8249069","address":"八卦一路47号(老酒川菜馆对面)","longitude":"114.108071","latitude":"22.564662","total_money":"9928.87","environment_images":["http://qcloud.dpfile.com/pc/pjOb53a8HLKVmDNIJyucuWO2tM5KJAwhkmOxbIVGc71F-0yissx54CYggZKkmVktTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/SIGusdKI3fjjuvbtU_614CdNPUbDdL3UoXpTh4wEAAaOS9KTf3d-j_wdvfcJFGnLTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/N15Ki3mzSqsCZIpyEHRcNVqWi8cuFhxHL8kh4zJeSoRfvXDPj6ufn7ygGkheaIIZTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/Lr7BY1nvBzElETofaq0reN0vHOPb7bvdtVQuFt10gPaX9oUsvtzPecE0z1l25yCuTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/Nk5PfvYvAxp-2_VHjDX4TNznJnphnLlC81Rb9IQaPRxWzN_4hUPhHPENFzwE2h71TYGVDmosZWTLal1WbWRW3A.jpg"],"goods_images":["http://qcloud.dpfile.com/pc/cuG9IqK-jUcBYzwhUSJ-q_0Hj7yt9QXqAeU9nR6Ec2ZLEw4bJRr9m6L_LKNNFAG_TYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/VxvWAVU63D22XHjEZSDf1T6J0lKgpSSj3zX1klvy2QeBIyGHOMGHtHbXfqxk0-SJTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/qqRw5PCeHf76nYJPmE49v2fjFZbLZymYquzsT7wiBqYJax8J12Hib8O1myTwRDFoTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/FrxlZt16WSA6eOQi4jst08NXQwDn2_7ptBqIhPwBIsxCuOLBp4VcPfUu7tXbiRZdTYGVDmosZWTLal1WbWRW3A.jpg"],"create_time":0,"distance":0.25116310480322,"red_packet":0.08},{"id":4,"logo":"http://p1.meituan.net/msmerchant/2dbf035ecfa10bd944ce1a62441cfe04105778.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"水木潮汕牛肉馆","hours":"周一至周日 10:00-05:00","tel":"0755-8269699","address":"八卦一路美食街8栋14-1号","longitude":"114.108138","latitude":"22.563672","total_money":"9723.04","environment_images":[],"goods_images":[],"create_time":0,"distance":110.07696125555,"red_packet":0.72},{"id":3,"logo":"https://img.meituan.net/msmerchant/4734b3204fcab10d094102951085daa258771.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"新起点脆肉鲩火锅","hours":"周一至周日 11:00-14:30 17:00-23:00","tel":"0755-2293477","address":"八卦二路106号(上林苑酒店一楼)","longitude":"114.106591","latitude":"22.565430","total_money":"7182.83","environment_images":[],"goods_images":[],"create_time":0,"distance":174.33162173177,"red_packet":0.37},{"id":6,"logo":"http://p1.meituan.net/mogu/df47c05d505da89002d8a2654113780f289952.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"重庆梁山鸡","hours":"周一至周日 11:00-03:00","tel":"15818782389","address":"八卦岭八卦二路二栋49号(串门串串香火锅旁)","longitude":"114.105844","latitude":"22.565009","total_money":"6270.01","environment_images":[],"goods_images":[],"create_time":0,"distance":231.83102885486,"red_packet":0.8},{"id":2,"logo":"https://img.meituan.net/msmerchant/3c8e00650f703026903e9c5b0f39e7c8176127.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"慕炭东北烤肉","hours":"周一至周日 11:30-01:00","tel":"0755-2220882","address":"八卦一路4号银泉酒店一楼","longitude":"114.110061","latitude":"22.563642","total_money":"5392.40","environment_images":["http://qcloud.dpfile.com/pc/NfdHLe9qKfJ-7o8lLHgLlEuHl2AxsM49Ai-wvzi3SaDrnnpZez_AhVhqWF73PBr4TYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/8g8pHTPpAIYhYG-S3K6jUsg4Bdp6eeHpq_NUO-5hdjUaWV7jnGXDu6q3ZNCC13ooTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/IQCumZt5Q1A6NW-q6XTSMGDwmDwiOhmx9INpSBfbRZaMeRpyKCg0A0wXsXAOAdxoTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/nZVDREwErAcY3VdNBsRQW-bQ2Kny8yz9useCVeX4yWBF-0yissx54CYggZKkmVktTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/lOKBXa2Cjor3wZvCaFr-FgtErqqUCdkx16dZjvsKHhix0FB5XAwjlC-OyK5Y2SgaTYGVDmosZWTLal1WbWRW3A.jpg"],"goods_images":["http://qcloud.dpfile.com/pc/009h__kBilu8wiRZLUfmpP45z96oNgCcjp-1P09htW3Sg2m0nWO5k_AN68tpSUOMTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/c5L1DnICIOothC-klQV8BBjzrpYmvlUNegJwL2MrHfhF3HNB3vggzmw8raY1JNntTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/1yat4CTvVlDtmY5Gl2A7vcb3NsnWgRN5vd_GZ2vqSjpdl2PSGM1_kJwSN2GQRCDrTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/q2LhWjWIcyT_zFDUHdPax2cOdhXrUFS0CpfiuvezcAA4OnA797ve0qcViZcSKwMVTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/z7bxfurT7sS6b1Wlff85AAYWG1cx7Gdh6dOaMnKn8RPRKFpYEo6H3OpKaU98Sy8-TYGVDmosZWTLal1WbWRW3A.jpg"],"create_time":0,"distance":233.67638497825,"red_packet":0.45},{"id":5,"logo":"http://p1.meituan.net/mogu/2ec2261d38fed7780525649574bb1dba215891.png%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"五味缘钢管厂小郡肝串串香火锅","hours":"","tel":"0755-2510857","address":"八卦一路盛世鹏城花园5号(上步路与笋岗路交汇处东北侧)","longitude":"114.105476","latitude":"22.563135","total_money":"7091.19","environment_images":[],"goods_images":[],"create_time":0,"distance":315.74186473716,"red_packet":0.11},{"id":10,"logo":"http://qcloud.dpfile.com/pc/acQtsD6mCGf1za9038BBQio538JFu94Q-8DJImNfTnk9umVb1JMGHSHLe193sXzls9bU8u7TlyVW3ovyQ7Jtmw.jpg","name":"唐潮酒楼","hours":"周一至周日 09:30-13:30 下午茶 14:00-15:30 晚市 17:00-22:00","tel":"0755-2221066","address":"八卦三路荣生综合楼98号(近荣生大厦)","longitude":"114.106862","latitude":"22.568361","total_money":"4086.15","environment_images":[],"goods_images":[],"create_time":0,"distance":429.79858670726,"red_packet":0.2},{"id":9,"logo":"https://p0.meituan.net/shopmainpic/fd6bd0d9be2a7fb4b4a82da7dfc9ccec31906.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"麓谷小镇","hours":"周一至周日 10:00-22:00","tel":"13530750702","address":"八卦一路50-4鹏基时空商务大厦1楼","longitude":"114.102061","latitude":"22.566685","total_money":"8067.10","environment_images":[],"goods_images":[],"create_time":0,"distance":656.78602493602,"red_packet":0.76},{"id":8,"logo":"https://img.meituan.net/msmerchant/894c95614a3d43d159aae0d4f4e3dbbc81757.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"小条食堂","hours":"周一至周日 10:00-22:00","tel":"0755-2290993","address":"园岭中路万佳广场一楼1306铺","longitude":"114.106146","latitude":"22.558189","total_money":"8752.72","environment_images":[],"goods_images":[],"create_time":0,"distance":746.13670234823,"red_packet":0.15},{"id":7,"logo":"http://p1.meituan.net/mogu/8de6ef0169a448258296ebf794574826440455.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20","name":"爱上素食","hours":"周一至周日 午市 11:30-13:30 晚市 17:30-20:30","tel":"13689573601","address":"松园路鸿翔花园商铺1栋2楼","longitude":"114.112713","latitude":"22.559069","total_money":"5230.79","environment_images":[],"goods_images":[],"create_time":0,"distance":783.41904939659,"red_packet":0.4}]
         */

        private double total_red_packet;
        private List<DataBean> data;

        public double getTotal_red_packet() {
            return total_red_packet;
        }

        public void setTotal_red_packet(double total_red_packet) {
            this.total_red_packet = total_red_packet;
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
                    "total_red_packet=" + total_red_packet +
                    ", data=" + data +
                    '}';
        }

        public static class DataBean implements Serializable {
            /**
             * id : 1
             * logo : https://img.meituan.net/msmerchant/c641ffddf16a11c73f40aa24d5fad53737125.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D1%26%26r%3D1%26p%3D9%26x%3D2%26y%3D2%26relative%3D1%26o%3D20
             * name : 金利龙酒楼
             * hours : 周一至周日 11:30-01:00
             * tel : 0755-8249069
             * address : 八卦一路47号(老酒川菜馆对面)
             * longitude : 114.108071
             * latitude : 22.564662
             * total_money : 9928.87
             * environment_images : ["http://qcloud.dpfile.com/pc/pjOb53a8HLKVmDNIJyucuWO2tM5KJAwhkmOxbIVGc71F-0yissx54CYggZKkmVktTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/SIGusdKI3fjjuvbtU_614CdNPUbDdL3UoXpTh4wEAAaOS9KTf3d-j_wdvfcJFGnLTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/N15Ki3mzSqsCZIpyEHRcNVqWi8cuFhxHL8kh4zJeSoRfvXDPj6ufn7ygGkheaIIZTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/Lr7BY1nvBzElETofaq0reN0vHOPb7bvdtVQuFt10gPaX9oUsvtzPecE0z1l25yCuTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/Nk5PfvYvAxp-2_VHjDX4TNznJnphnLlC81Rb9IQaPRxWzN_4hUPhHPENFzwE2h71TYGVDmosZWTLal1WbWRW3A.jpg"]
             * goods_images : ["http://qcloud.dpfile.com/pc/cuG9IqK-jUcBYzwhUSJ-q_0Hj7yt9QXqAeU9nR6Ec2ZLEw4bJRr9m6L_LKNNFAG_TYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/VxvWAVU63D22XHjEZSDf1T6J0lKgpSSj3zX1klvy2QeBIyGHOMGHtHbXfqxk0-SJTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/qqRw5PCeHf76nYJPmE49v2fjFZbLZymYquzsT7wiBqYJax8J12Hib8O1myTwRDFoTYGVDmosZWTLal1WbWRW3A.jpg","http://qcloud.dpfile.com/pc/FrxlZt16WSA6eOQi4jst08NXQwDn2_7ptBqIhPwBIsxCuOLBp4VcPfUu7tXbiRZdTYGVDmosZWTLal1WbWRW3A.jpg"]
             * create_time : 0
             * distance : 0.25116310480322
             * red_packet : 0.08
             */

            private int id;
            private String logo;
            private String name;
            private String hours;
            private String tel;
            private String address;
            private String longitude;
            private String latitude;
            private String total_money;
            private int create_time;
            private double distance;
            private double red_packet;
            private List<String> environment_images;
            private List<String> goods_images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
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

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public double getRed_packet() {
                return red_packet;
            }

            public void setRed_packet(double red_packet) {
                this.red_packet = red_packet;
            }

            public List<String> getEnvironment_images() {
                return environment_images;
            }

            public void setEnvironment_images(List<String> environment_images) {
                this.environment_images = environment_images;
            }

            public List<String> getGoods_images() {
                return goods_images;
            }

            public void setGoods_images(List<String> goods_images) {
                this.goods_images = goods_images;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", logo='" + logo + '\'' +
                        ", name='" + name + '\'' +
                        ", hours='" + hours + '\'' +
                        ", tel='" + tel + '\'' +
                        ", address='" + address + '\'' +
                        ", longitude='" + longitude + '\'' +
                        ", latitude='" + latitude + '\'' +
                        ", total_money='" + total_money + '\'' +
                        ", create_time=" + create_time +
                        ", distance=" + distance +
                        ", red_packet=" + red_packet +
                        ", environment_images=" + environment_images +
                        ", goods_images=" + goods_images +
                        '}';
            }
        }
    }
}
