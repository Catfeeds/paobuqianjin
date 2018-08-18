package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/7/2.
 * {"error":0,"message":"success","data":[{"url":"http:\/\/runmoney-1255484416.cos.ap-guangzhou.myqcloud.com\/CROP_1530503333740.jpg"},{"url":"http:\/\/runmoney-1255484416.cos.ap-guangzhou.myqcloud
 */
public class ChatCircleInfoResponse {
    private int error;
    private String message;
    private ChatCircleInfoBean data;

    public ChatCircleInfoBean getData() {
        return data;
    }

    public class ChatCircleInfoBean{
        private String group_delete;
        private List<ChatImageBean> images;

        public List<ChatImageBean> getImages() {
            return images;
        }

        public String getGroup_delete() {
            return group_delete;
        }
    }

    public class ChatImageBean implements Serializable {
        String url;

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

}
