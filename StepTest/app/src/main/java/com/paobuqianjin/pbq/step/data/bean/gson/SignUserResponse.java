package com.paobuqianjin.pbq.step.data.bean.gson;

/**
 * Created by pbq on 2017/12/22.
 */

public class SignUserResponse {
    /**
     * error : 0
     * message : 注册成功
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
        return "SignUserResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
