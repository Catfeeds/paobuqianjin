package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskDetailResponse;

/**
 * Created by pbq on 2018/2/2.
 */

public interface TaskListInterface extends CallBackInterface {
    public void response(TaskDetailResponse taskIndexResponse);
}
