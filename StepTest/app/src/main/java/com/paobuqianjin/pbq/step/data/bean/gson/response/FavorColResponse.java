package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/29.
 */
/*
@className :FavorColResponse v1/CoalitionLine/favorColumn tianmao and taobao jiekou
*@date 2018/11/29
*@author
*@description
*/
public class FavorColResponse {
    /**
     * error : 0
     * message : success
     * data : [{"favorites_id":"18903459","favorites_title":"9块9邮","type":"1","icon_url":"https://img.alicdn.com/bao/uploaded/i3/4131061455/O1CN011McQjzajp172OcG_!!0-item_pic.jpg_220x220_.webp","column":1},{"favorites_id":"18890880","favorites_title":"20元封顶","type":"1","icon_url":"https://img.alicdn.com/bao/uploaded/i1/2116537140/TB1zOS4eiqAXuNjy1XdXXaYcVXa_!!0-item_pic.jpg_220x220_.webp","column":1},{"favorites_id":"18890730","favorites_title":"美食","type":"1","icon_url":"https://img.alicdn.com/bao/uploaded/i3/TB1YgDNMXXXXXaQXXXXXXXXXXXX_!!0-item_pic.jpg_220x220_.webp","column":1},{"favorites_title":"淘宝特卖","icon_url":"https://img.alicdn.com/bao/uploaded/i3/4131061455/O1CN011McQjzajp172OcG_!!0-item_pic.jpg_220x220_.webp","android":"https://jhs.m.taobao.com/?spm=a219t.7664554.1998457203.222.512235d9ekJ37V","favorites_id":0,"column":2},{"favorites_title":"聚划算","icon_url":"https://img.alicdn.com/bao/uploaded/i3/4131061455/O1CN011McQjzajp172OcG_!!0-item_pic.jpg_220x220_.webp","android":"https://jhs.m.taobao.com/?spm=a219t.7664554.1998457203.222.512235d9ekJ37V","favorites_id":0,"column":2}]
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

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * favorites_id : 18903459
         * favorites_title : 9块9邮
         * type : 1
         * icon_url : https://img.alicdn.com/bao/uploaded/i3/4131061455/O1CN011McQjzajp172OcG_!!0-item_pic.jpg_220x220_.webp
         * column : 1
         * android : https://jhs.m.taobao.com/?spm=a219t.7664554.1998457203.222.512235d9ekJ37V
         */

        private String favorites_id;
        private String favorites_title;
        private String type;
        private String icon_url;
        private int column;
        private String android;

        public String getFavorites_id() {
            return favorites_id;
        }

        public void setFavorites_id(String favorites_id) {
            this.favorites_id = favorites_id;
        }

        public String getFavorites_title() {
            return favorites_title;
        }

        public void setFavorites_title(String favorites_title) {
            this.favorites_title = favorites_title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public String getAndroid() {
            return android;
        }

        public void setAndroid(String android) {
            this.android = android;
        }
    }
}
