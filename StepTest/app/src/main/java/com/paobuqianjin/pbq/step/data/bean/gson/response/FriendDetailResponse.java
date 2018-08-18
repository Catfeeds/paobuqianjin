package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by admin on 2018年8月8日
 */

public class FriendDetailResponse {
    /**
     * {
     "error":0,
     "message":"success",
     "data":{
     "userid":35822,
     "nickname":"rm_13424156029",
     "sex":0,
     "education":0,
     "birthday":"0-0-0",
     "height":0,
     "weight":"0.00",
     "province":"",
     "city":"",
     "n_long":"113.937298",
     "n_lat":"22.554521",
     "n_updatetime":1533691201,
     "step":574,
     "age":0,
     "is_follow":0,
     "remark_name":"",
     "dynamic":[
     {
     "dynamicid":2114,
     "oneimg":""
     },
     {
     "dynamicid":2095,
     "oneimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/27/35822201807271543349405.jpg"
     },
     {
     "dynamicid":2071,
     "oneimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251621232599.jpg"
     },
     {
     "dynamicid":2070,
     "oneimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251620450429.jpg"
     },
     {
     "dynamicid":2069,
     "oneimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251051809834.png"
     }
     ]
     }
     }
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
         * "userid":35822,
         * "nickname":"rm_13424156029",
         * "sex":0,
         * "education":0,
         * "birthday":"0-0-0",
         * "height":0,
         * "weight":"0.00",
         * "province":"",
         * "city":"",
         * "n_long":"113.937298",
         * "n_lat":"22.554521",
         * "n_updatetime":1533691201,
         * "step":574,
         * "age":0,
         * "is_follow":0,
         * "remark_name":"",
         * "dynamic":[
         * {
         * "dynamicid":2114,
         * "oneimg":""
         * },
         * {
         * "dynamicid":2095,
         * "oneimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/27/35822201807271543349405.jpg"
         * },
         * {
         * "dynamicid":2071,
         * "oneimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251621232599.jpg"
         * },
         * {
         * "dynamicid":2070,
         * "oneimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251620450429.jpg"
         * },
         * {
         * "dynamicid":2069,
         * "oneimg":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2018/7/25/35822201807251051809834.png"
         * }
         * ]
         */

        private String userid;
        private String nickname;
        private String avatar;
        private String userno;
        private String sex;
        private String education;
        private String birthday;
        private String province;
        private String city;
        private String step;
        private int is_follow;
        private String remark_name;
        private List<DynamicOnlyImg> dynamic;

        public String getUserid() {
            return userid;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getUserno() {
            return userno;
        }

        public String getNickname() {
            return nickname;
        }

        public String getSex() {
            return sex;
        }

        public String getEducation() {
            return education;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getStep() {
            return step;
        }

        /**
         * 关注状态：0未关注|1已关注|2互相关注
         * @return
         */
        public int getIs_follow() {
            return is_follow;
        }

        public String getRemark_name() {
            return remark_name;
        }

        public List<DynamicOnlyImg> getDynamic() {
            return dynamic;
        }
    }

    public static class DynamicOnlyImg {
        private int dynamicid;
        private String oneimg;

        public String getOneimg() {
            return oneimg;
        }
    }
}
