package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class CircleTagResponse {

    /**
     * error : 0
     * message : success
     * data : {"HotCircleTags":[{"id":1,"name":"热血少年"},{"id":2,"name":"腹黑男"},{"id":3,"name":"90后"},{"id":4,"name":"爱运动"},{"id":5,"name":"美少女"},{"id":6,"name":"飞毛腿"},{"id":7,"name":"北包客"}],"AllCircleTags":[{"id":1,"name":"热血少年"},{"id":2,"name":"腹黑男"},{"id":3,"name":"90后"},{"id":4,"name":"爱运动"},{"id":5,"name":"美少女"},{"id":6,"name":"飞毛腿"},{"id":7,"name":"北包客"},{"id":8,"name":"铲屎官"},{"id":9,"name":"女神"},{"id":10,"name":"耳机族"},{"id":11,"name":"魅力大叔"},{"id":12,"name":"大长腿"},{"id":13,"name":"精力充沛"},{"id":14,"name":"生长达人"}]}
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
        return "CircleTagResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        private List<HotCircleTagsBean> HotCircleTags;

        @Override
        public String toString() {
            return "DataBean{" +
                    "AllCircleTags=" + AllCircleTags +
                    '}';
        }

        private List<AllCircleTagsBean> AllCircleTags;

        public List<HotCircleTagsBean> getHotCircleTags() {
            return HotCircleTags;
        }

        public void setHotCircleTags(List<HotCircleTagsBean> HotCircleTags) {
            this.HotCircleTags = HotCircleTags;
        }

        public List<AllCircleTagsBean> getAllCircleTags() {
            return AllCircleTags;
        }

        public void setAllCircleTags(List<AllCircleTagsBean> AllCircleTags) {
            this.AllCircleTags = AllCircleTags;
        }

        public static class HotCircleTagsBean {
            /**
             * id : 1
             * name : 热血少年
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "HotCircleTagsBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }
        }

        public static class AllCircleTagsBean {
            /**
             * id : 1
             * name : 热血少年
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "AllCircleTagsBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }
        }
    }
}
