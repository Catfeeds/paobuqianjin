package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/2/27.
 */

public class ErrorCode {
    /**
     * error : -1
     * message : 请勿重复邀请
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
        return "ErrorCode{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
