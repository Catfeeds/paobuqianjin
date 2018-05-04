package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/2/5.
 */
/*
@className :NearByResponse
*@date 2018/2/5
*@author
*@description 获取附近的人接口返回
*/
public class NearByResponse {
    /**
     * error : 0
     * message : success
     * data : {"data":[{"userid":245,"nickname":"周周周","avatar":"","sex":0,"vip":1,"latitude":"22.554608","longitude":"113.937079","step_number":"2732","distance":5.3927005575911,"is_follow":0},{"userid":253,"nickname":"rm_13682385800","avatar":"","sex":0,"vip":0,"latitude":"22.554740","longitude":"113.937054","step_number":"2732","distance":18.878808294455,"is_follow":0},{"userid":241,"nickname":"rm_13652354126","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/215BF503-6AE1-463D-8C2B-420BBF54BFB1.jpg","sex":2,"vip":1,"latitude":"22.554562","longitude":"113.937078","step_number":"2242","distance":5.2692969301361,"is_follow":0},{"userid":201,"nickname":"九洲涧","avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/838E8FC341776719299893171D5F552C/100","sex":1,"vip":0,"latitude":"22.554590","longitude":"113.937150","step_number":"1763","distance":2.7806765023218,"is_follow":0},{"userid":246,"nickname":"跑步钱进","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/9FFhUmEXdia1YSaTCicpp8dUhMhuV0Hia7DfAkdPPvuV8Lu95fpcuNB4ekItUQibKeKtFGHCBLBiaGx4UNpRDmZtmzw/132","sex":2,"vip":0,"latitude":"22.554618","longitude":"113.937143","step_number":"36","distance":4.3533788136489,"is_follow":0},{"userid":178,"nickname":"嘉年华","avatar":"","sex":2,"vip":0,"latitude":"22.554668","longitude":"113.937145","step_number":"16","distance":9.6940964036013,"is_follow":0},{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","sex":1,"vip":0,"latitude":"22.575980","longitude":"113.874750","step_number":"1","distance":6832.0150445147,"is_follow":0},{"userid":250,"nickname":".","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/icx2uvqOiaR6R1f8UA0V5gp98BcUviaeVpN3VWaMEYyPGQzJTdsJYibbrw3ialv1TMibrJNfLdFL7hrJM08RGxIEvqpA/132","sex":2,"vip":0,"latitude":"22.554574","longitude":"113.937141","step_number":"1","distance":2.0137848993687,"is_follow":0},{"userid":1,"nickname":"嗯好几块没空看开没开门妈妈额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","sex":0,"vip":0,"latitude":"22.554630","longitude":"113.937160","step_number":"0","distance":6.4020203609615,"is_follow":0},{"userid":8,"nickname":"酒自斟","avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","sex":1,"vip":0,"latitude":"22.575980","longitude":"113.874750","step_number":"0","distance":6832.0150445147,"is_follow":0},{"userid":9,"nickname":"孤傲王者","avatar":"http://pic.qqtn.com/up/2017-12/15127898933628309.jpg","sex":1,"vip":0,"latitude":"22.575980","longitude":"113.874750","step_number":"0","distance":6832.0150445147,"is_follow":0},{"userid":10,"nickname":"孤君独战","avatar":"http://pic.qqtn.com/up/2017-12/15127898932239362.jpg","sex":2,"vip":0,"latitude":"22.575980","longitude":"113.874750","step_number":"0","distance":6832.0150445147,"is_follow":0},{"userid":57,"nickname":"周周","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","sex":2,"vip":0,"latitude":"22.554779","longitude":"113.937169","step_number":"0","distance":22.277595000603,"is_follow":0},{"userid":63,"nickname":"旺旺xwy","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/8042CCF8-59DD-475D-A79D-09E40F6F5CA5.jpg","sex":1,"vip":0,"latitude":"22.609996","longitude":"113.842727","step_number":"0","distance":11483.991445851,"is_follow":0},{"userid":66,"nickname":"rm_75997","avatar":"https://rumcdn-1255484416.cos.ap-chengdu.myqcloud.com/img/d_h.png","sex":0,"vip":1,"latitude":"22.554610","longitude":"113.937110","step_number":"0","distance":3.329349430524,"is_follow":0},{"userid":147,"nickname":"何盼","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180402_095809.jpg","sex":1,"vip":0,"latitude":"22.554440","longitude":"113.937220","step_number":"0","distance":18.70785819196,"is_follow":0},{"userid":148,"nickname":"烨雨","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK4PMObfI0CchBeqQia3fYqW3g2E0SRmHXcPcqq6qkweyp1bRhHmaZz81YEicWxOx1jB3nI8W8gWbWg/132","sex":0,"vip":0,"latitude":"22.554570","longitude":"113.937170","step_number":"0","distance":4.9400478210614,"is_follow":0},{"userid":165,"nickname":"跑步钱进","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/9FFhUmEXdia1YSaTCicpp8dUhMhuV0Hia7DfAkdPPvuV8Lu95fpcuNB4ekItUQibKeKtFGHCBLBiaGx4UNpRDmZtmzw/132","sex":1,"vip":1,"latitude":"22.554862","longitude":"113.937086","step_number":"0","distance":31.266528142973,"is_follow":0},{"userid":166,"nickname":"俊","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","sex":1,"vip":0,"latitude":"22.554550","longitude":"113.937150","step_number":"0","distance":4.5388406531078,"is_follow":0},{"userid":174,"nickname":"红米手机","avatar":"","sex":2,"vip":0,"latitude":"22.555086","longitude":"113.936761","step_number":"0","distance":67.211493557706,"is_follow":0},{"userid":179,"nickname":"路","avatar":"","sex":1,"vip":0,"latitude":"22.554550","longitude":"113.937130","step_number":"0","distance":3.7205096119699,"is_follow":0},{"userid":185,"nickname":"何盼","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/1518467817664.jpg","sex":0,"vip":0,"latitude":"22.590260","longitude":"113.955370","step_number":"0","distance":4387.0064328067,"is_follow":0},{"userid":190,"nickname":"当饭吃","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/18200-cde8303da80f6e1bdec6975fa0cb214f_1523462400000_1523548800000.jpg","sex":1,"vip":0,"latitude":"22.554560","longitude":"113.937130","step_number":"0","distance":2.6307975760935,"is_follow":0},{"userid":191,"nickname":"","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/YXr2VhxJpdg2dcIZMrju42mmGXmLabwpbTiaoKq9lP5WbdkhcTkkZ6pjuBwLIQdC0u68UhqUkpb5yZ89PoyMiarA/132","sex":2,"vip":0,"latitude":"22.554550","longitude":"113.937150","step_number":"0","distance":4.5388406531078,"is_follow":0},{"userid":192,"nickname":"bjk","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/YXr2VhxJpdg2dcIZMrju42mmGXmLabwpbTiaoKq9lP5WbdkhcTkkZ6pjuBwLIQdC0u68UhqUkpb5yZ89PoyMiarA/132","sex":1,"vip":0,"latitude":"22.554500","longitude":"113.937140","step_number":"0","distance":9.3736585771039,"is_follow":0},{"userid":193,"nickname":"李","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/YXr2VhxJpdg2dcIZMrju42mmGXmLabwpbTiaoKq9lP5WbdkhcTkkZ6pjuBwLIQdC0u68UhqUkpb5yZ89PoyMiarA/132","sex":0,"vip":0,"latitude":"22.554570","longitude":"113.937140","step_number":"0","distance":2.1875303318336,"is_follow":0},{"userid":194,"nickname":"唐僧","avatar":"","sex":1,"vip":0,"latitude":"22.554520","longitude":"113.937120","step_number":"0","distance":7.0178148511669,"is_follow":0},{"userid":195,"nickname":"九州涧（空）","avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/838E8FC341776719299893171D5F552C/100","sex":1,"vip":0,"latitude":"22.554570","longitude":"113.937110","step_number":"0","distance":2.0382492243463,"is_follow":0},{"userid":199,"nickname":"烨雨","avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/0919F56A42D07DD48AB83583A609CD22/100","sex":0,"vip":0,"latitude":"22.554520","longitude":"113.937110","step_number":"0","distance":7.1513783595676,"is_follow":0},{"userid":204,"nickname":"哈哈哈","avatar":"https://thirdqq.qlogo.cn/qqapp/1106537341/0CA2C4611E03AA3FD652CF6580C10B0B/100","sex":2,"vip":0,"latitude":"22.554790","longitude":"113.937076","step_number":"0","distance":23.538227304896,"is_follow":0},{"userid":205,"nickname":"烨雨","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/WF31lOIW2S6riagibhH27WzicLhyH4BM744qXJqjoWfnia1OLxEaRERwMRUVXda49qkxgiaCwP5lF90icgjUecJbJWng/132","sex":1,"vip":0,"latitude":"22.554570","longitude":"113.937160","step_number":"0","distance":3.9689674496519,"is_follow":0},{"userid":223,"nickname":"雨的星空","avatar":"https://thirdqq.qlogo.cn/qqapp/1106537341/F1C31988B7F6364C51B76FB6025C40ED/100","sex":2,"vip":0,"latitude":"22.554682","longitude":"113.937102","step_number":"0","distance":11.237168351153,"is_follow":0},{"userid":224,"nickname":"adc","avatar":"","sex":0,"vip":0,"latitude":"22.554928","longitude":"113.936969","step_number":"0","distance":41.531299543487,"is_follow":0},{"userid":233,"nickname":"荷包蛋","avatar":"","sex":1,"vip":0,"latitude":"22.554681","longitude":"113.937088","step_number":"0","distance":11.506607067314,"is_follow":0},{"userid":234,"nickname":"Tina","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/EwasHcHdlQ9rxtNtfNJaviaBvsRESVFcmpO5Qf6JRyTwdgTwsSiaRGs1vOKjn8gDtibtX6GiahiaGNJVNN8dQPHD0Rg/132","sex":2,"vip":0,"latitude":"22.554682","longitude":"113.937139","step_number":"0","distance":11.115411633598,"is_follow":0},{"userid":235,"nickname":"abcdefg哦哦哦www你仔细嘻同意O","avatar":"","sex":0,"vip":0,"latitude":"22.554664","longitude":"113.937102","step_number":"0","distance":9.2852737030706,"is_follow":0},{"userid":239,"nickname":"雨的星空","avatar":"https://thirdqq.qlogo.cn/qqapp/1106537341/F1C31988B7F6364C51B76FB6025C40ED/100","sex":2,"vip":0,"latitude":"22.554607","longitude":"113.937118","step_number":"0","distance":2.7382209328159,"is_follow":0},{"userid":240,"nickname":"雨的星空","avatar":"https://thirdqq.qlogo.cn/qqapp/1106537341/F1C31988B7F6364C51B76FB6025C40ED/100","sex":2,"vip":0,"latitude":"22.554637","longitude":"113.937114","step_number":"0","distance":6.0911214164288,"is_follow":0},{"userid":242,"nickname":"联通谭","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_1525266009625.jpg","sex":1,"vip":0,"latitude":"22.554686","longitude":"113.937058","step_number":"0","distance":13.307920591432,"is_follow":0},{"userid":243,"nickname":"移动谭","avatar":"","sex":0,"vip":0,"latitude":"22.554670","longitude":"113.937096","step_number":"0","distance":10.092168071853,"is_follow":0}]}
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
        return "NearByResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "data=" + data +
                    '}';
        }

        public static class DataBean {
            /**
             * userid : 245
             * nickname : 周周周
             * avatar :
             * sex : 0
             * vip : 1
             * latitude : 22.554608
             * longitude : 113.937079
             * step_number : 2732
             * distance : 5.3927005575911
             * is_follow : 0
             */

            private int userid;
            private String nickname;
            private String avatar;
            private int sex;
            private int vip;
            private String latitude;
            private String longitude;
            private int step_number;
            private double distance;
            private int is_follow;

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

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
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

            public int getStep_number() {
                return step_number;
            }

            public void setStep_number(int step_number) {
                this.step_number = step_number;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public int getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(int is_follow) {
                this.is_follow = is_follow;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "userid=" + userid +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", sex=" + sex +
                        ", vip=" + vip +
                        ", latitude='" + latitude + '\'' +
                        ", longitude='" + longitude + '\'' +
                        ", step_number='" + step_number + '\'' +
                        ", distance=" + distance +
                        ", is_follow=" + is_follow +
                        '}';
            }
        }
    }
}
