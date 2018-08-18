package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by Administrator on 2018/7/18.
 */
public class PagenationBean {
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

    @Override
    public String toString() {
        return "PagenationBean{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                '}';
    }
}