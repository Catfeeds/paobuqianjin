package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/9.
 */
/*
@className :RecPayResponse
*@date 2018/3/9
*@author
*@description 领取奖励返回
*/
public class RecPayResponse {
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
        return "RecPayResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
