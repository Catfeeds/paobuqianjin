package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by Administrator on 2018/5/10.
 */

public class NormalResponse {
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
}