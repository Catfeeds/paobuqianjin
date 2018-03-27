package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/3/27.
 */
/*
@className :MessageSystemResponse
*@date 2018/3/27
*@author
*@description 系统消息
*/
public class MessageSystemResponse {
    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":1,"totalCount":2},"data":[{"id":20,"dynamicid":0,"from_userid":0,"from_nickanme":null,"from_avatar":null,"to_userid":1,"to_nickanme":"嗯额","typeid":4,"title":"红包你还未领!","content":"红包你还未领!","dynamic":null,"images":[],"create_time":1519634237,"dynamicdata":[]},{"id":57,"dynamicid":0,"from_userid":0,"from_nickanme":null,"from_avatar":null,"to_userid":1,"to_nickanme":"嗯额","typeid":4,"title":"系统升级","content":"系统将于2018年3月25日至2018年3月30之间进行系统维护，届时系统将无法登录，给大家带来了不便敬请原谅。","dynamic":null,"images":[],"create_time":0,"dynamicdata":[]}]}
     */

    private int error;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX implements Serializable {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":1,"totalCount":2}
         * data : [{"id":20,"dynamicid":0,"from_userid":0,"from_nickanme":null,"from_avatar":null,"to_userid":1,"to_nickanme":"嗯额","typeid":4,"title":"红包你还未领!","content":"红包你还未领!","dynamic":null,"images":[],"create_time":1519634237,"dynamicdata":[]},{"id":57,"dynamicid":0,"from_userid":0,"from_nickanme":null,"from_avatar":null,"to_userid":1,"to_nickanme":"嗯额","typeid":4,"title":"系统升级","content":"系统将于2018年3月25日至2018年3月30之间进行系统维护，届时系统将无法登录，给大家带来了不便敬请原谅。","dynamic":null,"images":[],"create_time":0,"dynamicdata":[]}]
         */

        private PagenationBean pagenation;
        private List<DataBean> data;

        public PagenationBean getPagenation() {
            return pagenation;
        }

        public void setPagenation(PagenationBean pagenation) {
            this.pagenation = pagenation;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 10
             * totalPage : 1
             * totalCount : 2
             */

            private int page;
            private int pageSize;
            private int totalPage;
            private int totalCount;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }
        }

        public static class DataBean implements Serializable {
            /**
             * id : 20
             * dynamicid : 0
             * from_userid : 0
             * from_nickanme : null
             * from_avatar : null
             * to_userid : 1
             * to_nickanme : 嗯额
             * typeid : 4
             * title : 红包你还未领!
             * content : 红包你还未领!
             * dynamic : null
             * images : []
             * create_time : 1519634237
             * dynamicdata : []
             */

            private int id;
            private int dynamicid;
            private int from_userid;
            private Object from_nickanme;
            private Object from_avatar;
            private int to_userid;
            private String to_nickanme;
            private int typeid;
            private String title;
            private String content;
            private Object dynamic;
            private int create_time;
            private List<?> images;
            private List<?> dynamicdata;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDynamicid() {
                return dynamicid;
            }

            public void setDynamicid(int dynamicid) {
                this.dynamicid = dynamicid;
            }

            public int getFrom_userid() {
                return from_userid;
            }

            public void setFrom_userid(int from_userid) {
                this.from_userid = from_userid;
            }

            public Object getFrom_nickanme() {
                return from_nickanme;
            }

            public void setFrom_nickanme(Object from_nickanme) {
                this.from_nickanme = from_nickanme;
            }

            public Object getFrom_avatar() {
                return from_avatar;
            }

            public void setFrom_avatar(Object from_avatar) {
                this.from_avatar = from_avatar;
            }

            public int getTo_userid() {
                return to_userid;
            }

            public void setTo_userid(int to_userid) {
                this.to_userid = to_userid;
            }

            public String getTo_nickanme() {
                return to_nickanme;
            }

            public void setTo_nickanme(String to_nickanme) {
                this.to_nickanme = to_nickanme;
            }

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getDynamic() {
                return dynamic;
            }

            public void setDynamic(Object dynamic) {
                this.dynamic = dynamic;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }

            public List<?> getDynamicdata() {
                return dynamicdata;
            }

            public void setDynamicdata(List<?> dynamicdata) {
                this.dynamicdata = dynamicdata;
            }
        }
    }
}
