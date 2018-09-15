package com.paobuqianjin.pbq.step.data.bean;

/**
 * Created by pbq on 2018/8/13.
 */

public class AdObject {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private String target_url;
    private String img_url;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    private int rid;

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "AdObject{" +
                "title='" + title + '\'' +
                ", target_url='" + target_url + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
