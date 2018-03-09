package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ReceiveTaskResponse;

/**
 * Created by pbq on 2018/3/9.
 */

public interface ReceiveTaskInterface extends CallBackInterface {
    public void response(ReceiveTaskResponse receiveTaskResponse);

    public void receiveTask(int taskId);
}
