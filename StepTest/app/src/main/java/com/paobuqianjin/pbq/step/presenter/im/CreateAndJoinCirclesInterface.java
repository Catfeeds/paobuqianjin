package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;
import com.paobuqianjin.pbq.step.data.bean.gson.response.MyCreateCircleResponse;

/**
 * Created by pbq on 2018/1/4.
 */

public interface CreateAndJoinCirclesInterface extends CallBackInterface {
    public void response(MyCreateCircleResponse myCreateCircleResponse);

    public void response(ErrorCode errorCode);

}
