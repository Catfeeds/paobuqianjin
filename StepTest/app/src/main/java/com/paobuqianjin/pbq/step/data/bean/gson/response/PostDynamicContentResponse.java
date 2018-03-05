package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/5.
 */
/*
@className :PostDynamicContentResponse
*@date 2018/3/5
*@author
*@description 发表评论时的返回
*/
public class PostDynamicContentResponse {
    /**
     * error : 0
     * message : success
     */

    private int error;
    private String message;

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

    @Override
    public String toString() {
        return "PostDynamicContentResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
