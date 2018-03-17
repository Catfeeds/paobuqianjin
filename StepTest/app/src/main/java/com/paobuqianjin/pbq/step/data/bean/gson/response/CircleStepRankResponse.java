package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/17.
 */

public class CircleStepRankResponse {

    /**
     * error : 0
     * message : success
     * data : {"id":1,"wx_openid":"","qq_openid":"","mobile":"18588278880","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","levelid":3,"sex":0,"birthyear":2018,"birthmonth":2,"birthday":26,"height":166,"weight":120,"type":1,"province":"安徽省","city":"安庆市","balance":"68.07","target_step":10000,"credit":60,"status":0,"is_perfect":1,"create_time":1512699325,"delete_time":null,"circleid":100302,"circle":[{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":13360},{"userid":66,"nickname":"小沙","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","step_number":9474},{"userid":61,"nickname":"俊","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","step_number":6000},{"userid":67,"nickname":"rm_13828873978","avatar":"","step_number":39}],"step_number":13360,"rank":1}
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
        return "CircleStepRankResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 1
         * wx_openid :
         * qq_openid :
         * mobile : 18588278880
         * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg
         * nickname : 嗯额
         * levelid : 3
         * sex : 0
         * birthyear : 2018
         * birthmonth : 2
         * birthday : 26
         * height : 166
         * weight : 120
         * type : 1
         * province : 安徽省
         * city : 安庆市
         * balance : 68.07
         * target_step : 10000
         * credit : 60
         * status : 0
         * is_perfect : 1
         * create_time : 1512699325
         * delete_time : null
         * circleid : 100302
         * circle : [{"userid":30,"nickname":"黄钦平","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","step_number":13360},{"userid":66,"nickname":"小沙","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","step_number":9474},{"userid":61,"nickname":"俊","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/D6125934-AB04-4203-95F9-C023746A1C08.jpg","step_number":6000},{"userid":67,"nickname":"rm_13828873978","avatar":"","step_number":39}]
         * step_number : 13360
         * rank : 1
         */

        private int id;
        private String wx_openid;
        private String qq_openid;
        private String mobile;
        private String avatar;
        private String nickname;
        private int levelid;
        private int sex;
        private int birthyear;
        private int birthmonth;
        private int birthday;
        private int height;
        private int weight;
        private int type;
        private String province;
        private String city;
        private String balance;
        private int target_step;
        private int credit;
        private int status;
        private int is_perfect;
        private int create_time;
        private Object delete_time;
        private int circleid;
        private int step_number;
        private int rank;
        private List<CircleBean> circle;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWx_openid() {
            return wx_openid;
        }

        public void setWx_openid(String wx_openid) {
            this.wx_openid = wx_openid;
        }

        public String getQq_openid() {
            return qq_openid;
        }

        public void setQq_openid(String qq_openid) {
            this.qq_openid = qq_openid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public int getLevelid() {
            return levelid;
        }

        public void setLevelid(int levelid) {
            this.levelid = levelid;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getBirthyear() {
            return birthyear;
        }

        public void setBirthyear(int birthyear) {
            this.birthyear = birthyear;
        }

        public int getBirthmonth() {
            return birthmonth;
        }

        public void setBirthmonth(int birthmonth) {
            this.birthmonth = birthmonth;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public int getTarget_step() {
            return target_step;
        }

        public void setTarget_step(int target_step) {
            this.target_step = target_step;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_perfect() {
            return is_perfect;
        }

        public void setIs_perfect(int is_perfect) {
            this.is_perfect = is_perfect;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public Object getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(Object delete_time) {
            this.delete_time = delete_time;
        }

        public int getCircleid() {
            return circleid;
        }

        public void setCircleid(int circleid) {
            this.circleid = circleid;
        }

        public int getStep_number() {
            return step_number;
        }

        public void setStep_number(int step_number) {
            this.step_number = step_number;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public List<CircleBean> getCircle() {
            return circle;
        }

        public void setCircle(List<CircleBean> circle) {
            this.circle = circle;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", wx_openid='" + wx_openid + '\'' +
                    ", qq_openid='" + qq_openid + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", levelid=" + levelid +
                    ", sex=" + sex +
                    ", birthyear=" + birthyear +
                    ", birthmonth=" + birthmonth +
                    ", birthday=" + birthday +
                    ", height=" + height +
                    ", weight=" + weight +
                    ", type=" + type +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", balance='" + balance + '\'' +
                    ", target_step=" + target_step +
                    ", credit=" + credit +
                    ", status=" + status +
                    ", is_perfect=" + is_perfect +
                    ", create_time=" + create_time +
                    ", delete_time=" + delete_time +
                    ", circleid=" + circleid +
                    ", step_number=" + step_number +
                    ", rank=" + rank +
                    ", circle=" + circle +
                    '}';
        }

        public static class CircleBean {
            /**
             * userid : 30
             * nickname : 黄钦平
             * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
             * step_number : 13360
             */

            private int userid;
            private String nickname;
            private String avatar;
            private int step_number;

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

            public int getStep_number() {
                return step_number;
            }

            public void setStep_number(int step_number) {
                this.step_number = step_number;
            }

            @Override
            public String toString() {
                return "CircleBean{" +
                        "userid=" + userid +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", step_number=" + step_number +
                        '}';
            }
        }
    }
}
