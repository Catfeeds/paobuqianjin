package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */

public class CircleType2Response {

    /**
     {"error":0,"message":"success","data":[{"tagid":1,"name":"热血少年"},{"tagid":2,"name":"腹黑男"},{"tagid":3,"name":"90后"},{"tagid":4,"name":"爱运动"},{"tagid":5,"name":"美少女"},{"tagid":6,"name":"飞毛腿"},{"tagid":7,"name":"北包客"},{"tagid":8,"name":"铲屎官"},{"tagid":9,"name":"女神"},{"tagid":10,"name":"耳机族"},{"tagid":11,"name":"魅力大叔"},{"tagid":12,"name":"大长腿"},{"tagid":13,"name":"精力充沛"},{"tagid":14,"name":"生长达人"}]}
     */

    private int error;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public class DataBean {
        private String tagid;
        private String name;
        private boolean isSelect;

        public String getName() {
            return name;
        }

        public String getTagid() {
            return tagid;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

}
