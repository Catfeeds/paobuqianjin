package com.paobuqianjin.pbq.step.data.bean.gson;

/**
 * Created by pbq on 2017/12/25.
 */

public class UserIdInviterResponse {
    /**
     * error : 0
     * message : success
     * data : 3
     */

    private int error;
    private String message;
    private int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
