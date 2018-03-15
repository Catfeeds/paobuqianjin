package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.LoginOutResponse;

/**
 * Created by pbq on 2018/2/1.
 */

public interface CircleDetailInterface extends CallBackInterface {
    public void response(CircleDetailResponse circleDetailResponse);

    public void response(LoginOutResponse loginOutResponse);
}
