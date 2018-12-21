package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/12/21.
 */

public class ShareResponse {
    /**
     * error : 0
     * message : success
     * data : {"share_id":"1","reward":{"reward_way":1,"money":"0.00","credit":20}}
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
         * share_id : 1
         * reward : {"reward_way":1,"money":"0.00","credit":20}
         */

        private String share_id;
        private RewardBean reward;

        public String getShare_id() {
            return share_id;
        }

        public void setShare_id(String share_id) {
            this.share_id = share_id;
        }

        public RewardBean getReward() {
            return reward;
        }

        public void setReward(RewardBean reward) {
            this.reward = reward;
        }

        public static class RewardBean {
            /**
             * reward_way : 1
             * money : 0.00
             * credit : 20
             */

            private int reward_way;
            private String money;
            private int credit;

            public int getReward_way() {
                return reward_way;
            }

            public void setReward_way(int reward_way) {
                this.reward_way = reward_way;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getCredit() {
                return credit;
            }

            public void setCredit(int credit) {
                this.credit = credit;
            }
        }
    }
}
