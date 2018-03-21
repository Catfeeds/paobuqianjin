package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/2/8.
 */
/*
@className :CrashResponse
*@date 2018/2/8
*@author
*@description 提现返回类型
*/
public class CrashResponse {
    /**
     * error : 0
     * message : 提现成功
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
        return "CrashResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
