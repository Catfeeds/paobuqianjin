package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/7/2.
 * {"error":0,"message":"success","data":[{"url":"http:\/\/runmoney-1255484416.cos.ap-guangzhou.myqcloud.com\/CROP_1530503333740.jpg"},{"url":"http:\/\/runmoney-1255484416.cos.ap-guangzhou.myqcloud
 */
public class ChatImagesResponse {
    private int error;
    private String message;
    private List<ChatImageBean> data;

    public class ChatImageBean implements Serializable {
        String url;

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public List<ChatImageBean> getData() {
        return data;
    }
}
