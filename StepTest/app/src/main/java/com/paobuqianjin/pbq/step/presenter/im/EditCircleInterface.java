package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleTargetResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.EditCircleResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/3/28.
 */

public interface EditCircleInterface extends CallBackInterface {
    public void response(ErrorCode errorCode);

    public void response(CircleTargetResponse circleTargetResponse);

    public void response(EditCircleResponse editCircleResponse);
}