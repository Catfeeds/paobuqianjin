package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.StepDollarDetailResponse;
import com.paobuqianjin.pbq.step.data.bean.gson.response.UserInfoResponse;

/**
 * Created by pbq on 2018/2/26.
 */

public interface StepDollarDetailInterface extends CallBackInterface {
    public void response(StepDollarDetailResponse stepDollarDetailResponse);

    public void response(UserInfoResponse userInfoResponse);
}
