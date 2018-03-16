package com.paobuqianjin.pbq.step.presenter.im;

import com.paobuqianjin.pbq.step.data.bean.gson.response.CircleDetailResponse;

/**
 * Created by pbq on 2018/3/16.
 */

public interface QueryRedPkgInterface extends CallBackInterface {
    public void response(CircleDetailResponse circleDetailResponse);
}
