package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.TaskMyReleaseResponse;

/**
 * Created by pbq on 2018/2/27.
 */

public interface MyReleaseTaskInterface extends CallBackInterface {
    public void response(TaskMyReleaseResponse taskMyReleaseResponse);

    public void response(ErrorCode errorCode);
}
