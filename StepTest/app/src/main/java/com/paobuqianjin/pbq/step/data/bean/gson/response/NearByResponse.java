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
     * data : {"data":[{"userid":245,"nickname":"周周周","avatar":"","vip":0,"latitude":"22.554764","longitude":"113.937137","step_number":"2292","distance":20.237397173066,"is_follow":0},{"userid":241,"nickname":"rm_13652354126","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/215BF503-6AE1-463D-8C2B-420BBF54BFB1.jpg","vip":1,"latitude":"22.554650","longitude":"113.937123","step_number":"2234","distance":7.6606217460869,"is_follow":0},{"userid":201,"nickname":"九洲涧","avatar":"http://thirdqq.qlogo.cn/qqapp/1106825696/838E8FC341776719299893171D5F552C/100","vip":1,"latitude":"22.554590","longitude":"113.937150","step_number":"1469","distance":1.7785266667447,"is_follow":0},{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","vip":1,"latitude":"22.575980","longitude":"113.874750","step_number":"663","distance":6833.1125470818,"is_follow":0},{"userid":178,"nickname":"嘉年华","avatar":"","vip":1,"latitude":"22.554668","longitude":"113.937145","step_number":"16","distance":9.6170968014248,"is_follow":0},{"userid":246,"nickname":"跑步钱进","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/9FFhUmEXdia1YSaTCicpp8dUhMhuV0Hia7DfAkdPPvuV8Lu95fpcuNB4ekItUQibKeKtFGHCBLBiaGx4UNpRDmZtmzw/132","vip":0,"latitude":"22.554804","longitude":"113.937062","step_number":"10","distance":25.797065775803,"is_follow":0},{"userid":250,"nickname":".","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/icx2uvqOiaR6R1f8UA0V5gp98BcUviaeVpN3VWaMEYyPGQzJTdsJYibbrw3ialv1TMibrJNfLdFL7hrJM08RGxIEvqpA/132","vip":0,"latitude":"22.554574","longitude":"113.937141","step_number":"1","distance":1.0823768605561,"is_follow":0},{"userid":1,"nickname":"嗯好几块没空看开没开门妈妈额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","vip":1,"latitude":"22.554630","longitude":"113.937160","step_number":"0","distance":5.9223386680613,"is_follow":0},{"userid":57,"nickname":"周周","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","vip":1,"latitude":"22.554779","longitude":"113.937169","step_number":"0","distance":22.180702824055,"is_follow":0},{"userid":63,"nickname":"旺旺xwy","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/8042CCF8-59DD-475D-A79D-09E40F6F5CA5.jpg","vip":0,"latitude":"22.609996","longitude":"113.842727","step_number":"0","distance":11485.004155193,"is_follow":0}]}
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
             * vip : 0
             * latitude : 22.554764
             * longitude : 113.937137
             * step_number : 2292
             * distance : 20.237397173066
             * is_follow : 0
             */

            private int userid;
            private String nickname;
            private String avatar;
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
