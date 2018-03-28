package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/3/28.
 */

public class FriendAddResponse {
    /**
     * error : 0
     * message : success
     * data : {"in_system":[{"name":"额","phone":"18588278880","userid":1,"nickname":"嗯额","avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","follow_type":3}],"out_system":[{"name":"金赫","phone":"18665787652"},{"name":"专线电话","phone":"02868654077"},{"name":"专线电话","phone":"02868654084"},{"name":"专线电话","phone":"02886544200"},{"name":"专线电话","phone":"07507841000"},{"name":"江靖","phone":"13048807624"},{"name":"陈成明","phone":"13066979761"},{"name":"陈李龙","phone":"13243827037"},{"name":"杨德东","phone":"13267103391"},{"name":"刘伟颐","phone":"13280863710"},{"name":"杨雄","phone":"13265738125"},{"name":"贺泽平","phone":"13422393882"},{"name":"罗紫英","phone":"13430515468"},{"name":"家里","phone":"13479678455"},{"name":"黄卫娟弟弟","phone":"13510436862"},{"name":"黄兵兵","phone":"13510632936"},{"name":"龙庆河","phone":"13510826286"},{"name":"向玫","phone":"13548975850"},{"name":"小姑父","phone":"13576609203"},{"name":"罗珊珊","phone":"13580876905"},{"name":"黄延平","phone":"13602511853"},{"name":"邓光仁","phone":"13602515269"},{"name":"二舅","phone":"13619647312"},{"name":"吴健雄","phone":"13622371361"},{"name":"黄娟娟","phone":"13642846240"},{"name":"李卫平","phone":"13689531456"},{"name":"牛伟峰","phone":"13710945583"},{"name":"余超","phone":"13724241853"},{"name":"头莲","phone":"13766200578"},{"name":"陆莹","phone":"13769696250"},{"name":"肖承睿","phone":"13798346540"},{"name":"陈中仁","phone":"13823737358"},{"name":"刘彬","phone":"13916018538"},{"name":"黄钦平","phone":"15006487795"},{"name":"双乐辉","phone":"15012936695"},{"name":"郑思平","phone":"15034983378"},{"name":"杨时林","phone":"15688894376"},{"name":"焦健","phone":"15805539414"},{"name":"贺小东","phone":"15820485601"},{"name":"李希斌","phone":"15863340106"},{"name":"田根华","phone":"15914133257"},{"name":"黄文平","phone":"15914180971"},{"name":"张细龙","phone":"15914187972"},{"name":"李波","phone":"15932905364"},{"name":"黄丹","phone":"15979656009"},{"name":"黄水华","phone":"15985964910"},{"name":"方宇","phone":"17727960446"},{"name":"漆辉龙","phone":"17770614729"},{"name":"金博","phone":"18029849004"},{"name":"黄丹丹","phone":"18126164912"},{"name":"刘小山","phone":"18370615911"},{"name":"杨凯","phone":"18386176045"},{"name":"李轩","phone":"18500234155"},{"name":"王志峰","phone":"18510511392"},{"name":"黄小东","phone":"18551812513"},{"name":"周黎明","phone":"18588409415"},{"name":"李菲","phone":"18607594289"},{"name":"彭士礼","phone":"18664363010"},{"name":"黄佩佩","phone":"18665968568"},{"name":"龙志刚","phone":"18675577646"},{"name":"刘平","phone":"18688773597"},{"name":"韩永亮","phone":"18764301636"},{"name":"韩永亮","phone":"18811538120"},{"name":"王鑫璐","phone":"18820916084"},{"name":"李广龙","phone":"18879602868"},{"name":"廖总","phone":"18879694117"},{"name":"张孔权","phone":"18899776401"},{"name":"许荣强","phone":"18910987478"},{"name":"陈金丁","phone":"18923473782"},{"name":"尹文景","phone":"18950106284"},{"name":"专线电话","phone":"4008016062"},{"name":"专线电话","phone":"4008257119"},{"name":"专线电话","phone":"4008640166"},{"name":"专线电话","phone":"4009180338"}]}
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
        return "FriendAddResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        private List<InSystemBean> in_system;
        private List<OutSystemBean> out_system;

        public List<InSystemBean> getIn_system() {
            return in_system;
        }

        public void setIn_system(List<InSystemBean> in_system) {
            this.in_system = in_system;
        }

        public List<OutSystemBean> getOut_system() {
            return out_system;
        }

        public void setOut_system(List<OutSystemBean> out_system) {
            this.out_system = out_system;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "in_system=" + in_system +
                    ", out_system=" + out_system +
                    '}';
        }

        public static class InSystemBean {
            /**
             * name : 额
             * phone : 18588278880
             * userid : 1
             * nickname : 嗯额
             * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg
             * follow_type : 3
             */

            private String name;
            private String phone;
            private int userid;
            private String nickname;
            private String avatar;
            private int follow_type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

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

            public int getFollow_type() {
                return follow_type;
            }

            public void setFollow_type(int follow_type) {
                this.follow_type = follow_type;
            }

            @Override
            public String toString() {
                return "InSystemBean{" +
                        "name='" + name + '\'' +
                        ", phone='" + phone + '\'' +
                        ", userid=" + userid +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", follow_type=" + follow_type +
                        '}';
            }
        }

        public static class OutSystemBean {
            /**
             * name : 金赫
             * phone : 18665787652
             */

            private String name;
            private String phone;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            @Override
            public String toString() {
                return "OutSystemBean{" +
                        "name='" + name + '\'' +
                        ", phone='" + phone + '\'' +
                        '}';
            }
        }
    }
}
