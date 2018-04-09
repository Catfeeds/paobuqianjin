package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.AddDeleteFollowResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.ErrorCode;

/**
 * Created by pbq on 2018/3/15.
 */

public interface AddDeleteFollowInterface extends CallBackInterface {
    public void response(AddDeleteFollowResponse addDeleteFollowResponse);

    public void response(ErrorCode errorCode);
}
