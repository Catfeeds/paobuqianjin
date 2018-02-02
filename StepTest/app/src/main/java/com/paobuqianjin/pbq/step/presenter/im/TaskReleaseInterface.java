package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskReleaseResponse;

/**
 * Created by pbq on 2018/2/2.
 */

public interface TaskReleaseInterface extends CallBackInterface {
    public void response(TaskReleaseResponse taskReleaseResponse);
}
