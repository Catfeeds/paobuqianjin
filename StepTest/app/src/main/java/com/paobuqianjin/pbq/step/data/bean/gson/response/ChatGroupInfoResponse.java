package com.paobuqianjin.pbq.step.data.bean.gson.response;

import com.paobuqianjin.pbq.step.data.bean.table.ChatGroupInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */
public class ChatGroupInfoResponse {
    private int error;
    private String message;
    private List<ChatGroupInfo> data;

    public List<ChatGroupInfo> getData() {
        return data;
    }
}
