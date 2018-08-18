package com.paobuqianjin.pbq.step.data.bean.gson.response;

import com.paobuqianjin.pbq.step.data.bean.table.ChatGroupInfo;
import com.paobuqianjin.pbq.step.data.bean.table.ChatUserInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */
public class ChatUserInfoResponse {
    private int error;
    private String message;
    private List<ChatUserInfo> data;

    public List<ChatUserInfo> getData() {
        return data;
    }
}
