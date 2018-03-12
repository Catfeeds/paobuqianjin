package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.param.JoinCircleParam;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.JoinCircleResponse;

/**
 * Created by pbq on 2018/3/12.
 */

public interface JoinCircleInterface extends CallBackInterface {
    public void response(JoinCircleResponse joinCircleResponse);

    public void response(ErrorCode errorCode);
}
