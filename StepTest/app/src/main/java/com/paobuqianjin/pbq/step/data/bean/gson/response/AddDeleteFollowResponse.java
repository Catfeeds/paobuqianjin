package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/15.
 */

public class AddDeleteFollowResponse {
    /**
     * error : 0
     * message : 取消关注成功
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
        return "AddDeleteFollowResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
