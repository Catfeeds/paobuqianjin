package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/24.
 */
/*
@className :UserInfoSetResponse
*@date 2018/3/24
*@author
*@description 第一次登陆设置资料返回
*/
public class UserInfoSetResponse {
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
        return "UserInfoSetResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
