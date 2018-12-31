package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/12/29.
 */

public class ExGoodDetailResponse {
    /**
     * error : 0
     * message : success
     * data : {"id":7,"userid":35905,"name":"好几块","content":"么啦啦啦啦上课","old_price":"30.00","credit":100,"number":1,"express_status":2,"express_price":"0.00","status":1,"delete_id":0,"imgs_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081145950904.jpg"],"is_need":0,"need_count":0,"user_info":{"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/5/35905201901051655987828.jpg","nickname":"呵呵哒","sale_count":3,"trade_count":0,"other_commnuity":[{"id":5,"userid":35905,"name":"路虎","old_price":"1223.00","credit":1000,"number":1,"express_status":1,"express_price":"10.00","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081111520394.jpg"]},{"id":6,"userid":35905,"name":"路虎","old_price":"30.00","credit":300,"number":1,"express_status":2,"express_price":"0.00","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"]}]}}
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

    public static class DataBean implements Serializable {
        /**
         * id : 7
         * userid : 35905
         * name : 好几块
         * content : 么啦啦啦啦上课
         * old_price : 30.00
         * credit : 100
         * number : 1
         * express_status : 2
         * express_price : 0.00
         * status : 1
         * delete_id : 0
         * imgs_arr : ["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081145950904.jpg"]
         * is_need : 0
         * need_count : 0
         * user_info : {"avatar":"http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/5/35905201901051655987828.jpg","nickname":"呵呵哒","sale_count":3,"trade_count":0,"other_commnuity":[{"id":5,"userid":35905,"name":"路虎","old_price":"1223.00","credit":1000,"number":1,"express_status":1,"express_price":"10.00","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081111520394.jpg"]},{"id":6,"userid":35905,"name":"路虎","old_price":"30.00","credit":300,"number":1,"express_status":2,"express_price":"0.00","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"]}]}
         */

        private int id;
        private int userid;
        private String name;
        private String content;
        private String old_price;
        private int credit;
        private int number;
        private int express_status;
        private String express_price;
        private int status;
        private int delete_id;
        private int is_need;
        private int need_count;
        private UserInfoBean user_info;
        private List<String> imgs_arr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOld_price() {
            return old_price;
        }

        public void setOld_price(String old_price) {
            this.old_price = old_price;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getExpress_status() {
            return express_status;
        }

        public void setExpress_status(int express_status) {
            this.express_status = express_status;
        }

        public String getExpress_price() {
            return express_price;
        }

        public void setExpress_price(String express_price) {
            this.express_price = express_price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getDelete_id() {
            return delete_id;
        }

        public void setDelete_id(int delete_id) {
            this.delete_id = delete_id;
        }

        public int getIs_need() {
            return is_need;
        }

        public void setIs_need(int is_need) {
            this.is_need = is_need;
        }

        public int getNeed_count() {
            return need_count;
        }

        public void setNeed_count(int need_count) {
            this.need_count = need_count;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public List<String> getImgs_arr() {
            return imgs_arr;
        }

        public void setImgs_arr(List<String> imgs_arr) {
            this.imgs_arr = imgs_arr;
        }

        public static class UserInfoBean implements Serializable {
            /**
             * avatar : http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/5/35905201901051655987828.jpg
             * nickname : 呵呵哒
             * sale_count : 3
             * trade_count : 0
             * other_commnuity : [{"id":5,"userid":35905,"name":"路虎","old_price":"1223.00","credit":1000,"number":1,"express_status":1,"express_price":"10.00","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081111520394.jpg"]},{"id":6,"userid":35905,"name":"路虎","old_price":"30.00","credit":300,"number":1,"express_status":2,"express_price":"0.00","img_arr":["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081140993534.jpg"]}]
             */

            private String avatar;
            private String nickname;
            private int sale_count;
            private int trade_count;
            private List<OtherCommnuityBean> other_commnuity;

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

            public int getSale_count() {
                return sale_count;
            }

            public void setSale_count(int sale_count) {
                this.sale_count = sale_count;
            }

            public int getTrade_count() {
                return trade_count;
            }

            public void setTrade_count(int trade_count) {
                this.trade_count = trade_count;
            }

            public List<OtherCommnuityBean> getOther_commnuity() {
                return other_commnuity;
            }

            public void setOther_commnuity(List<OtherCommnuityBean> other_commnuity) {
                this.other_commnuity = other_commnuity;
            }

            public static class OtherCommnuityBean implements Serializable {
                /**
                 * id : 5
                 * userid : 35905
                 * name : 路虎
                 * old_price : 1223.00
                 * credit : 1000
                 * number : 1
                 * express_status : 1
                 * express_price : 10.00
                 * img_arr : ["http://pbqj-cdn.oss-cn-shenzhen.aliyuncs.com/2019/1/8/35905201901081111520394.jpg"]
                 */

                private int id;
                private int userid;
                private String name;
                private String old_price;
                private int credit;
                private int number;
                private int express_status;
                private String express_price;
                private List<String> img_arr;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOld_price() {
                    return old_price;
                }

                public void setOld_price(String old_price) {
                    this.old_price = old_price;
                }

                public int getCredit() {
                    return credit;
                }

                public void setCredit(int credit) {
                    this.credit = credit;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public int getExpress_status() {
                    return express_status;
                }

                public void setExpress_status(int express_status) {
                    this.express_status = express_status;
                }

                public String getExpress_price() {
                    return express_price;
                }

                public void setExpress_price(String express_price) {
                    this.express_price = express_price;
                }

                public List<String> getImg_arr() {
                    return img_arr;
                }

                public void setImg_arr(List<String> img_arr) {
                    this.img_arr = img_arr;
                }
            }
        }
    }
}
