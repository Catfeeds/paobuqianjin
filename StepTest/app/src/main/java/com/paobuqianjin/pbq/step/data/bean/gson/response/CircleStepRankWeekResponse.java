package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/30.
 */
/*
@className :CircleStepRankWeekResponse
*@date 2018/3/30
*@author
*@description 个人周名次
*/
public class CircleStepRankWeekResponse {
    /**
     * error : 0
     * message : success
     * data : {"userid":30,"step_number":"16562","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","rank":2,"sum_step":66916,"avg_step":16729}
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

    @Override
    public String toString() {
        return "CircleStepRankWeekResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * userid : 30
         * step_number : 16562
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
         * nickname : 黄钦平
         * rank : 2
         * sum_step : 66916
         * avg_step : 16729
         */

        private int userid;
        private String step_number;
        private String avatar;
        private String nickname;
        private int rank;
        private int sum_step;
        private int avg_step;

        @Override
        public String toString() {
            return "DataBean{" +
                    "userid=" + userid +
                    ", step_number='" + step_number + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", rank=" + rank +
                    ", sum_step=" + sum_step +
                    ", avg_step=" + avg_step +
                    ", vip=" + vip +
                    '}';
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        private int vip;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getStep_number() {
            return step_number;
        }

        public void setStep_number(String step_number) {
            this.step_number = step_number;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getSum_step() {
            return sum_step;
        }

        public void setSum_step(int sum_step) {
            this.sum_step = sum_step;
        }

        public int getAvg_step() {
            return avg_step;
        }

        public void setAvg_step(int avg_step) {
            this.avg_step = avg_step;
        }

    }
}
