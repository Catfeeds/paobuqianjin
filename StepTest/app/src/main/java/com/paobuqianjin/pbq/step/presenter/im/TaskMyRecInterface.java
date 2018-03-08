package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.MyRecTaskRecordResponse;

/**
 * Created by pbq on 2018/3/8.
 */
/*
@className :TaskMyRecInterface
*@date 2018/3/8
*@author
*@description 我领取的任务列表
*/
public interface TaskMyRecInterface extends CallBackInterface {
    public void response(MyRecTaskRecordResponse myRecvTaskRecordResponse);
}
