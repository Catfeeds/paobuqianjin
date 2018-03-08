package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskRecDetailResponse;

/**
 * Created by pbq on 2018/3/8.
 */

public interface TaskDetailRecInterface extends CallBackInterface {
    public void response(TaskRecDetailResponse taskRecDetailResponse);
}
