package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by pbq on 2018/3/5.
 */
/*
@className :PutVoteResponse
*@date 2018/3/5
*@author
*@description 点赞，取消点赞返回
*/
public class PutVoteResponse {
    /**
     * error : 0
     * message : 取消赞成功
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
        return "PutVoteResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
